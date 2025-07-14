package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.math.BigInteger;
import java.util.Date;

/**
 * <p>
 * 系统用户角色表 查询对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-10
 */
public class UserRoleSearchBO extends BaseSearchBo
{
        
    private BigInteger eq_id;

    private BigInteger neq_id;

    private String like_id;

    private String liker_id;

    private String likel_id;

    private String notlike_id;

    private String notliker_id;

    private String notlikel_id;

    private String in_id;

    private String notin_id;
        
    private BigInteger eq_userId;

    private BigInteger neq_userId;

    private String like_userId;

    private String liker_userId;

    private String likel_userId;

    private String notlike_userId;

    private String notliker_userId;

    private String notlikel_userId;

    private String in_userId;

    private String notin_userId;
        
    private String eq_systemCode;

    private String neq_systemCode;

    private String like_systemCode;

    private String liker_systemCode;

    private String likel_systemCode;

    private String notlike_systemCode;

    private String notliker_systemCode;

    private String notlikel_systemCode;

    private String in_systemCode;

    private String notin_systemCode;
        
    private BigInteger eq_roleId;

    private BigInteger neq_roleId;

    private String like_roleId;

    private String liker_roleId;

    private String likel_roleId;

    private String notlike_roleId;

    private String notliker_roleId;

    private String notlikel_roleId;

    private String in_roleId;

    private String notin_roleId;
        
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

    public BigInteger getEq_id()
    {
        return eq_id;
    }

    public UserRoleSearchBO setEq_id(BigInteger eq_id)
    {
        this.eq_id = eq_id;
        return this;
    }

    public BigInteger getNeq_id()
    {
        return neq_id;
    }

    public UserRoleSearchBO setNeq_id(BigInteger neq_id)
    {
        this.neq_id = neq_id;
        return this;
    }

    public String getLike_id()
    {
        return like_id;
    }

    public UserRoleSearchBO setLike_id(String like_id)
    {
        this.like_id = like_id;
        return this;
    }

    public String getLiker_id()
    {
        return liker_id;
     }

    public UserRoleSearchBO setLiker_id(String liker_id)
    {
        this.liker_id = liker_id;
        return this;
    }

    public String getLikel_id()
    {
        return likel_id;
    }

    public UserRoleSearchBO setLikel_id(String likel_id)
    {
        this.likel_id = likel_id;
        return this;
    }

    public String getNotlike_id()
    {
        return notlike_id;
    }

    public UserRoleSearchBO setNotlike_id(String notlike_id)
    {
        this.notlike_id = notlike_id;
        return this;
    }

    public String getNotliker_id()
    {
        return notliker_id;
    }

    public UserRoleSearchBO setNotliker_id(String notliker_id)
    {
        this.notliker_id = notliker_id;
        return this;
    }

    public String getNotlikel_id()
    {
        return notlikel_id;
    }

    public UserRoleSearchBO setNotlikel_id(String notlikel_id)
    {
        this.notlikel_id = notlikel_id;
        return this;
    }

    public String getIn_id()
    {
        return in_id;
    }

    public UserRoleSearchBO setIn_id(String in_id)
    {
        this.in_id = in_id;
        return this;
    }

    public String getNotin_id()
    {
        return notin_id;
    }

    public UserRoleSearchBO setNotin_id(String notin_id)
    {
        this.notin_id = notin_id;
        return this;
    }

    public BigInteger getEq_userId()
    {
        return eq_userId;
    }

    public UserRoleSearchBO setEq_userId(BigInteger eq_userId)
    {
        this.eq_userId = eq_userId;
        return this;
    }

    public BigInteger getNeq_userId()
    {
        return neq_userId;
    }

    public UserRoleSearchBO setNeq_userId(BigInteger neq_userId)
    {
        this.neq_userId = neq_userId;
        return this;
    }

    public String getLike_userId()
    {
        return like_userId;
    }

    public UserRoleSearchBO setLike_userId(String like_userId)
    {
        this.like_userId = like_userId;
        return this;
    }

    public String getLiker_userId()
    {
        return liker_userId;
     }

    public UserRoleSearchBO setLiker_userId(String liker_userId)
    {
        this.liker_userId = liker_userId;
        return this;
    }

    public String getLikel_userId()
    {
        return likel_userId;
    }

