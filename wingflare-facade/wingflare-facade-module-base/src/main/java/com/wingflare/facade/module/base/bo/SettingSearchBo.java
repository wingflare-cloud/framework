package com.wingflare.facade.module.base.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.math.BigInteger;
import java.util.Date;

/**
 * SettingSearchBo
 * 
 * @author naizui_ycx
 * @date Fri Mar 03 09:48:21 CST 2023
 */
public class SettingSearchBo extends BaseSearchBo
{

        private BigInteger eq_settingId;

        private BigInteger neq_settingId;
		
        private String in_settingId;

        private String notin_settingId;

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

        private String eq_settingCode;

        private String neq_settingCode;
		
		private String like_settingCode;

        private String liker_settingCode;

        private String likel_settingCode;

        private String notlike_settingCode;

        private String notliker_settingCode;

        private String notlikel_settingCode;
		
        private String in_settingCode;

        private String notin_settingCode;

        private String eq_settingName;

        private String neq_settingName;
		
		private String like_settingName;

        private String liker_settingName;

        private String likel_settingName;

        private String notlike_settingName;

        private String notliker_settingName;

        private String notlikel_settingName;
		
        private String in_settingName;

        private String notin_settingName;

        private String eq_settingValue;

        private String neq_settingValue;
		
		private String like_settingValue;

        private String liker_settingValue;

        private String likel_settingValue;

        private String notlike_settingValue;

        private String notliker_settingValue;

        private String notlikel_settingValue;
		
        private String in_settingValue;

        private String notin_settingValue;

        private String eq_settingText;

        private String neq_settingText;
		
		private String like_settingText;

        private String liker_settingText;

        private String likel_settingText;

        private String notlike_settingText;

        private String notliker_settingText;

        private String notlikel_settingText;
		
        private String in_settingText;

        private String notin_settingText;

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


        public BigInteger getEq_settingId()
        {
            return eq_settingId;
        }

        public SettingSearchBo setEq_settingId(BigInteger eq_settingId)
        {
            this.eq_settingId = eq_settingId;
            return this;
        }

        public BigInteger getNeq_settingId()
        {
            return neq_settingId;
        }

        public SettingSearchBo setNeq_settingId(BigInteger neq_settingId)
        {
            this.neq_settingId = neq_settingId;
            return this;
        }

        public String getIn_settingId()
        {
            return in_settingId;
        }

        public SettingSearchBo setIn_settingId(String in_settingId)
        {
            this.in_settingId = in_settingId;
            return this;
        }

        public String getNotin_settingId()
        {
            return notin_settingId;
        }

        public void setNotin_settingId(String notin_settingId)
        {
            this.notin_settingId = notin_settingId;
        }

        public Integer getEq_state()
        {
            return eq_state;
        }

        public SettingSearchBo setEq_state(Integer eq_state)
        {
            this.eq_state = eq_state;
            return this;
        }

        public Integer getNeq_state()
        {
            return neq_state;
        }

        public SettingSearchBo setNeq_state(Integer neq_state)
        {
            this.neq_state = neq_state;
            return this;
        }
		
        public Integer getGt_state()
        {
            return gt_state;
        }

        public SettingSearchBo setGt_state(Integer gt_state)
        {
            this.gt_state = gt_state;
            return this;
        }

        public Integer getLt_state()
        {
            return lt_state;
        }

        public SettingSearchBo setLt_state(Integer lt_state)
        {
            this.lt_state = lt_state;
            return this;
        }

        public Integer getEgt_state()
        {
            return egt_state;
        }

        public SettingSearchBo setEgt_state(Integer egt_state)
        {
            this.egt_state = egt_state;
            return this;
        }

        public Integer getElt_state()
        {
            return elt_state;
        }

        public SettingSearchBo setElt_state(Integer elt_state)
        {
            this.elt_state = elt_state;
            return this;
        }
		
		public String getBetween_state()
        {
            return between_state;
        }

