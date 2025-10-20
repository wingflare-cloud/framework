package com.wingflare.module.auth.controller;


import com.wingflare.api.core.PageResult;
import com.wingflare.api.http.HttpContainer;
import com.wingflare.api.http.HttpHeaderConstants;
import com.wingflare.api.mvc.RequestMethod;
import com.wingflare.api.mvc.annotation.Controller;
import com.wingflare.api.mvc.annotation.RequestBody;
import com.wingflare.api.mvc.annotation.RequestMapping;
import com.wingflare.api.mvc.annotation.ResponseBody;
import com.wingflare.api.security.UserAuth;
import com.wingflare.api.security.annotation.BusinessSystem;
import com.wingflare.api.security.annotation.RequiresLogin;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.api.security.annotation.Secret;
import com.wingflare.facade.module.auth.biz.LoginBiz;
import com.wingflare.facade.module.auth.bo.GetLoginUsersBO;
import com.wingflare.facade.module.auth.bo.LoginBO;
import com.wingflare.facade.module.auth.bo.RefreshTokenBO;
import com.wingflare.facade.module.auth.bo.TokenIdBO;
import com.wingflare.facade.module.auth.dto.TokenDTO;
import com.wingflare.facade.module.user.biz.UserBiz;
import com.wingflare.facade.module.user.dto.UserDTO;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.standard.bo.StringIdBo;
import com.wingflare.lib.standard.utils.SecurityUtil;
import com.wingflare.module.auth.PermissionCode;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;

/**
 * @InterfaceName ErrorCode
 * @Author naizui_ycx
 * @Date 2023/03/10
 * @Description 认证中心控制器
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final LoginBiz loginBiz;

    private final UserBiz userBiz;

    private final HttpContainer httpContainer;

    public AuthController(LoginBiz loginBiz, UserBiz userBiz, HttpContainer httpContainer) {
        this.loginBiz = loginBiz;
        this.userBiz = userBiz;
        this.httpContainer = httpContainer;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @Secret
    public TokenDTO login(@Secret @RequestBody LoginBO bo) {
        bo.setUserAgent(httpContainer.getHeader(HttpHeaderConstants.REQUEST_USER_AGENT));
        bo.setIpaddr(httpContainer.getClientIp());
        return loginBiz.login(bo);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @RequiresLogin
    @ResponseBody
    public UserAuth logout() {
        if (SecurityUtil.getUser() != null) {
            return loginBiz.logout(new StringIdBo().setId(SecurityUtil.getUser().getTokenId()));
        }

        return null;
    }

    /**
     * 踢用户下线
     *
     * @param bo
     */
    @RequestMapping(value = "/kickOut", method = RequestMethod.POST)
    @RequiresPermissions(PermissionCode.AUTH_KICK_OUT)
    @ResponseBody
    @Secret
    @BusinessSystem("base")
    public UserAuth kickOut(@Secret @RequestBody TokenIdBO bo) {
        StringIdBo idBo = new StringIdBo().setId(bo.getToken());
        return loginBiz.logout(idBo);
    }

    @RequestMapping(value = "/getUserLoginInfo", method = RequestMethod.GET)
    @RequiresLogin
    @ResponseBody
    @BusinessSystem("base")
    public UserAuth getUserLoginInfo() {
        return loginBiz.getUserLoginInfo(new StringIdBo().setId(SecurityUtil.getUser().getTokenId()));
    }

    @RequestMapping(value = "/getLoginUser", method = RequestMethod.GET)
    @RequiresLogin
    @ResponseBody
    public UserDTO getLoginUser() {
        UserAuth userAuth = loginBiz.getUserLoginInfo(new StringIdBo().setId(SecurityUtil.getUser().getTokenId()));

        if (userAuth == null) {
            return null;
        }

        return userBiz.get(new IdBo().setId(BigInteger.valueOf(Long.parseLong(userAuth.getUserId()))));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresLogin
    @RequiresPermissions(PermissionCode.AUTH_TOKEN_VIEW)
    @ResponseBody
    @BusinessSystem("base")
    public PageResult<UserAuth> list(@Valid @NotNull GetLoginUsersBO bo) {
        return loginBiz.getLoginUsers(bo);
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    @RequiresLogin
    @RequiresPermissions(PermissionCode.AUTH_USER_TOKEN_VIEW)
    @ResponseBody
    @BusinessSystem("base")
    public PageResult<UserAuth> userList(@Valid @NotNull GetLoginUsersBO bo) {
        return loginBiz.getUserLoginInfos(bo);
    }

    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    @RequiresLogin
    @ResponseBody
    public TokenDTO refreshToken(@RequestBody @Valid @NotNull RefreshTokenBO bo) {
        return loginBiz.refreshToken(bo);
    }

}
