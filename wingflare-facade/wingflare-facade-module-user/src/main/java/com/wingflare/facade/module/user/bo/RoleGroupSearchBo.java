package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.util.Date;

/**
 * RoleGroupSearchBo
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:42:56 CST 2023
 */
public class RoleGroupSearchBo extends BaseSearchBo
{

        private String eq_roleGroupId;

        private String neq_roleGroupId;
		
        private String in_roleGroupId;

        private String notin_roleGroupId;

        private String eq_groupName;

        private String neq_groupName;
		
		private String like_groupName;

        private String liker_groupName;

        private String likel_groupName;

        private String notlike_groupName;

        private String notliker_groupName;

        private String notlikel_groupName;
		
        private String in_groupName;

        private String notin_groupName;

        private String eq_groupRemark;

        private String neq_groupRemark;
		
		private String like_groupRemark;

        private String liker_groupRemark;

        private String likel_groupRemark;

        private String notlike_groupRemark;

        private String notliker_groupRemark;

        private String notlikel_groupRemark;
		
        private String in_groupRemark;

        private String notin_groupRemark;

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

        private String eq_updateUserId;

        private String neq_updateUserId;
		
		private String like_updateUserId;

        private String liker_updateUserId;

        private String likel_updateUserId;

        private String notlike_updateUserId;

        private String notliker_updateUserId;

        private String notlikel_updateUserId;
		
        private String in_updateUserId;

        private String notin_updateUserId;


        public String getEq_roleGroupId()
        {
            return eq_roleGroupId;
        }

        public RoleGroupSearchBo setEq_roleGroupId(String eq_roleGroupId)
        {
            this.eq_roleGroupId = eq_roleGroupId;
            return this;
        }

        public String getNeq_roleGroupId()
        {
            return neq_roleGroupId;
        }

        public RoleGroupSearchBo setNeq_roleGroupId(String neq_roleGroupId)
        {
            this.neq_roleGroupId = neq_roleGroupId;
            return this;
        }

        public String getIn_roleGroupId()
        {
            return in_roleGroupId;
        }

        public RoleGroupSearchBo setIn_roleGroupId(String in_roleGroupId)
        {
            this.in_roleGroupId = in_roleGroupId;
            return this;
        }

        public String getNotin_roleGroupId()
        {
            return notin_roleGroupId;
        }

        public void setNotin_roleGroupId(String notin_roleGroupId)
        {
            this.notin_roleGroupId = notin_roleGroupId;
        }

        public String getEq_groupName()
        {
            return eq_groupName;
        }

        public RoleGroupSearchBo setEq_groupName(String eq_groupName)
        {
            this.eq_groupName = eq_groupName;
            return this;
        }

        public String getNeq_groupName()
        {
            return neq_groupName;
        }

        public RoleGroupSearchBo setNeq_groupName(String neq_groupName)
        {
            this.neq_groupName = neq_groupName;
            return this;
        }
		
        public String getLike_groupName()
        {
            return like_groupName;
        }

        public RoleGroupSearchBo setLike_groupName(String like_groupName)
        {
            this.like_groupName = like_groupName;
            return this;
        }

        public String getLiker_groupName()
        {
            return liker_groupName;
        }

        public RoleGroupSearchBo setLiker_groupName(String liker_groupName)
        {
            this.liker_groupName = liker_groupName;
            return this;
        }

        public String getLikel_groupName()
        {
            return likel_groupName;
        }

        public RoleGroupSearchBo setLikel_groupName(String likel_groupName)
        {
            this.likel_groupName = likel_groupName;
            return this;
        }

        public String getNotlike_groupName()
        {
            return notlike_groupName;
        }

        public RoleGroupSearchBo setNotlike_groupName(String notlike_groupName)
        {
            this.notlike_groupName = notlike_groupName;
            return this;
        }

        public String getNotliker_groupName()
        {
            return notliker_groupName;
        }

        public RoleGroupSearchBo setNotliker_groupName(String notliker_groupName)
        {
            this.notliker_groupName = notliker_groupName;
            return this;
        }

        public String getNotlikel_groupName()
        {
            return notlikel_groupName;
        }

