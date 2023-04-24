package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.util.Date;

/**
 * OrgSearchBo
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:36:56 CST 2023
 */
public class OrgSearchBo extends BaseSearchBo
{

        private String eq_orgId;

        private String neq_orgId;
		
        private String in_orgId;

        private String notin_orgId;

        private String eq_parentOrgId;

        private String neq_parentOrgId;
		
		private String like_parentOrgId;

        private String liker_parentOrgId;

        private String likel_parentOrgId;

        private String notlike_parentOrgId;

        private String notliker_parentOrgId;

        private String notlikel_parentOrgId;
		
        private String in_parentOrgId;

        private String notin_parentOrgId;

        private Integer eq_state;

        private Integer neq_state;
		
        private Integer gt_state;

        private Integer lt_state;

        private Integer egt_state;

        private Integer elt_state;
		
		private String between_state;

        private String notbetween_state;
		
        private String in_state;

        private String notin_state;

        private String eq_orgType;

        private String neq_orgType;
		
		private String like_orgType;

        private String liker_orgType;

        private String likel_orgType;

        private String notlike_orgType;

        private String notliker_orgType;

        private String notlikel_orgType;
		
        private String in_orgType;

        private String notin_orgType;

        private String eq_orgName;

        private String neq_orgName;
		
		private String like_orgName;

        private String liker_orgName;

        private String likel_orgName;

        private String notlike_orgName;

        private String notliker_orgName;

        private String notlikel_orgName;
		
        private String in_orgName;

        private String notin_orgName;

        private String eq_orgPhone;

        private String neq_orgPhone;
		
		private String like_orgPhone;

        private String liker_orgPhone;

        private String likel_orgPhone;

        private String notlike_orgPhone;

        private String notliker_orgPhone;

        private String notlikel_orgPhone;
		
        private String in_orgPhone;

        private String notin_orgPhone;

        private String eq_orgAddress;

        private String neq_orgAddress;
		
		private String like_orgAddress;

        private String liker_orgAddress;

        private String likel_orgAddress;

        private String notlike_orgAddress;

        private String notliker_orgAddress;

        private String notlikel_orgAddress;
		
        private String in_orgAddress;

        private String notin_orgAddress;

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

        private String eq_userId;

        private String neq_userId;
		
		private String like_userId;

        private String liker_userId;

        private String likel_userId;

        private String notlike_userId;

        private String notliker_userId;

        private String notlikel_userId;
		
        private String in_userId;

        private String notin_userId;

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


        public String getEq_orgId()
        {
            return eq_orgId;
        }

        public OrgSearchBo setEq_orgId(String eq_orgId)
        {
            this.eq_orgId = eq_orgId;
            return this;
        }

        public String getNeq_orgId()
        {
            return neq_orgId;
        }

        public OrgSearchBo setNeq_orgId(String neq_orgId)
        {
            this.neq_orgId = neq_orgId;
            return this;
        }

        public String getIn_orgId()
        {
            return in_orgId;
        }

        public OrgSearchBo setIn_orgId(String in_orgId)
        {
            this.in_orgId = in_orgId;
            return this;
        }

        public String getNotin_orgId()
        {
            return notin_orgId;
        }

        public void setNotin_orgId(String notin_orgId)
        {
            this.notin_orgId = notin_orgId;
        }

        public String getEq_parentOrgId()
        {
            return eq_parentOrgId;
        }

        public OrgSearchBo setEq_parentOrgId(String eq_parentOrgId)
        {
            this.eq_parentOrgId = eq_parentOrgId;
            return this;
        }

        public String getNeq_parentOrgId()
        {
            return neq_parentOrgId;
        }

        public OrgSearchBo setNeq_parentOrgId(String neq_parentOrgId)
        {
            this.neq_parentOrgId = neq_parentOrgId;
            return this;
        }
		
        public String getLike_parentOrgId()
        {
            return like_parentOrgId;
        }

        public OrgSearchBo setLike_parentOrgId(String like_parentOrgId)
        {
            this.like_parentOrgId = like_parentOrgId;
            return this;
        }

        public String getLiker_parentOrgId()
        {
            return liker_parentOrgId;
        }

