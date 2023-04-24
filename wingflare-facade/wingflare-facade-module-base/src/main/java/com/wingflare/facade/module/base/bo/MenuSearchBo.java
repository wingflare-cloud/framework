package com.wingflare.facade.module.base.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.util.Date;

/**
 * MenuSearchBo
 * 
 * @author naizui_ycx
 * @date Sat Mar 04 21:30:08 CST 2023
 */
public class MenuSearchBo extends BaseSearchBo
{

        private String eq_menuId;

        private String neq_menuId;
		
        private String in_menuId;

        private String notin_menuId;

        private String eq_parentMenuId;

        private String neq_parentMenuId;
		
		private String like_parentMenuId;

        private String liker_parentMenuId;

        private String likel_parentMenuId;

        private String notlike_parentMenuId;

        private String notliker_parentMenuId;

        private String notlikel_parentMenuId;
		
        private String in_parentMenuId;

        private String notin_parentMenuId;

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

        private String eq_menuType;

        private String neq_menuType;
		
		private String like_menuType;

        private String liker_menuType;

        private String likel_menuType;

        private String notlike_menuType;

        private String notliker_menuType;

        private String notlikel_menuType;
		
        private String in_menuType;

        private String notin_menuType;

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

        private String eq_permissionCode;

        private String neq_permissionCode;
		
		private String like_permissionCode;

        private String liker_permissionCode;

        private String likel_permissionCode;

        private String notlike_permissionCode;

        private String notliker_permissionCode;

        private String notlikel_permissionCode;
		
        private String in_permissionCode;

        private String notin_permissionCode;

        private String eq_menuName;

        private String neq_menuName;
		
		private String like_menuName;

        private String liker_menuName;

        private String likel_menuName;

        private String notlike_menuName;

        private String notliker_menuName;

        private String notlikel_menuName;
		
        private String in_menuName;

        private String notin_menuName;

        private String eq_menuIcon;

        private String neq_menuIcon;
		
		private String like_menuIcon;

        private String liker_menuIcon;

        private String likel_menuIcon;

        private String notlike_menuIcon;

        private String notliker_menuIcon;

        private String notlikel_menuIcon;
		
        private String in_menuIcon;

        private String notin_menuIcon;

        private String eq_menuPath;

        private String neq_menuPath;
		
		private String like_menuPath;

        private String liker_menuPath;

        private String likel_menuPath;

        private String notlike_menuPath;

        private String notliker_menuPath;

        private String notlikel_menuPath;
		
        private String in_menuPath;

        private String notin_menuPath;

        private String eq_menuCode;

        private String neq_menuCode;
		
		private String like_menuCode;

        private String liker_menuCode;

        private String likel_menuCode;

        private String notlike_menuCode;

        private String notliker_menuCode;

        private String notlikel_menuCode;
		
        private String in_menuCode;

        private String notin_menuCode;

        private String eq_href;

        private String neq_href;
		
		private String like_href;

        private String liker_href;

        private String likel_href;

        private String notlike_href;

        private String notliker_href;

        private String notlikel_href;
		
        private String in_href;

        private String notin_href;

        private Long eq_sort;

        private Long neq_sort;
		
        private Long gt_sort;

        private Long lt_sort;

        private Long egt_sort;

        private Long elt_sort;
		
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


        public String getEq_menuId()
        {
            return eq_menuId;
        }

        public MenuSearchBo setEq_menuId(String eq_menuId)
        {
            this.eq_menuId = eq_menuId;
            return this;
        }

        public String getNeq_menuId()
        {
            return neq_menuId;
        }

        public MenuSearchBo setNeq_menuId(String neq_menuId)
        {
            this.neq_menuId = neq_menuId;
            return this;
        }

        public String getIn_menuId()
        {
            return in_menuId;
        }

        public MenuSearchBo setIn_menuId(String in_menuId)
        {
            this.in_menuId = in_menuId;
            return this;
        }

        public String getNotin_menuId()
        {
            return notin_menuId;
        }

        public void setNotin_menuId(String notin_menuId)
        {
            this.notin_menuId = notin_menuId;
        }

        public String getEq_parentMenuId()
        {
            return eq_parentMenuId;
        }

        public MenuSearchBo setEq_parentMenuId(String eq_parentMenuId)
        {
            this.eq_parentMenuId = eq_parentMenuId;
            return this;
        }

        public String getNeq_parentMenuId()
        {
            return neq_parentMenuId;
        }

        public MenuSearchBo setNeq_parentMenuId(String neq_parentMenuId)
        {
            this.neq_parentMenuId = neq_parentMenuId;
            return this;
        }
		
        public String getLike_parentMenuId()
        {
            return like_parentMenuId;
        }

        public MenuSearchBo setLike_parentMenuId(String like_parentMenuId)
        {
            this.like_parentMenuId = like_parentMenuId;
            return this;
        }

        public String getLiker_parentMenuId()
        {
            return liker_parentMenuId;
        }

