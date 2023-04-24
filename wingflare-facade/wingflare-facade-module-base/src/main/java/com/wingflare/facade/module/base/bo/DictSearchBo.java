package com.wingflare.facade.module.base.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.util.Date;

/**
 * DictSearchBo
 * 
 * @author naizui_ycx
 * @date Sat Mar 04 17:48:17 CST 2023
 */
public class DictSearchBo extends BaseSearchBo
{

        private String eq_dictId;

        private String neq_dictId;
		
        private String in_dictId;

        private String notin_dictId;

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


        public String getEq_dictId()
        {
            return eq_dictId;
        }

        public DictSearchBo setEq_dictId(String eq_dictId)
        {
            this.eq_dictId = eq_dictId;
            return this;
        }

        public String getNeq_dictId()
        {
            return neq_dictId;
        }

        public DictSearchBo setNeq_dictId(String neq_dictId)
        {
            this.neq_dictId = neq_dictId;
            return this;
        }

        public String getIn_dictId()
        {
            return in_dictId;
        }

        public DictSearchBo setIn_dictId(String in_dictId)
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

        public String getEq_systemCode()
        {
            return eq_systemCode;
        }

        public DictSearchBo setEq_systemCode(String eq_systemCode)
        {
            this.eq_systemCode = eq_systemCode;
            return this;
        }

        public String getNeq_systemCode()
        {
            return neq_systemCode;
        }

        public DictSearchBo setNeq_systemCode(String neq_systemCode)
        {
            this.neq_systemCode = neq_systemCode;
            return this;
        }
		
        public String getLike_systemCode()
        {
            return like_systemCode;
        }

        public DictSearchBo setLike_systemCode(String like_systemCode)
        {
            this.like_systemCode = like_systemCode;
            return this;
        }

        public String getLiker_systemCode()
        {
            return liker_systemCode;
        }

        public DictSearchBo setLiker_systemCode(String liker_systemCode)
        {
            this.liker_systemCode = liker_systemCode;
            return this;
        }

        public String getLikel_systemCode()
        {
            return likel_systemCode;
        }

        public DictSearchBo setLikel_systemCode(String likel_systemCode)
        {
            this.likel_systemCode = likel_systemCode;
            return this;
        }

        public String getNotlike_systemCode()
        {
            return notlike_systemCode;
        }

        public DictSearchBo setNotlike_systemCode(String notlike_systemCode)
        {
            this.notlike_systemCode = notlike_systemCode;
            return this;
        }

        public String getNotliker_systemCode()
        {
            return notliker_systemCode;
        }

        public DictSearchBo setNotliker_systemCode(String notliker_systemCode)
        {
            this.notliker_systemCode = notliker_systemCode;
            return this;
        }

        public String getNotlikel_systemCode()
        {
            return notlikel_systemCode;
        }

        public DictSearchBo setNotlikel_systemCode(String notlikel_systemCode)
        {
            this.notlikel_systemCode = notlikel_systemCode;
            return this;
        }

        public String getIn_systemCode()
        {
            return in_systemCode;
        }

        public DictSearchBo setIn_systemCode(String in_systemCode)
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

        public Integer getEq_state()
        {
            return eq_state;
        }

        public DictSearchBo setEq_state(Integer eq_state)
        {
            this.eq_state = eq_state;
            return this;
        }

        public Integer getNeq_state()
        {
            return neq_state;
        }

        public DictSearchBo setNeq_state(Integer neq_state)
        {
            this.neq_state = neq_state;
            return this;
        }
		
        public Integer getGt_state()
        {
            return gt_state;
        }

        public DictSearchBo setGt_state(Integer gt_state)
        {
            this.gt_state = gt_state;
            return this;
        }

        public Integer getLt_state()
        {
            return lt_state;
        }

        public DictSearchBo setLt_state(Integer lt_state)
        {
            this.lt_state = lt_state;
            return this;
        }

        public Integer getEgt_state()
        {
            return egt_state;
        }

