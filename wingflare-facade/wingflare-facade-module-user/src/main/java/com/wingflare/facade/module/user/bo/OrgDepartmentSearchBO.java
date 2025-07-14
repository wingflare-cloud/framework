package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.math.BigInteger;
import java.util.Date;

/**
 * OrgDepartmentSearchBo
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:40:11 CST 2023
 */
public class OrgDepartmentSearchBO extends BaseSearchBo
{

        private BigInteger eq_departmentId;

        private BigInteger neq_departmentId;
		
        private String in_departmentId;

        private String notin_departmentId;

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

        private BigInteger eq_parentDepartmentId;

        private BigInteger neq_parentDepartmentId;
		
		private String like_parentDepartmentId;

        private String liker_parentDepartmentId;

        private String likel_parentDepartmentId;

        private String notlike_parentDepartmentId;

        private String notliker_parentDepartmentId;

        private String notlikel_parentDepartmentId;
		
        private String in_parentDepartmentId;

        private String notin_parentDepartmentId;

        private String eq_departmentName;

        private String neq_departmentName;
		
		private String like_departmentName;

        private String liker_departmentName;

        private String likel_departmentName;

        private String notlike_departmentName;

        private String notliker_departmentName;

        private String notlikel_departmentName;
		
        private String in_departmentName;

        private String notin_departmentName;

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


        public BigInteger getEq_departmentId()
        {
            return eq_departmentId;
        }

        public OrgDepartmentSearchBO setEq_departmentId(BigInteger eq_departmentId)
        {
            this.eq_departmentId = eq_departmentId;
            return this;
        }

        public BigInteger getNeq_departmentId()
        {
            return neq_departmentId;
        }

        public OrgDepartmentSearchBO setNeq_departmentId(BigInteger neq_departmentId)
        {
            this.neq_departmentId = neq_departmentId;
            return this;
        }

        public String getIn_departmentId()
        {
            return in_departmentId;
        }

        public OrgDepartmentSearchBO setIn_departmentId(String in_departmentId)
        {
            this.in_departmentId = in_departmentId;
            return this;
        }

        public String getNotin_departmentId()
        {
            return notin_departmentId;
        }

        public void setNotin_departmentId(String notin_departmentId)
        {
            this.notin_departmentId = notin_departmentId;
        }

        public Integer getEq_state()
        {
            return eq_state;
        }

        public OrgDepartmentSearchBO setEq_state(Integer eq_state)
        {
            this.eq_state = eq_state;
            return this;
        }

        public Integer getNeq_state()
        {
            return neq_state;
        }

        public OrgDepartmentSearchBO setNeq_state(Integer neq_state)
        {
            this.neq_state = neq_state;
            return this;
        }
		
        public Integer getGt_state()
        {
            return gt_state;
        }

        public OrgDepartmentSearchBO setGt_state(Integer gt_state)
        {
            this.gt_state = gt_state;
            return this;
        }

        public Integer getLt_state()
        {
            return lt_state;
        }

        public OrgDepartmentSearchBO setLt_state(Integer lt_state)
        {
            this.lt_state = lt_state;
            return this;
        }

        public Integer getEgt_state()
        {
            return egt_state;
        }

        public OrgDepartmentSearchBO setEgt_state(Integer egt_state)
        {
            this.egt_state = egt_state;
            return this;
        }

        public Integer getElt_state()
        {
            return elt_state;
        }

        public OrgDepartmentSearchBO setElt_state(Integer elt_state)
        {
            this.elt_state = elt_state;
            return this;
        }
		
		public String getBetween_state()
        {
            return between_state;
        }

        public OrgDepartmentSearchBO setBetween_state(String between_state)
        {
            this.between_state = between_state;
            return this;
        }

        public String getNotbetween_state()
        {
            return notbetween_state;
        }

        public OrgDepartmentSearchBO setNotbetween_state(String notbetween_state)
        {
            this.notbetween_state = notbetween_state;
            return this;
        }

        public String getIn_state()
        {
            return in_state;
        }

        public OrgDepartmentSearchBO setIn_state(String in_state)
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

