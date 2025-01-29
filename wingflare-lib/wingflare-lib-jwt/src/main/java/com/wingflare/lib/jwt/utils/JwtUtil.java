package com.wingflare.lib.jwt.utils;


import com.wingflare.lib.core.utils.ConvertUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author naizui_ycx
 * @date {2021/12/22}
 * @description
 */
@Component
public class JwtUtil {


    @Value("${jwt.secret:sxxfXHW1s6baG6JcFmQ0c7NKE07RZFNkdJmKeybKpbN2HtZGr6SMMhDPhnA7jFE3HPHtfPdBhyRRDHKZkHnEb3Y3C9nBETKmwi7YXQCwHsG0denDjH3Yjap2FM3Efs4p58aYCKXh7iynJNPY6bR61wYrcQWy9aRzhtyA0zXbwKcjEWnSmHn2Yx55dncha78kSp0jrb6RyspjWBDic2jjCbypcsD27xsWK04p0Ba2Q0FWscHsXwf3ct6kKEW08FG8}")
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
                .claims(claims)
                .signWith(getSecret(), Jwts.SIG.HS512)
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
                .verifyWith(getSecret())
                .build()
                .parseSignedClaims(token)
                .getPayload();
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
    public SecretKey getSecret() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

}
