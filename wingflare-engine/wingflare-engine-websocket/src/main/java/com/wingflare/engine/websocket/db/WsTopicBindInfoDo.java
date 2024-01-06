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
 * 频道终端绑定信息 数据对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2024-01-06
 */
@TableName("wf_ws_topic_bind_info")
public class WsTopicBindInfoDo extends BaseDoAbstract {

    @TableId(value = "bind_id", type = IdType.ASSIGN_ID)
    private String bindId;

    /**
     * 频道名 
     */
    private String topic;

    /**
     * 频道类型 
     */
    private String topicType;

    /**
     * 终端sn
     */
    private String terminalSn;

    /**
     * 权限数值 
     */
    private Integer permissionNum;

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
    public void setPk(String bindId)
    {
        setBindId(bindId);
    }

	@Override
    public String getPk()
    {
        return getBindId();
    }

    public String getBindId() {
        return bindId;
    }

    public WsTopicBindInfoDo setBindId(String bindId) {
        addNewField("bindId");
        this.bindId = bindId;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public WsTopicBindInfoDo setTopic(String topic) {
        addNewField("topic");
        this.topic = topic;
        return this;
    }

    public String getTopicType() {
        return topicType;
    }

    public WsTopicBindInfoDo setTopicType(String topicType) {
        addNewField("topicType");
        this.topicType = topicType;
        return this;
    }

    public String getTerminalSn() {
        return terminalSn;
    }

    public WsTopicBindInfoDo setTerminalSn(String terminalSn) {
        addNewField("terminalSn");
        this.terminalSn = terminalSn;
        return this;
    }

    public Integer getPermissionNum() {
        return permissionNum;
    }

    public WsTopicBindInfoDo setPermissionNum(Integer permissionNum) {
        addNewField("permissionNum");
        this.permissionNum = permissionNum;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public WsTopicBindInfoDo setCreatedTime(Date createdTime) {
        addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public WsTopicBindInfoDo setUpdatedTime(Date updatedTime) {
        addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public WsTopicBindInfoDo setCreateUser(String createUser) {
        addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public WsTopicBindInfoDo setCreateUserId(String createUserId) {
        addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public WsTopicBindInfoDo setUpdateUser(String updateUser) {
        addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public WsTopicBindInfoDo setUpdateUserId(String updateUserId) {
        addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public WsTopicBindInfoDo setIsDelete(Byte isDelete) {
        addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public WsTopicBindInfoDo setVersion(Integer version) {
        addNewField("version");
        this.version = version;
        return this;
    }

	@Override
    public void clearNullNewField() {

	    if (getBindId() == null) {
            removeNewField("bindId");
        }

	    if (getTopic() == null) {
            removeNewField("topic");
        }

	    if (getTopicType() == null) {
            removeNewField("topicType");
        }

	    if (getTerminalSn() == null) {
            removeNewField("terminalSn");
        }

	    if (getPermissionNum() == null) {
            removeNewField("permissionNum");
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

    public WsTopicBindInfoDo setOnNew(WsTopicBindInfoDo newDo)
    {
        WsTopicBindInfoDo oldFieldObj = new WsTopicBindInfoDo();

        if (newDo.getBindId() != null && !newDo.getBindId().equals(getBindId())) {
            oldFieldObj.setBindId(getBindId());
            setBindId(newDo.getBindId());
        }

        if (newDo.getTopic() != null && !newDo.getTopic().equals(getTopic())) {
            oldFieldObj.setTopic(getTopic());
            setTopic(newDo.getTopic());
        }

        if (newDo.getTopicType() != null && !newDo.getTopicType().equals(getTopicType())) {
            oldFieldObj.setTopicType(getTopicType());
            setTopicType(newDo.getTopicType());
        }

        if (newDo.getTerminalSn() != null && !newDo.getTerminalSn().equals(getTerminalSn())) {
            oldFieldObj.setTerminalSn(getTerminalSn());
            setTerminalSn(newDo.getTerminalSn());
        }

        if (newDo.getPermissionNum() != null && !newDo.getPermissionNum().equals(getPermissionNum())) {
            oldFieldObj.setPermissionNum(getPermissionNum());
            setPermissionNum(newDo.getPermissionNum());
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
        return "WsTopicBindInfoDo{" +
        "bindId = " + bindId +
        ", topic = " + topic +
        ", topicType = " + topicType +
        ", terminalId = " + terminalSn +
        ", permissionNum = " + permissionNum +
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
