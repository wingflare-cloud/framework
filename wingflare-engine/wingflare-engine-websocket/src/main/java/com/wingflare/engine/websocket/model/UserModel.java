package com.wingflare.engine.websocket.model;

import com.wingflare.lib.standard.model.UserAuth;

import javax.security.auth.Subject;
import java.security.Principal;

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
