package com.wingflare.facade.module.user.biz;


import com.wingflare.api.core.PageDto;
import com.wingflare.facade.module.user.bo.UpdatePasswdBO;
import com.wingflare.facade.module.user.bo.UserBO;
import com.wingflare.facade.module.user.bo.UserBindRoleBO;
import com.wingflare.facade.module.user.bo.UserSearchBO;
import com.wingflare.facade.module.user.dto.UserDTO;
import com.wingflare.lib.standard.bo.IdBo;

import java.util.Map;


/**
 * 系统用户Biz
 *
 * @author naizui_ycx
 * @date Sun Mar 05 09:45:12 CST 2023
 */
public interface UserBiz {

    /**
     * 查询系统用户列表
     */
    PageDto<UserDTO> list(UserSearchBO bo);

    /**
     * 查询系统用户详情
     */
    UserDTO get(IdBo bo);

    /**
     * 通过条件查询单个系统用户详情
     */
    UserDTO getOnlyOne(UserSearchBO searchBo);

    /**
     * 删除系统用户
     */
    UserDTO delete(IdBo bo);

    /**
     * 新增系统用户
     */
    UserDTO create(UserBO bo);

    /**
     * 更新系统用户
     */
    UserDTO update(UserBO bo);

    /**
     * 更新用户密码
     *
     * @param bo
     */
    UserDTO updatePasswd(UpdatePasswdBO bo);

    /**
     * 通过登录名获取登录用户
     *
     * @param loginName
     * @return
     */
    UserDTO getUserByLoginName(String loginName);

    /**
     * 通过用户id获取用户附加属性
     *
     * @param bo
     * @return
     */
    Map<String, Object> getAttribute(IdBo bo);

    /**
     * 用户绑定角色
     *
     * @param bo
     */
    void userBindRole(UserBindRoleBO bo);

}