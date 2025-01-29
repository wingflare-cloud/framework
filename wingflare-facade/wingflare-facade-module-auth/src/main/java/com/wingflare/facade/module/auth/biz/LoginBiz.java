package com.wingflare.facade.module.auth.biz;


import com.wingflare.facade.module.auth.bo.GetLoginUsersBo;
import com.wingflare.facade.module.auth.bo.LoginBo;
import com.wingflare.facade.module.auth.bo.RefreshTokenBo;
import com.wingflare.facade.module.auth.dto.TokenDto;
import com.wingflare.lib.standard.CacheService;
import com.wingflare.lib.standard.PageResult;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.standard.model.UserAuth;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
    TokenDto login(@Valid @NotNull LoginBo bo);

    /**
     * 登出
     *
     * @param bo token id bo
     */
    UserAuth logout(@Valid @NotNull IdBo bo);

    /**
     * 获取用户登录信息
     *
     * @param bo token id bo
     *
     * @return
     */
    UserAuth getUserLoginInfo(@Valid @NotNull IdBo bo);

    /**
     * 刷新token
     *
     * @param bo
     *
     * @return
     */
    TokenDto refreshToken(@Valid @NotNull RefreshTokenBo bo);

    /**
     * 获取登录用户信息列表
     *
     * @param bo
     * @return
     */
    public PageResult<UserAuth> getLoginUsers(@Valid @NotNull GetLoginUsersBo bo);

    /**
     * 获取指定用户登录用户信息列表
     *
     * @param bo
     * @return
     */
    public PageResult<UserAuth> getUserLoginInfos(@Valid @NotNull GetLoginUsersBo bo);

}
