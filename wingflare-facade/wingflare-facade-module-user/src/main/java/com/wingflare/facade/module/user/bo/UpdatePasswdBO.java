package com.wingflare.facade.module.user.bo;


import com.wingflare.api.security.annotation.Decrypt;
import jakarta.validation.constraints.Min;


/**
 * @ClassName UpdatePasswdBo
 * @Author naizui_ycx
 * @Date 2023/03/03 03
 * @Description 密码更新
 */
public class UpdatePasswdBO {

    @Min(message = "user.userId.error", value = 1)
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
