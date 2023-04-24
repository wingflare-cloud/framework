package com.wingflare.lib.standard;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author naizui_ycx
 * @date {2021/12/16}
 * @description 缓存工具标准接口
 */
public interface CacheService {

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    <T> void setCacheObject(final String key, final T value);

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     */
    <T> void setCacheObject(final String key, final T value, final Long timeout, final TimeUnit timeUnit);

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    Boolean expire(final String key, final long timeout);

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return true=设置成功；false=设置失败
     */
    Boolean expire(final String key, final long timeout, final TimeUnit unit);
    /**
     * 获取有效时间
     *
     * @param key Redis键
     * @return 有效时间
     */
    long getExpire(final String key);

    /**
     * 判断 key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    boolean hasKey(String key);

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    <T> T getCacheObject(final String key);

    /**
     * 获取并删除缓存对象
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    <T> T getDeleteCacheObject(final String key);

    /**
     * 原子自增
     * @param key 缓存key
     * @return Long 自增后的值
     * @author lixuan
     * @date 2022/3/16 0:16
     **/
    Long increment(String key);

    /**
     * 原子递减
     *
     * @param key 缓存key
     * @return {@link Long}
     * @author shaoyuyao
     * @date 2022/10/7 17:55
     */
    Long decrement(String key);

    /**
     * 删除单个对象
     *
     * @param key
     * @return
     */
    void deleteObject(final String key);

    /**
     * 删除集合对象
     *
     * @param keys 多个对象
     * @return
     */
    Long deleteObject(final Collection<String> keys);

    /**
     * 缓存List数据
     *
     * @param key 缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    <T> Long setCacheList(final String key, final List<T> dataList);

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    <T> List<T> getCacheList(final String key);

    /**
     * 列表获取指定索引元素
     *
     * @param key
     * @param index
     * @param <T>
     * @return
     */
    <T> T listIndex(String key, long index);

    /**
     * 获取元素在列表中的索引
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    <T> Long listIndexOf(String key, T value);

    /**
     * 获取元素在列表中最后一个索引
     *
     * @param key
     * @param value
     * @param <T>
     *
     * @return
     */
    <T> Long listLastIndexOf(String key, T value);

    /**
     * 列表右出
     *
     * @param key 缓存key
     * @return
     */
    <T> T listRightPop(String key) ;

    /**
     * 列表左出
     *
     * @param key
     * @param <T>
     * @return
     */
    <T> T listLeftPop(String key);

    /**
     * 获取列表元素个数
     *
     * @param key
     * @return
     */
    Long listSize(String key);

    /**
     * 左推
     *
     * @param key
     * @param value
     * @param <T>
     *
     * @return
     */
    <T> Long listLeftPush(String key, T value);

    /**
     * 左推
     *
     * @param key
     * @param values
     * @param <T>
     * @return
     */
    <T> Long listLeftPushAll(String key, T... values);

    /**
     * 左推
     *
     * @param key
     * @param values
     * @param <T>
     * @return
     */
    <T> Long listLeftPushAll(String key, Collection<T> values);

    /**
     * 如果key存在则左推
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    <T> Long listLeftPushIfPresent(String key, T value);

    /**
     * 右推
     *
     * @param key
     * @param value
     * @param <T>
     *
     * @return
     */
    <T> Long listRightPush(String key, T value);

    /**
     * 右推
     *
     * @param key
     * @param values
     * @param <T>
     * @return
     */
    <T> Long listRightPushAll(String key, T... values);

    /**
     * 右推
     *
     * @param key
     * @param values
     * @param <T>
     * @return
     */
    <T> Long listRightPushAll(String key, Collection<T> values);

    /**
     * 如果key存在则右推
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    <T> Long listRightPushIfPresent(String key, T value);

    /**
     * 缓存Set
     *
     * @param key 缓存键值
     * @param dataSet 缓存的数据
     * @return
     */
    <T> Long setCacheSet(final String key, final Set<T> dataSet);

    /**
     * 删除指定元素
     *
     * @param key     key
     * @param dataSet 元素列表
     * @param <T>
     * @return
     */
    <T> Long removeItemSet(final String key, final Set<T> dataSet);

    /**
     * 判断set列表是否存在某个元素
     *
     * @param key
     * @param value
     * @return true -> 存在; false -> 不存在
     */
    Boolean isMemberSet(final String key, final Object value);

    /**
     * 判断成员是否在集合中
     *
     * @param key
     * @param value
     *
     * @return
     */
    Boolean isMember(final String key, String value);

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    <T> Set<T> getCacheSet(final String key);

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    <T> void setCacheMap(final String key, final Map<String, T> dataMap);

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    <T> Map<String, T> getCacheMap(final String key);

    /**
     * 往Hash中存入数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @param value 值
     */
    <T> void setCacheMapValue(final String key, final String hKey, final T value);

    /**
     * 删除redis指定map key
     *
     * @param key Redis键
     * @param hKey Hash键
     */
    <T> void delCacheMapValue(final String key, final String hKey);

    /**
     * 获取Hash中的数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    <T> T getCacheMapValue(final String key, final String hKey);

    /**
     * 获取多个Hash中的数据
     *
     * @param key Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys);

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    Collection<String> keys(final String pattern);

    /**
     * 值自增
     *
     * @param key
     * @param num
     * @return
     */
    Long atomic(String key, long num);

}
