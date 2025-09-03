package com.wingflare.adapter.spring.mail;


import com.wingflare.api.email.EmailDrive;
import com.wingflare.api.email.MailAccount;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * Spring环境下EmailDrive具体实现
 */
public class SpringEmailDriveImpl implements EmailDrive {


    private final JavaMailSender mailSender;

    public SpringEmailDriveImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * 从默认JavaMailSender提取默认MailAccount（反向适配，确保默认配置与Spring一致）
     */
    private MailAccount getDefaultMailAccount() {
        if (!(mailSender instanceof JavaMailSenderImpl senderImpl)) {
            throw new IllegalArgumentException("默认JavaMailSender必须是JavaMailSenderImpl类型");
        }

        MailAccount defaultAccount = new MailAccount();
        Properties springMailProps = senderImpl.getJavaMailProperties();

        // 1. 基础SMTP配置（从Spring默认Sender提取）
        defaultAccount.setHost(senderImpl.getHost())
                .setPort(senderImpl.getPort())
                .setUser(senderImpl.getUsername())
                .setPass(senderImpl.getPassword())
                .setFrom(springMailProps.getProperty("mail.from", senderImpl.getUsername()))
                .setAuth(Boolean.parseBoolean(springMailProps.getProperty("mail.smtp.auth", "true")));

        // 2. 编码配置
        String springCharset = senderImpl.getDefaultEncoding();
        defaultAccount.setCharset(StringUtils.hasText(springCharset) ? Charset.forName(springCharset) : Charset.defaultCharset());

        // 3. 安全连接配置
        defaultAccount.setSslEnable(Boolean.parseBoolean(springMailProps.getProperty("mail.smtp.ssl.enable", "false")))
                .setStarttlsEnable(Boolean.parseBoolean(springMailProps.getProperty("mail.smtp.starttls.enable", "false")))
                .setSslProtocols(springMailProps.getProperty("mail.smtp.ssl.protocols"));

        // 4. Socket工厂配置
        defaultAccount.setSocketFactoryClass(springMailProps.getProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"))
                .setSocketFactoryFallback(Boolean.parseBoolean(springMailProps.getProperty("mail.smtp.socketFactory.fallback", "true")));
        String socketPort = springMailProps.getProperty("mail.smtp.socketFactory.port");
        if (StringUtils.hasText(socketPort)) {
            defaultAccount.setSocketFactoryPort(Integer.parseInt(socketPort));
        }

        // 5. 超时配置
        String timeout = springMailProps.getProperty("mail.smtp.timeout");
        String connTimeout = springMailProps.getProperty("mail.smtp.connectiontimeout");
        String writeTimeout = springMailProps.getProperty("mail.smtp.writetimeout");
        if (StringUtils.hasText(timeout)) defaultAccount.setTimeout(Long.parseLong(timeout));
        if (StringUtils.hasText(connTimeout)) defaultAccount.setConnectionTimeout(Long.parseLong(connTimeout));
        if (StringUtils.hasText(writeTimeout)) defaultAccount.setWriteTimeout(Long.parseLong(writeTimeout));

        // 6. 调试与参数拆分配置
        defaultAccount.setDebug(Boolean.parseBoolean(springMailProps.getProperty("mail.debug", "false")))
                .setSplitlongparameters(Boolean.parseBoolean(springMailProps.getProperty("mail.mime.splitlongparameters", "false")));

        // 7. 自定义属性
        springMailProps.forEach((key, value) -> {
            if (key instanceof String && value instanceof String) {
                defaultAccount.setCustomProperty((String) key, value);
            }
        });

        return defaultAccount;
    }

    /**
     * 根据MailAccount创建JavaMailSender
     */
    private JavaMailSender createSenderFromMailAccount(MailAccount mailAccount) {
        // 获取MailAccount的SMTP配置（包含系统属性设置）
        Properties smtpProps = mailAccount.getSmtpProps();

        // 构建JavaMailSenderImpl
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(mailAccount.getHost());
        sender.setPort(mailAccount.getPort() != null ? mailAccount.getPort() : 25);
        sender.setUsername(mailAccount.getUser());
        sender.setPassword(mailAccount.getPass());

        // 编码配置
        Charset accountCharset = mailAccount.getCharset();
        sender.setDefaultEncoding(accountCharset != null ? accountCharset.name() : "UTF-8");

        // 注入SMTP属性
        sender.setJavaMailProperties(smtpProps);

        return sender;
    }

    /**
     * 解析收件人（字符串/集合 → 数组）
     */
    private String[] parseRecipients(Object recipients) {
        return switch (recipients) {
            case String str -> StringUtils.hasText(str) ? str.replaceAll("[;,]", ",").split(",") : new String[0];
            case Collection<?> coll -> coll.stream()
                    .filter(item -> item != null && StringUtils.hasText(item.toString()))
                    .map(Object::toString)
                    .toArray(String[]::new);
            case null, default -> new String[0];
        };
    }

    /**
     * 编码附件文件名（根据MailAccount配置）
     */
    private String encodeFileName(Charset charset, String fileName) {

        if (!StringUtils.hasText(fileName)) {
            return fileName;
        }

        Charset nowCharset = charset != null ? charset : Charset.defaultCharset();

        try {
            // 使用RFC 2047标准编码文件名
            return "=?" + nowCharset.name() + "?B?" +
                    java.util.Base64.getEncoder().encodeToString(fileName.getBytes(nowCharset)) + "?=";
        } catch (Exception e) {
            // 编码失败时返回原始文件名
            return fileName;
        }
    }

    /**
     * 通用邮件发送逻辑（已修正MimeMessageHelper初始化错误）
     */
    private String doSend(MailAccount mailAccount, Object tos, Object ccs, Object bccs,
                          String subject, String content, Map<String, InputStream> imageMap,
                          boolean isHtml, File... files) {
        try {
            // 创建适配MailAccount的JavaMailSender
            JavaMailSender sender = createSenderFromMailAccount(mailAccount);
            MimeMessage mimeMessage = sender.createMimeMessage();

            // 配置MimeMessageHelper（修正初始化错误）
            Charset accountCharset = mailAccount.getCharset() != null ? mailAccount.getCharset() : Charset.defaultCharset();
            // 正确的构造方法：不包含encodefilename参数
            MimeMessageHelper helper = new MimeMessageHelper(
                    mimeMessage, true, accountCharset.name()
            );

            // 基础邮件信息
            String from = StringUtils.hasText(mailAccount.getFrom()) ? mailAccount.getFrom() : mailAccount.getUser();
            helper.setFrom(from);
            helper.setTo(parseRecipients(tos));
            helper.setSubject(subject);
            helper.setText(content, isHtml);

            // 抄送/密送
            String[] ccArr = parseRecipients(ccs);
            String[] bccArr = parseRecipients(bccs);
            if (ccArr.length > 0) helper.setCc(ccArr);
            if (bccArr.length > 0) helper.setBcc(bccArr);

            // 内嵌图片
            if (!CollectionUtils.isEmpty(imageMap)) {
                for (Map.Entry<String, InputStream> entry : imageMap.entrySet()) {
                    String cid = entry.getKey();
                    helper.addInline(cid, entry::getValue, "image/*");
                }
            }

            // 附件（根据MailAccount配置处理文件名编码）
            if (files != null) {
                for (File file : files) {
                    if (file.exists() && file.isFile()) {
                        if (mailAccount.isEncodefilename()) {
                            String fileName = encodeFileName(mailAccount.getCharset(), file.getName());
                            helper.addAttachment(fileName, file);
                        } else {
                            helper.addAttachment(file.getName(), file);
                        }
                    }
                }
            }

            // 发送邮件并返回message-id
            sender.send(mimeMessage);
            return mimeMessage.getMessageID();

        } catch (MessagingException e) {
            throw new RuntimeException("邮件发送失败（账户：" + mailAccount.getUser() + "）：" + e.getMessage(), e);
        }
    }

    private String doSend(Object tos, Object ccs, Object bccs,
                          String subject, String content, Map<String, InputStream> imageMap,
                          boolean isHtml, File... files) {
        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

            // 基础邮件信息
            helper.setTo(parseRecipients(tos));
            helper.setSubject(subject);
            helper.setText(content, isHtml);

            // 抄送/密送
            String[] ccArr = parseRecipients(ccs);
            String[] bccArr = parseRecipients(bccs);
            if (ccArr.length > 0) helper.setCc(ccArr);
            if (bccArr.length > 0) helper.setBcc(bccArr);

            // 内嵌图片
            if (!CollectionUtils.isEmpty(imageMap)) {
                for (Map.Entry<String, InputStream> entry : imageMap.entrySet()) {
                    String cid = entry.getKey();
                    helper.addInline(cid, entry::getValue, "image/*");
                }
            }

            JavaMailSenderImpl senderImpl = (JavaMailSenderImpl) mailSender;

            // 附件（根据MailAccount配置处理文件名编码）
            if (files != null) {
                for (File file : files) {
                    if (file.exists() && file.isFile()) {
                        if (senderImpl.getDefaultEncoding() != null) {
                            String fileName = encodeFileName(Charset.forName(senderImpl.getDefaultEncoding()), file.getName());
                            helper.addAttachment(fileName, file);
                        } else {
                            helper.addAttachment(file.getName(), file);
                        }
                    }
                }
            }

            // 发送邮件并返回message-id
            mailSender.send(mimeMessage);
            return mimeMessage.getMessageID();

        } catch (MessagingException e) {
            throw new RuntimeException("邮件发送失败：" + e.getMessage(), e);
        }
    }