    public UserRoleSearchBO setLikel_userId(String likel_userId)
    {
        this.likel_userId = likel_userId;
        return this;
    }

    public String getNotlike_userId()
    {
        return notlike_userId;
    }

    public UserRoleSearchBO setNotlike_userId(String notlike_userId)
    {
        this.notlike_userId = notlike_userId;
        return this;
    }

    public String getNotliker_userId()
    {
        return notliker_userId;
    }

    public UserRoleSearchBO setNotliker_userId(String notliker_userId)
    {
        this.notliker_userId = notliker_userId;
        return this;
    }

    public String getNotlikel_userId()
    {
        return notlikel_userId;
    }

    public UserRoleSearchBO setNotlikel_userId(String notlikel_userId)
    {
        this.notlikel_userId = notlikel_userId;
        return this;
    }

    public String getIn_userId()
    {
        return in_userId;
    }

    public UserRoleSearchBO setIn_userId(String in_userId)
    {
        this.in_userId = in_userId;
        return this;
    }

    public String getNotin_userId()
    {
        return notin_userId;
    }

    public UserRoleSearchBO setNotin_userId(String notin_userId)
    {
        this.notin_userId = notin_userId;
        return this;
    }

    public String getEq_systemCode()
    {
        return eq_systemCode;
    }

    public UserRoleSearchBO setEq_systemCode(String eq_systemCode)
    {
        this.eq_systemCode = eq_systemCode;
        return this;
    }

    public String getNeq_systemCode()
    {
        return neq_systemCode;
    }

    public UserRoleSearchBO setNeq_systemCode(String neq_systemCode)
    {
        this.neq_systemCode = neq_systemCode;
        return this;
    }

    public String getLike_systemCode()
    {
        return like_systemCode;
    }

    public UserRoleSearchBO setLike_systemCode(String like_systemCode)
    {
        this.like_systemCode = like_systemCode;
        return this;
    }

    public String getLiker_systemCode()
    {
        return liker_systemCode;
     }

    public UserRoleSearchBO setLiker_systemCode(String liker_systemCode)
    {
        this.liker_systemCode = liker_systemCode;
        return this;
    }

    public String getLikel_systemCode()
    {
        return likel_systemCode;
    }

    public UserRoleSearchBO setLikel_systemCode(String likel_systemCode)
    {
        this.likel_systemCode = likel_systemCode;
        return this;
    }

    public String getNotlike_systemCode()
    {
        return notlike_systemCode;
    }

    public UserRoleSearchBO setNotlike_systemCode(String notlike_systemCode)
    {
        this.notlike_systemCode = notlike_systemCode;
        return this;
    }

    public String getNotliker_systemCode()
    {
        return notliker_systemCode;
    }

    public UserRoleSearchBO setNotliker_systemCode(String notliker_systemCode)
    {
        this.notliker_systemCode = notliker_systemCode;
        return this;
    }

    public String getNotlikel_systemCode()
    {
        return notlikel_systemCode;
    }

    public UserRoleSearchBO setNotlikel_systemCode(String notlikel_systemCode)
    {
        this.notlikel_systemCode = notlikel_systemCode;
        return this;
    }

    public String getIn_systemCode()
    {
        return in_systemCode;
    }

    public UserRoleSearchBO setIn_systemCode(String in_systemCode)
    {
        this.in_systemCode = in_systemCode;
        return this;
    }

    public String getNotin_systemCode()
    {
        return notin_systemCode;
    }

    public UserRoleSearchBO setNotin_systemCode(String notin_systemCode)
    {
        this.notin_systemCode = notin_systemCode;
        return this;
    }

    public BigInteger getEq_roleId()
    {
        return eq_roleId;
    }

    public UserRoleSearchBO setEq_roleId(BigInteger eq_roleId)
    {
        this.eq_roleId = eq_roleId;
        return this;
    }

    public BigInteger getNeq_roleId()
    {
        return neq_roleId;
    }

    public UserRoleSearchBO setNeq_roleId(BigInteger neq_roleId)
    {
        this.neq_roleId = neq_roleId;
        return this;
    }

    public String getLike_roleId()
    {
        return like_roleId;
    }

    public UserRoleSearchBO setLike_roleId(String like_roleId)
    {
        this.like_roleId = like_roleId;
        return this;
    }

    public String getLiker_roleId()
    {
        return liker_roleId;
     }

