package com.wingflare.engine.task.server.web.model.request;

import com.wingflare.engine.task.server.common.enums.IdGeneratorModeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * 组、场景、通知配置类
 *
 * @author: opensnail
 * @date : 2021-11-22 13:45
 * @since 1.0.0
 */
public class GroupConfigRequestVO {

    @NotBlank(message = "Group name cannot be null")
    @Pattern(regexp = "^[A-Za-z0-9_-]{1,64}$", message = "Only supports 1~64 characters, including numbers, letters, underscores, and hyphens")
    private String groupName;

    @NotNull(message = "Group status cannot be null")
    private Integer groupStatus;

    /**
     * 令牌
     */
    @NotBlank(message = "Token cannot be null")
    private String token;

    /**
     * 描述
     */
    private String description;

    /**
     * 分区
     */
    @NotNull(message = "Partition cannot be null")
    private Integer groupPartition;

    /**
     * 唯一id生成模式
     * {@link IdGeneratorModeEnum}
     */
    @NotNull(message = "ID generation mode cannot be null")
    private Integer idGeneratorMode;

    /**
     * 是否初始化场景
     */
    @NotNull(message = "Initialization field cannot be null")
    private Integer initScene;

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

    public Integer getInitScene() {
        return initScene;
    }

    public void setInitScene(Integer initScene) {
        this.initScene = initScene;
    }
}
