package com.wingflare.facade.module.base.bo;


import com.wingflare.facade.module.base.dict.DictTypes;
import com.wingflare.lib.core.Regexp;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Enum;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.enums.OnOffEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 系统字典Bo
 * 
 * @author naizui_ycx
 * @date Sat Mar 04 17:48:17 CST 2023
 */
public class DictBo
{

    @NotBlank(message = "sys.dictId.notBlank", groups = Update.class)
    @Pattern(regexp = Regexp.SNOWFLAKE_ID, message = "sys.dictId.formatError", groups = Update.class)
    private String dictId;

	/**
     * 系统代码
     */
    @NotBlank(message = "sys.systemCode.notBlank", groups = Create.class)
    @Pattern(regexp = "^[a-zA-Z]{2,32}$", message = "sys.systemCode.formatError", groups = Create.class)
    private String systemCode;

    /**
     * 启禁用状态
     */
    @Enum(clazz = OnOffEnum.class, method = "getValue", message = "sys.dict.stateFormatError",
            groups = {Create.class, Update.class})
    private Integer state;

	/**
     * 字典类型
     */
    @NotBlank(message = "sys.dict.typeNotBlank", groups = Create.class)
    @Pattern(regexp = "^\\w{2,32}$", message = "sys.dict.typeFormatError", groups = Create.class)
    @Enum(clazz = DictTypes.class, method = "getValue", message = "sys.dict.typeError")
    private String dictType;

	/**
     * 字典代码
     */
    @NotBlank(message = "sys.dict.codeNotBlank", groups = Create.class)
    @Pattern(regexp = "^\\w{1,128}$", message = "sys.dict.codeFormatError", groups = Create.class)
    private String dictCode;

	/**
     * 字典名称
     */
    @Length(max = 64, message = "sys.dict.textLengthError")
    @NotBlank(message = "sys.dict.nameNotBlank", groups = {Create.class})
    private String dictName;

	/**
     * 字典值
     */
    @Length(max = 256, message = "sys.dict.textLengthError")
    private String dictValue;

	/**
     * 字典文本
     */
    @Length(max = 256, message = "sys.dict.textLengthError")
    private String dictText;

	/**
     * 排序值
     */
	@Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "sys.dict.sortFormatError",
            groups = {Create.class, Update.class})
    private Integer sort;

    private Integer version;
    
	public DictBo setDictId(String dictId)
    {
        this.dictId = dictId;
        return this;
    }

    public String getDictId()
    {
        return dictId;
    }
    
	public DictBo setSystemCode(String systemCode)
    {
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
    
	public DictBo setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
    
	public DictBo setDictType(String dictType)
    {
        this.dictType = dictType;
        return this;
    }

    public String getDictType()
    {
        return dictType;
    }
    
	public DictBo setDictCode(String dictCode)
    {
        this.dictCode = dictCode;
        return this;
    }

    public String getDictCode()
    {
        return dictCode;
    }
    
	public DictBo setDictName(String dictName)
    {
        this.dictName = dictName;
        return this;
    }

    public String getDictName()
    {
        return dictName;
    }
    
	public DictBo setDictValue(String dictValue)
    {
        this.dictValue = dictValue;
        return this;
    }

    public String getDictValue()
    {
        return dictValue;
    }
    
	public DictBo setDictText(String dictText)
    {
        this.dictText = dictText;
        return this;
    }

    public String getDictText()
    {
        return dictText;
    }
    
	public DictBo setSort(Integer sort)
    {
        this.sort = sort;
        return this;
    }

    public Integer getSort()
    {
        return sort;
    }
    
	public DictBo setVersion(Integer version)
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
            .append("version", getVersion())
            .toString();
    }
	
}