        public DictSearchBo setEgt_state(Integer egt_state)
        {
            this.egt_state = egt_state;
            return this;
        }

        public Integer getElt_state()
        {
            return elt_state;
        }

        public DictSearchBo setElt_state(Integer elt_state)
        {
            this.elt_state = elt_state;
            return this;
        }
		
		public String getBetween_state()
        {
            return between_state;
        }

        public DictSearchBo setBetween_state(String between_state)
        {
            this.between_state = between_state;
            return this;
        }

        public String getNotbetween_state()
        {
            return notbetween_state;
        }

        public DictSearchBo setNotbetween_state(String notbetween_state)
        {
            this.notbetween_state = notbetween_state;
            return this;
        }

        public String getIn_state()
        {
            return in_state;
        }

        public DictSearchBo setIn_state(String in_state)
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

        public DictSearchBo setEq_dictType(String eq_dictType)
        {
            this.eq_dictType = eq_dictType;
            return this;
        }

        public String getNeq_dictType()
        {
            return neq_dictType;
        }

        public DictSearchBo setNeq_dictType(String neq_dictType)
        {
            this.neq_dictType = neq_dictType;
            return this;
        }
		
        public String getLike_dictType()
        {
            return like_dictType;
        }

        public DictSearchBo setLike_dictType(String like_dictType)
        {
            this.like_dictType = like_dictType;
            return this;
        }

        public String getLiker_dictType()
        {
            return liker_dictType;
        }

        public DictSearchBo setLiker_dictType(String liker_dictType)
        {
            this.liker_dictType = liker_dictType;
            return this;
        }

        public String getLikel_dictType()
        {
            return likel_dictType;
        }

        public DictSearchBo setLikel_dictType(String likel_dictType)
        {
            this.likel_dictType = likel_dictType;
            return this;
        }

        public String getNotlike_dictType()
        {
            return notlike_dictType;
        }

        public DictSearchBo setNotlike_dictType(String notlike_dictType)
        {
            this.notlike_dictType = notlike_dictType;
            return this;
        }

        public String getNotliker_dictType()
        {
            return notliker_dictType;
        }

        public DictSearchBo setNotliker_dictType(String notliker_dictType)
        {
            this.notliker_dictType = notliker_dictType;
            return this;
        }

        public String getNotlikel_dictType()
        {
            return notlikel_dictType;
        }

        public DictSearchBo setNotlikel_dictType(String notlikel_dictType)
        {
            this.notlikel_dictType = notlikel_dictType;
            return this;
        }

        public String getIn_dictType()
        {
            return in_dictType;
        }

        public DictSearchBo setIn_dictType(String in_dictType)
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

        public DictSearchBo setEq_dictCode(String eq_dictCode)
        {
            this.eq_dictCode = eq_dictCode;
            return this;
        }

        public String getNeq_dictCode()
        {
            return neq_dictCode;
        }

        public DictSearchBo setNeq_dictCode(String neq_dictCode)
        {
            this.neq_dictCode = neq_dictCode;
            return this;
        }
		
        public String getLike_dictCode()
        {
            return like_dictCode;
        }

        public DictSearchBo setLike_dictCode(String like_dictCode)
        {
            this.like_dictCode = like_dictCode;
            return this;
        }

        public String getLiker_dictCode()
        {
            return liker_dictCode;
        }

        public DictSearchBo setLiker_dictCode(String liker_dictCode)
        {
            this.liker_dictCode = liker_dictCode;
            return this;
        }

        public String getLikel_dictCode()
        {
            return likel_dictCode;
        }

        public DictSearchBo setLikel_dictCode(String likel_dictCode)
        {
            this.likel_dictCode = likel_dictCode;
            return this;
        }

        public String getNotlike_dictCode()
        {
            return notlike_dictCode;
        }

        public DictSearchBo setNotlike_dictCode(String notlike_dictCode)
        {
            this.notlike_dictCode = notlike_dictCode;
            return this;
        }

