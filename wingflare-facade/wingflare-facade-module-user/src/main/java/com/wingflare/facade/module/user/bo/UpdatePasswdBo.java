package com.wingflare.facade.module.user.bo;

import com.wingflare.lib.standard.annotation.security.Decrypt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * @ClassName UpdatePasswdBo
 * @Author naizui_ycx
 * @Date 2023/03/03 03
 * @Description 密码更新
 */
public class UpdatePasswdBo {

    @NotBlank(message = "user.userId.notBlank")
    @Pattern(regexp = "^$|^[0-9a-zA-Z]{1,32}$", message = "user.userId.formatError")
    private String userId;

    @Decrypt(type = "RSA")
    private String passwd;

    @Decrypt(type = "RSA")
    private String oldPasswd;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getOldPasswd() {
        return oldPasswd;
    }

    public void setOldPasswd(String oldPasswd) {
        this.oldPasswd = oldPasswd;
    }

}
