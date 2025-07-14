package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.math.BigInteger;
import java.util.Date;

/**
 * RoleSearchBo
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:04:01 CST 2023
 */
public class RoleSearchBO extends BaseSearchBo
{

        private BigInteger eq_roleId;

        private BigInteger neq_roleId;
		
        private String in_roleId;

        private String notin_roleId;

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

        private String eq_roleName;

        private String neq_roleName;
		
		private String like_roleName;

        private String liker_roleName;

        private String likel_roleName;

        private String notlike_roleName;

        private String notliker_roleName;

        private String notlikel_roleName;
		
        private String in_roleName;

        private String notin_roleName;

        private String eq_roleRemark;

        private String neq_roleRemark;
		
		private String like_roleRemark;

        private String liker_roleRemark;

        private String likel_roleRemark;

        private String notlike_roleRemark;

        private String notliker_roleRemark;

        private String notlikel_roleRemark;
		
        private String in_roleRemark;

        private String notin_roleRemark;

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


        public BigInteger getEq_roleId()
        {
            return eq_roleId;
        }

        public RoleSearchBO setEq_roleId(BigInteger eq_roleId)
        {
            this.eq_roleId = eq_roleId;
            return this;
        }

        public BigInteger getNeq_roleId()
        {
            return neq_roleId;
        }

        public RoleSearchBO setNeq_roleId(BigInteger neq_roleId)
        {
            this.neq_roleId = neq_roleId;
            return this;
        }

        public String getIn_roleId()
        {
            return in_roleId;
        }

        public RoleSearchBO setIn_roleId(String in_roleId)
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

        public Integer getEq_state()
        {
            return eq_state;
        }

        public RoleSearchBO setEq_state(Integer eq_state)
        {
            this.eq_state = eq_state;
            return this;
        }

        public Integer getNeq_state()
        {
            return neq_state;
        }

        public RoleSearchBO setNeq_state(Integer neq_state)
        {
            this.neq_state = neq_state;
            return this;
        }
		
        public Integer getGt_state()
        {
            return gt_state;
        }

        public RoleSearchBO setGt_state(Integer gt_state)
        {
            this.gt_state = gt_state;
            return this;
        }

        public Integer getLt_state()
        {
            return lt_state;
        }

        public RoleSearchBO setLt_state(Integer lt_state)
        {
            this.lt_state = lt_state;
            return this;
        }

        public Integer getEgt_state()
        {
            return egt_state;
        }

        public RoleSearchBO setEgt_state(Integer egt_state)
        {
            this.egt_state = egt_state;
            return this;
        }

        public Integer getElt_state()
        {
            return elt_state;
        }

        public RoleSearchBO setElt_state(Integer elt_state)
        {
            this.elt_state = elt_state;
            return this;
        }
		
		public String getBetween_state()
        {
            return between_state;
        }

        public RoleSearchBO setBetween_state(String between_state)
        {
            this.between_state = between_state;
            return this;
        }

        public String getNotbetween_state()
        {
            return notbetween_state;
        }

        public RoleSearchBO setNotbetween_state(String notbetween_state)
        {
            this.notbetween_state = notbetween_state;
            return this;
        }

        public String getIn_state()
        {
            return in_state;
        }

        public RoleSearchBO setIn_state(String in_state)
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

        public String getEq_roleName()
        {
            return eq_roleName;
        }

        public RoleSearchBO setEq_roleName(String eq_roleName)
        {
            this.eq_roleName = eq_roleName;
            return this;
        }

        public String getNeq_roleName()
        {
            return neq_roleName;
        }

        public RoleSearchBO setNeq_roleName(String neq_roleName)
        {
            this.neq_roleName = neq_roleName;
            return this;
        }
		
        public String getLike_roleName()
        {
            return like_roleName;
        }

        public RoleSearchBO setLike_roleName(String like_roleName)
        {
            this.like_roleName = like_roleName;
            return this;
        }

        public String getLiker_roleName()
        {
            return liker_roleName;
        }

