package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.util.Date;

/**
 * RoleMutexSearchBo
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:09:10 CST 2023
 */
public class RoleMutexSearchBo extends BaseSearchBo
{

        private String eq_id;

        private String neq_id;
		
        private String in_id;

        private String notin_id;

        private String eq_roleId;

        private String neq_roleId;
		
		private String like_roleId;

        private String liker_roleId;

        private String likel_roleId;

        private String notlike_roleId;

        private String notliker_roleId;

        private String notlikel_roleId;
		
        private String in_roleId;

        private String notin_roleId;

        private String eq_mutexRoleId;

        private String neq_mutexRoleId;
		
		private String like_mutexRoleId;

        private String liker_mutexRoleId;

        private String likel_mutexRoleId;

        private String notlike_mutexRoleId;

        private String notliker_mutexRoleId;

        private String notlikel_mutexRoleId;
		
        private String in_mutexRoleId;

        private String notin_mutexRoleId;

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

        private String eq_createUserId;

        private String neq_createUserId;
		
		private String like_createUserId;

        private String liker_createUserId;

        private String likel_createUserId;

        private String notlike_createUserId;

        private String notliker_createUserId;

        private String notlikel_createUserId;
		
        private String in_createUserId;

        private String notin_createUserId;


        public String getEq_id()
        {
            return eq_id;
        }

        public RoleMutexSearchBo setEq_id(String eq_id)
        {
            this.eq_id = eq_id;
            return this;
        }

        public String getNeq_id()
        {
            return neq_id;
        }

        public RoleMutexSearchBo setNeq_id(String neq_id)
        {
            this.neq_id = neq_id;
            return this;
        }

        public String getIn_id()
        {
            return in_id;
        }

        public RoleMutexSearchBo setIn_id(String in_id)
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

        public String getEq_roleId()
        {
            return eq_roleId;
        }

        public RoleMutexSearchBo setEq_roleId(String eq_roleId)
        {
            this.eq_roleId = eq_roleId;
            return this;
        }

        public String getNeq_roleId()
        {
            return neq_roleId;
        }

        public RoleMutexSearchBo setNeq_roleId(String neq_roleId)
        {
            this.neq_roleId = neq_roleId;
            return this;
        }
		
        public String getLike_roleId()
        {
            return like_roleId;
        }

        public RoleMutexSearchBo setLike_roleId(String like_roleId)
        {
            this.like_roleId = like_roleId;
            return this;
        }

        public String getLiker_roleId()
        {
            return liker_roleId;
        }

        public RoleMutexSearchBo setLiker_roleId(String liker_roleId)
        {
            this.liker_roleId = liker_roleId;
            return this;
        }

        public String getLikel_roleId()
        {
            return likel_roleId;
        }

        public RoleMutexSearchBo setLikel_roleId(String likel_roleId)
        {
            this.likel_roleId = likel_roleId;
            return this;
        }

        public String getNotlike_roleId()
        {
            return notlike_roleId;
        }

        public RoleMutexSearchBo setNotlike_roleId(String notlike_roleId)
        {
            this.notlike_roleId = notlike_roleId;
            return this;
        }

        public String getNotliker_roleId()
        {
            return notliker_roleId;
        }

        public RoleMutexSearchBo setNotliker_roleId(String notliker_roleId)
        {
            this.notliker_roleId = notliker_roleId;
            return this;
        }

        public String getNotlikel_roleId()
        {
            return notlikel_roleId;
        }

        public RoleMutexSearchBo setNotlikel_roleId(String notlikel_roleId)
        {
            this.notlikel_roleId = notlikel_roleId;
            return this;
        }

        public String getIn_roleId()
        {
            return in_roleId;
        }

        public RoleMutexSearchBo setIn_roleId(String in_roleId)
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

        public String getEq_mutexRoleId()
        {
            return eq_mutexRoleId;
        }

        public RoleMutexSearchBo setEq_mutexRoleId(String eq_mutexRoleId)
        {
            this.eq_mutexRoleId = eq_mutexRoleId;
            return this;
        }

        public String getNeq_mutexRoleId()
        {
            return neq_mutexRoleId;
        }

        public RoleMutexSearchBo setNeq_mutexRoleId(String neq_mutexRoleId)
        {
            this.neq_mutexRoleId = neq_mutexRoleId;
            return this;
        }
		
