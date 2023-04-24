package com.wingflare.facade.module.auth.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.util.Date;

/**
 * LoginTokenSearchBo
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 14:36:13 CST 2023
 */
public class LoginTokenSearchBo extends BaseSearchBo
{

        private String eq_tokenId;

        private String neq_tokenId;
		
        private String in_tokenId;

        private String notin_tokenId;

        private String eq_loginId;

        private String neq_loginId;
		
		private String like_loginId;

        private String liker_loginId;

        private String likel_loginId;

        private String notlike_loginId;

        private String notliker_loginId;

        private String notlikel_loginId;
		
        private String in_loginId;

        private String notin_loginId;

        private String eq_tokenKey;

        private String neq_tokenKey;
		
		private String like_tokenKey;

        private String liker_tokenKey;

        private String likel_tokenKey;

        private String notlike_tokenKey;

        private String notliker_tokenKey;

        private String notlikel_tokenKey;
		
        private String in_tokenKey;

        private String notin_tokenKey;

        private Date eq_expireTime;

        private Date neq_expireTime;
		
        private Date gt_expireTime;

        private Date lt_expireTime;

        private Date egt_expireTime;

        private Date elt_expireTime;
		
		private String between_expireTime;

        private String notbetween_expireTime;
		
        private String in_expireTime;

        private String notin_expireTime;

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


        public String getEq_tokenId()
        {
            return eq_tokenId;
        }

        public LoginTokenSearchBo setEq_tokenId(String eq_tokenId)
        {
            this.eq_tokenId = eq_tokenId;
            return this;
        }

        public String getNeq_tokenId()
        {
            return neq_tokenId;
        }

        public LoginTokenSearchBo setNeq_tokenId(String neq_tokenId)
        {
            this.neq_tokenId = neq_tokenId;
            return this;
        }

        public String getIn_tokenId()
        {
            return in_tokenId;
        }

        public LoginTokenSearchBo setIn_tokenId(String in_tokenId)
        {
            this.in_tokenId = in_tokenId;
            return this;
        }

        public String getNotin_tokenId()
        {
            return notin_tokenId;
        }

        public void setNotin_tokenId(String notin_tokenId)
        {
            this.notin_tokenId = notin_tokenId;
        }

        public String getEq_loginId()
        {
            return eq_loginId;
        }

        public LoginTokenSearchBo setEq_loginId(String eq_loginId)
        {
            this.eq_loginId = eq_loginId;
            return this;
        }

        public String getNeq_loginId()
        {
            return neq_loginId;
        }

        public LoginTokenSearchBo setNeq_loginId(String neq_loginId)
        {
            this.neq_loginId = neq_loginId;
            return this;
        }
		
        public String getLike_loginId()
        {
            return like_loginId;
        }

        public LoginTokenSearchBo setLike_loginId(String like_loginId)
        {
            this.like_loginId = like_loginId;
            return this;
        }

        public String getLiker_loginId()
        {
            return liker_loginId;
        }

        public LoginTokenSearchBo setLiker_loginId(String liker_loginId)
        {
            this.liker_loginId = liker_loginId;
            return this;
        }

        public String getLikel_loginId()
        {
            return likel_loginId;
        }

        public LoginTokenSearchBo setLikel_loginId(String likel_loginId)
        {
            this.likel_loginId = likel_loginId;
            return this;
        }

        public String getNotlike_loginId()
        {
            return notlike_loginId;
        }

        public LoginTokenSearchBo setNotlike_loginId(String notlike_loginId)
        {
            this.notlike_loginId = notlike_loginId;
            return this;
        }

        public String getNotliker_loginId()
        {
            return notliker_loginId;
        }

        public LoginTokenSearchBo setNotliker_loginId(String notliker_loginId)
        {
            this.notliker_loginId = notliker_loginId;
            return this;
        }

        public String getNotlikel_loginId()
        {
            return notlikel_loginId;
        }

        public LoginTokenSearchBo setNotlikel_loginId(String notlikel_loginId)
        {
            this.notlikel_loginId = notlikel_loginId;
            return this;
        }

        public String getIn_loginId()
        {
            return in_loginId;
        }

        public LoginTokenSearchBo setIn_loginId(String in_loginId)
        {
            this.in_loginId = in_loginId;
            return this;
        }

        public String getNotin_loginId()
        {
            return notin_loginId;
        }

        public void setNotin_loginId(String notin_loginId)
        {
            this.notin_loginId = notin_loginId;
        }

        public String getEq_tokenKey()
        {
            return eq_tokenKey;
        }

        public LoginTokenSearchBo setEq_tokenKey(String eq_tokenKey)
        {
            this.eq_tokenKey = eq_tokenKey;
            return this;
        }

        public String getNeq_tokenKey()
        {
            return neq_tokenKey;
        }

        public LoginTokenSearchBo setNeq_tokenKey(String neq_tokenKey)
        {
            this.neq_tokenKey = neq_tokenKey;
            return this;
        }
		
        public String getLike_tokenKey()
        {
            return like_tokenKey;
        }

        public LoginTokenSearchBo setLike_tokenKey(String like_tokenKey)
        {
            this.like_tokenKey = like_tokenKey;
            return this;
        }

        public String getLiker_tokenKey()
        {
            return liker_tokenKey;
        }

        public LoginTokenSearchBo setLiker_tokenKey(String liker_tokenKey)
        {
            this.liker_tokenKey = liker_tokenKey;
            return this;
        }

        public String getLikel_tokenKey()
        {
            return likel_tokenKey;
        }

        public LoginTokenSearchBo setLikel_tokenKey(String likel_tokenKey)
        {
            this.likel_tokenKey = likel_tokenKey;
            return this;
        }