        public SettingSearchBo setBetween_state(String between_state)
        {
            this.between_state = between_state;
            return this;
        }

        public String getNotbetween_state()
        {
            return notbetween_state;
        }

        public SettingSearchBo setNotbetween_state(String notbetween_state)
        {
            this.notbetween_state = notbetween_state;
            return this;
        }

        public String getIn_state()
        {
            return in_state;
        }

        public SettingSearchBo setIn_state(String in_state)
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

        public String getEq_systemCode()
        {
            return eq_systemCode;
        }

        public SettingSearchBo setEq_systemCode(String eq_systemCode)
        {
            this.eq_systemCode = eq_systemCode;
            return this;
        }

        public String getNeq_systemCode()
        {
            return neq_systemCode;
        }

        public SettingSearchBo setNeq_systemCode(String neq_systemCode)
        {
            this.neq_systemCode = neq_systemCode;
            return this;
        }
		
        public String getLike_systemCode()
        {
            return like_systemCode;
        }

        public SettingSearchBo setLike_systemCode(String like_systemCode)
        {
            this.like_systemCode = like_systemCode;
            return this;
        }

        public String getLiker_systemCode()
        {
            return liker_systemCode;
        }

        public SettingSearchBo setLiker_systemCode(String liker_systemCode)
        {
            this.liker_systemCode = liker_systemCode;
            return this;
        }

        public String getLikel_systemCode()
        {
            return likel_systemCode;
        }

        public SettingSearchBo setLikel_systemCode(String likel_systemCode)
        {
            this.likel_systemCode = likel_systemCode;
            return this;
        }

        public String getNotlike_systemCode()
        {
            return notlike_systemCode;
        }

        public SettingSearchBo setNotlike_systemCode(String notlike_systemCode)
        {
            this.notlike_systemCode = notlike_systemCode;
            return this;
        }

        public String getNotliker_systemCode()
        {
            return notliker_systemCode;
        }

        public SettingSearchBo setNotliker_systemCode(String notliker_systemCode)
        {
            this.notliker_systemCode = notliker_systemCode;
            return this;
        }

        public String getNotlikel_systemCode()
        {
            return notlikel_systemCode;
        }

        public SettingSearchBo setNotlikel_systemCode(String notlikel_systemCode)
        {
            this.notlikel_systemCode = notlikel_systemCode;
            return this;
        }

        public String getIn_systemCode()
        {
            return in_systemCode;
        }

        public SettingSearchBo setIn_systemCode(String in_systemCode)
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

        public String getEq_settingCode()
        {
            return eq_settingCode;
        }

        public SettingSearchBo setEq_settingCode(String eq_settingCode)
        {
            this.eq_settingCode = eq_settingCode;
            return this;
        }

        public String getNeq_settingCode()
        {
            return neq_settingCode;
        }

        public SettingSearchBo setNeq_settingCode(String neq_settingCode)
        {
            this.neq_settingCode = neq_settingCode;
            return this;
        }
		
        public String getLike_settingCode()
        {
            return like_settingCode;
        }

        public SettingSearchBo setLike_settingCode(String like_settingCode)
        {
            this.like_settingCode = like_settingCode;
            return this;
        }

        public String getLiker_settingCode()
        {
            return liker_settingCode;
        }

        public SettingSearchBo setLiker_settingCode(String liker_settingCode)
        {
            this.liker_settingCode = liker_settingCode;
            return this;
        }

        public String getLikel_settingCode()
        {
            return likel_settingCode;
        }

        public SettingSearchBo setLikel_settingCode(String likel_settingCode)
        {
            this.likel_settingCode = likel_settingCode;
            return this;
        }

        public String getNotlike_settingCode()
        {
            return notlike_settingCode;
        }

        public SettingSearchBo setNotlike_settingCode(String notlike_settingCode)
        {
            this.notlike_settingCode = notlike_settingCode;
            return this;
        }

