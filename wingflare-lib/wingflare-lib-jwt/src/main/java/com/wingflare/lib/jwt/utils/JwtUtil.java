package com.wingflare.lib.jwt.utils;


import com.wingflare.lib.core.utils.ConvertUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author naizui_ycx
 * @date {2021/12/22}
 * @description
 */
@Component
public class JwtUtil {


    @Value("${jwt.secret:abcdefghijklmnopqrstuvwxyz}")
    private String secret;

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    public String createToken(Map<String, Object> claims)
    {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 根据身份信息获取键值
     *
     * @param claims 身份信息
     * @param key 键
     * @return 值
     */
    public String getValue(Claims claims, String key)
    {
        return ConvertUtil.toStr(claims.get(key), "");
    }

    /**
     * 获取secret
     *
     * @return
     */
    public String getSecret() {
        return secret;
    }

}