        public MenuSearchBo setLiker_parentMenuId(String liker_parentMenuId)
        {
            this.liker_parentMenuId = liker_parentMenuId;
            return this;
        }

        public String getLikel_parentMenuId()
        {
            return likel_parentMenuId;
        }

        public MenuSearchBo setLikel_parentMenuId(String likel_parentMenuId)
        {
            this.likel_parentMenuId = likel_parentMenuId;
            return this;
        }

        public String getNotlike_parentMenuId()
        {
            return notlike_parentMenuId;
        }

        public MenuSearchBo setNotlike_parentMenuId(String notlike_parentMenuId)
        {
            this.notlike_parentMenuId = notlike_parentMenuId;
            return this;
        }

        public String getNotliker_parentMenuId()
        {
            return notliker_parentMenuId;
        }

        public MenuSearchBo setNotliker_parentMenuId(String notliker_parentMenuId)
        {
            this.notliker_parentMenuId = notliker_parentMenuId;
            return this;
        }

        public String getNotlikel_parentMenuId()
        {
            return notlikel_parentMenuId;
        }

        public MenuSearchBo setNotlikel_parentMenuId(String notlikel_parentMenuId)
        {
            this.notlikel_parentMenuId = notlikel_parentMenuId;
            return this;
        }

        public String getIn_parentMenuId()
        {
            return in_parentMenuId;
        }

        public MenuSearchBo setIn_parentMenuId(String in_parentMenuId)
        {
            this.in_parentMenuId = in_parentMenuId;
            return this;
        }

        public String getNotin_parentMenuId()
        {
            return notin_parentMenuId;
        }

        public void setNotin_parentMenuId(String notin_parentMenuId)
        {
            this.notin_parentMenuId = notin_parentMenuId;
        }

        public Integer getEq_state()
        {
            return eq_state;
        }

        public MenuSearchBo setEq_state(Integer eq_state)
        {
            this.eq_state = eq_state;
            return this;
        }

        public Integer getNeq_state()
        {
            return neq_state;
        }

        public MenuSearchBo setNeq_state(Integer neq_state)
        {
            this.neq_state = neq_state;
            return this;
        }
		
        public Integer getGt_state()
        {
            return gt_state;
        }

        public MenuSearchBo setGt_state(Integer gt_state)
        {
            this.gt_state = gt_state;
            return this;
        }

        public Integer getLt_state()
        {
            return lt_state;
        }

        public MenuSearchBo setLt_state(Integer lt_state)
        {
            this.lt_state = lt_state;
            return this;
        }

        public Integer getEgt_state()
        {
            return egt_state;
        }

        public MenuSearchBo setEgt_state(Integer egt_state)
        {
            this.egt_state = egt_state;
            return this;
        }

        public Integer getElt_state()
        {
            return elt_state;
        }

        public MenuSearchBo setElt_state(Integer elt_state)
        {
            this.elt_state = elt_state;
            return this;
        }
		
		public String getBetween_state()
        {
            return between_state;
        }

        public MenuSearchBo setBetween_state(String between_state)
        {
            this.between_state = between_state;
            return this;
        }

        public String getNotbetween_state()
        {
            return notbetween_state;
        }

        public MenuSearchBo setNotbetween_state(String notbetween_state)
        {
            this.notbetween_state = notbetween_state;
            return this;
        }

        public String getIn_state()
        {
            return in_state;
        }

        public MenuSearchBo setIn_state(String in_state)
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

        public String getEq_menuType()
        {
            return eq_menuType;
        }

        public MenuSearchBo setEq_menuType(String eq_menuType)
        {
            this.eq_menuType = eq_menuType;
            return this;
        }

        public String getNeq_menuType()
        {
            return neq_menuType;
        }

        public MenuSearchBo setNeq_menuType(String neq_menuType)
        {
            this.neq_menuType = neq_menuType;
            return this;
        }
		
        public String getLike_menuType()
        {
            return like_menuType;
        }

        public MenuSearchBo setLike_menuType(String like_menuType)
        {
            this.like_menuType = like_menuType;
            return this;
        }

        public String getLiker_menuType()
        {
            return liker_menuType;
        }

        public MenuSearchBo setLiker_menuType(String liker_menuType)
        {
            this.liker_menuType = liker_menuType;
            return this;
        }

        public String getLikel_menuType()
        {
            return likel_menuType;
        }

        public MenuSearchBo setLikel_menuType(String likel_menuType)
        {
            this.likel_menuType = likel_menuType;
            return this;
        }

        public String getNotlike_menuType()
        {
            return notlike_menuType;
        }

        public MenuSearchBo setNotlike_menuType(String notlike_menuType)
        {
            this.notlike_menuType = notlike_menuType;
            return this;
        }

        public String getNotliker_menuType()
        {
            return notliker_menuType;
        }

        public MenuSearchBo setNotliker_menuType(String notliker_menuType)
        {
            this.notliker_menuType = notliker_menuType;
            return this;
        }

        public String getNotlikel_menuType()
        {
            return notlikel_menuType;
        }

