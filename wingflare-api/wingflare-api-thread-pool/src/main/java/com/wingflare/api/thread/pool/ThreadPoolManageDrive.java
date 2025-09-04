package com.wingflare.api.thread.pool;


import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 线程池管理驱动
 */
public interface ThreadPoolManageDrive {

    /**
     * 提交一个 Runnable 任务到默认线程池执行，无返回值
     * @param command
     */
    void execute(Runnable command);

    /**
     * 提交一个 Callable 任务（有返回值），返回 Future<T> 对象，可通过该对象获取任务执行结果（get() 方法）或取消任务（cancel() 方法）
     *
     * @param task
     * @return
     * @param <T>
     */
    <T> Future<T> submit(Callable<T> task);

    /**
     * 提交 Runnable 任务，同时指定一个 "默认结果"。当任务执行完成后，Future.get() 会返回该默认结果（适合需要返回值但任务本身无计算结果的场景）
     * @param task
     * @param result
     * @return
     * @param <T>
     */
    <T> Future<T> submit(Runnable task, T result);

    /**
     * 提交 Runnable 任务
     * @param task
     * @return
     */
    Future<?> submit(Runnable task);

    /**
     * 提交一批 Callable 任务，阻塞等待所有任务完成，返回包含所有任务结果的 Future 列表
     * @param tasks
     * @return
     * @param <T>
     * @throws InterruptedException
     */
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
            throws InterruptedException;


    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                  long timeout, TimeUnit unit)
            throws InterruptedException;

    /**
     * 提交一批 Callable 任务，阻塞等待任意一个任务完成，返回该任务的结果（其他未完成任务会被取消）
     * @param tasks
     * @return
     * @param <T>
     * @throws InterruptedException
     * @throws ExecutionException
     */
    <T> T invokeAny(Collection<? extends Callable<T>> tasks)
            throws InterruptedException, ExecutionException;

    <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                    long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException;

    /**
     * 提交一个 Runnable 任务到指定线程池执行，无返回值
     * @param command
     */
    void execute(String key, Runnable command);

    /**
     * 提交一个 Callable 任务（有返回值），返回 Future<T> 对象，可通过该对象获取任务执行结果（get() 方法）或取消任务（cancel() 方法）
     *
     * @param task
     * @return
     * @param <T>
     */
    <T> Future<T> submit(String key, Callable<T> task);

    <T> Future<T> submit(String key, Runnable task, T result);

    Future<?> submit(String key, Runnable task);

    <T> List<Future<T>> invokeAll(String key, Collection<? extends Callable<T>> tasks)
            throws InterruptedException;

    <T> List<Future<T>> invokeAll(String key, Collection<? extends Callable<T>> tasks,
                                  long timeout, TimeUnit unit)
            throws InterruptedException;

    <T> T invokeAny(String key, Collection<? extends Callable<T>> tasks)
            throws InterruptedException, ExecutionException;

    <T> T invokeAny(String key, Collection<? extends Callable<T>> tasks,
                    long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException;

    /**
     * 获取线程池工厂
     * @return
     */
    ThreadFactory factory();

    ThreadFactory factory(String key);

}