    public UserRoleSearchBO setLiker_roleId(String liker_roleId)
    {
        this.liker_roleId = liker_roleId;
        return this;
    }

    public String getLikel_roleId()
    {
        return likel_roleId;
    }

    public UserRoleSearchBO setLikel_roleId(String likel_roleId)
    {
        this.likel_roleId = likel_roleId;
        return this;
    }

    public String getNotlike_roleId()
    {
        return notlike_roleId;
    }

    public UserRoleSearchBO setNotlike_roleId(String notlike_roleId)
    {
        this.notlike_roleId = notlike_roleId;
        return this;
    }

    public String getNotliker_roleId()
    {
        return notliker_roleId;
    }

    public UserRoleSearchBO setNotliker_roleId(String notliker_roleId)
    {
        this.notliker_roleId = notliker_roleId;
        return this;
    }

    public String getNotlikel_roleId()
    {
        return notlikel_roleId;
    }

    public UserRoleSearchBO setNotlikel_roleId(String notlikel_roleId)
    {
        this.notlikel_roleId = notlikel_roleId;
        return this;
    }

    public String getIn_roleId()
    {
        return in_roleId;
    }

    public UserRoleSearchBO setIn_roleId(String in_roleId)
    {
        this.in_roleId = in_roleId;
        return this;
    }

    public String getNotin_roleId()
    {
        return notin_roleId;
    }

    public UserRoleSearchBO setNotin_roleId(String notin_roleId)
    {
        this.notin_roleId = notin_roleId;
        return this;
    }

    public Date getEq_createdTime()
    {
        return eq_createdTime;
    }

    public UserRoleSearchBO setEq_createdTime(Date eq_createdTime)
    {
        this.eq_createdTime = eq_createdTime;
        return this;
    }

    public Date getNeq_createdTime()
    {
        return neq_createdTime;
    }

    public UserRoleSearchBO setNeq_createdTime(Date neq_createdTime)
    {
        this.neq_createdTime = neq_createdTime;
        return this;
    }

    public Date getGt_createdTime()
    {
         return gt_createdTime;
    }

    public UserRoleSearchBO setGt_createdTime(Date gt_createdTime)
    {
        this.gt_createdTime = gt_createdTime;
        return this;
    }

    public Date getLt_createdTime()
    {
        return lt_createdTime;
    }

    public UserRoleSearchBO setLt_createdTime(Date lt_createdTime)
    {
        this.lt_createdTime = lt_createdTime;
        return this;
    }

    public Date getEgt_createdTime()
    {
        return egt_createdTime;
    }

    public UserRoleSearchBO setEgt_createdTime(Date egt_createdTime)
    {
        this.egt_createdTime = egt_createdTime;
        return this;
    }

    public Date getElt_createdTime()
    {
        return elt_createdTime;
    }

    public UserRoleSearchBO setElt_createdTime(Date elt_createdTime)
    {
        this.elt_createdTime = elt_createdTime;
        return this;
    }

    public String getBetween_createdTime()
    {
        return between_createdTime;
    }

    public UserRoleSearchBO setBetween_createdTime(String between_createdTime)
    {
        this.between_createdTime = between_createdTime;
        return this;
    }

    public String getNotbetween_createdTime()
    {
        return notbetween_createdTime;
    }

    public UserRoleSearchBO setNotbetween_createdTime(String notbetween_createdTime)
    {
        this.notbetween_createdTime = notbetween_createdTime;
        return this;
    }

    public String getIn_createdTime()
    {
        return in_createdTime;
    }

    public UserRoleSearchBO setIn_createdTime(String in_createdTime)
    {
        this.in_createdTime = in_createdTime;
        return this;
    }

    public String getNotin_createdTime()
    {
        return notin_createdTime;
    }

    public UserRoleSearchBO setNotin_createdTime(String notin_createdTime)
    {
        this.notin_createdTime = notin_createdTime;
        return this;
    }

    public String getEq_createUser()
    {
        return eq_createUser;
    }

    public UserRoleSearchBO setEq_createUser(String eq_createUser)
    {
        this.eq_createUser = eq_createUser;
        return this;
    }

    public String getNeq_createUser()
    {
        return neq_createUser;
    }

    public UserRoleSearchBO setNeq_createUser(String neq_createUser)
    {
        this.neq_createUser = neq_createUser;
        return this;
    }

