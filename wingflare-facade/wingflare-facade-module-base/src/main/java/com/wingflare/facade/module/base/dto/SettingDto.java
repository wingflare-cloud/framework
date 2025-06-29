package com.wingflare.facade.module.base.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigInteger;
import java.util.Date;

/**
 * 系统设置Dto
 * 
 * @author naizui_ycx
 * @date Fri Mar 03 09:48:21 CST 2023
 */
public class SettingDto
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
	
    public SettingDto setSettingId(BigInteger settingId)
    {
        this.settingId = settingId;
        return this;
    }

    public BigInteger getSettingId()
    {
        return settingId;
    }
	
    public SettingDto setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public SettingDto setSystemCode(String systemCode)
    {
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
	
    public SettingDto setSettingCode(String settingCode)
    {
        this.settingCode = settingCode;
        return this;
    }

    public String getSettingCode()
    {
        return settingCode;
    }
	
    public SettingDto setSettingName(String settingName)
    {
        this.settingName = settingName;
        return this;
    }

    public String getSettingName()
    {
        return settingName;
    }
	
    public SettingDto setSettingValue(String settingValue)
    {
        this.settingValue = settingValue;
        return this;
    }

    public String getSettingValue()
    {
        return settingValue;
    }
	
    public SettingDto setSettingText(String settingText)
    {
        this.settingText = settingText;
        return this;
    }

    public String getSettingText()
    {
        return settingText;
    }
	
    public SettingDto setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public SettingDto setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public SettingDto setCreateUser(String createUser)
    {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public SettingDto setCreateUserId(BigInteger createUserId)
    {
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId()
    {
        return createUserId;
    }
	
    public SettingDto setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public SettingDto setUpdateUserId(BigInteger updateUserId)
    {
        this.updateUserId = updateUserId;
        return this;
    }

    public BigInteger getUpdateUserId()
    {
        return updateUserId;
    }
	
    public SettingDto setVersion(Integer version)
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("settingId", getSettingId())
            .append("state", getState())
            .append("systemCode", getSystemCode())
            .append("settingCode", getSettingCode())
            .append("settingName", getSettingName())
            .append("settingValue", getSettingValue())
            .append("settingText", getSettingText())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .append("createUser", getCreateUser())
            .append("createUserId", getCreateUserId())
            .append("updateUser", getUpdateUser())
            .append("updateUserId", getUpdateUserId())
            .append("version", getVersion())
            .toString();
    }
	
}
