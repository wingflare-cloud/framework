package com.wingflare.facade.module.base.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 系统字典Dto
 * 
 * @author naizui_ycx
 * @date Sat Mar 04 17:48:17 CST 2023
 */
public class DictDto
{
	
    private String dictId;
	
	/**
     * 系统代码
     */
    private String systemCode;
	
    private Integer state;
	
	/**
     * 字典类型
     */
    private String dictType;
	
	/**
     * 字典代码
     */
    private String dictCode;
	
	/**
     * 字典名称
     */
    private String dictName;
	
	/**
     * 字典值
     */
    private String dictValue;
	
	/**
     * 字典文本
     */
    private String dictText;
	
	/**
     * 排序值
     */
    private Integer sort;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
	
    private String createUser;
	
    private String createUserId;
	
    private String updateUser;
	
    private String updateUserId;
	
    private Integer version;
	
    public DictDto setDictId(String dictId)
    {
        this.dictId = dictId;
        return this;
    }

    public String getDictId()
    {
        return dictId;
    }
	
    public DictDto setSystemCode(String systemCode)
    {
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
	
    public DictDto setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public DictDto setDictType(String dictType)
    {
        this.dictType = dictType;
        return this;
    }

    public String getDictType()
    {
        return dictType;
    }
	
    public DictDto setDictCode(String dictCode)
    {
        this.dictCode = dictCode;
        return this;
    }

    public String getDictCode()
    {
        return dictCode;
    }
	
    public DictDto setDictName(String dictName)
    {
        this.dictName = dictName;
        return this;
    }

    public String getDictName()
    {
        return dictName;
    }
	
    public DictDto setDictValue(String dictValue)
    {
        this.dictValue = dictValue;
        return this;
    }

    public String getDictValue()
    {
        return dictValue;
    }
	
    public DictDto setDictText(String dictText)
    {
        this.dictText = dictText;
        return this;
    }

    public String getDictText()
    {
        return dictText;
    }
	
    public DictDto setSort(Integer sort)
    {
        this.sort = sort;
        return this;
    }

    public Integer getSort()
    {
        return sort;
    }
	
    public DictDto setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public DictDto setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public DictDto setCreateUser(String createUser)
    {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public DictDto setCreateUserId(String createUserId)
    {
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateUserId()
    {
        return createUserId;
    }
	
    public DictDto setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public DictDto setUpdateUserId(String updateUserId)
    {
        this.updateUserId = updateUserId;
        return this;
    }

    public String getUpdateUserId()
    {
        return updateUserId;
    }
	
    public DictDto setVersion(Integer version)
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
            .append("dictId", getDictId())
            .append("systemCode", getSystemCode())
            .append("state", getState())
            .append("dictType", getDictType())
            .append("dictCode", getDictCode())
            .append("dictName", getDictName())
            .append("dictValue", getDictValue())
            .append("dictText", getDictText())
            .append("sort", getSort())
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