        public String getLike_mutexRoleId()
        {
            return like_mutexRoleId;
        }

        public RoleMutexSearchBo setLike_mutexRoleId(String like_mutexRoleId)
        {
            this.like_mutexRoleId = like_mutexRoleId;
            return this;
        }

        public String getLiker_mutexRoleId()
        {
            return liker_mutexRoleId;
        }

        public RoleMutexSearchBo setLiker_mutexRoleId(String liker_mutexRoleId)
        {
            this.liker_mutexRoleId = liker_mutexRoleId;
            return this;
        }

        public String getLikel_mutexRoleId()
        {
            return likel_mutexRoleId;
        }

        public RoleMutexSearchBo setLikel_mutexRoleId(String likel_mutexRoleId)
        {
            this.likel_mutexRoleId = likel_mutexRoleId;
            return this;
        }

        public String getNotlike_mutexRoleId()
        {
            return notlike_mutexRoleId;
        }

        public RoleMutexSearchBo setNotlike_mutexRoleId(String notlike_mutexRoleId)
        {
            this.notlike_mutexRoleId = notlike_mutexRoleId;
            return this;
        }

        public String getNotliker_mutexRoleId()
        {
            return notliker_mutexRoleId;
        }

        public RoleMutexSearchBo setNotliker_mutexRoleId(String notliker_mutexRoleId)
        {
            this.notliker_mutexRoleId = notliker_mutexRoleId;
            return this;
        }

        public String getNotlikel_mutexRoleId()
        {
            return notlikel_mutexRoleId;
        }

        public RoleMutexSearchBo setNotlikel_mutexRoleId(String notlikel_mutexRoleId)
        {
            this.notlikel_mutexRoleId = notlikel_mutexRoleId;
            return this;
        }

        public String getIn_mutexRoleId()
        {
            return in_mutexRoleId;
        }

        public RoleMutexSearchBo setIn_mutexRoleId(String in_mutexRoleId)
        {
            this.in_mutexRoleId = in_mutexRoleId;
            return this;
        }

        public String getNotin_mutexRoleId()
        {
            return notin_mutexRoleId;
        }

        public void setNotin_mutexRoleId(String notin_mutexRoleId)
        {
            this.notin_mutexRoleId = notin_mutexRoleId;
        }

        public Date getEq_createdTime()
        {
            return eq_createdTime;
        }

        public RoleMutexSearchBo setEq_createdTime(Date eq_createdTime)
        {
            this.eq_createdTime = eq_createdTime;
            return this;
        }

        public Date getNeq_createdTime()
        {
            return neq_createdTime;
        }

        public RoleMutexSearchBo setNeq_createdTime(Date neq_createdTime)
        {
            this.neq_createdTime = neq_createdTime;
            return this;
        }
		
        public Date getGt_createdTime()
        {
            return gt_createdTime;
        }

        public RoleMutexSearchBo setGt_createdTime(Date gt_createdTime)
        {
            this.gt_createdTime = gt_createdTime;
            return this;
        }

        public Date getLt_createdTime()
        {
            return lt_createdTime;
        }

        public RoleMutexSearchBo setLt_createdTime(Date lt_createdTime)
        {
            this.lt_createdTime = lt_createdTime;
            return this;
        }

        public Date getEgt_createdTime()
        {
            return egt_createdTime;
        }

        public RoleMutexSearchBo setEgt_createdTime(Date egt_createdTime)
        {
            this.egt_createdTime = egt_createdTime;
            return this;
        }

        public Date getElt_createdTime()
        {
            return elt_createdTime;
        }

        public RoleMutexSearchBo setElt_createdTime(Date elt_createdTime)
        {
            this.elt_createdTime = elt_createdTime;
            return this;
        }
		
		public String getBetween_createdTime()
        {
            return between_createdTime;
        }

        public RoleMutexSearchBo setBetween_createdTime(String between_createdTime)
        {
            this.between_createdTime = between_createdTime;
            return this;
        }

        public String getNotbetween_createdTime()
        {
            return notbetween_createdTime;
        }

        public RoleMutexSearchBo setNotbetween_createdTime(String notbetween_createdTime)
        {
            this.notbetween_createdTime = notbetween_createdTime;
            return this;
        }

