package com.wingflare.lib.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.wingflare.api.security.AuthResponseDTO;
import com.wingflare.api.security.UserAuthServer;
import com.wingflare.lib.config.ConfigUtil;
import com.wingflare.lib.core.utils.StringUtil;

import java.math.BigInteger;
import java.util.Date;

/**
 * 认证工具类
 */
public class AuthTool {

    private final UserAuthServer userAuthServer;

    public AuthTool(UserAuthServer userAuthServer) {
        this.userAuthServer = userAuthServer;
    }

    public AuthResponseDTO checkLogin(String token, String headerBusinessSystem) {
        boolean hasToken = StringUtil.isNotBlank(token);
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();

        if (hasToken) {
            DecodedJWT decodedJWT;

            try {
                decodedJWT = verifyToken(token, headerBusinessSystem);
            } catch (TokenExpiredException e) {
                return authResponseDTO.setError(ErrorCode.TOKEN_EXPIRATION);
            } catch (JWTVerificationException e) {
                return authResponseDTO.setError(ErrorCode.TOKEN_EXPIRATION_OR_ERROR);
            }

            authResponseDTO.setUserAuth(userAuthServer.getUser(decodedJWT.getId()));

            if (authResponseDTO.getUserAuth() == null || authResponseDTO.getUserAuth().getUserId() == null
                    || StringUtil.isBlank(authResponseDTO.getUserAuth().getUserId())
                    || StringUtil.isEmpty(authResponseDTO.getUserAuth().getUserName())) {
                return authResponseDTO.setError(ErrorCode.TOKEN_LOGIN_EXPIRATION);
            }
        } else {
            return authResponseDTO.setError(ErrorCode.NOT_LOGIN);
        }

        return authResponseDTO;
    }

    /**
     * 创建登录token
     *
     * @param tokenId
     * @param userId
     * @param audience
     * @return
     */
    public String createLoginToken(String tokenId, String userId, Date expireTime, String ... audience) {
        return JWT.create()
                .withIssuer(ConfigUtil.getProperty("jwt.issuer", "wingflare"))
                .withIssuedAt(new Date())
                .withSubject(userId)
                .withAudience(audience)
                .withJWTId(tokenId)
                .withExpiresAt(expireTime)
                .sign(AlgorithmFactory.getInstance().createAlgorithm());
    }

    /**
     * 创建刷新token
     *
     * @param tokenId
     * @return
     */
    public String createRefreshToken(String tokenId, String userId, Date expireTime) {
        return JWT.create()
                .withIssuer(ConfigUtil.getProperty("jwt.issuer", "wingflare"))
                .withIssuedAt(new Date())
                .withSubject(userId)
                .withJWTId(tokenId)
                .withExpiresAt(expireTime)
                .sign(AlgorithmFactory.getInstance().createAlgorithm());
    }

    /**
     * 验证 JWT 令牌并返回解析结果
     * @param token 要验证的 JWT 令牌
     * @return 解析后的 JWT 对象
     * @throws com.auth0.jwt.exceptions.JWTVerificationException 验证失败时抛出
     */
    public DecodedJWT verifyToken(String token, String id, String subject) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(AlgorithmFactory.getInstance().createAlgorithm())
                .withIssuer(ConfigUtil.getProperty("jwt.issuer", "wingflare"))
                .withSubject(subject)
                .withJWTId(id)
                .build();

        return verifier.verify(token);
    }

    /**
     * 验证 JWT 令牌并返回解析结果
     * @param token 要验证的 JWT 令牌
     * @return 解析后的 JWT 对象
     * @throws com.auth0.jwt.exceptions.JWTVerificationException 验证失败时抛出
     */
    public DecodedJWT verifyToken(String token, String audience) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(AlgorithmFactory.getInstance().createAlgorithm())
                .withIssuer(ConfigUtil.getProperty("jwt.issuer", "wingflare"))
                .withAnyOfAudience(audience)
                .build();

        return verifier.verify(token);
    }

    /**
     * 验证 JWT 令牌并返回解析结果
     * @param token 要验证的 JWT 令牌
     * @return 解析后的 JWT 对象
     * @throws com.auth0.jwt.exceptions.JWTVerificationException 验证失败时抛出
     */
    public DecodedJWT verifyToken(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(AlgorithmFactory.getInstance().createAlgorithm())
                .withIssuer(ConfigUtil.getProperty("jwt.issuer", "wingflare"))
                .build();

        return verifier.verify(token);
    }


    /**
     * 验证 JWT 令牌并返回解析结果
     * @param token 要验证的 JWT 令牌
     * @return 解析后的 JWT 对象
     * @throws com.auth0.jwt.exceptions.JWTVerificationException 验证失败时抛出
     */
    public DecodedJWT verifyToken(String token, String id, String subject, String audience) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(AlgorithmFactory.getInstance().createAlgorithm())
                .withIssuer(ConfigUtil.getProperty("jwt.issuer", "wingflare"))
                .withSubject(subject)
                .withJWTId(id)
                .withAnyOfAudience(audience)
                .build();

        return verifier.verify(token);
    }


}