        public RoleGroupSearchBo setNotlikel_groupName(String notlikel_groupName)
        {
            this.notlikel_groupName = notlikel_groupName;
            return this;
        }

        public String getIn_groupName()
        {
            return in_groupName;
        }

        public RoleGroupSearchBo setIn_groupName(String in_groupName)
        {
            this.in_groupName = in_groupName;
            return this;
        }

        public String getNotin_groupName()
        {
            return notin_groupName;
        }

        public void setNotin_groupName(String notin_groupName)
        {
            this.notin_groupName = notin_groupName;
        }

        public String getEq_groupRemark()
        {
            return eq_groupRemark;
        }

        public RoleGroupSearchBo setEq_groupRemark(String eq_groupRemark)
        {
            this.eq_groupRemark = eq_groupRemark;
            return this;
        }

        public String getNeq_groupRemark()
        {
            return neq_groupRemark;
        }

        public RoleGroupSearchBo setNeq_groupRemark(String neq_groupRemark)
        {
            this.neq_groupRemark = neq_groupRemark;
            return this;
        }
		
        public String getLike_groupRemark()
        {
            return like_groupRemark;
        }

        public RoleGroupSearchBo setLike_groupRemark(String like_groupRemark)
        {
            this.like_groupRemark = like_groupRemark;
            return this;
        }

        public String getLiker_groupRemark()
        {
            return liker_groupRemark;
        }

        public RoleGroupSearchBo setLiker_groupRemark(String liker_groupRemark)
        {
            this.liker_groupRemark = liker_groupRemark;
            return this;
        }

        public String getLikel_groupRemark()
        {
            return likel_groupRemark;
        }

        public RoleGroupSearchBo setLikel_groupRemark(String likel_groupRemark)
        {
            this.likel_groupRemark = likel_groupRemark;
            return this;
        }

        public String getNotlike_groupRemark()
        {
            return notlike_groupRemark;
        }

        public RoleGroupSearchBo setNotlike_groupRemark(String notlike_groupRemark)
        {
            this.notlike_groupRemark = notlike_groupRemark;
            return this;
        }

        public String getNotliker_groupRemark()
        {
            return notliker_groupRemark;
        }

        public RoleGroupSearchBo setNotliker_groupRemark(String notliker_groupRemark)
        {
            this.notliker_groupRemark = notliker_groupRemark;
            return this;
        }

        public String getNotlikel_groupRemark()
        {
            return notlikel_groupRemark;
        }

        public RoleGroupSearchBo setNotlikel_groupRemark(String notlikel_groupRemark)
        {
            this.notlikel_groupRemark = notlikel_groupRemark;
            return this;
        }

        public String getIn_groupRemark()
        {
            return in_groupRemark;
        }

        public RoleGroupSearchBo setIn_groupRemark(String in_groupRemark)
        {
            this.in_groupRemark = in_groupRemark;
            return this;
        }

        public String getNotin_groupRemark()
        {
            return notin_groupRemark;
        }

        public void setNotin_groupRemark(String notin_groupRemark)
        {
            this.notin_groupRemark = notin_groupRemark;
        }

        public Date getEq_createdTime()
        {
            return eq_createdTime;
        }

        public RoleGroupSearchBo setEq_createdTime(Date eq_createdTime)
        {
            this.eq_createdTime = eq_createdTime;
            return this;
        }

        public Date getNeq_createdTime()
        {
            return neq_createdTime;
        }

        public RoleGroupSearchBo setNeq_createdTime(Date neq_createdTime)
        {
            this.neq_createdTime = neq_createdTime;
            return this;
        }
		
        public Date getGt_createdTime()
        {
            return gt_createdTime;
        }

        public RoleGroupSearchBo setGt_createdTime(Date gt_createdTime)
        {
            this.gt_createdTime = gt_createdTime;
            return this;
        }

        public Date getLt_createdTime()
        {
            return lt_createdTime;
        }

        public RoleGroupSearchBo setLt_createdTime(Date lt_createdTime)
        {
            this.lt_createdTime = lt_createdTime;
            return this;
        }

        public Date getEgt_createdTime()
        {
            return egt_createdTime;
        }

