package com.wingflare.facade.module.base.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.math.BigInteger;
import java.util.Date;

/**
 * DictSearchBo
 * 
 * @author naizui_ycx
 * @date Sat Mar 04 17:48:17 CST 2023
 */
public class DictSearchBO extends BaseSearchBo
{

        private BigInteger eq_dictId;

        private BigInteger neq_dictId;
		
        private String in_dictId;

        private String notin_dictId;

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

        private String eq_dictType;

        private String neq_dictType;
		
		private String like_dictType;

        private String liker_dictType;

        private String likel_dictType;

        private String notlike_dictType;

        private String notliker_dictType;

        private String notlikel_dictType;
		
        private String in_dictType;

        private String notin_dictType;

        private String eq_dictCode;

        private String neq_dictCode;
		
		private String like_dictCode;

        private String liker_dictCode;

        private String likel_dictCode;

        private String notlike_dictCode;

        private String notliker_dictCode;

        private String notlikel_dictCode;
		
        private String in_dictCode;

        private String notin_dictCode;

        private String eq_dictName;

        private String neq_dictName;
		
		private String like_dictName;

        private String liker_dictName;

        private String likel_dictName;

        private String notlike_dictName;

        private String notliker_dictName;

        private String notlikel_dictName;
		
        private String in_dictName;

        private String notin_dictName;

        private String eq_dictValue;

        private String neq_dictValue;
		
		private String like_dictValue;

        private String liker_dictValue;

        private String likel_dictValue;

        private String notlike_dictValue;

        private String notliker_dictValue;

        private String notlikel_dictValue;
		
        private String in_dictValue;

        private String notin_dictValue;

        private String eq_dictText;

        private String neq_dictText;
		
		private String like_dictText;

        private String liker_dictText;

        private String likel_dictText;

        private String notlike_dictText;

        private String notliker_dictText;

        private String notlikel_dictText;
		
        private String in_dictText;

        private String notin_dictText;

        private Integer eq_sort;

        private Integer neq_sort;
		
        private Integer gt_sort;

        private Integer lt_sort;

        private Integer egt_sort;

        private Integer elt_sort;
		
		private String between_sort;

        private String notbetween_sort;
		
        private String in_sort;

        private String notin_sort;

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


        public BigInteger getEq_dictId()
        {
            return eq_dictId;
        }

        public DictSearchBO setEq_dictId(BigInteger eq_dictId)
        {
            this.eq_dictId = eq_dictId;
            return this;
        }

        public BigInteger getNeq_dictId()
        {
            return neq_dictId;
        }

        public DictSearchBO setNeq_dictId(BigInteger neq_dictId)
        {
            this.neq_dictId = neq_dictId;
            return this;
        }

        public String getIn_dictId()
        {
            return in_dictId;
        }

        public DictSearchBO setIn_dictId(String in_dictId)
        {
            this.in_dictId = in_dictId;
            return this;
        }

        public String getNotin_dictId()
        {
            return notin_dictId;
        }

        public void setNotin_dictId(String notin_dictId)
        {
            this.notin_dictId = notin_dictId;
        }

        public Integer getEq_state()
        {
            return eq_state;
        }

        public DictSearchBO setEq_state(Integer eq_state)
        {
            this.eq_state = eq_state;
            return this;
        }

        public Integer getNeq_state()
        {
            return neq_state;
        }

        public DictSearchBO setNeq_state(Integer neq_state)
        {
            this.neq_state = neq_state;
            return this;
        }
		
        public Integer getGt_state()
        {
            return gt_state;
        }

        public DictSearchBO setGt_state(Integer gt_state)
        {
            this.gt_state = gt_state;
            return this;
        }

        public Integer getLt_state()
        {
            return lt_state;
        }

        public DictSearchBO setLt_state(Integer lt_state)
        {
            this.lt_state = lt_state;
            return this;
        }

        public Integer getEgt_state()
        {
            return egt_state;
        }

        public DictSearchBO setEgt_state(Integer egt_state)
        {
            this.egt_state = egt_state;
            return this;
        }

        public Integer getElt_state()
        {
            return elt_state;
        }

        public DictSearchBO setElt_state(Integer elt_state)
        {
            this.elt_state = elt_state;
            return this;
        }
		
