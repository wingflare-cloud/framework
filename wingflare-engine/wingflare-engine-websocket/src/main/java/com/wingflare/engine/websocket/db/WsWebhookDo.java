package com.wingflare.engine.websocket.db;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.wingflare.lib.mybatis.plus.base.BaseDoAbstract;
import java.util.Date;

/**
 * <p>
 * ws webhook信息表 数据对象
 * </p>
 *
 * @author 
 * @since 2024-01-11
 */
@TableName("wf_ws_webhook")
public class WsWebhookDo extends BaseDoAbstract {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 服务名 
     */
    private String serverName;

    /**
     * 主机 
     */
    private String host;

    /**
     * 请求路径 
     */
    private String path;

    /**
     * 频道名 
     */
    private String topic;

    /**
     * 是否启用ssl 
     */
    private Integer enableSsl;

    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    @TableField(fill = FieldFill.INSERT)
    private String createUserId;

    private String updateUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Byte isDelete;

    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;

	@Override
    public void setPk(String id)
    {
        setId(id);
    }

	@Override
    public String getPk()
    {
        return getId();
    }

    public String getId() {
        return id;
    }

    public WsWebhookDo setId(String id) {
        addNewField("id");
        this.id = id;
        return this;
    }

    public String getServerName() {
        return serverName;
    }

    public WsWebhookDo setServerName(String serverName) {
        addNewField("serverName");
        this.serverName = serverName;
        return this;
    }

    public String getHost() {
        return host;
    }

    public WsWebhookDo setHost(String host) {
        addNewField("host");
        this.host = host;
        return this;
    }

    public String getPath() {
        return path;
    }

    public WsWebhookDo setPath(String path) {
        addNewField("path");
        this.path = path;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public WsWebhookDo setTopic(String topic) {
        addNewField("topic");
        this.topic = topic;
        return this;
    }

    public Integer getEnableSsl() {
        return enableSsl;
    }

    public WsWebhookDo setEnableSsl(Integer enableSsl) {
        addNewField("enableSsl");
        this.enableSsl = enableSsl;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public WsWebhookDo setCreatedTime(Date createdTime) {
        addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public WsWebhookDo setUpdatedTime(Date updatedTime) {
        addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public WsWebhookDo setCreateUser(String createUser) {
        addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public WsWebhookDo setCreateUserId(String createUserId) {
        addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public WsWebhookDo setUpdateUser(String updateUser) {
        addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public WsWebhookDo setUpdateUserId(String updateUserId) {
        addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public WsWebhookDo setIsDelete(Byte isDelete) {
        addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public WsWebhookDo setVersion(Integer version) {
        addNewField("version");
        this.version = version;
        return this;
    }

	@Override
    public void clearNullNewField() {

	    if (getId() == null) {
            removeNewField("id");
        }

	    if (getServerName() == null) {
            removeNewField("serverName");
        }

	    if (getHost() == null) {
            removeNewField("host");
        }

	    if (getPath() == null) {
            removeNewField("path");
        }

	    if (getTopic() == null) {
            removeNewField("topic");
        }

	    if (getEnableSsl() == null) {
            removeNewField("enableSsl");
        }

	    if (getCreatedTime() == null) {
            removeNewField("createdTime");
        }

	    if (getUpdatedTime() == null) {
            removeNewField("updatedTime");
        }

	    if (getCreateUser() == null) {
            removeNewField("createUser");
        }

	    if (getCreateUserId() == null) {
            removeNewField("createUserId");
        }

	    if (getUpdateUser() == null) {
            removeNewField("updateUser");
        }

	    if (getUpdateUserId() == null) {
            removeNewField("updateUserId");
        }

	    if (getIsDelete() == null) {
            removeNewField("isDelete");
        }

	    if (getVersion() == null) {
            removeNewField("version");
        }
    }

    public WsWebhookDo setOnNew(WsWebhookDo newDo)
    {
        WsWebhookDo oldFieldObj = new WsWebhookDo();

        if (newDo.getId() != null && !newDo.getId().equals(getId())) {
            oldFieldObj.setId(getId());
            setId(newDo.getId());
        }

        if (newDo.getServerName() != null && !newDo.getServerName().equals(getServerName())) {
            oldFieldObj.setServerName(getServerName());
            setServerName(newDo.getServerName());
        }

        if (newDo.getHost() != null && !newDo.getHost().equals(getHost())) {
            oldFieldObj.setHost(getHost());
            setHost(newDo.getHost());
        }

        if (newDo.getPath() != null && !newDo.getPath().equals(getPath())) {
            oldFieldObj.setPath(getPath());
            setPath(newDo.getPath());
        }

        if (newDo.getTopic() != null && !newDo.getTopic().equals(getTopic())) {
            oldFieldObj.setTopic(getTopic());
            setTopic(newDo.getTopic());
        }

        if (newDo.getEnableSsl() != null && !newDo.getEnableSsl().equals(getEnableSsl())) {
            oldFieldObj.setEnableSsl(getEnableSsl());
            setEnableSsl(newDo.getEnableSsl());
        }

        if (newDo.getVersion() != null && !newDo.getVersion().equals(getVersion())) {
            oldFieldObj.setVersion(getVersion());
            setVersion(newDo.getVersion());
        }

        if (!oldFieldObj.hasNewField()) {
            return null;
        }

        return oldFieldObj;
    }

	@Override
    public String toString() {
        return "WsWebhookDo{" +
        "id = " + id +
        ", serverName = " + serverName +
        ", host = " + host +
        ", path = " + path +
        ", topic = " + topic +
        ", enableSsl = " + enableSsl +
        ", createdTime = " + createdTime +
        ", updatedTime = " + updatedTime +
        ", createUser = " + createUser +
        ", createUserId = " + createUserId +
        ", updateUser = " + updateUser +
        ", updateUserId = " + updateUserId +
        ", isDelete = " + isDelete +
        ", version = " + version +
        "}";
    }

}
