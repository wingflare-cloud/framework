package com.wingflare.lib.standard.utils;


import com.wingflare.lib.core.constants.DateFormat;
import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.exceptions.NoAuthException;
import com.wingflare.lib.core.utils.DateUtil;
import com.wingflare.lib.core.utils.ObjectUtil;
import com.wingflare.lib.core.utils.SerializationUtil;
import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.standard.enums.AuthType;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/22}
 * @description 安全以及认证工具
 */
public class SecurityUtil {

    private static final Pattern pattern = Pattern.compile("@S\\[(.*?)\\]");

    /**
     * 获取当前认证模式
     */
    public static AuthType getAuthMode() {
        return ContextHolder.get(Ctx.AUTH_MODE_CONTEXT, AuthType.class);
    }

    /**
     * 获取用户登录token存储key
     *
     * @param id
     * @return
     */
    public static String getTokenKey(String id) {
        return String.format("%s:%s", Ctx.PREFIX_ACCESS_TOKEN, id);
    }

    /**
     * 获取refresh token存储key
     *
     * @param id
     * @return
     */
    public static String getRefreshTokenKey(String id) {
        return String.format("%s:%s", Ctx.PREFIX_REFRESH_TOKEN, id);
    }

    /**
     * 获取getClaimsMap
     */
    public static Map<String, Object> getClaimsMap(String id, Date date, String secret) {
        String timeS = DateUtil.format(date, DateFormat.PATTERN_CLASSICAL);
        return new HashMap<String, Object>(4){{
            put("time", timeS);
            put(Ctx.HEADER_KEY_TOKEN_ID, id);
            put("dam", DigestUtils.md5Hex(id + timeS + secret));
            put("dah", DigestUtils.sha256Hex(id + timeS + secret));
        }};
    }

    /**
     * claimsMap信息签名
     */
    public static String claimsMapSign(Map<String, Object> claimsMap, String secret) {
        return DigestUtils.sha256Hex(String.format("%s=%s&%s=%s&%s=%s&%s=%s&%s=%s",
                "time", claimsMap.get("time"), "id", claimsMap.get(Ctx.HEADER_KEY_TOKEN_ID),
                "dam", claimsMap.get("dam"), "dah",
                claimsMap.get("dah"), "secret", secret
        ));
    }

    /**
     * 判断claimsMap信息是否被修改
     */
    public static boolean checkClaimsMapSign(Map<String, Object> claimsMap, String secret) {
        if (claimsMap.get(Ctx.AUTH_JSON_SIGN_KEY) != null) {
            return claimsMap.get(Ctx.AUTH_JSON_SIGN_KEY).equals(claimsMapSign(claimsMap, secret));
        }
        return false;
    }

    /**
     * 获取认证主体ID
     *
     * @return
     */
    public static String getAuthMainId() {
        switch (getAuthMode()) {
            case USER:
                return getUserId();
            case APP:
                return getAppId();
            default:
                throw new NoAuthException();
        }
    }

    /**
     * 获取用户ID
     */
    public static String getUserId() {
        return ContextHolder.get(Ctx.CONTEXT_KEY_USER_ID, "1");
    }

    /**
     * 获取用户名称
     */
    public static String getUsername() {
        return ContextHolder.get(Ctx.CONTEXT_KEY_USER_NAME, "SYSTEM");
    }

    /**
     * 获取用户key
     */
    public static String getTokenId() {
        return ContextHolder.get(Ctx.CONTEXT_KEY_TOKEN_ID);
    }

    /**
     * 带类型信息的数据编码
     *
     * @param value
     *
     * @return
     */
    public static String typeValueEncode(Object value) {
        return String.format(
                "@S[%s]",
                Base64.encodeBase64URLSafeString(
                        SerializationUtil.serialize(value)
                )
        );
    }

    /**
     * 匹配带类型的值
     *
     * @param s
     * @return
     */
    public static Matcher typeValueMatch(String s) {
        return pattern.matcher(s);
    }

    /**
     * 带类型值字符串解码
     *
     * @param s
     * @param <T>
     *
     * @return
     */
    public static <T> T typeValueDecode(String s) {
        return ObjectUtil.cast(SerializationUtil.deserialize(Base64.decodeBase64(s)));
    }

    /**
     * 获取业务系统
     */
    public static String getBusinessSystem() {
        return ContextHolder.get(Ctx.CONTEXT_KEY_BUSINESS_SYSTEM);
    }

    /**
     * 获取应用id
     */
    public static String getAppId() {
        return ContextHolder.get(Ctx.CONTEXT_KEY_APP_ID, "1");
    }

    /**
     * 获取父级应用id
     */
    public static String getParentAppId() {
        return ContextHolder.get(Ctx.CONTEXT_KEY_PARENT_APP_ID, "1");
    }

    /**
     * 获取商户id
     */
    public static String getMerchantId() {
        return ContextHolder.get(Ctx.CONTEXT_KEY_MERCHANT_ID, "1");
    }

    /**
     * 获取父级商户id
     */
    public static String getParentMerchantId() {
        return ContextHolder.get(Ctx.CONTEXT_KEY_PARENT_MERCHANT_ID, "1");
    }

}