        public MenuSearchBo setNotlikel_menuType(String notlikel_menuType)
        {
            this.notlikel_menuType = notlikel_menuType;
            return this;
        }

        public String getIn_menuType()
        {
            return in_menuType;
        }

        public MenuSearchBo setIn_menuType(String in_menuType)
        {
            this.in_menuType = in_menuType;
            return this;
        }

        public String getNotin_menuType()
        {
            return notin_menuType;
        }

        public void setNotin_menuType(String notin_menuType)
        {
            this.notin_menuType = notin_menuType;
        }

        public String getEq_systemCode()
        {
            return eq_systemCode;
        }

        public MenuSearchBo setEq_systemCode(String eq_systemCode)
        {
            this.eq_systemCode = eq_systemCode;
            return this;
        }

        public String getNeq_systemCode()
        {
            return neq_systemCode;
        }

        public MenuSearchBo setNeq_systemCode(String neq_systemCode)
        {
            this.neq_systemCode = neq_systemCode;
            return this;
        }
		
        public String getLike_systemCode()
        {
            return like_systemCode;
        }

        public MenuSearchBo setLike_systemCode(String like_systemCode)
        {
            this.like_systemCode = like_systemCode;
            return this;
        }

        public String getLiker_systemCode()
        {
            return liker_systemCode;
        }

        public MenuSearchBo setLiker_systemCode(String liker_systemCode)
        {
            this.liker_systemCode = liker_systemCode;
            return this;
        }

        public String getLikel_systemCode()
        {
            return likel_systemCode;
        }

        public MenuSearchBo setLikel_systemCode(String likel_systemCode)
        {
            this.likel_systemCode = likel_systemCode;
            return this;
        }

        public String getNotlike_systemCode()
        {
            return notlike_systemCode;
        }

        public MenuSearchBo setNotlike_systemCode(String notlike_systemCode)
        {
            this.notlike_systemCode = notlike_systemCode;
            return this;
        }

        public String getNotliker_systemCode()
        {
            return notliker_systemCode;
        }

        public MenuSearchBo setNotliker_systemCode(String notliker_systemCode)
        {
            this.notliker_systemCode = notliker_systemCode;
            return this;
        }

        public String getNotlikel_systemCode()
        {
            return notlikel_systemCode;
        }

        public MenuSearchBo setNotlikel_systemCode(String notlikel_systemCode)
        {
            this.notlikel_systemCode = notlikel_systemCode;
            return this;
        }

        public String getIn_systemCode()
        {
            return in_systemCode;
        }

        public MenuSearchBo setIn_systemCode(String in_systemCode)
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

        public String getEq_permissionCode()
        {
            return eq_permissionCode;
        }

        public MenuSearchBo setEq_permissionCode(String eq_permissionCode)
        {
            this.eq_permissionCode = eq_permissionCode;
            return this;
        }

        public String getNeq_permissionCode()
        {
            return neq_permissionCode;
        }

        public MenuSearchBo setNeq_permissionCode(String neq_permissionCode)
        {
            this.neq_permissionCode = neq_permissionCode;
            return this;
        }
		
        public String getLike_permissionCode()
        {
            return like_permissionCode;
        }

        public MenuSearchBo setLike_permissionCode(String like_permissionCode)
        {
            this.like_permissionCode = like_permissionCode;
            return this;
        }

        public String getLiker_permissionCode()
        {
            return liker_permissionCode;
        }

        public MenuSearchBo setLiker_permissionCode(String liker_permissionCode)
        {
            this.liker_permissionCode = liker_permissionCode;
            return this;
        }

        public String getLikel_permissionCode()
        {
            return likel_permissionCode;
        }

        public MenuSearchBo setLikel_permissionCode(String likel_permissionCode)
        {
            this.likel_permissionCode = likel_permissionCode;
            return this;
        }

        public String getNotlike_permissionCode()
        {
            return notlike_permissionCode;
        }

        public MenuSearchBo setNotlike_permissionCode(String notlike_permissionCode)
        {
            this.notlike_permissionCode = notlike_permissionCode;
            return this;
        }

        public String getNotliker_permissionCode()
        {
            return notliker_permissionCode;
        }

        public MenuSearchBo setNotliker_permissionCode(String notliker_permissionCode)
        {
            this.notliker_permissionCode = notliker_permissionCode;
            return this;
        }

        public String getNotlikel_permissionCode()
        {
            return notlikel_permissionCode;
        }

        public MenuSearchBo setNotlikel_permissionCode(String notlikel_permissionCode)
        {
            this.notlikel_permissionCode = notlikel_permissionCode;
            return this;
        }

        public String getIn_permissionCode()
        {
            return in_permissionCode;
        }

        public MenuSearchBo setIn_permissionCode(String in_permissionCode)
        {
            this.in_permissionCode = in_permissionCode;
            return this;
        }

        public String getNotin_permissionCode()
        {
            return notin_permissionCode;
        }

