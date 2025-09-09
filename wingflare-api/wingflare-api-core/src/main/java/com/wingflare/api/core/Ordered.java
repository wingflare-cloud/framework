package com.wingflare.api.core;

/**
 * 排序接口
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
public interface Ordered {

    int MAX = Integer.MAX_VALUE;
    int MIN = Integer.MIN_VALUE;
    int DEFAULT_ORDER = 0;

    default public int getOrder() {
        return DEFAULT_ORDER;
    }

}
