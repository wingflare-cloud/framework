package com.wingflare.facade.module.auth.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.util.Date;

/**
 * LoginInfoSearchBo
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 14:29:43 CST 2023
 */
public class LoginInfoSearchBo extends BaseSearchBo
{

        private String eq_loginId;

        private String neq_loginId;
		
        private String in_loginId;

        private String notin_loginId;

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

        private String eq_orgId;

        private String neq_orgId;
		
		private String like_orgId;

        private String liker_orgId;

        private String likel_orgId;

        private String notlike_orgId;

        private String notliker_orgId;

        private String notlikel_orgId;
		
        private String in_orgId;

        private String notin_orgId;

        private String eq_identityId;

        private String neq_identityId;
		
		private String like_identityId;

        private String liker_identityId;

        private String likel_identityId;

        private String notlike_identityId;

        private String notliker_identityId;

        private String notlikel_identityId;
		
        private String in_identityId;

        private String notin_identityId;

        private String eq_refreshToken;

        private String neq_refreshToken;
		
		private String like_refreshToken;

        private String liker_refreshToken;

        private String likel_refreshToken;

        private String notlike_refreshToken;

        private String notliker_refreshToken;

        private String notlikel_refreshToken;
		
        private String in_refreshToken;

        private String notin_refreshToken;

        private String eq_userAgent;

        private String neq_userAgent;
		
		private String like_userAgent;

        private String liker_userAgent;

        private String likel_userAgent;

        private String notlike_userAgent;

        private String notliker_userAgent;

        private String notlikel_userAgent;
		
        private String in_userAgent;

        private String notin_userAgent;

        private String eq_ipaddr;

        private String neq_ipaddr;
		
		private String like_ipaddr;

        private String liker_ipaddr;

        private String likel_ipaddr;

        private String notlike_ipaddr;

        private String notliker_ipaddr;

        private String notlikel_ipaddr;
		
        private String in_ipaddr;

        private String notin_ipaddr;

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


        public String getEq_loginId()
        {
            return eq_loginId;
        }

        public LoginInfoSearchBo setEq_loginId(String eq_loginId)
        {
            this.eq_loginId = eq_loginId;
            return this;
        }

        public String getNeq_loginId()
        {
            return neq_loginId;
        }

        public LoginInfoSearchBo setNeq_loginId(String neq_loginId)
        {
            this.neq_loginId = neq_loginId;
            return this;
        }

        public String getIn_loginId()
        {
            return in_loginId;
        }

        public LoginInfoSearchBo setIn_loginId(String in_loginId)
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

        public String getEq_systemCode()
        {
            return eq_systemCode;
        }

        public LoginInfoSearchBo setEq_systemCode(String eq_systemCode)
        {
            this.eq_systemCode = eq_systemCode;
            return this;
        }

        public String getNeq_systemCode()
        {
            return neq_systemCode;
        }

        public LoginInfoSearchBo setNeq_systemCode(String neq_systemCode)
        {
            this.neq_systemCode = neq_systemCode;
            return this;
        }
		
        public String getLike_systemCode()
        {
            return like_systemCode;
        }

        public LoginInfoSearchBo setLike_systemCode(String like_systemCode)
        {
            this.like_systemCode = like_systemCode;
            return this;
        }

        public String getLiker_systemCode()
        {
            return liker_systemCode;
        }

        public LoginInfoSearchBo setLiker_systemCode(String liker_systemCode)
        {
            this.liker_systemCode = liker_systemCode;
            return this;
        }

        public String getLikel_systemCode()
        {
            return likel_systemCode;
        }

        public LoginInfoSearchBo setLikel_systemCode(String likel_systemCode)
        {
            this.likel_systemCode = likel_systemCode;
            return this;
        }

        public String getNotlike_systemCode()
        {
            return notlike_systemCode;
        }

        public LoginInfoSearchBo setNotlike_systemCode(String notlike_systemCode)
        {
            this.notlike_systemCode = notlike_systemCode;
            return this;
        }

        public String getNotliker_systemCode()
        {
            return notliker_systemCode;
        }

        public LoginInfoSearchBo setNotliker_systemCode(String notliker_systemCode)
        {
            this.notliker_systemCode = notliker_systemCode;
            return this;
        }

        public String getNotlikel_systemCode()
        {
            return notlikel_systemCode;
        }

        public LoginInfoSearchBo setNotlikel_systemCode(String notlikel_systemCode)
        {
            this.notlikel_systemCode = notlikel_systemCode;
            return this;
        }

