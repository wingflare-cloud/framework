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
 * session信息 数据对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2024-01-06
 */
@TableName("wf_ws_session")
public class WsSessionDo extends BaseDoAbstract {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String sid;

    /**
     * 终端编号 
     */
    private String terminalSn;

    /**
     * 链接端点 
     */
    private String point;

    /**
     * session服务 
     */
    private String wsServ;

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

    public WsSessionDo setId(String id) {
        addNewField("id");
        this.id = id;
        return this;
    }

    public String getSid() {
        return sid;
    }

    public WsSessionDo setSid(String sid) {
        addNewField("sid");
        this.sid = sid;
        return this;
    }

    public String getTerminalSn() {
        return terminalSn;
    }

    public WsSessionDo setTerminalSn(String terminalSn) {
        addNewField("terminalSn");
        this.terminalSn = terminalSn;
        return this;
    }

    public String getPoint() {
        return point;
    }

    public WsSessionDo setPoint(String point) {
        addNewField("point");
        this.point = point;
        return this;
    }

    public String getWsServ() {
        return wsServ;
    }

    public WsSessionDo setWsServ(String wsServ) {
        addNewField("wsServ");
        this.wsServ = wsServ;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public WsSessionDo setCreatedTime(Date createdTime) {
        addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public WsSessionDo setUpdatedTime(Date updatedTime) {
        addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public WsSessionDo setCreateUser(String createUser) {
        addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public WsSessionDo setCreateUserId(String createUserId) {
        addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public WsSessionDo setUpdateUser(String updateUser) {
        addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public WsSessionDo setUpdateUserId(String updateUserId) {
        addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public WsSessionDo setIsDelete(Byte isDelete) {
        addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public WsSessionDo setVersion(Integer version) {
        addNewField("version");
        this.version = version;
        return this;
    }

	@Override
    public void clearNullNewField() {

	    if (getId() == null) {
            removeNewField("id");
        }

	    if (getSid() == null) {
            removeNewField("sid");
        }

	    if (getTerminalSn() == null) {
            removeNewField("terminalSn");
        }

	    if (getPoint() == null) {
            removeNewField("point");
        }

	    if (getWsServ() == null) {
            removeNewField("wsServ");
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

    public WsSessionDo setOnNew(WsSessionDo newDo)
    {
        WsSessionDo oldFieldObj = new WsSessionDo();

        if (newDo.getId() != null && !newDo.getId().equals(getId())) {
            oldFieldObj.setId(getId());
            setId(newDo.getId());
        }

        if (newDo.getSid() != null && !newDo.getSid().equals(getSid())) {
            oldFieldObj.setSid(getSid());
            setSid(newDo.getSid());
        }

        if (newDo.getTerminalSn() != null && !newDo.getTerminalSn().equals(getTerminalSn())) {
            oldFieldObj.setTerminalSn(getTerminalSn());
            setTerminalSn(newDo.getTerminalSn());
        }

        if (newDo.getPoint() != null && !newDo.getPoint().equals(getPoint())) {
            oldFieldObj.setPoint(getPoint());
            setPoint(newDo.getPoint());
        }

        if (newDo.getWsServ() != null && !newDo.getWsServ().equals(getWsServ())) {
            oldFieldObj.setWsServ(getWsServ());
            setWsServ(newDo.getWsServ());
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
        return "WsSessionDo{" +
        "id = " + id +
        ", sid = " + sid +
        ", terminalSn = " + terminalSn +
        ", point = " + point +
        ", wsServ = " + wsServ +
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