        public BigInteger getEq_orgId()
        {
            return eq_orgId;
        }

        public OrgDepartmentSearchBO setEq_orgId(BigInteger eq_orgId)
        {
            this.eq_orgId = eq_orgId;
            return this;
        }

        public BigInteger getNeq_orgId()
        {
            return neq_orgId;
        }

        public OrgDepartmentSearchBO setNeq_orgId(BigInteger neq_orgId)
        {
            this.neq_orgId = neq_orgId;
            return this;
        }
		
        public String getLike_orgId()
        {
            return like_orgId;
        }

        public OrgDepartmentSearchBO setLike_orgId(String like_orgId)
        {
            this.like_orgId = like_orgId;
            return this;
        }

        public String getLiker_orgId()
        {
            return liker_orgId;
        }

        public OrgDepartmentSearchBO setLiker_orgId(String liker_orgId)
        {
            this.liker_orgId = liker_orgId;
            return this;
        }

        public String getLikel_orgId()
        {
            return likel_orgId;
        }

        public OrgDepartmentSearchBO setLikel_orgId(String likel_orgId)
        {
            this.likel_orgId = likel_orgId;
            return this;
        }

        public String getNotlike_orgId()
        {
            return notlike_orgId;
        }

        public OrgDepartmentSearchBO setNotlike_orgId(String notlike_orgId)
        {
            this.notlike_orgId = notlike_orgId;
            return this;
        }

        public String getNotliker_orgId()
        {
            return notliker_orgId;
        }

        public OrgDepartmentSearchBO setNotliker_orgId(String notliker_orgId)
        {
            this.notliker_orgId = notliker_orgId;
            return this;
        }

        public String getNotlikel_orgId()
        {
            return notlikel_orgId;
        }

        public OrgDepartmentSearchBO setNotlikel_orgId(String notlikel_orgId)
        {
            this.notlikel_orgId = notlikel_orgId;
            return this;
        }

        public String getIn_orgId()
        {
            return in_orgId;
        }

        public OrgDepartmentSearchBO setIn_orgId(String in_orgId)
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

        public BigInteger getEq_parentDepartmentId()
        {
            return eq_parentDepartmentId;
        }

        public OrgDepartmentSearchBO setEq_parentDepartmentId(BigInteger eq_parentDepartmentId)
        {
            this.eq_parentDepartmentId = eq_parentDepartmentId;
            return this;
        }

        public BigInteger getNeq_parentDepartmentId()
        {
            return neq_parentDepartmentId;
        }

        public OrgDepartmentSearchBO setNeq_parentDepartmentId(BigInteger neq_parentDepartmentId)
        {
            this.neq_parentDepartmentId = neq_parentDepartmentId;
            return this;
        }
		
        public String getLike_parentDepartmentId()
        {
            return like_parentDepartmentId;
        }

        public OrgDepartmentSearchBO setLike_parentDepartmentId(String like_parentDepartmentId)
        {
            this.like_parentDepartmentId = like_parentDepartmentId;
            return this;
        }

        public String getLiker_parentDepartmentId()
        {
            return liker_parentDepartmentId;
        }

        public OrgDepartmentSearchBO setLiker_parentDepartmentId(String liker_parentDepartmentId)
        {
            this.liker_parentDepartmentId = liker_parentDepartmentId;
            return this;
        }

        public String getLikel_parentDepartmentId()
        {
            return likel_parentDepartmentId;
        }

        public OrgDepartmentSearchBO setLikel_parentDepartmentId(String likel_parentDepartmentId)
        {
            this.likel_parentDepartmentId = likel_parentDepartmentId;
            return this;
        }

        public String getNotlike_parentDepartmentId()
        {
            return notlike_parentDepartmentId;
        }

        public OrgDepartmentSearchBO setNotlike_parentDepartmentId(String notlike_parentDepartmentId)
        {
            this.notlike_parentDepartmentId = notlike_parentDepartmentId;
            return this;
        }

        public String getNotliker_parentDepartmentId()
        {
            return notliker_parentDepartmentId;
        }

