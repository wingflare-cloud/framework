package com.wingflare.facade.module.auth.biz;


import com.wingflare.facade.module.auth.bo.GetLoginUsersBO;
import com.wingflare.facade.module.auth.bo.LoginBO;
import com.wingflare.facade.module.auth.bo.RefreshTokenBO;
import com.wingflare.facade.module.auth.dto.TokenDTO;
import com.wingflare.lib.standard.PageResult;
import com.wingflare.lib.standard.bo.StringIdBo;
import com.wingflare.lib.standard.model.UserAuth;



/**
 * @ClassName LoginBiz
 * @Author naizui_ycx
 * @Date 2023/03/10
 * @Description 登陆业务
 */
public interface LoginBiz {

    /**
     * 登入
     *
     * @param bo
     * @return
     */
    TokenDTO login(LoginBO bo);

    /**
     * 登出
     *
     * @param bo token id bo
     */
    UserAuth logout(StringIdBo bo);

    /**
     * 获取用户登录信息
     *
     * @param bo token id bo
     *
     * @return
     */
    UserAuth getUserLoginInfo(StringIdBo bo);

    /**
     * 刷新token
     *
     * @param bo
     *
     * @return
     */
    TokenDTO refreshToken(RefreshTokenBO bo);

    /**
     * 获取登录用户信息列表
     *
     * @param bo
     * @return
     */
    PageResult<UserAuth> getLoginUsers(GetLoginUsersBO bo);

    /**
     * 获取指定用户登录用户信息列表
     *
     * @param bo
     * @return
     */
    PageResult<UserAuth> getUserLoginInfos(GetLoginUsersBO bo);

}
