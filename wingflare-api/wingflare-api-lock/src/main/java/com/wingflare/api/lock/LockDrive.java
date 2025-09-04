package com.wingflare.api.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 基于key的读写锁抽象，支持超时机制
 * 可以针对不同的key分别加锁，支持读锁（共享）和写锁（排他）
 * @param <K>
 */
public interface LockDrive<K> {

    /**
     * 尝试获取指定key的读锁，并设置超时时间
     *
     * @param key 锁的标识key
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return 如果成功获取锁返回true，超时则返回false
     * @throws InterruptedException 如果线程在等待过程中被中断
     */
    boolean tryReadLock(K key, long timeout, TimeUnit unit) throws InterruptedException;

    /**
     * 尝试获取指定key的写锁，并设置超时时间
     *
     * @param key 锁的标识key
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return 如果成功获取锁返回true，超时则返回false
     * @throws InterruptedException 如果线程在等待过程中被中断
     */
    boolean tryLock(K key, long timeout, TimeUnit unit) throws InterruptedException;

    /**
     * 释放指定key锁
     *
     * @param key 锁的标识key
     * @throws IllegalMonitorStateException 如果当前线程未持有该锁
     */
    void unlock(K key);

    /**
     * 获取指定key的读锁对象
     *
     * @param key 锁的标识key
     * @return 读锁对象
     */
    Lock readLock(K key);

    /**
     * 获取指定key的写锁对象
     *
     * @param key 锁的标识key
     * @return 写锁对象
     */
    Lock lock(K key);

}
