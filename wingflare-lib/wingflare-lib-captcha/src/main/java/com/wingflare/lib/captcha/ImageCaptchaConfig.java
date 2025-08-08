package com.wingflare.lib.captcha;

import java.awt.*;

/**
 * 图片验证码配置
 *
 * @param width
 * @param height
 * @param length
 * @param imageType
 * @param charType
 * @param font
 * @param fontSize
 * @param arithmetic
 * @param arithmeticDigit
 */
public record ImageCaptchaConfig(
        int width,
        int height,
        int length,
        String imageType,
        String charType,
        Font font,
        int fontSize,
        boolean arithmetic,
        int arithmeticDigit  // 运算位数
) {
    // 构建器模式实现
    public static class Builder {
        private int width = 130;
        private int height = 48;
        private int length = 4;
        private String imageType = "png";
        private String charType = "DEFAULT";
        private Font font = null;
        private int fontSize = 32;
        private boolean arithmetic = false;
        private int arithmeticDigit = 2;

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public Builder imageType(String imageType) {
            this.imageType = imageType;
            return this;
        }

        public Builder charType(String charType) {
            this.charType = charType;
            return this;
        }

        public Builder font(Font font) {
            this.font = font;
            return this;
        }

        public Builder fontSize(int fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        public Builder arithmetic(boolean arithmetic) {
            this.arithmetic = arithmetic;
            return this;
        }

        public Builder arithmeticDigit(int arithmeticDigit) {
            this.arithmeticDigit = arithmeticDigit;
            return this;
        }

        public ImageCaptchaConfig build() {
            return new ImageCaptchaConfig(
                    width, height, length, imageType,
                    charType, font, fontSize, arithmetic, arithmeticDigit
            );
        }
    }
}