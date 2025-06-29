package com.wingflare.business.base.db;


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

import java.math.BigInteger;
import java.util.Date;

/**
 * 系统字典Do
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 11:45:09 CST 2023
 */
@TableName("sys_dict")
public class DictDo extends BaseDoAbstract
{

	@TableId(type = IdType.ASSIGN_ID)
    private BigInteger dictId;


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

	@TableField(fill = FieldFill.INSERT)
    private Date createdTime;

	@TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

	@TableField(fill = FieldFill.INSERT)
    private String createUser;

	@TableField(fill = FieldFill.INSERT)
    private BigInteger createUserId;

	@TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

	@TableField(fill = FieldFill.INSERT_UPDATE)
    private BigInteger updateUserId;

	@TableLogic
	@TableField(fill = FieldFill.INSERT)
    private Integer isDelete;

	@Version
	@TableField(fill = FieldFill.INSERT)
    private Integer version;

	@Override
	public void setPk(BigInteger dictId)
	{
		setDictId(dictId);
	}

	@Override
	public BigInteger getPk()
	{
		return getDictId();
	}

	
    public DictDo setDictId(BigInteger dictId)
    {
		addNewField("dictId");
        this.dictId = dictId;
        return this;
    }

    public BigInteger getDictId()
    {
        return dictId;
    }
	
    public DictDo setState(Integer state)
    {
		addNewField("state");
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public DictDo setDictType(String dictType)
    {
		addNewField("dictType");
        this.dictType = dictType;
        return this;
    }

    public String getDictType()
    {
        return dictType;
    }
	
    public DictDo setDictCode(String dictCode)
    {
		addNewField("dictCode");
        this.dictCode = dictCode;
        return this;
    }

    public String getDictCode()
    {
        return dictCode;
    }
	
    public DictDo setDictName(String dictName)
    {
		addNewField("dictName");
        this.dictName = dictName;
        return this;
    }

    public String getDictName()
    {
        return dictName;
    }
	
    public DictDo setDictValue(String dictValue)
    {
		addNewField("dictValue");
        this.dictValue = dictValue;
        return this;
    }

    public String getDictValue()
    {
        return dictValue;
    }
	
    public DictDo setDictText(String dictText)
    {
		addNewField("dictText");
        this.dictText = dictText;
        return this;
    }

    public String getDictText()
    {
        return dictText;
    }
	
    public DictDo setSort(Integer sort)
    {
		addNewField("sort");
        this.sort = sort;
        return this;
    }

    public Integer getSort()
    {
        return sort;
    }
	
    public DictDo setCreatedTime(Date createdTime)
    {
		addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public DictDo setUpdatedTime(Date updatedTime)
    {
		addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public DictDo setCreateUser(String createUser)
    {
		addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public DictDo setCreateUserId(BigInteger createUserId)
    {
		addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId()
    {
        return createUserId;
    }
	
    public DictDo setUpdateUser(String updateUser)
    {
		addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public DictDo setUpdateUserId(BigInteger updateUserId)
    {
		addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public BigInteger getUpdateUserId()
    {
        return updateUserId;
    }
	
    public DictDo setIsDelete(Integer isDelete)
    {
		addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }
	
    public DictDo setVersion(Integer version)
    {
		addNewField("version");
        this.version = version;
        return this;
    }

    public Integer getVersion()
    {
        return version;
    }

	@Override
	public void clearNullNewField()
	{

		if (getDictId() == null) {
			removeNewField("dictId");
		}

		if (getState() == null) {
			removeNewField("state");
		}

		if (getDictType() == null) {
			removeNewField("dictType");
		}

		if (getDictCode() == null) {
			removeNewField("dictCode");
		}

		if (getDictName() == null) {
			removeNewField("dictName");
		}

		if (getDictValue() == null) {
			removeNewField("dictValue");
		}

		if (getDictText() == null) {
			removeNewField("dictText");
		}

		if (getSort() == null) {
			removeNewField("sort");
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

	public DictDo setOnNew(DictDo newDo)
	{
	    DictDo oldFieldObj = new DictDo();

		if (newDo.getState() != null && !newDo.getState().equals(getState())) {
			oldFieldObj.setState(getState());
			setState(newDo.getState());
		}

		if (newDo.getDictType() != null && !newDo.getDictType().equals(getDictType())) {
			oldFieldObj.setDictType(getDictType());
			setDictType(newDo.getDictType());
		}

		if (newDo.getDictCode() != null && !newDo.getDictCode().equals(getDictCode())) {
			oldFieldObj.setDictCode(getDictCode());
			setDictCode(newDo.getDictCode());
		}

		if (newDo.getDictName() != null && !newDo.getDictName().equals(getDictName())) {
			oldFieldObj.setDictName(getDictName());
			setDictName(newDo.getDictName());
		}

		if (newDo.getDictValue() != null && !newDo.getDictValue().equals(getDictValue())) {
			oldFieldObj.setDictValue(getDictValue());
			setDictValue(newDo.getDictValue());
		}

		if (newDo.getDictText() != null && !newDo.getDictText().equals(getDictText())) {
			oldFieldObj.setDictText(getDictText());
			setDictText(newDo.getDictText());
		}

		if (newDo.getSort() != null && !newDo.getSort().equals(getSort())) {
			oldFieldObj.setSort(getSort());
			setSort(newDo.getSort());
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dictId", getDictId())
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
            .append("isDelete", getIsDelete())
            .append("version", getVersion())
            .toString();
    }
	
}