		public String getBetween_state()
        {
            return between_state;
        }

        public DictSearchBO setBetween_state(String between_state)
        {
            this.between_state = between_state;
            return this;
        }

        public String getNotbetween_state()
        {
            return notbetween_state;
        }

        public DictSearchBO setNotbetween_state(String notbetween_state)
        {
            this.notbetween_state = notbetween_state;
            return this;
        }

        public String getIn_state()
        {
            return in_state;
        }

        public DictSearchBO setIn_state(String in_state)
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

        public String getEq_dictType()
        {
            return eq_dictType;
        }

        public DictSearchBO setEq_dictType(String eq_dictType)
        {
            this.eq_dictType = eq_dictType;
            return this;
        }

        public String getNeq_dictType()
        {
            return neq_dictType;
        }

        public DictSearchBO setNeq_dictType(String neq_dictType)
        {
            this.neq_dictType = neq_dictType;
            return this;
        }
		
        public String getLike_dictType()
        {
            return like_dictType;
        }

        public DictSearchBO setLike_dictType(String like_dictType)
        {
            this.like_dictType = like_dictType;
            return this;
        }

        public String getLiker_dictType()
        {
            return liker_dictType;
        }

        public DictSearchBO setLiker_dictType(String liker_dictType)
        {
            this.liker_dictType = liker_dictType;
            return this;
        }

        public String getLikel_dictType()
        {
            return likel_dictType;
        }

        public DictSearchBO setLikel_dictType(String likel_dictType)
        {
            this.likel_dictType = likel_dictType;
            return this;
        }

        public String getNotlike_dictType()
        {
            return notlike_dictType;
        }

        public DictSearchBO setNotlike_dictType(String notlike_dictType)
        {
            this.notlike_dictType = notlike_dictType;
            return this;
        }

        public String getNotliker_dictType()
        {
            return notliker_dictType;
        }

        public DictSearchBO setNotliker_dictType(String notliker_dictType)
        {
            this.notliker_dictType = notliker_dictType;
            return this;
        }

        public String getNotlikel_dictType()
        {
            return notlikel_dictType;
        }

        public DictSearchBO setNotlikel_dictType(String notlikel_dictType)
        {
            this.notlikel_dictType = notlikel_dictType;
            return this;
        }

        public String getIn_dictType()
        {
            return in_dictType;
        }

        public DictSearchBO setIn_dictType(String in_dictType)
        {
            this.in_dictType = in_dictType;
            return this;
        }

        public String getNotin_dictType()
        {
            return notin_dictType;
        }

        public void setNotin_dictType(String notin_dictType)
        {
            this.notin_dictType = notin_dictType;
        }

        public String getEq_dictCode()
        {
            return eq_dictCode;
        }

        public DictSearchBO setEq_dictCode(String eq_dictCode)
        {
            this.eq_dictCode = eq_dictCode;
            return this;
        }

        public String getNeq_dictCode()
        {
            return neq_dictCode;
        }

        public DictSearchBO setNeq_dictCode(String neq_dictCode)
        {
            this.neq_dictCode = neq_dictCode;
            return this;
        }
		
        public String getLike_dictCode()
        {
            return like_dictCode;
        }

        public DictSearchBO setLike_dictCode(String like_dictCode)
        {
            this.like_dictCode = like_dictCode;
            return this;
        }

        public String getLiker_dictCode()
        {
            return liker_dictCode;
        }

        public DictSearchBO setLiker_dictCode(String liker_dictCode)
        {
            this.liker_dictCode = liker_dictCode;
            return this;
        }

        public String getLikel_dictCode()
        {
            return likel_dictCode;
        }

        public DictSearchBO setLikel_dictCode(String likel_dictCode)
        {
            this.likel_dictCode = likel_dictCode;
            return this;
        }

        public String getNotlike_dictCode()
        {
            return notlike_dictCode;
        }

        public DictSearchBO setNotlike_dictCode(String notlike_dictCode)
        {
            this.notlike_dictCode = notlike_dictCode;
            return this;
        }

        public String getNotliker_dictCode()
        {
            return notliker_dictCode;
        }

        public DictSearchBO setNotliker_dictCode(String notliker_dictCode)
        {
            this.notliker_dictCode = notliker_dictCode;
            return this;
        }

