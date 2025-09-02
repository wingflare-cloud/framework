package com.wingflare.lib.email;

import com.wingflare.api.email.EmailDrive;
import com.wingflare.api.email.MailAccount;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author: naizui.ycx
 * @date : 2025-09-02 19:20
 */
public class EmailUtil {

    private static volatile EmailDrive drive;

    private static EmailDrive getDrive() {
        if (drive == null) {
            synchronized (EmailUtil.class) {
                if (drive == null) {
                    ServiceLoader<EmailDrive> drives = ServiceLoader.load(EmailDrive.class);
                    for (EmailDrive nowDrive : drives) {
                        drive = nowDrive;
                        break;
                    }
                }
            }
        }
        return drive;
    }

    /**
     * 使用配置文件中设置的账户发送文本邮件，发送给单个或多个收件人<br>
     * 多个收件人可以使用逗号“,”分隔，也可以通过分号“;”分隔
     *
     * @param to      收件人
     * @param subject 标题
     * @param content 正文
     * @param files   附件列表
     * @return message-id
     */
    public static String sendText(String to, String subject, String content, File... files) {
        return getDrive().sendText(to, subject, content, files);
    }

    /**
     * 使用配置文件中设置的账户发送HTML邮件，发送给单个或多个收件人<br>
     * 多个收件人可以使用逗号“,”分隔，也可以通过分号“;”分隔
     *
     * @param to      收件人
     * @param subject 标题
     * @param content 正文
     * @param files   附件列表
     * @return message-id
     */
    public static String sendHtml(String to, String subject, String content, File... files) {
        return getDrive().sendHtml(to, subject, content, files);
    }

    /**
     * 使用配置文件中设置的账户发送邮件，发送单个或多个收件人<br>
     * 多个收件人可以使用逗号“,”分隔，也可以通过分号“;”分隔
     *
     * @param to      收件人
     * @param subject 标题
     * @param content 正文
     * @param isHtml  是否为HTML
     * @param files   附件列表
     * @return message-id
     */
    public static String send(String to, String subject, String content, boolean isHtml, File... files) {
        return getDrive().send(to, subject, content, isHtml, files);
    }

    /**
     * 使用配置文件中设置的账户发送邮件，发送单个或多个收件人<br>
     * 多个收件人、抄送人、密送人可以使用逗号“,”分隔，也可以通过分号“;”分隔
     *
     * @param to      收件人，可以使用逗号“,”分隔，也可以通过分号“;”分隔
     * @param cc      抄送人，可以使用逗号“,”分隔，也可以通过分号“;”分隔
     * @param bcc     密送人，可以使用逗号“,”分隔，也可以通过分号“;”分隔
     * @param subject 标题
     * @param content 正文
     * @param isHtml  是否为HTML
     * @param files   附件列表
     * @return message-id
     */
    public static String send(String to, String cc, String bcc, String subject, String content, boolean isHtml, File... files) {
        return getDrive().send(to, cc, bcc, subject, content, isHtml, files);
    }

    /**
     * 使用配置文件中设置的账户发送文本邮件，发送给多人
     *
     * @param tos     收件人列表
     * @param subject 标题
     * @param content 正文
     * @param files   附件列表
     * @return message-id
     */
    public static String sendText(Collection<String> tos, String subject, String content, File... files) {
        return getDrive().sendText(tos, subject, content, files);
    }

    /**
     * 使用配置文件中设置的账户发送HTML邮件，发送给多人
     *
     * @param tos     收件人列表
     * @param subject 标题
     * @param content 正文
     * @param files   附件列表
     * @return message-id
     */
    public static String sendHtml(Collection<String> tos, String subject, String content, File... files) {
        return getDrive().sendHtml(tos, subject, content, files);
    }

    /**
     * 使用配置文件中设置的账户发送邮件，发送给多人
     *
     * @param tos     收件人列表
     * @param subject 标题
     * @param content 正文
     * @param isHtml  是否为HTML
     * @param files   附件列表
     * @return message-id
     */
    public static String send(Collection<String> tos, String subject, String content, boolean isHtml, File... files) {
        return getDrive().send(tos, subject, content, isHtml, files);
    }

