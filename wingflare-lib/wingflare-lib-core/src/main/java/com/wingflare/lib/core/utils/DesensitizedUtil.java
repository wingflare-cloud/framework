package com.wingflare.lib.core.utils;

/**
 * 数据脱敏工具
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
public class DesensitizedUtil {

    /**
     * 【银行卡号】数据脱敏
     * 只留前四位和后四位
     * 6227 0383 3938 3938 393 脱敏结果: 6227 **** **** ***8 393
     *
     * @param value
     * @return
     */
    public static String bankCard(String value) {
        if (StringUtil.isEmpty(value)) {
            return null;
        }

        return StringUtil.left(value, 4)
                .concat(StringUtil
                        .removeStart(StringUtil.leftPad(StringUtil.right(value, 4),
                                StringUtil.length(value), "*"), "***"));
    }

    /**
     * 【邮箱】数据脱敏
     * 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示
     * 例子:g**@163.com
     *
     * @param value
     * @return
     */
    public static String email(String value) {
        if (StringUtil.isEmpty(value)) {
            return null;
        }

        int index = StringUtil.indexOf(value, "@");

        if (index <= 1) {
            return value;
        } else {
            return StringUtil.rightPad(StringUtil.left(value, 1), index, "*").concat(
                    StringUtil.mid(value, index, StringUtil.length(value)));
        }
    }

    /**
     * 【身份证号】脱敏类型
     * 前3位，后4位
     * 130722199102323232 脱敏后: 130*************3232
     *
     * @param value
     * @return
     */
    public static String idCardNum(String value) {

        if (StringUtil.isEmpty(value)) {
            return null;
        }

        return StringUtil.left(value, 3)
                .concat(StringUtil.removeStart(
                        StringUtil.leftPad(StringUtil.right(value, 4),
                                StringUtil.length(value), "*"), "***"));
    }

    /**
     * 【手机号码】数据脱敏
     * 18233583070 脱敏后: 182****3030
     *
     * @param value
     * @return
     */
    public static String mobilePhone(String value) {
        if (StringUtil.isEmpty(value)) {
            return null;
        }
        return StringUtil.left(value, 3)
                .concat(StringUtil.removeStart(
                        StringUtil.leftPad(StringUtil.right(value, 4),
                                StringUtil.length(value), "*"), "***"));
    }

    /**
     * 【姓名】真实姓名脱敏
     * 中文姓名只显示最后一个汉字，其他隐藏为星号
     * 张三丰 ：**丰
     *
     * @param value
     * @return
     */
    public static String chineseName(String value) {
        if (StringUtil.isEmpty(value)) {
            return null;
        }

        return StringUtil.leftPad(StringUtil.right(value, 1), StringUtil.length(value), "*");
    }

    /**
     * 【地址】只显示到地区，不显示详细地址，比如：北京市海淀区****
     *
     * @param address
     * @return
     */
    public static String address(String address) {
        if (StringUtil.isBlank(address)) {
            return address;
        }

        String[] keys = new String[]{"区", "县", "镇", "村", "市", "省"};
        int index = 0;

        for (String key : keys) {
            index = address.indexOf(key, 1);
            if (index > 0) {
                break;
            }
        }

        if (index <= 0) {
            return address;
        }

        return StringUtil.rightPad(
                StringUtil.left(address, index + 1), StringUtil.length(address), "*");
    }

    /**
     * 密码脱敏
     *
     * @param passwd
     * @return
     */
    public static String passwd(String passwd) {
        return "******";
    }

    /**
     * 置空
     *
     * @param none
     * @return
     */
    public static String none(String none) {
        return null;
    }

}