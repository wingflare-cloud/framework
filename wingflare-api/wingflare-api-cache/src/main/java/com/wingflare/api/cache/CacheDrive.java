package com.wingflare.api.cache;

import java.util.concurrent.TimeUnit;

/**
 * 缓存接口，定义缓存的基本操作
 * 支持TTL（生存时间）功能
 */
public interface CacheDrive <K, V> {

    /**
     * 获取缓存值，如果已过期则返回null
     * @param key 缓存键
     * @return 缓存值，如不存在或已过期则返回null
     */
    V get(K key);

    /**
     * 添加缓存项，使用默认TTL
     * @param key 缓存键
     * @param value 缓存值
     */
    void put(K key, V value);

    /**
     * 添加缓存项，指定TTL
     * @param key 缓存键
     * @param value 缓存值
     * @param ttl 生存时间（毫秒）
     */
    void put(K key, V value, long ttl, TimeUnit unit);

    /**
     * 移除缓存项
     * @param key 缓存键
     */
    void remove(K key);

    /**
     * 清空缓存
     */
    void clear();

    /**
     * 检查缓存项是否存在且未过期
     * @param key 缓存键
     * @return 如果存在且未过期则返回true，否则返回false
     */
    boolean containsKey(K key);

    /**
     * 获取缓存中有效（未过期）的项数量
     */
    int size();

}