        public String getIn_createdTime()
        {
            return in_createdTime;
        }

        public RoleMutexSearchBo setIn_createdTime(String in_createdTime)
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

        public RoleMutexSearchBo setEq_createUser(String eq_createUser)
        {
            this.eq_createUser = eq_createUser;
            return this;
        }

        public String getNeq_createUser()
        {
            return neq_createUser;
        }

        public RoleMutexSearchBo setNeq_createUser(String neq_createUser)
        {
            this.neq_createUser = neq_createUser;
            return this;
        }
		
        public String getLike_createUser()
        {
            return like_createUser;
        }

        public RoleMutexSearchBo setLike_createUser(String like_createUser)
        {
            this.like_createUser = like_createUser;
            return this;
        }

        public String getLiker_createUser()
        {
            return liker_createUser;
        }

        public RoleMutexSearchBo setLiker_createUser(String liker_createUser)
        {
            this.liker_createUser = liker_createUser;
            return this;
        }

        public String getLikel_createUser()
        {
            return likel_createUser;
        }

        public RoleMutexSearchBo setLikel_createUser(String likel_createUser)
        {
            this.likel_createUser = likel_createUser;
            return this;
        }

        public String getNotlike_createUser()
        {
            return notlike_createUser;
        }

        public RoleMutexSearchBo setNotlike_createUser(String notlike_createUser)
        {
            this.notlike_createUser = notlike_createUser;
            return this;
        }

        public String getNotliker_createUser()
        {
            return notliker_createUser;
        }

        public RoleMutexSearchBo setNotliker_createUser(String notliker_createUser)
        {
            this.notliker_createUser = notliker_createUser;
            return this;
        }

        public String getNotlikel_createUser()
        {
            return notlikel_createUser;
        }

        public RoleMutexSearchBo setNotlikel_createUser(String notlikel_createUser)
        {
            this.notlikel_createUser = notlikel_createUser;
            return this;
        }

        public String getIn_createUser()
        {
            return in_createUser;
        }

        public RoleMutexSearchBo setIn_createUser(String in_createUser)
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

        public String getEq_createUserId()
        {
            return eq_createUserId;
        }

        public RoleMutexSearchBo setEq_createUserId(String eq_createUserId)
        {
            this.eq_createUserId = eq_createUserId;
            return this;
        }

        public String getNeq_createUserId()
        {
            return neq_createUserId;
        }

        public RoleMutexSearchBo setNeq_createUserId(String neq_createUserId)
        {
            this.neq_createUserId = neq_createUserId;
            return this;
        }
		
        public String getLike_createUserId()
        {
            return like_createUserId;
        }

        public RoleMutexSearchBo setLike_createUserId(String like_createUserId)
        {
            this.like_createUserId = like_createUserId;
            return this;
        }

        public String getLiker_createUserId()
        {
            return liker_createUserId;
        }

        public RoleMutexSearchBo setLiker_createUserId(String liker_createUserId)
        {
            this.liker_createUserId = liker_createUserId;
            return this;
        }

        public String getLikel_createUserId()
        {
            return likel_createUserId;
        }

        public RoleMutexSearchBo setLikel_createUserId(String likel_createUserId)
        {
            this.likel_createUserId = likel_createUserId;
            return this;
        }

        public String getNotlike_createUserId()
        {
            return notlike_createUserId;
        }

        public RoleMutexSearchBo setNotlike_createUserId(String notlike_createUserId)
        {
            this.notlike_createUserId = notlike_createUserId;
            return this;
        }

        public String getNotliker_createUserId()
        {
            return notliker_createUserId;
        }

        public RoleMutexSearchBo setNotliker_createUserId(String notliker_createUserId)
        {
            this.notliker_createUserId = notliker_createUserId;
            return this;
        }

        public String getNotlikel_createUserId()
        {
            return notlikel_createUserId;
        }

        public RoleMutexSearchBo setNotlikel_createUserId(String notlikel_createUserId)
        {
            this.notlikel_createUserId = notlikel_createUserId;
            return this;
        }

        public String getIn_createUserId()
        {
            return in_createUserId;
        }

        public RoleMutexSearchBo setIn_createUserId(String in_createUserId)
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