        public OrgSearchBo setLiker_parentOrgId(String liker_parentOrgId)
        {
            this.liker_parentOrgId = liker_parentOrgId;
            return this;
        }

        public String getLikel_parentOrgId()
        {
            return likel_parentOrgId;
        }

        public OrgSearchBo setLikel_parentOrgId(String likel_parentOrgId)
        {
            this.likel_parentOrgId = likel_parentOrgId;
            return this;
        }

        public String getNotlike_parentOrgId()
        {
            return notlike_parentOrgId;
        }

        public OrgSearchBo setNotlike_parentOrgId(String notlike_parentOrgId)
        {
            this.notlike_parentOrgId = notlike_parentOrgId;
            return this;
        }

        public String getNotliker_parentOrgId()
        {
            return notliker_parentOrgId;
        }

        public OrgSearchBo setNotliker_parentOrgId(String notliker_parentOrgId)
        {
            this.notliker_parentOrgId = notliker_parentOrgId;
            return this;
        }

        public String getNotlikel_parentOrgId()
        {
            return notlikel_parentOrgId;
        }

        public OrgSearchBo setNotlikel_parentOrgId(String notlikel_parentOrgId)
        {
            this.notlikel_parentOrgId = notlikel_parentOrgId;
            return this;
        }

        public String getIn_parentOrgId()
        {
            return in_parentOrgId;
        }

        public OrgSearchBo setIn_parentOrgId(String in_parentOrgId)
        {
            this.in_parentOrgId = in_parentOrgId;
            return this;
        }

        public String getNotin_parentOrgId()
        {
            return notin_parentOrgId;
        }

        public void setNotin_parentOrgId(String notin_parentOrgId)
        {
            this.notin_parentOrgId = notin_parentOrgId;
        }

        public Integer getEq_state()
        {
            return eq_state;
        }

        public OrgSearchBo setEq_state(Integer eq_state)
        {
            this.eq_state = eq_state;
            return this;
        }

        public Integer getNeq_state()
        {
            return neq_state;
        }

        public OrgSearchBo setNeq_state(Integer neq_state)
        {
            this.neq_state = neq_state;
            return this;
        }
		
        public Integer getGt_state()
        {
            return gt_state;
        }

        public OrgSearchBo setGt_state(Integer gt_state)
        {
            this.gt_state = gt_state;
            return this;
        }

        public Integer getLt_state()
        {
            return lt_state;
        }

        public OrgSearchBo setLt_state(Integer lt_state)
        {
            this.lt_state = lt_state;
            return this;
        }

        public Integer getEgt_state()
        {
            return egt_state;
        }

        public OrgSearchBo setEgt_state(Integer egt_state)
        {
            this.egt_state = egt_state;
            return this;
        }

        public Integer getElt_state()
        {
            return elt_state;
        }

        public OrgSearchBo setElt_state(Integer elt_state)
        {
            this.elt_state = elt_state;
            return this;
        }
		
		public String getBetween_state()
        {
            return between_state;
        }

        public OrgSearchBo setBetween_state(String between_state)
        {
            this.between_state = between_state;
            return this;
        }

        public String getNotbetween_state()
        {
            return notbetween_state;
        }

        public OrgSearchBo setNotbetween_state(String notbetween_state)
        {
            this.notbetween_state = notbetween_state;
            return this;
        }

        public String getIn_state()
        {
            return in_state;
        }

        public OrgSearchBo setIn_state(String in_state)
        {
            this.in_state = in_state;
            return this;
        }

        public String getNotin_state()
        {
            return notin_state;
        }

        public void setNotin_state(String notin_state)
        {
            this.notin_state = notin_state;
        }

        public String getEq_orgType()
        {
            return eq_orgType;
        }

        public OrgSearchBo setEq_orgType(String eq_orgType)
        {
            this.eq_orgType = eq_orgType;
            return this;
        }

        public String getNeq_orgType()
        {
            return neq_orgType;
        }

        public OrgSearchBo setNeq_orgType(String neq_orgType)
        {
            this.neq_orgType = neq_orgType;
            return this;
        }
		
