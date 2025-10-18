package com.wingflare.lib.captcha;


import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;
import java.security.SecureRandom;

/**
 * 验证码抽象类
 */
public abstract class ImagesAbstractCaptcha implements CaptchaInterface {

    protected ImageCaptchaConfig config;
    protected static final SecureRandom random = new SecureRandom();
    protected static final String CHINESE = "一二三四五六七八九十百千万亿零元角分年月日时分秒天周月季年春夏秋冬东南西北中上下左右前后里外大小多少高矮长短宽窄厚薄黑白红黄蓝绿紫粉橙灰青碧银金铜铁木石水火土山川河海湖江田地林森草花树苗竹松柏杨柳榆槐桂梅兰菊荷桃李杏梨枣瓜果米面油盐酱醋茶肉鱼鸡鸭鹅牛羊马猪狗猫兔虎狼狮豹熊蛇龙凤龟鱼虾蟹贝虫鸟蝶蜂燕雁鹰鸽鹤蛙蝉蛛蚊蝇蚁蚕蛾蜻蜓蚯蚓蜈蚣蜗牛蚜蝗蜜蜂蝴蝶蝌蚪螃蟹蜈蚣蜘蛛蚯蚓蛤蟆蚯蚓蜈蚣蜗牛蚜蝗蜜蜂蝴蝶蝌蚪螃蟹蜈蚣蜘蛛蚯蚓蛤蟆鲤鱼鲫鱼鲈鱼鲑鱼鲸鱼鲨鱼鲍鱼乌龟章鱼墨鱼鲈鱼鲫鱼鲤鱼草鱼青鱼鲢鱼鳙鱼鳜鱼鲳鱼鲟鱼鳇鱼鳗鱼鳝鱼鳕鱼鲶鱼鳟鱼鲂鱼鲮鱼鳊鱼鲻鱼鲼鱼魟鱼鱿鱼鲈鱼鲫鱼鲤鱼草鱼青鱼鲢鱼鳙鱼鳜鱼鲳鱼鲟鱼鳇鱼鳗鱼鳝鱼鳕鱼鲶鱼鳟鱼鲂鱼鲮鱼鳊鱼鲻鱼鲼鱼魟鱼鱿鱼虾米龙虾小虾对虾龙虾毛虾大虾草虾河虾海虾蟹肉大闸蟹毛蟹海蟹河蟹贝类牡蛎蛤蜊扇贝鲍贝海参鲍鱼干贝瑶柱章鱼乌贼墨鱼鲍鱼海蜇海螺牡蛎蛤蜊扇贝鲍贝海参鲍鱼干贝瑶柱章鱼乌贼墨鱼鲍鱼海蜇海螺虫子蚜虫蝗虫蜜蜂蝴蝶蝌蚪螃蟹蜈蚣蜘蛛蚯蚓蛤蟆蚯蚓蜈蚣蜗牛蚜蝗蜜蜂蝴蝶蝌蚪螃蟹蜈蚣蜘蛛蚯蚓蛤蟆鸟类燕子麻雀喜鹊鹰隼鸽子鹌鹑鹧鸪鸬鹚鸵鸟企鹅鸳鸯鸿雁";

    // 常用颜色
    protected static final int[][] COLORS = {
            {0, 135, 255}, {51, 153, 51}, {255, 102, 102}, {255, 153, 0},
            {153, 102, 0}, {153, 102, 153}, {51, 153, 153}, {102, 102, 255},
            {0, 102, 204}, {204, 51, 51}, {0, 153, 204}, {0, 51, 102}
    };

    /**
     * 生成随机验证码字符
     */
    protected char[] generateChars() {
        int len = config.length();
        char[] result = new char[len];

        for (int i = 0; i < len; i++) {
            result[i] = generateChar();
        }

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
            case "CHINESE" -> CHINESE.charAt(random.nextInt(CHINESE.length()));
            default -> // DEFAULT: 字母数字混合，包括大小写
                    switch (random.nextInt(3)) {
                        case 0 -> (char) ('0' + random.nextInt(10));
                        case 1 -> (char) ('A' + random.nextInt(26));
                        default -> (char) ('a' + random.nextInt(26));
                    };
        };
    }

    /**
     * 获取随机颜色
     */
    protected Color getRandomColor() {
        int[] color = COLORS[random.nextInt(COLORS.length)];
        return new Color(color[0], color[1], color[2]);
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

    /**
     * 获取指定范围的随机整数
     */
    protected int randomInt(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }

    public void config(ImageCaptchaConfig config) {
        this.config = config;
    }

}
