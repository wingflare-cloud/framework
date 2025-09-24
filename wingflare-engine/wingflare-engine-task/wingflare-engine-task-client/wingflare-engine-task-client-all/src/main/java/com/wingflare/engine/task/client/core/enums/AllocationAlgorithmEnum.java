package com.wingflare.engine.task.client.core.enums;


public enum AllocationAlgorithmEnum {
    // Hash
    CONSISTENT_HASH(1),
    // 随机
    RANDOM(2),
    // LRU
    LRU(3),
    // 轮询
    ROUND(4),
    // 匹配第一个
    FIRST(5),
    // 匹配最后一个
    LAST(6);

    private final int type;

    AllocationAlgorithmEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

}