    /**
     * 使用配置文件中设置的账户发送邮件，发送给多人
     *
     * @param tos     收件人列表
     * @param ccs     抄送人列表，可以为null或空
     * @param bccs    密送人列表，可以为null或空
     * @param subject 标题
     * @param content 正文
     * @param isHtml  是否为HTML
     * @param files   附件列表
     * @return message-id
     */
    public static String send(Collection<String> tos, Collection<String> ccs, Collection<String> bccs, String subject, String content,
                boolean isHtml, File... files) {
        return getDrive().send(tos, ccs, bccs, subject, content, isHtml, files);
    }

    /**
     * 发送邮件给多人
     *
     * @param mailAccount 邮件认证对象
     * @param to          收件人，多个收件人逗号或者分号隔开
     * @param subject     标题
     * @param content     正文
     * @param isHtml      是否为HTML格式
     * @param files       附件列表
     * @return message-id
     */
    public static String send(MailAccount mailAccount, String to, String subject, String content, boolean isHtml, File... files) {
        return getDrive().send(mailAccount, to, subject, content, isHtml, files);
    }

    /**
     * 发送邮件给多人
     *
     * @param mailAccount 邮件帐户信息
     * @param tos         收件人列表
     * @param subject     标题
     * @param content     正文
     * @param isHtml      是否为HTML格式
     * @param files       附件列表
     * @return message-id
     */
    public static String send(MailAccount mailAccount, Collection<String> tos, String subject, String content, boolean isHtml, File... files) {
        return getDrive().send(mailAccount, tos, subject, content, isHtml, files);
    }

    /**
     * 发送邮件给多人
     *
     * @param mailAccount 邮件帐户信息
     * @param tos         收件人列表
     * @param ccs         抄送人列表，可以为null或空
     * @param bccs        密送人列表，可以为null或空
     * @param subject     标题
     * @param content     正文
     * @param isHtml      是否为HTML格式
     * @param files       附件列表
     * @return message-id
     */
    public static String send(MailAccount mailAccount, Collection<String> tos, Collection<String> ccs, Collection<String> bccs, String subject,
                String content, boolean isHtml, File... files) {
        return getDrive().send(mailAccount, tos, ccs, bccs, subject, content, isHtml, files);
    }

    /**
     * 使用配置文件中设置的账户发送HTML邮件，发送给单个或多个收件人<br>
     * 多个收件人可以使用逗号“,”分隔，也可以通过分号“;”分隔
     *
     * @param to       收件人
     * @param subject  标题
     * @param content  正文
     * @param imageMap 图片与占位符，占位符格式为cid:$IMAGE_PLACEHOLDER
     * @param files    附件列表
     * @return message-id
     */
    public static String sendHtml(String to, String subject, String content, Map<String, InputStream> imageMap, File... files) {
        return getDrive().sendHtml(to, subject, content, imageMap, files);
    }

    /**
     * 使用配置文件中设置的账户发送邮件，发送单个或多个收件人<br>
     * 多个收件人可以使用逗号“,”分隔，也可以通过分号“;”分隔
     *
     * @param to       收件人
     * @param subject  标题
     * @param content  正文
     * @param imageMap 图片与占位符，占位符格式为cid:$IMAGE_PLACEHOLDER
     * @param isHtml   是否为HTML
     * @param files    附件列表
     * @return message-id
     */
    public static String send(String to, String subject, String content, Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return getDrive().send(to, subject, content, imageMap, isHtml, files);
    }

    /**
     * 使用配置文件中设置的账户发送邮件，发送单个或多个收件人<br>
     * 多个收件人、抄送人、密送人可以使用逗号“,”分隔，也可以通过分号“;”分隔
     *
     * @param to       收件人，可以使用逗号“,”分隔，也可以通过分号“;”分隔
     * @param cc       抄送人，可以使用逗号“,”分隔，也可以通过分号“;”分隔
     * @param bcc      密送人，可以使用逗号“,”分隔，也可以通过分号“;”分隔
     * @param subject  标题
     * @param content  正文
     * @param imageMap 图片与占位符，占位符格式为cid:$IMAGE_PLACEHOLDER
     * @param isHtml   是否为HTML
     * @param files    附件列表
     * @return message-id
     */
    public static String send(String to, String cc, String bcc, String subject, String content, Map<String, InputStream> imageMap,
                              boolean isHtml, File... files) {
        return getDrive().send(to, cc, bcc, subject, content, imageMap, isHtml, files);
    }