    public String getLike_createUser()
    {
        return like_createUser;
    }

    public UserRoleSearchBO setLike_createUser(String like_createUser)
    {
        this.like_createUser = like_createUser;
        return this;
    }

    public String getLiker_createUser()
    {
        return liker_createUser;
     }

    public UserRoleSearchBO setLiker_createUser(String liker_createUser)
    {
        this.liker_createUser = liker_createUser;
        return this;
    }

    public String getLikel_createUser()
    {
        return likel_createUser;
    }

    public UserRoleSearchBO setLikel_createUser(String likel_createUser)
    {
        this.likel_createUser = likel_createUser;
        return this;
    }

    public String getNotlike_createUser()
    {
        return notlike_createUser;
    }

    public UserRoleSearchBO setNotlike_createUser(String notlike_createUser)
    {
        this.notlike_createUser = notlike_createUser;
        return this;
    }

    public String getNotliker_createUser()
    {
        return notliker_createUser;
    }

    public UserRoleSearchBO setNotliker_createUser(String notliker_createUser)
    {
        this.notliker_createUser = notliker_createUser;
        return this;
    }

    public String getNotlikel_createUser()
    {
        return notlikel_createUser;
    }

    public UserRoleSearchBO setNotlikel_createUser(String notlikel_createUser)
    {
        this.notlikel_createUser = notlikel_createUser;
        return this;
    }

    public String getIn_createUser()
    {
        return in_createUser;
    }

    public UserRoleSearchBO setIn_createUser(String in_createUser)
    {
        this.in_createUser = in_createUser;
        return this;
    }

    public String getNotin_createUser()
    {
        return notin_createUser;
    }

    public UserRoleSearchBO setNotin_createUser(String notin_createUser)
    {
        this.notin_createUser = notin_createUser;
        return this;
    }

    public BigInteger getEq_createUserId()
    {
        return eq_createUserId;
    }

    public UserRoleSearchBO setEq_createUserId(BigInteger eq_createUserId)
    {
        this.eq_createUserId = eq_createUserId;
        return this;
    }

    public BigInteger getNeq_createUserId()
    {
        return neq_createUserId;
    }

    public UserRoleSearchBO setNeq_createUserId(BigInteger neq_createUserId)
    {
        this.neq_createUserId = neq_createUserId;
        return this;
    }

    public String getLike_createUserId()
    {
        return like_createUserId;
    }

    public UserRoleSearchBO setLike_createUserId(String like_createUserId)
    {
        this.like_createUserId = like_createUserId;
        return this;
    }

    public String getLiker_createUserId()
    {
        return liker_createUserId;
     }

    public UserRoleSearchBO setLiker_createUserId(String liker_createUserId)
    {
        this.liker_createUserId = liker_createUserId;
        return this;
    }

    public String getLikel_createUserId()
    {
        return likel_createUserId;
    }

    public UserRoleSearchBO setLikel_createUserId(String likel_createUserId)
    {
        this.likel_createUserId = likel_createUserId;
        return this;
    }

    public String getNotlike_createUserId()
    {
        return notlike_createUserId;
    }

    public UserRoleSearchBO setNotlike_createUserId(String notlike_createUserId)
    {
        this.notlike_createUserId = notlike_createUserId;
        return this;
    }

    public String getNotliker_createUserId()
    {
        return notliker_createUserId;
    }

    public UserRoleSearchBO setNotliker_createUserId(String notliker_createUserId)
    {
        this.notliker_createUserId = notliker_createUserId;
        return this;
    }

    public String getNotlikel_createUserId()
    {
        return notlikel_createUserId;
    }

    public UserRoleSearchBO setNotlikel_createUserId(String notlikel_createUserId)
    {
        this.notlikel_createUserId = notlikel_createUserId;
        return this;
    }

    public String getIn_createUserId()
    {
        return in_createUserId;
    }

    public UserRoleSearchBO setIn_createUserId(String in_createUserId)
    {
        this.in_createUserId = in_createUserId;
        return this;
    }

    public String getNotin_createUserId()
    {
        return notin_createUserId;
    }

    public UserRoleSearchBO setNotin_createUserId(String notin_createUserId)
    {
        this.notin_createUserId = notin_createUserId;
        return this;
    }

}