package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.math.BigInteger;
import java.util.Date;

/**
 * <p>
 * 职级分类表 查询对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
public class JobLevelClassifySearchBO extends BaseSearchBo
{
        
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
        
    private String eq_classifyName;

    private String neq_classifyName;

    private String like_classifyName;

    private String liker_classifyName;

    private String likel_classifyName;

    private String notlike_classifyName;

    private String notliker_classifyName;

    private String notlikel_classifyName;

    private String in_classifyName;

    private String notin_classifyName;
        
    private String eq_classifyCode;

    private String neq_classifyCode;

    private String like_classifyCode;

    private String liker_classifyCode;

    private String likel_classifyCode;

    private String notlike_classifyCode;

    private String notliker_classifyCode;

    private String notlikel_classifyCode;

    private String in_classifyCode;

    private String notin_classifyCode;
        
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

    public BigInteger getEq_levelClassifyId()
    {
        return eq_levelClassifyId;
    }

    public JobLevelClassifySearchBO setEq_levelClassifyId(BigInteger eq_levelClassifyId)
    {
        this.eq_levelClassifyId = eq_levelClassifyId;
        return this;
    }

    public BigInteger getNeq_levelClassifyId()
    {
        return neq_levelClassifyId;
    }

    public JobLevelClassifySearchBO setNeq_levelClassifyId(BigInteger neq_levelClassifyId)
    {
        this.neq_levelClassifyId = neq_levelClassifyId;
        return this;
    }

    public String getLike_levelClassifyId()
    {
        return like_levelClassifyId;
    }

    public JobLevelClassifySearchBO setLike_levelClassifyId(String like_levelClassifyId)
    {
        this.like_levelClassifyId = like_levelClassifyId;
        return this;
    }

    public String getLiker_levelClassifyId()
    {
        return liker_levelClassifyId;
     }

    public JobLevelClassifySearchBO setLiker_levelClassifyId(String liker_levelClassifyId)
    {
        this.liker_levelClassifyId = liker_levelClassifyId;
        return this;
    }

    public String getLikel_levelClassifyId()
    {
        return likel_levelClassifyId;
    }

    public JobLevelClassifySearchBO setLikel_levelClassifyId(String likel_levelClassifyId)
    {
        this.likel_levelClassifyId = likel_levelClassifyId;
        return this;
    }

    public String getNotlike_levelClassifyId()
    {
        return notlike_levelClassifyId;
    }

    public JobLevelClassifySearchBO setNotlike_levelClassifyId(String notlike_levelClassifyId)
    {
        this.notlike_levelClassifyId = notlike_levelClassifyId;
        return this;
    }

    public String getNotliker_levelClassifyId()
    {
        return notliker_levelClassifyId;
    }

    public JobLevelClassifySearchBO setNotliker_levelClassifyId(String notliker_levelClassifyId)
    {
        this.notliker_levelClassifyId = notliker_levelClassifyId;
        return this;
    }

    public String getNotlikel_levelClassifyId()
    {
        return notlikel_levelClassifyId;
    }

    public JobLevelClassifySearchBO setNotlikel_levelClassifyId(String notlikel_levelClassifyId)
    {
        this.notlikel_levelClassifyId = notlikel_levelClassifyId;
        return this;
    }

    public String getIn_levelClassifyId()
    {
        return in_levelClassifyId;
    }

    public JobLevelClassifySearchBO setIn_levelClassifyId(String in_levelClassifyId)
    {
        this.in_levelClassifyId = in_levelClassifyId;
        return this;
    }

    public String getNotin_levelClassifyId()
    {
        return notin_levelClassifyId;
    }

    public JobLevelClassifySearchBO setNotin_levelClassifyId(String notin_levelClassifyId)
    {
        this.notin_levelClassifyId = notin_levelClassifyId;
        return this;
    }

    public String getEq_classifyName()
    {
        return eq_classifyName;
    }

    public JobLevelClassifySearchBO setEq_classifyName(String eq_classifyName)
    {
        this.eq_classifyName = eq_classifyName;
        return this;
    }

    public String getNeq_classifyName()
    {
        return neq_classifyName;
    }

    public JobLevelClassifySearchBO setNeq_classifyName(String neq_classifyName)
    {
        this.neq_classifyName = neq_classifyName;
        return this;
    }

    public String getLike_classifyName()
    {
        return like_classifyName;
    }

    public JobLevelClassifySearchBO setLike_classifyName(String like_classifyName)
    {
        this.like_classifyName = like_classifyName;
        return this;
    }

    public String getLiker_classifyName()
    {
        return liker_classifyName;
     }

    public JobLevelClassifySearchBO setLiker_classifyName(String liker_classifyName)
    {
        this.liker_classifyName = liker_classifyName;
        return this;
    }

    public String getLikel_classifyName()
    {
        return likel_classifyName;
    }

    public JobLevelClassifySearchBO setLikel_classifyName(String likel_classifyName)
    {
        this.likel_classifyName = likel_classifyName;
        return this;
    }

    public String getNotlike_classifyName()
    {
        return notlike_classifyName;
    }

    public JobLevelClassifySearchBO setNotlike_classifyName(String notlike_classifyName)
    {
        this.notlike_classifyName = notlike_classifyName;
        return this;
    }

    public String getNotliker_classifyName()
    {
        return notliker_classifyName;
    }

    public JobLevelClassifySearchBO setNotliker_classifyName(String notliker_classifyName)
    {
        this.notliker_classifyName = notliker_classifyName;
        return this;
    }

    public String getNotlikel_classifyName()
    {
        return notlikel_classifyName;
    }

    public JobLevelClassifySearchBO setNotlikel_classifyName(String notlikel_classifyName)
    {
        this.notlikel_classifyName = notlikel_classifyName;
        return this;
    }

    public String getIn_classifyName()
    {
        return in_classifyName;
    }

    public JobLevelClassifySearchBO setIn_classifyName(String in_classifyName)
    {
        this.in_classifyName = in_classifyName;
        return this;
    }

    public String getNotin_classifyName()
    {
        return notin_classifyName;
    }

    public JobLevelClassifySearchBO setNotin_classifyName(String notin_classifyName)
    {
        this.notin_classifyName = notin_classifyName;
        return this;
    }

    public String getEq_classifyCode()
    {
        return eq_classifyCode;
    }

    public JobLevelClassifySearchBO setEq_classifyCode(String eq_classifyCode)
    {
        this.eq_classifyCode = eq_classifyCode;
        return this;
    }

    public String getNeq_classifyCode()
    {
        return neq_classifyCode;
    }

    public JobLevelClassifySearchBO setNeq_classifyCode(String neq_classifyCode)
    {
        this.neq_classifyCode = neq_classifyCode;
        return this;
    }

    public String getLike_classifyCode()
    {
        return like_classifyCode;
    }

    public JobLevelClassifySearchBO setLike_classifyCode(String like_classifyCode)
    {
        this.like_classifyCode = like_classifyCode;
        return this;
    }

    public String getLiker_classifyCode()
    {
        return liker_classifyCode;
     }

    public JobLevelClassifySearchBO setLiker_classifyCode(String liker_classifyCode)
    {
        this.liker_classifyCode = liker_classifyCode;
        return this;
    }

    public String getLikel_classifyCode()
    {
        return likel_classifyCode;
    }

    public JobLevelClassifySearchBO setLikel_classifyCode(String likel_classifyCode)
    {
        this.likel_classifyCode = likel_classifyCode;
        return this;
    }

    public String getNotlike_classifyCode()
    {
        return notlike_classifyCode;
    }

    public JobLevelClassifySearchBO setNotlike_classifyCode(String notlike_classifyCode)
    {
        this.notlike_classifyCode = notlike_classifyCode;
        return this;
    }

    public String getNotliker_classifyCode()
    {
        return notliker_classifyCode;
    }

    public JobLevelClassifySearchBO setNotliker_classifyCode(String notliker_classifyCode)
    {
        this.notliker_classifyCode = notliker_classifyCode;
        return this;
    }

    public String getNotlikel_classifyCode()
    {
        return notlikel_classifyCode;
    }

    public JobLevelClassifySearchBO setNotlikel_classifyCode(String notlikel_classifyCode)
    {
        this.notlikel_classifyCode = notlikel_classifyCode;
        return this;
    }

    public String getIn_classifyCode()
    {
        return in_classifyCode;
    }

    public JobLevelClassifySearchBO setIn_classifyCode(String in_classifyCode)
    {
        this.in_classifyCode = in_classifyCode;
        return this;
    }

    public String getNotin_classifyCode()
    {
        return notin_classifyCode;
    }

    public JobLevelClassifySearchBO setNotin_classifyCode(String notin_classifyCode)
    {
        this.notin_classifyCode = notin_classifyCode;
        return this;
    }

    public Date getEq_createdTime()
    {
        return eq_createdTime;
    }

    public JobLevelClassifySearchBO setEq_createdTime(Date eq_createdTime)
    {
        this.eq_createdTime = eq_createdTime;
        return this;
    }

    public Date getNeq_createdTime()
    {
        return neq_createdTime;
    }

    public JobLevelClassifySearchBO setNeq_createdTime(Date neq_createdTime)
    {
        this.neq_createdTime = neq_createdTime;
        return this;
    }

    public Date getGt_createdTime()
    {
         return gt_createdTime;
    }

    public JobLevelClassifySearchBO setGt_createdTime(Date gt_createdTime)
    {
        this.gt_createdTime = gt_createdTime;
        return this;
    }

    public Date getLt_createdTime()
    {
        return lt_createdTime;
    }

    public JobLevelClassifySearchBO setLt_createdTime(Date lt_createdTime)
    {
        this.lt_createdTime = lt_createdTime;
        return this;
    }

    public Date getEgt_createdTime()
    {
        return egt_createdTime;
    }

    public JobLevelClassifySearchBO setEgt_createdTime(Date egt_createdTime)
    {
        this.egt_createdTime = egt_createdTime;
        return this;
    }

    public Date getElt_createdTime()
    {
        return elt_createdTime;
    }

    public JobLevelClassifySearchBO setElt_createdTime(Date elt_createdTime)
    {
        this.elt_createdTime = elt_createdTime;
        return this;
    }

    public String getBetween_createdTime()
    {
        return between_createdTime;
    }

    public JobLevelClassifySearchBO setBetween_createdTime(String between_createdTime)
    {
        this.between_createdTime = between_createdTime;
        return this;
    }

    public String getNotbetween_createdTime()
    {
        return notbetween_createdTime;
    }

    public JobLevelClassifySearchBO setNotbetween_createdTime(String notbetween_createdTime)
    {
        this.notbetween_createdTime = notbetween_createdTime;
        return this;
    }

    public String getIn_createdTime()
    {
        return in_createdTime;
    }

    public JobLevelClassifySearchBO setIn_createdTime(String in_createdTime)
    {
        this.in_createdTime = in_createdTime;
        return this;
    }

    public String getNotin_createdTime()
    {
        return notin_createdTime;
    }

    public JobLevelClassifySearchBO setNotin_createdTime(String notin_createdTime)
    {
        this.notin_createdTime = notin_createdTime;
        return this;
    }

    public Date getEq_updatedTime()
    {
        return eq_updatedTime;
    }

    public JobLevelClassifySearchBO setEq_updatedTime(Date eq_updatedTime)
    {
        this.eq_updatedTime = eq_updatedTime;
        return this;
    }

    public Date getNeq_updatedTime()
    {
        return neq_updatedTime;
    }

    public JobLevelClassifySearchBO setNeq_updatedTime(Date neq_updatedTime)
    {
        this.neq_updatedTime = neq_updatedTime;
        return this;
    }

    public Date getGt_updatedTime()
    {
         return gt_updatedTime;
    }

    public JobLevelClassifySearchBO setGt_updatedTime(Date gt_updatedTime)
    {
        this.gt_updatedTime = gt_updatedTime;
        return this;
    }

    public Date getLt_updatedTime()
    {
        return lt_updatedTime;
    }

    public JobLevelClassifySearchBO setLt_updatedTime(Date lt_updatedTime)
    {
        this.lt_updatedTime = lt_updatedTime;
        return this;
    }

    public Date getEgt_updatedTime()
    {
        return egt_updatedTime;
    }

    public JobLevelClassifySearchBO setEgt_updatedTime(Date egt_updatedTime)
    {
        this.egt_updatedTime = egt_updatedTime;
        return this;
    }

    public Date getElt_updatedTime()
    {
        return elt_updatedTime;
    }

    public JobLevelClassifySearchBO setElt_updatedTime(Date elt_updatedTime)
    {
        this.elt_updatedTime = elt_updatedTime;
        return this;
    }

    public String getBetween_updatedTime()
    {
        return between_updatedTime;
    }

    public JobLevelClassifySearchBO setBetween_updatedTime(String between_updatedTime)
    {
        this.between_updatedTime = between_updatedTime;
        return this;
    }

    public String getNotbetween_updatedTime()
    {
        return notbetween_updatedTime;
    }

    public JobLevelClassifySearchBO setNotbetween_updatedTime(String notbetween_updatedTime)
    {
        this.notbetween_updatedTime = notbetween_updatedTime;
        return this;
    }

    public String getIn_updatedTime()
    {
        return in_updatedTime;
    }

    public JobLevelClassifySearchBO setIn_updatedTime(String in_updatedTime)
    {
        this.in_updatedTime = in_updatedTime;
        return this;
    }

    public String getNotin_updatedTime()
    {
        return notin_updatedTime;
    }

    public JobLevelClassifySearchBO setNotin_updatedTime(String notin_updatedTime)
    {
        this.notin_updatedTime = notin_updatedTime;
        return this;
    }

    public String getEq_createUser()
    {
        return eq_createUser;
    }

    public JobLevelClassifySearchBO setEq_createUser(String eq_createUser)
    {
        this.eq_createUser = eq_createUser;
        return this;
    }

    public String getNeq_createUser()
    {
        return neq_createUser;
    }

    public JobLevelClassifySearchBO setNeq_createUser(String neq_createUser)
    {
        this.neq_createUser = neq_createUser;
        return this;
    }

    public String getLike_createUser()
    {
        return like_createUser;
    }

    public JobLevelClassifySearchBO setLike_createUser(String like_createUser)
    {
        this.like_createUser = like_createUser;
        return this;
    }

    public String getLiker_createUser()
    {
        return liker_createUser;
     }

    public JobLevelClassifySearchBO setLiker_createUser(String liker_createUser)
    {
        this.liker_createUser = liker_createUser;
        return this;
    }

    public String getLikel_createUser()
    {
        return likel_createUser;
    }

    public JobLevelClassifySearchBO setLikel_createUser(String likel_createUser)
    {
        this.likel_createUser = likel_createUser;
        return this;
    }

    public String getNotlike_createUser()
    {
        return notlike_createUser;
    }

    public JobLevelClassifySearchBO setNotlike_createUser(String notlike_createUser)
    {
        this.notlike_createUser = notlike_createUser;
        return this;
    }

    public String getNotliker_createUser()
    {
        return notliker_createUser;
    }

    public JobLevelClassifySearchBO setNotliker_createUser(String notliker_createUser)
    {
        this.notliker_createUser = notliker_createUser;
        return this;
    }

    public String getNotlikel_createUser()
    {
        return notlikel_createUser;
    }

    public JobLevelClassifySearchBO setNotlikel_createUser(String notlikel_createUser)
    {
        this.notlikel_createUser = notlikel_createUser;
        return this;
    }

    public String getIn_createUser()
    {
        return in_createUser;
    }

    public JobLevelClassifySearchBO setIn_createUser(String in_createUser)
    {
        this.in_createUser = in_createUser;
        return this;
    }

    public String getNotin_createUser()
    {
        return notin_createUser;
    }

    public JobLevelClassifySearchBO setNotin_createUser(String notin_createUser)
    {
        this.notin_createUser = notin_createUser;
        return this;
    }

    public BigInteger getEq_createUserId()
    {
        return eq_createUserId;
    }

    public JobLevelClassifySearchBO setEq_createUserId(BigInteger eq_createUserId)
    {
        this.eq_createUserId = eq_createUserId;
        return this;
    }

    public BigInteger getNeq_createUserId()
    {
        return neq_createUserId;
    }

    public JobLevelClassifySearchBO setNeq_createUserId(BigInteger neq_createUserId)
    {
        this.neq_createUserId = neq_createUserId;
        return this;
    }

    public String getLike_createUserId()
    {
        return like_createUserId;
    }

    public JobLevelClassifySearchBO setLike_createUserId(String like_createUserId)
    {
        this.like_createUserId = like_createUserId;
        return this;
    }

    public String getLiker_createUserId()
    {
        return liker_createUserId;
     }

    public JobLevelClassifySearchBO setLiker_createUserId(String liker_createUserId)
    {
        this.liker_createUserId = liker_createUserId;
        return this;
    }

    public String getLikel_createUserId()
    {
        return likel_createUserId;
    }

    public JobLevelClassifySearchBO setLikel_createUserId(String likel_createUserId)
    {
        this.likel_createUserId = likel_createUserId;
        return this;
    }

    public String getNotlike_createUserId()
    {
        return notlike_createUserId;
    }

    public JobLevelClassifySearchBO setNotlike_createUserId(String notlike_createUserId)
    {
        this.notlike_createUserId = notlike_createUserId;
        return this;
    }

    public String getNotliker_createUserId()
    {
        return notliker_createUserId;
    }

    public JobLevelClassifySearchBO setNotliker_createUserId(String notliker_createUserId)
    {
        this.notliker_createUserId = notliker_createUserId;
        return this;
    }

    public String getNotlikel_createUserId()
    {
        return notlikel_createUserId;
    }

    public JobLevelClassifySearchBO setNotlikel_createUserId(String notlikel_createUserId)
    {
        this.notlikel_createUserId = notlikel_createUserId;
        return this;
    }

    public String getIn_createUserId()
    {
        return in_createUserId;
    }

    public JobLevelClassifySearchBO setIn_createUserId(String in_createUserId)
    {
        this.in_createUserId = in_createUserId;
        return this;
    }

    public String getNotin_createUserId()
    {
        return notin_createUserId;
    }

    public JobLevelClassifySearchBO setNotin_createUserId(String notin_createUserId)
    {
        this.notin_createUserId = notin_createUserId;
        return this;
    }

    public String getEq_updateUser()
    {
        return eq_updateUser;
    }

    public JobLevelClassifySearchBO setEq_updateUser(String eq_updateUser)
    {
        this.eq_updateUser = eq_updateUser;
        return this;
    }

    public String getNeq_updateUser()
    {
        return neq_updateUser;
    }

    public JobLevelClassifySearchBO setNeq_updateUser(String neq_updateUser)
    {
        this.neq_updateUser = neq_updateUser;
        return this;
    }

    public String getLike_updateUser()
    {
        return like_updateUser;
    }

    public JobLevelClassifySearchBO setLike_updateUser(String like_updateUser)
    {
        this.like_updateUser = like_updateUser;
        return this;
    }

    public String getLiker_updateUser()
    {
        return liker_updateUser;
     }

    public JobLevelClassifySearchBO setLiker_updateUser(String liker_updateUser)
    {
        this.liker_updateUser = liker_updateUser;
        return this;
    }

    public String getLikel_updateUser()
    {
        return likel_updateUser;
    }

    public JobLevelClassifySearchBO setLikel_updateUser(String likel_updateUser)
    {
        this.likel_updateUser = likel_updateUser;
        return this;
    }

    public String getNotlike_updateUser()
    {
        return notlike_updateUser;
    }

    public JobLevelClassifySearchBO setNotlike_updateUser(String notlike_updateUser)
    {
        this.notlike_updateUser = notlike_updateUser;
        return this;
    }

    public String getNotliker_updateUser()
    {
        return notliker_updateUser;
    }

    public JobLevelClassifySearchBO setNotliker_updateUser(String notliker_updateUser)
    {
        this.notliker_updateUser = notliker_updateUser;
        return this;
    }

    public String getNotlikel_updateUser()
    {
        return notlikel_updateUser;
    }

    public JobLevelClassifySearchBO setNotlikel_updateUser(String notlikel_updateUser)
    {
        this.notlikel_updateUser = notlikel_updateUser;
        return this;
    }

    public String getIn_updateUser()
    {
        return in_updateUser;
    }

    public JobLevelClassifySearchBO setIn_updateUser(String in_updateUser)
    {
        this.in_updateUser = in_updateUser;
        return this;
    }

    public String getNotin_updateUser()
    {
        return notin_updateUser;
    }

    public JobLevelClassifySearchBO setNotin_updateUser(String notin_updateUser)
    {
        this.notin_updateUser = notin_updateUser;
        return this;
    }

    public BigInteger getEq_updateUserId()
    {
        return eq_updateUserId;
    }

    public JobLevelClassifySearchBO setEq_updateUserId(BigInteger eq_updateUserId)
    {
        this.eq_updateUserId = eq_updateUserId;
        return this;
    }

    public BigInteger getNeq_updateUserId()
    {
        return neq_updateUserId;
    }

    public JobLevelClassifySearchBO setNeq_updateUserId(BigInteger neq_updateUserId)
    {
        this.neq_updateUserId = neq_updateUserId;
        return this;
    }

    public String getLike_updateUserId()
    {
        return like_updateUserId;
    }

    public JobLevelClassifySearchBO setLike_updateUserId(String like_updateUserId)
    {
        this.like_updateUserId = like_updateUserId;
        return this;
    }

    public String getLiker_updateUserId()
    {
        return liker_updateUserId;
     }

    public JobLevelClassifySearchBO setLiker_updateUserId(String liker_updateUserId)
    {
        this.liker_updateUserId = liker_updateUserId;
        return this;
    }

    public String getLikel_updateUserId()
    {
        return likel_updateUserId;
    }

    public JobLevelClassifySearchBO setLikel_updateUserId(String likel_updateUserId)
    {
        this.likel_updateUserId = likel_updateUserId;
        return this;
    }

    public String getNotlike_updateUserId()
    {
        return notlike_updateUserId;
    }

    public JobLevelClassifySearchBO setNotlike_updateUserId(String notlike_updateUserId)
    {
        this.notlike_updateUserId = notlike_updateUserId;
        return this;
    }

    public String getNotliker_updateUserId()
    {
        return notliker_updateUserId;
    }

    public JobLevelClassifySearchBO setNotliker_updateUserId(String notliker_updateUserId)
    {
        this.notliker_updateUserId = notliker_updateUserId;
        return this;
    }

    public String getNotlikel_updateUserId()
    {
        return notlikel_updateUserId;
    }

    public JobLevelClassifySearchBO setNotlikel_updateUserId(String notlikel_updateUserId)
    {
        this.notlikel_updateUserId = notlikel_updateUserId;
        return this;
    }

    public String getIn_updateUserId()
    {
        return in_updateUserId;
    }

    public JobLevelClassifySearchBO setIn_updateUserId(String in_updateUserId)
    {
        this.in_updateUserId = in_updateUserId;
        return this;
    }

    public String getNotin_updateUserId()
    {
        return notin_updateUserId;
    }

    public JobLevelClassifySearchBO setNotin_updateUserId(String notin_updateUserId)
    {
        this.notin_updateUserId = notin_updateUserId;
        return this;
    }

}