        public void setNotin_permissionCode(String notin_permissionCode)
        {
            this.notin_permissionCode = notin_permissionCode;
        }

        public String getEq_menuName()
        {
            return eq_menuName;
        }

        public MenuSearchBo setEq_menuName(String eq_menuName)
        {
            this.eq_menuName = eq_menuName;
            return this;
        }

        public String getNeq_menuName()
        {
            return neq_menuName;
        }

        public MenuSearchBo setNeq_menuName(String neq_menuName)
        {
            this.neq_menuName = neq_menuName;
            return this;
        }
		
        public String getLike_menuName()
        {
            return like_menuName;
        }

        public MenuSearchBo setLike_menuName(String like_menuName)
        {
            this.like_menuName = like_menuName;
            return this;
        }

        public String getLiker_menuName()
        {
            return liker_menuName;
        }

        public MenuSearchBo setLiker_menuName(String liker_menuName)
        {
            this.liker_menuName = liker_menuName;
            return this;
        }

        public String getLikel_menuName()
        {
            return likel_menuName;
        }

        public MenuSearchBo setLikel_menuName(String likel_menuName)
        {
            this.likel_menuName = likel_menuName;
            return this;
        }

        public String getNotlike_menuName()
        {
            return notlike_menuName;
        }

        public MenuSearchBo setNotlike_menuName(String notlike_menuName)
        {
            this.notlike_menuName = notlike_menuName;
            return this;
        }

        public String getNotliker_menuName()
        {
            return notliker_menuName;
        }

        public MenuSearchBo setNotliker_menuName(String notliker_menuName)
        {
            this.notliker_menuName = notliker_menuName;
            return this;
        }

        public String getNotlikel_menuName()
        {
            return notlikel_menuName;
        }

        public MenuSearchBo setNotlikel_menuName(String notlikel_menuName)
        {
            this.notlikel_menuName = notlikel_menuName;
            return this;
        }

        public String getIn_menuName()
        {
            return in_menuName;
        }

        public MenuSearchBo setIn_menuName(String in_menuName)
        {
            this.in_menuName = in_menuName;
            return this;
        }

        public String getNotin_menuName()
        {
            return notin_menuName;
        }

        public void setNotin_menuName(String notin_menuName)
        {
            this.notin_menuName = notin_menuName;
        }

        public String getEq_menuIcon()
        {
            return eq_menuIcon;
        }

        public MenuSearchBo setEq_menuIcon(String eq_menuIcon)
        {
            this.eq_menuIcon = eq_menuIcon;
            return this;
        }

        public String getNeq_menuIcon()
        {
            return neq_menuIcon;
        }

        public MenuSearchBo setNeq_menuIcon(String neq_menuIcon)
        {
            this.neq_menuIcon = neq_menuIcon;
            return this;
        }
		
        public String getLike_menuIcon()
        {
            return like_menuIcon;
        }

        public MenuSearchBo setLike_menuIcon(String like_menuIcon)
        {
            this.like_menuIcon = like_menuIcon;
            return this;
        }

        public String getLiker_menuIcon()
        {
            return liker_menuIcon;
        }

        public MenuSearchBo setLiker_menuIcon(String liker_menuIcon)
        {
            this.liker_menuIcon = liker_menuIcon;
            return this;
        }

        public String getLikel_menuIcon()
        {
            return likel_menuIcon;
        }

        public MenuSearchBo setLikel_menuIcon(String likel_menuIcon)
        {
            this.likel_menuIcon = likel_menuIcon;
            return this;
        }

        public String getNotlike_menuIcon()
        {
            return notlike_menuIcon;
        }

        public MenuSearchBo setNotlike_menuIcon(String notlike_menuIcon)
        {
            this.notlike_menuIcon = notlike_menuIcon;
            return this;
        }

        public String getNotliker_menuIcon()
        {
            return notliker_menuIcon;
        }

        public MenuSearchBo setNotliker_menuIcon(String notliker_menuIcon)
        {
            this.notliker_menuIcon = notliker_menuIcon;
            return this;
        }

        public String getNotlikel_menuIcon()
        {
            return notlikel_menuIcon;
        }

        public MenuSearchBo setNotlikel_menuIcon(String notlikel_menuIcon)
        {
            this.notlikel_menuIcon = notlikel_menuIcon;
            return this;
        }

        public String getIn_menuIcon()
        {
            return in_menuIcon;
        }

        public MenuSearchBo setIn_menuIcon(String in_menuIcon)
        {
            this.in_menuIcon = in_menuIcon;
            return this;
        }

        public String getNotin_menuIcon()
        {
            return notin_menuIcon;
        }

        public void setNotin_menuIcon(String notin_menuIcon)
        {
            this.notin_menuIcon = notin_menuIcon;
        }

        public String getEq_menuPath()
        {
            return eq_menuPath;
        }

        public MenuSearchBo setEq_menuPath(String eq_menuPath)
        {
            this.eq_menuPath = eq_menuPath;
            return this;
        }