        public RoleSearchBO setLiker_roleName(String liker_roleName)
        {
            this.liker_roleName = liker_roleName;
            return this;
        }

        public String getLikel_roleName()
        {
            return likel_roleName;
        }

        public RoleSearchBO setLikel_roleName(String likel_roleName)
        {
            this.likel_roleName = likel_roleName;
            return this;
        }

        public String getNotlike_roleName()
        {
            return notlike_roleName;
        }

        public RoleSearchBO setNotlike_roleName(String notlike_roleName)
        {
            this.notlike_roleName = notlike_roleName;
            return this;
        }

        public String getNotliker_roleName()
        {
            return notliker_roleName;
        }

        public RoleSearchBO setNotliker_roleName(String notliker_roleName)
        {
            this.notliker_roleName = notliker_roleName;
            return this;
        }

        public String getNotlikel_roleName()
        {
            return notlikel_roleName;
        }

        public RoleSearchBO setNotlikel_roleName(String notlikel_roleName)
        {
            this.notlikel_roleName = notlikel_roleName;
            return this;
        }

        public String getIn_roleName()
        {
            return in_roleName;
        }

        public RoleSearchBO setIn_roleName(String in_roleName)
        {
            this.in_roleName = in_roleName;
            return this;
        }

        public String getNotin_roleName()
        {
            return notin_roleName;
        }

        public void setNotin_roleName(String notin_roleName)
        {
            this.notin_roleName = notin_roleName;
        }

        public String getEq_roleRemark()
        {
            return eq_roleRemark;
        }

        public RoleSearchBO setEq_roleRemark(String eq_roleRemark)
        {
            this.eq_roleRemark = eq_roleRemark;
            return this;
        }

        public String getNeq_roleRemark()
        {
            return neq_roleRemark;
        }

        public RoleSearchBO setNeq_roleRemark(String neq_roleRemark)
        {
            this.neq_roleRemark = neq_roleRemark;
            return this;
        }
		
        public String getLike_roleRemark()
        {
            return like_roleRemark;
        }

        public RoleSearchBO setLike_roleRemark(String like_roleRemark)
        {
            this.like_roleRemark = like_roleRemark;
            return this;
        }

        public String getLiker_roleRemark()
        {
            return liker_roleRemark;
        }

        public RoleSearchBO setLiker_roleRemark(String liker_roleRemark)
        {
            this.liker_roleRemark = liker_roleRemark;
            return this;
        }

        public String getLikel_roleRemark()
        {
            return likel_roleRemark;
        }

        public RoleSearchBO setLikel_roleRemark(String likel_roleRemark)
        {
            this.likel_roleRemark = likel_roleRemark;
            return this;
        }

        public String getNotlike_roleRemark()
        {
            return notlike_roleRemark;
        }

        public RoleSearchBO setNotlike_roleRemark(String notlike_roleRemark)
        {
            this.notlike_roleRemark = notlike_roleRemark;
            return this;
        }

        public String getNotliker_roleRemark()
        {
            return notliker_roleRemark;
        }

        public RoleSearchBO setNotliker_roleRemark(String notliker_roleRemark)
        {
            this.notliker_roleRemark = notliker_roleRemark;
            return this;
        }

        public String getNotlikel_roleRemark()
        {
            return notlikel_roleRemark;
        }

        public RoleSearchBO setNotlikel_roleRemark(String notlikel_roleRemark)
        {
            this.notlikel_roleRemark = notlikel_roleRemark;
            return this;
        }

        public String getIn_roleRemark()
        {
            return in_roleRemark;
        }

        public RoleSearchBO setIn_roleRemark(String in_roleRemark)
        {
            this.in_roleRemark = in_roleRemark;
            return this;
        }

        public String getNotin_roleRemark()
        {
            return notin_roleRemark;
        }

        public void setNotin_roleRemark(String notin_roleRemark)
        {
            this.notin_roleRemark = notin_roleRemark;
        }

        public Date getEq_createdTime()
        {
            return eq_createdTime;
        }

