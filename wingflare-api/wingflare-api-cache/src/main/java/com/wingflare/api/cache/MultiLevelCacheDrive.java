package com.wingflare.api.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 多级缓存接口，支持管理多个级别的缓存
 */
public interface MultiLevelCacheDrive<K, V> extends CacheDrive<K, V> {
    /**
     * 添加一级缓存
     * @param cache 要添加的缓存实例
     */
    void addLevel(CacheDrive<K, V> cache);

    /**
     * 获取所有缓存级别
     * @return 缓存级别列表
     */
    List<CacheDrive<K, V>> getLevels();

    /**
     * 获取指定级别的缓存
     * @param index 级别索引（从0开始）
     * @return 对应级别的缓存实例
     */
    CacheDrive<K, V> getLevel(int index);

    /**
     * 从指定级别获取缓存值
     * @param key 缓存键
     * @param level 级别索引
     * @return 缓存值，如不存在或已过期则返回null
     */
    V getFromLevel(K key, int level);

    /**
     * 将缓存项同步到所有级别
     * @param key 缓存键
     * @param value 缓存值
     * @param ttl 生存时间（毫秒）
     */
    void syncToAllLevels(K key, V value, long ttl, TimeUnit unit);
}
