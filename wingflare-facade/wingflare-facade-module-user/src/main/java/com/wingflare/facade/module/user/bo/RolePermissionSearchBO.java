package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.math.BigInteger;
import java.util.Date;

/**
 * RolePermissionSearchBo
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:13:02 CST 2023
 */
public class RolePermissionSearchBO extends BaseSearchBo
{

        private BigInteger eq_id;

        private BigInteger neq_id;
		
        private String in_id;

        private String notin_id;

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

        private String eq_permissionCode;

        private String neq_permissionCode;
		
		private String like_permissionCode;

        private String liker_permissionCode;

        private String likel_permissionCode;

        private String notlike_permissionCode;

        private String notliker_permissionCode;

        private String notlikel_permissionCode;
		
        private String in_permissionCode;

        private String notin_permissionCode;

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

        public RolePermissionSearchBO setEq_id(BigInteger eq_id)
        {
            this.eq_id = eq_id;
            return this;
        }

        public BigInteger getNeq_id()
        {
            return neq_id;
        }

        public RolePermissionSearchBO setNeq_id(BigInteger neq_id)
        {
            this.neq_id = neq_id;
            return this;
        }

        public String getIn_id()
        {
            return in_id;
        }

        public RolePermissionSearchBO setIn_id(String in_id)
        {
            this.in_id = in_id;
            return this;
        }

        public String getNotin_id()
        {
            return notin_id;
        }

        public void setNotin_id(String notin_id)
        {
            this.notin_id = notin_id;
        }

        public BigInteger getEq_roleId()
        {
            return eq_roleId;
        }

        public RolePermissionSearchBO setEq_roleId(BigInteger eq_roleId)
        {
            this.eq_roleId = eq_roleId;
            return this;
        }

        public BigInteger getNeq_roleId()
        {
            return neq_roleId;
        }

        public RolePermissionSearchBO setNeq_roleId(BigInteger neq_roleId)
        {
            this.neq_roleId = neq_roleId;
            return this;
        }
		
        public String getLike_roleId()
        {
            return like_roleId;
        }

        public RolePermissionSearchBO setLike_roleId(String like_roleId)
        {
            this.like_roleId = like_roleId;
            return this;
        }

        public String getLiker_roleId()
        {
            return liker_roleId;
        }

        public RolePermissionSearchBO setLiker_roleId(String liker_roleId)
        {
            this.liker_roleId = liker_roleId;
            return this;
        }

        public String getLikel_roleId()
        {
            return likel_roleId;
        }

        public RolePermissionSearchBO setLikel_roleId(String likel_roleId)
        {
            this.likel_roleId = likel_roleId;
            return this;
        }

        public String getNotlike_roleId()
        {
            return notlike_roleId;
        }

        public RolePermissionSearchBO setNotlike_roleId(String notlike_roleId)
        {
            this.notlike_roleId = notlike_roleId;
            return this;
        }

        public String getNotliker_roleId()
        {
            return notliker_roleId;
        }

        public RolePermissionSearchBO setNotliker_roleId(String notliker_roleId)
        {
            this.notliker_roleId = notliker_roleId;
            return this;
        }

        public String getNotlikel_roleId()
        {
            return notlikel_roleId;
        }

        public RolePermissionSearchBO setNotlikel_roleId(String notlikel_roleId)
        {
            this.notlikel_roleId = notlikel_roleId;
            return this;
        }

        public String getIn_roleId()
        {
            return in_roleId;
        }

        public RolePermissionSearchBO setIn_roleId(String in_roleId)
        {
            this.in_roleId = in_roleId;
            return this;
        }

        public String getNotin_roleId()
        {
            return notin_roleId;
        }

        public void setNotin_roleId(String notin_roleId)
        {
            this.notin_roleId = notin_roleId;
        }

        public String getEq_systemCode()
        {
            return eq_systemCode;
        }

        public RolePermissionSearchBO setEq_systemCode(String eq_systemCode)
        {
            this.eq_systemCode = eq_systemCode;
            return this;
        }