        public String getNeq_menuPath()
        {
            return neq_menuPath;
        }

        public MenuSearchBo setNeq_menuPath(String neq_menuPath)
        {
            this.neq_menuPath = neq_menuPath;
            return this;
        }
		
        public String getLike_menuPath()
        {
            return like_menuPath;
        }

        public MenuSearchBo setLike_menuPath(String like_menuPath)
        {
            this.like_menuPath = like_menuPath;
            return this;
        }

        public String getLiker_menuPath()
        {
            return liker_menuPath;
        }

        public MenuSearchBo setLiker_menuPath(String liker_menuPath)
        {
            this.liker_menuPath = liker_menuPath;
            return this;
        }

        public String getLikel_menuPath()
        {
            return likel_menuPath;
        }

        public MenuSearchBo setLikel_menuPath(String likel_menuPath)
        {
            this.likel_menuPath = likel_menuPath;
            return this;
        }

        public String getNotlike_menuPath()
        {
            return notlike_menuPath;
        }

        public MenuSearchBo setNotlike_menuPath(String notlike_menuPath)
        {
            this.notlike_menuPath = notlike_menuPath;
            return this;
        }

        public String getNotliker_menuPath()
        {
            return notliker_menuPath;
        }

        public MenuSearchBo setNotliker_menuPath(String notliker_menuPath)
        {
            this.notliker_menuPath = notliker_menuPath;
            return this;
        }

        public String getNotlikel_menuPath()
        {
            return notlikel_menuPath;
        }

        public MenuSearchBo setNotlikel_menuPath(String notlikel_menuPath)
        {
            this.notlikel_menuPath = notlikel_menuPath;
            return this;
        }

        public String getIn_menuPath()
        {
            return in_menuPath;
        }

        public MenuSearchBo setIn_menuPath(String in_menuPath)
        {
            this.in_menuPath = in_menuPath;
            return this;
        }

        public String getNotin_menuPath()
        {
            return notin_menuPath;
        }

        public void setNotin_menuPath(String notin_menuPath)
        {
            this.notin_menuPath = notin_menuPath;
        }

        public String getEq_menuCode()
        {
            return eq_menuCode;
        }

        public MenuSearchBo setEq_menuCode(String eq_menuCode)
        {
            this.eq_menuCode = eq_menuCode;
            return this;
        }

        public String getNeq_menuCode()
        {
            return neq_menuCode;
        }

        public MenuSearchBo setNeq_menuCode(String neq_menuCode)
        {
            this.neq_menuCode = neq_menuCode;
            return this;
        }
		
        public String getLike_menuCode()
        {
            return like_menuCode;
        }

        public MenuSearchBo setLike_menuCode(String like_menuCode)
        {
            this.like_menuCode = like_menuCode;
            return this;
        }

        public String getLiker_menuCode()
        {
            return liker_menuCode;
        }

        public MenuSearchBo setLiker_menuCode(String liker_menuCode)
        {
            this.liker_menuCode = liker_menuCode;
            return this;
        }

        public String getLikel_menuCode()
        {
            return likel_menuCode;
        }

        public MenuSearchBo setLikel_menuCode(String likel_menuCode)
        {
            this.likel_menuCode = likel_menuCode;
            return this;
        }

        public String getNotlike_menuCode()
        {
            return notlike_menuCode;
        }

        public MenuSearchBo setNotlike_menuCode(String notlike_menuCode)
        {
            this.notlike_menuCode = notlike_menuCode;
            return this;
        }

        public String getNotliker_menuCode()
        {
            return notliker_menuCode;
        }

        public MenuSearchBo setNotliker_menuCode(String notliker_menuCode)
        {
            this.notliker_menuCode = notliker_menuCode;
            return this;
        }

        public String getNotlikel_menuCode()
        {
            return notlikel_menuCode;
        }

        public MenuSearchBo setNotlikel_menuCode(String notlikel_menuCode)
        {
            this.notlikel_menuCode = notlikel_menuCode;
            return this;
        }

        public String getIn_menuCode()
        {
            return in_menuCode;
        }

        public MenuSearchBo setIn_menuCode(String in_menuCode)
        {
            this.in_menuCode = in_menuCode;
            return this;
        }

        public String getNotin_menuCode()
        {
            return notin_menuCode;
        }

        public void setNotin_menuCode(String notin_menuCode)
        {
            this.notin_menuCode = notin_menuCode;
        }

        public String getEq_href()
        {
            return eq_href;
        }

        public MenuSearchBo setEq_href(String eq_href)
        {
            this.eq_href = eq_href;
            return this;
        }

        public String getNeq_href()
        {
            return neq_href;
        }

        public MenuSearchBo setNeq_href(String neq_href)
        {
            this.neq_href = neq_href;
            return this;
        }
		
        public String getLike_href()
        {
            return like_href;
        }

        public MenuSearchBo setLike_href(String like_href)
        {
            this.like_href = like_href;
            return this;
        }

        public String getLiker_href()
        {
            return liker_href;
        }

