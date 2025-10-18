package com.wingflare.lib.captcha;


import com.wingflare.api.captcha.CaptchaStoreInterface;
import com.wingflare.lib.container.Container;
import com.wingflare.lib.core.utils.StringUtil;

import java.awt.*;
import java.awt.image.BufferedImage;


public final class PngCaptcha extends ImagesAbstractCaptcha {

    public PngCaptcha(ImageCaptchaConfig config) {
        config(config);
    }

    @Override
    public Object generate(String captchaId) {
        char[] chars = generateChars();
        BufferedImage image = new BufferedImage(
                config.width(), config.height(), BufferedImage.TYPE_INT_RGB
        );

        CaptchaStoreInterface store = Container.get(CaptchaStoreInterface.class);
        store.save(captchaId, new String(chars));

        Graphics2D g2d = image.createGraphics();
        // 配置绘图设置
        configureGraphics(g2d);
        // 绘制背景
        drawBackground(g2d);
        // 绘制干扰元素
        drawInterference(g2d);
        // 绘制验证码
        drawCharacters(g2d, chars);
        // 完成绘制
        g2d.dispose();
        return image;
    }

    @Override
    public boolean verify(String captchaId, String value) {
        CaptchaStoreInterface store = Container.get(CaptchaStoreInterface.class);
        String captchaCode = store.get(captchaId);

        if (StringUtil.equals(captchaCode, value)) {
            store.delete(captchaId);
            return true;
        }

        return false;
    }

    /**
     * 配置绘图设置
     */
    private void configureGraphics(Graphics2D g2d) {
        // 抗锯齿
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        // 设置字体
        if (config.font() != null) {
            g2d.setFont(config.font());
        } else {
            // 使用默认字体
            g2d.setFont(new Font("Arial", Font.BOLD, config.fontSize()));
        }
    }

    /**
     * 绘制背景
     */
    private void drawBackground(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, config.width(), config.height());
    }

    /**
     * 绘制干扰元素
     */
    private void drawInterference(Graphics2D g2d) {
        // 绘制干扰圆
        drawOvals(2, g2d);
        // 绘制贝塞尔曲线
        g2d.setStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        drawBesselLines(1, g2d);
    }

    /**
     * 绘制验证码字符
     */
    private void drawCharacters(Graphics2D g2d, char[] chars) {
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int fW = config.width() / chars.length;  // 每个字符的宽度
        int fSp = (fW - (int) fontMetrics.getStringBounds(String.valueOf(chars[0]), g2d).getWidth()) / 2;  // 字符间距

        for (int i = 0; i < chars.length; i++) {
            g2d.setColor(getRandomColor());
            // 随机旋转角度
            int degree = randomInt(-30, 30);
            g2d.rotate(Math.toRadians(degree), i * fW + fSp + 15, config.height() / 2);
            // 绘制字符
            int fY = config.height() - ((config.height() -
                    (int) fontMetrics.getStringBounds(String.valueOf(chars[i]), g2d).getHeight()) >> 1);
            g2d.drawString(String.valueOf(chars[i]), i * fW + fSp + 3, fY - 3);
            // 恢复旋转
            g2d.rotate(-Math.toRadians(degree), i * fW + fSp + 15, config.height() / 2);
        }
    }

}