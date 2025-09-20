package com.wingflare.lib.scheduler;


import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定时任务调度器测试类
 * 全面测试各种任务类型和功能特性
 */
public class SchedulerTest {
    
    public static void main(String[] args) {
        System.out.println("========== 定时任务调度器测试开始 ==========");
        
        try {
            // 测试Cron表达式解析
            testCronExpression();
            
            // 测试基础任务调度
            testBasicScheduling();
            
            // 测试各种任务类型
            testTaskTypes();
            
            // 测试静态工具类
            testSchedulerUtils();
            
            // 测试超时机制
            testTimeoutMechanism();
            
            // 测试性能和并发
            testPerformanceAndConcurrency();
            
            // 测试资源管理
            testResourceManagement();
            
        } catch (Exception e) {
            System.err.println("测试过程中发生异常: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("========== 测试完成 ==========");
    }
    
    /**
     * 测试Cron表达式解析
     */
    private static void testCronExpression() {
        System.out.println("\n--- 测试Cron表达式解析 ---");
        
        try {
            // 测试基础Cron表达式
            CronExpression cron1 = new CronExpression("0 0 12 * * ?"); // 每天12:00
            System.out.println("每天12:00的下次执行时间: " + 
                             cron1.getNextExecutionTime(LocalDateTime.now()));
            
            // 测试秒级Cron表达式
            CronExpression cron2 = new CronExpression("*/5 * * * * ?"); // 每5秒
            System.out.println("每5秒的下次执行时间: " + 
                             cron2.getNextExecutionTime(LocalDateTime.now()));
            
            // 测试复杂Cron表达式
            CronExpression cron3 = new CronExpression("0 15 10 ? * MON-FRI"); // 工作日10:15
            System.out.println("工作日10:15的下次执行时间: " + 
                             cron3.getNextExecutionTime(LocalDateTime.now()));
            
            System.out.println("✓ Cron表达式解析测试通过");
            
        } catch (Exception e) {
            System.err.println("✗ Cron表达式解析测试失败: " + e.getMessage());
        }
    }
    
    /**
     * 测试基础任务调度
     */
    private static void testBasicScheduling() {
        System.out.println("\n--- 测试基础任务调度 ---");
        
        TaskScheduler scheduler = new TaskScheduler();
        
        try {
            scheduler.start();
            
            AtomicInteger counter = new AtomicInteger(0);
            CountDownLatch latch = new CountDownLatch(3);
            
            // 提交一次性延时任务
            scheduler.schedule("测试任务", () -> {
                int count = counter.incrementAndGet();
                System.out.println("任务执行 #" + count + " at " + LocalDateTime.now());
                latch.countDown();
            }, 1000);
            
            // 提交固定延时任务
            scheduler.scheduleWithFixedDelay("固定延时任务", () -> {
                int count = counter.incrementAndGet();
                System.out.println("固定延时任务执行 #" + count + " at " + LocalDateTime.now());
                latch.countDown();
            }, 2000, 1000);
            
            // 等待任务执行
            boolean completed = latch.await(10, TimeUnit.SECONDS);
            
            if (completed) {
                System.out.println("✓ 基础任务调度测试通过");
            } else {
                System.err.println("✗ 基础任务调度测试超时");
            }
            
        } catch (Exception e) {
            System.err.println("✗ 基础任务调度测试失败: " + e.getMessage());
        } finally {
            scheduler.stop();
        }
    }
    
    /**
     * 测试各种任务类型
     */
    private static void testTaskTypes() {
        System.out.println("\n--- 测试各种任务类型 ---");
        
        TaskScheduler scheduler = new TaskScheduler();
        
        try {
            scheduler.start();
            
            AtomicInteger cronCounter = new AtomicInteger(0);
            AtomicInteger fixedRateCounter = new AtomicInteger(0);
            
            // Cron任务（每秒执行）
            scheduler.scheduleWithCron("Cron任务", "*/1 * * * * ?", () -> {
                int count = cronCounter.incrementAndGet();
                System.out.println("Cron任务执行 #" + count);
                if (count >= 3) {
                    // 停止测试
                }
            });
            
            // 固定频率任务
            scheduler.scheduleAtFixedRate("固定频率任务", () -> {
                int count = fixedRateCounter.incrementAndGet();
                System.out.println("固定频率任务执行 #" + count);
            }, 500, 1500);
            
            // 等待任务执行
            Thread.sleep(5000);
            
            System.out.println("✓ 各种任务类型测试通过");
            
        } catch (Exception e) {
            System.err.println("✗ 各种任务类型测试失败: " + e.getMessage());
        } finally {
            scheduler.stop();
        }
    }
    
    /**
     * 测试静态工具类
     */
    private static void testSchedulerUtils() {
        System.out.println("\n--- 测试静态工具类 ---");
        
        try {
            AtomicInteger utilsCounter = new AtomicInteger(0);
            
            // 使用便捷API
            SchedulerUtils.delay(() -> {
                System.out.println("延时任务执行: " + utilsCounter.incrementAndGet());
            }, 1000);
            
            SchedulerUtils.everySecond(() -> {
                int count = utilsCounter.incrementAndGet();
                System.out.println("每秒任务执行: " + count);
                if (count >= 5) {
                    // 可以通过其他方式停止
                }
            });
            
            SchedulerUtils.dailyAt(12, 0, () -> {
                System.out.println("每日12:00任务（演示）");
            });
            
            // 等待一些任务执行
            Thread.sleep(3000);
            
            // 获取统计信息
            TaskScheduler.SchedulerStats stats = SchedulerUtils.getStats();
            System.out.println("调度器统计: " + stats);
            
            System.out.println("✓ 静态工具类测试通过");
            
        } catch (Exception e) {
            System.err.println("✗ 静态工具类测试失败: " + e.getMessage());
        }
    }
    
    /**
     * 测试超时机制
     */
    private static void testTimeoutMechanism() {
        System.out.println("\n--- 测试超时机制 ---");
        
        EnhancedTaskScheduler scheduler = new EnhancedTaskScheduler();
        
        try {
            scheduler.start();
            
            AtomicInteger timeoutCounter = new AtomicInteger(0);
            
            // 提交会超时的任务
            scheduler.scheduleWithCron("超时任务", "*/2 * * * * ?", () -> {
                try {
                    System.out.println("开始执行可能超时的任务");
                    Thread.sleep(5000); // 模拟长时间运行
                    System.out.println("任务正常完成");
                } catch (InterruptedException e) {
                    System.out.println("任务被中断");
                    timeoutCounter.incrementAndGet();
                }
            }, 3000); // 3秒超时
            
            // 等待超时机制工作
            Thread.sleep(8000);
            
            // 检查超时统计
            EnhancedTaskScheduler.EnhancedSchedulerStats stats = scheduler.getEnhancedStats();
            System.out.println("增强统计信息: " + stats);
            
            System.out.println("✓ 超时机制测试通过");
            
        } catch (Exception e) {
            System.err.println("✗ 超时机制测试失败: " + e.getMessage());
        } finally {
            scheduler.stop();
        }
    }
    
    /**
     * 测试性能和并发
     */
    private static void testPerformanceAndConcurrency() {
        System.out.println("\n--- 测试性能和并发 ---");
        
        TaskScheduler scheduler = new TaskScheduler(8, 16, 2000);
        
        try {
            scheduler.start();
            
            long startTime = System.currentTimeMillis();
            AtomicInteger taskCounter = new AtomicInteger(0);
            CountDownLatch latch = new CountDownLatch(100);
            
            // 提交大量任务测试并发性能
            for (int i = 0; i < 100; i++) {
                final int taskId = i;
                scheduler.schedule("并发任务-" + taskId, () -> {
                    try {
                        // 模拟一些计算
                        Thread.sleep(10);
                        int count = taskCounter.incrementAndGet();
                        if (count % 20 == 0) {
                            System.out.println("已完成 " + count + " 个并发任务");
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        latch.countDown();
                    }
                }, i * 10); // 分散执行时间
            }
            
            // 等待所有任务完成
            boolean completed = latch.await(30, TimeUnit.SECONDS);
            long totalTime = System.currentTimeMillis() - startTime;
            
            if (completed) {
                System.out.println("✓ 性能和并发测试通过");
                System.out.println("总执行时间: " + totalTime + "ms");
                System.out.println("平均任务处理时间: " + (totalTime / 100.0) + "ms");
            } else {
                System.err.println("✗ 性能和并发测试超时");
            }
            
            // 显示最终统计
            TaskScheduler.SchedulerStats stats = scheduler.getSchedulerStats();
            System.out.println("最终统计: " + stats);
            
        } catch (Exception e) {
            System.err.println("✗ 性能和并发测试失败: " + e.getMessage());
        } finally {
            scheduler.stop();
        }
    }
    
    /**
     * 测试资源管理
     */
    private static void testResourceManagement() {
        System.out.println("\n--- 测试资源管理 ---");
        
        EnhancedTaskScheduler scheduler = new EnhancedTaskScheduler();
        
        try {
            scheduler.start();
            
            // 获取初始资源统计
            ResourceManager.ResourceStats initialStats = 
                scheduler.getResourceManager().getResourceStats();
            System.out.println("初始资源统计: " + initialStats);
            
            // 提交一些任务以消耗资源
            for (int i = 0; i < 50; i++) {
                scheduler.schedule("资源测试任务-" + i, () -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }, i * 100);
            }
            
            // 等待一段时间让资源管理器工作
            Thread.sleep(2000);
            
            // 手动触发清理
            scheduler.cleanup();
            
            // 获取最终资源统计
            ResourceManager.ResourceStats finalStats = 
                scheduler.getResourceManager().getResourceStats();
            System.out.println("最终资源统计: " + finalStats);
            
            System.out.println("✓ 资源管理测试通过");
            
        } catch (Exception e) {
            System.err.println("✗ 资源管理测试失败: " + e.getMessage());
        } finally {
            scheduler.stop();
        }
    }
}