        public MenuSearchBo setLiker_href(String liker_href)
        {
            this.liker_href = liker_href;
            return this;
        }

        public String getLikel_href()
        {
            return likel_href;
        }

        public MenuSearchBo setLikel_href(String likel_href)
        {
            this.likel_href = likel_href;
            return this;
        }

        public String getNotlike_href()
        {
            return notlike_href;
        }

        public MenuSearchBo setNotlike_href(String notlike_href)
        {
            this.notlike_href = notlike_href;
            return this;
        }

        public String getNotliker_href()
        {
            return notliker_href;
        }

        public MenuSearchBo setNotliker_href(String notliker_href)
        {
            this.notliker_href = notliker_href;
            return this;
        }

        public String getNotlikel_href()
        {
            return notlikel_href;
        }

        public MenuSearchBo setNotlikel_href(String notlikel_href)
        {
            this.notlikel_href = notlikel_href;
            return this;
        }

        public String getIn_href()
        {
            return in_href;
        }

        public MenuSearchBo setIn_href(String in_href)
        {
            this.in_href = in_href;
            return this;
        }

        public String getNotin_href()
        {
            return notin_href;
        }

        public void setNotin_href(String notin_href)
        {
            this.notin_href = notin_href;
        }

        public Long getEq_sort()
        {
            return eq_sort;
        }

        public MenuSearchBo setEq_sort(Long eq_sort)
        {
            this.eq_sort = eq_sort;
            return this;
        }

        public Long getNeq_sort()
        {
            return neq_sort;
        }

        public MenuSearchBo setNeq_sort(Long neq_sort)
        {
            this.neq_sort = neq_sort;
            return this;
        }
		
        public Long getGt_sort()
        {
            return gt_sort;
        }

        public MenuSearchBo setGt_sort(Long gt_sort)
        {
            this.gt_sort = gt_sort;
            return this;
        }

        public Long getLt_sort()
        {
            return lt_sort;
        }

        public MenuSearchBo setLt_sort(Long lt_sort)
        {
            this.lt_sort = lt_sort;
            return this;
        }

        public Long getEgt_sort()
        {
            return egt_sort;
        }

        public MenuSearchBo setEgt_sort(Long egt_sort)
        {
            this.egt_sort = egt_sort;
            return this;
        }

        public Long getElt_sort()
        {
            return elt_sort;
        }

        public MenuSearchBo setElt_sort(Long elt_sort)
        {
            this.elt_sort = elt_sort;
            return this;
        }
		
		public String getBetween_sort()
        {
            return between_sort;
        }

        public MenuSearchBo setBetween_sort(String between_sort)
        {
            this.between_sort = between_sort;
            return this;
        }

        public String getNotbetween_sort()
        {
            return notbetween_sort;
        }

        public MenuSearchBo setNotbetween_sort(String notbetween_sort)
        {
            this.notbetween_sort = notbetween_sort;
            return this;
        }

        public String getIn_sort()
        {
            return in_sort;
        }

        public MenuSearchBo setIn_sort(String in_sort)
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

        public MenuSearchBo setEq_createdTime(Date eq_createdTime)
        {
            this.eq_createdTime = eq_createdTime;
            return this;
        }

        public Date getNeq_createdTime()
        {
            return neq_createdTime;
        }

        public MenuSearchBo setNeq_createdTime(Date neq_createdTime)
        {
            this.neq_createdTime = neq_createdTime;
            return this;
        }
		
        public Date getGt_createdTime()
        {
            return gt_createdTime;
        }

        public MenuSearchBo setGt_createdTime(Date gt_createdTime)
        {
            this.gt_createdTime = gt_createdTime;
            return this;
        }

        public Date getLt_createdTime()
        {
            return lt_createdTime;
        }

        public MenuSearchBo setLt_createdTime(Date lt_createdTime)
        {
            this.lt_createdTime = lt_createdTime;
            return this;
        }

        public Date getEgt_createdTime()
        {
            return egt_createdTime;
        }

        public MenuSearchBo setEgt_createdTime(Date egt_createdTime)
        {
            this.egt_createdTime = egt_createdTime;
            return this;
        }

        public Date getElt_createdTime()
        {
            return elt_createdTime;
        }

        public MenuSearchBo setElt_createdTime(Date elt_createdTime)
        {
            this.elt_createdTime = elt_createdTime;
            return this;
        }
		
		public String getBetween_createdTime()
        {
            return between_createdTime;
        }

        public MenuSearchBo setBetween_createdTime(String between_createdTime)
        {
            this.between_createdTime = between_createdTime;
            return this;
        }

        public String getNotbetween_createdTime()
        {
            return notbetween_createdTime;
        }

        public MenuSearchBo setNotbetween_createdTime(String notbetween_createdTime)
        {
            this.notbetween_createdTime = notbetween_createdTime;
            return this;
        }

        public String getIn_createdTime()
        {
            return in_createdTime;
        }

        public MenuSearchBo setIn_createdTime(String in_createdTime)
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

