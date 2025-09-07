package com.wingflare.adapter.spring.redission.lock;


import com.wingflare.api.lock.RemoveLockDrive;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;


/**
 * 远程锁
 */
public class RemoteLock<K> implements RemoveLockDrive<K> {

    private final RedissonClient redissonClient;

    // 存储当前线程持有的锁信息，用于解锁操作
    private final ThreadLocal<RLock> currentLock = new ThreadLocal<>();

    // 缓存已经获取的读写锁对象
    private final ConcurrentMap<K, RReadWriteLock> lockCache = new ConcurrentHashMap<>();

    public RemoteLock(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 获取指定key对应的RReadWriteLock，优先从缓存获取
     */
    private RReadWriteLock getReadWriteLock(K key) {
        return lockCache.computeIfAbsent(key, k ->
                redissonClient.getReadWriteLock(buildLockKey(k)));
    }

    /**
     * 将key转换为字符串锁键
     */
    private String buildLockKey(K key) {
        return "lock:" + key.toString();
    }

    @Override
    public boolean tryReadLock(K key, Duration timeout) throws InterruptedException {
        return tryReadLock(key, timeout, Duration.ofMinutes(30)); // 默认30分钟过期
    }

    @Override
    public boolean tryLock(K key, Duration timeout) throws InterruptedException {
        return tryLock(key, timeout, Duration.ofMinutes(30)); // 默认30分钟过期
    }

    @Override
    public boolean tryReadLock(K key, Duration timeout, Duration expire) throws InterruptedException {
        RReadWriteLock rwLock = getReadWriteLock(key);
        RLock readLock = rwLock.readLock();

        boolean success = readLock.tryLock(timeout.toMillis(), expire.toMillis(), TimeUnit.MILLISECONDS);
        if (success) {
            currentLock.set(readLock);
        }
        return success;
    }

    @Override
    public boolean tryLock(K key, Duration timeout, Duration expire) throws InterruptedException {
        RReadWriteLock rwLock = getReadWriteLock(key);
        RLock writeLock = rwLock.writeLock();

        boolean success = writeLock.tryLock(timeout.toMillis(), expire.toMillis(), TimeUnit.MILLISECONDS);
        if (success) {
            currentLock.set(writeLock);
        }
        return success;
    }

    @Override
    public boolean tryReadLock(K key) throws InterruptedException {
        return tryReadLock(key, Duration.ofSeconds(0));
    }

    @Override
    public boolean tryLock(K key) throws InterruptedException {
        return tryLock(key, Duration.ofSeconds(0));
    }

    @Override
    public boolean tryReadLockNoTimeOut(K key, Duration expire) throws InterruptedException {
        return tryReadLock(key, Duration.ofMillis(Long.MAX_VALUE), expire);
    }

    @Override
    public boolean tryLockNoTimeOut(K key, Duration expire) throws InterruptedException {
        return tryLock(key, Duration.ofMillis(Long.MAX_VALUE), expire);
    }

    @Override
    public void unlock() {
        RLock lock = currentLock.get();

        if (lock == null) {
            throw new IllegalMonitorStateException("当前线程未持有任何锁");
        }

        try {
            lock.unlock();
        } finally {
            currentLock.remove();
        }
    }

}
