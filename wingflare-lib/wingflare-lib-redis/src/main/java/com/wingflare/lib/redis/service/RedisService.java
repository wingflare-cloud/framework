package com.wingflare.lib.redis.service;


import com.wingflare.lib.core.Assert;
import com.wingflare.lib.standard.CacheService;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 * redis工具类
 *
 * @author naizui_ycx
 * @date {2021/12/16}
 * @description
 */
@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
public class RedisService implements CacheService {

    @Resource
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    @Override
    public <T> void setCacheObject(final String key, final T value)
    {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     */
    @Override
    public <T> void setCacheObject(final String key, final T value, final Long timeout, final TimeUnit timeUnit)
    {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    @Override
    public Boolean expire(final String key, final long timeout)
    {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return true=设置成功；false=设置失败
     */
    @Override
    public Boolean expire(final String key, final long timeout, final TimeUnit unit)
    {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获取有效时间
     *
     * @param key Redis键
     * @return 有效时间
     */
    @Override
    public long getExpire(final String key)
    {
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断 key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    @Override
    public boolean hasKey(String key)
    {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    @Override
    public <T> T getCacheObject(final String key)
    {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    @Override
    public <T> List<T> getCacheListObject(final Collection<String> keys)
    {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.multiGet(keys);
    }

    /**
     * 获取并删除缓存对象
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    @Override
    public <T> T getDeleteCacheObject(final String key)
    {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.getAndDelete(key);
    }

    /**
     * 原子自增
     * @param key 缓存key
     * @return Long 自增后的值
     * @author lixuan
     * @date 2022/3/16 0:16
     **/
    @Override
    public Long increment(String key){
        Assert.notNull(key,"RedisService.increment.key is null");
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 原子递减
     *
     * @param key 缓存key
     * @return {@link Long}
     * @author shaoyuyao
     * @date 2022/10/7 17:55
     */
    @Override
    public Long decrement(String key) {
        Assert.notNull(key,"RedisService.decrement.key is null");
        return redisTemplate.opsForValue().decrement(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    @Override
    public void deleteObject(final String key)
    {
        redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param keys 多个对象
     * @return
     */
    @Override
    public Long deleteObject(final Collection<String> keys)
    {
        return redisTemplate.delete(keys);
    }

    /**
     * 缓存List数据
     *
     * @param key 缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    @Override
    public <T> Long setCacheList(final String key, final List<T> dataList)
    {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    @Override
    public <T> List<T> getCacheList(final String key)
    {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 列表左出
     *
     * @param key 缓存key
     * @return Object
     */
    @Override
    public Object listIndex(String key, long index) {
        return this.redisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取元素在列表中的索引
     *
     * @param key 缓存key
     * @return
     */
    @Override
    public <T> Long listIndexOf(String key, T value) {
        return this.redisTemplate.opsForList().indexOf(key, value);
    }

    /**
     * 获取元素在列表中最后一个索引
     *
     * @param key
     * @param value
     * @param <T>
     *
     * @return
     */
    @Override
    public <T> Long listLastIndexOf(String key, T value) {
        return this.redisTemplate.opsForList().lastIndexOf(key, value);
    }

    /**
     * 列表左出
     *
     * @param key 缓存key
     * @return Object
     */
    @Override
    public Object listLeftPop(String key) {
        return this.redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 列表右出
     *
     * @param key 缓存key
     * @return Object
     */
    @Override
    public Object listRightPop(String key) {
        return this.redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 获取列表元素个数
     *
     * @param key
     * @return
     */
    @Override
    public Long listSize(String key) {
        return this.redisTemplate.opsForList().size(key);
    }

    /**
     * 左推
     *
     * @param key
     * @param value
     * @param <T>
     *
     * @return
     */
    @Override
    public <T> Long listLeftPush(String key, T value) {
        return this.redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 左推
     *
     * @param key
     * @param values
     * @param <T>
     * @return
     */
    @Override
    public <T> Long listLeftPushAll(String key, T... values) {
        return this.redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 左推
     *
     * @param key
     * @param values
     * @param <T>
     * @return
     */
    @Override
    public <T> Long listLeftPushAll(String key, Collection<T> values) {
        return this.redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 如果key存在则左推
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    @Override
    public <T> Long listLeftPushIfPresent(String key, T value) {
        return this.redisTemplate.opsForList().leftPushIfPresent(key, value);
    }

    /**
     * 右推
     *
     * @param key
     * @param value
     * @param <T>
     *
     * @return
     */
    @Override
    public <T> Long listRightPush(String key, T value) {
        return this.redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 右推
     *
     * @param key
     * @param values
     * @param <T>
     * @return
     */
    @Override
    public <T> Long listRightPushAll(String key, T... values) {
        return this.redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 右推
     *
     * @param key
     * @param values
     * @param <T>
     * @return
     */
    @Override
    public <T> Long listRightPushAll(String key, Collection<T> values) {
        return this.redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 如果key存在则右推
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    @Override
    public <T> Long listRightPushIfPresent(String key, T value) {
        return this.redisTemplate.opsForList().rightPushIfPresent(key, value);
    }

    /**
     * 缓存Set
     *
     * @param key 缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    @Override
    public <T> Long setCacheSet(final String key, final Set<T> dataSet)
    {
        return redisTemplate.boundSetOps(key).add(dataSet.toArray());
    }

    /**
     * 删除指定元素
     *
     * @param key     key
     * @param dataSet 元素列表
     * @return BoundSetOperations<String, T>
     */
    @Override
    public <T> Long removeItemSet(final String key, final Set<T> dataSet)
    {
        return redisTemplate.boundSetOps(key).remove(dataSet.toArray());
    }

    /**
     * 判断set列表是否存在某个元素
     *
     * @return true -> 存在; false -> 不存在
     */
    @Override
    public Boolean isMemberSet(final String key, final Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 判断成员是否在集合中
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Boolean isMember(final String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    @Override
    public <T> Set<T> getCacheSet(final String key)
    {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    @Override
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap)
    {
        redisTemplate.opsForHash().putAll(key, dataMap);
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    @Override
    public <T> Map<String, T> getCacheMap(final String key)
    {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @param value 值
     */
    @Override
    public <T> void setCacheMapValue(final String key, final String hKey, final T value)
    {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 删除redis指定map key
     *
     * @param key Redis键
     * @param hKey Hash键
     */
    @Override
    public <T> void delCacheMapValue(final String key, final String hKey)
    {
        redisTemplate.opsForHash().delete(key, hKey);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    @Override
    public <T> T getCacheMapValue(final String key, final String hKey)
    {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    @Override
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys)
    {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    @Override
    public Collection<String> keys(final String pattern)
    {
        return redisTemplate.keys(pattern);
    }

    /**
     * 值自增
     *
     * @return
     */
    @Override
    public Long atomic(String key, long num) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return operations.increment(key, num);
    }

}