        public String getNotliker_dictCode()
        {
            return notliker_dictCode;
        }

        public DictSearchBo setNotliker_dictCode(String notliker_dictCode)
        {
            this.notliker_dictCode = notliker_dictCode;
            return this;
        }

        public String getNotlikel_dictCode()
        {
            return notlikel_dictCode;
        }

        public DictSearchBo setNotlikel_dictCode(String notlikel_dictCode)
        {
            this.notlikel_dictCode = notlikel_dictCode;
            return this;
        }

        public String getIn_dictCode()
        {
            return in_dictCode;
        }

        public DictSearchBo setIn_dictCode(String in_dictCode)
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

        public DictSearchBo setEq_dictName(String eq_dictName)
        {
            this.eq_dictName = eq_dictName;
            return this;
        }

        public String getNeq_dictName()
        {
            return neq_dictName;
        }

        public DictSearchBo setNeq_dictName(String neq_dictName)
        {
            this.neq_dictName = neq_dictName;
            return this;
        }
		
        public String getLike_dictName()
        {
            return like_dictName;
        }

        public DictSearchBo setLike_dictName(String like_dictName)
        {
            this.like_dictName = like_dictName;
            return this;
        }

        public String getLiker_dictName()
        {
            return liker_dictName;
        }

        public DictSearchBo setLiker_dictName(String liker_dictName)
        {
            this.liker_dictName = liker_dictName;
            return this;
        }

        public String getLikel_dictName()
        {
            return likel_dictName;
        }

        public DictSearchBo setLikel_dictName(String likel_dictName)
        {
            this.likel_dictName = likel_dictName;
            return this;
        }

        public String getNotlike_dictName()
        {
            return notlike_dictName;
        }

        public DictSearchBo setNotlike_dictName(String notlike_dictName)
        {
            this.notlike_dictName = notlike_dictName;
            return this;
        }

        public String getNotliker_dictName()
        {
            return notliker_dictName;
        }

        public DictSearchBo setNotliker_dictName(String notliker_dictName)
        {
            this.notliker_dictName = notliker_dictName;
            return this;
        }

        public String getNotlikel_dictName()
        {
            return notlikel_dictName;
        }

        public DictSearchBo setNotlikel_dictName(String notlikel_dictName)
        {
            this.notlikel_dictName = notlikel_dictName;
            return this;
        }

        public String getIn_dictName()
        {
            return in_dictName;
        }

        public DictSearchBo setIn_dictName(String in_dictName)
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

        public DictSearchBo setEq_dictValue(String eq_dictValue)
        {
            this.eq_dictValue = eq_dictValue;
            return this;
        }

        public String getNeq_dictValue()
        {
            return neq_dictValue;
        }

        public DictSearchBo setNeq_dictValue(String neq_dictValue)
        {
            this.neq_dictValue = neq_dictValue;
            return this;
        }
		
        public String getLike_dictValue()
        {
            return like_dictValue;
        }

        public DictSearchBo setLike_dictValue(String like_dictValue)
        {
            this.like_dictValue = like_dictValue;
            return this;
        }

        public String getLiker_dictValue()
        {
            return liker_dictValue;
        }

        public DictSearchBo setLiker_dictValue(String liker_dictValue)
        {
            this.liker_dictValue = liker_dictValue;
            return this;
        }

        public String getLikel_dictValue()
        {
            return likel_dictValue;
        }

        public DictSearchBo setLikel_dictValue(String likel_dictValue)
        {
            this.likel_dictValue = likel_dictValue;
            return this;
        }

        public String getNotlike_dictValue()
        {
            return notlike_dictValue;
        }

        public DictSearchBo setNotlike_dictValue(String notlike_dictValue)
        {
            this.notlike_dictValue = notlike_dictValue;
            return this;
        }

        public String getNotliker_dictValue()
        {
            return notliker_dictValue;
        }

        public DictSearchBo setNotliker_dictValue(String notliker_dictValue)
        {
            this.notliker_dictValue = notliker_dictValue;
            return this;
        }

