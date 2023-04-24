package com.wingflare.lib.captcha.service;


import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.wingflare.abstraction.lib.captcha.CaptchaGenerateServer;
import com.wingflare.abstraction.lib.captcha.dto.CaptchaDto;
import com.wingflare.lib.core.exceptions.ServerInternalException;
import com.wingflare.lib.core.utils.StringUtil;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 普通验证码
 *
 * @author naizui_ycx
 * @className KaptchaServer
 * @email chenxi2511@qqq.com
 * @date 2023/04/20
 */
public class KaptchaGenerateServer implements CaptchaGenerateServer<String> {

    private final Producer producer;

    private String type;

    public final static String TYPE_KEY = "type";

    /**
     * 字符串类型验证码
     */
    public final static String TYPE_STRING = "string";

    /**
     * 数学计算类型
     */
    public final static String TYPE_MATH = "math";


    public KaptchaGenerateServer(Properties properties) {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        if (StringUtil.isNotEmpty(properties.getProperty(TYPE_KEY))) {
            type = properties.getProperty(TYPE_KEY);
        } else {
            type = TYPE_STRING;
        }

        producer = defaultKaptcha;
    }

    @Override
    public CaptchaDto<String> generate() {
        CaptchaDto<String> captchaDto = new CaptchaDto<>();
        BufferedImage image = null;

        if (type.equals(TYPE_MATH)) {
            String capText = producer.createText();
            captchaDto.setValue(capText.substring(capText.lastIndexOf("@") + 1));
            String capStr = capText.substring(0, capText.lastIndexOf("@"));
            image = producer.createImage(capStr);
        } else {
            captchaDto.setValue(producer.createText());
            image = producer.createImage(captchaDto.getValue());
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "jpg", os);
            captchaDto.setBody(Base64.encodeBase64String(os.toByteArray()));
        } catch (IOException e) {
            throw new ServerInternalException(e.getMessage(), e);
        }

        return captchaDto;
    }

    @Override
    public boolean compare(String v1, String v2) {
        return v1.equals(v2);
    }

}