        public String getNotliker_settingCode()
        {
            return notliker_settingCode;
        }

        public SettingSearchBo setNotliker_settingCode(String notliker_settingCode)
        {
            this.notliker_settingCode = notliker_settingCode;
            return this;
        }

        public String getNotlikel_settingCode()
        {
            return notlikel_settingCode;
        }

        public SettingSearchBo setNotlikel_settingCode(String notlikel_settingCode)
        {
            this.notlikel_settingCode = notlikel_settingCode;
            return this;
        }

        public String getIn_settingCode()
        {
            return in_settingCode;
        }

        public SettingSearchBo setIn_settingCode(String in_settingCode)
        {
            this.in_settingCode = in_settingCode;
            return this;
        }

        public String getNotin_settingCode()
        {
            return notin_settingCode;
        }

        public void setNotin_settingCode(String notin_settingCode)
        {
            this.notin_settingCode = notin_settingCode;
        }

        public String getEq_settingName()
        {
            return eq_settingName;
        }

        public SettingSearchBo setEq_settingName(String eq_settingName)
        {
            this.eq_settingName = eq_settingName;
            return this;
        }

        public String getNeq_settingName()
        {
            return neq_settingName;
        }

        public SettingSearchBo setNeq_settingName(String neq_settingName)
        {
            this.neq_settingName = neq_settingName;
            return this;
        }
		
        public String getLike_settingName()
        {
            return like_settingName;
        }

        public SettingSearchBo setLike_settingName(String like_settingName)
        {
            this.like_settingName = like_settingName;
            return this;
        }

        public String getLiker_settingName()
        {
            return liker_settingName;
        }

        public SettingSearchBo setLiker_settingName(String liker_settingName)
        {
            this.liker_settingName = liker_settingName;
            return this;
        }

        public String getLikel_settingName()
        {
            return likel_settingName;
        }

        public SettingSearchBo setLikel_settingName(String likel_settingName)
        {
            this.likel_settingName = likel_settingName;
            return this;
        }

        public String getNotlike_settingName()
        {
            return notlike_settingName;
        }

        public SettingSearchBo setNotlike_settingName(String notlike_settingName)
        {
            this.notlike_settingName = notlike_settingName;
            return this;
        }

        public String getNotliker_settingName()
        {
            return notliker_settingName;
        }

        public SettingSearchBo setNotliker_settingName(String notliker_settingName)
        {
            this.notliker_settingName = notliker_settingName;
            return this;
        }

        public String getNotlikel_settingName()
        {
            return notlikel_settingName;
        }

        public SettingSearchBo setNotlikel_settingName(String notlikel_settingName)
        {
            this.notlikel_settingName = notlikel_settingName;
            return this;
        }

        public String getIn_settingName()
        {
            return in_settingName;
        }

        public SettingSearchBo setIn_settingName(String in_settingName)
        {
            this.in_settingName = in_settingName;
            return this;
        }

        public String getNotin_settingName()
        {
            return notin_settingName;
        }

        public void setNotin_settingName(String notin_settingName)
        {
            this.notin_settingName = notin_settingName;
        }

        public String getEq_settingValue()
        {
            return eq_settingValue;
        }

        public SettingSearchBo setEq_settingValue(String eq_settingValue)
        {
            this.eq_settingValue = eq_settingValue;
            return this;
        }

        public String getNeq_settingValue()
        {
            return neq_settingValue;
        }

        public SettingSearchBo setNeq_settingValue(String neq_settingValue)
        {
            this.neq_settingValue = neq_settingValue;
            return this;
        }
		
        public String getLike_settingValue()
        {
            return like_settingValue;
        }

        public SettingSearchBo setLike_settingValue(String like_settingValue)
        {
            this.like_settingValue = like_settingValue;
            return this;
        }

        public String getLiker_settingValue()
        {
            return liker_settingValue;
        }

        public SettingSearchBo setLiker_settingValue(String liker_settingValue)
        {
            this.liker_settingValue = liker_settingValue;
            return this;
        }