        public OrgDepartmentSearchBO setNotliker_parentDepartmentId(String notliker_parentDepartmentId)
        {
            this.notliker_parentDepartmentId = notliker_parentDepartmentId;
            return this;
        }

        public String getNotlikel_parentDepartmentId()
        {
            return notlikel_parentDepartmentId;
        }

        public OrgDepartmentSearchBO setNotlikel_parentDepartmentId(String notlikel_parentDepartmentId)
        {
            this.notlikel_parentDepartmentId = notlikel_parentDepartmentId;
            return this;
        }

        public String getIn_parentDepartmentId()
        {
            return in_parentDepartmentId;
        }

        public OrgDepartmentSearchBO setIn_parentDepartmentId(String in_parentDepartmentId)
        {
            this.in_parentDepartmentId = in_parentDepartmentId;
            return this;
        }

        public String getNotin_parentDepartmentId()
        {
            return notin_parentDepartmentId;
        }

        public void setNotin_parentDepartmentId(String notin_parentDepartmentId)
        {
            this.notin_parentDepartmentId = notin_parentDepartmentId;
        }

        public String getEq_departmentName()
        {
            return eq_departmentName;
        }

        public OrgDepartmentSearchBO setEq_departmentName(String eq_departmentName)
        {
            this.eq_departmentName = eq_departmentName;
            return this;
        }

        public String getNeq_departmentName()
        {
            return neq_departmentName;
        }

        public OrgDepartmentSearchBO setNeq_departmentName(String neq_departmentName)
        {
            this.neq_departmentName = neq_departmentName;
            return this;
        }
		
        public String getLike_departmentName()
        {
            return like_departmentName;
        }

        public OrgDepartmentSearchBO setLike_departmentName(String like_departmentName)
        {
            this.like_departmentName = like_departmentName;
            return this;
        }

        public String getLiker_departmentName()
        {
            return liker_departmentName;
        }

        public OrgDepartmentSearchBO setLiker_departmentName(String liker_departmentName)
        {
            this.liker_departmentName = liker_departmentName;
            return this;
        }

        public String getLikel_departmentName()
        {
            return likel_departmentName;
        }

        public OrgDepartmentSearchBO setLikel_departmentName(String likel_departmentName)
        {
            this.likel_departmentName = likel_departmentName;
            return this;
        }

        public String getNotlike_departmentName()
        {
            return notlike_departmentName;
        }

        public OrgDepartmentSearchBO setNotlike_departmentName(String notlike_departmentName)
        {
            this.notlike_departmentName = notlike_departmentName;
            return this;
        }

        public String getNotliker_departmentName()
        {
            return notliker_departmentName;
        }

        public OrgDepartmentSearchBO setNotliker_departmentName(String notliker_departmentName)
        {
            this.notliker_departmentName = notliker_departmentName;
            return this;
        }

        public String getNotlikel_departmentName()
        {
            return notlikel_departmentName;
        }

        public OrgDepartmentSearchBO setNotlikel_departmentName(String notlikel_departmentName)
        {
            this.notlikel_departmentName = notlikel_departmentName;
            return this;
        }

        public String getIn_departmentName()
        {
            return in_departmentName;
        }

        public OrgDepartmentSearchBO setIn_departmentName(String in_departmentName)
        {
            this.in_departmentName = in_departmentName;
            return this;
        }

        public String getNotin_departmentName()
        {
            return notin_departmentName;
        }

        public void setNotin_departmentName(String notin_departmentName)
        {
            this.notin_departmentName = notin_departmentName;
        }

        public BigInteger getEq_roleId()
        {
            return eq_roleId;
        }

        public OrgDepartmentSearchBO setEq_roleId(BigInteger eq_roleId)
        {
            this.eq_roleId = eq_roleId;
            return this;
        }

        public BigInteger getNeq_roleId()
        {
            return neq_roleId;
        }

        public OrgDepartmentSearchBO setNeq_roleId(BigInteger neq_roleId)
        {
            this.neq_roleId = neq_roleId;
            return this;
        }
		