        public String getLike_orgType()
        {
            return like_orgType;
        }

        public OrgSearchBo setLike_orgType(String like_orgType)
        {
            this.like_orgType = like_orgType;
            return this;
        }

        public String getLiker_orgType()
        {
            return liker_orgType;
        }

        public OrgSearchBo setLiker_orgType(String liker_orgType)
        {
            this.liker_orgType = liker_orgType;
            return this;
        }

        public String getLikel_orgType()
        {
            return likel_orgType;
        }

        public OrgSearchBo setLikel_orgType(String likel_orgType)
        {
            this.likel_orgType = likel_orgType;
            return this;
        }

        public String getNotlike_orgType()
        {
            return notlike_orgType;
        }

        public OrgSearchBo setNotlike_orgType(String notlike_orgType)
        {
            this.notlike_orgType = notlike_orgType;
            return this;
        }

        public String getNotliker_orgType()
        {
            return notliker_orgType;
        }

        public OrgSearchBo setNotliker_orgType(String notliker_orgType)
        {
            this.notliker_orgType = notliker_orgType;
            return this;
        }

        public String getNotlikel_orgType()
        {
            return notlikel_orgType;
        }

        public OrgSearchBo setNotlikel_orgType(String notlikel_orgType)
        {
            this.notlikel_orgType = notlikel_orgType;
            return this;
        }

        public String getIn_orgType()
        {
            return in_orgType;
        }

        public OrgSearchBo setIn_orgType(String in_orgType)
        {
            this.in_orgType = in_orgType;
            return this;
        }

        public String getNotin_orgType()
        {
            return notin_orgType;
        }

        public void setNotin_orgType(String notin_orgType)
        {
            this.notin_orgType = notin_orgType;
        }

        public String getEq_orgName()
        {
            return eq_orgName;
        }

        public OrgSearchBo setEq_orgName(String eq_orgName)
        {
            this.eq_orgName = eq_orgName;
            return this;
        }

        public String getNeq_orgName()
        {
            return neq_orgName;
        }

        public OrgSearchBo setNeq_orgName(String neq_orgName)
        {
            this.neq_orgName = neq_orgName;
            return this;
        }
		
        public String getLike_orgName()
        {
            return like_orgName;
        }

        public OrgSearchBo setLike_orgName(String like_orgName)
        {
            this.like_orgName = like_orgName;
            return this;
        }

        public String getLiker_orgName()
        {
            return liker_orgName;
        }

        public OrgSearchBo setLiker_orgName(String liker_orgName)
        {
            this.liker_orgName = liker_orgName;
            return this;
        }

        public String getLikel_orgName()
        {
            return likel_orgName;
        }

        public OrgSearchBo setLikel_orgName(String likel_orgName)
        {
            this.likel_orgName = likel_orgName;
            return this;
        }

        public String getNotlike_orgName()
        {
            return notlike_orgName;
        }

        public OrgSearchBo setNotlike_orgName(String notlike_orgName)
        {
            this.notlike_orgName = notlike_orgName;
            return this;
        }

        public String getNotliker_orgName()
        {
            return notliker_orgName;
        }

        public OrgSearchBo setNotliker_orgName(String notliker_orgName)
        {
            this.notliker_orgName = notliker_orgName;
            return this;
        }

        public String getNotlikel_orgName()
        {
            return notlikel_orgName;
        }

        public OrgSearchBo setNotlikel_orgName(String notlikel_orgName)
        {
            this.notlikel_orgName = notlikel_orgName;
            return this;
        }

        public String getIn_orgName()
        {
            return in_orgName;
        }

        public OrgSearchBo setIn_orgName(String in_orgName)
        {
            this.in_orgName = in_orgName;
            return this;
        }

        public String getNotin_orgName()
        {
            return notin_orgName;
        }

        public void setNotin_orgName(String notin_orgName)
        {
            this.notin_orgName = notin_orgName;
        }

        public String getEq_orgPhone()
        {
            return eq_orgPhone;
        }

        public OrgSearchBo setEq_orgPhone(String eq_orgPhone)
        {
            this.eq_orgPhone = eq_orgPhone;
            return this;
        }

