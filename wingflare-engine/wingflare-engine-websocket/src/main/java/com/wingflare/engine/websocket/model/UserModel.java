package com.wingflare.engine.websocket.model;

import com.wingflare.lib.security.model.UserAuth;

import javax.security.auth.Subject;
import java.security.Principal;

/**
 * @ClassName UserModel
 * @Author naizui_ycx
 * @Date 2023/03/03 03
 * @Description websocket认证用户
 */
public class UserModel extends UserAuth implements Principal {

    @Override
    public String getName() {
        return getUserName();
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }

}