        public String getNotlikel_dictCode()
        {
            return notlikel_dictCode;
        }

        public DictSearchBO setNotlikel_dictCode(String notlikel_dictCode)
        {
            this.notlikel_dictCode = notlikel_dictCode;
            return this;
        }

        public String getIn_dictCode()
        {
            return in_dictCode;
        }

        public DictSearchBO setIn_dictCode(String in_dictCode)
        {
            this.in_dictCode = in_dictCode;
            return this;
        }

        public String getNotin_dictCode()
        {
            return notin_dictCode;
        }

        public void setNotin_dictCode(String notin_dictCode)
        {
            this.notin_dictCode = notin_dictCode;
        }

        public String getEq_dictName()
        {
            return eq_dictName;
        }

        public DictSearchBO setEq_dictName(String eq_dictName)
        {
            this.eq_dictName = eq_dictName;
            return this;
        }

        public String getNeq_dictName()
        {
            return neq_dictName;
        }

        public DictSearchBO setNeq_dictName(String neq_dictName)
        {
            this.neq_dictName = neq_dictName;
            return this;
        }
		
        public String getLike_dictName()
        {
            return like_dictName;
        }

        public DictSearchBO setLike_dictName(String like_dictName)
        {
            this.like_dictName = like_dictName;
            return this;
        }

        public String getLiker_dictName()
        {
            return liker_dictName;
        }

        public DictSearchBO setLiker_dictName(String liker_dictName)
        {
            this.liker_dictName = liker_dictName;
            return this;
        }

        public String getLikel_dictName()
        {
            return likel_dictName;
        }

        public DictSearchBO setLikel_dictName(String likel_dictName)
        {
            this.likel_dictName = likel_dictName;
            return this;
        }

        public String getNotlike_dictName()
        {
            return notlike_dictName;
        }

        public DictSearchBO setNotlike_dictName(String notlike_dictName)
        {
            this.notlike_dictName = notlike_dictName;
            return this;
        }

        public String getNotliker_dictName()
        {
            return notliker_dictName;
        }

        public DictSearchBO setNotliker_dictName(String notliker_dictName)
        {
            this.notliker_dictName = notliker_dictName;
            return this;
        }

        public String getNotlikel_dictName()
        {
            return notlikel_dictName;
        }

        public DictSearchBO setNotlikel_dictName(String notlikel_dictName)
        {
            this.notlikel_dictName = notlikel_dictName;
            return this;
        }

        public String getIn_dictName()
        {
            return in_dictName;
        }

        public DictSearchBO setIn_dictName(String in_dictName)
        {
            this.in_dictName = in_dictName;
            return this;
        }

        public String getNotin_dictName()
        {
            return notin_dictName;
        }

        public void setNotin_dictName(String notin_dictName)
        {
            this.notin_dictName = notin_dictName;
        }

        public String getEq_dictValue()
        {
            return eq_dictValue;
        }

        public DictSearchBO setEq_dictValue(String eq_dictValue)
        {
            this.eq_dictValue = eq_dictValue;
            return this;
        }

        public String getNeq_dictValue()
        {
            return neq_dictValue;
        }

        public DictSearchBO setNeq_dictValue(String neq_dictValue)
        {
            this.neq_dictValue = neq_dictValue;
            return this;
        }
		
        public String getLike_dictValue()
        {
            return like_dictValue;
        }

        public DictSearchBO setLike_dictValue(String like_dictValue)
        {
            this.like_dictValue = like_dictValue;
            return this;
        }

        public String getLiker_dictValue()
        {
            return liker_dictValue;
        }

        public DictSearchBO setLiker_dictValue(String liker_dictValue)
        {
            this.liker_dictValue = liker_dictValue;
            return this;
        }

        public String getLikel_dictValue()
        {
            return likel_dictValue;
        }

        public DictSearchBO setLikel_dictValue(String likel_dictValue)
        {
            this.likel_dictValue = likel_dictValue;
            return this;
        }

        public String getNotlike_dictValue()
        {
            return notlike_dictValue;
        }

        public DictSearchBO setNotlike_dictValue(String notlike_dictValue)
        {
            this.notlike_dictValue = notlike_dictValue;
            return this;
        }

