package com.wingflare.lib.scheduler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 时间轮槽位 - 双向链表实现
 * 每个槽位维护一个任务链表，支持高效的插入和删除操作
 */
public class TimeSlot {
    
    // 槽位头节点（哨兵节点）
    private final TaskNode head;
    
    // 槽位中的任务数量
    private final AtomicInteger taskCount;
    
    // 槽位索引
    private final int slotIndex;
    
    /**
     * 创建时间槽位
     */
    public TimeSlot(int slotIndex) {
        this.slotIndex = slotIndex;
        this.head = new TaskNode(); // 哨兵节点
        this.head.setNext(head);
        this.head.setPrev(head);
        this.taskCount = new AtomicInteger(0);
    }
    
    /**
     * 添加任务到槽位
     */
    public void addTask(TaskNode taskNode) {
        if (taskNode == null) {
            return;
        }
        
        synchronized (head) {
            // 在头节点后插入
            taskNode.setNext(head.getNext());
            taskNode.setPrev(head);
            
            head.getNext().setPrev(taskNode);
            head.setNext(taskNode);
            
            taskNode.setSlotIndex(slotIndex);
            taskCount.incrementAndGet();
        }
    }
    
    /**
     * 从槽位移除任务
     */
    public boolean removeTask(TaskNode taskNode) {
        if (taskNode == null) {
            return false;
        }
        
        synchronized (head) {
            // 检查任务是否在当前槽位
            if (taskNode.getSlotIndex() != slotIndex) {
                return false;
            }
            
            TaskNode prev = taskNode.getPrev();
            TaskNode next = taskNode.getNext();
            
            if (prev != null) {
                prev.setNext(next);
            }
            if (next != null) {
                next.setPrev(prev);
            }
            
            taskNode.setPrev(null);
            taskNode.setNext(null);
            taskNode.setSlotIndex(-1);
            
            taskCount.decrementAndGet();
            return true;
        }
    }
    
    /**
     * 获取并移除所有到期任务
     */
    public TaskNode pollExpiredTasks() {
        synchronized (head) {
            if (isEmpty()) {
                return null;
            }
            
            // 获取第一个任务节点
            TaskNode firstTask = head.getNext();
            if (firstTask == head) { // 空链表
                return null;
            }
            
            // 摘取整个链表
            TaskNode lastTask = head.getPrev();
            
            // 重置头节点指针
            head.setNext(head);
            head.setPrev(head);
            
            // 断开链表连接
            firstTask.setPrev(null);
            lastTask.setNext(null);
            
            // 重置任务计数
            int count = taskCount.getAndSet(0);
            
            // 重置所有任务节点的槽位索引
            TaskNode current = firstTask;
            while (current != null) {
                current.setSlotIndex(-1);
                current = current.getNext();
            }
            
            return firstTask;
        }
    }
    
    /**
     * 清空槽位
     */
    public void clear() {
        synchronized (head) {
            TaskNode current = head.getNext();
            
            while (current != head) {
                TaskNode next = current.getNext();
                
                current.setPrev(null);
                current.setNext(null);
                current.setSlotIndex(-1);
                
                current = next;
            }
            
            head.setNext(head);
            head.setPrev(head);
            taskCount.set(0);
        }
    }
    
    /**
     * 检查槽位是否为空
     */
    public boolean isEmpty() {
        return taskCount.get() == 0;
    }
    
    /**
     * 获取槽位中的任务数量
     */
    public int getTaskCount() {
        return taskCount.get();
    }
    
    /**
     * 获取槽位索引
     */
    public int getSlotIndex() {
        return slotIndex;
    }
    
    /**
     * 遍历槽位中的所有任务（调试用）
     */
    public void forEach(TaskConsumer consumer) {
        synchronized (head) {
            TaskNode current = head.getNext();
            
            while (current != head) {
                TaskNode next = current.getNext(); // 提前保存，防止在消费过程中被修改
                consumer.accept(current);
                current = next;
            }
        }
    }
    
    /**
     * 任务消费者接口
     */
    @FunctionalInterface
    public interface TaskConsumer {
        void accept(TaskNode taskNode);
    }
    
    @Override
    public String toString() {
        return String.format("TimeSlot[index=%d, taskCount=%d]", slotIndex, taskCount.get());
    }
}