    // ---------------------- 实现EmailDrive接口（默认账户） ----------------------

    @Override
    public String sendText(String to, String subject, String content, File... files) {
        return doSend(to, null, null, subject, content, null, false, files);
    }

    @Override
    public String sendHtml(String to, String subject, String content, File... files) {
        return doSend(to, null, null, subject, content, null, true, files);
    }

    @Override
    public String send(String to, String subject, String content, boolean isHtml, File... files) {
        return doSend(to, null, null, subject, content, null, isHtml, files);
    }

    @Override
    public String send(String to, String cc, String bcc, String subject, String content, boolean isHtml, File... files) {
        return doSend(to, cc, bcc, subject, content, null, isHtml, files);
    }

    @Override
    public String sendText(Collection<String> tos, String subject, String content, File... files) {
        return doSend(tos, null, null, subject, content, null, false, files);
    }

    @Override
    public String sendHtml(Collection<String> tos, String subject, String content, File... files) {
        return doSend(tos, null, null, subject, content, null, true, files);
    }

    @Override
    public String send(Collection<String> tos, String subject, String content, boolean isHtml, File... files) {
        return doSend(tos, null, null, subject, content, null, isHtml, files);
    }

    @Override
    public String send(Collection<String> tos, Collection<String> ccs, Collection<String> bccs, String subject, String content, boolean isHtml, File... files) {
        return doSend(tos, ccs, bccs, subject, content, null, isHtml, files);
    }