        public String getNeq_systemCode()
        {
            return neq_systemCode;
        }

        public RolePermissionSearchBO setNeq_systemCode(String neq_systemCode)
        {
            this.neq_systemCode = neq_systemCode;
            return this;
        }
		
        public String getLike_systemCode()
        {
            return like_systemCode;
        }

        public RolePermissionSearchBO setLike_systemCode(String like_systemCode)
        {
            this.like_systemCode = like_systemCode;
            return this;
        }

        public String getLiker_systemCode()
        {
            return liker_systemCode;
        }

        public RolePermissionSearchBO setLiker_systemCode(String liker_systemCode)
        {
            this.liker_systemCode = liker_systemCode;
            return this;
        }

        public String getLikel_systemCode()
        {
            return likel_systemCode;
        }

        public RolePermissionSearchBO setLikel_systemCode(String likel_systemCode)
        {
            this.likel_systemCode = likel_systemCode;
            return this;
        }

        public String getNotlike_systemCode()
        {
            return notlike_systemCode;
        }

        public RolePermissionSearchBO setNotlike_systemCode(String notlike_systemCode)
        {
            this.notlike_systemCode = notlike_systemCode;
            return this;
        }

        public String getNotliker_systemCode()
        {
            return notliker_systemCode;
        }

        public RolePermissionSearchBO setNotliker_systemCode(String notliker_systemCode)
        {
            this.notliker_systemCode = notliker_systemCode;
            return this;
        }

        public String getNotlikel_systemCode()
        {
            return notlikel_systemCode;
        }

        public RolePermissionSearchBO setNotlikel_systemCode(String notlikel_systemCode)
        {
            this.notlikel_systemCode = notlikel_systemCode;
            return this;
        }

        public String getIn_systemCode()
        {
            return in_systemCode;
        }

        public RolePermissionSearchBO setIn_systemCode(String in_systemCode)
        {
            this.in_systemCode = in_systemCode;
            return this;
        }

        public String getNotin_systemCode()
        {
            return notin_systemCode;
        }

        public void setNotin_systemCode(String notin_systemCode)
        {
            this.notin_systemCode = notin_systemCode;
        }

        public String getEq_permissionCode()
        {
            return eq_permissionCode;
        }

        public RolePermissionSearchBO setEq_permissionCode(String eq_permissionCode)
        {
            this.eq_permissionCode = eq_permissionCode;
            return this;
        }

        public String getNeq_permissionCode()
        {
            return neq_permissionCode;
        }

        public RolePermissionSearchBO setNeq_permissionCode(String neq_permissionCode)
        {
            this.neq_permissionCode = neq_permissionCode;
            return this;
        }
		
        public String getLike_permissionCode()
        {
            return like_permissionCode;
        }

        public RolePermissionSearchBO setLike_permissionCode(String like_permissionCode)
        {
            this.like_permissionCode = like_permissionCode;
            return this;
        }

        public String getLiker_permissionCode()
        {
            return liker_permissionCode;
        }

        public RolePermissionSearchBO setLiker_permissionCode(String liker_permissionCode)
        {
            this.liker_permissionCode = liker_permissionCode;
            return this;
        }

        public String getLikel_permissionCode()
        {
            return likel_permissionCode;
        }

        public RolePermissionSearchBO setLikel_permissionCode(String likel_permissionCode)
        {
            this.likel_permissionCode = likel_permissionCode;
            return this;
        }

        public String getNotlike_permissionCode()
        {
            return notlike_permissionCode;
        }

        public RolePermissionSearchBO setNotlike_permissionCode(String notlike_permissionCode)
        {
            this.notlike_permissionCode = notlike_permissionCode;
            return this;
        }

        public String getNotliker_permissionCode()
        {
            return notliker_permissionCode;
        }

        public RolePermissionSearchBO setNotliker_permissionCode(String notliker_permissionCode)
        {
            this.notliker_permissionCode = notliker_permissionCode;
            return this;
        }