        public String getLikel_settingValue()
        {
            return likel_settingValue;
        }

        public SettingSearchBo setLikel_settingValue(String likel_settingValue)
        {
            this.likel_settingValue = likel_settingValue;
            return this;
        }

        public String getNotlike_settingValue()
        {
            return notlike_settingValue;
        }

        public SettingSearchBo setNotlike_settingValue(String notlike_settingValue)
        {
            this.notlike_settingValue = notlike_settingValue;
            return this;
        }

        public String getNotliker_settingValue()
        {
            return notliker_settingValue;
        }

        public SettingSearchBo setNotliker_settingValue(String notliker_settingValue)
        {
            this.notliker_settingValue = notliker_settingValue;
            return this;
        }

        public String getNotlikel_settingValue()
        {
            return notlikel_settingValue;
        }

        public SettingSearchBo setNotlikel_settingValue(String notlikel_settingValue)
        {
            this.notlikel_settingValue = notlikel_settingValue;
            return this;
        }

        public String getIn_settingValue()
        {
            return in_settingValue;
        }

        public SettingSearchBo setIn_settingValue(String in_settingValue)
        {
            this.in_settingValue = in_settingValue;
            return this;
        }

        public String getNotin_settingValue()
        {
            return notin_settingValue;
        }

        public void setNotin_settingValue(String notin_settingValue)
        {
            this.notin_settingValue = notin_settingValue;
        }

        public String getEq_settingText()
        {
            return eq_settingText;
        }

        public SettingSearchBo setEq_settingText(String eq_settingText)
        {
            this.eq_settingText = eq_settingText;
            return this;
        }

        public String getNeq_settingText()
        {
            return neq_settingText;
        }

        public SettingSearchBo setNeq_settingText(String neq_settingText)
        {
            this.neq_settingText = neq_settingText;
            return this;
        }
		
        public String getLike_settingText()
        {
            return like_settingText;
        }

        public SettingSearchBo setLike_settingText(String like_settingText)
        {
            this.like_settingText = like_settingText;
            return this;
        }

        public String getLiker_settingText()
        {
            return liker_settingText;
        }

        public SettingSearchBo setLiker_settingText(String liker_settingText)
        {
            this.liker_settingText = liker_settingText;
            return this;
        }

        public String getLikel_settingText()
        {
            return likel_settingText;
        }

        public SettingSearchBo setLikel_settingText(String likel_settingText)
        {
            this.likel_settingText = likel_settingText;
            return this;
        }

        public String getNotlike_settingText()
        {
            return notlike_settingText;
        }

        public SettingSearchBo setNotlike_settingText(String notlike_settingText)
        {
            this.notlike_settingText = notlike_settingText;
            return this;
        }

        public String getNotliker_settingText()
        {
            return notliker_settingText;
        }

        public SettingSearchBo setNotliker_settingText(String notliker_settingText)
        {
            this.notliker_settingText = notliker_settingText;
            return this;
        }

        public String getNotlikel_settingText()
        {
            return notlikel_settingText;
        }

        public SettingSearchBo setNotlikel_settingText(String notlikel_settingText)
        {
            this.notlikel_settingText = notlikel_settingText;
            return this;
        }

        public String getIn_settingText()
        {
            return in_settingText;
        }

        public SettingSearchBo setIn_settingText(String in_settingText)
        {
            this.in_settingText = in_settingText;
            return this;
        }

        public String getNotin_settingText()
        {
            return notin_settingText;
        }

        public void setNotin_settingText(String notin_settingText)
        {
            this.notin_settingText = notin_settingText;
        }

        public Date getEq_createdTime()
        {
            return eq_createdTime;
        }

        public SettingSearchBo setEq_createdTime(Date eq_createdTime)
        {
            this.eq_createdTime = eq_createdTime;
            return this;
        }

        public Date getNeq_createdTime()
        {
            return neq_createdTime;
        }

