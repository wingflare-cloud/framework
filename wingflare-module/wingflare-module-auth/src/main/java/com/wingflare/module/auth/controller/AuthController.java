package com.wingflare.module.auth.controller;


import com.wingflare.adapter.spring.server.web.utils.ServletUtil;
import com.wingflare.facade.module.auth.biz.LoginBiz;
import com.wingflare.facade.module.auth.bo.LoginBo;
import com.wingflare.facade.module.auth.bo.RefreshTokenBo;
import com.wingflare.facade.module.auth.dto.TokenDto;
import com.wingflare.lib.core.constants.HttpHeader;
import com.wingflare.lib.security.annotation.RequiresLogin;
import com.wingflare.lib.security.annotation.RequiresPermissions;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.standard.model.UserAuth;
import com.wingflare.lib.standard.utils.SecurityUtil;
import com.wingflare.module.auth.PermissionCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public TokenDto login(@RequestBody LoginBo bo) {
        bo.setUserAgent(ServletUtil.getHeader(HttpHeader.REQUEST_USER_AGENT));
        bo.setIpaddr(ServletUtil.getClientIpAddr());
        bo.setSystemCode(SecurityUtil.getBusinessSystem());
        return loginBiz.login(bo);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @RequiresLogin
    @ResponseBody
    public UserAuth logout() {
        return loginBiz.logout(new IdBo().setId(SecurityUtil.getTokenId()));
    }

    /**
     * 踢用户下线
     *
     * @param bo
     */
    @RequestMapping(value = "/kickOut", method = RequestMethod.POST)
    @RequiresLogin
    @RequiresPermissions(PermissionCode.AUTH_KICK_OUT)
    @ResponseBody
    public UserAuth kickOut(IdBo bo) {
        return loginBiz.logout(bo);
    }

    @RequestMapping(value = "/getLoginUser", method = RequestMethod.GET)
    @RequiresLogin
    @ResponseBody
    public UserAuth getLoginUser() {
        return loginBiz.getLoginUser(new IdBo().setId(SecurityUtil.getTokenId()));
    }

    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    @RequiresLogin
    @ResponseBody
    public TokenDto refreshToken(RefreshTokenBo bo) {
        bo.setUserId(SecurityUtil.getUserId());
        return loginBiz.refreshToken(bo);
    }

}
