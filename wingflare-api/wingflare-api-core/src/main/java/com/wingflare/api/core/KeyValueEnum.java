package com.wingflare.api.core;


/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description 通用键值对枚举类
 */
public interface KeyValueEnum<T> extends ValueEnum<T> {

    String getKey();

}
