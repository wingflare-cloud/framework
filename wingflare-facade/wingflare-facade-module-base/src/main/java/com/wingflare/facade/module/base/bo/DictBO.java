package com.wingflare.facade.module.base.bo;


import com.wingflare.api.core.enums.OnOffEnum;
import com.wingflare.facade.module.base.dict.DictTypes;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Enum;
import com.wingflare.api.core.validate.Update;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.math.BigInteger;

/**
 * 系统字典Bo
 * 
 * @author naizui_ycx
 * @date Sat Mar 04 17:48:17 CST 2023
 */
public class DictBO
{

    @Min(message = "sys.dictId.error", value = 1, groups = Update.class)
    private BigInteger dictId;

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
    
	public DictBO setDictId(BigInteger dictId)
    {
        this.dictId = dictId;
        return this;
    }

    public BigInteger getDictId()
    {
        return dictId;
    }
    
	public DictBO setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
    
	public DictBO setDictType(String dictType)
    {
        this.dictType = dictType;
        return this;
    }

    public String getDictType()
    {
        return dictType;
    }
    
	public DictBO setDictCode(String dictCode)
    {
        this.dictCode = dictCode;
        return this;
    }

    public String getDictCode()
    {
        return dictCode;
    }
    
	public DictBO setDictName(String dictName)
    {
        this.dictName = dictName;
        return this;
    }

    public String getDictName()
    {
        return dictName;
    }
    
	public DictBO setDictValue(String dictValue)
    {
        this.dictValue = dictValue;
        return this;
    }

    public String getDictValue()
    {
        return dictValue;
    }
    
	public DictBO setDictText(String dictText)
    {
        this.dictText = dictText;
        return this;
    }

    public String getDictText()
    {
        return dictText;
    }
    
	public DictBO setSort(Integer sort)
    {
        this.sort = sort;
        return this;
    }

    public Integer getSort()
    {
        return sort;
    }
    
	public DictBO setVersion(Integer version)
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
        return "DictBO{" +
                "dictId=" + dictId +
                ", state=" + state +
                ", dictType='" + dictType + '\'' +
                ", dictCode='" + dictCode + '\'' +
                ", dictName='" + dictName + '\'' +
                ", dictValue='" + dictValue + '\'' +
                ", dictText='" + dictText + '\'' +
                ", sort=" + sort +
                ", version=" + version +
                '}';
    }

}