        public String getIn_systemCode()
        {
            return in_systemCode;
        }

        public LoginInfoSearchBo setIn_systemCode(String in_systemCode)
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

        public String getEq_userId()
        {
            return eq_userId;
        }

        public LoginInfoSearchBo setEq_userId(String eq_userId)
        {
            this.eq_userId = eq_userId;
            return this;
        }

        public String getNeq_userId()
        {
            return neq_userId;
        }

        public LoginInfoSearchBo setNeq_userId(String neq_userId)
        {
            this.neq_userId = neq_userId;
            return this;
        }
		
        public String getLike_userId()
        {
            return like_userId;
        }

        public LoginInfoSearchBo setLike_userId(String like_userId)
        {
            this.like_userId = like_userId;
            return this;
        }

        public String getLiker_userId()
        {
            return liker_userId;
        }

        public LoginInfoSearchBo setLiker_userId(String liker_userId)
        {
            this.liker_userId = liker_userId;
            return this;
        }

        public String getLikel_userId()
        {
            return likel_userId;
        }

        public LoginInfoSearchBo setLikel_userId(String likel_userId)
        {
            this.likel_userId = likel_userId;
            return this;
        }

        public String getNotlike_userId()
        {
            return notlike_userId;
        }

        public LoginInfoSearchBo setNotlike_userId(String notlike_userId)
        {
            this.notlike_userId = notlike_userId;
            return this;
        }

        public String getNotliker_userId()
        {
            return notliker_userId;
        }

        public LoginInfoSearchBo setNotliker_userId(String notliker_userId)
        {
            this.notliker_userId = notliker_userId;
            return this;
        }

        public String getNotlikel_userId()
        {
            return notlikel_userId;
        }

        public LoginInfoSearchBo setNotlikel_userId(String notlikel_userId)
        {
            this.notlikel_userId = notlikel_userId;
            return this;
        }

        public String getIn_userId()
        {
            return in_userId;
        }

        public LoginInfoSearchBo setIn_userId(String in_userId)
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

        public String getEq_orgId()
        {
            return eq_orgId;
        }

        public LoginInfoSearchBo setEq_orgId(String eq_orgId)
        {
            this.eq_orgId = eq_orgId;
            return this;
        }

        public String getNeq_orgId()
        {
            return neq_orgId;
        }

        public LoginInfoSearchBo setNeq_orgId(String neq_orgId)
        {
            this.neq_orgId = neq_orgId;
            return this;
        }
		
        public String getLike_orgId()
        {
            return like_orgId;
        }

        public LoginInfoSearchBo setLike_orgId(String like_orgId)
        {
            this.like_orgId = like_orgId;
            return this;
        }

        public String getLiker_orgId()
        {
            return liker_orgId;
        }

        public LoginInfoSearchBo setLiker_orgId(String liker_orgId)
        {
            this.liker_orgId = liker_orgId;
            return this;
        }

        public String getLikel_orgId()
        {
            return likel_orgId;
        }

        public LoginInfoSearchBo setLikel_orgId(String likel_orgId)
        {
            this.likel_orgId = likel_orgId;
            return this;
        }

        public String getNotlike_orgId()
        {
            return notlike_orgId;
        }

        public LoginInfoSearchBo setNotlike_orgId(String notlike_orgId)
        {
            this.notlike_orgId = notlike_orgId;
            return this;
        }

        public String getNotliker_orgId()
        {
            return notliker_orgId;
        }

        public LoginInfoSearchBo setNotliker_orgId(String notliker_orgId)
        {
            this.notliker_orgId = notliker_orgId;
            return this;
        }

        public String getNotlikel_orgId()
        {
            return notlikel_orgId;
        }

        public LoginInfoSearchBo setNotlikel_orgId(String notlikel_orgId)
        {
            this.notlikel_orgId = notlikel_orgId;
            return this;
        }

        public String getIn_orgId()
        {
            return in_orgId;
        }

        public LoginInfoSearchBo setIn_orgId(String in_orgId)
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

        public String getEq_identityId()
        {
            return eq_identityId;
        }

        public LoginInfoSearchBo setEq_identityId(String eq_identityId)
        {
            this.eq_identityId = eq_identityId;
            return this;
        }

        public String getNeq_identityId()
        {
            return neq_identityId;
        }

        public LoginInfoSearchBo setNeq_identityId(String neq_identityId)
        {
            this.neq_identityId = neq_identityId;
            return this;
        }
		