        public String getNotlikel_dictValue()
        {
            return notlikel_dictValue;
        }

        public DictSearchBo setNotlikel_dictValue(String notlikel_dictValue)
        {
            this.notlikel_dictValue = notlikel_dictValue;
            return this;
        }

        public String getIn_dictValue()
        {
            return in_dictValue;
        }

        public DictSearchBo setIn_dictValue(String in_dictValue)
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

        public DictSearchBo setEq_dictText(String eq_dictText)
        {
            this.eq_dictText = eq_dictText;
            return this;
        }

        public String getNeq_dictText()
        {
            return neq_dictText;
        }

        public DictSearchBo setNeq_dictText(String neq_dictText)
        {
            this.neq_dictText = neq_dictText;
            return this;
        }
		
        public String getLike_dictText()
        {
            return like_dictText;
        }

        public DictSearchBo setLike_dictText(String like_dictText)
        {
            this.like_dictText = like_dictText;
            return this;
        }

        public String getLiker_dictText()
        {
            return liker_dictText;
        }

        public DictSearchBo setLiker_dictText(String liker_dictText)
        {
            this.liker_dictText = liker_dictText;
            return this;
        }

        public String getLikel_dictText()
        {
            return likel_dictText;
        }

        public DictSearchBo setLikel_dictText(String likel_dictText)
        {
            this.likel_dictText = likel_dictText;
            return this;
        }

        public String getNotlike_dictText()
        {
            return notlike_dictText;
        }

        public DictSearchBo setNotlike_dictText(String notlike_dictText)
        {
            this.notlike_dictText = notlike_dictText;
            return this;
        }

        public String getNotliker_dictText()
        {
            return notliker_dictText;
        }

        public DictSearchBo setNotliker_dictText(String notliker_dictText)
        {
            this.notliker_dictText = notliker_dictText;
            return this;
        }

        public String getNotlikel_dictText()
        {
            return notlikel_dictText;
        }

        public DictSearchBo setNotlikel_dictText(String notlikel_dictText)
        {
            this.notlikel_dictText = notlikel_dictText;
            return this;
        }

        public String getIn_dictText()
        {
            return in_dictText;
        }

        public DictSearchBo setIn_dictText(String in_dictText)
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

        public DictSearchBo setEq_sort(Integer eq_sort)
        {
            this.eq_sort = eq_sort;
            return this;
        }

        public Integer getNeq_sort()
        {
            return neq_sort;
        }

        public DictSearchBo setNeq_sort(Integer neq_sort)
        {
            this.neq_sort = neq_sort;
            return this;
        }
		
        public Integer getGt_sort()
        {
            return gt_sort;
        }

        public DictSearchBo setGt_sort(Integer gt_sort)
        {
            this.gt_sort = gt_sort;
            return this;
        }

        public Integer getLt_sort()
        {
            return lt_sort;
        }

        public DictSearchBo setLt_sort(Integer lt_sort)
        {
            this.lt_sort = lt_sort;
            return this;
        }

        public Integer getEgt_sort()
        {
            return egt_sort;
        }

        public DictSearchBo setEgt_sort(Integer egt_sort)
        {
            this.egt_sort = egt_sort;
            return this;
        }

        public Integer getElt_sort()
        {
            return elt_sort;
        }

        public DictSearchBo setElt_sort(Integer elt_sort)
        {
            this.elt_sort = elt_sort;
            return this;
        }
		
		public String getBetween_sort()
        {
            return between_sort;
        }

        public DictSearchBo setBetween_sort(String between_sort)
        {
            this.between_sort = between_sort;
            return this;
        }

        public String getNotbetween_sort()
        {
            return notbetween_sort;
        }

        public DictSearchBo setNotbetween_sort(String notbetween_sort)
        {
            this.notbetween_sort = notbetween_sort;
            return this;
        }

        public String getIn_sort()
        {
            return in_sort;
        }