        public SettingSearchBo setNeq_createdTime(Date neq_createdTime)
        {
            this.neq_createdTime = neq_createdTime;
            return this;
        }
		
        public Date getGt_createdTime()
        {
            return gt_createdTime;
        }

        public SettingSearchBo setGt_createdTime(Date gt_createdTime)
        {
            this.gt_createdTime = gt_createdTime;
            return this;
        }

        public Date getLt_createdTime()
        {
            return lt_createdTime;
        }

        public SettingSearchBo setLt_createdTime(Date lt_createdTime)
        {
            this.lt_createdTime = lt_createdTime;
            return this;
        }

        public Date getEgt_createdTime()
        {
            return egt_createdTime;
        }

        public SettingSearchBo setEgt_createdTime(Date egt_createdTime)
        {
            this.egt_createdTime = egt_createdTime;
            return this;
        }

        public Date getElt_createdTime()
        {
            return elt_createdTime;
        }

        public SettingSearchBo setElt_createdTime(Date elt_createdTime)
        {
            this.elt_createdTime = elt_createdTime;
            return this;
        }
		
		public String getBetween_createdTime()
        {
            return between_createdTime;
        }

        public SettingSearchBo setBetween_createdTime(String between_createdTime)
        {
            this.between_createdTime = between_createdTime;
            return this;
        }

        public String getNotbetween_createdTime()
        {
            return notbetween_createdTime;
        }

        public SettingSearchBo setNotbetween_createdTime(String notbetween_createdTime)
        {
            this.notbetween_createdTime = notbetween_createdTime;
            return this;
        }

        public String getIn_createdTime()
        {
            return in_createdTime;
        }

        public SettingSearchBo setIn_createdTime(String in_createdTime)
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

        public SettingSearchBo setEq_updatedTime(Date eq_updatedTime)
        {
            this.eq_updatedTime = eq_updatedTime;
            return this;
        }

        public Date getNeq_updatedTime()
        {
            return neq_updatedTime;
        }

        public SettingSearchBo setNeq_updatedTime(Date neq_updatedTime)
        {
            this.neq_updatedTime = neq_updatedTime;
            return this;
        }
		
        public Date getGt_updatedTime()
        {
            return gt_updatedTime;
        }

        public SettingSearchBo setGt_updatedTime(Date gt_updatedTime)
        {
            this.gt_updatedTime = gt_updatedTime;
            return this;
        }

        public Date getLt_updatedTime()
        {
            return lt_updatedTime;
        }

        public SettingSearchBo setLt_updatedTime(Date lt_updatedTime)
        {
            this.lt_updatedTime = lt_updatedTime;
            return this;
        }

        public Date getEgt_updatedTime()
        {
            return egt_updatedTime;
        }

        public SettingSearchBo setEgt_updatedTime(Date egt_updatedTime)
        {
            this.egt_updatedTime = egt_updatedTime;
            return this;
        }

        public Date getElt_updatedTime()
        {
            return elt_updatedTime;
        }

        public SettingSearchBo setElt_updatedTime(Date elt_updatedTime)
        {
            this.elt_updatedTime = elt_updatedTime;
            return this;
        }
		
		public String getBetween_updatedTime()
        {
            return between_updatedTime;
        }

        public SettingSearchBo setBetween_updatedTime(String between_updatedTime)
        {
            this.between_updatedTime = between_updatedTime;
            return this;
        }

        public String getNotbetween_updatedTime()
        {
            return notbetween_updatedTime;
        }

        public SettingSearchBo setNotbetween_updatedTime(String notbetween_updatedTime)
        {
            this.notbetween_updatedTime = notbetween_updatedTime;
            return this;
        }

        public String getIn_updatedTime()
        {
            return in_updatedTime;
        }

        public SettingSearchBo setIn_updatedTime(String in_updatedTime)
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

        public SettingSearchBo setEq_createUser(String eq_createUser)
        {
            this.eq_createUser = eq_createUser;
            return this;
        }

