package com.wingflare.facade.module.base.bo;


import com.wingflare.lib.standard.BaseSearchBo;
import java.util.Date;

/**
 * <p>
 * 系统菜单表 查询对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-06
 */
public class MenuSearchBo extends BaseSearchBo
{
        
    private String eq_menuId;

    private String neq_menuId;

    private String like_menuId;

    private String liker_menuId;

    private String likel_menuId;

    private String notlike_menuId;

    private String notliker_menuId;

    private String notlikel_menuId;

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
        
    private Integer eq_hide;

    private Integer neq_hide;

    private Integer gt_hide;

    private Integer lt_hide;

    private Integer egt_hide;

    private Integer elt_hide;

    private String between_hide;

    private String notbetween_hide;

    private String in_hide;

    private String notin_hide;

    private Integer eq_constant;

    private Integer neq_constant;
        
    private Integer eq_multiTab;

    private Integer neq_multiTab;

    private Integer gt_multiTab;

    private Integer lt_multiTab;

    private Integer egt_multiTab;

    private Integer elt_multiTab;

    private String between_multiTab;

    private String notbetween_multiTab;

    private String in_multiTab;

    private String notin_multiTab;
        
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
        
    private String eq_langKey;

    private String neq_langKey;

    private String like_langKey;

    private String liker_langKey;

    private String likel_langKey;

    private String notlike_langKey;

    private String notliker_langKey;

    private String notlikel_langKey;

    private String in_langKey;

    private String notin_langKey;
        
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
        
    private String eq_iconType;

    private String neq_iconType;

    private String like_iconType;

    private String liker_iconType;

    private String likel_iconType;

    private String notlike_iconType;

    private String notliker_iconType;

    private String notlikel_iconType;

    private String in_iconType;

    private String notin_iconType;
        
    private String eq_routeName;

    private String neq_routeName;

    private String like_routeName;

    private String liker_routeName;

    private String likel_routeName;

    private String notlike_routeName;

    private String notliker_routeName;

    private String notlikel_routeName;

    private String in_routeName;

    private String notin_routeName;
        
    private String eq_routePath;

    private String neq_routePath;

    private String like_routePath;

    private String liker_routePath;

    private String likel_routePath;

    private String notlike_routePath;

    private String notliker_routePath;

    private String notlikel_routePath;

    private String in_routePath;

    private String notin_routePath;
        
    private String eq_component;

    private String neq_component;

    private String like_component;

    private String liker_component;

    private String likel_component;

    private String notlike_component;

    private String notliker_component;

    private String notlikel_component;

    private String in_component;

    private String notin_component;
        
    private String eq_query;

    private String neq_query;

    private String like_query;

    private String liker_query;

    private String likel_query;

    private String notlike_query;

    private String notliker_query;

    private String notlikel_query;

    private String in_query;

    private String notin_query;
        
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

    public String getLike_menuId()
    {
        return like_menuId;
    }

    public MenuSearchBo setLike_menuId(String like_menuId)
    {
        this.like_menuId = like_menuId;
        return this;
    }

    public String getLiker_menuId()
    {
        return liker_menuId;
     }

    public MenuSearchBo setLiker_menuId(String liker_menuId)
    {
        this.liker_menuId = liker_menuId;
        return this;
    }

    public String getLikel_menuId()
    {
        return likel_menuId;
    }

    public MenuSearchBo setLikel_menuId(String likel_menuId)
    {
        this.likel_menuId = likel_menuId;
        return this;
    }

    public String getNotlike_menuId()
    {
        return notlike_menuId;
    }

    public MenuSearchBo setNotlike_menuId(String notlike_menuId)
    {
        this.notlike_menuId = notlike_menuId;
        return this;
    }

    public String getNotliker_menuId()
    {
        return notliker_menuId;
    }

    public MenuSearchBo setNotliker_menuId(String notliker_menuId)
    {
        this.notliker_menuId = notliker_menuId;
        return this;
    }

    public String getNotlikel_menuId()
    {
        return notlikel_menuId;
    }