        public String getLike_roleId()
        {
            return like_roleId;
        }

        public OrgDepartmentSearchBO setLike_roleId(String like_roleId)
        {
            this.like_roleId = like_roleId;
            return this;
        }

        public String getLiker_roleId()
        {
            return liker_roleId;
        }

        public OrgDepartmentSearchBO setLiker_roleId(String liker_roleId)
        {
            this.liker_roleId = liker_roleId;
            return this;
        }

        public String getLikel_roleId()
        {
            return likel_roleId;
        }

        public OrgDepartmentSearchBO setLikel_roleId(String likel_roleId)
        {
            this.likel_roleId = likel_roleId;
            return this;
        }

        public String getNotlike_roleId()
        {
            return notlike_roleId;
        }

        public OrgDepartmentSearchBO setNotlike_roleId(String notlike_roleId)
        {
            this.notlike_roleId = notlike_roleId;
            return this;
        }

        public String getNotliker_roleId()
        {
            return notliker_roleId;
        }

        public OrgDepartmentSearchBO setNotliker_roleId(String notliker_roleId)
        {
            this.notliker_roleId = notliker_roleId;
            return this;
        }

        public String getNotlikel_roleId()
        {
            return notlikel_roleId;
        }

        public OrgDepartmentSearchBO setNotlikel_roleId(String notlikel_roleId)
        {
            this.notlikel_roleId = notlikel_roleId;
            return this;
        }

        public String getIn_roleId()
        {
            return in_roleId;
        }

        public OrgDepartmentSearchBO setIn_roleId(String in_roleId)
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

        public BigInteger getEq_userId()
        {
            return eq_userId;
        }

        public OrgDepartmentSearchBO setEq_userId(BigInteger eq_userId)
        {
            this.eq_userId = eq_userId;
            return this;
        }

        public BigInteger getNeq_userId()
        {
            return neq_userId;
        }

        public OrgDepartmentSearchBO setNeq_userId(BigInteger neq_userId)
        {
            this.neq_userId = neq_userId;
            return this;
        }
		
        public String getLike_userId()
        {
            return like_userId;
        }

        public OrgDepartmentSearchBO setLike_userId(String like_userId)
        {
            this.like_userId = like_userId;
            return this;
        }

        public String getLiker_userId()
        {
            return liker_userId;
        }

        public OrgDepartmentSearchBO setLiker_userId(String liker_userId)
        {
            this.liker_userId = liker_userId;
            return this;
        }

        public String getLikel_userId()
        {
            return likel_userId;
        }

        public OrgDepartmentSearchBO setLikel_userId(String likel_userId)
        {
            this.likel_userId = likel_userId;
            return this;
        }

        public String getNotlike_userId()
        {
            return notlike_userId;
        }

        public OrgDepartmentSearchBO setNotlike_userId(String notlike_userId)
        {
            this.notlike_userId = notlike_userId;
            return this;
        }

        public String getNotliker_userId()
        {
            return notliker_userId;
        }

        public OrgDepartmentSearchBO setNotliker_userId(String notliker_userId)
        {
            this.notliker_userId = notliker_userId;
            return this;
        }

        public String getNotlikel_userId()
        {
            return notlikel_userId;
        }

        public OrgDepartmentSearchBO setNotlikel_userId(String notlikel_userId)
        {
            this.notlikel_userId = notlikel_userId;
            return this;
        }

        public String getIn_userId()
        {
            return in_userId;
        }

        public OrgDepartmentSearchBO setIn_userId(String in_userId)
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

        public OrgDepartmentSearchBO setEq_createdTime(Date eq_createdTime)
        {
            this.eq_createdTime = eq_createdTime;
            return this;
        }

        public Date getNeq_createdTime()
        {
            return neq_createdTime;
        }

        public OrgDepartmentSearchBO setNeq_createdTime(Date neq_createdTime)
        {
            this.neq_createdTime = neq_createdTime;
            return this;
        }
		
        public Date getGt_createdTime()
        {
            return gt_createdTime;
        }

        public OrgDepartmentSearchBO setGt_createdTime(Date gt_createdTime)
        {
            this.gt_createdTime = gt_createdTime;
            return this;
        }