        public String getNotlike_tokenKey()
        {
            return notlike_tokenKey;
        }

        public LoginTokenSearchBo setNotlike_tokenKey(String notlike_tokenKey)
        {
            this.notlike_tokenKey = notlike_tokenKey;
            return this;
        }

        public String getNotliker_tokenKey()
        {
            return notliker_tokenKey;
        }

        public LoginTokenSearchBo setNotliker_tokenKey(String notliker_tokenKey)
        {
            this.notliker_tokenKey = notliker_tokenKey;
            return this;
        }

        public String getNotlikel_tokenKey()
        {
            return notlikel_tokenKey;
        }

        public LoginTokenSearchBo setNotlikel_tokenKey(String notlikel_tokenKey)
        {
            this.notlikel_tokenKey = notlikel_tokenKey;
            return this;
        }

        public String getIn_tokenKey()
        {
            return in_tokenKey;
        }

        public LoginTokenSearchBo setIn_tokenKey(String in_tokenKey)
        {
            this.in_tokenKey = in_tokenKey;
            return this;
        }

        public String getNotin_tokenKey()
        {
            return notin_tokenKey;
        }

        public void setNotin_tokenKey(String notin_tokenKey)
        {
            this.notin_tokenKey = notin_tokenKey;
        }

        public Date getEq_expireTime()
        {
            return eq_expireTime;
        }

        public LoginTokenSearchBo setEq_expireTime(Date eq_expireTime)
        {
            this.eq_expireTime = eq_expireTime;
            return this;
        }

        public Date getNeq_expireTime()
        {
            return neq_expireTime;
        }

        public LoginTokenSearchBo setNeq_expireTime(Date neq_expireTime)
        {
            this.neq_expireTime = neq_expireTime;
            return this;
        }
		
        public Date getGt_expireTime()
        {
            return gt_expireTime;
        }

        public LoginTokenSearchBo setGt_expireTime(Date gt_expireTime)
        {
            this.gt_expireTime = gt_expireTime;
            return this;
        }

        public Date getLt_expireTime()
        {
            return lt_expireTime;
        }

        public LoginTokenSearchBo setLt_expireTime(Date lt_expireTime)
        {
            this.lt_expireTime = lt_expireTime;
            return this;
        }

        public Date getEgt_expireTime()
        {
            return egt_expireTime;
        }

        public LoginTokenSearchBo setEgt_expireTime(Date egt_expireTime)
        {
            this.egt_expireTime = egt_expireTime;
            return this;
        }

        public Date getElt_expireTime()
        {
            return elt_expireTime;
        }

        public LoginTokenSearchBo setElt_expireTime(Date elt_expireTime)
        {
            this.elt_expireTime = elt_expireTime;
            return this;
        }
		
		public String getBetween_expireTime()
        {
            return between_expireTime;
        }

        public LoginTokenSearchBo setBetween_expireTime(String between_expireTime)
        {
            this.between_expireTime = between_expireTime;
            return this;
        }

        public String getNotbetween_expireTime()
        {
            return notbetween_expireTime;
        }

        public LoginTokenSearchBo setNotbetween_expireTime(String notbetween_expireTime)
        {
            this.notbetween_expireTime = notbetween_expireTime;
            return this;
        }

        public String getIn_expireTime()
        {
            return in_expireTime;
        }

        public LoginTokenSearchBo setIn_expireTime(String in_expireTime)
        {
            this.in_expireTime = in_expireTime;
            return this;
        }

        public String getNotin_expireTime()
        {
            return notin_expireTime;
        }

        public void setNotin_expireTime(String notin_expireTime)
        {
            this.notin_expireTime = notin_expireTime;
        }

        public Date getEq_createdTime()
        {
            return eq_createdTime;
        }

        public LoginTokenSearchBo setEq_createdTime(Date eq_createdTime)
        {
            this.eq_createdTime = eq_createdTime;
            return this;
        }

        public Date getNeq_createdTime()
        {
            return neq_createdTime;
        }

        public LoginTokenSearchBo setNeq_createdTime(Date neq_createdTime)
        {
            this.neq_createdTime = neq_createdTime;
            return this;
        }
		
        public Date getGt_createdTime()
        {
            return gt_createdTime;
        }

        public LoginTokenSearchBo setGt_createdTime(Date gt_createdTime)
        {
            this.gt_createdTime = gt_createdTime;
            return this;
        }

        public Date getLt_createdTime()
        {
            return lt_createdTime;
        }

        public LoginTokenSearchBo setLt_createdTime(Date lt_createdTime)
        {
            this.lt_createdTime = lt_createdTime;
            return this;
        }

        public Date getEgt_createdTime()
        {
            return egt_createdTime;
        }

        public LoginTokenSearchBo setEgt_createdTime(Date egt_createdTime)
        {
            this.egt_createdTime = egt_createdTime;
            return this;
        }

        public Date getElt_createdTime()
        {
            return elt_createdTime;
        }

        public LoginTokenSearchBo setElt_createdTime(Date elt_createdTime)
        {
            this.elt_createdTime = elt_createdTime;
            return this;
        }
		
		public String getBetween_createdTime()
        {
            return between_createdTime;
        }

        public LoginTokenSearchBo setBetween_createdTime(String between_createdTime)
        {
            this.between_createdTime = between_createdTime;
            return this;
        }

        public String getNotbetween_createdTime()
        {
            return notbetween_createdTime;
        }

        public LoginTokenSearchBo setNotbetween_createdTime(String notbetween_createdTime)
        {
            this.notbetween_createdTime = notbetween_createdTime;
            return this;
        }

        public String getIn_createdTime()
        {
            return in_createdTime;
        }

        public LoginTokenSearchBo setIn_createdTime(String in_createdTime)
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
}
