package com.wingflare.lib.standard.enums;


/**
 * 数据保密类型枚举值
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
public enum SecretType {
    /**
     * 调用时执行数据保密
     */
    CALL,
    /**
     * 返回时执行数据保密
     */
    RETURN,
    /**
     * 调用以及返回时都执行数据保密
     */
    ALL
    ;

}