        public String getNeq_orgPhone()
        {
            return neq_orgPhone;
        }

        public OrgSearchBo setNeq_orgPhone(String neq_orgPhone)
        {
            this.neq_orgPhone = neq_orgPhone;
            return this;
        }
		
        public String getLike_orgPhone()
        {
            return like_orgPhone;
        }

        public OrgSearchBo setLike_orgPhone(String like_orgPhone)
        {
            this.like_orgPhone = like_orgPhone;
            return this;
        }

        public String getLiker_orgPhone()
        {
            return liker_orgPhone;
        }

        public OrgSearchBo setLiker_orgPhone(String liker_orgPhone)
        {
            this.liker_orgPhone = liker_orgPhone;
            return this;
        }

        public String getLikel_orgPhone()
        {
            return likel_orgPhone;
        }

        public OrgSearchBo setLikel_orgPhone(String likel_orgPhone)
        {
            this.likel_orgPhone = likel_orgPhone;
            return this;
        }

        public String getNotlike_orgPhone()
        {
            return notlike_orgPhone;
        }

        public OrgSearchBo setNotlike_orgPhone(String notlike_orgPhone)
        {
            this.notlike_orgPhone = notlike_orgPhone;
            return this;
        }

        public String getNotliker_orgPhone()
        {
            return notliker_orgPhone;
        }

        public OrgSearchBo setNotliker_orgPhone(String notliker_orgPhone)
        {
            this.notliker_orgPhone = notliker_orgPhone;
            return this;
        }

        public String getNotlikel_orgPhone()
        {
            return notlikel_orgPhone;
        }

        public OrgSearchBo setNotlikel_orgPhone(String notlikel_orgPhone)
        {
            this.notlikel_orgPhone = notlikel_orgPhone;
            return this;
        }

        public String getIn_orgPhone()
        {
            return in_orgPhone;
        }

        public OrgSearchBo setIn_orgPhone(String in_orgPhone)
        {
            this.in_orgPhone = in_orgPhone;
            return this;
        }

        public String getNotin_orgPhone()
        {
            return notin_orgPhone;
        }

        public void setNotin_orgPhone(String notin_orgPhone)
        {
            this.notin_orgPhone = notin_orgPhone;
        }

        public String getEq_orgAddress()
        {
            return eq_orgAddress;
        }

        public OrgSearchBo setEq_orgAddress(String eq_orgAddress)
        {
            this.eq_orgAddress = eq_orgAddress;
            return this;
        }

        public String getNeq_orgAddress()
        {
            return neq_orgAddress;
        }

        public OrgSearchBo setNeq_orgAddress(String neq_orgAddress)
        {
            this.neq_orgAddress = neq_orgAddress;
            return this;
        }
		
        public String getLike_orgAddress()
        {
            return like_orgAddress;
        }

        public OrgSearchBo setLike_orgAddress(String like_orgAddress)
        {
            this.like_orgAddress = like_orgAddress;
            return this;
        }

        public String getLiker_orgAddress()
        {
            return liker_orgAddress;
        }

        public OrgSearchBo setLiker_orgAddress(String liker_orgAddress)
        {
            this.liker_orgAddress = liker_orgAddress;
            return this;
        }

        public String getLikel_orgAddress()
        {
            return likel_orgAddress;
        }

        public OrgSearchBo setLikel_orgAddress(String likel_orgAddress)
        {
            this.likel_orgAddress = likel_orgAddress;
            return this;
        }

        public String getNotlike_orgAddress()
        {
            return notlike_orgAddress;
        }

        public OrgSearchBo setNotlike_orgAddress(String notlike_orgAddress)
        {
            this.notlike_orgAddress = notlike_orgAddress;
            return this;
        }

        public String getNotliker_orgAddress()
        {
            return notliker_orgAddress;
        }

        public OrgSearchBo setNotliker_orgAddress(String notliker_orgAddress)
        {
            this.notliker_orgAddress = notliker_orgAddress;
            return this;
        }

        public String getNotlikel_orgAddress()
        {
            return notlikel_orgAddress;
        }

