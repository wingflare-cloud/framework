package com.wingflare.lib.captcha;


import com.wingflare.facade.lib.captcha.CaptchaStoreInterface;
import com.wingflare.lib.container.Container;
import com.wingflare.lib.core.utils.StringUtil;

import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;


public final class GifCaptcha extends ImagesAbstractCaptcha {

    public GifCaptcha(ImageCaptchaConfig config) {
        config(config);
    }

    @Override
    public Object out(String captchaId) {
        char[] chars = generateChars();
        Color[] fontColors = new Color[chars.length];

        CaptchaStoreInterface store = Container.get(CaptchaStoreInterface.class);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        store.save(captchaId, new String(chars));

        for (int i = 0; i < chars.length; i++) {
            fontColors[i] = getRandomColor();
        }

        // 生成贝塞尔曲线参数
        int[][] besselParams = generateBesselParameters();

        // 生成GIF
        GifEncoder gifEncoder = new GifEncoder();
        gifEncoder.setQuality(180);
        gifEncoder.setDelay(100);
        gifEncoder.setRepeat(0);
        gifEncoder.start(os);

        // 生成每一帧
        for (int i = 0; i < chars.length; i++) {
            BufferedImage frame = createFrame(fontColors, chars, i, besselParams);
            gifEncoder.addFrame(frame);
            frame.flush();
        }

        gifEncoder.finish();

        return os;
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
     * 生成贝塞尔曲线参数
     */
    private int[][] generateBesselParameters() {
        int x1 = 5;
        int y1 = 5 + random.nextInt(config.height() / 2);
        int x2 = config.width() - 5;
        int y2 = config.height() / 2 + random.nextInt(config.height() / 2 - 5);

        int ctrlx = config.width() / 4 + random.nextInt(config.width() / 2);
        int ctrly = 5 + random.nextInt(config.height() - 10);

        // 随机交换y1和y2
        if (random.nextBoolean()) {
            int temp = y1;
            y1 = y2;
            y2 = temp;
        }

        int ctrlx1 = config.width() / 4 + random.nextInt(config.width() / 2);
        int ctrly1 = 5 + random.nextInt(config.height() - 10);

        return new int[][]{{x1, y1}, {ctrlx, ctrly}, {ctrlx1, ctrly1}, {x2, y2}};
    }

    /**
     * 创建GIF帧
     */
    private BufferedImage createFrame(Color[] fontColors, char[] chars, int frameIndex, int[][] besselParams) {
        BufferedImage image = new BufferedImage(
                config.width(), config.height(), BufferedImage.TYPE_INT_RGB
        );
        Graphics2D g2d = image.createGraphics();
        // 配置绘图设置
        configureGraphics(g2d);
        // 绘制背景
        drawBackground(g2d);
        // 绘制干扰元素
        drawInterference(g2d, fontColors[0], besselParams);
        // 绘制验证码字符
        drawCharacters(g2d, fontColors, chars, frameIndex);
        g2d.dispose();
        return image;
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
    private void drawInterference(Graphics2D g2d, Color color, int[][] besselParams) {
        // 绘制干扰圆
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f * randomInt(3, 10)));
        drawOvals(2, g2d);

        // 绘制贝塞尔曲线
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        g2d.setStroke(new BasicStroke(1.2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        g2d.setColor(color);

        CubicCurve2D shape = new CubicCurve2D.Double(
                besselParams[0][0], besselParams[0][1],
                besselParams[1][0], besselParams[1][1],
                besselParams[2][0], besselParams[2][1],
                besselParams[3][0], besselParams[3][1]
        );
        g2d.draw(shape);
    }

    /**
     * 绘制验证码字符
     */
    private void drawCharacters(Graphics2D g2d, Color[] fontColors, char[] chars, int frameIndex) {
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int fW = config.width() / chars.length;  // 每个字符的宽度
        int fSp = (fW - (int) fontMetrics.getStringBounds(String.valueOf(chars[0]), g2d).getWidth()) / 2;  // 字符间距

        for (int i = 0; i < chars.length; i++) {
            // 设置透明度
            float alpha = getAlpha(frameIndex, i, chars.length);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.setColor(fontColors[i]);

            // 绘制字符
            int fY = config.height() - ((config.height() -
                    (int) fontMetrics.getStringBounds(String.valueOf(chars[i]), g2d).getHeight()) >> 1);
            g2d.drawString(String.valueOf(chars[i]), i * fW + fSp - 3, fY - 3);
        }
    }

    /**
     * 获取透明度
     */
    private float getAlpha(int frameIndex, int charIndex, int totalChars) {
        int num = frameIndex + charIndex;
        float ratio = 1.0f / (totalChars - 1);
        float threshold = totalChars * ratio;
        return num >= totalChars ? (num * ratio - threshold) : num * ratio;
    }

}