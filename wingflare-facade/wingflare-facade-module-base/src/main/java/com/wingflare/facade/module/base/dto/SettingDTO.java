package com.wingflare.facade.module.base.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.util.Date;

/**
 * 系统设置Dto
 * 
 * @author naizui_ycx
 * @date Fri Mar 03 09:48:21 CST 2023
 */
public class SettingDTO
{
	
    private BigInteger settingId;
	
	/**
     * 设置状态
     */
    private Integer state;
	
	/**
     * 系统代码
     */
    private String systemCode;
	
	/**
     * 设置代码
     */
    private String settingCode;
	
	/**
     * 设置名称
     */
    private String settingName;
	
	/**
     * 设置值
     */
    private String settingValue;
	
	/**
     * 设置文本描述
     */
    private String settingText;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
	
    private String createUser;
	
    private BigInteger createUserId;
	
    private String updateUser;
	
    private BigInteger updateUserId;
	
    private Integer version;
	
    public SettingDTO setSettingId(BigInteger settingId)
    {
        this.settingId = settingId;
        return this;
    }

    public BigInteger getSettingId()
    {
        return settingId;
    }
	
    public SettingDTO setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public SettingDTO setSystemCode(String systemCode)
    {
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
	
    public SettingDTO setSettingCode(String settingCode)
    {
        this.settingCode = settingCode;
        return this;
    }

    public String getSettingCode()
    {
        return settingCode;
    }
	
    public SettingDTO setSettingName(String settingName)
    {
        this.settingName = settingName;
        return this;
    }

    public String getSettingName()
    {
        return settingName;
    }
	
    public SettingDTO setSettingValue(String settingValue)
    {
        this.settingValue = settingValue;
        return this;
    }

    public String getSettingValue()
    {
        return settingValue;
    }
	
    public SettingDTO setSettingText(String settingText)
    {
        this.settingText = settingText;
        return this;
    }

    public String getSettingText()
    {
        return settingText;
    }
	
    public SettingDTO setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public SettingDTO setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public SettingDTO setCreateUser(String createUser)
    {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public SettingDTO setCreateUserId(BigInteger createUserId)
    {
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId()
    {
        return createUserId;
    }
	
    public SettingDTO setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public SettingDTO setUpdateUserId(BigInteger updateUserId)
    {
        this.updateUserId = updateUserId;
        return this;
    }

    public BigInteger getUpdateUserId()
    {
        return updateUserId;
    }
	
    public SettingDTO setVersion(Integer version)
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
        return "SettingDTO{" +
                "settingId=" + settingId +
                ", state=" + state +
                ", systemCode='" + systemCode + '\'' +
                ", settingCode='" + settingCode + '\'' +
                ", settingName='" + settingName + '\'' +
                ", settingValue='" + settingValue + '\'' +
                ", settingText='" + settingText + '\'' +
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
