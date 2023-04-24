package com.wingflare.facade.module.base.bo;


import com.wingflare.lib.core.Regexp;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Enum;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.enums.OnOffEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 系统设置Bo
 * 
 * @author naizui_ycx
 * @date Fri Mar 03 09:48:21 CST 2023
 */
public class SettingBo
{

    @NotBlank(message = "sys.settingId.notBlank", groups = Update.class)
    @Pattern(regexp = Regexp.SNOWFLAKE_ID, message = "sys.settingId.formatError", groups = Update.class)
    private String settingId;

	/**
     * 设置状态
     */
    @Enum(clazz = OnOffEnum.class, method = "getValue", message = "sys.setting.stateFormatError",
            groups = {Create.class, Update.class})
    private Integer state;

	/**
     * 系统代码
     */
    @NotBlank(message = "sys.systemCode.notBlank", groups = Create.class)
    @Pattern(regexp = "^[a-zA-Z]{2,32}$", message = "sys.systemCode.formatError", groups = Create.class)
    private String systemCode;

	/**
     * 设置代码
     */
    @NotBlank(message = "sys.setting.codeNotBlank", groups = Create.class)
    @Pattern(regexp = "^\\w{1,128}$", message = "sys.setting.codeFormatError", groups = Create.class)
    private String settingCode;

	/**
     * 设置名称
     */
    @Length(min = 1, max = 64, message = "sys.setting.nameLengthError")
    @NotBlank(message = "sys.setting.nameNotBlank", groups = {Create.class})
    private String settingName;

	/**
     * 设置值
     */
    private String settingValue;

	/**
     * 设置文本描述
     */
    @Length(max = 256, message = "sys.setting.textLengthError")
    private String settingText;

    private Integer version;
    
	public SettingBo setSettingId(String settingId)
    {
        this.settingId = settingId;
        return this;
    }

    public String getSettingId()
    {
        return settingId;
    }
    
	public SettingBo setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
    
	public SettingBo setSystemCode(String systemCode)
    {
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
    
	public SettingBo setSettingCode(String settingCode)
    {
        this.settingCode = settingCode;
        return this;
    }

    public String getSettingCode()
    {
        return settingCode;
    }
    
	public SettingBo setSettingName(String settingName)
    {
        this.settingName = settingName;
        return this;
    }

    public String getSettingName()
    {
        return settingName;
    }
    
	public SettingBo setSettingValue(String settingValue)
    {
        this.settingValue = settingValue;
        return this;
    }

    public String getSettingValue()
    {
        return settingValue;
    }
    
	public SettingBo setSettingText(String settingText)
    {
        this.settingText = settingText;
        return this;
    }

    public String getSettingText()
    {
        return settingText;
    }
    
	public SettingBo setVersion(Integer version)
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
            .append("version", getVersion())
            .toString();
    }
	
}