        public String getNotlikel_permissionCode()
        {
            return notlikel_permissionCode;
        }

        public RolePermissionSearchBO setNotlikel_permissionCode(String notlikel_permissionCode)
        {
            this.notlikel_permissionCode = notlikel_permissionCode;
            return this;
        }

        public String getIn_permissionCode()
        {
            return in_permissionCode;
        }

        public RolePermissionSearchBO setIn_permissionCode(String in_permissionCode)
        {
            this.in_permissionCode = in_permissionCode;
            return this;
        }

        public String getNotin_permissionCode()
        {
            return notin_permissionCode;
        }

        public void setNotin_permissionCode(String notin_permissionCode)
        {
            this.notin_permissionCode = notin_permissionCode;
        }

        public Date getEq_createdTime()
        {
            return eq_createdTime;
        }

        public RolePermissionSearchBO setEq_createdTime(Date eq_createdTime)
        {
            this.eq_createdTime = eq_createdTime;
            return this;
        }

        public Date getNeq_createdTime()
        {
            return neq_createdTime;
        }

        public RolePermissionSearchBO setNeq_createdTime(Date neq_createdTime)
        {
            this.neq_createdTime = neq_createdTime;
            return this;
        }
		
        public Date getGt_createdTime()
        {
            return gt_createdTime;
        }

        public RolePermissionSearchBO setGt_createdTime(Date gt_createdTime)
        {
            this.gt_createdTime = gt_createdTime;
            return this;
        }

        public Date getLt_createdTime()
        {
            return lt_createdTime;
        }

        public RolePermissionSearchBO setLt_createdTime(Date lt_createdTime)
        {
            this.lt_createdTime = lt_createdTime;
            return this;
        }

        public Date getEgt_createdTime()
        {
            return egt_createdTime;
        }

        public RolePermissionSearchBO setEgt_createdTime(Date egt_createdTime)
        {
            this.egt_createdTime = egt_createdTime;
            return this;
        }

        public Date getElt_createdTime()
        {
            return elt_createdTime;
        }

        public RolePermissionSearchBO setElt_createdTime(Date elt_createdTime)
        {
            this.elt_createdTime = elt_createdTime;
            return this;
        }
		
		public String getBetween_createdTime()
        {
            return between_createdTime;
        }

        public RolePermissionSearchBO setBetween_createdTime(String between_createdTime)
        {
            this.between_createdTime = between_createdTime;
            return this;
        }

        public String getNotbetween_createdTime()
        {
            return notbetween_createdTime;
        }

        public RolePermissionSearchBO setNotbetween_createdTime(String notbetween_createdTime)
        {
            this.notbetween_createdTime = notbetween_createdTime;
            return this;
        }

        public String getIn_createdTime()
        {
            return in_createdTime;
        }

        public RolePermissionSearchBO setIn_createdTime(String in_createdTime)
        {
            this.in_createdTime = in_createdTime;
            return this;
        }

        public String getNotin_createdTime()
        {
            return notin_createdTime;
        }

        public void setNotin_createdTime(String notin_createdTime)
        {
            this.notin_createdTime = notin_createdTime;
        }

        public String getEq_createUser()
        {
            return eq_createUser;
        }

        public RolePermissionSearchBO setEq_createUser(String eq_createUser)
        {
            this.eq_createUser = eq_createUser;
            return this;
        }

        public String getNeq_createUser()
        {
            return neq_createUser;
        }

        public RolePermissionSearchBO setNeq_createUser(String neq_createUser)
        {
            this.neq_createUser = neq_createUser;
            return this;
        }
		
        public String getLike_createUser()
        {
            return like_createUser;
        }

        public RolePermissionSearchBO setLike_createUser(String like_createUser)
        {
            this.like_createUser = like_createUser;
            return this;
        }

        public String getLiker_createUser()
        {
            return liker_createUser;
        }