        public String getLike_identityId()
        {
            return like_identityId;
        }

        public LoginInfoSearchBo setLike_identityId(String like_identityId)
        {
            this.like_identityId = like_identityId;
            return this;
        }

        public String getLiker_identityId()
        {
            return liker_identityId;
        }

        public LoginInfoSearchBo setLiker_identityId(String liker_identityId)
        {
            this.liker_identityId = liker_identityId;
            return this;
        }

        public String getLikel_identityId()
        {
            return likel_identityId;
        }

        public LoginInfoSearchBo setLikel_identityId(String likel_identityId)
        {
            this.likel_identityId = likel_identityId;
            return this;
        }

        public String getNotlike_identityId()
        {
            return notlike_identityId;
        }

        public LoginInfoSearchBo setNotlike_identityId(String notlike_identityId)
        {
            this.notlike_identityId = notlike_identityId;
            return this;
        }

        public String getNotliker_identityId()
        {
            return notliker_identityId;
        }

        public LoginInfoSearchBo setNotliker_identityId(String notliker_identityId)
        {
            this.notliker_identityId = notliker_identityId;
            return this;
        }

        public String getNotlikel_identityId()
        {
            return notlikel_identityId;
        }

        public LoginInfoSearchBo setNotlikel_identityId(String notlikel_identityId)
        {
            this.notlikel_identityId = notlikel_identityId;
            return this;
        }

        public String getIn_identityId()
        {
            return in_identityId;
        }

        public LoginInfoSearchBo setIn_identityId(String in_identityId)
        {
            this.in_identityId = in_identityId;
            return this;
        }

        public String getNotin_identityId()
        {
            return notin_identityId;
        }

        public void setNotin_identityId(String notin_identityId)
        {
            this.notin_identityId = notin_identityId;
        }

        public String getEq_refreshToken()
        {
            return eq_refreshToken;
        }

        public LoginInfoSearchBo setEq_refreshToken(String eq_refreshToken)
        {
            this.eq_refreshToken = eq_refreshToken;
            return this;
        }

        public String getNeq_refreshToken()
        {
            return neq_refreshToken;
        }

        public LoginInfoSearchBo setNeq_refreshToken(String neq_refreshToken)
        {
            this.neq_refreshToken = neq_refreshToken;
            return this;
        }
		
        public String getLike_refreshToken()
        {
            return like_refreshToken;
        }

        public LoginInfoSearchBo setLike_refreshToken(String like_refreshToken)
        {
            this.like_refreshToken = like_refreshToken;
            return this;
        }

        public String getLiker_refreshToken()
        {
            return liker_refreshToken;
        }

        public LoginInfoSearchBo setLiker_refreshToken(String liker_refreshToken)
        {
            this.liker_refreshToken = liker_refreshToken;
            return this;
        }

        public String getLikel_refreshToken()
        {
            return likel_refreshToken;
        }

        public LoginInfoSearchBo setLikel_refreshToken(String likel_refreshToken)
        {
            this.likel_refreshToken = likel_refreshToken;
            return this;
        }

        public String getNotlike_refreshToken()
        {
            return notlike_refreshToken;
        }

        public LoginInfoSearchBo setNotlike_refreshToken(String notlike_refreshToken)
        {
            this.notlike_refreshToken = notlike_refreshToken;
            return this;
        }

        public String getNotliker_refreshToken()
        {
            return notliker_refreshToken;
        }

        public LoginInfoSearchBo setNotliker_refreshToken(String notliker_refreshToken)
        {
            this.notliker_refreshToken = notliker_refreshToken;
            return this;
        }

        public String getNotlikel_refreshToken()
        {
            return notlikel_refreshToken;
        }

        public LoginInfoSearchBo setNotlikel_refreshToken(String notlikel_refreshToken)
        {
            this.notlikel_refreshToken = notlikel_refreshToken;
            return this;
        }

        public String getIn_refreshToken()
        {
            return in_refreshToken;
        }

        public LoginInfoSearchBo setIn_refreshToken(String in_refreshToken)
        {
            this.in_refreshToken = in_refreshToken;
            return this;
        }

        public String getNotin_refreshToken()
        {
            return notin_refreshToken;
        }

        public void setNotin_refreshToken(String notin_refreshToken)
        {
            this.notin_refreshToken = notin_refreshToken;
        }

        public String getEq_userAgent()
        {
            return eq_userAgent;
        }

