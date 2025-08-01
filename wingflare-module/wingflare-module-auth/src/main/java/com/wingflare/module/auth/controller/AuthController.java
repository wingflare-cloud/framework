package com.wingflare.module.auth.controller;


import com.wingflare.adapter.spring.server.web.utils.ServletUtil;
import com.wingflare.facade.module.auth.biz.LoginBiz;
import com.wingflare.facade.module.auth.bo.GetLoginUsersBO;
import com.wingflare.facade.module.auth.bo.LoginBO;
import com.wingflare.facade.module.auth.bo.RefreshTokenBO;
import com.wingflare.facade.module.auth.bo.TokenIdBO;
import com.wingflare.facade.module.auth.dto.TokenDTO;
import com.wingflare.facade.module.user.biz.UserBiz;
import com.wingflare.facade.module.user.dto.UserDTO;
import com.wingflare.lib.core.constants.HttpHeader;
import com.wingflare.lib.security.annotation.BusinessSystem;
import com.wingflare.lib.security.annotation.RequiresLogin;
import com.wingflare.lib.security.annotation.RequiresPermissions;
import com.wingflare.lib.standard.PageResult;
import com.wingflare.lib.standard.SettingUtil;
import com.wingflare.lib.standard.annotation.security.Secret;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.standard.bo.StringIdBo;
import com.wingflare.lib.standard.model.UserAuth;
import com.wingflare.lib.standard.utils.SecurityUtil;
import com.wingflare.module.auth.PermissionCode;
import com.wingflare.module.auth.SettingCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * @InterfaceName ErrorCode
 * @Author naizui_ycx
 * @Date 2023/03/10
 * @Description 认证中心控制器
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private LoginBiz loginBiz;

    @Resource
    private UserBiz userBiz;

    @Resource
    private SettingUtil settingUtil;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @Secret
    public TokenDTO login(@Secret @RequestBody LoginBO bo) {
        bo.setUserAgent(ServletUtil.getHeader(HttpHeader.REQUEST_USER_AGENT));
        bo.setIpaddr(ServletUtil.getClientIpAddr());

        if (bo.getExpireTime() == null) {
            bo.setExpireTime(settingUtil.get(SettingCode.LOGIN_EXPIRE_TIME,(long) (24 * 60 * 60), Long.class));
        }

        return loginBiz.login(bo);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @RequiresLogin
    @ResponseBody
    public UserAuth logout() {
        return loginBiz.logout(new StringIdBo().setId(SecurityUtil.getUser().getTokenId()));
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
        return userBiz.get(new IdBo().setId(userAuth.getUserId()));
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
