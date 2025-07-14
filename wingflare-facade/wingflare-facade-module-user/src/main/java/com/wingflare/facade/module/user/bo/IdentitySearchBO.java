package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.math.BigInteger;
import java.util.Date;

/**
 * <p>
 * 岗位身份数据表 查询对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
public class IdentitySearchBO extends BaseSearchBo
{
        
    private BigInteger eq_identityId;

    private BigInteger neq_identityId;

    private String like_identityId;

    private String liker_identityId;

    private String likel_identityId;

    private String notlike_identityId;

    private String notliker_identityId;

    private String notlikel_identityId;

    private String in_identityId;

    private String notin_identityId;
        
    private String eq_identityCode;

    private String neq_identityCode;

    private String like_identityCode;

    private String liker_identityCode;

    private String likel_identityCode;

    private String notlike_identityCode;

    private String notliker_identityCode;

    private String notlikel_identityCode;

    private String in_identityCode;

    private String notin_identityCode;
        
    private BigInteger eq_orgId;

    private BigInteger neq_orgId;

    private String like_orgId;

    private String liker_orgId;

    private String likel_orgId;

    private String notlike_orgId;

    private String notliker_orgId;

    private String notlikel_orgId;

    private String in_orgId;

    private String notin_orgId;
        
    private BigInteger eq_departmentId;

    private BigInteger neq_departmentId;

    private String like_departmentId;

    private String liker_departmentId;

    private String likel_departmentId;

    private String notlike_departmentId;

    private String notliker_departmentId;

    private String notlikel_departmentId;

    private String in_departmentId;

    private String notin_departmentId;
        
    private String eq_identityName;

    private String neq_identityName;

    private String like_identityName;

    private String liker_identityName;

    private String likel_identityName;

    private String notlike_identityName;

    private String notliker_identityName;

    private String notlikel_identityName;

    private String in_identityName;

    private String notin_identityName;
        
    private String eq_jobLevelId;

    private String neq_jobLevelId;

    private String like_jobLevelId;

    private String liker_jobLevelId;

    private String likel_jobLevelId;

    private String notlike_jobLevelId;

    private String notliker_jobLevelId;

    private String notlikel_jobLevelId;

    private String in_jobLevelId;

    private String notin_jobLevelId;
        
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

    public BigInteger getEq_identityId()
    {
        return eq_identityId;
    }

    public IdentitySearchBO setEq_identityId(BigInteger eq_identityId)
    {
        this.eq_identityId = eq_identityId;
        return this;
    }

    public BigInteger getNeq_identityId()
    {
        return neq_identityId;
    }

    public IdentitySearchBO setNeq_identityId(BigInteger neq_identityId)
    {
        this.neq_identityId = neq_identityId;
        return this;
    }

    public String getLike_identityId()
    {
        return like_identityId;
    }

    public IdentitySearchBO setLike_identityId(String like_identityId)
    {
        this.like_identityId = like_identityId;
        return this;
    }

    public String getLiker_identityId()
    {
        return liker_identityId;
     }

    public IdentitySearchBO setLiker_identityId(String liker_identityId)
    {
        this.liker_identityId = liker_identityId;
        return this;
    }

    public String getLikel_identityId()
    {
        return likel_identityId;
    }

    public IdentitySearchBO setLikel_identityId(String likel_identityId)
    {
        this.likel_identityId = likel_identityId;
        return this;
    }

    public String getNotlike_identityId()
    {
        return notlike_identityId;
    }

    public IdentitySearchBO setNotlike_identityId(String notlike_identityId)
    {
        this.notlike_identityId = notlike_identityId;
        return this;
    }

    public String getNotliker_identityId()
    {
        return notliker_identityId;
    }

    public IdentitySearchBO setNotliker_identityId(String notliker_identityId)
    {
        this.notliker_identityId = notliker_identityId;
        return this;
    }

    public String getNotlikel_identityId()
    {
        return notlikel_identityId;
    }

    public IdentitySearchBO setNotlikel_identityId(String notlikel_identityId)
    {
        this.notlikel_identityId = notlikel_identityId;
        return this;
    }

    public String getIn_identityId()
    {
        return in_identityId;
    }

    public IdentitySearchBO setIn_identityId(String in_identityId)
    {
        this.in_identityId = in_identityId;
        return this;
    }

    public String getNotin_identityId()
    {
        return notin_identityId;
    }

    public IdentitySearchBO setNotin_identityId(String notin_identityId)
    {
        this.notin_identityId = notin_identityId;
        return this;
    }

    public String getEq_identityCode()
    {
        return eq_identityCode;
    }

    public IdentitySearchBO setEq_identityCode(String eq_identityCode)
    {
        this.eq_identityCode = eq_identityCode;
        return this;
    }

    public String getNeq_identityCode()
    {
        return neq_identityCode;
    }

    public IdentitySearchBO setNeq_identityCode(String neq_identityCode)
    {
        this.neq_identityCode = neq_identityCode;
        return this;
    }

    public String getLike_identityCode()
    {
        return like_identityCode;
    }

    public IdentitySearchBO setLike_identityCode(String like_identityCode)
    {
        this.like_identityCode = like_identityCode;
        return this;
    }

    public String getLiker_identityCode()
    {
        return liker_identityCode;
     }

    public IdentitySearchBO setLiker_identityCode(String liker_identityCode)
    {
        this.liker_identityCode = liker_identityCode;
        return this;
    }

    public String getLikel_identityCode()
    {
        return likel_identityCode;
    }

    public IdentitySearchBO setLikel_identityCode(String likel_identityCode)
    {
        this.likel_identityCode = likel_identityCode;
        return this;
    }

    public String getNotlike_identityCode()
    {
        return notlike_identityCode;
    }

    public IdentitySearchBO setNotlike_identityCode(String notlike_identityCode)
    {
        this.notlike_identityCode = notlike_identityCode;
        return this;
    }

    public String getNotliker_identityCode()
    {
        return notliker_identityCode;
    }

    public IdentitySearchBO setNotliker_identityCode(String notliker_identityCode)
    {
        this.notliker_identityCode = notliker_identityCode;
        return this;
    }

    public String getNotlikel_identityCode()
    {
        return notlikel_identityCode;
    }

    public IdentitySearchBO setNotlikel_identityCode(String notlikel_identityCode)
    {
        this.notlikel_identityCode = notlikel_identityCode;
        return this;
    }

    public String getIn_identityCode()
    {
        return in_identityCode;
    }

    public IdentitySearchBO setIn_identityCode(String in_identityCode)
    {
        this.in_identityCode = in_identityCode;
        return this;
    }

    public String getNotin_identityCode()
    {
        return notin_identityCode;
    }

    public IdentitySearchBO setNotin_identityCode(String notin_identityCode)
    {
        this.notin_identityCode = notin_identityCode;
        return this;
    }

    public BigInteger getEq_orgId()
    {
        return eq_orgId;
    }

    public IdentitySearchBO setEq_orgId(BigInteger eq_orgId)
    {
        this.eq_orgId = eq_orgId;
        return this;
    }

    public BigInteger getNeq_orgId()
    {
        return neq_orgId;
    }

    public IdentitySearchBO setNeq_orgId(BigInteger neq_orgId)
    {
        this.neq_orgId = neq_orgId;
        return this;
    }

    public String getLike_orgId()
    {
        return like_orgId;
    }

    public IdentitySearchBO setLike_orgId(String like_orgId)
    {
        this.like_orgId = like_orgId;
        return this;
    }

    public String getLiker_orgId()
    {
        return liker_orgId;
     }

    public IdentitySearchBO setLiker_orgId(String liker_orgId)
    {
        this.liker_orgId = liker_orgId;
        return this;
    }

    public String getLikel_orgId()
    {
        return likel_orgId;
    }

    public IdentitySearchBO setLikel_orgId(String likel_orgId)
    {
        this.likel_orgId = likel_orgId;
        return this;
    }

    public String getNotlike_orgId()
    {
        return notlike_orgId;
    }

    public IdentitySearchBO setNotlike_orgId(String notlike_orgId)
    {
        this.notlike_orgId = notlike_orgId;
        return this;
    }

    public String getNotliker_orgId()
    {
        return notliker_orgId;
    }

    public IdentitySearchBO setNotliker_orgId(String notliker_orgId)
    {
        this.notliker_orgId = notliker_orgId;
        return this;
    }

    public String getNotlikel_orgId()
    {
        return notlikel_orgId;
    }

    public IdentitySearchBO setNotlikel_orgId(String notlikel_orgId)
    {
        this.notlikel_orgId = notlikel_orgId;
        return this;
    }

    public String getIn_orgId()
    {
        return in_orgId;
    }

    public IdentitySearchBO setIn_orgId(String in_orgId)
    {
        this.in_orgId = in_orgId;
        return this;
    }

    public String getNotin_orgId()
    {
        return notin_orgId;
    }

    public IdentitySearchBO setNotin_orgId(String notin_orgId)
    {
        this.notin_orgId = notin_orgId;
        return this;
    }

    public BigInteger getEq_departmentId()
    {
        return eq_departmentId;
    }

    public IdentitySearchBO setEq_departmentId(BigInteger eq_departmentId)
    {
        this.eq_departmentId = eq_departmentId;
        return this;
    }

    public BigInteger getNeq_departmentId()
    {
        return neq_departmentId;
    }

    public IdentitySearchBO setNeq_departmentId(BigInteger neq_departmentId)
    {
        this.neq_departmentId = neq_departmentId;
        return this;
    }

    public String getLike_departmentId()
    {
        return like_departmentId;
    }

    public IdentitySearchBO setLike_departmentId(String like_departmentId)
    {
        this.like_departmentId = like_departmentId;
        return this;
    }

    public String getLiker_departmentId()
    {
        return liker_departmentId;
     }

    public IdentitySearchBO setLiker_departmentId(String liker_departmentId)
    {
        this.liker_departmentId = liker_departmentId;
        return this;
    }

    public String getLikel_departmentId()
    {
        return likel_departmentId;
    }

    public IdentitySearchBO setLikel_departmentId(String likel_departmentId)
    {
        this.likel_departmentId = likel_departmentId;
        return this;
    }

    public String getNotlike_departmentId()
    {
        return notlike_departmentId;
    }

    public IdentitySearchBO setNotlike_departmentId(String notlike_departmentId)
    {
        this.notlike_departmentId = notlike_departmentId;
        return this;
    }

    public String getNotliker_departmentId()
    {
        return notliker_departmentId;
    }

    public IdentitySearchBO setNotliker_departmentId(String notliker_departmentId)
    {
        this.notliker_departmentId = notliker_departmentId;
        return this;
    }

    public String getNotlikel_departmentId()
    {
        return notlikel_departmentId;
    }

    public IdentitySearchBO setNotlikel_departmentId(String notlikel_departmentId)
    {
        this.notlikel_departmentId = notlikel_departmentId;
        return this;
    }

    public String getIn_departmentId()
    {
        return in_departmentId;
    }

    public IdentitySearchBO setIn_departmentId(String in_departmentId)
    {
        this.in_departmentId = in_departmentId;
        return this;
    }

    public String getNotin_departmentId()
    {
        return notin_departmentId;
    }

    public IdentitySearchBO setNotin_departmentId(String notin_departmentId)
    {
        this.notin_departmentId = notin_departmentId;
        return this;
    }

    public String getEq_identityName()
    {
        return eq_identityName;
    }

    public IdentitySearchBO setEq_identityName(String eq_identityName)
    {
        this.eq_identityName = eq_identityName;
        return this;
    }

    public String getNeq_identityName()
    {
        return neq_identityName;
    }

    public IdentitySearchBO setNeq_identityName(String neq_identityName)
    {
        this.neq_identityName = neq_identityName;
        return this;
    }

    public String getLike_identityName()
    {
        return like_identityName;
    }

    public IdentitySearchBO setLike_identityName(String like_identityName)
    {
        this.like_identityName = like_identityName;
        return this;
    }

    public String getLiker_identityName()
    {
        return liker_identityName;
     }

    public IdentitySearchBO setLiker_identityName(String liker_identityName)
    {
        this.liker_identityName = liker_identityName;
        return this;
    }

    public String getLikel_identityName()
    {
        return likel_identityName;
    }

    public IdentitySearchBO setLikel_identityName(String likel_identityName)
    {
        this.likel_identityName = likel_identityName;
        return this;
    }

    public String getNotlike_identityName()
    {
        return notlike_identityName;
    }

    public IdentitySearchBO setNotlike_identityName(String notlike_identityName)
    {
        this.notlike_identityName = notlike_identityName;
        return this;
    }

    public String getNotliker_identityName()
    {
        return notliker_identityName;
    }

    public IdentitySearchBO setNotliker_identityName(String notliker_identityName)
    {
        this.notliker_identityName = notliker_identityName;
        return this;
    }

    public String getNotlikel_identityName()
    {
        return notlikel_identityName;
    }

    public IdentitySearchBO setNotlikel_identityName(String notlikel_identityName)
    {
        this.notlikel_identityName = notlikel_identityName;
        return this;
    }

    public String getIn_identityName()
    {
        return in_identityName;
    }

    public IdentitySearchBO setIn_identityName(String in_identityName)
    {
        this.in_identityName = in_identityName;
        return this;
    }

    public String getNotin_identityName()
    {
        return notin_identityName;
    }

    public IdentitySearchBO setNotin_identityName(String notin_identityName)
    {
        this.notin_identityName = notin_identityName;
        return this;
    }

    public String getEq_jobLevelId()
    {
        return eq_jobLevelId;
    }

    public IdentitySearchBO setEq_jobLevelId(String eq_jobLevelId)
    {
        this.eq_jobLevelId = eq_jobLevelId;
        return this;
    }

    public String getNeq_jobLevelId()
    {
        return neq_jobLevelId;
    }

    public IdentitySearchBO setNeq_jobLevelId(String neq_jobLevelId)
    {
        this.neq_jobLevelId = neq_jobLevelId;
        return this;
    }

    public String getLike_jobLevelId()
    {
        return like_jobLevelId;
    }

    public IdentitySearchBO setLike_jobLevelId(String like_jobLevelId)
    {
        this.like_jobLevelId = like_jobLevelId;
        return this;
    }

    public String getLiker_jobLevelId()
    {
        return liker_jobLevelId;
     }

    public IdentitySearchBO setLiker_jobLevelId(String liker_jobLevelId)
    {
        this.liker_jobLevelId = liker_jobLevelId;
        return this;
    }

    public String getLikel_jobLevelId()
    {
        return likel_jobLevelId;
    }

    public IdentitySearchBO setLikel_jobLevelId(String likel_jobLevelId)
    {
        this.likel_jobLevelId = likel_jobLevelId;
        return this;
    }

    public String getNotlike_jobLevelId()
    {
        return notlike_jobLevelId;
    }

    public IdentitySearchBO setNotlike_jobLevelId(String notlike_jobLevelId)
    {
        this.notlike_jobLevelId = notlike_jobLevelId;
        return this;
    }

    public String getNotliker_jobLevelId()
    {
        return notliker_jobLevelId;
    }

    public IdentitySearchBO setNotliker_jobLevelId(String notliker_jobLevelId)
    {
        this.notliker_jobLevelId = notliker_jobLevelId;
        return this;
    }

    public String getNotlikel_jobLevelId()
    {
        return notlikel_jobLevelId;
    }

    public IdentitySearchBO setNotlikel_jobLevelId(String notlikel_jobLevelId)
    {
        this.notlikel_jobLevelId = notlikel_jobLevelId;
        return this;
    }

    public String getIn_jobLevelId()
    {
        return in_jobLevelId;
    }

    public IdentitySearchBO setIn_jobLevelId(String in_jobLevelId)
    {
        this.in_jobLevelId = in_jobLevelId;
        return this;
    }

    public String getNotin_jobLevelId()
    {
        return notin_jobLevelId;
    }

    public IdentitySearchBO setNotin_jobLevelId(String notin_jobLevelId)
    {
        this.notin_jobLevelId = notin_jobLevelId;
        return this;
    }

    public Date getEq_createdTime()
    {
        return eq_createdTime;
    }

    public IdentitySearchBO setEq_createdTime(Date eq_createdTime)
    {
        this.eq_createdTime = eq_createdTime;
        return this;
    }

    public Date getNeq_createdTime()
    {
        return neq_createdTime;
    }

    public IdentitySearchBO setNeq_createdTime(Date neq_createdTime)
    {
        this.neq_createdTime = neq_createdTime;
        return this;
    }

    public Date getGt_createdTime()
    {
         return gt_createdTime;
    }

    public IdentitySearchBO setGt_createdTime(Date gt_createdTime)
    {
        this.gt_createdTime = gt_createdTime;
        return this;
    }

    public Date getLt_createdTime()
    {
        return lt_createdTime;
    }

    public IdentitySearchBO setLt_createdTime(Date lt_createdTime)
    {
        this.lt_createdTime = lt_createdTime;
        return this;
    }

    public Date getEgt_createdTime()
    {
        return egt_createdTime;
    }

    public IdentitySearchBO setEgt_createdTime(Date egt_createdTime)
    {
        this.egt_createdTime = egt_createdTime;
        return this;
    }

    public Date getElt_createdTime()
    {
        return elt_createdTime;
    }

    public IdentitySearchBO setElt_createdTime(Date elt_createdTime)
    {
        this.elt_createdTime = elt_createdTime;
        return this;
    }

    public String getBetween_createdTime()
    {
        return between_createdTime;
    }

    public IdentitySearchBO setBetween_createdTime(String between_createdTime)
    {
        this.between_createdTime = between_createdTime;
        return this;
    }

    public String getNotbetween_createdTime()
    {
        return notbetween_createdTime;
    }

    public IdentitySearchBO setNotbetween_createdTime(String notbetween_createdTime)
    {
        this.notbetween_createdTime = notbetween_createdTime;
        return this;
    }

    public String getIn_createdTime()
    {
        return in_createdTime;
    }

    public IdentitySearchBO setIn_createdTime(String in_createdTime)
    {
        this.in_createdTime = in_createdTime;
        return this;
    }

    public String getNotin_createdTime()
    {
        return notin_createdTime;
    }

    public IdentitySearchBO setNotin_createdTime(String notin_createdTime)
    {
        this.notin_createdTime = notin_createdTime;
        return this;
    }

    public Date getEq_updatedTime()
    {
        return eq_updatedTime;
    }

    public IdentitySearchBO setEq_updatedTime(Date eq_updatedTime)
    {
        this.eq_updatedTime = eq_updatedTime;
        return this;
    }

    public Date getNeq_updatedTime()
    {
        return neq_updatedTime;
    }

    public IdentitySearchBO setNeq_updatedTime(Date neq_updatedTime)
    {
        this.neq_updatedTime = neq_updatedTime;
        return this;
    }

    public Date getGt_updatedTime()
    {
         return gt_updatedTime;
    }

    public IdentitySearchBO setGt_updatedTime(Date gt_updatedTime)
    {
        this.gt_updatedTime = gt_updatedTime;
        return this;
    }

    public Date getLt_updatedTime()
    {
        return lt_updatedTime;
    }

    public IdentitySearchBO setLt_updatedTime(Date lt_updatedTime)
    {
        this.lt_updatedTime = lt_updatedTime;
        return this;
    }

    public Date getEgt_updatedTime()
    {
        return egt_updatedTime;
    }

    public IdentitySearchBO setEgt_updatedTime(Date egt_updatedTime)
    {
        this.egt_updatedTime = egt_updatedTime;
        return this;
    }

    public Date getElt_updatedTime()
    {
        return elt_updatedTime;
    }

    public IdentitySearchBO setElt_updatedTime(Date elt_updatedTime)
    {
        this.elt_updatedTime = elt_updatedTime;
        return this;
    }

    public String getBetween_updatedTime()
    {
        return between_updatedTime;
    }

    public IdentitySearchBO setBetween_updatedTime(String between_updatedTime)
    {
        this.between_updatedTime = between_updatedTime;
        return this;
    }

    public String getNotbetween_updatedTime()
    {
        return notbetween_updatedTime;
    }

    public IdentitySearchBO setNotbetween_updatedTime(String notbetween_updatedTime)
    {
        this.notbetween_updatedTime = notbetween_updatedTime;
        return this;
    }

    public String getIn_updatedTime()
    {
        return in_updatedTime;
    }

    public IdentitySearchBO setIn_updatedTime(String in_updatedTime)
    {
        this.in_updatedTime = in_updatedTime;
        return this;
    }

    public String getNotin_updatedTime()
    {
        return notin_updatedTime;
    }

    public IdentitySearchBO setNotin_updatedTime(String notin_updatedTime)
    {
        this.notin_updatedTime = notin_updatedTime;
        return this;
    }

    public String getEq_createUser()
    {
        return eq_createUser;
    }

    public IdentitySearchBO setEq_createUser(String eq_createUser)
    {
        this.eq_createUser = eq_createUser;
        return this;
    }

    public String getNeq_createUser()
    {
        return neq_createUser;
    }

    public IdentitySearchBO setNeq_createUser(String neq_createUser)
    {
        this.neq_createUser = neq_createUser;
        return this;
    }

    public String getLike_createUser()
    {
        return like_createUser;
    }

    public IdentitySearchBO setLike_createUser(String like_createUser)
    {
        this.like_createUser = like_createUser;
        return this;
    }

    public String getLiker_createUser()
    {
        return liker_createUser;
     }

    public IdentitySearchBO setLiker_createUser(String liker_createUser)
    {
        this.liker_createUser = liker_createUser;
        return this;
    }

    public String getLikel_createUser()
    {
        return likel_createUser;
    }

    public IdentitySearchBO setLikel_createUser(String likel_createUser)
    {
        this.likel_createUser = likel_createUser;
        return this;
    }

    public String getNotlike_createUser()
    {
        return notlike_createUser;
    }

    public IdentitySearchBO setNotlike_createUser(String notlike_createUser)
    {
        this.notlike_createUser = notlike_createUser;
        return this;
    }

    public String getNotliker_createUser()
    {
        return notliker_createUser;
    }

    public IdentitySearchBO setNotliker_createUser(String notliker_createUser)
    {
        this.notliker_createUser = notliker_createUser;
        return this;
    }

    public String getNotlikel_createUser()
    {
        return notlikel_createUser;
    }

    public IdentitySearchBO setNotlikel_createUser(String notlikel_createUser)
    {
        this.notlikel_createUser = notlikel_createUser;
        return this;
    }

    public String getIn_createUser()
    {
        return in_createUser;
    }

    public IdentitySearchBO setIn_createUser(String in_createUser)
    {
        this.in_createUser = in_createUser;
        return this;
    }

    public String getNotin_createUser()
    {
        return notin_createUser;
    }

    public IdentitySearchBO setNotin_createUser(String notin_createUser)
    {
        this.notin_createUser = notin_createUser;
        return this;
    }

    public BigInteger getEq_createUserId()
    {
        return eq_createUserId;
    }

    public IdentitySearchBO setEq_createUserId(BigInteger eq_createUserId)
    {
        this.eq_createUserId = eq_createUserId;
        return this;
    }

    public BigInteger getNeq_createUserId()
    {
        return neq_createUserId;
    }

    public IdentitySearchBO setNeq_createUserId(BigInteger neq_createUserId)
    {
        this.neq_createUserId = neq_createUserId;
        return this;
    }

    public String getLike_createUserId()
    {
        return like_createUserId;
    }

    public IdentitySearchBO setLike_createUserId(String like_createUserId)
    {
        this.like_createUserId = like_createUserId;
        return this;
    }

    public String getLiker_createUserId()
    {
        return liker_createUserId;
     }

    public IdentitySearchBO setLiker_createUserId(String liker_createUserId)
    {
        this.liker_createUserId = liker_createUserId;
        return this;
    }

    public String getLikel_createUserId()
    {
        return likel_createUserId;
    }

    public IdentitySearchBO setLikel_createUserId(String likel_createUserId)
    {
        this.likel_createUserId = likel_createUserId;
        return this;
    }

    public String getNotlike_createUserId()
    {
        return notlike_createUserId;
    }

    public IdentitySearchBO setNotlike_createUserId(String notlike_createUserId)
    {
        this.notlike_createUserId = notlike_createUserId;
        return this;
    }

    public String getNotliker_createUserId()
    {
        return notliker_createUserId;
    }

    public IdentitySearchBO setNotliker_createUserId(String notliker_createUserId)
    {
        this.notliker_createUserId = notliker_createUserId;
        return this;
    }

    public String getNotlikel_createUserId()
    {
        return notlikel_createUserId;
    }

    public IdentitySearchBO setNotlikel_createUserId(String notlikel_createUserId)
    {
        this.notlikel_createUserId = notlikel_createUserId;
        return this;
    }

    public String getIn_createUserId()
    {
        return in_createUserId;
    }

    public IdentitySearchBO setIn_createUserId(String in_createUserId)
    {
        this.in_createUserId = in_createUserId;
        return this;
    }

    public String getNotin_createUserId()
    {
        return notin_createUserId;
    }

    public IdentitySearchBO setNotin_createUserId(String notin_createUserId)
    {
        this.notin_createUserId = notin_createUserId;
        return this;
    }

    public String getEq_updateUser()
    {
        return eq_updateUser;
    }

    public IdentitySearchBO setEq_updateUser(String eq_updateUser)
    {
        this.eq_updateUser = eq_updateUser;
        return this;
    }

    public String getNeq_updateUser()
    {
        return neq_updateUser;
    }

    public IdentitySearchBO setNeq_updateUser(String neq_updateUser)
    {
        this.neq_updateUser = neq_updateUser;
        return this;
    }

    public String getLike_updateUser()
    {
        return like_updateUser;
    }

    public IdentitySearchBO setLike_updateUser(String like_updateUser)
    {
        this.like_updateUser = like_updateUser;
        return this;
    }

    public String getLiker_updateUser()
    {
        return liker_updateUser;
     }

    public IdentitySearchBO setLiker_updateUser(String liker_updateUser)
    {
        this.liker_updateUser = liker_updateUser;
        return this;
    }

    public String getLikel_updateUser()
    {
        return likel_updateUser;
    }

    public IdentitySearchBO setLikel_updateUser(String likel_updateUser)
    {
        this.likel_updateUser = likel_updateUser;
        return this;
    }

    public String getNotlike_updateUser()
    {
        return notlike_updateUser;
    }

    public IdentitySearchBO setNotlike_updateUser(String notlike_updateUser)
    {
        this.notlike_updateUser = notlike_updateUser;
        return this;
    }

    public String getNotliker_updateUser()
    {
        return notliker_updateUser;
    }

    public IdentitySearchBO setNotliker_updateUser(String notliker_updateUser)
    {
        this.notliker_updateUser = notliker_updateUser;
        return this;
    }

    public String getNotlikel_updateUser()
    {
        return notlikel_updateUser;
    }

    public IdentitySearchBO setNotlikel_updateUser(String notlikel_updateUser)
    {
        this.notlikel_updateUser = notlikel_updateUser;
        return this;
    }

    public String getIn_updateUser()
    {
        return in_updateUser;
    }

    public IdentitySearchBO setIn_updateUser(String in_updateUser)
    {
        this.in_updateUser = in_updateUser;
        return this;
    }

    public String getNotin_updateUser()
    {
        return notin_updateUser;
    }

    public IdentitySearchBO setNotin_updateUser(String notin_updateUser)
    {
        this.notin_updateUser = notin_updateUser;
        return this;
    }

    public BigInteger getEq_updateUserId()
    {
        return eq_updateUserId;
    }

    public IdentitySearchBO setEq_updateUserId(BigInteger eq_updateUserId)
    {
        this.eq_updateUserId = eq_updateUserId;
        return this;
    }

    public BigInteger getNeq_updateUserId()
    {
        return neq_updateUserId;
    }

    public IdentitySearchBO setNeq_updateUserId(BigInteger neq_updateUserId)
    {
        this.neq_updateUserId = neq_updateUserId;
        return this;
    }

    public String getLike_updateUserId()
    {
        return like_updateUserId;
    }

    public IdentitySearchBO setLike_updateUserId(String like_updateUserId)
    {
        this.like_updateUserId = like_updateUserId;
        return this;
    }

    public String getLiker_updateUserId()
    {
        return liker_updateUserId;
     }

    public IdentitySearchBO setLiker_updateUserId(String liker_updateUserId)
    {
        this.liker_updateUserId = liker_updateUserId;
        return this;
    }

    public String getLikel_updateUserId()
    {
        return likel_updateUserId;
    }

    public IdentitySearchBO setLikel_updateUserId(String likel_updateUserId)
    {
        this.likel_updateUserId = likel_updateUserId;
        return this;
    }

    public String getNotlike_updateUserId()
    {
        return notlike_updateUserId;
    }

    public IdentitySearchBO setNotlike_updateUserId(String notlike_updateUserId)
    {
        this.notlike_updateUserId = notlike_updateUserId;
        return this;
    }

    public String getNotliker_updateUserId()
    {
        return notliker_updateUserId;
    }

    public IdentitySearchBO setNotliker_updateUserId(String notliker_updateUserId)
    {
        this.notliker_updateUserId = notliker_updateUserId;
        return this;
    }

    public String getNotlikel_updateUserId()
    {
        return notlikel_updateUserId;
    }

    public IdentitySearchBO setNotlikel_updateUserId(String notlikel_updateUserId)
    {
        this.notlikel_updateUserId = notlikel_updateUserId;
        return this;
    }

    public String getIn_updateUserId()
    {
        return in_updateUserId;
    }

    public IdentitySearchBO setIn_updateUserId(String in_updateUserId)
    {
        this.in_updateUserId = in_updateUserId;
        return this;
    }

    public String getNotin_updateUserId()
    {
        return notin_updateUserId;
    }

    public IdentitySearchBO setNotin_updateUserId(String notin_updateUserId)
    {
        this.notin_updateUserId = notin_updateUserId;
        return this;
    }

}