        public String getNotliker_dictValue()
        {
            return notliker_dictValue;
        }

        public DictSearchBO setNotliker_dictValue(String notliker_dictValue)
        {
            this.notliker_dictValue = notliker_dictValue;
            return this;
        }

        public String getNotlikel_dictValue()
        {
            return notlikel_dictValue;
        }

        public DictSearchBO setNotlikel_dictValue(String notlikel_dictValue)
        {
            this.notlikel_dictValue = notlikel_dictValue;
            return this;
        }

        public String getIn_dictValue()
        {
            return in_dictValue;
        }

        public DictSearchBO setIn_dictValue(String in_dictValue)
        {
            this.in_dictValue = in_dictValue;
            return this;
        }

        public String getNotin_dictValue()
        {
            return notin_dictValue;
        }

        public void setNotin_dictValue(String notin_dictValue)
        {
            this.notin_dictValue = notin_dictValue;
        }

        public String getEq_dictText()
        {
            return eq_dictText;
        }

        public DictSearchBO setEq_dictText(String eq_dictText)
        {
            this.eq_dictText = eq_dictText;
            return this;
        }

        public String getNeq_dictText()
        {
            return neq_dictText;
        }

        public DictSearchBO setNeq_dictText(String neq_dictText)
        {
            this.neq_dictText = neq_dictText;
            return this;
        }
		
        public String getLike_dictText()
        {
            return like_dictText;
        }

        public DictSearchBO setLike_dictText(String like_dictText)
        {
            this.like_dictText = like_dictText;
            return this;
        }

        public String getLiker_dictText()
        {
            return liker_dictText;
        }

        public DictSearchBO setLiker_dictText(String liker_dictText)
        {
            this.liker_dictText = liker_dictText;
            return this;
        }

        public String getLikel_dictText()
        {
            return likel_dictText;
        }

        public DictSearchBO setLikel_dictText(String likel_dictText)
        {
            this.likel_dictText = likel_dictText;
            return this;
        }

        public String getNotlike_dictText()
        {
            return notlike_dictText;
        }

        public DictSearchBO setNotlike_dictText(String notlike_dictText)
        {
            this.notlike_dictText = notlike_dictText;
            return this;
        }

        public String getNotliker_dictText()
        {
            return notliker_dictText;
        }

        public DictSearchBO setNotliker_dictText(String notliker_dictText)
        {
            this.notliker_dictText = notliker_dictText;
            return this;
        }

        public String getNotlikel_dictText()
        {
            return notlikel_dictText;
        }

        public DictSearchBO setNotlikel_dictText(String notlikel_dictText)
        {
            this.notlikel_dictText = notlikel_dictText;
            return this;
        }

        public String getIn_dictText()
        {
            return in_dictText;
        }

        public DictSearchBO setIn_dictText(String in_dictText)
        {
            this.in_dictText = in_dictText;
            return this;
        }

        public String getNotin_dictText()
        {
            return notin_dictText;
        }

        public void setNotin_dictText(String notin_dictText)
        {
            this.notin_dictText = notin_dictText;
        }

        public Integer getEq_sort()
        {
            return eq_sort;
        }

        public DictSearchBO setEq_sort(Integer eq_sort)
        {
            this.eq_sort = eq_sort;
            return this;
        }

        public Integer getNeq_sort()
        {
            return neq_sort;
        }

        public DictSearchBO setNeq_sort(Integer neq_sort)
        {
            this.neq_sort = neq_sort;
            return this;
        }
		
        public Integer getGt_sort()
        {
            return gt_sort;
        }

        public DictSearchBO setGt_sort(Integer gt_sort)
        {
            this.gt_sort = gt_sort;
            return this;
        }

        public Integer getLt_sort()
        {
            return lt_sort;
        }

        public DictSearchBO setLt_sort(Integer lt_sort)
        {
            this.lt_sort = lt_sort;
            return this;
        }

        public Integer getEgt_sort()
        {
            return egt_sort;
        }

        public DictSearchBO setEgt_sort(Integer egt_sort)
        {
            this.egt_sort = egt_sort;
            return this;
        }

        public Integer getElt_sort()
        {
            return elt_sort;
        }

        public DictSearchBO setElt_sort(Integer elt_sort)
        {
            this.elt_sort = elt_sort;
            return this;
        }
		
