package com.wingflare.facade.module.auth.bo;


import com.wingflare.api.security.annotation.Decrypt;

import java.math.BigInteger;

/**
 * @ClassName LoginBo
 * @Author naizui_ycx
 * @Date 2023/03/12
 * @Description 登录bo
 */
public class LoginBO {

    private String loginName;

    @Decrypt(type = "RSA")
    private String passwd;

    private BigInteger orgId;

    private BigInteger identityId;

    private String userAgent;

    private String ipaddr;

    private String clientType;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public BigInteger getOrgId() {
        return orgId;
    }

    public void setOrgId(BigInteger orgId) {
        this.orgId = orgId;
    }

    public BigInteger getIdentityId() {
        return identityId;
    }

    public void setIdentityId(BigInteger identityId) {
        this.identityId = identityId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
}