        public DictSearchBo setIn_sort(String in_sort)
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

        public DictSearchBo setEq_createdTime(Date eq_createdTime)
        {
            this.eq_createdTime = eq_createdTime;
            return this;
        }

        public Date getNeq_createdTime()
        {
            return neq_createdTime;
        }

        public DictSearchBo setNeq_createdTime(Date neq_createdTime)
        {
            this.neq_createdTime = neq_createdTime;
            return this;
        }
		
        public Date getGt_createdTime()
        {
            return gt_createdTime;
        }

        public DictSearchBo setGt_createdTime(Date gt_createdTime)
        {
            this.gt_createdTime = gt_createdTime;
            return this;
        }

        public Date getLt_createdTime()
        {
            return lt_createdTime;
        }

        public DictSearchBo setLt_createdTime(Date lt_createdTime)
        {
            this.lt_createdTime = lt_createdTime;
            return this;
        }

        public Date getEgt_createdTime()
        {
            return egt_createdTime;
        }

        public DictSearchBo setEgt_createdTime(Date egt_createdTime)
        {
            this.egt_createdTime = egt_createdTime;
            return this;
        }

        public Date getElt_createdTime()
        {
            return elt_createdTime;
        }

        public DictSearchBo setElt_createdTime(Date elt_createdTime)
        {
            this.elt_createdTime = elt_createdTime;
            return this;
        }
		
		public String getBetween_createdTime()
        {
            return between_createdTime;
        }

        public DictSearchBo setBetween_createdTime(String between_createdTime)
        {
            this.between_createdTime = between_createdTime;
            return this;
        }

        public String getNotbetween_createdTime()
        {
            return notbetween_createdTime;
        }

        public DictSearchBo setNotbetween_createdTime(String notbetween_createdTime)
        {
            this.notbetween_createdTime = notbetween_createdTime;
            return this;
        }

        public String getIn_createdTime()
        {
            return in_createdTime;
        }

        public DictSearchBo setIn_createdTime(String in_createdTime)
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

        public DictSearchBo setEq_updatedTime(Date eq_updatedTime)
        {
            this.eq_updatedTime = eq_updatedTime;
            return this;
        }

        public Date getNeq_updatedTime()
        {
            return neq_updatedTime;
        }

        public DictSearchBo setNeq_updatedTime(Date neq_updatedTime)
        {
            this.neq_updatedTime = neq_updatedTime;
            return this;
        }
		
        public Date getGt_updatedTime()
        {
            return gt_updatedTime;
        }

        public DictSearchBo setGt_updatedTime(Date gt_updatedTime)
        {
            this.gt_updatedTime = gt_updatedTime;
            return this;
        }

        public Date getLt_updatedTime()
        {
            return lt_updatedTime;
        }

        public DictSearchBo setLt_updatedTime(Date lt_updatedTime)
        {
            this.lt_updatedTime = lt_updatedTime;
            return this;
        }

        public Date getEgt_updatedTime()
        {
            return egt_updatedTime;
        }

        public DictSearchBo setEgt_updatedTime(Date egt_updatedTime)
        {
            this.egt_updatedTime = egt_updatedTime;
            return this;
        }

        public Date getElt_updatedTime()
        {
            return elt_updatedTime;
        }

        public DictSearchBo setElt_updatedTime(Date elt_updatedTime)
        {
            this.elt_updatedTime = elt_updatedTime;
            return this;
        }
		
		public String getBetween_updatedTime()
        {
            return between_updatedTime;
        }

        public DictSearchBo setBetween_updatedTime(String between_updatedTime)
        {
            this.between_updatedTime = between_updatedTime;
            return this;
        }

        public String getNotbetween_updatedTime()
        {
            return notbetween_updatedTime;
        }

        public DictSearchBo setNotbetween_updatedTime(String notbetween_updatedTime)
        {
            this.notbetween_updatedTime = notbetween_updatedTime;
            return this;
        }

        public String getIn_updatedTime()
        {
            return in_updatedTime;
        }