        public OrgSearchBo setNotlikel_orgAddress(String notlikel_orgAddress)
        {
            this.notlikel_orgAddress = notlikel_orgAddress;
            return this;
        }

        public String getIn_orgAddress()
        {
            return in_orgAddress;
        }

        public OrgSearchBo setIn_orgAddress(String in_orgAddress)
        {
            this.in_orgAddress = in_orgAddress;
            return this;
        }

        public String getNotin_orgAddress()
        {
            return notin_orgAddress;
        }

        public void setNotin_orgAddress(String notin_orgAddress)
        {
            this.notin_orgAddress = notin_orgAddress;
        }

        public String getEq_roleId()
        {
            return eq_roleId;
        }

        public OrgSearchBo setEq_roleId(String eq_roleId)
        {
            this.eq_roleId = eq_roleId;
            return this;
        }

        public String getNeq_roleId()
        {
            return neq_roleId;
        }

        public OrgSearchBo setNeq_roleId(String neq_roleId)
        {
            this.neq_roleId = neq_roleId;
            return this;
        }
		
        public String getLike_roleId()
        {
            return like_roleId;
        }

        public OrgSearchBo setLike_roleId(String like_roleId)
        {
            this.like_roleId = like_roleId;
            return this;
        }

        public String getLiker_roleId()
        {
            return liker_roleId;
        }

        public OrgSearchBo setLiker_roleId(String liker_roleId)
        {
            this.liker_roleId = liker_roleId;
            return this;
        }

        public String getLikel_roleId()
        {
            return likel_roleId;
        }

        public OrgSearchBo setLikel_roleId(String likel_roleId)
        {
            this.likel_roleId = likel_roleId;
            return this;
        }

        public String getNotlike_roleId()
        {
            return notlike_roleId;
        }

        public OrgSearchBo setNotlike_roleId(String notlike_roleId)
        {
            this.notlike_roleId = notlike_roleId;
            return this;
        }

        public String getNotliker_roleId()
        {
            return notliker_roleId;
        }

        public OrgSearchBo setNotliker_roleId(String notliker_roleId)
        {
            this.notliker_roleId = notliker_roleId;
            return this;
        }

        public String getNotlikel_roleId()
        {
            return notlikel_roleId;
        }

        public OrgSearchBo setNotlikel_roleId(String notlikel_roleId)
        {
            this.notlikel_roleId = notlikel_roleId;
            return this;
        }

        public String getIn_roleId()
        {
            return in_roleId;
        }

        public OrgSearchBo setIn_roleId(String in_roleId)
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

        public String getEq_userId()
        {
            return eq_userId;
        }

        public OrgSearchBo setEq_userId(String eq_userId)
        {
            this.eq_userId = eq_userId;
            return this;
        }

        public String getNeq_userId()
        {
            return neq_userId;
        }

        public OrgSearchBo setNeq_userId(String neq_userId)
        {
            this.neq_userId = neq_userId;
            return this;
        }
		
        public String getLike_userId()
        {
            return like_userId;
        }

        public OrgSearchBo setLike_userId(String like_userId)
        {
            this.like_userId = like_userId;
            return this;
        }

        public String getLiker_userId()
        {
            return liker_userId;
        }

        public OrgSearchBo setLiker_userId(String liker_userId)
        {
            this.liker_userId = liker_userId;
            return this;
        }

        public String getLikel_userId()
        {
            return likel_userId;
        }

        public OrgSearchBo setLikel_userId(String likel_userId)
        {
            this.likel_userId = likel_userId;
            return this;
        }

        public String getNotlike_userId()
        {
            return notlike_userId;
        }

        public OrgSearchBo setNotlike_userId(String notlike_userId)
        {
            this.notlike_userId = notlike_userId;
            return this;
        }

        public String getNotliker_userId()
        {
            return notliker_userId;
        }

        public OrgSearchBo setNotliker_userId(String notliker_userId)
        {
            this.notliker_userId = notliker_userId;
            return this;
        }

        public String getNotlikel_userId()
        {
            return notlikel_userId;
        }

        public OrgSearchBo setNotlikel_userId(String notlikel_userId)
        {
            this.notlikel_userId = notlikel_userId;
            return this;
        }