        public MenuSearchBo setEq_updatedTime(Date eq_updatedTime)
        {
            this.eq_updatedTime = eq_updatedTime;
            return this;
        }

        public Date getNeq_updatedTime()
        {
            return neq_updatedTime;
        }

        public MenuSearchBo setNeq_updatedTime(Date neq_updatedTime)
        {
            this.neq_updatedTime = neq_updatedTime;
            return this;
        }
		
        public Date getGt_updatedTime()
        {
            return gt_updatedTime;
        }

        public MenuSearchBo setGt_updatedTime(Date gt_updatedTime)
        {
            this.gt_updatedTime = gt_updatedTime;
            return this;
        }

        public Date getLt_updatedTime()
        {
            return lt_updatedTime;
        }

        public MenuSearchBo setLt_updatedTime(Date lt_updatedTime)
        {
            this.lt_updatedTime = lt_updatedTime;
            return this;
        }

        public Date getEgt_updatedTime()
        {
            return egt_updatedTime;
        }

        public MenuSearchBo setEgt_updatedTime(Date egt_updatedTime)
        {
            this.egt_updatedTime = egt_updatedTime;
            return this;
        }

        public Date getElt_updatedTime()
        {
            return elt_updatedTime;
        }

        public MenuSearchBo setElt_updatedTime(Date elt_updatedTime)
        {
            this.elt_updatedTime = elt_updatedTime;
            return this;
        }
		
		public String getBetween_updatedTime()
        {
            return between_updatedTime;
        }

        public MenuSearchBo setBetween_updatedTime(String between_updatedTime)
        {
            this.between_updatedTime = between_updatedTime;
            return this;
        }

        public String getNotbetween_updatedTime()
        {
            return notbetween_updatedTime;
        }

        public MenuSearchBo setNotbetween_updatedTime(String notbetween_updatedTime)
        {
            this.notbetween_updatedTime = notbetween_updatedTime;
            return this;
        }

        public String getIn_updatedTime()
        {
            return in_updatedTime;
        }

        public MenuSearchBo setIn_updatedTime(String in_updatedTime)
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

        public MenuSearchBo setEq_createUser(String eq_createUser)
        {
            this.eq_createUser = eq_createUser;
            return this;
        }

        public String getNeq_createUser()
        {
            return neq_createUser;
        }

        public MenuSearchBo setNeq_createUser(String neq_createUser)
        {
            this.neq_createUser = neq_createUser;
            return this;
        }
		
        public String getLike_createUser()
        {
            return like_createUser;
        }

        public MenuSearchBo setLike_createUser(String like_createUser)
        {
            this.like_createUser = like_createUser;
            return this;
        }

        public String getLiker_createUser()
        {
            return liker_createUser;
        }

        public MenuSearchBo setLiker_createUser(String liker_createUser)
        {
            this.liker_createUser = liker_createUser;
            return this;
        }

        public String getLikel_createUser()
        {
            return likel_createUser;
        }

        public MenuSearchBo setLikel_createUser(String likel_createUser)
        {
            this.likel_createUser = likel_createUser;
            return this;
        }

        public String getNotlike_createUser()
        {
            return notlike_createUser;
        }

        public MenuSearchBo setNotlike_createUser(String notlike_createUser)
        {
            this.notlike_createUser = notlike_createUser;
            return this;
        }

        public String getNotliker_createUser()
        {
            return notliker_createUser;
        }

        public MenuSearchBo setNotliker_createUser(String notliker_createUser)
        {
            this.notliker_createUser = notliker_createUser;
            return this;
        }

        public String getNotlikel_createUser()
        {
            return notlikel_createUser;
        }

        public MenuSearchBo setNotlikel_createUser(String notlikel_createUser)
        {
            this.notlikel_createUser = notlikel_createUser;
            return this;
        }

        public String getIn_createUser()
        {
            return in_createUser;
        }

        public MenuSearchBo setIn_createUser(String in_createUser)
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

        public MenuSearchBo setEq_createUserId(String eq_createUserId)
        {
            this.eq_createUserId = eq_createUserId;
            return this;
        }

        public String getNeq_createUserId()
        {
            return neq_createUserId;
        }

        public MenuSearchBo setNeq_createUserId(String neq_createUserId)
        {
            this.neq_createUserId = neq_createUserId;
            return this;
        }
		
        public String getLike_createUserId()
        {
            return like_createUserId;
        }

        public MenuSearchBo setLike_createUserId(String like_createUserId)
        {
            this.like_createUserId = like_createUserId;
            return this;
        }

        public String getLiker_createUserId()
        {
            return liker_createUserId;
        }

        public MenuSearchBo setLiker_createUserId(String liker_createUserId)
        {
            this.liker_createUserId = liker_createUserId;
            return this;
        }

        public String getLikel_createUserId()
        {
            return likel_createUserId;
        }

        public MenuSearchBo setLikel_createUserId(String likel_createUserId)
        {
            this.likel_createUserId = likel_createUserId;
            return this;
        }

