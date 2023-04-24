package com.wingflare.lib.core;

import com.wingflare.lib.core.exceptions.ServerInternalException;

/**
 * 一些简单的基础正则
 *
 * @author naizui_ycx
 * @date Sat Mar 11 17:46:17 CST 2023
 */
public interface Regexp {

    /**
     * 限制最小
     */
    int LIMIT_MODE_MIN = 1;

    /**
     * 限制最大
     */
    int LIMIT_MODE_MAX = 2;

    /**
     * 限制范围
     */
    int LIMIT_MODE_RANGE = 3;

    /**
     * 限制数量
     */
    int LIMIT_MODE_NUM = 4;

    /**
     * 雪花id
     */
    String SNOWFLAKE_ID = "^\\d{0,32}$";

    /**
     * 半角字符
     */
    String HALF_WIDTH_CHA = "^[\\u0000-\\u00FF]*$";

    static String HALF_WIDTH_CHA(int min, int max, int limitMode) {
        return regexp("[\\u0000-\\u00FF]", min, max, limitMode);
    }

    static String HALF_WIDTH_CHA(int min, int max) {
        return HALF_WIDTH_CHA(min, max, LIMIT_MODE_RANGE);
    }

    static String HALF_WIDTH_CHA(int n) {
        return HALF_WIDTH_CHA(n, 0, LIMIT_MODE_NUM);
    }

    /**
     * 非半角字符
     */
    String NOT_HALF_WIDTH_CHA = "^[^\\u0000-\\u00FF]*$";

    static String NOT_HALF_WIDTH_CHA(int min, int max, int limitMode) {
        return regexp("[^\\u0000-\\u00FF]", min, max, limitMode);
    }

    static String NOT_HALF_WIDTH_CHA(int min, int max) {
        return NOT_HALF_WIDTH_CHA(min, max, LIMIT_MODE_RANGE);
    }

    static String NOT_HALF_WIDTH_CHA(int n) {
        return NOT_HALF_WIDTH_CHA(n, 0, LIMIT_MODE_NUM);
    }

    /**
     * 汉字
     */
    String CHS = "^[\\u4e00-\\u9fa5]*$";

    static String CHS(int min, int max, int limitMode) {
        return regexp("[\\u4e00-\\u9fa5]", min, max, limitMode);
    }

    static String CHS(int min, int max) {
        return CHS(min, max, LIMIT_MODE_RANGE);
    }

    static String CHS(int n) {
        return CHS(n, 0, LIMIT_MODE_NUM);
    }

    /**
     * 非汉字
     */
    String NOT_CHS = "^[^\\u4e00-\\u9fa5]*$";

    static String NOT_CHS(int min, int max, int limitMode) {
        return regexp("[^\\u4e00-\\u9fa5]", min, max, limitMode);
    }

    static String NOT_CHS(int min, int max) {
        return NOT_CHS(min, max, LIMIT_MODE_RANGE);
    }

    static String NOT_CHINESE_CHAR(int n) {
        return NOT_CHS(n, 0, LIMIT_MODE_NUM);
    }

    /**
     * 数值
     */
    String NUMERICAL = "^\\d*$";

    static String NUMERICAL(int min, int max, int limitMode) {
        return regexp("[\\d]", min, max, limitMode);
    }

    static String NUMERICAL(int min, int max) {
        return NUMERICAL(min, max, LIMIT_MODE_RANGE);
    }

    static String NUMERICAL(int n) {
        return NUMERICAL(n, 0, LIMIT_MODE_NUM);
    }

    /**
     * 非数值
     */
    String NOT_NUMERICAL = "^[^\\d]*$";

    static String NOT_NUMERICAL(int min, int max, int limitMode) {
        return regexp("[^\\d]", min, max, limitMode);
    }

    static String NOT_NUMERICAL(int min, int max) {
        return NOT_NUMERICAL(min, max, LIMIT_MODE_RANGE);
    }

    static String NOT_NUMERICAL(int n) {
        return NOT_NUMERICAL(n, 0, LIMIT_MODE_NUM);
    }

    /**
     * 字母
     */
    String ENGLISH_LETTER = "^[a-zA-Z]*$";

    static String ENGLISH_LETTER(int min, int max, int limitMode) {
        return regexp("[a-zA-Z]", min, max, limitMode);
    }

    static String ENGLISH_LETTER(int min, int max) {
        return ENGLISH_LETTER(min, max, LIMIT_MODE_RANGE);
    }

    static String ENGLISH_LETTER(int n) {
        return ENGLISH_LETTER(n, 0, LIMIT_MODE_NUM);
    }

    /**
     * 非字母
     */
    String NOT_ENGLISH_LETTER = "^[^a-zA-Z]*$";

    static String NOT_ENGLISH_LETTER(int min, int max, int limitMode) {
        return regexp("[^a-zA-Z]", min, max, limitMode);
    }

    static String NOT_ENGLISH_LETTER(int min, int max) {
        return NOT_ENGLISH_LETTER(min, max, LIMIT_MODE_RANGE);
    }

    
    
    static String NOT_ENGLISH_LETTER(int n) {
        return NOT_ENGLISH_LETTER(n, 0, LIMIT_MODE_NUM);
    }