        public LoginInfoSearchBo setEq_userAgent(String eq_userAgent)
        {
            this.eq_userAgent = eq_userAgent;
            return this;
        }

        public String getNeq_userAgent()
        {
            return neq_userAgent;
        }

        public LoginInfoSearchBo setNeq_userAgent(String neq_userAgent)
        {
            this.neq_userAgent = neq_userAgent;
            return this;
        }
		
        public String getLike_userAgent()
        {
            return like_userAgent;
        }

        public LoginInfoSearchBo setLike_userAgent(String like_userAgent)
        {
            this.like_userAgent = like_userAgent;
            return this;
        }

        public String getLiker_userAgent()
        {
            return liker_userAgent;
        }

        public LoginInfoSearchBo setLiker_userAgent(String liker_userAgent)
        {
            this.liker_userAgent = liker_userAgent;
            return this;
        }

        public String getLikel_userAgent()
        {
            return likel_userAgent;
        }

        public LoginInfoSearchBo setLikel_userAgent(String likel_userAgent)
        {
            this.likel_userAgent = likel_userAgent;
            return this;
        }

        public String getNotlike_userAgent()
        {
            return notlike_userAgent;
        }

        public LoginInfoSearchBo setNotlike_userAgent(String notlike_userAgent)
        {
            this.notlike_userAgent = notlike_userAgent;
            return this;
        }

        public String getNotliker_userAgent()
        {
            return notliker_userAgent;
        }

        public LoginInfoSearchBo setNotliker_userAgent(String notliker_userAgent)
        {
            this.notliker_userAgent = notliker_userAgent;
            return this;
        }

        public String getNotlikel_userAgent()
        {
            return notlikel_userAgent;
        }

        public LoginInfoSearchBo setNotlikel_userAgent(String notlikel_userAgent)
        {
            this.notlikel_userAgent = notlikel_userAgent;
            return this;
        }

        public String getIn_userAgent()
        {
            return in_userAgent;
        }

        public LoginInfoSearchBo setIn_userAgent(String in_userAgent)
        {
            this.in_userAgent = in_userAgent;
            return this;
        }

        public String getNotin_userAgent()
        {
            return notin_userAgent;
        }

        public void setNotin_userAgent(String notin_userAgent)
        {
            this.notin_userAgent = notin_userAgent;
        }

        public String getEq_ipaddr()
        {
            return eq_ipaddr;
        }

        public LoginInfoSearchBo setEq_ipaddr(String eq_ipaddr)
        {
            this.eq_ipaddr = eq_ipaddr;
            return this;
        }

        public String getNeq_ipaddr()
        {
            return neq_ipaddr;
        }

        public LoginInfoSearchBo setNeq_ipaddr(String neq_ipaddr)
        {
            this.neq_ipaddr = neq_ipaddr;
            return this;
        }
		
        public String getLike_ipaddr()
        {
            return like_ipaddr;
        }

        public LoginInfoSearchBo setLike_ipaddr(String like_ipaddr)
        {
            this.like_ipaddr = like_ipaddr;
            return this;
        }

        public String getLiker_ipaddr()
        {
            return liker_ipaddr;
        }

        public LoginInfoSearchBo setLiker_ipaddr(String liker_ipaddr)
        {
            this.liker_ipaddr = liker_ipaddr;
            return this;
        }

        public String getLikel_ipaddr()
        {
            return likel_ipaddr;
        }

        public LoginInfoSearchBo setLikel_ipaddr(String likel_ipaddr)
        {
            this.likel_ipaddr = likel_ipaddr;
            return this;
        }

        public String getNotlike_ipaddr()
        {
            return notlike_ipaddr;
        }

        public LoginInfoSearchBo setNotlike_ipaddr(String notlike_ipaddr)
        {
            this.notlike_ipaddr = notlike_ipaddr;
            return this;
        }

        public String getNotliker_ipaddr()
        {
            return notliker_ipaddr;
        }

        public LoginInfoSearchBo setNotliker_ipaddr(String notliker_ipaddr)
        {
            this.notliker_ipaddr = notliker_ipaddr;
            return this;
        }

        public String getNotlikel_ipaddr()
        {
            return notlikel_ipaddr;
        }

        public LoginInfoSearchBo setNotlikel_ipaddr(String notlikel_ipaddr)
        {
            this.notlikel_ipaddr = notlikel_ipaddr;
            return this;
        }

        public String getIn_ipaddr()
        {
            return in_ipaddr;
        }