        public RoleSearchBO setEq_createdTime(Date eq_createdTime)
        {
            this.eq_createdTime = eq_createdTime;
            return this;
        }

        public Date getNeq_createdTime()
        {
            return neq_createdTime;
        }

        public RoleSearchBO setNeq_createdTime(Date neq_createdTime)
        {
            this.neq_createdTime = neq_createdTime;
            return this;
        }
		
        public Date getGt_createdTime()
        {
            return gt_createdTime;
        }

        public RoleSearchBO setGt_createdTime(Date gt_createdTime)
        {
            this.gt_createdTime = gt_createdTime;
            return this;
        }

        public Date getLt_createdTime()
        {
            return lt_createdTime;
        }

        public RoleSearchBO setLt_createdTime(Date lt_createdTime)
        {
            this.lt_createdTime = lt_createdTime;
            return this;
        }

        public Date getEgt_createdTime()
        {
            return egt_createdTime;
        }

        public RoleSearchBO setEgt_createdTime(Date egt_createdTime)
        {
            this.egt_createdTime = egt_createdTime;
            return this;
        }

        public Date getElt_createdTime()
        {
            return elt_createdTime;
        }

        public RoleSearchBO setElt_createdTime(Date elt_createdTime)
        {
            this.elt_createdTime = elt_createdTime;
            return this;
        }
		
		public String getBetween_createdTime()
        {
            return between_createdTime;
        }

        public RoleSearchBO setBetween_createdTime(String between_createdTime)
        {
            this.between_createdTime = between_createdTime;
            return this;
        }

        public String getNotbetween_createdTime()
        {
            return notbetween_createdTime;
        }

        public RoleSearchBO setNotbetween_createdTime(String notbetween_createdTime)
        {
            this.notbetween_createdTime = notbetween_createdTime;
            return this;
        }

        public String getIn_createdTime()
        {
            return in_createdTime;
        }

        public RoleSearchBO setIn_createdTime(String in_createdTime)
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

        public RoleSearchBO setEq_updatedTime(Date eq_updatedTime)
        {
            this.eq_updatedTime = eq_updatedTime;
            return this;
        }

        public Date getNeq_updatedTime()
        {
            return neq_updatedTime;
        }

        public RoleSearchBO setNeq_updatedTime(Date neq_updatedTime)
        {
            this.neq_updatedTime = neq_updatedTime;
            return this;
        }
		
        public Date getGt_updatedTime()
        {
            return gt_updatedTime;
        }

        public RoleSearchBO setGt_updatedTime(Date gt_updatedTime)
        {
            this.gt_updatedTime = gt_updatedTime;
            return this;
        }

        public Date getLt_updatedTime()
        {
            return lt_updatedTime;
        }

        public RoleSearchBO setLt_updatedTime(Date lt_updatedTime)
        {
            this.lt_updatedTime = lt_updatedTime;
            return this;
        }

        public Date getEgt_updatedTime()
        {
            return egt_updatedTime;
        }

        public RoleSearchBO setEgt_updatedTime(Date egt_updatedTime)
        {
            this.egt_updatedTime = egt_updatedTime;
            return this;
        }

        public Date getElt_updatedTime()
        {
            return elt_updatedTime;
        }

        public RoleSearchBO setElt_updatedTime(Date elt_updatedTime)
        {
            this.elt_updatedTime = elt_updatedTime;
            return this;
        }
		
		public String getBetween_updatedTime()
        {
            return between_updatedTime;
        }

        public RoleSearchBO setBetween_updatedTime(String between_updatedTime)
        {
            this.between_updatedTime = between_updatedTime;
            return this;
        }

        public String getNotbetween_updatedTime()
        {
            return notbetween_updatedTime;
        }

        public RoleSearchBO setNotbetween_updatedTime(String notbetween_updatedTime)
        {
            this.notbetween_updatedTime = notbetween_updatedTime;
            return this;
        }

        public String getIn_updatedTime()
        {
            return in_updatedTime;
        }