        public String getNotlike_createUserId()
        {
            return notlike_createUserId;
        }

        public MenuSearchBo setNotlike_createUserId(String notlike_createUserId)
        {
            this.notlike_createUserId = notlike_createUserId;
            return this;
        }

        public String getNotliker_createUserId()
        {
            return notliker_createUserId;
        }

        public MenuSearchBo setNotliker_createUserId(String notliker_createUserId)
        {
            this.notliker_createUserId = notliker_createUserId;
            return this;
        }

        public String getNotlikel_createUserId()
        {
            return notlikel_createUserId;
        }

        public MenuSearchBo setNotlikel_createUserId(String notlikel_createUserId)
        {
            this.notlikel_createUserId = notlikel_createUserId;
            return this;
        }

        public String getIn_createUserId()
        {
            return in_createUserId;
        }

        public MenuSearchBo setIn_createUserId(String in_createUserId)
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

        public MenuSearchBo setEq_updateUser(String eq_updateUser)
        {
            this.eq_updateUser = eq_updateUser;
            return this;
        }

        public String getNeq_updateUser()
        {
            return neq_updateUser;
        }

        public MenuSearchBo setNeq_updateUser(String neq_updateUser)
        {
            this.neq_updateUser = neq_updateUser;
            return this;
        }
		
        public String getLike_updateUser()
        {
            return like_updateUser;
        }

        public MenuSearchBo setLike_updateUser(String like_updateUser)
        {
            this.like_updateUser = like_updateUser;
            return this;
        }

        public String getLiker_updateUser()
        {
            return liker_updateUser;
        }

        public MenuSearchBo setLiker_updateUser(String liker_updateUser)
        {
            this.liker_updateUser = liker_updateUser;
            return this;
        }

        public String getLikel_updateUser()
        {
            return likel_updateUser;
        }

        public MenuSearchBo setLikel_updateUser(String likel_updateUser)
        {
            this.likel_updateUser = likel_updateUser;
            return this;
        }

        public String getNotlike_updateUser()
        {
            return notlike_updateUser;
        }

        public MenuSearchBo setNotlike_updateUser(String notlike_updateUser)
        {
            this.notlike_updateUser = notlike_updateUser;
            return this;
        }

        public String getNotliker_updateUser()
        {
            return notliker_updateUser;
        }

        public MenuSearchBo setNotliker_updateUser(String notliker_updateUser)
        {
            this.notliker_updateUser = notliker_updateUser;
            return this;
        }

        public String getNotlikel_updateUser()
        {
            return notlikel_updateUser;
        }

        public MenuSearchBo setNotlikel_updateUser(String notlikel_updateUser)
        {
            this.notlikel_updateUser = notlikel_updateUser;
            return this;
        }

        public String getIn_updateUser()
        {
            return in_updateUser;
        }

        public MenuSearchBo setIn_updateUser(String in_updateUser)
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

        public MenuSearchBo setEq_updateUserId(String eq_updateUserId)
        {
            this.eq_updateUserId = eq_updateUserId;
            return this;
        }

        public String getNeq_updateUserId()
        {
            return neq_updateUserId;
        }

        public MenuSearchBo setNeq_updateUserId(String neq_updateUserId)
        {
            this.neq_updateUserId = neq_updateUserId;
            return this;
        }
		
        public String getLike_updateUserId()
        {
            return like_updateUserId;
        }

        public MenuSearchBo setLike_updateUserId(String like_updateUserId)
        {
            this.like_updateUserId = like_updateUserId;
            return this;
        }

        public String getLiker_updateUserId()
        {
            return liker_updateUserId;
        }

        public MenuSearchBo setLiker_updateUserId(String liker_updateUserId)
        {
            this.liker_updateUserId = liker_updateUserId;
            return this;
        }

        public String getLikel_updateUserId()
        {
            return likel_updateUserId;
        }

        public MenuSearchBo setLikel_updateUserId(String likel_updateUserId)
        {
            this.likel_updateUserId = likel_updateUserId;
            return this;
        }

        public String getNotlike_updateUserId()
        {
            return notlike_updateUserId;
        }

        public MenuSearchBo setNotlike_updateUserId(String notlike_updateUserId)
        {
            this.notlike_updateUserId = notlike_updateUserId;
            return this;
        }

        public String getNotliker_updateUserId()
        {
            return notliker_updateUserId;
        }

        public MenuSearchBo setNotliker_updateUserId(String notliker_updateUserId)
        {
            this.notliker_updateUserId = notliker_updateUserId;
            return this;
        }

        public String getNotlikel_updateUserId()
        {
            return notlikel_updateUserId;
        }

        public MenuSearchBo setNotlikel_updateUserId(String notlikel_updateUserId)
        {
            this.notlikel_updateUserId = notlikel_updateUserId;
            return this;
        }

        public String getIn_updateUserId()
        {
            return in_updateUserId;
        }

        public MenuSearchBo setIn_updateUserId(String in_updateUserId)
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