		public String getBetween_sort()
        {
            return between_sort;
        }

        public DictSearchBO setBetween_sort(String between_sort)
        {
            this.between_sort = between_sort;
            return this;
        }

        public String getNotbetween_sort()
        {
            return notbetween_sort;
        }

        public DictSearchBO setNotbetween_sort(String notbetween_sort)
        {
            this.notbetween_sort = notbetween_sort;
            return this;
        }

        public String getIn_sort()
        {
            return in_sort;
        }

        public DictSearchBO setIn_sort(String in_sort)
        {
            this.in_sort = in_sort;
            return this;
        }

        public String getNotin_sort()
        {
            return notin_sort;
        }

        public void setNotin_sort(String notin_sort)
        {
            this.notin_sort = notin_sort;
        }

        public Date getEq_createdTime()
        {
            return eq_createdTime;
        }

        public DictSearchBO setEq_createdTime(Date eq_createdTime)
        {
            this.eq_createdTime = eq_createdTime;
            return this;
        }

        public Date getNeq_createdTime()
        {
            return neq_createdTime;
        }

        public DictSearchBO setNeq_createdTime(Date neq_createdTime)
        {
            this.neq_createdTime = neq_createdTime;
            return this;
        }
		
        public Date getGt_createdTime()
        {
            return gt_createdTime;
        }

        public DictSearchBO setGt_createdTime(Date gt_createdTime)
        {
            this.gt_createdTime = gt_createdTime;
            return this;
        }

        public Date getLt_createdTime()
        {
            return lt_createdTime;
        }

        public DictSearchBO setLt_createdTime(Date lt_createdTime)
        {
            this.lt_createdTime = lt_createdTime;
            return this;
        }

        public Date getEgt_createdTime()
        {
            return egt_createdTime;
        }

        public DictSearchBO setEgt_createdTime(Date egt_createdTime)
        {
            this.egt_createdTime = egt_createdTime;
            return this;
        }

        public Date getElt_createdTime()
        {
            return elt_createdTime;
        }

        public DictSearchBO setElt_createdTime(Date elt_createdTime)
        {
            this.elt_createdTime = elt_createdTime;
            return this;
        }
		
		public String getBetween_createdTime()
        {
            return between_createdTime;
        }

        public DictSearchBO setBetween_createdTime(String between_createdTime)
        {
            this.between_createdTime = between_createdTime;
            return this;
        }

        public String getNotbetween_createdTime()
        {
            return notbetween_createdTime;
        }

        public DictSearchBO setNotbetween_createdTime(String notbetween_createdTime)
        {
            this.notbetween_createdTime = notbetween_createdTime;
            return this;
        }

        public String getIn_createdTime()
        {
            return in_createdTime;
        }

        public DictSearchBO setIn_createdTime(String in_createdTime)
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

        public DictSearchBO setEq_updatedTime(Date eq_updatedTime)
        {
            this.eq_updatedTime = eq_updatedTime;
            return this;
        }

        public Date getNeq_updatedTime()
        {
            return neq_updatedTime;
        }

        public DictSearchBO setNeq_updatedTime(Date neq_updatedTime)
        {
            this.neq_updatedTime = neq_updatedTime;
            return this;
        }
		
        public Date getGt_updatedTime()
        {
            return gt_updatedTime;
        }

        public DictSearchBO setGt_updatedTime(Date gt_updatedTime)
        {
            this.gt_updatedTime = gt_updatedTime;
            return this;
        }

        public Date getLt_updatedTime()
        {
            return lt_updatedTime;
        }

        public DictSearchBO setLt_updatedTime(Date lt_updatedTime)
        {
            this.lt_updatedTime = lt_updatedTime;
            return this;
        }

        public Date getEgt_updatedTime()
        {
            return egt_updatedTime;
        }

        public DictSearchBO setEgt_updatedTime(Date egt_updatedTime)
        {
            this.egt_updatedTime = egt_updatedTime;
            return this;
        }

        public Date getElt_updatedTime()
        {
            return elt_updatedTime;
        }

        public DictSearchBO setElt_updatedTime(Date elt_updatedTime)
        {
            this.elt_updatedTime = elt_updatedTime;
            return this;
        }
		