        public Date getLt_createdTime()
        {
            return lt_createdTime;
        }

        public OrgDepartmentSearchBO setLt_createdTime(Date lt_createdTime)
        {
            this.lt_createdTime = lt_createdTime;
            return this;
        }

        public Date getEgt_createdTime()
        {
            return egt_createdTime;
        }

        public OrgDepartmentSearchBO setEgt_createdTime(Date egt_createdTime)
        {
            this.egt_createdTime = egt_createdTime;
            return this;
        }

        public Date getElt_createdTime()
        {
            return elt_createdTime;
        }

        public OrgDepartmentSearchBO setElt_createdTime(Date elt_createdTime)
        {
            this.elt_createdTime = elt_createdTime;
            return this;
        }
		
		public String getBetween_createdTime()
        {
            return between_createdTime;
        }

        public OrgDepartmentSearchBO setBetween_createdTime(String between_createdTime)
        {
            this.between_createdTime = between_createdTime;
            return this;
        }

        public String getNotbetween_createdTime()
        {
            return notbetween_createdTime;
        }

        public OrgDepartmentSearchBO setNotbetween_createdTime(String notbetween_createdTime)
        {
            this.notbetween_createdTime = notbetween_createdTime;
            return this;
        }

        public String getIn_createdTime()
        {
            return in_createdTime;
        }

        public OrgDepartmentSearchBO setIn_createdTime(String in_createdTime)
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

        public OrgDepartmentSearchBO setEq_updatedTime(Date eq_updatedTime)
        {
            this.eq_updatedTime = eq_updatedTime;
            return this;
        }

        public Date getNeq_updatedTime()
        {
            return neq_updatedTime;
        }

        public OrgDepartmentSearchBO setNeq_updatedTime(Date neq_updatedTime)
        {
            this.neq_updatedTime = neq_updatedTime;
            return this;
        }
		
        public Date getGt_updatedTime()
        {
            return gt_updatedTime;
        }

        public OrgDepartmentSearchBO setGt_updatedTime(Date gt_updatedTime)
        {
            this.gt_updatedTime = gt_updatedTime;
            return this;
        }

        public Date getLt_updatedTime()
        {
            return lt_updatedTime;
        }

        public OrgDepartmentSearchBO setLt_updatedTime(Date lt_updatedTime)
        {
            this.lt_updatedTime = lt_updatedTime;
            return this;
        }

        public Date getEgt_updatedTime()
        {
            return egt_updatedTime;
        }

        public OrgDepartmentSearchBO setEgt_updatedTime(Date egt_updatedTime)
        {
            this.egt_updatedTime = egt_updatedTime;
            return this;
        }

        public Date getElt_updatedTime()
        {
            return elt_updatedTime;
        }

        public OrgDepartmentSearchBO setElt_updatedTime(Date elt_updatedTime)
        {
            this.elt_updatedTime = elt_updatedTime;
            return this;
        }
		
		public String getBetween_updatedTime()
        {
            return between_updatedTime;
        }

        public OrgDepartmentSearchBO setBetween_updatedTime(String between_updatedTime)
        {
            this.between_updatedTime = between_updatedTime;
            return this;
        }

        public String getNotbetween_updatedTime()
        {
            return notbetween_updatedTime;
        }

        public OrgDepartmentSearchBO setNotbetween_updatedTime(String notbetween_updatedTime)
        {
            this.notbetween_updatedTime = notbetween_updatedTime;
            return this;
        }

        public String getIn_updatedTime()
        {
            return in_updatedTime;
        }

        public OrgDepartmentSearchBO setIn_updatedTime(String in_updatedTime)
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

        public OrgDepartmentSearchBO setEq_createUser(String eq_createUser)
        {
            this.eq_createUser = eq_createUser;
            return this;
        }

        public String getNeq_createUser()
        {
            return neq_createUser;
        }

        public OrgDepartmentSearchBO setNeq_createUser(String neq_createUser)
        {
            this.neq_createUser = neq_createUser;
            return this;
        }
		
