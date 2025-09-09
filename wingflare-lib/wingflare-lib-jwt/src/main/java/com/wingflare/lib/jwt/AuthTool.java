package com.wingflare.lib.jwt;


import com.wingflare.api.core.Ctx;
import com.wingflare.api.security.AuthResponseDTO;
import com.wingflare.api.security.UserAuthServer;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.jwt.utils.JwtUtil;
import io.jsonwebtoken.Claims;

import java.math.BigInteger;
import java.util.Date;

/**
 * 认证工具类
 */
public class AuthTool {

    private final UserAuthServer userAuthServer;

    private final JwtUtil jwtUtil;

    public AuthTool(UserAuthServer userAuthServer, JwtUtil jwtUtil) {
        this.userAuthServer = userAuthServer;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponseDTO checkLogin(String token, String headerBusinessSystem) {
        Date now = new Date();
        boolean hasToken = StringUtil.isNotEmpty(token);
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();

        if (hasToken) {
            Claims claims = jwtUtil.parseToken(token);

            if (claims == null) {
                return authResponseDTO.setError(ErrorCode.TOKEN_EXPIRATION_OR_ERROR);
            }

            String tokenId = "";
            String businessSystem = "";

            if (claims.containsKey(Ctx.HEADER_KEY_TOKEN_ID)) {
                tokenId = claims.get(Ctx.HEADER_KEY_TOKEN_ID, String.class);
            }

            if (claims.containsKey(Ctx.HEADER_KEY_BUSINESS_SYSTEM)) {
                businessSystem = claims.get(Ctx.HEADER_KEY_BUSINESS_SYSTEM, String.class);
            }

            if (StringUtil.isBlank(tokenId) || StringUtil.isBlank(businessSystem)) {
                return authResponseDTO.setError(ErrorCode.TOKEN_EXPIRATION_OR_ERROR);
            }

            if (!StringUtil.contains(headerBusinessSystem, businessSystem)) {
                return authResponseDTO.setError(ErrorCode.NO_ACCESS);
            }

            authResponseDTO.setUserAuth(userAuthServer.getUser(tokenId));

            if (authResponseDTO.getUserAuth() == null || authResponseDTO.getUserAuth().getUserId() == null
                    || authResponseDTO.getUserAuth().getUserId().compareTo(BigInteger.ZERO) == 0
                    || StringUtil.isEmpty(authResponseDTO.getUserAuth().getUserName())) {
                return authResponseDTO.setError(ErrorCode.TOKEN_LOGIN_EXPIRATION);
            }

            if (now.getTime() > authResponseDTO.getUserAuth().getTokenExpireTime()) {
                return authResponseDTO.setError(ErrorCode.TOKEN_EXPIRATION);
            }
        } else {
            return authResponseDTO.setError(ErrorCode.NOT_LOGIN);
        }

        return authResponseDTO;
    }

}
