package com.wingflare.api.core.enums;


/**
 * 字符集枚举类
 */
public enum Charset {

    // 基础字符集
    US_ASCII("US-ASCII", "美国ASCII码，7位字符集"),
    ISO_8859_1("ISO-8859-1", "拉丁字母表No.1，包含西欧语言"),
    UTF_8("UTF-8", "8位Unicode转换格式，支持所有Unicode字符"),
    UTF_16BE("UTF-16BE", "16位Unicode转换格式，大端字节顺序"),
    UTF_16LE("UTF-16LE", "16位Unicode转换格式，小端字节顺序"),
    UTF_16("UTF-16", "16位Unicode转换格式，带强制字节顺序标记"),
    UTF_32BE("UTF-32BE", "32位Unicode转换格式，大端字节顺序"),
    UTF_32LE("UTF-32LE", "32位Unicode转换格式，小端字节顺序"),
    UTF_32("UTF-32", "32位Unicode转换格式，带强制字节顺序标记"),

    // 中文字符集
    GBK("GBK", "汉字内码扩展规范，支持简体和繁体中文"),
    GB2312("GB2312", "中国国家标准字符集，包含简体中文"),
    GB18030("GB18030", "中国国家标准，支持所有汉字，包括少数民族文字"),

    // 日文字符集
    SHIFT_JIS("Shift_JIS", "日本工业标准，用于日语，兼容ASCII"),
    EUC_JP("EUC-JP", "扩展UNIX编码，用于日语"),
    ISO_2022_JP("ISO-2022-JP", "ISO标准，用于日语，支持JIS X 0208字符集"),

    // 韩文字符集
    EUC_KR("EUC-KR", "扩展UNIX编码，用于韩语，韩国工业标准"),
    ISO_2022_KR("ISO-2022-KR", "ISO标准，用于韩语"),
    KS_C_5601("KS_C_5601", "韩国国家标准字符集，包含韩文字母和汉字"),

    // 阿拉伯语字符集
    ISO_8859_6("ISO-8859-6", "拉丁字母表No.6，用于阿拉伯语"),
    IBM0437("IBM0437", "IBM阿拉伯语编码，支持阿拉伯文字符");

    private final String charsetName;
    private final String description;

    /**
     * 构造函数
     * @param charsetName 字符集标准名称
     * @param description 字符集中文描述
     */
    Charset(String charsetName, String description) {
        this.charsetName = charsetName;
        this.description = description;
    }

    /**
     * 获取字符集名称
     * @return 字符集标准名称
     */
    public String getCharsetName() {
        return charsetName;
    }

    /**
     * 获取字符集描述
     * @return 字符集中文描述信息
     */
    public String getDescription() {
        return description;
    }

    /**
     * 获取对应的Charset对象
     * @return 字符集对象，如果不支持则返回null
     */
    public java.nio.charset.Charset getCharset() {
        try {
            return java.nio.charset.Charset.forName(charsetName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 检查当前JVM是否支持该字符集
     * @return 如果支持返回true，否则返回false
     */
    public boolean isSupported() {
        return java.nio.charset.Charset.isSupported(charsetName);
    }

    /**
     * 根据字符集名称获取枚举实例
     * @param charsetName 字符集名称
     * @return 对应的枚举实例，如果没有找到则返回null
     */
    public static Charset fromCharsetName(String charsetName) {
        if (charsetName == null) {
            return null;
        }

        for (Charset charset : values()) {
            if (charsetName.equalsIgnoreCase(charset.charsetName)) {
                return charset;
            }
        }

        return null;
    }

}