    /**
     * 使用配置文件中设置的账户发送HTML邮件，发送给多人
     *
     * @param tos      收件人列表
     * @param subject  标题
     * @param content  正文
     * @param imageMap 图片与占位符，占位符格式为cid:$IMAGE_PLACEHOLDER
     * @param files    附件列表
     * @return message-id
     */
    public static String sendHtml(Collection<String> tos, String subject, String content, Map<String, InputStream> imageMap, File... files) {
        return getDrive().sendHtml(tos, subject, content, imageMap, files);
    }

    /**
     * 使用配置文件中设置的账户发送邮件，发送给多人
     *
     * @param tos      收件人列表
     * @param subject  标题
     * @param content  正文
     * @param imageMap 图片与占位符，占位符格式为cid:$IMAGE_PLACEHOLDER
     * @param isHtml   是否为HTML
     * @param files    附件列表
     * @return message-id
     */
    public static String send(Collection<String> tos, String subject, String content, Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return getDrive().send(tos, subject, content, imageMap, isHtml, files);
    }

    /**
     * 使用配置文件中设置的账户发送邮件，发送给多人
     *
     * @param tos      收件人列表
     * @param ccs      抄送人列表，可以为null或空
     * @param bccs     密送人列表，可以为null或空
     * @param subject  标题
     * @param content  正文
     * @param imageMap 图片与占位符，占位符格式为cid:$IMAGE_PLACEHOLDER
     * @param isHtml   是否为HTML
     * @param files    附件列表
     * @return message-id
     */
    public static String send(Collection<String> tos, Collection<String> ccs, Collection<String> bccs, String subject, String content,
                              Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return getDrive().send(tos, ccs, bccs, subject, content, imageMap, isHtml, files);
    }

    // ------------------------------------------------------------------------------------------------------------------------------- Custom MailAccount

    /**
     * 发送邮件给多人
     *
     * @param mailAccount 邮件认证对象
     * @param to          收件人，多个收件人逗号或者分号隔开
     * @param subject     标题
     * @param content     正文
     * @param imageMap    图片与占位符，占位符格式为cid:$IMAGE_PLACEHOLDER
     * @param isHtml      是否为HTML格式
     * @param files       附件列表
     * @return message-id
     */
    public static String send(MailAccount mailAccount, String to, String subject, String content, Map<String, InputStream> imageMap,
                boolean isHtml, File... files) {
        return getDrive().send(mailAccount, to, subject, content, imageMap, isHtml, files);
    }

    /**
     * 发送邮件给多人
     *
     * @param mailAccount 邮件帐户信息
     * @param tos         收件人列表
     * @param subject     标题
     * @param content     正文
     * @param imageMap    图片与占位符，占位符格式为cid:$IMAGE_PLACEHOLDER
     * @param isHtml      是否为HTML格式
     * @param files       附件列表
     * @return message-id
     */
    public static String send(MailAccount mailAccount, Collection<String> tos, String subject, String content,
                              Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return getDrive().send(mailAccount, tos, subject, content, imageMap, isHtml, files);
    }

    /**
     * 发送邮件给多人
     *
     * @param mailAccount 邮件帐户信息
     * @param tos         收件人列表
     * @param ccs         抄送人列表，可以为null或空
     * @param bccs        密送人列表，可以为null或空
     * @param subject     标题
     * @param content     正文
     * @param imageMap    图片与占位符，占位符格式为cid:$IMAGE_PLACEHOLDER
     * @param isHtml      是否为HTML格式
     * @param files       附件列表
     * @return message-id
     */
    public static String send(MailAccount mailAccount, Collection<String> tos, Collection<String> ccs, Collection<String> bccs,
                              String subject, String content, Map<String, InputStream> imageMap,
                              boolean isHtml, File... files) {
        return getDrive().send(mailAccount, tos, ccs, bccs, subject, content, imageMap, isHtml, files);
    }

}
