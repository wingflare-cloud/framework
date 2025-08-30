package com.wingflare.engine.task.server.starter.dispatch;


import java.util.Set;

/**
 * @author opensnail
 * @date 2023-09-21 23:30:22
 * @since 2.4.0
 */
public class ConsumerBucket {

    private Set<Integer> buckets;

    public Set<Integer> getBuckets() {
        return buckets;
    }

    public void setBuckets(Set<Integer> buckets) {
        this.buckets = buckets;
    }
}
