package com.wingflare.api.http;


import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * HTTP Header接口
 */
public interface HttpHeader extends Iterable<HttpHeader.HeaderEntry> {
    /**
     * 添加头部值（不会覆盖现有值，而是追加）
     * @param name 头部名称
     * @param value 头部值
     */
    HttpHeader addHeader(String name, String value);

    /**
     * 设置头部值（会覆盖现有所有值）
     * @param name 头部名称
     * @param value 头部值
     */
    HttpHeader setHeader(String name, String value);

    /**
     * 设置头部的多个值（会覆盖现有所有值）
     * @param name 头部名称
     * @param values 头部值列表
     */
    HttpHeader setHeader(String name, List<String> values);

    /**
     * 获取头部的第一个值
     * @param name 头部名称
     * @return 头部的第一个值，如果不存在返回null
     */
    String getFirstHeader(String name);

    /**
     * 获取头部的所有值
     * @param name 头部名称
     * @return 头部值的列表，如果不存在返回空列表
     */
    List<String> getHeaders(String name);

    /**
     * 获取头部的所有值，用逗号拼接成一个字符串
     * @param name 头部名称
     * @return 拼接后的字符串，如果不存在返回null
     */
    String getHeader(String name);

    /**
     * 移除指定头部的所有值
     * @param name 头部名称
     */
    HttpHeader removeHeader(String name);

    /**
     * 检查是否包含指定头部
     * @param name 头部名称
     * @return 如果包含返回true，否则返回false
     */
    boolean containsHeader(String name);

    /**
     * 获取所有头部名称
     * @return 头部名称集合
     */
    Set<String> getHeaderNames();

    /**
     * 获取头部的数量
     * @return 头部数量
     */
    int size();

    /**
     * 清空所有头部
     */
    HttpHeader clear();

    /**
     * 头部键值对条目接口，用于迭代器返回
     * 包含规范化的头部名称和对应的所有值
     */
    interface HeaderEntry {
        /**
         * 获取规范化的头部名称
         * @return 头部名称（如 "Content-Type"）
         */
        String getName();

        /**
         * 获取头部的所有值
         * @return 不可修改的头部值列表（避免外部篡改）
         */
        List<String> getValues();
    }

    /**
     * 返回迭代器，用于遍历所有头部键值对
     * 迭代顺序与内部存储顺序一致（不保证有序，取决于实现）
     * @return 头部条目迭代器
     */
    Iterator<HeaderEntry> iterator();

}

