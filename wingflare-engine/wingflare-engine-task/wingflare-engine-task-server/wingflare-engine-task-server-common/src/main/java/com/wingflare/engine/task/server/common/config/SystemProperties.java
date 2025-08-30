package com.wingflare.engine.task.server.common.config;

import com.wingflare.engine.task.common.core.alarm.email.TaskMailProperties;
import com.wingflare.engine.task.common.core.enums.RpcTypeEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * 系统配置
 *
 * @author: opensnail
 * @date : 2021-12-21 10:19
 */
@Configuration
@ConfigurationProperties(value = "snail-job")
public class SystemProperties {

    /**
     * 重试每次拉取的条数
     */
    private int retryPullPageSize = 1000;

    /**
     * 重试任务拉取的并行度
     */
    private int retryMaxPullParallel = 2;

    /**
     * 任务调度每次拉取的条数
     */
    private int jobPullPageSize = 1000;

    /**
     * 服务端端口
     */
    private int serverPort = 17888;


    /**
     * 服务端地址
     */
    private String serverHost;

    /**
     * server token
     */
    private String serverToken = "SJ_H9HGGmrX3QBVTfsAAG2mcKH3SR7bCLsK";

    /**
     * 单个节点支持的最大调度量
     */
    private int maxDispatchCapacity = 10000;

    /**
     * 日志默认保存天数
     */
    private int logStorage = 7;

    /**
     * 合并日志默认保存天数
     */
    private int mergeLogDays = 1;

    /**
     * 合并日志默认的条数
     */
    private int mergeLogNum = 500;

    /**
     * 负载均衡周期时间
     */
    private int loadBalanceCycleTime = 10;

    /**
     * 桶的总数量
     */
    private int bucketTotal = 128;


    /**
     * Dashboard 任务容错天数
     */
    private int summaryDay = 7;

    /**
     * rpc类型
     */
    private RpcTypeEnum rpcType = RpcTypeEnum.GRPC;

    /**
     * 邮件配置
     */
    @NestedConfigurationProperty
    private TaskMailProperties mail = new TaskMailProperties();

    /**
     * 客户端Rpc配置
     */
    private RpcClientProperties clientRpc = new RpcClientProperties();

    /**
     * 服务端Rpc配置
     */
    private RpcServerProperties serverRpc = new RpcServerProperties();

    public SystemProperties() {
    }

    public SystemProperties(int retryPullPageSize, int retryMaxPullParallel, int jobPullPageSize, int serverPort, String serverHost, String serverToken, int maxDispatchCapacity, int logStorage, int mergeLogDays, int mergeLogNum, int loadBalanceCycleTime, int bucketTotal, int summaryDay, RpcTypeEnum rpcType, TaskMailProperties mail, RpcClientProperties clientRpc, RpcServerProperties serverRpc) {
        this.retryPullPageSize = retryPullPageSize;
        this.retryMaxPullParallel = retryMaxPullParallel;
        this.jobPullPageSize = jobPullPageSize;
        this.serverPort = serverPort;
        this.serverHost = serverHost;
        this.serverToken = serverToken;
        this.maxDispatchCapacity = maxDispatchCapacity;
        this.logStorage = logStorage;
        this.mergeLogDays = mergeLogDays;
        this.mergeLogNum = mergeLogNum;
        this.loadBalanceCycleTime = loadBalanceCycleTime;
        this.bucketTotal = bucketTotal;
        this.summaryDay = summaryDay;
        this.rpcType = rpcType;
        this.mail = mail;
        this.clientRpc = clientRpc;
        this.serverRpc = serverRpc;
    }

    public int getRetryPullPageSize() {
        return retryPullPageSize;
    }

    public void setRetryPullPageSize(int retryPullPageSize) {
        this.retryPullPageSize = retryPullPageSize;
    }

    public int getRetryMaxPullParallel() {
        return retryMaxPullParallel;
    }

    public void setRetryMaxPullParallel(int retryMaxPullParallel) {
        this.retryMaxPullParallel = retryMaxPullParallel;
    }

    public int getJobPullPageSize() {
        return jobPullPageSize;
    }