        public String getIn_userId()
        {
            return in_userId;
        }

        public OrgSearchBo setIn_userId(String in_userId)
        {
            this.in_userId = in_userId;
            return this;
        }

        public String getNotin_userId()
        {
            return notin_userId;
        }

        public void setNotin_userId(String notin_userId)
        {
            this.notin_userId = notin_userId;
        }

        public Date getEq_createdTime()
        {
            return eq_createdTime;
        }

        public OrgSearchBo setEq_createdTime(Date eq_createdTime)
        {
            this.eq_createdTime = eq_createdTime;
            return this;
        }

        public Date getNeq_createdTime()
        {
            return neq_createdTime;
        }

        public OrgSearchBo setNeq_createdTime(Date neq_createdTime)
        {
            this.neq_createdTime = neq_createdTime;
            return this;
        }
		
        public Date getGt_createdTime()
        {
            return gt_createdTime;
        }

        public OrgSearchBo setGt_createdTime(Date gt_createdTime)
        {
            this.gt_createdTime = gt_createdTime;
            return this;
        }

        public Date getLt_createdTime()
        {
            return lt_createdTime;
        }

        public OrgSearchBo setLt_createdTime(Date lt_createdTime)
        {
            this.lt_createdTime = lt_createdTime;
            return this;
        }

        public Date getEgt_createdTime()
        {
            return egt_createdTime;
        }

        public OrgSearchBo setEgt_createdTime(Date egt_createdTime)
        {
            this.egt_createdTime = egt_createdTime;
            return this;
        }

        public Date getElt_createdTime()
        {
            return elt_createdTime;
        }

        public OrgSearchBo setElt_createdTime(Date elt_createdTime)
        {
            this.elt_createdTime = elt_createdTime;
            return this;
        }
		
		public String getBetween_createdTime()
        {
            return between_createdTime;
        }

        public OrgSearchBo setBetween_createdTime(String between_createdTime)
        {
            this.between_createdTime = between_createdTime;
            return this;
        }

        public String getNotbetween_createdTime()
        {
            return notbetween_createdTime;
        }

        public OrgSearchBo setNotbetween_createdTime(String notbetween_createdTime)
        {
            this.notbetween_createdTime = notbetween_createdTime;
            return this;
        }

        public String getIn_createdTime()
        {
            return in_createdTime;
        }

        public OrgSearchBo setIn_createdTime(String in_createdTime)
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

        public OrgSearchBo setEq_updatedTime(Date eq_updatedTime)
        {
            this.eq_updatedTime = eq_updatedTime;
            return this;
        }

        public Date getNeq_updatedTime()
        {
            return neq_updatedTime;
        }

        public OrgSearchBo setNeq_updatedTime(Date neq_updatedTime)
        {
            this.neq_updatedTime = neq_updatedTime;
            return this;
        }
		
        public Date getGt_updatedTime()
        {
            return gt_updatedTime;
        }

        public OrgSearchBo setGt_updatedTime(Date gt_updatedTime)
        {
            this.gt_updatedTime = gt_updatedTime;
            return this;
        }

        public Date getLt_updatedTime()
        {
            return lt_updatedTime;
        }

        public OrgSearchBo setLt_updatedTime(Date lt_updatedTime)
        {
            this.lt_updatedTime = lt_updatedTime;
            return this;
        }

        public Date getEgt_updatedTime()
        {
            return egt_updatedTime;
        }

        public OrgSearchBo setEgt_updatedTime(Date egt_updatedTime)
        {
            this.egt_updatedTime = egt_updatedTime;
            return this;
        }

        public Date getElt_updatedTime()
        {
            return elt_updatedTime;
        }

        public OrgSearchBo setElt_updatedTime(Date elt_updatedTime)
        {
            this.elt_updatedTime = elt_updatedTime;
            return this;
        }
		
		public String getBetween_updatedTime()
        {
            return between_updatedTime;
        }

        public OrgSearchBo setBetween_updatedTime(String between_updatedTime)
        {
            this.between_updatedTime = between_updatedTime;
            return this;
        }

        public String getNotbetween_updatedTime()
        {
            return notbetween_updatedTime;
        }

