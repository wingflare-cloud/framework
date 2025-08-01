package com.wingflare.lib.captcha.image;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * png格式验证码
 * Created by 王帆 on 2018-07-27 上午 10:08.
 */
public class ArithmeticCaptcha extends ArithmeticCaptchaAbstract {

    public ArithmeticCaptcha() {
    }

    public ArithmeticCaptcha(int width, int height) {
        this();
        setWidth(width);
        setHeight(height);
    }

    public ArithmeticCaptcha(int width, int height, int len) {
        this(width, height);
        setLen(len);
    }

    public ArithmeticCaptcha(int width, int height, int len, Font font) {
        this(width, height, len);
        setFont(font);
    }

    /**
     * 生成验证码
     *
     * @param out 输出流
     * @return 是否成功
     */
    @Override
    public void out(OutputStream out) throws IOException {
        checkAlpha();
        graphicsImage(getArithmeticString().toCharArray(), out);
    }

    @Override
    public String toBase64() throws IOException {
        return toBase64("data:image/png;base64,");
    }

    /**
     * 生成验证码图形
     *
     * @param strs 验证码
     * @param out  输出流
     * @return boolean
     */
    private void graphicsImage(char[] strs, OutputStream out) throws IOException {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) bi.getGraphics();
        // 填充背景
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        // 抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 画干扰圆
        drawOval(2, g2d);
        // 画字符串
        g2d.setFont(getFont());
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int fW = width / strs.length;  // 每一个字符所占的宽度
        int fSp = (fW - (int) fontMetrics.getStringBounds("8", g2d).getWidth()) / 2;  // 字符的左右边距
        for (int i = 0; i < strs.length; i++) {
            g2d.setColor(color());
            int fY = height - ((height - (int) fontMetrics.getStringBounds(String.valueOf(strs[i]), g2d).getHeight()) >> 1);  // 文字的纵坐标
            g2d.drawString(String.valueOf(strs[i]), i * fW + fSp + 3, fY - 3);
        }
        g2d.dispose();
        ImageIO.write(bi, "png", out);
        out.flush();
    }

}
