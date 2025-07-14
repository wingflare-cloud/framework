package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.UpdatePasswdBO;
import com.wingflare.facade.module.user.bo.UserBO;
import com.wingflare.facade.module.user.bo.UserBindRoleBO;
import com.wingflare.facade.module.user.bo.UserSearchBO;
import com.wingflare.facade.module.user.dto.UserDTO;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import java.util.Map;


/**
 * 系统用户Biz
 *
 * @author naizui_ycx
 * @date Sun Mar 05 09:45:12 CST 2023
 */
@Validated
public interface UserBiz {

    /**
     * 查询系统用户列表
     */
    PageDto<UserDTO> list(@Valid UserSearchBO bo);

    /**
     * 查询系统用户详情
     */
    UserDTO get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个系统用户详情
     */
    UserDTO getOnlyOne(@Valid @NotNull UserSearchBO searchBo);

    /**
     * 删除系统用户
     */
    UserDTO delete(@Valid @NotNull IdBo bo);

    /**
     * 新增系统用户
     */
    @Validated({Default.class, Create.class})
    UserDTO create(@Valid @NotNull UserBO bo);

    /**
     * 更新系统用户
     */
    @Validated({Default.class, Update.class})
    UserDTO update(@Valid @NotNull UserBO bo);

    /**
     * 更新用户密码
     *
     * @param bo
     */
    UserDTO updatePasswd(@Valid @NotNull UpdatePasswdBO bo);

    /**
     * 通过登录名获取登录用户
     *
     * @param loginName
     * @return
     */
    UserDTO getUserByLoginName(@NotBlank String loginName);

    /**
     * 通过用户id获取用户附加属性
     *
     * @param bo
     * @return
     */
    Map<String, Object> getAttribute(@Valid @NotNull IdBo bo);

    /**
     * 用户绑定角色
     *
     * @param bo
     */
    void userBindRole(@Valid @NotNull UserBindRoleBO bo);

}