        public String getLike_createUser()
        {
            return like_createUser;
        }

        public OrgDepartmentSearchBO setLike_createUser(String like_createUser)
        {
            this.like_createUser = like_createUser;
            return this;
        }

        public String getLiker_createUser()
        {
            return liker_createUser;
        }

        public OrgDepartmentSearchBO setLiker_createUser(String liker_createUser)
        {
            this.liker_createUser = liker_createUser;
            return this;
        }

        public String getLikel_createUser()
        {
            return likel_createUser;
        }

        public OrgDepartmentSearchBO setLikel_createUser(String likel_createUser)
        {
            this.likel_createUser = likel_createUser;
            return this;
        }

        public String getNotlike_createUser()
        {
            return notlike_createUser;
        }

        public OrgDepartmentSearchBO setNotlike_createUser(String notlike_createUser)
        {
            this.notlike_createUser = notlike_createUser;
            return this;
        }

        public String getNotliker_createUser()
        {
            return notliker_createUser;
        }

        public OrgDepartmentSearchBO setNotliker_createUser(String notliker_createUser)
        {
            this.notliker_createUser = notliker_createUser;
            return this;
        }

        public String getNotlikel_createUser()
        {
            return notlikel_createUser;
        }

        public OrgDepartmentSearchBO setNotlikel_createUser(String notlikel_createUser)
        {
            this.notlikel_createUser = notlikel_createUser;
            return this;
        }

        public String getIn_createUser()
        {
            return in_createUser;
        }

        public OrgDepartmentSearchBO setIn_createUser(String in_createUser)
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

        public OrgDepartmentSearchBO setEq_createUserId(BigInteger eq_createUserId)
        {
            this.eq_createUserId = eq_createUserId;
            return this;
        }

        public BigInteger getNeq_createUserId()
        {
            return neq_createUserId;
        }

        public OrgDepartmentSearchBO setNeq_createUserId(BigInteger neq_createUserId)
        {
            this.neq_createUserId = neq_createUserId;
            return this;
        }
		
        public String getLike_createUserId()
        {
            return like_createUserId;
        }

        public OrgDepartmentSearchBO setLike_createUserId(String like_createUserId)
        {
            this.like_createUserId = like_createUserId;
            return this;
        }

        public String getLiker_createUserId()
        {
            return liker_createUserId;
        }

        public OrgDepartmentSearchBO setLiker_createUserId(String liker_createUserId)
        {
            this.liker_createUserId = liker_createUserId;
            return this;
        }

        public String getLikel_createUserId()
        {
            return likel_createUserId;
        }

        public OrgDepartmentSearchBO setLikel_createUserId(String likel_createUserId)
        {
            this.likel_createUserId = likel_createUserId;
            return this;
        }

        public String getNotlike_createUserId()
        {
            return notlike_createUserId;
        }

        public OrgDepartmentSearchBO setNotlike_createUserId(String notlike_createUserId)
        {
            this.notlike_createUserId = notlike_createUserId;
            return this;
        }

        public String getNotliker_createUserId()
        {
            return notliker_createUserId;
        }

        public OrgDepartmentSearchBO setNotliker_createUserId(String notliker_createUserId)
        {
            this.notliker_createUserId = notliker_createUserId;
            return this;
        }

        public String getNotlikel_createUserId()
        {
            return notlikel_createUserId;
        }

        public OrgDepartmentSearchBO setNotlikel_createUserId(String notlikel_createUserId)
        {
            this.notlikel_createUserId = notlikel_createUserId;
            return this;
        }

        public String getIn_createUserId()
        {
            return in_createUserId;
        }

        public OrgDepartmentSearchBO setIn_createUserId(String in_createUserId)
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

        public OrgDepartmentSearchBO setEq_updateUser(String eq_updateUser)
        {
            this.eq_updateUser = eq_updateUser;
            return this;
        }

        public String getNeq_updateUser()
        {
            return neq_updateUser;
        }

        public OrgDepartmentSearchBO setNeq_updateUser(String neq_updateUser)
        {
            this.neq_updateUser = neq_updateUser;
            return this;
        }
		