		public String getBetween_updatedTime()
        {
            return between_updatedTime;
        }

        public DictSearchBO setBetween_updatedTime(String between_updatedTime)
        {
            this.between_updatedTime = between_updatedTime;
            return this;
        }

        public String getNotbetween_updatedTime()
        {
            return notbetween_updatedTime;
        }

        public DictSearchBO setNotbetween_updatedTime(String notbetween_updatedTime)
        {
            this.notbetween_updatedTime = notbetween_updatedTime;
            return this;
        }

        public String getIn_updatedTime()
        {
            return in_updatedTime;
        }

        public DictSearchBO setIn_updatedTime(String in_updatedTime)
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

        public DictSearchBO setEq_createUser(String eq_createUser)
        {
            this.eq_createUser = eq_createUser;
            return this;
        }

        public String getNeq_createUser()
        {
            return neq_createUser;
        }

        public DictSearchBO setNeq_createUser(String neq_createUser)
        {
            this.neq_createUser = neq_createUser;
            return this;
        }
		
        public String getLike_createUser()
        {
            return like_createUser;
        }

        public DictSearchBO setLike_createUser(String like_createUser)
        {
            this.like_createUser = like_createUser;
            return this;
        }

        public String getLiker_createUser()
        {
            return liker_createUser;
        }

        public DictSearchBO setLiker_createUser(String liker_createUser)
        {
            this.liker_createUser = liker_createUser;
            return this;
        }

        public String getLikel_createUser()
        {
            return likel_createUser;
        }

        public DictSearchBO setLikel_createUser(String likel_createUser)
        {
            this.likel_createUser = likel_createUser;
            return this;
        }

        public String getNotlike_createUser()
        {
            return notlike_createUser;
        }

        public DictSearchBO setNotlike_createUser(String notlike_createUser)
        {
            this.notlike_createUser = notlike_createUser;
            return this;
        }

        public String getNotliker_createUser()
        {
            return notliker_createUser;
        }

        public DictSearchBO setNotliker_createUser(String notliker_createUser)
        {
            this.notliker_createUser = notliker_createUser;
            return this;
        }

        public String getNotlikel_createUser()
        {
            return notlikel_createUser;
        }

        public DictSearchBO setNotlikel_createUser(String notlikel_createUser)
        {
            this.notlikel_createUser = notlikel_createUser;
            return this;
        }

        public String getIn_createUser()
        {
            return in_createUser;
        }

        public DictSearchBO setIn_createUser(String in_createUser)
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

        public DictSearchBO setEq_createUserId(BigInteger eq_createUserId)
        {
            this.eq_createUserId = eq_createUserId;
            return this;
        }

        public BigInteger getNeq_createUserId()
        {
            return neq_createUserId;
        }

        public DictSearchBO setNeq_createUserId(BigInteger neq_createUserId)
        {
            this.neq_createUserId = neq_createUserId;
            return this;
        }
		
        public String getLike_createUserId()
        {
            return like_createUserId;
        }

        public DictSearchBO setLike_createUserId(String like_createUserId)
        {
            this.like_createUserId = like_createUserId;
            return this;
        }

        public String getLiker_createUserId()
        {
            return liker_createUserId;
        }

        public DictSearchBO setLiker_createUserId(String liker_createUserId)
        {
            this.liker_createUserId = liker_createUserId;
            return this;
        }

        public String getLikel_createUserId()
        {
            return likel_createUserId;
        }

        public DictSearchBO setLikel_createUserId(String likel_createUserId)
        {
            this.likel_createUserId = likel_createUserId;
            return this;
        }

        public String getNotlike_createUserId()
        {
            return notlike_createUserId;
        }

        public DictSearchBO setNotlike_createUserId(String notlike_createUserId)
        {
            this.notlike_createUserId = notlike_createUserId;
            return this;
        }

        public String getNotliker_createUserId()
        {
            return notliker_createUserId;
        }

        public DictSearchBO setNotliker_createUserId(String notliker_createUserId)
        {
            this.notliker_createUserId = notliker_createUserId;
            return this;
        }

        public String getNotlikel_createUserId()
        {
            return notlikel_createUserId;
        }

        public DictSearchBO setNotlikel_createUserId(String notlikel_createUserId)
        {
            this.notlikel_createUserId = notlikel_createUserId;
            return this;
        }