        public RoleSearchBO setIn_updatedTime(String in_updatedTime)
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

        public RoleSearchBO setEq_createUser(String eq_createUser)
        {
            this.eq_createUser = eq_createUser;
            return this;
        }

        public String getNeq_createUser()
        {
            return neq_createUser;
        }

        public RoleSearchBO setNeq_createUser(String neq_createUser)
        {
            this.neq_createUser = neq_createUser;
            return this;
        }
		
        public String getLike_createUser()
        {
            return like_createUser;
        }

        public RoleSearchBO setLike_createUser(String like_createUser)
        {
            this.like_createUser = like_createUser;
            return this;
        }

        public String getLiker_createUser()
        {
            return liker_createUser;
        }

        public RoleSearchBO setLiker_createUser(String liker_createUser)
        {
            this.liker_createUser = liker_createUser;
            return this;
        }

        public String getLikel_createUser()
        {
            return likel_createUser;
        }

        public RoleSearchBO setLikel_createUser(String likel_createUser)
        {
            this.likel_createUser = likel_createUser;
            return this;
        }

        public String getNotlike_createUser()
        {
            return notlike_createUser;
        }

        public RoleSearchBO setNotlike_createUser(String notlike_createUser)
        {
            this.notlike_createUser = notlike_createUser;
            return this;
        }

        public String getNotliker_createUser()
        {
            return notliker_createUser;
        }

        public RoleSearchBO setNotliker_createUser(String notliker_createUser)
        {
            this.notliker_createUser = notliker_createUser;
            return this;
        }

        public String getNotlikel_createUser()
        {
            return notlikel_createUser;
        }

        public RoleSearchBO setNotlikel_createUser(String notlikel_createUser)
        {
            this.notlikel_createUser = notlikel_createUser;
            return this;
        }

        public String getIn_createUser()
        {
            return in_createUser;
        }

        public RoleSearchBO setIn_createUser(String in_createUser)
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

        public RoleSearchBO setEq_createUserId(BigInteger eq_createUserId)
        {
            this.eq_createUserId = eq_createUserId;
            return this;
        }

        public BigInteger getNeq_createUserId()
        {
            return neq_createUserId;
        }

        public RoleSearchBO setNeq_createUserId(BigInteger neq_createUserId)
        {
            this.neq_createUserId = neq_createUserId;
            return this;
        }
		
        public String getLike_createUserId()
        {
            return like_createUserId;
        }

        public RoleSearchBO setLike_createUserId(String like_createUserId)
        {
            this.like_createUserId = like_createUserId;
            return this;
        }

        public String getLiker_createUserId()
        {
            return liker_createUserId;
        }

        public RoleSearchBO setLiker_createUserId(String liker_createUserId)
        {
            this.liker_createUserId = liker_createUserId;
            return this;
        }

        public String getLikel_createUserId()
        {
            return likel_createUserId;
        }

        public RoleSearchBO setLikel_createUserId(String likel_createUserId)
        {
            this.likel_createUserId = likel_createUserId;
            return this;
        }

        public String getNotlike_createUserId()
        {
            return notlike_createUserId;
        }

        public RoleSearchBO setNotlike_createUserId(String notlike_createUserId)
        {
            this.notlike_createUserId = notlike_createUserId;
            return this;
        }

        public String getNotliker_createUserId()
        {
            return notliker_createUserId;
        }

        public RoleSearchBO setNotliker_createUserId(String notliker_createUserId)
        {
            this.notliker_createUserId = notliker_createUserId;
            return this;
        }

        public String getNotlikel_createUserId()
        {
            return notlikel_createUserId;
        }

        public RoleSearchBO setNotlikel_createUserId(String notlikel_createUserId)
        {
            this.notlikel_createUserId = notlikel_createUserId;
            return this;
        }

        public String getIn_createUserId()
        {
            return in_createUserId;
        }

        public RoleSearchBO setIn_createUserId(String in_createUserId)
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

        public RoleSearchBO setEq_updateUser(String eq_updateUser)
        {
            this.eq_updateUser = eq_updateUser;
            return this;
        }