        public String getLike_updateUser()
        {
            return like_updateUser;
        }

        public OrgDepartmentSearchBO setLike_updateUser(String like_updateUser)
        {
            this.like_updateUser = like_updateUser;
            return this;
        }

        public String getLiker_updateUser()
        {
            return liker_updateUser;
        }

        public OrgDepartmentSearchBO setLiker_updateUser(String liker_updateUser)
        {
            this.liker_updateUser = liker_updateUser;
            return this;
        }

        public String getLikel_updateUser()
        {
            return likel_updateUser;
        }

        public OrgDepartmentSearchBO setLikel_updateUser(String likel_updateUser)
        {
            this.likel_updateUser = likel_updateUser;
            return this;
        }

        public String getNotlike_updateUser()
        {
            return notlike_updateUser;
        }

        public OrgDepartmentSearchBO setNotlike_updateUser(String notlike_updateUser)
        {
            this.notlike_updateUser = notlike_updateUser;
            return this;
        }

        public String getNotliker_updateUser()
        {
            return notliker_updateUser;
        }

        public OrgDepartmentSearchBO setNotliker_updateUser(String notliker_updateUser)
        {
            this.notliker_updateUser = notliker_updateUser;
            return this;
        }

        public String getNotlikel_updateUser()
        {
            return notlikel_updateUser;
        }

        public OrgDepartmentSearchBO setNotlikel_updateUser(String notlikel_updateUser)
        {
            this.notlikel_updateUser = notlikel_updateUser;
            return this;
        }

        public String getIn_updateUser()
        {
            return in_updateUser;
        }

        public OrgDepartmentSearchBO setIn_updateUser(String in_updateUser)
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

        public BigInteger getEq_updateUserId()
        {
            return eq_updateUserId;
        }

        public OrgDepartmentSearchBO setEq_updateUserId(BigInteger eq_updateUserId)
        {
            this.eq_updateUserId = eq_updateUserId;
            return this;
        }

        public BigInteger getNeq_updateUserId()
        {
            return neq_updateUserId;
        }

        public OrgDepartmentSearchBO setNeq_updateUserId(BigInteger neq_updateUserId)
        {
            this.neq_updateUserId = neq_updateUserId;
            return this;
        }
		
        public String getLike_updateUserId()
        {
            return like_updateUserId;
        }

        public OrgDepartmentSearchBO setLike_updateUserId(String like_updateUserId)
        {
            this.like_updateUserId = like_updateUserId;
            return this;
        }

        public String getLiker_updateUserId()
        {
            return liker_updateUserId;
        }

        public OrgDepartmentSearchBO setLiker_updateUserId(String liker_updateUserId)
        {
            this.liker_updateUserId = liker_updateUserId;
            return this;
        }

        public String getLikel_updateUserId()
        {
            return likel_updateUserId;
        }

        public OrgDepartmentSearchBO setLikel_updateUserId(String likel_updateUserId)
        {
            this.likel_updateUserId = likel_updateUserId;
            return this;
        }

        public String getNotlike_updateUserId()
        {
            return notlike_updateUserId;
        }

        public OrgDepartmentSearchBO setNotlike_updateUserId(String notlike_updateUserId)
        {
            this.notlike_updateUserId = notlike_updateUserId;
            return this;
        }

        public String getNotliker_updateUserId()
        {
            return notliker_updateUserId;
        }

        public OrgDepartmentSearchBO setNotliker_updateUserId(String notliker_updateUserId)
        {
            this.notliker_updateUserId = notliker_updateUserId;
            return this;
        }

        public String getNotlikel_updateUserId()
        {
            return notlikel_updateUserId;
        }

        public OrgDepartmentSearchBO setNotlikel_updateUserId(String notlikel_updateUserId)
        {
            this.notlikel_updateUserId = notlikel_updateUserId;
            return this;
        }

        public String getIn_updateUserId()
        {
            return in_updateUserId;
        }

        public OrgDepartmentSearchBO setIn_updateUserId(String in_updateUserId)
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
