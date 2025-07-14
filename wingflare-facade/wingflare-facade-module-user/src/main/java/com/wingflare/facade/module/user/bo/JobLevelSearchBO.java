package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.math.BigInteger;
import java.util.Date;

/**
 * <p>
 * 职级表 查询对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
public class JobLevelSearchBO extends BaseSearchBo
{
        
    private BigInteger eq_jobLevelId;

    private BigInteger neq_jobLevelId;

    private String like_jobLevelId;

    private String liker_jobLevelId;

    private String likel_jobLevelId;

    private String notlike_jobLevelId;

    private String notliker_jobLevelId;

    private String notlikel_jobLevelId;

    private String in_jobLevelId;

    private String notin_jobLevelId;
        
    private BigInteger eq_levelClassifyId;

    private BigInteger neq_levelClassifyId;

    private String like_levelClassifyId;

    private String liker_levelClassifyId;

    private String likel_levelClassifyId;

    private String notlike_levelClassifyId;

    private String notliker_levelClassifyId;

    private String notlikel_levelClassifyId;

    private String in_levelClassifyId;

    private String notin_levelClassifyId;
        
    private String eq_levelName;

    private String neq_levelName;

    private String like_levelName;

    private String liker_levelName;

    private String likel_levelName;

    private String notlike_levelName;

    private String notliker_levelName;

    private String notlikel_levelName;

    private String in_levelName;

    private String notin_levelName;
        
    private Integer eq_rootLevel;

    private Integer neq_rootLevel;

    private Integer gt_rootLevel;

    private Integer lt_rootLevel;

    private Integer egt_rootLevel;

    private Integer elt_rootLevel;

    private String between_rootLevel;

    private String notbetween_rootLevel;

    private String in_rootLevel;

    private String notin_rootLevel;
        
    private Integer eq_classifyLevel;

    private Integer neq_classifyLevel;

    private Integer gt_classifyLevel;

    private Integer lt_classifyLevel;

    private Integer egt_classifyLevel;

    private Integer elt_classifyLevel;

    private String between_classifyLevel;

    private String notbetween_classifyLevel;

    private String in_classifyLevel;

    private String notin_classifyLevel;
        
    private Date eq_createdTime;

    private Date neq_createdTime;

    private Date gt_createdTime;

    private Date lt_createdTime;

    private Date egt_createdTime;

    private Date elt_createdTime;

    private String between_createdTime;

    private String notbetween_createdTime;

    private String in_createdTime;

    private String notin_createdTime;
        
    private Date eq_updatedTime;

    private Date neq_updatedTime;

    private Date gt_updatedTime;

    private Date lt_updatedTime;

    private Date egt_updatedTime;

    private Date elt_updatedTime;

    private String between_updatedTime;

    private String notbetween_updatedTime;

    private String in_updatedTime;

    private String notin_updatedTime;
        
    private String eq_createUser;

    private String neq_createUser;

    private String like_createUser;

    private String liker_createUser;

    private String likel_createUser;

    private String notlike_createUser;

    private String notliker_createUser;

    private String notlikel_createUser;

    private String in_createUser;

    private String notin_createUser;
        
    private BigInteger eq_createUserId;

    private BigInteger neq_createUserId;

    private String like_createUserId;

    private String liker_createUserId;

    private String likel_createUserId;

    private String notlike_createUserId;

    private String notliker_createUserId;

    private String notlikel_createUserId;

    private String in_createUserId;

    private String notin_createUserId;
        
    private String eq_updateUser;

    private String neq_updateUser;

    private String like_updateUser;

    private String liker_updateUser;

    private String likel_updateUser;

    private String notlike_updateUser;

    private String notliker_updateUser;

    private String notlikel_updateUser;

    private String in_updateUser;

    private String notin_updateUser;
        
    private BigInteger eq_updateUserId;

    private BigInteger neq_updateUserId;

    private String like_updateUserId;

    private String liker_updateUserId;

    private String likel_updateUserId;

    private String notlike_updateUserId;

    private String notliker_updateUserId;

    private String notlikel_updateUserId;

    private String in_updateUserId;

    private String notin_updateUserId;

    public BigInteger getEq_jobLevelId()
    {
        return eq_jobLevelId;
    }

    public JobLevelSearchBO setEq_jobLevelId(BigInteger eq_jobLevelId)
    {
        this.eq_jobLevelId = eq_jobLevelId;
        return this;
    }

    public BigInteger getNeq_jobLevelId()
    {
        return neq_jobLevelId;
    }

    public JobLevelSearchBO setNeq_jobLevelId(BigInteger neq_jobLevelId)
    {
        this.neq_jobLevelId = neq_jobLevelId;
        return this;
    }

    public String getLike_jobLevelId()
    {
        return like_jobLevelId;
    }

    public JobLevelSearchBO setLike_jobLevelId(String like_jobLevelId)
    {
        this.like_jobLevelId = like_jobLevelId;
        return this;
    }

    public String getLiker_jobLevelId()
    {
        return liker_jobLevelId;
     }

    public JobLevelSearchBO setLiker_jobLevelId(String liker_jobLevelId)
    {
        this.liker_jobLevelId = liker_jobLevelId;
        return this;
    }

    public String getLikel_jobLevelId()
    {
        return likel_jobLevelId;
    }

    public JobLevelSearchBO setLikel_jobLevelId(String likel_jobLevelId)
    {
        this.likel_jobLevelId = likel_jobLevelId;
        return this;
    }

    public String getNotlike_jobLevelId()
    {
        return notlike_jobLevelId;
    }

    public JobLevelSearchBO setNotlike_jobLevelId(String notlike_jobLevelId)
    {
        this.notlike_jobLevelId = notlike_jobLevelId;
        return this;
    }

    public String getNotliker_jobLevelId()
    {
        return notliker_jobLevelId;
    }

    public JobLevelSearchBO setNotliker_jobLevelId(String notliker_jobLevelId)
    {
        this.notliker_jobLevelId = notliker_jobLevelId;
        return this;
    }

    public String getNotlikel_jobLevelId()
    {
        return notlikel_jobLevelId;
    }

    public JobLevelSearchBO setNotlikel_jobLevelId(String notlikel_jobLevelId)
    {
        this.notlikel_jobLevelId = notlikel_jobLevelId;
        return this;
    }

    public String getIn_jobLevelId()
    {
        return in_jobLevelId;
    }

    public JobLevelSearchBO setIn_jobLevelId(String in_jobLevelId)
    {
        this.in_jobLevelId = in_jobLevelId;
        return this;
    }

    public String getNotin_jobLevelId()
    {
        return notin_jobLevelId;
    }

    public JobLevelSearchBO setNotin_jobLevelId(String notin_jobLevelId)
    {
        this.notin_jobLevelId = notin_jobLevelId;
        return this;
    }

    public BigInteger getEq_levelClassifyId()
    {
        return eq_levelClassifyId;
    }

    public JobLevelSearchBO setEq_levelClassifyId(BigInteger eq_levelClassifyId)
    {
        this.eq_levelClassifyId = eq_levelClassifyId;
        return this;
    }

    public BigInteger getNeq_levelClassifyId()
    {
        return neq_levelClassifyId;
    }

    public JobLevelSearchBO setNeq_levelClassifyId(BigInteger neq_levelClassifyId)
    {
        this.neq_levelClassifyId = neq_levelClassifyId;
        return this;
    }

    public String getLike_levelClassifyId()
    {
        return like_levelClassifyId;
    }

    public JobLevelSearchBO setLike_levelClassifyId(String like_levelClassifyId)
    {
        this.like_levelClassifyId = like_levelClassifyId;
        return this;
    }

    public String getLiker_levelClassifyId()
    {
        return liker_levelClassifyId;
     }

    public JobLevelSearchBO setLiker_levelClassifyId(String liker_levelClassifyId)
    {
        this.liker_levelClassifyId = liker_levelClassifyId;
        return this;
    }

    public String getLikel_levelClassifyId()
    {
        return likel_levelClassifyId;
    }

    public JobLevelSearchBO setLikel_levelClassifyId(String likel_levelClassifyId)
    {
        this.likel_levelClassifyId = likel_levelClassifyId;
        return this;
    }

    public String getNotlike_levelClassifyId()
    {
        return notlike_levelClassifyId;
    }

    public JobLevelSearchBO setNotlike_levelClassifyId(String notlike_levelClassifyId)
    {
        this.notlike_levelClassifyId = notlike_levelClassifyId;
        return this;
    }

    public String getNotliker_levelClassifyId()
    {
        return notliker_levelClassifyId;
    }

    public JobLevelSearchBO setNotliker_levelClassifyId(String notliker_levelClassifyId)
    {
        this.notliker_levelClassifyId = notliker_levelClassifyId;
        return this;
    }

    public String getNotlikel_levelClassifyId()
    {
        return notlikel_levelClassifyId;
    }

    public JobLevelSearchBO setNotlikel_levelClassifyId(String notlikel_levelClassifyId)
    {
        this.notlikel_levelClassifyId = notlikel_levelClassifyId;
        return this;
    }

    public String getIn_levelClassifyId()
    {
        return in_levelClassifyId;
    }

    public JobLevelSearchBO setIn_levelClassifyId(String in_levelClassifyId)
    {
        this.in_levelClassifyId = in_levelClassifyId;
        return this;
    }

    public String getNotin_levelClassifyId()
    {
        return notin_levelClassifyId;
    }

    public JobLevelSearchBO setNotin_levelClassifyId(String notin_levelClassifyId)
    {
        this.notin_levelClassifyId = notin_levelClassifyId;
        return this;
    }

    public String getEq_levelName()
    {
        return eq_levelName;
    }

    public JobLevelSearchBO setEq_levelName(String eq_levelName)
    {
        this.eq_levelName = eq_levelName;
        return this;
    }

    public String getNeq_levelName()
    {
        return neq_levelName;
    }

    public JobLevelSearchBO setNeq_levelName(String neq_levelName)
    {
        this.neq_levelName = neq_levelName;
        return this;
    }

    public String getLike_levelName()
    {
        return like_levelName;
    }

    public JobLevelSearchBO setLike_levelName(String like_levelName)
    {
        this.like_levelName = like_levelName;
        return this;
    }

    public String getLiker_levelName()
    {
        return liker_levelName;
     }

    public JobLevelSearchBO setLiker_levelName(String liker_levelName)
    {
        this.liker_levelName = liker_levelName;
        return this;
    }

    public String getLikel_levelName()
    {
        return likel_levelName;
    }

    public JobLevelSearchBO setLikel_levelName(String likel_levelName)
    {
        this.likel_levelName = likel_levelName;
        return this;
    }

    public String getNotlike_levelName()
    {
        return notlike_levelName;
    }

    public JobLevelSearchBO setNotlike_levelName(String notlike_levelName)
    {
        this.notlike_levelName = notlike_levelName;
        return this;
    }

    public String getNotliker_levelName()
    {
        return notliker_levelName;
    }

    public JobLevelSearchBO setNotliker_levelName(String notliker_levelName)
    {
        this.notliker_levelName = notliker_levelName;
        return this;
    }

    public String getNotlikel_levelName()
    {
        return notlikel_levelName;
    }

    public JobLevelSearchBO setNotlikel_levelName(String notlikel_levelName)
    {
        this.notlikel_levelName = notlikel_levelName;
        return this;
    }

    public String getIn_levelName()
    {
        return in_levelName;
    }

    public JobLevelSearchBO setIn_levelName(String in_levelName)
    {
        this.in_levelName = in_levelName;
        return this;
    }

    public String getNotin_levelName()
    {
        return notin_levelName;
    }

    public JobLevelSearchBO setNotin_levelName(String notin_levelName)
    {
        this.notin_levelName = notin_levelName;
        return this;
    }

    public Integer getEq_rootLevel()
    {
        return eq_rootLevel;
    }

    public JobLevelSearchBO setEq_rootLevel(Integer eq_rootLevel)
    {
        this.eq_rootLevel = eq_rootLevel;
        return this;
    }

    public Integer getNeq_rootLevel()
    {
        return neq_rootLevel;
    }

    public JobLevelSearchBO setNeq_rootLevel(Integer neq_rootLevel)
    {
        this.neq_rootLevel = neq_rootLevel;
        return this;
    }

    public Integer getGt_rootLevel()
    {
         return gt_rootLevel;
    }

    public JobLevelSearchBO setGt_rootLevel(Integer gt_rootLevel)
    {
        this.gt_rootLevel = gt_rootLevel;
        return this;
    }

    public Integer getLt_rootLevel()
    {
        return lt_rootLevel;
    }

    public JobLevelSearchBO setLt_rootLevel(Integer lt_rootLevel)
    {
        this.lt_rootLevel = lt_rootLevel;
        return this;
    }

    public Integer getEgt_rootLevel()
    {
        return egt_rootLevel;
    }

    public JobLevelSearchBO setEgt_rootLevel(Integer egt_rootLevel)
    {
        this.egt_rootLevel = egt_rootLevel;
        return this;
    }

    public Integer getElt_rootLevel()
    {
        return elt_rootLevel;
    }

    public JobLevelSearchBO setElt_rootLevel(Integer elt_rootLevel)
    {
        this.elt_rootLevel = elt_rootLevel;
        return this;
    }

    public String getBetween_rootLevel()
    {
        return between_rootLevel;
    }

    public JobLevelSearchBO setBetween_rootLevel(String between_rootLevel)
    {
        this.between_rootLevel = between_rootLevel;
        return this;
    }

    public String getNotbetween_rootLevel()
    {
        return notbetween_rootLevel;
    }

    public JobLevelSearchBO setNotbetween_rootLevel(String notbetween_rootLevel)
    {
        this.notbetween_rootLevel = notbetween_rootLevel;
        return this;
    }

    public String getIn_rootLevel()
    {
        return in_rootLevel;
    }

    public JobLevelSearchBO setIn_rootLevel(String in_rootLevel)
    {
        this.in_rootLevel = in_rootLevel;
        return this;
    }

    public String getNotin_rootLevel()
    {
        return notin_rootLevel;
    }

    public JobLevelSearchBO setNotin_rootLevel(String notin_rootLevel)
    {
        this.notin_rootLevel = notin_rootLevel;
        return this;
    }

    public Integer getEq_classifyLevel()
    {
        return eq_classifyLevel;
    }

    public JobLevelSearchBO setEq_classifyLevel(Integer eq_classifyLevel)
    {
        this.eq_classifyLevel = eq_classifyLevel;
        return this;
    }

    public Integer getNeq_classifyLevel()
    {
        return neq_classifyLevel;
    }

    public JobLevelSearchBO setNeq_classifyLevel(Integer neq_classifyLevel)
    {
        this.neq_classifyLevel = neq_classifyLevel;
        return this;
    }

    public Integer getGt_classifyLevel()
    {
         return gt_classifyLevel;
    }

    public JobLevelSearchBO setGt_classifyLevel(Integer gt_classifyLevel)
    {
        this.gt_classifyLevel = gt_classifyLevel;
        return this;
    }

    public Integer getLt_classifyLevel()
    {
        return lt_classifyLevel;
    }

    public JobLevelSearchBO setLt_classifyLevel(Integer lt_classifyLevel)
    {
        this.lt_classifyLevel = lt_classifyLevel;
        return this;
    }

    public Integer getEgt_classifyLevel()
    {
        return egt_classifyLevel;
    }

    public JobLevelSearchBO setEgt_classifyLevel(Integer egt_classifyLevel)
    {
        this.egt_classifyLevel = egt_classifyLevel;
        return this;
    }

    public Integer getElt_classifyLevel()
    {
        return elt_classifyLevel;
    }

    public JobLevelSearchBO setElt_classifyLevel(Integer elt_classifyLevel)
    {
        this.elt_classifyLevel = elt_classifyLevel;
        return this;
    }

    public String getBetween_classifyLevel()
    {
        return between_classifyLevel;
    }

    public JobLevelSearchBO setBetween_classifyLevel(String between_classifyLevel)
    {
        this.between_classifyLevel = between_classifyLevel;
        return this;
    }

    public String getNotbetween_classifyLevel()
    {
        return notbetween_classifyLevel;
    }

    public JobLevelSearchBO setNotbetween_classifyLevel(String notbetween_classifyLevel)
    {
        this.notbetween_classifyLevel = notbetween_classifyLevel;
        return this;
    }

    public String getIn_classifyLevel()
    {
        return in_classifyLevel;
    }

    public JobLevelSearchBO setIn_classifyLevel(String in_classifyLevel)
    {
        this.in_classifyLevel = in_classifyLevel;
        return this;
    }

    public String getNotin_classifyLevel()
    {
        return notin_classifyLevel;
    }

    public JobLevelSearchBO setNotin_classifyLevel(String notin_classifyLevel)
    {
        this.notin_classifyLevel = notin_classifyLevel;
        return this;
    }

    public Date getEq_createdTime()
    {
        return eq_createdTime;
    }

    public JobLevelSearchBO setEq_createdTime(Date eq_createdTime)
    {
        this.eq_createdTime = eq_createdTime;
        return this;
    }

    public Date getNeq_createdTime()
    {
        return neq_createdTime;
    }

    public JobLevelSearchBO setNeq_createdTime(Date neq_createdTime)
    {
        this.neq_createdTime = neq_createdTime;
        return this;
    }

    public Date getGt_createdTime()
    {
         return gt_createdTime;
    }

    public JobLevelSearchBO setGt_createdTime(Date gt_createdTime)
    {
        this.gt_createdTime = gt_createdTime;
        return this;
    }

    public Date getLt_createdTime()
    {
        return lt_createdTime;
    }

    public JobLevelSearchBO setLt_createdTime(Date lt_createdTime)
    {
        this.lt_createdTime = lt_createdTime;
        return this;
    }

    public Date getEgt_createdTime()
    {
        return egt_createdTime;
    }

    public JobLevelSearchBO setEgt_createdTime(Date egt_createdTime)
    {
        this.egt_createdTime = egt_createdTime;
        return this;
    }

    public Date getElt_createdTime()
    {
        return elt_createdTime;
    }

    public JobLevelSearchBO setElt_createdTime(Date elt_createdTime)
    {
        this.elt_createdTime = elt_createdTime;
        return this;
    }

    public String getBetween_createdTime()
    {
        return between_createdTime;
    }

    public JobLevelSearchBO setBetween_createdTime(String between_createdTime)
    {
        this.between_createdTime = between_createdTime;
        return this;
    }

    public String getNotbetween_createdTime()
    {
        return notbetween_createdTime;
    }

    public JobLevelSearchBO setNotbetween_createdTime(String notbetween_createdTime)
    {
        this.notbetween_createdTime = notbetween_createdTime;
        return this;
    }

    public String getIn_createdTime()
    {
        return in_createdTime;
    }

    public JobLevelSearchBO setIn_createdTime(String in_createdTime)
    {
        this.in_createdTime = in_createdTime;
        return this;
    }

    public String getNotin_createdTime()
    {
        return notin_createdTime;
    }

    public JobLevelSearchBO setNotin_createdTime(String notin_createdTime)
    {
        this.notin_createdTime = notin_createdTime;
        return this;
    }

    public Date getEq_updatedTime()
    {
        return eq_updatedTime;
    }

    public JobLevelSearchBO setEq_updatedTime(Date eq_updatedTime)
    {
        this.eq_updatedTime = eq_updatedTime;
        return this;
    }

    public Date getNeq_updatedTime()
    {
        return neq_updatedTime;
    }

    public JobLevelSearchBO setNeq_updatedTime(Date neq_updatedTime)
    {
        this.neq_updatedTime = neq_updatedTime;
        return this;
    }

    public Date getGt_updatedTime()
    {
         return gt_updatedTime;
    }

    public JobLevelSearchBO setGt_updatedTime(Date gt_updatedTime)
    {
        this.gt_updatedTime = gt_updatedTime;
        return this;
    }

    public Date getLt_updatedTime()
    {
        return lt_updatedTime;
    }

    public JobLevelSearchBO setLt_updatedTime(Date lt_updatedTime)
    {
        this.lt_updatedTime = lt_updatedTime;
        return this;
    }

    public Date getEgt_updatedTime()
    {
        return egt_updatedTime;
    }

    public JobLevelSearchBO setEgt_updatedTime(Date egt_updatedTime)
    {
        this.egt_updatedTime = egt_updatedTime;
        return this;
    }

    public Date getElt_updatedTime()
    {
        return elt_updatedTime;
    }

    public JobLevelSearchBO setElt_updatedTime(Date elt_updatedTime)
    {
        this.elt_updatedTime = elt_updatedTime;
        return this;
    }

    public String getBetween_updatedTime()
    {
        return between_updatedTime;
    }

    public JobLevelSearchBO setBetween_updatedTime(String between_updatedTime)
    {
        this.between_updatedTime = between_updatedTime;
        return this;
    }

    public String getNotbetween_updatedTime()
    {
        return notbetween_updatedTime;
    }

    public JobLevelSearchBO setNotbetween_updatedTime(String notbetween_updatedTime)
    {
        this.notbetween_updatedTime = notbetween_updatedTime;
        return this;
    }

    public String getIn_updatedTime()
    {
        return in_updatedTime;
    }

    public JobLevelSearchBO setIn_updatedTime(String in_updatedTime)
    {
        this.in_updatedTime = in_updatedTime;
        return this;
    }

    public String getNotin_updatedTime()
    {
        return notin_updatedTime;
    }

    public JobLevelSearchBO setNotin_updatedTime(String notin_updatedTime)
    {
        this.notin_updatedTime = notin_updatedTime;
        return this;
    }

    public String getEq_createUser()
    {
        return eq_createUser;
    }

    public JobLevelSearchBO setEq_createUser(String eq_createUser)
    {
        this.eq_createUser = eq_createUser;
        return this;
    }

    public String getNeq_createUser()
    {
        return neq_createUser;
    }

    public JobLevelSearchBO setNeq_createUser(String neq_createUser)
    {
        this.neq_createUser = neq_createUser;
        return this;
    }

    public String getLike_createUser()
    {
        return like_createUser;
    }

    public JobLevelSearchBO setLike_createUser(String like_createUser)
    {
        this.like_createUser = like_createUser;
        return this;
    }

    public String getLiker_createUser()
    {
        return liker_createUser;
     }

    public JobLevelSearchBO setLiker_createUser(String liker_createUser)
    {
        this.liker_createUser = liker_createUser;
        return this;
    }

    public String getLikel_createUser()
    {
        return likel_createUser;
    }

    public JobLevelSearchBO setLikel_createUser(String likel_createUser)
    {
        this.likel_createUser = likel_createUser;
        return this;
    }

    public String getNotlike_createUser()
    {
        return notlike_createUser;
    }

    public JobLevelSearchBO setNotlike_createUser(String notlike_createUser)
    {
        this.notlike_createUser = notlike_createUser;
        return this;
    }

    public String getNotliker_createUser()
    {
        return notliker_createUser;
    }

    public JobLevelSearchBO setNotliker_createUser(String notliker_createUser)
    {
        this.notliker_createUser = notliker_createUser;
        return this;
    }

    public String getNotlikel_createUser()
    {
        return notlikel_createUser;
    }

    public JobLevelSearchBO setNotlikel_createUser(String notlikel_createUser)
    {
        this.notlikel_createUser = notlikel_createUser;
        return this;
    }

    public String getIn_createUser()
    {
        return in_createUser;
    }

    public JobLevelSearchBO setIn_createUser(String in_createUser)
    {
        this.in_createUser = in_createUser;
        return this;
    }

    public String getNotin_createUser()
    {
        return notin_createUser;
    }

    public JobLevelSearchBO setNotin_createUser(String notin_createUser)
    {
        this.notin_createUser = notin_createUser;
        return this;
    }

    public BigInteger getEq_createUserId()
    {
        return eq_createUserId;
    }

    public JobLevelSearchBO setEq_createUserId(BigInteger eq_createUserId)
    {
        this.eq_createUserId = eq_createUserId;
        return this;
    }

    public BigInteger getNeq_createUserId()
    {
        return neq_createUserId;
    }

    public JobLevelSearchBO setNeq_createUserId(BigInteger neq_createUserId)
    {
        this.neq_createUserId = neq_createUserId;
        return this;
    }

    public String getLike_createUserId()
    {
        return like_createUserId;
    }

    public JobLevelSearchBO setLike_createUserId(String like_createUserId)
    {
        this.like_createUserId = like_createUserId;
        return this;
    }

    public String getLiker_createUserId()
    {
        return liker_createUserId;
     }

    public JobLevelSearchBO setLiker_createUserId(String liker_createUserId)
    {
        this.liker_createUserId = liker_createUserId;
        return this;
    }

    public String getLikel_createUserId()
    {
        return likel_createUserId;
    }

    public JobLevelSearchBO setLikel_createUserId(String likel_createUserId)
    {
        this.likel_createUserId = likel_createUserId;
        return this;
    }

    public String getNotlike_createUserId()
    {
        return notlike_createUserId;
    }

    public JobLevelSearchBO setNotlike_createUserId(String notlike_createUserId)
    {
        this.notlike_createUserId = notlike_createUserId;
        return this;
    }

    public String getNotliker_createUserId()
    {
        return notliker_createUserId;
    }

    public JobLevelSearchBO setNotliker_createUserId(String notliker_createUserId)
    {
        this.notliker_createUserId = notliker_createUserId;
        return this;
    }

    public String getNotlikel_createUserId()
    {
        return notlikel_createUserId;
    }

    public JobLevelSearchBO setNotlikel_createUserId(String notlikel_createUserId)
    {
        this.notlikel_createUserId = notlikel_createUserId;
        return this;
    }

    public String getIn_createUserId()
    {
        return in_createUserId;
    }

    public JobLevelSearchBO setIn_createUserId(String in_createUserId)
    {
        this.in_createUserId = in_createUserId;
        return this;
    }

    public String getNotin_createUserId()
    {
        return notin_createUserId;
    }

    public JobLevelSearchBO setNotin_createUserId(String notin_createUserId)
    {
        this.notin_createUserId = notin_createUserId;
        return this;
    }

    public String getEq_updateUser()
    {
        return eq_updateUser;
    }

    public JobLevelSearchBO setEq_updateUser(String eq_updateUser)
    {
        this.eq_updateUser = eq_updateUser;
        return this;
    }

    public String getNeq_updateUser()
    {
        return neq_updateUser;
    }

    public JobLevelSearchBO setNeq_updateUser(String neq_updateUser)
    {
        this.neq_updateUser = neq_updateUser;
        return this;
    }

    public String getLike_updateUser()
    {
        return like_updateUser;
    }

    public JobLevelSearchBO setLike_updateUser(String like_updateUser)
    {
        this.like_updateUser = like_updateUser;
        return this;
    }

    public String getLiker_updateUser()
    {
        return liker_updateUser;
     }

    public JobLevelSearchBO setLiker_updateUser(String liker_updateUser)
    {
        this.liker_updateUser = liker_updateUser;
        return this;
    }

    public String getLikel_updateUser()
    {
        return likel_updateUser;
    }

    public JobLevelSearchBO setLikel_updateUser(String likel_updateUser)
    {
        this.likel_updateUser = likel_updateUser;
        return this;
    }

    public String getNotlike_updateUser()
    {
        return notlike_updateUser;
    }

    public JobLevelSearchBO setNotlike_updateUser(String notlike_updateUser)
    {
        this.notlike_updateUser = notlike_updateUser;
        return this;
    }

    public String getNotliker_updateUser()
    {
        return notliker_updateUser;
    }

    public JobLevelSearchBO setNotliker_updateUser(String notliker_updateUser)
    {
        this.notliker_updateUser = notliker_updateUser;
        return this;
    }

    public String getNotlikel_updateUser()
    {
        return notlikel_updateUser;
    }

    public JobLevelSearchBO setNotlikel_updateUser(String notlikel_updateUser)
    {
        this.notlikel_updateUser = notlikel_updateUser;
        return this;
    }

    public String getIn_updateUser()
    {
        return in_updateUser;
    }

    public JobLevelSearchBO setIn_updateUser(String in_updateUser)
    {
        this.in_updateUser = in_updateUser;
        return this;
    }

    public String getNotin_updateUser()
    {
        return notin_updateUser;
    }

    public JobLevelSearchBO setNotin_updateUser(String notin_updateUser)
    {
        this.notin_updateUser = notin_updateUser;
        return this;
    }

    public BigInteger getEq_updateUserId()
    {
        return eq_updateUserId;
    }

    public JobLevelSearchBO setEq_updateUserId(BigInteger eq_updateUserId)
    {
        this.eq_updateUserId = eq_updateUserId;
        return this;
    }

    public BigInteger getNeq_updateUserId()
    {
        return neq_updateUserId;
    }

    public JobLevelSearchBO setNeq_updateUserId(BigInteger neq_updateUserId)
    {
        this.neq_updateUserId = neq_updateUserId;
        return this;
    }

    public String getLike_updateUserId()
    {
        return like_updateUserId;
    }

    public JobLevelSearchBO setLike_updateUserId(String like_updateUserId)
    {
        this.like_updateUserId = like_updateUserId;
        return this;
    }

    public String getLiker_updateUserId()
    {
        return liker_updateUserId;
     }

    public JobLevelSearchBO setLiker_updateUserId(String liker_updateUserId)
    {
        this.liker_updateUserId = liker_updateUserId;
        return this;
    }

    public String getLikel_updateUserId()
    {
        return likel_updateUserId;
    }

    public JobLevelSearchBO setLikel_updateUserId(String likel_updateUserId)
    {
        this.likel_updateUserId = likel_updateUserId;
        return this;
    }

    public String getNotlike_updateUserId()
    {
        return notlike_updateUserId;
    }

    public JobLevelSearchBO setNotlike_updateUserId(String notlike_updateUserId)
    {
        this.notlike_updateUserId = notlike_updateUserId;
        return this;
    }

    public String getNotliker_updateUserId()
    {
        return notliker_updateUserId;
    }

    public JobLevelSearchBO setNotliker_updateUserId(String notliker_updateUserId)
    {
        this.notliker_updateUserId = notliker_updateUserId;
        return this;
    }

    public String getNotlikel_updateUserId()
    {
        return notlikel_updateUserId;
    }

    public JobLevelSearchBO setNotlikel_updateUserId(String notlikel_updateUserId)
    {
        this.notlikel_updateUserId = notlikel_updateUserId;
        return this;
    }

    public String getIn_updateUserId()
    {
        return in_updateUserId;
    }

    public JobLevelSearchBO setIn_updateUserId(String in_updateUserId)
    {
        this.in_updateUserId = in_updateUserId;
        return this;
    }

    public String getNotin_updateUserId()
    {
        return notin_updateUserId;
    }

    public JobLevelSearchBO setNotin_updateUserId(String notin_updateUserId)
    {
        this.notin_updateUserId = notin_updateUserId;
        return this;
    }

}