        public RoleGroupSearchBo setEgt_createdTime(Date egt_createdTime)
        {
            this.egt_createdTime = egt_createdTime;
            return this;
        }

        public Date getElt_createdTime()
        {
            return elt_createdTime;
        }

        public RoleGroupSearchBo setElt_createdTime(Date elt_createdTime)
        {
            this.elt_createdTime = elt_createdTime;
            return this;
        }
		
		public String getBetween_createdTime()
        {
            return between_createdTime;
        }

        public RoleGroupSearchBo setBetween_createdTime(String between_createdTime)
        {
            this.between_createdTime = between_createdTime;
            return this;
        }

        public String getNotbetween_createdTime()
        {
            return notbetween_createdTime;
        }

        public RoleGroupSearchBo setNotbetween_createdTime(String notbetween_createdTime)
        {
            this.notbetween_createdTime = notbetween_createdTime;
            return this;
        }

        public String getIn_createdTime()
        {
            return in_createdTime;
        }

        public RoleGroupSearchBo setIn_createdTime(String in_createdTime)
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

        public Date getEq_updatedTime()
        {
            return eq_updatedTime;
        }

        public RoleGroupSearchBo setEq_updatedTime(Date eq_updatedTime)
        {
            this.eq_updatedTime = eq_updatedTime;
            return this;
        }

        public Date getNeq_updatedTime()
        {
            return neq_updatedTime;
        }

        public RoleGroupSearchBo setNeq_updatedTime(Date neq_updatedTime)
        {
            this.neq_updatedTime = neq_updatedTime;
            return this;
        }
		
        public Date getGt_updatedTime()
        {
            return gt_updatedTime;
        }

        public RoleGroupSearchBo setGt_updatedTime(Date gt_updatedTime)
        {
            this.gt_updatedTime = gt_updatedTime;
            return this;
        }

        public Date getLt_updatedTime()
        {
            return lt_updatedTime;
        }

        public RoleGroupSearchBo setLt_updatedTime(Date lt_updatedTime)
        {
            this.lt_updatedTime = lt_updatedTime;
            return this;
        }

        public Date getEgt_updatedTime()
        {
            return egt_updatedTime;
        }

        public RoleGroupSearchBo setEgt_updatedTime(Date egt_updatedTime)
        {
            this.egt_updatedTime = egt_updatedTime;
            return this;
        }

        public Date getElt_updatedTime()
        {
            return elt_updatedTime;
        }

        public RoleGroupSearchBo setElt_updatedTime(Date elt_updatedTime)
        {
            this.elt_updatedTime = elt_updatedTime;
            return this;
        }
		
		public String getBetween_updatedTime()
        {
            return between_updatedTime;
        }

        public RoleGroupSearchBo setBetween_updatedTime(String between_updatedTime)
        {
            this.between_updatedTime = between_updatedTime;
            return this;
        }

        public String getNotbetween_updatedTime()
        {
            return notbetween_updatedTime;
        }

        public RoleGroupSearchBo setNotbetween_updatedTime(String notbetween_updatedTime)
        {
            this.notbetween_updatedTime = notbetween_updatedTime;
            return this;
        }

        public String getIn_updatedTime()
        {
            return in_updatedTime;
        }

        public RoleGroupSearchBo setIn_updatedTime(String in_updatedTime)
        {
            this.in_updatedTime = in_updatedTime;
            return this;
        }

        public String getNotin_updatedTime()
        {
            return notin_updatedTime;
        }

        public void setNotin_updatedTime(String notin_updatedTime)
        {
            this.notin_updatedTime = notin_updatedTime;
        }

        public String getEq_createUser()
        {
            return eq_createUser;
        }

        public RoleGroupSearchBo setEq_createUser(String eq_createUser)
        {
            this.eq_createUser = eq_createUser;
            return this;
        }

        public String getNeq_createUser()
        {
            return neq_createUser;
        }

        public RoleGroupSearchBo setNeq_createUser(String neq_createUser)
        {
            this.neq_createUser = neq_createUser;
            return this;
        }
		
        public String getLike_createUser()
        {
            return like_createUser;
        }

        public RoleGroupSearchBo setLike_createUser(String like_createUser)
        {
            this.like_createUser = like_createUser;
            return this;
        }

