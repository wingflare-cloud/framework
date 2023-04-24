package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.core.validation.Update;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 职级Bo
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:42:34 CST 2023
 */
public class JobLevelBo
{

    @NotBlank(message = "org.jobLevelId.notBlank", groups = Update.class)
    @Pattern(regexp = "^$|^[0-9a-zA-Z]{1,32}$", message = "org.jobLevelId.formatError", groups = Update.class)
    private String jobLevelId;

	/**
     * 职级名称
     */
    private String levelName;

	/**
     * 职级数值
     */
    private Long level;

    private Integer version;
    
	public JobLevelBo setJobLevelId(String jobLevelId)
    {
        this.jobLevelId = jobLevelId;
        return this;
    }

    public String getJobLevelId()
    {
        return jobLevelId;
    }
    
	public JobLevelBo setLevelName(String levelName)
    {
        this.levelName = levelName;
        return this;
    }

    public String getLevelName()
    {
        return levelName;
    }
    
	public JobLevelBo setLevel(Long level)
    {
        this.level = level;
        return this;
    }

    public Long getLevel()
    {
        return level;
    }
    
	public JobLevelBo setVersion(Integer version)
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
            .append("jobLevelId", getJobLevelId())
            .append("levelName", getLevelName())
            .append("level", getLevel())
            .append("version", getVersion())
            .toString();
    }
	
}
