package com.wingflare.lib.captcha;

import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;
import java.security.SecureRandom;

/**
 * 验证码抽象类
 */
public abstract non-sealed class ImagesAbstractCaptcha implements CaptchaInterface {

    protected ImageCaptchaConfig config;
    protected static final SecureRandom random = new SecureRandom();
    protected String chars;  // 当前验证码

    // 常用颜色
    protected static final int[][] COLORS = {
            {0, 135, 255}, {51, 153, 51}, {255, 102, 102}, {255, 153, 0},
            {153, 102, 0}, {153, 102, 153}, {51, 153, 153}, {102, 102, 255},
            {0, 102, 204}, {204, 51, 51}, {0, 153, 204}, {0, 51, 102}
    };


    /**
     * 初始化字体
     */
    private void initFont() {
        if (config == null || config.font() == null) {
            // 随机选择一个内置字体
            int fontIndex = random.nextInt(10);
            try {
                Font font = FontCache.getBuiltInFont(fontIndex, Font.BOLD, config.fontSize());
                // 通过新的配置创建一个新的对象（因为record是不可变的）
                // 这里使用构建器模式重新构建配置
            } catch (Exception e) {
                // 异常处理
            }
        }
    }

    /**
     * 生成随机验证码字符
     */
    protected char[] generateChars() {
        if (chars != null) {
            return chars.toCharArray();
        }

        int len = config.length();
        char[] result = new char[len];

        for (int i = 0; i < len; i++) {
            result[i] = generateChar();
        }

        chars = new String(result);
        return result;
    }

    /**
     * 生成单个随机字符
     */
    protected char generateChar() {
        return switch (config.charType()) {
            case "ONLY_NUMBER" -> (char) ('0' + random.nextInt(10));
            case "ONLY_CHAR", "ONLY_UPPER" -> (char) ('A' + random.nextInt(26));
            case "ONLY_LOWER" -> (char) ('a' + random.nextInt(26));
            case "NUM_AND_UPPER" -> random.nextBoolean()
                    ? (char) ('0' + random.nextInt(10))
                    : (char) ('A' + random.nextInt(26));
            case "CHINESE" -> generateChineseChar();
            default -> // DEFAULT: 字母数字混合，包括大小写
                    switch (random.nextInt(3)) {
                        case 0 -> (char) ('0' + random.nextInt(10));
                        case 1 -> (char) ('A' + random.nextInt(26));
                        default -> (char) ('a' + random.nextInt(26));
                    };
        };
    }

    /**
     * 生成中文字符
     */
    protected char generateChineseChar() {
        // 常用汉字范围：0x4e00-0x9fa5
        return (char) (0x4e00 + random.nextInt(0x9fa5 - 0x4e00 + 1));
    }

    /**
     * 获取随机颜色
     */
    protected Color getRandomColor() {
        int[] color = COLORS[random.nextInt(COLORS.length)];
        return new Color(color[0], color[1], color[2]);
    }

    /**
     * 获取指定范围的随机颜色
     */
    protected Color getRandomColor(int fc, int bc) {
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;

        int r = fc + random.nextInt(bc - fc + 1);
        int g = fc + random.nextInt(bc - fc + 1);
        int b = fc + random.nextInt(bc - fc + 1);

        return new Color(r, g, b);
    }

    /**
     * 画干扰线
     */
    protected void drawLines(int num, Graphics2D g) {
        for (int i = 0; i < num; i++) {
            g.setColor(getRandomColor());
            int x1 = random.nextInt(config.width() - 10) - 5;
            int y1 = random.nextInt(config.height() - 10) + 5;
            int x2 = random.nextInt(config.width() - 10) + 10;
            int y2 = random.nextInt(config.height() - 10) + 2;
            g.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 画干扰圆
     */
    protected void drawOvals(int num, Graphics2D g) {
        for (int i = 0; i < num; i++) {
            g.setColor(getRandomColor());
            int w = 5 + random.nextInt(10);
            int x = random.nextInt(config.width() - 25);
            int y = random.nextInt(config.height() - 15);
            g.drawOval(x, y, w, w);
        }
    }

    /**
     * 画贝塞尔曲线
     */
    protected void drawBesselLines(int num, Graphics2D g) {
        for (int i = 0; i < num; i++) {
            g.setColor(getRandomColor());
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

            if (random.nextBoolean()) {
                // 二阶贝塞尔曲线
                QuadCurve2D shape = new QuadCurve2D.Double();
                shape.setCurve(x1, y1, ctrlx, ctrly, x2, y2);
                g.draw(shape);
            } else {
                // 三阶贝塞尔曲线
                int ctrlx1 = config.width() / 4 + random.nextInt(config.width() / 2);
                int ctrly1 = 5 + random.nextInt(config.height() - 10);
                CubicCurve2D shape = new CubicCurve2D.Double(
                        x1, y1, ctrlx, ctrly, ctrlx1, ctrly1, x2, y2
                );
                g.draw(shape);
            }
        }
    }

    @Override
    public String text() {
        if (chars == null) {
            generateChars();
        }
        return chars;
    }

    /**
     * 获取指定范围的随机整数
     */
    protected int randomInt(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }

    @Override
    public void config(CaptchaConfigInterface config) {
    }

    public void config(ImageCaptchaConfig config) {
        this.config = config;
    }

}