        public String getLiker_createUser()
        {
            return liker_createUser;
        }

        public RoleGroupSearchBo setLiker_createUser(String liker_createUser)
        {
            this.liker_createUser = liker_createUser;
            return this;
        }

        public String getLikel_createUser()
        {
            return likel_createUser;
        }

        public RoleGroupSearchBo setLikel_createUser(String likel_createUser)
        {
            this.likel_createUser = likel_createUser;
            return this;
        }

        public String getNotlike_createUser()
        {
            return notlike_createUser;
        }

        public RoleGroupSearchBo setNotlike_createUser(String notlike_createUser)
        {
            this.notlike_createUser = notlike_createUser;
            return this;
        }

        public String getNotliker_createUser()
        {
            return notliker_createUser;
        }

        public RoleGroupSearchBo setNotliker_createUser(String notliker_createUser)
        {
            this.notliker_createUser = notliker_createUser;
            return this;
        }

        public String getNotlikel_createUser()
        {
            return notlikel_createUser;
        }

        public RoleGroupSearchBo setNotlikel_createUser(String notlikel_createUser)
        {
            this.notlikel_createUser = notlikel_createUser;
            return this;
        }

        public String getIn_createUser()
        {
            return in_createUser;
        }

        public RoleGroupSearchBo setIn_createUser(String in_createUser)
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

        public RoleGroupSearchBo setEq_createUserId(String eq_createUserId)
        {
            this.eq_createUserId = eq_createUserId;
            return this;
        }

        public String getNeq_createUserId()
        {
            return neq_createUserId;
        }

        public RoleGroupSearchBo setNeq_createUserId(String neq_createUserId)
        {
            this.neq_createUserId = neq_createUserId;
            return this;
        }
		
        public String getLike_createUserId()
        {
            return like_createUserId;
        }

        public RoleGroupSearchBo setLike_createUserId(String like_createUserId)
        {
            this.like_createUserId = like_createUserId;
            return this;
        }

        public String getLiker_createUserId()
        {
            return liker_createUserId;
        }

        public RoleGroupSearchBo setLiker_createUserId(String liker_createUserId)
        {
            this.liker_createUserId = liker_createUserId;
            return this;
        }

        public String getLikel_createUserId()
        {
            return likel_createUserId;
        }

        public RoleGroupSearchBo setLikel_createUserId(String likel_createUserId)
        {
            this.likel_createUserId = likel_createUserId;
            return this;
        }

        public String getNotlike_createUserId()
        {
            return notlike_createUserId;
        }

        public RoleGroupSearchBo setNotlike_createUserId(String notlike_createUserId)
        {
            this.notlike_createUserId = notlike_createUserId;
            return this;
        }

        public String getNotliker_createUserId()
        {
            return notliker_createUserId;
        }

        public RoleGroupSearchBo setNotliker_createUserId(String notliker_createUserId)
        {
            this.notliker_createUserId = notliker_createUserId;
            return this;
        }

        public String getNotlikel_createUserId()
        {
            return notlikel_createUserId;
        }

        public RoleGroupSearchBo setNotlikel_createUserId(String notlikel_createUserId)
        {
            this.notlikel_createUserId = notlikel_createUserId;
            return this;
        }

        public String getIn_createUserId()
        {
            return in_createUserId;
        }

        public RoleGroupSearchBo setIn_createUserId(String in_createUserId)
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

        public String getEq_updateUser()
        {
            return eq_updateUser;
        }

        public RoleGroupSearchBo setEq_updateUser(String eq_updateUser)
        {
            this.eq_updateUser = eq_updateUser;
            return this;
        }

        public String getNeq_updateUser()
        {
            return neq_updateUser;
        }

        public RoleGroupSearchBo setNeq_updateUser(String neq_updateUser)
        {
            this.neq_updateUser = neq_updateUser;
            return this;
        }
		
        public String getLike_updateUser()
        {
            return like_updateUser;
        }

        public RoleGroupSearchBo setLike_updateUser(String like_updateUser)
        {
            this.like_updateUser = like_updateUser;
            return this;
        }

        public String getLiker_updateUser()
        {
            return liker_updateUser;
        }

