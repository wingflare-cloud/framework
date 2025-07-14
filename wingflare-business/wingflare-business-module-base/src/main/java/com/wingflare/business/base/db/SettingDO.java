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
 * 系统设置Do
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 18:56:50 CST 2023
 */
@TableName("sys_setting")
public class SettingDO extends BaseDoAbstract
{

	@TableId(type = IdType.ASSIGN_ID)
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
	public void setPk(BigInteger settingId)
	{
		setSettingId(settingId);
	}

	@Override
	public BigInteger getPk()
	{
		return getSettingId();
	}

	
    public SettingDO setSettingId(BigInteger settingId)
    {
		addNewField("settingId");
        this.settingId = settingId;
        return this;
    }

    public BigInteger getSettingId()
    {
        return settingId;
    }
	
    public SettingDO setState(Integer state)
    {
		addNewField("state");
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public SettingDO setSystemCode(String systemCode)
    {
		addNewField("systemCode");
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
	
    public SettingDO setSettingCode(String settingCode)
    {
		addNewField("settingCode");
        this.settingCode = settingCode;
        return this;
    }

    public String getSettingCode()
    {
        return settingCode;
    }
	
    public SettingDO setSettingName(String settingName)
    {
		addNewField("settingName");
        this.settingName = settingName;
        return this;
    }

    public String getSettingName()
    {
        return settingName;
    }
	
    public SettingDO setSettingValue(String settingValue)
    {
		addNewField("settingValue");
        this.settingValue = settingValue;
        return this;
    }

    public String getSettingValue()
    {
        return settingValue;
    }
	
    public SettingDO setSettingText(String settingText)
    {
		addNewField("settingText");
        this.settingText = settingText;
        return this;
    }

    public String getSettingText()
    {
        return settingText;
    }
	
    public SettingDO setCreatedTime(Date createdTime)
    {
		addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public SettingDO setUpdatedTime(Date updatedTime)
    {
		addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public SettingDO setCreateUser(String createUser)
    {
		addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public SettingDO setCreateUserId(BigInteger createUserId)
    {
		addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId()
    {
        return createUserId;
    }
	
    public SettingDO setUpdateUser(String updateUser)
    {
		addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public SettingDO setUpdateUserId(BigInteger updateUserId)
    {
		addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public BigInteger getUpdateUserId()
    {
        return updateUserId;
    }
	
    public SettingDO setIsDelete(Integer isDelete)
    {
		addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }
	
    public SettingDO setVersion(Integer version)
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

		if (getSettingId() == null) {
			removeNewField("settingId");
		}

		if (getState() == null) {
			removeNewField("state");
		}

		if (getSystemCode() == null) {
			removeNewField("systemCode");
		}

		if (getSettingCode() == null) {
			removeNewField("settingCode");
		}

		if (getSettingName() == null) {
			removeNewField("settingName");
		}

		if (getSettingValue() == null) {
			removeNewField("settingValue");
		}

		if (getSettingText() == null) {
			removeNewField("settingText");
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

	public SettingDO setOnNew(SettingDO newDo)
	{
	    SettingDO oldFieldObj = new SettingDO();

		if (newDo.getState() != null && !newDo.getState().equals(getState())) {
			oldFieldObj.setState(getState());
			setState(newDo.getState());
		}

		if (newDo.getSystemCode() != null && !newDo.getSystemCode().equals(getSystemCode())) {
			oldFieldObj.setSystemCode(getSystemCode());
			setSystemCode(newDo.getSystemCode());
		}

		if (newDo.getSettingCode() != null && !newDo.getSettingCode().equals(getSettingCode())) {
			oldFieldObj.setSettingCode(getSettingCode());
			setSettingCode(newDo.getSettingCode());
		}

		if (newDo.getSettingName() != null && !newDo.getSettingName().equals(getSettingName())) {
			oldFieldObj.setSettingName(getSettingName());
			setSettingName(newDo.getSettingName());
		}

		if (newDo.getSettingValue() != null && !newDo.getSettingValue().equals(getSettingValue())) {
			oldFieldObj.setSettingValue(getSettingValue());
			setSettingValue(newDo.getSettingValue());
		}

		if (newDo.getSettingText() != null && !newDo.getSettingText().equals(getSettingText())) {
			oldFieldObj.setSettingText(getSettingText());
			setSettingText(newDo.getSettingText());
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
            .append("isDelete", getIsDelete())
            .append("version", getVersion())
            .toString();
    }
	
}
