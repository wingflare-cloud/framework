package com.wingflare.facade.module.auth.biz;


import com.wingflare.api.core.PageResult;
import com.wingflare.api.security.UserAuth;
import com.wingflare.api.validation.annotation.Validated;
import com.wingflare.facade.module.auth.bo.GetLoginUsersBO;
import com.wingflare.facade.module.auth.bo.LoginBO;
import com.wingflare.facade.module.auth.bo.RefreshTokenBO;
import com.wingflare.facade.module.auth.dto.TokenDTO;
import com.wingflare.lib.standard.bo.StringIdBo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * @ClassName LoginBiz
 * @Author naizui_ycx
 * @Date 2023/03/10
 * @Description 登陆业务
 */
@Validated
public interface LoginBiz {

    /**
     * 登入
     *
     * @param bo
     * @return
     */
    TokenDTO login(@Valid @NotNull LoginBO bo);

    /**
     * 登出
     *
     * @param bo token id bo
     */
    UserAuth logout(@Valid @NotNull StringIdBo bo);

    /**
     * 获取用户登录信息
     *
     * @param bo token id bo
     *
     * @return
     */
    UserAuth getUserLoginInfo(@Valid @NotNull StringIdBo bo);

    /**
     * 刷新token
     *
     * @param bo
     *
     * @return
     */
    TokenDTO refreshToken(@Valid @NotNull RefreshTokenBO bo);

    /**
     * 获取登录用户信息列表
     *
     * @param bo
     * @return
     */
    public PageResult<UserAuth> getLoginUsers(@Valid @NotNull GetLoginUsersBO bo);

    /**
     * 获取指定用户登录用户信息列表
     *
     * @param bo
     * @return
     */
    public PageResult<UserAuth> getUserLoginInfos(@Valid @NotNull GetLoginUsersBO bo);

}