        public RoleGroupSearchBo setLiker_updateUser(String liker_updateUser)
        {
            this.liker_updateUser = liker_updateUser;
            return this;
        }

        public String getLikel_updateUser()
        {
            return likel_updateUser;
        }

        public RoleGroupSearchBo setLikel_updateUser(String likel_updateUser)
        {
            this.likel_updateUser = likel_updateUser;
            return this;
        }

        public String getNotlike_updateUser()
        {
            return notlike_updateUser;
        }

        public RoleGroupSearchBo setNotlike_updateUser(String notlike_updateUser)
        {
            this.notlike_updateUser = notlike_updateUser;
            return this;
        }

        public String getNotliker_updateUser()
        {
            return notliker_updateUser;
        }

        public RoleGroupSearchBo setNotliker_updateUser(String notliker_updateUser)
        {
            this.notliker_updateUser = notliker_updateUser;
            return this;
        }

        public String getNotlikel_updateUser()
        {
            return notlikel_updateUser;
        }

        public RoleGroupSearchBo setNotlikel_updateUser(String notlikel_updateUser)
        {
            this.notlikel_updateUser = notlikel_updateUser;
            return this;
        }

        public String getIn_updateUser()
        {
            return in_updateUser;
        }

        public RoleGroupSearchBo setIn_updateUser(String in_updateUser)
        {
            this.in_updateUser = in_updateUser;
            return this;
        }

        public String getNotin_updateUser()
        {
            return notin_updateUser;
        }

        public void setNotin_updateUser(String notin_updateUser)
        {
            this.notin_updateUser = notin_updateUser;
        }

        public String getEq_updateUserId()
        {
            return eq_updateUserId;
        }

        public RoleGroupSearchBo setEq_updateUserId(String eq_updateUserId)
        {
            this.eq_updateUserId = eq_updateUserId;
            return this;
        }

        public String getNeq_updateUserId()
        {
            return neq_updateUserId;
        }

        public RoleGroupSearchBo setNeq_updateUserId(String neq_updateUserId)
        {
            this.neq_updateUserId = neq_updateUserId;
            return this;
        }
		
        public String getLike_updateUserId()
        {
            return like_updateUserId;
        }

        public RoleGroupSearchBo setLike_updateUserId(String like_updateUserId)
        {
            this.like_updateUserId = like_updateUserId;
            return this;
        }

        public String getLiker_updateUserId()
        {
            return liker_updateUserId;
        }

        public RoleGroupSearchBo setLiker_updateUserId(String liker_updateUserId)
        {
            this.liker_updateUserId = liker_updateUserId;
            return this;
        }

        public String getLikel_updateUserId()
        {
            return likel_updateUserId;
        }

        public RoleGroupSearchBo setLikel_updateUserId(String likel_updateUserId)
        {
            this.likel_updateUserId = likel_updateUserId;
            return this;
        }

        public String getNotlike_updateUserId()
        {
            return notlike_updateUserId;
        }

        public RoleGroupSearchBo setNotlike_updateUserId(String notlike_updateUserId)
        {
            this.notlike_updateUserId = notlike_updateUserId;
            return this;
        }

        public String getNotliker_updateUserId()
        {
            return notliker_updateUserId;
        }

        public RoleGroupSearchBo setNotliker_updateUserId(String notliker_updateUserId)
        {
            this.notliker_updateUserId = notliker_updateUserId;
            return this;
        }

        public String getNotlikel_updateUserId()
        {
            return notlikel_updateUserId;
        }

        public RoleGroupSearchBo setNotlikel_updateUserId(String notlikel_updateUserId)
        {
            this.notlikel_updateUserId = notlikel_updateUserId;
            return this;
        }

        public String getIn_updateUserId()
        {
            return in_updateUserId;
        }

        public RoleGroupSearchBo setIn_updateUserId(String in_updateUserId)
        {
            this.in_updateUserId = in_updateUserId;
            return this;
        }

        public String getNotin_updateUserId()
        {
            return notin_updateUserId;
        }

        public void setNotin_updateUserId(String notin_updateUserId)
        {
            this.notin_updateUserId = notin_updateUserId;
        }
}
