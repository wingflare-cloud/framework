package com.wingflare.engine.task.server.common.dto;


import java.util.Set;

/**
 * 扫描任务模型
 *
 * @author: opensnail
 * @date : 2023-06-05 16:30
 * @since 1.5.0
 */
public class ScanTask {

    private Set<Integer> buckets;

    private String bucketStr;

    public Set<Integer> getBuckets() {
        return buckets;
    }

    public void setBuckets(Set<Integer> buckets) {
        this.buckets = buckets;
    }

    public String getBucketStr() {
        return bucketStr;
    }

    public void setBucketStr(String bucketStr) {
        this.bucketStr = bucketStr;
    }
}
