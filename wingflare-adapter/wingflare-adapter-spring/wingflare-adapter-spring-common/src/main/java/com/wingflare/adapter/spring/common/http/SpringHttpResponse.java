package com.wingflare.adapter.spring.common.http;


import com.wingflare.api.core.enums.Charset;
import com.wingflare.api.core.enums.MimeType;
import com.wingflare.api.http.HttpCookie;
import com.wingflare.api.http.HttpHeader;
import com.wingflare.api.http.HttpHeaderConstants;
import com.wingflare.api.http.HttpResponse;
import com.wingflare.api.http.HttpStatus;
import okhttp3.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SpringHttpResponse implements HttpResponse {

    private static final Pattern COOKIE_PATTERN = Pattern.compile("(\\w+)(=([^;]+))?;?");
    private final Response response;
    private final HttpHeader header;
    private final List<HttpCookie> cookies;
    private String responseBody;

    // 新增构造函数，接受OkHttp的Response对象
    public SpringHttpResponse(Response response) {
        this.response = response;
        this.header = new SpringHttpHeader();
        this.cookies = new ArrayList<>();

        // 解析响应头
        if (response.headers() != null) {
            for (int i = 0; i < response.headers().size(); i++) {
                String name = response.headers().name(i);
                String value = response.headers().value(i);
                this.header.addHeader(name, value);

                // 解析Set-Cookie头
                if (HttpHeaderConstants.RESPONSE_SET_COOKIE.equalsIgnoreCase(name)) {
                    SpringHttpCookie springCookie = parseSingleCookie(value);
                    if (springCookie != null) {
                        cookies.add(springCookie);
                    }
                }
            }
        }

        // 读取响应体
        if (response.body() != null) {
            try {
                responseBody = response.body().string();
            } catch (IOException e) {
                responseBody = "";
            }
        } else {
            responseBody = "";
        }
    }

    @Override
    public boolean isStream() {
        return false;
    }

    @Override
    public boolean isText() {
        if (response != null) {
            String contentType = header.getFirstHeader("Content-Type");
            return contentType != null && (contentType.contains("text/") || contentType.contains("json") || contentType.contains("xml"));
        }
        return false;
    }

    @Override
    public boolean isOk() {
        if (response != null) {
            return response.isSuccessful();
        }
        return false;
    }

    @Override
    public Charset getCharset() {
        if (response != null) {
            String contentType = header.getFirstHeader("Content-Type");
            if (contentType != null && contentType.contains("charset=")) {
                String charsetStr = contentType.substring(contentType.indexOf("charset=") + 8);
                return Charset.fromCharsetName(charsetStr);
            }
            return null;
        }
        return null;
    }

    @Override
    public MimeType getContentType() {
        if (response != null) {
            String contentType = header.getFirstHeader("Content-Type");
            if (contentType != null) {
                // 简化处理，实际应该解析完整的MIME类型
                if (contentType.contains("json")) {
                    return MimeType.JSON;
                } else if (contentType.contains("xml")) {
                    return MimeType.XML;
                } else if (contentType.contains("html")) {
                    return MimeType.HTML;
                } else if (contentType.startsWith("text/")) {
                    return MimeType.TEXT;
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public HttpStatus getStatus() {
        if (response != null) {
            return HttpStatus.getByCode(response.code());
        }
        return null;
    }

    @Override
    public HttpHeader getHeader() {
        return header;
    }

    @Override
    public String getBody() {
        return responseBody;
    }

    @Override
    public <T> T getBody(Class<T> clz) {
        // 实际实现需要添加JSON转换逻辑
        return null;
    }

    @Override
    public List<HttpCookie> getCookie() {
        return cookies;
    }

    @Override
    public OutputStream getStream() {
        return new ByteArrayOutputStream();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isStreamingSupported() {
        return false;
    }

    private static SpringHttpCookie parseSingleCookie(String setCookie) {
        Matcher matcher = COOKIE_PATTERN.matcher(setCookie);
        SpringHttpCookie cookie = null;

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(3); // 可能为null（如Secure、HttpOnly等标志）

            if (cookie == null) {
                // 第一个键值对是Cookie的名称和值（格式：name=value）
                if (key == null || value == null) {
                    return null; // 无效的Cookie格式
                }
                cookie = new SpringHttpCookie(key);
                cookie.setValue(value);
            } else {
                // 解析Cookie属性（Expires、Path、Domain等）
                parseCookieAttribute(cookie, key, value);
            }
        }

        return cookie;
    }

    /**
     * 解析Cookie属性并设置到SpringHttpCookie对象
     */
    private static void parseCookieAttribute(SpringHttpCookie cookie, String key, String value) {
        switch (key.toLowerCase()) {
            case "expires":
                // 解析Expires（格式：EEE, dd-MMM-yyyy HH:mm:ss zzz）
                if (value != null) {
                    ZonedDateTime zonedDateTime = ZonedDateTime.parse(
                            value,
                            DateTimeFormatter.RFC_1123_DATE_TIME.withZone(ZoneId.of("GMT"))
                    );
                    cookie.setExpiry(zonedDateTime.toInstant());
                }
                break;
            case "path":
                cookie.setPath(value);
                break;
            case "domain":
                cookie.setDomain(value);
                break;
            case "secure":
                cookie.setSecure(true);
                break;
            case "httponly":
                cookie.setHttpOnly(true);
                break;
            case "samesite":
                cookie.setSameSite(value);
                break;
        }
    }

}