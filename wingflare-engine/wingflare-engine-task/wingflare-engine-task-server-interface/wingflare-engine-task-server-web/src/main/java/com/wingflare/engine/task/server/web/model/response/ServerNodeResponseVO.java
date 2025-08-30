package com.wingflare.engine.task.server.web.model.response;


import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author opensnail
 * @date 2023-06-06
 * @since 2.0
 */
public class ServerNodeResponseVO {

    private Long id;

    private String groupName;

    private String hostId;

    private String hostIp;

    private Integer hostPort;

    private Integer nodeType;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;

    private String extAttrs;

    private Set<Integer> consumerBuckets;

    private String labels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public Integer getHostPort() {
        return hostPort;
    }

    public void setHostPort(Integer hostPort) {
        this.hostPort = hostPort;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public LocalDateTime getCreateDt() {
        return createDt;
    }

    public void setCreateDt(LocalDateTime createDt) {
        this.createDt = createDt;
    }

    public LocalDateTime getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(LocalDateTime updateDt) {
        this.updateDt = updateDt;
    }

    public String getExtAttrs() {
        return extAttrs;
    }

    public void setExtAttrs(String extAttrs) {
        this.extAttrs = extAttrs;
    }

    public Set<Integer> getConsumerBuckets() {
        return consumerBuckets;
    }

    public void setConsumerBuckets(Set<Integer> consumerBuckets) {
        this.consumerBuckets = consumerBuckets;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }
}