        public LoginInfoSearchBo setIn_ipaddr(String in_ipaddr)
        {
            this.in_ipaddr = in_ipaddr;
            return this;
        }

        public String getNotin_ipaddr()
        {
            return notin_ipaddr;
        }

        public void setNotin_ipaddr(String notin_ipaddr)
        {
            this.notin_ipaddr = notin_ipaddr;
        }

        public Date getEq_expireTime()
        {
            return eq_expireTime;
        }

        public LoginInfoSearchBo setEq_expireTime(Date eq_expireTime)
        {
            this.eq_expireTime = eq_expireTime;
            return this;
        }

        public Date getNeq_expireTime()
        {
            return neq_expireTime;
        }

        public LoginInfoSearchBo setNeq_expireTime(Date neq_expireTime)
        {
            this.neq_expireTime = neq_expireTime;
            return this;
        }
		
        public Date getGt_expireTime()
        {
            return gt_expireTime;
        }

        public LoginInfoSearchBo setGt_expireTime(Date gt_expireTime)
        {
            this.gt_expireTime = gt_expireTime;
            return this;
        }

        public Date getLt_expireTime()
        {
            return lt_expireTime;
        }

        public LoginInfoSearchBo setLt_expireTime(Date lt_expireTime)
        {
            this.lt_expireTime = lt_expireTime;
            return this;
        }

        public Date getEgt_expireTime()
        {
            return egt_expireTime;
        }

        public LoginInfoSearchBo setEgt_expireTime(Date egt_expireTime)
        {
            this.egt_expireTime = egt_expireTime;
            return this;
        }

        public Date getElt_expireTime()
        {
            return elt_expireTime;
        }

        public LoginInfoSearchBo setElt_expireTime(Date elt_expireTime)
        {
            this.elt_expireTime = elt_expireTime;
            return this;
        }
		
		public String getBetween_expireTime()
        {
            return between_expireTime;
        }

        public LoginInfoSearchBo setBetween_expireTime(String between_expireTime)
        {
            this.between_expireTime = between_expireTime;
            return this;
        }

        public String getNotbetween_expireTime()
        {
            return notbetween_expireTime;
        }

        public LoginInfoSearchBo setNotbetween_expireTime(String notbetween_expireTime)
        {
            this.notbetween_expireTime = notbetween_expireTime;
            return this;
        }

        public String getIn_expireTime()
        {
            return in_expireTime;
        }

        public LoginInfoSearchBo setIn_expireTime(String in_expireTime)
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

        public LoginInfoSearchBo setEq_createdTime(Date eq_createdTime)
        {
            this.eq_createdTime = eq_createdTime;
            return this;
        }

        public Date getNeq_createdTime()
        {
            return neq_createdTime;
        }

        public LoginInfoSearchBo setNeq_createdTime(Date neq_createdTime)
        {
            this.neq_createdTime = neq_createdTime;
            return this;
        }
		
        public Date getGt_createdTime()
        {
            return gt_createdTime;
        }

        public LoginInfoSearchBo setGt_createdTime(Date gt_createdTime)
        {
            this.gt_createdTime = gt_createdTime;
            return this;
        }

        public Date getLt_createdTime()
        {
            return lt_createdTime;
        }

        public LoginInfoSearchBo setLt_createdTime(Date lt_createdTime)
        {
            this.lt_createdTime = lt_createdTime;
            return this;
        }

        public Date getEgt_createdTime()
        {
            return egt_createdTime;
        }

        public LoginInfoSearchBo setEgt_createdTime(Date egt_createdTime)
        {
            this.egt_createdTime = egt_createdTime;
            return this;
        }

        public Date getElt_createdTime()
        {
            return elt_createdTime;
        }

        public LoginInfoSearchBo setElt_createdTime(Date elt_createdTime)
        {
            this.elt_createdTime = elt_createdTime;
            return this;
        }
		
		public String getBetween_createdTime()
        {
            return between_createdTime;
        }

        public LoginInfoSearchBo setBetween_createdTime(String between_createdTime)
        {
            this.between_createdTime = between_createdTime;
            return this;
        }

        public String getNotbetween_createdTime()
        {
            return notbetween_createdTime;
        }

        public LoginInfoSearchBo setNotbetween_createdTime(String notbetween_createdTime)
        {
            this.notbetween_createdTime = notbetween_createdTime;
            return this;
        }

        public String getIn_createdTime()
        {
            return in_createdTime;
        }

        public LoginInfoSearchBo setIn_createdTime(String in_createdTime)
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
