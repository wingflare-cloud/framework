package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.UpdatePasswdBo;
import com.wingflare.facade.module.user.bo.UserBindRoleBo;
import com.wingflare.facade.module.user.bo.UserBo;
import com.wingflare.facade.module.user.bo.UserSearchBo;
import com.wingflare.facade.module.user.dto.UserDto;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
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
    PageDto<UserDto> list(@Valid UserSearchBo bo);

    /**
     * 查询系统用户详情
     */
    UserDto get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个系统用户详情
     */
    UserDto getOnlyOne(@Valid @NotNull UserSearchBo searchBo);

    /**
     * 删除系统用户
     */
    UserDto delete(@Valid @NotNull IdBo bo);

    /**
     * 新增系统用户
     */
    @Validated({Default.class, Create.class})
    UserDto create(@Valid @NotNull UserBo bo);

    /**
     * 更新系统用户
     */
    @Validated({Default.class, Update.class})
    UserDto update(@Valid @NotNull UserBo bo);

    /**
     * 更新用户密码
     *
     * @param bo
     */
    UserDto updatePasswd(@Valid @NotNull UpdatePasswdBo bo);

    /**
     * 通过登录名获取登录用户
     *
     * @param loginName
     * @return
     */
    UserDto getUserByLoginName(@NotBlank String loginName);

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
    void userBindRole(@Valid @NotNull UserBindRoleBo bo);

}