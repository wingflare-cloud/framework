package com.wingflare.engine.websocket.db;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.wingflare.lib.mybatis.plus.base.BaseDoAbstract;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * webhook信息Do
 *
 * @author naizui_ycx
 * @date Sun Apr 02 10:15:03 CST 2023
 */
@TableName("wf_ws_webhook")
public class WebhookDo extends BaseDoAbstract {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 主机名
     */
    private String serverName;

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

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDelete;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    public String getId() {
        return id;
    }

    public WebhookDo setId(String id) {
        addNewField("id");
        this.id = id;
        return this;
    }

    public String getServerName() {
        return serverName;
    }

    public WebhookDo setServerName(String serverName) {
        addNewField("serverName");
        this.serverName = serverName;
        return this;
    }

    public String getPath() {
        return path;
    }

    public WebhookDo setPath(String path) {
        addNewField("path");
        this.path = path;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public WebhookDo setTopic(String topic) {
        addNewField("topic");
        this.topic = topic;
        return this;
    }

    public Integer getEnableSsl() {
        return enableSsl;
    }

    public WebhookDo setEnableSsl(Integer enableSsl) {
        addNewField("enableSsl");
        this.enableSsl = enableSsl;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public WebhookDo setCreatedTime(Date createdTime) {
        addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public WebhookDo setUpdatedTime(Date updatedTime) {
        addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public WebhookDo setIsDelete(Integer isDelete) {
        addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public WebhookDo setVersion(Integer version) {
        addNewField("version");
        this.version = version;
        return this;
    }

    @Override
    public void setPk(String pk) {
        setId(pk);
    }

    @Override
    public String getPk() {
        return getId();
    }

    public WebhookDo setOnNew(WebhookDo newDo)
    {
        WebhookDo oldFieldObj = new WebhookDo();

        if (newDo.getServerName() != null && !newDo.getServerName().equals(getServerName())) {
            oldFieldObj.setServerName(getServerName());
            setServerName(newDo.getServerName());
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
    public void clearNullNewField() {
        if (getId() == null) {
            removeNewField("id");
        }

        if (getServerName() == null) {
            removeNewField("serverName");
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("topic", getTopic())
                .append("serverName", getServerName())
                .append("path", getPath())
                .append("enableSsl", getEnableSsl())
                .append("createdTime", getCreatedTime())
                .append("updatedTime", getUpdatedTime())
                .append("createUser", getCreateUser())
                .append("createUserId", getCreateUserId())
                .append("updateUser", getUpdateUser())
                .append("updateUserId", getUpdateUserId())
                .append("isDelete", getIsDelete())
                .append("version", getVersion())
                .toString();
    }

}
