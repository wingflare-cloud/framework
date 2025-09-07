package com.wingflare.api.lock;


import java.time.Duration;

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
     * @return 如果成功获取锁返回true，超时则返回false
     * @throws InterruptedException 如果线程在等待过程中被中断
     */
    boolean tryReadLock(K key, Duration timeout) throws InterruptedException;

    /**
     * 尝试获取指定key的写锁，并设置超时时间
     *
     * @param key 锁的标识key
     * @param timeout 超时时间
     * @return 如果成功获取锁返回true，超时则返回false
     * @throws InterruptedException 如果线程在等待过程中被中断
     */
    boolean tryLock(K key, Duration timeout) throws InterruptedException;


    /**
     * 尝试获取指定key的读锁，并设置超市和过期时间
     *
     * @param key 锁的标识key
     * @param timeout 超时时间
     * @param expire 最少锁住
     * @return 如果成功获取锁返回true，超时则返回false
     * @throws InterruptedException 如果线程在等待过程中被中断
     */
    boolean tryReadLock(K key, Duration timeout, Duration expire) throws InterruptedException;

    /**
     * 尝试获取指定key的写锁，并设置超时和过期时间
     *
     * @param key 锁的标识key
     * @param timeout 超时时间
     * @param expire 最少锁住
     * @return 如果成功获取锁返回true，超时则返回false
     * @throws InterruptedException 如果线程在等待过程中被中断
     */
    boolean tryLock(K key, Duration timeout, Duration expire) throws InterruptedException;

    /**
     * 尝试获取指定key的读锁
     *
     * @param key 锁的标识key
     * @return 如果成功获取锁返回true，超时则返回false
     * @throws InterruptedException 如果线程在等待过程中被中断
     */
    boolean tryReadLock(K key) throws InterruptedException;

    /**
     * 尝试获取指定key的写锁
     *
     * @param key 锁的标识key
     * @return 如果成功获取锁返回true，超时则返回false
     * @throws InterruptedException 如果线程在等待过程中被中断
     */
    boolean tryLock(K key) throws InterruptedException;


    /**
     * 尝试获取指定key的读锁，并设置过期时间
     *
     * @param key 锁的标识key
     * @param expire 最少锁住
     * @return 如果成功获取锁返回true，超时则返回false
     * @throws InterruptedException 如果线程在等待过程中被中断
     */
    boolean tryReadLockNoTimeOut(K key, Duration expire) throws InterruptedException;

    /**
     * 尝试获取指定key的写锁，并设置过期时间
     *
     * @param key 锁的标识key
     * @param expire 最少锁住
     * @return 如果成功获取锁返回true，超时则返回false
     * @throws InterruptedException 如果线程在等待过程中被中断
     */
    boolean tryLockNoTimeOut(K key, Duration expire) throws InterruptedException;

    /**
     * 释放指定key锁
     *
     * @throws IllegalMonitorStateException 如果当前线程未持有该锁
     */
    void unlock();

}