        public String getNeq_createUser()
        {
            return neq_createUser;
        }

        public SettingSearchBo setNeq_createUser(String neq_createUser)
        {
            this.neq_createUser = neq_createUser;
            return this;
        }
		
        public String getLike_createUser()
        {
            return like_createUser;
        }

        public SettingSearchBo setLike_createUser(String like_createUser)
        {
            this.like_createUser = like_createUser;
            return this;
        }

        public String getLiker_createUser()
        {
            return liker_createUser;
        }

        public SettingSearchBo setLiker_createUser(String liker_createUser)
        {
            this.liker_createUser = liker_createUser;
            return this;
        }

        public String getLikel_createUser()
        {
            return likel_createUser;
        }

        public SettingSearchBo setLikel_createUser(String likel_createUser)
        {
            this.likel_createUser = likel_createUser;
            return this;
        }

        public String getNotlike_createUser()
        {
            return notlike_createUser;
        }

        public SettingSearchBo setNotlike_createUser(String notlike_createUser)
        {
            this.notlike_createUser = notlike_createUser;
            return this;
        }

        public String getNotliker_createUser()
        {
            return notliker_createUser;
        }

        public SettingSearchBo setNotliker_createUser(String notliker_createUser)
        {
            this.notliker_createUser = notliker_createUser;
            return this;
        }

        public String getNotlikel_createUser()
        {
            return notlikel_createUser;
        }

        public SettingSearchBo setNotlikel_createUser(String notlikel_createUser)
        {
            this.notlikel_createUser = notlikel_createUser;
            return this;
        }

        public String getIn_createUser()
        {
            return in_createUser;
        }

        public SettingSearchBo setIn_createUser(String in_createUser)
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

        public SettingSearchBo setEq_createUserId(BigInteger eq_createUserId)
        {
            this.eq_createUserId = eq_createUserId;
            return this;
        }

        public BigInteger getNeq_createUserId()
        {
            return neq_createUserId;
        }

        public SettingSearchBo setNeq_createUserId(BigInteger neq_createUserId)
        {
            this.neq_createUserId = neq_createUserId;
            return this;
        }
		
        public String getLike_createUserId()
        {
            return like_createUserId;
        }

        public SettingSearchBo setLike_createUserId(String like_createUserId)
        {
            this.like_createUserId = like_createUserId;
            return this;
        }

        public String getLiker_createUserId()
        {
            return liker_createUserId;
        }

        public SettingSearchBo setLiker_createUserId(String liker_createUserId)
        {
            this.liker_createUserId = liker_createUserId;
            return this;
        }

        public String getLikel_createUserId()
        {
            return likel_createUserId;
        }

        public SettingSearchBo setLikel_createUserId(String likel_createUserId)
        {
            this.likel_createUserId = likel_createUserId;
            return this;
        }

        public String getNotlike_createUserId()
        {
            return notlike_createUserId;
        }

        public SettingSearchBo setNotlike_createUserId(String notlike_createUserId)
        {
            this.notlike_createUserId = notlike_createUserId;
            return this;
        }

        public String getNotliker_createUserId()
        {
            return notliker_createUserId;
        }

        public SettingSearchBo setNotliker_createUserId(String notliker_createUserId)
        {
            this.notliker_createUserId = notliker_createUserId;
            return this;
        }

        public String getNotlikel_createUserId()
        {
            return notlikel_createUserId;
        }

        public SettingSearchBo setNotlikel_createUserId(String notlikel_createUserId)
        {
            this.notlikel_createUserId = notlikel_createUserId;
            return this;
        }

        public String getIn_createUserId()
        {
            return in_createUserId;
        }

        public SettingSearchBo setIn_createUserId(String in_createUserId)
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

        public SettingSearchBo setEq_updateUser(String eq_updateUser)
        {
            this.eq_updateUser = eq_updateUser;
            return this;
        }