        public OrgSearchBo setNotbetween_updatedTime(String notbetween_updatedTime)
        {
            this.notbetween_updatedTime = notbetween_updatedTime;
            return this;
        }

        public String getIn_updatedTime()
        {
            return in_updatedTime;
        }

        public OrgSearchBo setIn_updatedTime(String in_updatedTime)
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

        public OrgSearchBo setEq_createUser(String eq_createUser)
        {
            this.eq_createUser = eq_createUser;
            return this;
        }

        public String getNeq_createUser()
        {
            return neq_createUser;
        }

        public OrgSearchBo setNeq_createUser(String neq_createUser)
        {
            this.neq_createUser = neq_createUser;
            return this;
        }
		
        public String getLike_createUser()
        {
            return like_createUser;
        }

        public OrgSearchBo setLike_createUser(String like_createUser)
        {
            this.like_createUser = like_createUser;
            return this;
        }

        public String getLiker_createUser()
        {
            return liker_createUser;
        }

        public OrgSearchBo setLiker_createUser(String liker_createUser)
        {
            this.liker_createUser = liker_createUser;
            return this;
        }

        public String getLikel_createUser()
        {
            return likel_createUser;
        }

        public OrgSearchBo setLikel_createUser(String likel_createUser)
        {
            this.likel_createUser = likel_createUser;
            return this;
        }

        public String getNotlike_createUser()
        {
            return notlike_createUser;
        }

        public OrgSearchBo setNotlike_createUser(String notlike_createUser)
        {
            this.notlike_createUser = notlike_createUser;
            return this;
        }

        public String getNotliker_createUser()
        {
            return notliker_createUser;
        }

        public OrgSearchBo setNotliker_createUser(String notliker_createUser)
        {
            this.notliker_createUser = notliker_createUser;
            return this;
        }

        public String getNotlikel_createUser()
        {
            return notlikel_createUser;
        }

        public OrgSearchBo setNotlikel_createUser(String notlikel_createUser)
        {
            this.notlikel_createUser = notlikel_createUser;
            return this;
        }

        public String getIn_createUser()
        {
            return in_createUser;
        }

        public OrgSearchBo setIn_createUser(String in_createUser)
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

        public OrgSearchBo setEq_createUserId(String eq_createUserId)
        {
            this.eq_createUserId = eq_createUserId;
            return this;
        }

        public String getNeq_createUserId()
        {
            return neq_createUserId;
        }

        public OrgSearchBo setNeq_createUserId(String neq_createUserId)
        {
            this.neq_createUserId = neq_createUserId;
            return this;
        }
		
        public String getLike_createUserId()
        {
            return like_createUserId;
        }

        public OrgSearchBo setLike_createUserId(String like_createUserId)
        {
            this.like_createUserId = like_createUserId;
            return this;
        }

        public String getLiker_createUserId()
        {
            return liker_createUserId;
        }

        public OrgSearchBo setLiker_createUserId(String liker_createUserId)
        {
            this.liker_createUserId = liker_createUserId;
            return this;
        }

        public String getLikel_createUserId()
        {
            return likel_createUserId;
        }

        public OrgSearchBo setLikel_createUserId(String likel_createUserId)
        {
            this.likel_createUserId = likel_createUserId;
            return this;
        }

        public String getNotlike_createUserId()
        {
            return notlike_createUserId;
        }

        public OrgSearchBo setNotlike_createUserId(String notlike_createUserId)
        {
            this.notlike_createUserId = notlike_createUserId;
            return this;
        }

        public String getNotliker_createUserId()
        {
            return notliker_createUserId;
        }

        public OrgSearchBo setNotliker_createUserId(String notliker_createUserId)
        {
            this.notliker_createUserId = notliker_createUserId;
            return this;
        }

        public String getNotlikel_createUserId()
        {
            return notlikel_createUserId;
        }

        public OrgSearchBo setNotlikel_createUserId(String notlikel_createUserId)
        {
            this.notlikel_createUserId = notlikel_createUserId;
            return this;
        }

        public String getIn_createUserId()
        {
            return in_createUserId;
        }

