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

import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
     * 获取getClaimsMap
     */
    public static Map<String, Object> getClaimsMap(String systemCode, String id, Date date, String secret) {
        String timeS = DateUtil.format(date, DateFormat.PATTERN_CLASSICAL);
        return new HashMap<String, Object>(4){{
            put("time", timeS);
            put(Ctx.HEADER_KEY_TOKEN_ID, id);
            put(Ctx.HEADER_KEY_BUSINESS_SYSTEM, systemCode);
            put("dam", DigestUtils.md5Hex(systemCode + id + timeS + secret));
            put("dah", DigestUtils.sha256Hex(systemCode + id + timeS + secret));
        }};
    }

    private static String mapToQueryString(Map<String, Object> map) {
        return map.entrySet().stream()
                .map(entry -> {
                    try {
                        String key = URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString());
                        String value = URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8.toString());
                        return key + "=" + value;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.joining("&"));
    }

    /**
     * claimsMap信息签名
     */
    public static String claimsMapSign(Map<String, Object> claimsMap, String secret) {
        claimsMap.remove(Ctx.AUTH_JSON_SIGN_KEY);
        claimsMap.put("secret", secret);
        return DigestUtils.sha256Hex(mapToQueryString(claimsMap));
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
     * 验证token数据
     *
     * @param claimsMap
     * @param secret
     * @return
     */
    public static boolean checkTokenClaimsMap(Map<String, Object> claimsMap, String secret) {
        if (claimsMap.containsKey(Ctx.HEADER_KEY_TOKEN_ID) && claimsMap.containsKey("time") && claimsMap.containsKey("dam")
                && claimsMap.containsKey("dah") && claimsMap.containsKey(Ctx.HEADER_KEY_BUSINESS_SYSTEM)) {
            String time = claimsMap.get("time").toString();
            String tokenId = claimsMap.get(Ctx.HEADER_KEY_TOKEN_ID).toString();
            String businessSystem = claimsMap.get(Ctx.HEADER_KEY_BUSINESS_SYSTEM).toString();
            String dam = DigestUtils.md5Hex(businessSystem + tokenId + time + secret);
            String dah = DigestUtils.sha256Hex(businessSystem + tokenId + time + secret);
            return claimsMap.get("dam").equals(dam) && claimsMap.get("dah").equals(dah);
        }
        return false;
    }

    /**
     * 获取认证主体ID
     *
     * @return
     */
    public static BigInteger getAuthMainId() {
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
    public static BigInteger getUserId() {
        return ContextHolder.get(Ctx.CONTEXT_KEY_USER_ID, new BigInteger("0"), BigInteger.class);
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
    public static BigInteger getAppId() {
        return ContextHolder.get(Ctx.CONTEXT_KEY_APP_ID, new BigInteger("0"), BigInteger.class);
    }

    /**
     * 获取父级应用id
     */
    public static BigInteger getParentAppId() {
        return ContextHolder.get(Ctx.CONTEXT_KEY_PARENT_APP_ID, new BigInteger("0"), BigInteger.class);
    }

    /**
     * 获取商户id
     */
    public static BigInteger getMerchantId() {
        return ContextHolder.get(Ctx.CONTEXT_KEY_MERCHANT_ID, new BigInteger("0"), BigInteger.class);
    }

    /**
     * 获取父级商户id
     */
    public static BigInteger getParentMerchantId() {
        return ContextHolder.get(Ctx.CONTEXT_KEY_PARENT_MERCHANT_ID, new BigInteger("0"), BigInteger.class);
    }

}