    public void setJobPullPageSize(int jobPullPageSize) {
        this.jobPullPageSize = jobPullPageSize;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public String getServerToken() {
        return serverToken;
    }

    public void setServerToken(String serverToken) {
        this.serverToken = serverToken;
    }

    public int getMaxDispatchCapacity() {
        return maxDispatchCapacity;
    }

    public void setMaxDispatchCapacity(int maxDispatchCapacity) {
        this.maxDispatchCapacity = maxDispatchCapacity;
    }

    public int getLogStorage() {
        return logStorage;
    }

    public void setLogStorage(int logStorage) {
        this.logStorage = logStorage;
    }

    public int getMergeLogDays() {
        return mergeLogDays;
    }

    public void setMergeLogDays(int mergeLogDays) {
        this.mergeLogDays = mergeLogDays;
    }

    public int getMergeLogNum() {
        return mergeLogNum;
    }

    public void setMergeLogNum(int mergeLogNum) {
        this.mergeLogNum = mergeLogNum;
    }

    public int getLoadBalanceCycleTime() {
        return loadBalanceCycleTime;
    }

    public void setLoadBalanceCycleTime(int loadBalanceCycleTime) {
        this.loadBalanceCycleTime = loadBalanceCycleTime;
    }

    public int getBucketTotal() {
        return bucketTotal;
    }

    public void setBucketTotal(int bucketTotal) {
        this.bucketTotal = bucketTotal;
    }

    public int getSummaryDay() {
        return summaryDay;
    }

    public void setSummaryDay(int summaryDay) {
        this.summaryDay = summaryDay;
    }

    public RpcTypeEnum getRpcType() {
        return rpcType;
    }

    public void setRpcType(RpcTypeEnum rpcType) {
        this.rpcType = rpcType;
    }

    public TaskMailProperties getMail() {
        return mail;
    }

    public void setMail(TaskMailProperties mail) {
        this.mail = mail;
    }

    public RpcClientProperties getClientRpc() {
        return clientRpc;
    }

    public void setClientRpc(RpcClientProperties clientRpc) {
        this.clientRpc = clientRpc;
    }

    public RpcServerProperties getServerRpc() {
        return serverRpc;
    }

    public void setServerRpc(RpcServerProperties serverRpc) {
        this.serverRpc = serverRpc;
    }

    public static class RpcServerProperties {

        private int maxInboundMessageSize = 10 * 1024 * 1024;

        private Duration keepAliveTime = Duration.of(30, ChronoUnit.SECONDS);

        private Duration keepAliveTimeout = Duration.of(10, ChronoUnit.SECONDS);

        private Duration permitKeepAliveTime = Duration.of(5, ChronoUnit.MINUTES);

        private ThreadPoolConfig dispatcherTp = new ThreadPoolConfig(16, 16, 1, TimeUnit.SECONDS, 10000);

        public int getMaxInboundMessageSize() {
            return maxInboundMessageSize;
        }

        public void setMaxInboundMessageSize(int maxInboundMessageSize) {
            this.maxInboundMessageSize = maxInboundMessageSize;
        }

        public Duration getKeepAliveTime() {
            return keepAliveTime;
        }

        public void setKeepAliveTime(Duration keepAliveTime) {
            this.keepAliveTime = keepAliveTime;
        }

        public Duration getKeepAliveTimeout() {
            return keepAliveTimeout;
        }

        public void setKeepAliveTimeout(Duration keepAliveTimeout) {
            this.keepAliveTimeout = keepAliveTimeout;
        }

        public Duration getPermitKeepAliveTime() {
            return permitKeepAliveTime;
        }

        public void setPermitKeepAliveTime(Duration permitKeepAliveTime) {
            this.permitKeepAliveTime = permitKeepAliveTime;
        }

        public ThreadPoolConfig getDispatcherTp() {
            return dispatcherTp;
        }

        public void setDispatcherTp(ThreadPoolConfig dispatcherTp) {
            this.dispatcherTp = dispatcherTp;
        }
    }


    public static class RpcClientProperties {

        private int maxInboundMessageSize = 10 * 1024 * 1024;

        private Duration keepAliveTime = Duration.of(30, ChronoUnit.SECONDS);

        private Duration keepAliveTimeout = Duration.of(10, ChronoUnit.SECONDS);

        private Duration idleTimeout = Duration.of(5, ChronoUnit.MINUTES);

        private ThreadPoolConfig clientTp = new ThreadPoolConfig(16, 16, 1, TimeUnit.SECONDS, 10000);

        public int getMaxInboundMessageSize() {
            return maxInboundMessageSize;
        }

        public void setMaxInboundMessageSize(int maxInboundMessageSize) {
            this.maxInboundMessageSize = maxInboundMessageSize;
        }

        public Duration getKeepAliveTime() {
            return keepAliveTime;
        }

        public void setKeepAliveTime(Duration keepAliveTime) {
            this.keepAliveTime = keepAliveTime;
        }

        public Duration getKeepAliveTimeout() {
            return keepAliveTimeout;
        }

        public void setKeepAliveTimeout(Duration keepAliveTimeout) {
            this.keepAliveTimeout = keepAliveTimeout;
        }

        public Duration getIdleTimeout() {
            return idleTimeout;
        }

        public void setIdleTimeout(Duration idleTimeout) {
            this.idleTimeout = idleTimeout;
        }

        public ThreadPoolConfig getClientTp() {
            return clientTp;
        }

        public void setClientTp(ThreadPoolConfig clientTp) {
            this.clientTp = clientTp;
        }
    }


    public static class ThreadPoolConfig {

        /**
         * 核心线程池
         */
        private int corePoolSize = 16;

        /**
         * 最大线程数
         */
        private int maximumPoolSize = 16;

        /**
         * 线程存活时间
         */
        private long keepAliveTime = 1;

        /**
         * 线程存活时间(单位)
         */
        private TimeUnit timeUnit = TimeUnit.SECONDS;

        /**
         * 队列容量
         */
        private int queueCapacity = 10000;

        public ThreadPoolConfig() {
        }

        public ThreadPoolConfig(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit, int queueCapacity) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
            this.timeUnit = timeUnit;
            this.queueCapacity = queueCapacity;
        }

        public int getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaximumPoolSize() {
            return maximumPoolSize;
        }

        public void setMaximumPoolSize(int maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
        }

        public long getKeepAliveTime() {
            return keepAliveTime;
        }

        public void setKeepAliveTime(long keepAliveTime) {
            this.keepAliveTime = keepAliveTime;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }

        public void setTimeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
        }

        public int getQueueCapacity() {
            return queueCapacity;
        }

        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }
    }
}