        public DictSearchBo setIn_updatedTime(String in_updatedTime)
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

        public DictSearchBo setEq_createUser(String eq_createUser)
        {
            this.eq_createUser = eq_createUser;
            return this;
        }

        public String getNeq_createUser()
        {
            return neq_createUser;
        }

        public DictSearchBo setNeq_createUser(String neq_createUser)
        {
            this.neq_createUser = neq_createUser;
            return this;
        }
		
        public String getLike_createUser()
        {
            return like_createUser;
        }

        public DictSearchBo setLike_createUser(String like_createUser)
        {
            this.like_createUser = like_createUser;
            return this;
        }

        public String getLiker_createUser()
        {
            return liker_createUser;
        }

        public DictSearchBo setLiker_createUser(String liker_createUser)
        {
            this.liker_createUser = liker_createUser;
            return this;
        }

        public String getLikel_createUser()
        {
            return likel_createUser;
        }

        public DictSearchBo setLikel_createUser(String likel_createUser)
        {
            this.likel_createUser = likel_createUser;
            return this;
        }

        public String getNotlike_createUser()
        {
            return notlike_createUser;
        }

        public DictSearchBo setNotlike_createUser(String notlike_createUser)
        {
            this.notlike_createUser = notlike_createUser;
            return this;
        }

        public String getNotliker_createUser()
        {
            return notliker_createUser;
        }

        public DictSearchBo setNotliker_createUser(String notliker_createUser)
        {
            this.notliker_createUser = notliker_createUser;
            return this;
        }

        public String getNotlikel_createUser()
        {
            return notlikel_createUser;
        }

        public DictSearchBo setNotlikel_createUser(String notlikel_createUser)
        {
            this.notlikel_createUser = notlikel_createUser;
            return this;
        }

        public String getIn_createUser()
        {
            return in_createUser;
        }

        public DictSearchBo setIn_createUser(String in_createUser)
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

        public DictSearchBo setEq_createUserId(String eq_createUserId)
        {
            this.eq_createUserId = eq_createUserId;
            return this;
        }

        public String getNeq_createUserId()
        {
            return neq_createUserId;
        }

        public DictSearchBo setNeq_createUserId(String neq_createUserId)
        {
            this.neq_createUserId = neq_createUserId;
            return this;
        }
		
        public String getLike_createUserId()
        {
            return like_createUserId;
        }

        public DictSearchBo setLike_createUserId(String like_createUserId)
        {
            this.like_createUserId = like_createUserId;
            return this;
        }

        public String getLiker_createUserId()
        {
            return liker_createUserId;
        }

        public DictSearchBo setLiker_createUserId(String liker_createUserId)
        {
            this.liker_createUserId = liker_createUserId;
            return this;
        }

        public String getLikel_createUserId()
        {
            return likel_createUserId;
        }

        public DictSearchBo setLikel_createUserId(String likel_createUserId)
        {
            this.likel_createUserId = likel_createUserId;
            return this;
        }

        public String getNotlike_createUserId()
        {
            return notlike_createUserId;
        }

        public DictSearchBo setNotlike_createUserId(String notlike_createUserId)
        {
            this.notlike_createUserId = notlike_createUserId;
            return this;
        }

        public String getNotliker_createUserId()
        {
            return notliker_createUserId;
        }

        public DictSearchBo setNotliker_createUserId(String notliker_createUserId)
        {
            this.notliker_createUserId = notliker_createUserId;
            return this;
        }

        public String getNotlikel_createUserId()
        {
            return notlikel_createUserId;
        }

        public DictSearchBo setNotlikel_createUserId(String notlikel_createUserId)
        {
            this.notlikel_createUserId = notlikel_createUserId;
            return this;
        }

        public String getIn_createUserId()
        {
            return in_createUserId;
        }

        public DictSearchBo setIn_createUserId(String in_createUserId)
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

