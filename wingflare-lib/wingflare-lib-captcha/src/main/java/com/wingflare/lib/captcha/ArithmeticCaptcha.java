package com.wingflare.lib.captcha;

import com.wingflare.facade.lib.captcha.CaptchaStoreInterface;
import com.wingflare.lib.core.utils.StringUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import java.util.Random;


public final class ArithmeticCaptcha extends ImagesAbstractCaptcha {

    public ArithmeticCaptcha(ImageCaptchaConfig config) {
        config(config);
    }

    /**
     * 生成算术表达式
     */
    private String generateArithmetic(String captchaId) {
        Random random = new Random();
        int digit = config.arithmeticDigit();
        int maxNum = (int) Math.pow(10, digit) - 1;
        CaptchaStoreInterface store = CaptchaStoreUtil.getStore();

        int a = random.nextInt(maxNum) + 1;
        int b = random.nextInt(maxNum) + 1;

        // 随机选择运算符
        int operator = random.nextInt(3);

        switch (operator) {
            case 0: // 加法
                store.save(captchaId, String.valueOf(a + b));
                return String.format("%d + %d = ?", a, b);
            case 1: // 减法，确保结果为正数
                if (a < b) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                store.save(captchaId, String.valueOf(a - b));
                return String.format("%d - %d = ?", a, b);
            case 2: // 乘法
            default:
                // 限制乘法结果大小
                if (digit > 1) {
                    a = random.nextInt((int) Math.sqrt(maxNum)) + 1;
                    b = random.nextInt((int) Math.sqrt(maxNum)) + 1;
                }
                store.save(captchaId, String.valueOf(a * b));
                return String.format("%d × %d = ?", a, b);
        }
    }

    @Override
    public void out(String captchaId, OutputStream os) throws IOException {
        BufferedImage image = new BufferedImage(
                config.width(), config.height(), BufferedImage.TYPE_INT_RGB
        );
        Graphics2D g2d = image.createGraphics();
        // 配置绘图设置
        configureGraphics(g2d);
        // 绘制背景
        drawBackground(g2d);
        // 绘制干扰元素
        drawInterference(g2d);
        // 绘制算术表达式
        drawArithmeticExpression(captchaId, g2d);
        // 完成绘制
        g2d.dispose();
        ImageIO.write(image, "png", os);
        os.flush();
    }

    @Override
    public boolean verify(String captchaId, String value) {
        CaptchaStoreInterface store = CaptchaStoreUtil.getStore();
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
    }

    /**
     * 绘制算术表达式
     */
    private void drawArithmeticExpression(String captchaId, Graphics2D g2d) {
        g2d.setColor(getRandomColor());
        FontMetrics fontMetrics = g2d.getFontMetrics();

        String arithmeticString = generateArithmetic(captchaId);

        // 计算文本位置，使其居中
        int textWidth = fontMetrics.stringWidth(arithmeticString);
        int textHeight = fontMetrics.getAscent() - fontMetrics.getDescent();

        int x = (config.width() - textWidth) / 2;
        int y = (config.height() + textHeight) / 2;

        g2d.drawString(arithmeticString, x, y);
    }

}