    /**
     * 小写字母
     */
    String SMALL_ENGLISH_LETTER = "^[a-z]*$";

    static String SMALL_ENGLISH_LETTER(int min, int max, int limitMode) {
        return regexp("[a-z]", min, max, limitMode);
    }

    static String SMALL_ENGLISH_LETTER(int min, int max) {
        return SMALL_ENGLISH_LETTER(min, max, LIMIT_MODE_RANGE);
    }

    static String SMALL_ENGLISH_LETTER(int n) {
        return SMALL_ENGLISH_LETTER(n, 0, LIMIT_MODE_NUM);
    }

    /**
     * 大写字母
     */
    String CAPITAL_ENGLISH_LETTER = "^[A-Z]*$";

    static String CAPITAL_ENGLISH_LETTER(int min, int max, int limitMode) {
        return regexp("[A-Z]", min, max, limitMode);
    }

    static String CAPITAL_ENGLISH_LETTER(int min, int max) {
        return CAPITAL_ENGLISH_LETTER(min, max, LIMIT_MODE_RANGE);
    }

    static String CAPITAL_ENGLISH_LETTER(int n) {
        return CAPITAL_ENGLISH_LETTER(n, 0, LIMIT_MODE_NUM);
    }

    /**
     * 字母表，即A-Za-z0-9_
     */
    String ALPHABET = "^\\w*$";

    static String ALPHABET(int min, int max, int limitMode) {
        return regexp("[A-Za-z0-9_]", min, max, limitMode);
    }

    static String ALPHABET(int min, int max) {
        return ALPHABET(min, max, LIMIT_MODE_RANGE);
    }

    static String ALPHABET(int n) {
        return ALPHABET(n, 0, LIMIT_MODE_NUM);
    }

    /**
     * 字母加数字
     */
    String ALPHA_NUM = "^[A-Za-z0-9]*$";

    static String ALPHA_NUM(int min, int max, int limitMode) {
        return regexp("[A-Za-z0-9]", min, max, limitMode);
    }

    static String ALPHA_NUM(int min, int max) {
        return ALPHA_NUM(min, max, LIMIT_MODE_RANGE);
    }

    static String ALPHA_NUM(int n) {
        return ALPHA_NUM(n, 0, LIMIT_MODE_NUM);
    }

    /**
     * 字母、数字、_以及-
     */
    String ALPHA_DASH = "^[A-Za-z0-9_\\-]*$";

    static String ALPHA_DASH(int min, int max, int limitMode) {
        return regexp("[A-Za-z0-9_\\-]", min, max, limitMode);
    }

    static String ALPHA_DASH(int min, int max) {
        return ALPHA_NUM(min, max, LIMIT_MODE_RANGE);
    }

    static String ALPHA_DASH(int n) {
        return ALPHA_NUM(n, 0, LIMIT_MODE_NUM);
    }

    /**
     * 汉字、字母和数字
     */
    String CHS_ALPHA_NUM = "^[A-Za-z0-9\\u4e00-\\u9fa5]$";

    static String CHS_ALPHA_NUM(int min, int max, int limitMode) {
        return regexp("[A-Za-z0-9\\u4e00-\\u9fa5]", min, max, limitMode);
    }

    static String CHS_ALPHA_NUM(int min, int max) {
        return CHS_ALPHA_NUM(min, max, LIMIT_MODE_RANGE);
    }

    static String CHS_ALPHA_NUM(int n) {
        return CHS_ALPHA_NUM(n, 0, LIMIT_MODE_NUM);
    }

    /**
     * 汉字、字母、数字、_以及-
     */
    String CHS_ALPHA_NUM_DASH = "^[A-Za-z0-9\\u4e00-\\u9fa5_\\-]$";

    static String CHS_ALPHA_NUM_DASH(int min, int max, int limitMode) {
        return regexp("[A-Za-z0-9\\u4e00-\\u9fa5_\\-]", min, max, limitMode);
    }

    static String CHS_ALPHA_NUM_DASH(int min, int max) {
        return CHS_ALPHA_NUM_DASH(min, max, LIMIT_MODE_RANGE);
    }

    static String CHS_ALPHA_NUM_DASH(int n) {
        return CHS_ALPHA_NUM_DASH(n, 0, LIMIT_MODE_NUM);
    }

    /**
     * 邮箱号
     */
    String EMAIL = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    /**
     * 手机号
     */
    String PHONE = "^1(3|4|5|6|7|8|9)\\d{9}$";

    /**
     * 固定电话号
     */
    String TEL_NUM = "^(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{8}$";

    /**
     * 域名
     */
    String DOMAIN = "^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$";

    /**
     * ipv4
     */
    String IPV4 = "^(?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)$";


    static String regexp(String regexp, int n1, int n2, int limitMode) {
        switch (limitMode) {
            case LIMIT_MODE_MIN :
                return String.format("^%s{%s,}$", regexp, n1);
            case LIMIT_MODE_MAX:
                return String.format("^%s{,%s}$", regexp, n1);
            case LIMIT_MODE_RANGE:
                return String.format("^%s{%s,%s}$", regexp, n1, n2);
            case LIMIT_MODE_NUM:
                return String.format("^%s{%s}$", regexp, n1);
            default:
                throw new ServerInternalException("sys.regexp.formatError");
        }
    }

}