    public MenuSearchBo setNotlikel_menuId(String notlikel_menuId)
    {
        this.notlikel_menuId = notlikel_menuId;
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

    public MenuSearchBo setNotin_menuId(String notin_menuId)
    {
        this.notin_menuId = notin_menuId;
        return this;
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

    public MenuSearchBo setNotin_parentMenuId(String notin_parentMenuId)
    {
        this.notin_parentMenuId = notin_parentMenuId;
        return this;
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

    public MenuSearchBo setNotin_state(String notin_state)
    {
        this.notin_state = notin_state;
        return this;
    }

    public Integer getEq_hide()
    {
        return eq_hide;
    }

    public MenuSearchBo setEq_hide(Integer eq_hide)
    {
        this.eq_hide = eq_hide;
        return this;
    }

    public Integer getNeq_hide()
    {
        return neq_hide;
    }

    public MenuSearchBo setNeq_hide(Integer neq_hide)
    {
        this.neq_hide = neq_hide;
        return this;
    }

    public Integer getGt_hide()
    {
         return gt_hide;
    }

    public MenuSearchBo setGt_hide(Integer gt_hide)
    {
        this.gt_hide = gt_hide;
        return this;
    }

    public Integer getLt_hide()
    {
        return lt_hide;
    }

    public MenuSearchBo setLt_hide(Integer lt_hide)
    {
        this.lt_hide = lt_hide;
        return this;
    }

    public Integer getEgt_hide()
    {
        return egt_hide;
    }

    public MenuSearchBo setEgt_hide(Integer egt_hide)
    {
        this.egt_hide = egt_hide;
        return this;
    }

    public Integer getElt_hide()
    {
        return elt_hide;
    }

    public MenuSearchBo setElt_hide(Integer elt_hide)
    {
        this.elt_hide = elt_hide;
        return this;
    }

    public String getBetween_hide()
    {
        return between_hide;
    }

    public MenuSearchBo setBetween_hide(String between_hide)
    {
        this.between_hide = between_hide;
        return this;
    }

    public String getNotbetween_hide()
    {
        return notbetween_hide;
    }

    public MenuSearchBo setNotbetween_hide(String notbetween_hide)
    {
        this.notbetween_hide = notbetween_hide;
        return this;
    }

    public String getIn_hide()
    {
        return in_hide;
    }

    public MenuSearchBo setIn_hide(String in_hide)
    {
        this.in_hide = in_hide;
        return this;
    }

    public String getNotin_hide()
    {
        return notin_hide;
    }

    public MenuSearchBo setNotin_hide(String notin_hide)
    {
        this.notin_hide = notin_hide;
        return this;
    }

    public Integer getNeq_constant() {
        return neq_constant;
    }

    public MenuSearchBo setNeq_constant(Integer neq_constant) {
        this.neq_constant = neq_constant;
        return this;
    }

    public Integer getEq_constant() {
        return eq_constant;
    }

    public MenuSearchBo setEq_constant(Integer eq_constant) {
        this.eq_constant = eq_constant;
        return this;
    }

    public Integer getEq_multiTab()
    {
        return eq_multiTab;
    }

    public MenuSearchBo setEq_multiTab(Integer eq_multiTab)
    {
        this.eq_multiTab = eq_multiTab;
        return this;
    }

    public Integer getNeq_multiTab()
    {
        return neq_multiTab;
    }

    public MenuSearchBo setNeq_multiTab(Integer neq_multiTab)
    {
        this.neq_multiTab = neq_multiTab;
        return this;
    }

    public Integer getGt_multiTab()
    {
         return gt_multiTab;
    }

    public MenuSearchBo setGt_multiTab(Integer gt_multiTab)
    {
        this.gt_multiTab = gt_multiTab;
        return this;
    }

    public Integer getLt_multiTab()
    {
        return lt_multiTab;
    }

    public MenuSearchBo setLt_multiTab(Integer lt_multiTab)
    {
        this.lt_multiTab = lt_multiTab;
        return this;
    }

    public Integer getEgt_multiTab()
    {
        return egt_multiTab;
    }

    public MenuSearchBo setEgt_multiTab(Integer egt_multiTab)
    {
        this.egt_multiTab = egt_multiTab;
        return this;
    }

    public Integer getElt_multiTab()
    {
        return elt_multiTab;
    }

    public MenuSearchBo setElt_multiTab(Integer elt_multiTab)
    {
        this.elt_multiTab = elt_multiTab;
        return this;
    }

    public String getBetween_multiTab()
    {
        return between_multiTab;
    }

    public MenuSearchBo setBetween_multiTab(String between_multiTab)
    {
        this.between_multiTab = between_multiTab;
        return this;
    }

    public String getNotbetween_multiTab()
    {
        return notbetween_multiTab;
    }

    public MenuSearchBo setNotbetween_multiTab(String notbetween_multiTab)
    {
        this.notbetween_multiTab = notbetween_multiTab;
        return this;
    }

    public String getIn_multiTab()
    {
        return in_multiTab;
    }

    public MenuSearchBo setIn_multiTab(String in_multiTab)
    {
        this.in_multiTab = in_multiTab;
        return this;
    }

    public String getNotin_multiTab()
    {
        return notin_multiTab;
    }

    public MenuSearchBo setNotin_multiTab(String notin_multiTab)
    {
        this.notin_multiTab = notin_multiTab;
        return this;
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

    public MenuSearchBo setNotin_menuType(String notin_menuType)
    {
        this.notin_menuType = notin_menuType;
        return this;
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

    public MenuSearchBo setNotin_systemCode(String notin_systemCode)
    {
        this.notin_systemCode = notin_systemCode;
        return this;
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

    public MenuSearchBo setNotin_permissionCode(String notin_permissionCode)
    {
        this.notin_permissionCode = notin_permissionCode;
        return this;
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

    public MenuSearchBo setNotin_menuName(String notin_menuName)
    {
        this.notin_menuName = notin_menuName;
        return this;
    }

    public String getEq_langKey()
    {
        return eq_langKey;
    }

    public MenuSearchBo setEq_langKey(String eq_langKey)
    {
        this.eq_langKey = eq_langKey;
        return this;
    }

    public String getNeq_langKey()
    {
        return neq_langKey;
    }

    public MenuSearchBo setNeq_langKey(String neq_langKey)
    {
        this.neq_langKey = neq_langKey;
        return this;
    }

    public String getLike_langKey()
    {
        return like_langKey;
    }

    public MenuSearchBo setLike_langKey(String like_langKey)
    {
        this.like_langKey = like_langKey;
        return this;
    }

    public String getLiker_langKey()
    {
        return liker_langKey;
     }

    public MenuSearchBo setLiker_langKey(String liker_langKey)
    {
        this.liker_langKey = liker_langKey;
        return this;
    }

    public String getLikel_langKey()
    {
        return likel_langKey;
    }

    public MenuSearchBo setLikel_langKey(String likel_langKey)
    {
        this.likel_langKey = likel_langKey;
        return this;
    }

    public String getNotlike_langKey()
    {
        return notlike_langKey;
    }

    public MenuSearchBo setNotlike_langKey(String notlike_langKey)
    {
        this.notlike_langKey = notlike_langKey;
        return this;
    }

    public String getNotliker_langKey()
    {
        return notliker_langKey;
    }

    public MenuSearchBo setNotliker_langKey(String notliker_langKey)
    {
        this.notliker_langKey = notliker_langKey;
        return this;
    }

    public String getNotlikel_langKey()
    {
        return notlikel_langKey;
    }

    public MenuSearchBo setNotlikel_langKey(String notlikel_langKey)
    {
        this.notlikel_langKey = notlikel_langKey;
        return this;
    }

    public String getIn_langKey()
    {
        return in_langKey;
    }

    public MenuSearchBo setIn_langKey(String in_langKey)
    {
        this.in_langKey = in_langKey;
        return this;
    }

    public String getNotin_langKey()
    {
        return notin_langKey;
    }

    public MenuSearchBo setNotin_langKey(String notin_langKey)
    {
        this.notin_langKey = notin_langKey;
        return this;
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

    public MenuSearchBo setNotin_menuIcon(String notin_menuIcon)
    {
        this.notin_menuIcon = notin_menuIcon;
        return this;
    }

    public String getEq_iconType()
    {
        return eq_iconType;
    }

    public MenuSearchBo setEq_iconType(String eq_iconType)
    {
        this.eq_iconType = eq_iconType;
        return this;
    }

    public String getNeq_iconType()
    {
        return neq_iconType;
    }

    public MenuSearchBo setNeq_iconType(String neq_iconType)
    {
        this.neq_iconType = neq_iconType;
        return this;
    }

    public String getLike_iconType()
    {
        return like_iconType;
    }

    public MenuSearchBo setLike_iconType(String like_iconType)
    {
        this.like_iconType = like_iconType;
        return this;
    }

    public String getLiker_iconType()
    {
        return liker_iconType;
     }

    public MenuSearchBo setLiker_iconType(String liker_iconType)
    {
        this.liker_iconType = liker_iconType;
        return this;
    }

    public String getLikel_iconType()
    {
        return likel_iconType;
    }

    public MenuSearchBo setLikel_iconType(String likel_iconType)
    {
        this.likel_iconType = likel_iconType;
        return this;
    }

    public String getNotlike_iconType()
    {
        return notlike_iconType;
    }

    public MenuSearchBo setNotlike_iconType(String notlike_iconType)
    {
        this.notlike_iconType = notlike_iconType;
        return this;
    }

    public String getNotliker_iconType()
    {
        return notliker_iconType;
    }

    public MenuSearchBo setNotliker_iconType(String notliker_iconType)
    {
        this.notliker_iconType = notliker_iconType;
        return this;
    }

    public String getNotlikel_iconType()
    {
        return notlikel_iconType;
    }

    public MenuSearchBo setNotlikel_iconType(String notlikel_iconType)
    {
        this.notlikel_iconType = notlikel_iconType;
        return this;
    }

    public String getIn_iconType()
    {
        return in_iconType;
    }

    public MenuSearchBo setIn_iconType(String in_iconType)
    {
        this.in_iconType = in_iconType;
        return this;
    }

    public String getNotin_iconType()
    {
        return notin_iconType;
    }

    public MenuSearchBo setNotin_iconType(String notin_iconType)
    {
        this.notin_iconType = notin_iconType;
        return this;
    }

    public String getEq_routeName()
    {
        return eq_routeName;
    }

    public MenuSearchBo setEq_routeName(String eq_routeName)
    {
        this.eq_routeName = eq_routeName;
        return this;
    }

    public String getNeq_routeName()
    {
        return neq_routeName;
    }

    public MenuSearchBo setNeq_routeName(String neq_routeName)
    {
        this.neq_routeName = neq_routeName;
        return this;
    }

    public String getLike_routeName()
    {
        return like_routeName;
    }

    public MenuSearchBo setLike_routeName(String like_routeName)
    {
        this.like_routeName = like_routeName;
        return this;
    }

    public String getLiker_routeName()
    {
        return liker_routeName;
     }

    public MenuSearchBo setLiker_routeName(String liker_routeName)
    {
        this.liker_routeName = liker_routeName;
        return this;
    }

    public String getLikel_routeName()
    {
        return likel_routeName;
    }

    public MenuSearchBo setLikel_routeName(String likel_routeName)
    {
        this.likel_routeName = likel_routeName;
        return this;
    }

    public String getNotlike_routeName()
    {
        return notlike_routeName;
    }

    public MenuSearchBo setNotlike_routeName(String notlike_routeName)
    {
        this.notlike_routeName = notlike_routeName;
        return this;
    }

    public String getNotliker_routeName()
    {
        return notliker_routeName;
    }

    public MenuSearchBo setNotliker_routeName(String notliker_routeName)
    {
        this.notliker_routeName = notliker_routeName;
        return this;
    }

    public String getNotlikel_routeName()
    {
        return notlikel_routeName;
    }

    public MenuSearchBo setNotlikel_routeName(String notlikel_routeName)
    {
        this.notlikel_routeName = notlikel_routeName;
        return this;
    }

    public String getIn_routeName()
    {
        return in_routeName;
    }

    public MenuSearchBo setIn_routeName(String in_routeName)
    {
        this.in_routeName = in_routeName;
        return this;
    }

    public String getNotin_routeName()
    {
        return notin_routeName;
    }

    public MenuSearchBo setNotin_routeName(String notin_routeName)
    {
        this.notin_routeName = notin_routeName;
        return this;
    }

    public String getEq_routePath()
    {
        return eq_routePath;
    }

    public MenuSearchBo setEq_routePath(String eq_routePath)
    {
        this.eq_routePath = eq_routePath;
        return this;
    }

    public String getNeq_routePath()
    {
        return neq_routePath;
    }

    public MenuSearchBo setNeq_routePath(String neq_routePath)
    {
        this.neq_routePath = neq_routePath;
        return this;
    }

    public String getLike_routePath()
    {
        return like_routePath;
    }

    public MenuSearchBo setLike_routePath(String like_routePath)
    {
        this.like_routePath = like_routePath;
        return this;
    }

    public String getLiker_routePath()
    {
        return liker_routePath;
     }

    public MenuSearchBo setLiker_routePath(String liker_routePath)
    {
        this.liker_routePath = liker_routePath;
        return this;
    }

    public String getLikel_routePath()
    {
        return likel_routePath;
    }

    public MenuSearchBo setLikel_routePath(String likel_routePath)
    {
        this.likel_routePath = likel_routePath;
        return this;
    }

    public String getNotlike_routePath()
    {
        return notlike_routePath;
    }

    public MenuSearchBo setNotlike_routePath(String notlike_routePath)
    {
        this.notlike_routePath = notlike_routePath;
        return this;
    }

    public String getNotliker_routePath()
    {
        return notliker_routePath;
    }

    public MenuSearchBo setNotliker_routePath(String notliker_routePath)
    {
        this.notliker_routePath = notliker_routePath;
        return this;
    }

    public String getNotlikel_routePath()
    {
        return notlikel_routePath;
    }

    public MenuSearchBo setNotlikel_routePath(String notlikel_routePath)
    {
        this.notlikel_routePath = notlikel_routePath;
        return this;
    }

    public String getIn_routePath()
    {
        return in_routePath;
    }

    public MenuSearchBo setIn_routePath(String in_routePath)
    {
        this.in_routePath = in_routePath;
        return this;
    }

    public String getNotin_routePath()
    {
        return notin_routePath;
    }

    public MenuSearchBo setNotin_routePath(String notin_routePath)
    {
        this.notin_routePath = notin_routePath;
        return this;
    }

    public String getEq_component()
    {
        return eq_component;
    }

    public MenuSearchBo setEq_component(String eq_component)
    {
        this.eq_component = eq_component;
        return this;
    }

    public String getNeq_component()
    {
        return neq_component;
    }

    public MenuSearchBo setNeq_component(String neq_component)
    {
        this.neq_component = neq_component;
        return this;
    }

    public String getLike_component()
    {
        return like_component;
    }

    public MenuSearchBo setLike_component(String like_component)
    {
        this.like_component = like_component;
        return this;
    }

    public String getLiker_component()
    {
        return liker_component;
     }

    public MenuSearchBo setLiker_component(String liker_component)
    {
        this.liker_component = liker_component;
        return this;
    }

    public String getLikel_component()
    {
        return likel_component;
    }

    public MenuSearchBo setLikel_component(String likel_component)
    {
        this.likel_component = likel_component;
        return this;
    }

    public String getNotlike_component()
    {
        return notlike_component;
    }

    public MenuSearchBo setNotlike_component(String notlike_component)
    {
        this.notlike_component = notlike_component;
        return this;
    }

    public String getNotliker_component()
    {
        return notliker_component;
    }

    public MenuSearchBo setNotliker_component(String notliker_component)
    {
        this.notliker_component = notliker_component;
        return this;
    }

    public String getNotlikel_component()
    {
        return notlikel_component;
    }

    public MenuSearchBo setNotlikel_component(String notlikel_component)
    {
        this.notlikel_component = notlikel_component;
        return this;
    }

    public String getIn_component()
    {
        return in_component;
    }

    public MenuSearchBo setIn_component(String in_component)
    {
        this.in_component = in_component;
        return this;
    }

    public String getNotin_component()
    {
        return notin_component;
    }

    public MenuSearchBo setNotin_component(String notin_component)
    {
        this.notin_component = notin_component;
        return this;
    }

    public String getEq_query()
    {
        return eq_query;
    }

    public MenuSearchBo setEq_query(String eq_query)
    {
        this.eq_query = eq_query;
        return this;
    }

    public String getNeq_query()
    {
        return neq_query;
    }

    public MenuSearchBo setNeq_query(String neq_query)
    {
        this.neq_query = neq_query;
        return this;
    }

    public String getLike_query()
    {
        return like_query;
    }

    public MenuSearchBo setLike_query(String like_query)
    {
        this.like_query = like_query;
        return this;
    }

    public String getLiker_query()
    {
        return liker_query;
     }

    public MenuSearchBo setLiker_query(String liker_query)
    {
        this.liker_query = liker_query;
        return this;
    }

    public String getLikel_query()
    {
        return likel_query;
    }

    public MenuSearchBo setLikel_query(String likel_query)
    {
        this.likel_query = likel_query;
        return this;
    }

    public String getNotlike_query()
    {
        return notlike_query;
    }

    public MenuSearchBo setNotlike_query(String notlike_query)
    {
        this.notlike_query = notlike_query;
        return this;
    }

    public String getNotliker_query()
    {
        return notliker_query;
    }

    public MenuSearchBo setNotliker_query(String notliker_query)
    {
        this.notliker_query = notliker_query;
        return this;
    }

    public String getNotlikel_query()
    {
        return notlikel_query;
    }

    public MenuSearchBo setNotlikel_query(String notlikel_query)
    {
        this.notlikel_query = notlikel_query;
        return this;
    }

    public String getIn_query()
    {
        return in_query;
    }

    public MenuSearchBo setIn_query(String in_query)
    {
        this.in_query = in_query;
        return this;
    }

    public String getNotin_query()
    {
        return notin_query;
    }

    public MenuSearchBo setNotin_query(String notin_query)
    {
        this.notin_query = notin_query;
        return this;
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

    public MenuSearchBo setNotin_href(String notin_href)
    {
        this.notin_href = notin_href;
        return this;
    }

    public Integer getEq_sort()
    {
        return eq_sort;
    }

    public MenuSearchBo setEq_sort(Integer eq_sort)
    {
        this.eq_sort = eq_sort;
        return this;
    }

    public Integer getNeq_sort()
    {
        return neq_sort;
    }

    public MenuSearchBo setNeq_sort(Integer neq_sort)
    {
        this.neq_sort = neq_sort;
        return this;
    }

    public Integer getGt_sort()
    {
         return gt_sort;
    }

    public MenuSearchBo setGt_sort(Integer gt_sort)
    {
        this.gt_sort = gt_sort;
        return this;
    }

    public Integer getLt_sort()
    {
        return lt_sort;
    }

    public MenuSearchBo setLt_sort(Integer lt_sort)
    {
        this.lt_sort = lt_sort;
        return this;
    }

    public Integer getEgt_sort()
    {
        return egt_sort;
    }

    public MenuSearchBo setEgt_sort(Integer egt_sort)
    {
        this.egt_sort = egt_sort;
        return this;
    }

    public Integer getElt_sort()
    {
        return elt_sort;
    }

    public MenuSearchBo setElt_sort(Integer elt_sort)
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

    public MenuSearchBo setNotin_sort(String notin_sort)
    {
        this.notin_sort = notin_sort;
        return this;
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

    public MenuSearchBo setNotin_createdTime(String notin_createdTime)
    {
        this.notin_createdTime = notin_createdTime;
        return this;
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

    public MenuSearchBo setNotin_updatedTime(String notin_updatedTime)
    {
        this.notin_updatedTime = notin_updatedTime;
        return this;
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

    public MenuSearchBo setNotin_createUser(String notin_createUser)
    {
        this.notin_createUser = notin_createUser;
        return this;
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

    public MenuSearchBo setNotin_createUserId(String notin_createUserId)
    {
        this.notin_createUserId = notin_createUserId;
        return this;
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

    public MenuSearchBo setNotin_updateUser(String notin_updateUser)
    {
        this.notin_updateUser = notin_updateUser;
        return this;
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

    public MenuSearchBo setNotin_updateUserId(String notin_updateUserId)
    {
        this.notin_updateUserId = notin_updateUserId;
        return this;
    }

}