        public String getNeq_updateUser()
        {
            return neq_updateUser;
        }

        public RoleSearchBO setNeq_updateUser(String neq_updateUser)
        {
            this.neq_updateUser = neq_updateUser;
            return this;
        }
		
        public String getLike_updateUser()
        {
            return like_updateUser;
        }

        public RoleSearchBO setLike_updateUser(String like_updateUser)
        {
            this.like_updateUser = like_updateUser;
            return this;
        }

        public String getLiker_updateUser()
        {
            return liker_updateUser;
        }

        public RoleSearchBO setLiker_updateUser(String liker_updateUser)
        {
            this.liker_updateUser = liker_updateUser;
            return this;
        }

        public String getLikel_updateUser()
        {
            return likel_updateUser;
        }

        public RoleSearchBO setLikel_updateUser(String likel_updateUser)
        {
            this.likel_updateUser = likel_updateUser;
            return this;
        }

        public String getNotlike_updateUser()
        {
            return notlike_updateUser;
        }

        public RoleSearchBO setNotlike_updateUser(String notlike_updateUser)
        {
            this.notlike_updateUser = notlike_updateUser;
            return this;
        }

        public String getNotliker_updateUser()
        {
            return notliker_updateUser;
        }

        public RoleSearchBO setNotliker_updateUser(String notliker_updateUser)
        {
            this.notliker_updateUser = notliker_updateUser;
            return this;
        }

        public String getNotlikel_updateUser()
        {
            return notlikel_updateUser;
        }

        public RoleSearchBO setNotlikel_updateUser(String notlikel_updateUser)
        {
            this.notlikel_updateUser = notlikel_updateUser;
            return this;
        }

        public String getIn_updateUser()
        {
            return in_updateUser;
        }

        public RoleSearchBO setIn_updateUser(String in_updateUser)
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

        public RoleSearchBO setEq_updateUserId(BigInteger eq_updateUserId)
        {
            this.eq_updateUserId = eq_updateUserId;
            return this;
        }

        public BigInteger getNeq_updateUserId()
        {
            return neq_updateUserId;
        }

        public RoleSearchBO setNeq_updateUserId(BigInteger neq_updateUserId)
        {
            this.neq_updateUserId = neq_updateUserId;
            return this;
        }
		
        public String getLike_updateUserId()
        {
            return like_updateUserId;
        }

        public RoleSearchBO setLike_updateUserId(String like_updateUserId)
        {
            this.like_updateUserId = like_updateUserId;
            return this;
        }

        public String getLiker_updateUserId()
        {
            return liker_updateUserId;
        }

        public RoleSearchBO setLiker_updateUserId(String liker_updateUserId)
        {
            this.liker_updateUserId = liker_updateUserId;
            return this;
        }

        public String getLikel_updateUserId()
        {
            return likel_updateUserId;
        }

        public RoleSearchBO setLikel_updateUserId(String likel_updateUserId)
        {
            this.likel_updateUserId = likel_updateUserId;
            return this;
        }

        public String getNotlike_updateUserId()
        {
            return notlike_updateUserId;
        }

        public RoleSearchBO setNotlike_updateUserId(String notlike_updateUserId)
        {
            this.notlike_updateUserId = notlike_updateUserId;
            return this;
        }

        public String getNotliker_updateUserId()
        {
            return notliker_updateUserId;
        }

        public RoleSearchBO setNotliker_updateUserId(String notliker_updateUserId)
        {
            this.notliker_updateUserId = notliker_updateUserId;
            return this;
        }

        public String getNotlikel_updateUserId()
        {
            return notlikel_updateUserId;
        }

        public RoleSearchBO setNotlikel_updateUserId(String notlikel_updateUserId)
        {
            this.notlikel_updateUserId = notlikel_updateUserId;
            return this;
        }

        public String getIn_updateUserId()
        {
            return in_updateUserId;
        }

        public RoleSearchBO setIn_updateUserId(String in_updateUserId)
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
