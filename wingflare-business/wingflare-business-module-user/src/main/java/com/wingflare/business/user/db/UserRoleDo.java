package com.wingflare.business.user.db;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wingflare.lib.mybatis.plus.base.BaseDoAbstract;
import java.util.Date;

/**
 * <p>
 * 系统用户角色表 数据对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-10
 */
@TableName("sys_user_role")
public class UserRoleDo extends BaseDoAbstract {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String userId;

    private String systemCode;

    private String roleId;

    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    @TableField(fill = FieldFill.INSERT)
    private String createUserId;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDelete;

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

    public UserRoleDo setId(String id) {
        addNewField("id");
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UserRoleDo setUserId(String userId) {
        addNewField("userId");
        this.userId = userId;
        return this;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public UserRoleDo setSystemCode(String systemCode) {
        addNewField("systemCode");
        this.systemCode = systemCode;
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public UserRoleDo setRoleId(String roleId) {
        addNewField("roleId");
        this.roleId = roleId;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public UserRoleDo setCreatedTime(Date createdTime) {
        addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public UserRoleDo setCreateUser(String createUser) {
        addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public UserRoleDo setCreateUserId(String createUserId) {
        addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public UserRoleDo setIsDelete(Integer isDelete) {
        addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

	@Override
    public void clearNullNewField() {

	    if (getId() == null) {
            removeNewField("id");
        }

	    if (getUserId() == null) {
            removeNewField("userId");
        }

	    if (getSystemCode() == null) {
            removeNewField("systemCode");
        }

	    if (getRoleId() == null) {
            removeNewField("roleId");
        }

	    if (getCreatedTime() == null) {
            removeNewField("createdTime");
        }

	    if (getCreateUser() == null) {
            removeNewField("createUser");
        }

	    if (getCreateUserId() == null) {
            removeNewField("createUserId");
        }

	    if (getIsDelete() == null) {
            removeNewField("isDelete");
        }
    }

    public UserRoleDo setOnNew(UserRoleDo newDo)
    {
        UserRoleDo oldFieldObj = new UserRoleDo();

        if (newDo.getId() != null && !newDo.getId().equals(getId())) {
            oldFieldObj.setId(getId());
            setId(newDo.getId());
        }

        if (newDo.getUserId() != null && !newDo.getUserId().equals(getUserId())) {
            oldFieldObj.setUserId(getUserId());
            setUserId(newDo.getUserId());
        }

        if (newDo.getSystemCode() != null && !newDo.getSystemCode().equals(getSystemCode())) {
            oldFieldObj.setSystemCode(getSystemCode());
            setSystemCode(newDo.getSystemCode());
        }

        if (newDo.getRoleId() != null && !newDo.getRoleId().equals(getRoleId())) {
            oldFieldObj.setRoleId(getRoleId());
            setRoleId(newDo.getRoleId());
        }

        if (!oldFieldObj.hasNewField()) {
            return null;
        }

        return oldFieldObj;
    }

	@Override
    public String toString() {
        return "UserRoleDo{" +
        "id = " + id +
        ", userId = " + userId +
        ", systemCode = " + systemCode +
        ", roleId = " + roleId +
        ", createdTime = " + createdTime +
        ", createUser = " + createUser +
        ", createUserId = " + createUserId +
        ", isDelete = " + isDelete +
        "}";
    }

}