        public String getIn_createUserId()
        {
            return in_createUserId;
        }

        public DictSearchBO setIn_createUserId(String in_createUserId)
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

        public DictSearchBO setEq_updateUser(String eq_updateUser)
        {
            this.eq_updateUser = eq_updateUser;
            return this;
        }

        public String getNeq_updateUser()
        {
            return neq_updateUser;
        }

        public DictSearchBO setNeq_updateUser(String neq_updateUser)
        {
            this.neq_updateUser = neq_updateUser;
            return this;
        }
		
        public String getLike_updateUser()
        {
            return like_updateUser;
        }

        public DictSearchBO setLike_updateUser(String like_updateUser)
        {
            this.like_updateUser = like_updateUser;
            return this;
        }

        public String getLiker_updateUser()
        {
            return liker_updateUser;
        }

        public DictSearchBO setLiker_updateUser(String liker_updateUser)
        {
            this.liker_updateUser = liker_updateUser;
            return this;
        }

        public String getLikel_updateUser()
        {
            return likel_updateUser;
        }

        public DictSearchBO setLikel_updateUser(String likel_updateUser)
        {
            this.likel_updateUser = likel_updateUser;
            return this;
        }

        public String getNotlike_updateUser()
        {
            return notlike_updateUser;
        }

        public DictSearchBO setNotlike_updateUser(String notlike_updateUser)
        {
            this.notlike_updateUser = notlike_updateUser;
            return this;
        }

        public String getNotliker_updateUser()
        {
            return notliker_updateUser;
        }

        public DictSearchBO setNotliker_updateUser(String notliker_updateUser)
        {
            this.notliker_updateUser = notliker_updateUser;
            return this;
        }

        public String getNotlikel_updateUser()
        {
            return notlikel_updateUser;
        }

        public DictSearchBO setNotlikel_updateUser(String notlikel_updateUser)
        {
            this.notlikel_updateUser = notlikel_updateUser;
            return this;
        }

        public String getIn_updateUser()
        {
            return in_updateUser;
        }

        public DictSearchBO setIn_updateUser(String in_updateUser)
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

        public DictSearchBO setEq_updateUserId(BigInteger eq_updateUserId)
        {
            this.eq_updateUserId = eq_updateUserId;
            return this;
        }

        public BigInteger getNeq_updateUserId()
        {
            return neq_updateUserId;
        }

        public DictSearchBO setNeq_updateUserId(BigInteger neq_updateUserId)
        {
            this.neq_updateUserId = neq_updateUserId;
            return this;
        }
		
        public String getLike_updateUserId()
        {
            return like_updateUserId;
        }

        public DictSearchBO setLike_updateUserId(String like_updateUserId)
        {
            this.like_updateUserId = like_updateUserId;
            return this;
        }

        public String getLiker_updateUserId()
        {
            return liker_updateUserId;
        }

        public DictSearchBO setLiker_updateUserId(String liker_updateUserId)
        {
            this.liker_updateUserId = liker_updateUserId;
            return this;
        }

        public String getLikel_updateUserId()
        {
            return likel_updateUserId;
        }

        public DictSearchBO setLikel_updateUserId(String likel_updateUserId)
        {
            this.likel_updateUserId = likel_updateUserId;
            return this;
        }

        public String getNotlike_updateUserId()
        {
            return notlike_updateUserId;
        }

        public DictSearchBO setNotlike_updateUserId(String notlike_updateUserId)
        {
            this.notlike_updateUserId = notlike_updateUserId;
            return this;
        }

        public String getNotliker_updateUserId()
        {
            return notliker_updateUserId;
        }

        public DictSearchBO setNotliker_updateUserId(String notliker_updateUserId)
        {
            this.notliker_updateUserId = notliker_updateUserId;
            return this;
        }

        public String getNotlikel_updateUserId()
        {
            return notlikel_updateUserId;
        }

        public DictSearchBO setNotlikel_updateUserId(String notlikel_updateUserId)
        {
            this.notlikel_updateUserId = notlikel_updateUserId;
            return this;
        }

        public String getIn_updateUserId()
        {
            return in_updateUserId;
        }

        public DictSearchBO setIn_updateUserId(String in_updateUserId)
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