    @Override
    public String sendHtml(String to, String subject, String content, Map<String, InputStream> imageMap, File... files) {
        return doSend(to, null, null, subject, content, imageMap, true, files);
    }

    @Override
    public String send(String to, String subject, String content, Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return doSend(to, null, null, subject, content, imageMap, isHtml, files);
    }

    @Override
    public String send(String to, String cc, String bcc, String subject, String content, Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return doSend(to, cc, bcc, subject, content, imageMap, isHtml, files);
    }

    @Override
    public String sendHtml(Collection<String> tos, String subject, String content, Map<String, InputStream> imageMap, File... files) {
        return doSend(tos, null, null, subject, content, imageMap, true, files);
    }

    @Override
    public String send(Collection<String> tos, String subject, String content, Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return doSend(tos, null, null, subject, content, imageMap, isHtml, files);
    }

    @Override
    public String send(Collection<String> tos, Collection<String> ccs, Collection<String> bccs, String subject, String content, Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return doSend(tos, ccs, bccs, subject, content, imageMap, isHtml, files);
    }

    // ---------------------- 实现EmailDrive接口（自定义MailAccount） ----------------------

    @Override
    public String send(MailAccount mailAccount, String to, String subject, String content, boolean isHtml, File... files) {
        return doSend(mailAccount, to, null, null, subject, content, null, isHtml, files);
    }

    @Override
    public String send(MailAccount mailAccount, Collection<String> tos, String subject, String content, boolean isHtml, File... files) {
        return doSend(mailAccount, tos, null, null, subject, content, null, isHtml, files);
    }

    @Override
    public String send(MailAccount mailAccount, Collection<String> tos, Collection<String> ccs, Collection<String> bccs, String subject, String content, boolean isHtml, File... files) {
        return doSend(mailAccount, tos, ccs, bccs, subject, content, null, isHtml, files);
    }

    @Override
    public String send(MailAccount mailAccount, String to, String subject, String content, Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return doSend(mailAccount, to, null, null, subject, content, imageMap, isHtml, files);
    }

    @Override
    public String send(MailAccount mailAccount, Collection<String> tos, String subject, String content, Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return doSend(mailAccount, tos, null, null, subject, content, imageMap, isHtml, files);
    }

    @Override
    public String send(MailAccount mailAccount, Collection<String> tos, Collection<String> ccs, Collection<String> bccs, String subject, String content, Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return doSend(mailAccount, tos, ccs, bccs, subject, content, imageMap, isHtml, files);
    }

}
