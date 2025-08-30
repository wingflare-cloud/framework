package com.wingflare.engine.task.server.web.model.response;


import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: opensnail
 * @date : 2022-02-25 13:42
 */
public class GroupConfigResponseVO {

    private Long id;

    private String groupName;

    private String namespaceId;

    private String namespaceName;

    private Integer groupStatus;

    private Integer groupPartition;

    private Integer routeKey;

    private Integer version;

    private String description;

    private Integer idGeneratorMode;

    private String idGeneratorModeName;

    private Integer initScene;

    private List<String> onlinePodList;

    private String token;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;

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

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getNamespaceName() {
        return namespaceName;
    }

    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    public Integer getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(Integer groupStatus) {
        this.groupStatus = groupStatus;
    }

    public Integer getGroupPartition() {
        return groupPartition;
    }

    public void setGroupPartition(Integer groupPartition) {
        this.groupPartition = groupPartition;
    }

    public Integer getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(Integer routeKey) {
        this.routeKey = routeKey;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdGeneratorMode() {
        return idGeneratorMode;
    }

    public void setIdGeneratorMode(Integer idGeneratorMode) {
        this.idGeneratorMode = idGeneratorMode;
    }

    public String getIdGeneratorModeName() {
        return idGeneratorModeName;
    }

    public void setIdGeneratorModeName(String idGeneratorModeName) {
        this.idGeneratorModeName = idGeneratorModeName;
    }

    public Integer getInitScene() {
        return initScene;
    }

    public void setInitScene(Integer initScene) {
        this.initScene = initScene;
    }

    public List<String> getOnlinePodList() {
        return onlinePodList;
    }

    public void setOnlinePodList(List<String> onlinePodList) {
        this.onlinePodList = onlinePodList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
}