        public OrgSearchBo setIn_createUserId(String in_createUserId)
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

        public OrgSearchBo setEq_updateUser(String eq_updateUser)
        {
            this.eq_updateUser = eq_updateUser;
            return this;
        }

        public String getNeq_updateUser()
        {
            return neq_updateUser;
        }

        public OrgSearchBo setNeq_updateUser(String neq_updateUser)
        {
            this.neq_updateUser = neq_updateUser;
            return this;
        }
		
        public String getLike_updateUser()
        {
            return like_updateUser;
        }

        public OrgSearchBo setLike_updateUser(String like_updateUser)
        {
            this.like_updateUser = like_updateUser;
            return this;
        }

        public String getLiker_updateUser()
        {
            return liker_updateUser;
        }

        public OrgSearchBo setLiker_updateUser(String liker_updateUser)
        {
            this.liker_updateUser = liker_updateUser;
            return this;
        }

        public String getLikel_updateUser()
        {
            return likel_updateUser;
        }

        public OrgSearchBo setLikel_updateUser(String likel_updateUser)
        {
            this.likel_updateUser = likel_updateUser;
            return this;
        }

        public String getNotlike_updateUser()
        {
            return notlike_updateUser;
        }

        public OrgSearchBo setNotlike_updateUser(String notlike_updateUser)
        {
            this.notlike_updateUser = notlike_updateUser;
            return this;
        }

        public String getNotliker_updateUser()
        {
            return notliker_updateUser;
        }

        public OrgSearchBo setNotliker_updateUser(String notliker_updateUser)
        {
            this.notliker_updateUser = notliker_updateUser;
            return this;
        }

        public String getNotlikel_updateUser()
        {
            return notlikel_updateUser;
        }

        public OrgSearchBo setNotlikel_updateUser(String notlikel_updateUser)
        {
            this.notlikel_updateUser = notlikel_updateUser;
            return this;
        }

        public String getIn_updateUser()
        {
            return in_updateUser;
        }

        public OrgSearchBo setIn_updateUser(String in_updateUser)
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

        public OrgSearchBo setEq_updateUserId(String eq_updateUserId)
        {
            this.eq_updateUserId = eq_updateUserId;
            return this;
        }

        public String getNeq_updateUserId()
        {
            return neq_updateUserId;
        }

        public OrgSearchBo setNeq_updateUserId(String neq_updateUserId)
        {
            this.neq_updateUserId = neq_updateUserId;
            return this;
        }
		
        public String getLike_updateUserId()
        {
            return like_updateUserId;
        }

        public OrgSearchBo setLike_updateUserId(String like_updateUserId)
        {
            this.like_updateUserId = like_updateUserId;
            return this;
        }

        public String getLiker_updateUserId()
        {
            return liker_updateUserId;
        }

        public OrgSearchBo setLiker_updateUserId(String liker_updateUserId)
        {
            this.liker_updateUserId = liker_updateUserId;
            return this;
        }

        public String getLikel_updateUserId()
        {
            return likel_updateUserId;
        }

        public OrgSearchBo setLikel_updateUserId(String likel_updateUserId)
        {
            this.likel_updateUserId = likel_updateUserId;
            return this;
        }

        public String getNotlike_updateUserId()
        {
            return notlike_updateUserId;
        }

        public OrgSearchBo setNotlike_updateUserId(String notlike_updateUserId)
        {
            this.notlike_updateUserId = notlike_updateUserId;
            return this;
        }

        public String getNotliker_updateUserId()
        {
            return notliker_updateUserId;
        }

        public OrgSearchBo setNotliker_updateUserId(String notliker_updateUserId)
        {
            this.notliker_updateUserId = notliker_updateUserId;
            return this;
        }

        public String getNotlikel_updateUserId()
        {
            return notlikel_updateUserId;
        }

        public OrgSearchBo setNotlikel_updateUserId(String notlikel_updateUserId)
        {
            this.notlikel_updateUserId = notlikel_updateUserId;
            return this;
        }

        public String getIn_updateUserId()
        {
            return in_updateUserId;
        }

        public OrgSearchBo setIn_updateUserId(String in_updateUserId)
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