        public String getNeq_updateUser()
        {
            return neq_updateUser;
        }

        public SettingSearchBo setNeq_updateUser(String neq_updateUser)
        {
            this.neq_updateUser = neq_updateUser;
            return this;
        }
		
        public String getLike_updateUser()
        {
            return like_updateUser;
        }

        public SettingSearchBo setLike_updateUser(String like_updateUser)
        {
            this.like_updateUser = like_updateUser;
            return this;
        }

        public String getLiker_updateUser()
        {
            return liker_updateUser;
        }

        public SettingSearchBo setLiker_updateUser(String liker_updateUser)
        {
            this.liker_updateUser = liker_updateUser;
            return this;
        }

        public String getLikel_updateUser()
        {
            return likel_updateUser;
        }

        public SettingSearchBo setLikel_updateUser(String likel_updateUser)
        {
            this.likel_updateUser = likel_updateUser;
            return this;
        }

        public String getNotlike_updateUser()
        {
            return notlike_updateUser;
        }

        public SettingSearchBo setNotlike_updateUser(String notlike_updateUser)
        {
            this.notlike_updateUser = notlike_updateUser;
            return this;
        }

        public String getNotliker_updateUser()
        {
            return notliker_updateUser;
        }

        public SettingSearchBo setNotliker_updateUser(String notliker_updateUser)
        {
            this.notliker_updateUser = notliker_updateUser;
            return this;
        }

        public String getNotlikel_updateUser()
        {
            return notlikel_updateUser;
        }

        public SettingSearchBo setNotlikel_updateUser(String notlikel_updateUser)
        {
            this.notlikel_updateUser = notlikel_updateUser;
            return this;
        }

        public String getIn_updateUser()
        {
            return in_updateUser;
        }

        public SettingSearchBo setIn_updateUser(String in_updateUser)
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

        public SettingSearchBo setEq_updateUserId(BigInteger eq_updateUserId)
        {
            this.eq_updateUserId = eq_updateUserId;
            return this;
        }

        public BigInteger getNeq_updateUserId()
        {
            return neq_updateUserId;
        }

        public SettingSearchBo setNeq_updateUserId(BigInteger neq_updateUserId)
        {
            this.neq_updateUserId = neq_updateUserId;
            return this;
        }
		
        public String getLike_updateUserId()
        {
            return like_updateUserId;
        }

        public SettingSearchBo setLike_updateUserId(String like_updateUserId)
        {
            this.like_updateUserId = like_updateUserId;
            return this;
        }

        public String getLiker_updateUserId()
        {
            return liker_updateUserId;
        }

        public SettingSearchBo setLiker_updateUserId(String liker_updateUserId)
        {
            this.liker_updateUserId = liker_updateUserId;
            return this;
        }

        public String getLikel_updateUserId()
        {
            return likel_updateUserId;
        }

        public SettingSearchBo setLikel_updateUserId(String likel_updateUserId)
        {
            this.likel_updateUserId = likel_updateUserId;
            return this;
        }

        public String getNotlike_updateUserId()
        {
            return notlike_updateUserId;
        }

        public SettingSearchBo setNotlike_updateUserId(String notlike_updateUserId)
        {
            this.notlike_updateUserId = notlike_updateUserId;
            return this;
        }

        public String getNotliker_updateUserId()
        {
            return notliker_updateUserId;
        }

        public SettingSearchBo setNotliker_updateUserId(String notliker_updateUserId)
        {
            this.notliker_updateUserId = notliker_updateUserId;
            return this;
        }

        public String getNotlikel_updateUserId()
        {
            return notlikel_updateUserId;
        }

        public SettingSearchBo setNotlikel_updateUserId(String notlikel_updateUserId)
        {
            this.notlikel_updateUserId = notlikel_updateUserId;
            return this;
        }

        public String getIn_updateUserId()
        {
            return in_updateUserId;
        }

        public SettingSearchBo setIn_updateUserId(String in_updateUserId)
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
