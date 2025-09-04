package com.wingflare.engine.task.datasource.template.persistence.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 组配置
 *
 * @author opensnail
 * @since 2023-01-14
 */
@TableName("wf_task_group_config")
public class GroupConfig extends CreateUpdateDt {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String namespaceId;

    private String groupName;

    private Integer groupStatus;

    private Integer groupPartition;

    private Integer idGeneratorMode;

    @TableField(value = "version", update = "%s+1")
    private Integer version;

    private Integer initScene;

    private String token;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public Integer getIdGeneratorMode() {
        return idGeneratorMode;
    }

    public void setIdGeneratorMode(Integer idGeneratorMode) {
        this.idGeneratorMode = idGeneratorMode;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getInitScene() {
        return initScene;
    }

    public void setInitScene(Integer initScene) {
        this.initScene = initScene;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
