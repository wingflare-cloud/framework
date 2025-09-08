package com.wingflare.facade.module.user.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.util.Date;

/**
 * 系统角色Dto
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:04:01 CST 2023
 */
public class RoleDTO
{
	
    private BigInteger roleId;
	
	/**
     * 角色状态
     */
    private Integer state;
	
	/**
     * 角色名称
     */
    private String roleName;
	
	/**
     * 角色备注
     */
    private String roleRemark;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
	
    private String createUser;
	
    private BigInteger createUserId;
	
    private String updateUser;
	
    private BigInteger updateUserId;
	
    private Integer version;
	
    public RoleDTO setRoleId(BigInteger roleId)
    {
        this.roleId = roleId;
        return this;
    }

    public BigInteger getRoleId()
    {
        return roleId;
    }
	
    public RoleDTO setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public RoleDTO setRoleName(String roleName)
    {
        this.roleName = roleName;
        return this;
    }

    public String getRoleName()
    {
        return roleName;
    }
	
    public RoleDTO setRoleRemark(String roleRemark)
    {
        this.roleRemark = roleRemark;
        return this;
    }

    public String getRoleRemark()
    {
        return roleRemark;
    }
	
    public RoleDTO setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public RoleDTO setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public RoleDTO setCreateUser(String createUser)
    {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public RoleDTO setCreateUserId(BigInteger createUserId)
    {
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId()
    {
        return createUserId;
    }
	
    public RoleDTO setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public RoleDTO setUpdateUserId(BigInteger updateUserId)
    {
        this.updateUserId = updateUserId;
        return this;
    }

    public BigInteger getUpdateUserId()
    {
        return updateUserId;
    }
	
    public RoleDTO setVersion(Integer version)
    {
        this.version = version;
        return this;
    }

    public Integer getVersion()
    {
        return version;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "roleId=" + roleId +
                ", state=" + state +
                ", roleName='" + roleName + '\'' +
                ", roleRemark='" + roleRemark + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", createUser='" + createUser + '\'' +
                ", createUserId=" + createUserId +
                ", updateUser='" + updateUser + '\'' +
                ", updateUserId=" + updateUserId +
                ", version=" + version +
                '}';
    }

}