        public DictSearchBo setEq_updateUser(String eq_updateUser)
        {
            this.eq_updateUser = eq_updateUser;
            return this;
        }

        public String getNeq_updateUser()
        {
            return neq_updateUser;
        }

        public DictSearchBo setNeq_updateUser(String neq_updateUser)
        {
            this.neq_updateUser = neq_updateUser;
            return this;
        }
		
        public String getLike_updateUser()
        {
            return like_updateUser;
        }

        public DictSearchBo setLike_updateUser(String like_updateUser)
        {
            this.like_updateUser = like_updateUser;
            return this;
        }

        public String getLiker_updateUser()
        {
            return liker_updateUser;
        }

        public DictSearchBo setLiker_updateUser(String liker_updateUser)
        {
            this.liker_updateUser = liker_updateUser;
            return this;
        }

        public String getLikel_updateUser()
        {
            return likel_updateUser;
        }

        public DictSearchBo setLikel_updateUser(String likel_updateUser)
        {
            this.likel_updateUser = likel_updateUser;
            return this;
        }

        public String getNotlike_updateUser()
        {
            return notlike_updateUser;
        }

        public DictSearchBo setNotlike_updateUser(String notlike_updateUser)
        {
            this.notlike_updateUser = notlike_updateUser;
            return this;
        }

        public String getNotliker_updateUser()
        {
            return notliker_updateUser;
        }

        public DictSearchBo setNotliker_updateUser(String notliker_updateUser)
        {
            this.notliker_updateUser = notliker_updateUser;
            return this;
        }

        public String getNotlikel_updateUser()
        {
            return notlikel_updateUser;
        }

        public DictSearchBo setNotlikel_updateUser(String notlikel_updateUser)
        {
            this.notlikel_updateUser = notlikel_updateUser;
            return this;
        }

        public String getIn_updateUser()
        {
            return in_updateUser;
        }

        public DictSearchBo setIn_updateUser(String in_updateUser)
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

        public DictSearchBo setEq_updateUserId(String eq_updateUserId)
        {
            this.eq_updateUserId = eq_updateUserId;
            return this;
        }

        public String getNeq_updateUserId()
        {
            return neq_updateUserId;
        }

        public DictSearchBo setNeq_updateUserId(String neq_updateUserId)
        {
            this.neq_updateUserId = neq_updateUserId;
            return this;
        }
		
        public String getLike_updateUserId()
        {
            return like_updateUserId;
        }

        public DictSearchBo setLike_updateUserId(String like_updateUserId)
        {
            this.like_updateUserId = like_updateUserId;
            return this;
        }

        public String getLiker_updateUserId()
        {
            return liker_updateUserId;
        }

        public DictSearchBo setLiker_updateUserId(String liker_updateUserId)
        {
            this.liker_updateUserId = liker_updateUserId;
            return this;
        }

        public String getLikel_updateUserId()
        {
            return likel_updateUserId;
        }

        public DictSearchBo setLikel_updateUserId(String likel_updateUserId)
        {
            this.likel_updateUserId = likel_updateUserId;
            return this;
        }

        public String getNotlike_updateUserId()
        {
            return notlike_updateUserId;
        }

        public DictSearchBo setNotlike_updateUserId(String notlike_updateUserId)
        {
            this.notlike_updateUserId = notlike_updateUserId;
            return this;
        }

        public String getNotliker_updateUserId()
        {
            return notliker_updateUserId;
        }

        public DictSearchBo setNotliker_updateUserId(String notliker_updateUserId)
        {
            this.notliker_updateUserId = notliker_updateUserId;
            return this;
        }

        public String getNotlikel_updateUserId()
        {
            return notlikel_updateUserId;
        }

        public DictSearchBo setNotlikel_updateUserId(String notlikel_updateUserId)
        {
            this.notlikel_updateUserId = notlikel_updateUserId;
            return this;
        }

        public String getIn_updateUserId()
        {
            return in_updateUserId;
        }

        public DictSearchBo setIn_updateUserId(String in_updateUserId)
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