        public RolePermissionSearchBO setLiker_createUser(String liker_createUser)
        {
            this.liker_createUser = liker_createUser;
            return this;
        }

        public String getLikel_createUser()
        {
            return likel_createUser;
        }

        public RolePermissionSearchBO setLikel_createUser(String likel_createUser)
        {
            this.likel_createUser = likel_createUser;
            return this;
        }

        public String getNotlike_createUser()
        {
            return notlike_createUser;
        }

        public RolePermissionSearchBO setNotlike_createUser(String notlike_createUser)
        {
            this.notlike_createUser = notlike_createUser;
            return this;
        }

        public String getNotliker_createUser()
        {
            return notliker_createUser;
        }

        public RolePermissionSearchBO setNotliker_createUser(String notliker_createUser)
        {
            this.notliker_createUser = notliker_createUser;
            return this;
        }

        public String getNotlikel_createUser()
        {
            return notlikel_createUser;
        }

        public RolePermissionSearchBO setNotlikel_createUser(String notlikel_createUser)
        {
            this.notlikel_createUser = notlikel_createUser;
            return this;
        }

        public String getIn_createUser()
        {
            return in_createUser;
        }

        public RolePermissionSearchBO setIn_createUser(String in_createUser)
        {
            this.in_createUser = in_createUser;
            return this;
        }

        public String getNotin_createUser()
        {
            return notin_createUser;
        }

        public void setNotin_createUser(String notin_createUser)
        {
            this.notin_createUser = notin_createUser;
        }

        public BigInteger getEq_createUserId()
        {
            return eq_createUserId;
        }

        public RolePermissionSearchBO setEq_createUserId(BigInteger eq_createUserId)
        {
            this.eq_createUserId = eq_createUserId;
            return this;
        }

        public BigInteger getNeq_createUserId()
        {
            return neq_createUserId;
        }

        public RolePermissionSearchBO setNeq_createUserId(BigInteger neq_createUserId)
        {
            this.neq_createUserId = neq_createUserId;
            return this;
        }
		
        public String getLike_createUserId()
        {
            return like_createUserId;
        }

        public RolePermissionSearchBO setLike_createUserId(String like_createUserId)
        {
            this.like_createUserId = like_createUserId;
            return this;
        }

        public String getLiker_createUserId()
        {
            return liker_createUserId;
        }

        public RolePermissionSearchBO setLiker_createUserId(String liker_createUserId)
        {
            this.liker_createUserId = liker_createUserId;
            return this;
        }

        public String getLikel_createUserId()
        {
            return likel_createUserId;
        }

        public RolePermissionSearchBO setLikel_createUserId(String likel_createUserId)
        {
            this.likel_createUserId = likel_createUserId;
            return this;
        }

        public String getNotlike_createUserId()
        {
            return notlike_createUserId;
        }

        public RolePermissionSearchBO setNotlike_createUserId(String notlike_createUserId)
        {
            this.notlike_createUserId = notlike_createUserId;
            return this;
        }

        public String getNotliker_createUserId()
        {
            return notliker_createUserId;
        }

        public RolePermissionSearchBO setNotliker_createUserId(String notliker_createUserId)
        {
            this.notliker_createUserId = notliker_createUserId;
            return this;
        }

        public String getNotlikel_createUserId()
        {
            return notlikel_createUserId;
        }

        public RolePermissionSearchBO setNotlikel_createUserId(String notlikel_createUserId)
        {
            this.notlikel_createUserId = notlikel_createUserId;
            return this;
        }

        public String getIn_createUserId()
        {
            return in_createUserId;
        }

        public RolePermissionSearchBO setIn_createUserId(String in_createUserId)
        {
            this.in_createUserId = in_createUserId;
            return this;
        }

        public String getNotin_createUserId()
        {
            return notin_createUserId;
        }

        public void setNotin_createUserId(String notin_createUserId)
        {
            this.notin_createUserId = notin_createUserId;
        }
}
