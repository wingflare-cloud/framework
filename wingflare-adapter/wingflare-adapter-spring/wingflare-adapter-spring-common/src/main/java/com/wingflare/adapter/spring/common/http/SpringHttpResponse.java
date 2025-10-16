package com.wingflare.adapter.spring.common.http;


import com.wingflare.api.core.enums.Charset;
import com.wingflare.api.core.enums.MimeType;
import com.wingflare.api.http.HttpCookie;
import com.wingflare.api.http.HttpHeader;
import com.wingflare.api.http.HttpHeaderConstants;
import com.wingflare.api.http.HttpResponse;
import com.wingflare.api.http.HttpStatus;
import okhttp3.Response;
import org.springframework.http.ResponseEntity;

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
    private final ResponseEntity<String> responseEntity;
    private final HttpHeader header;
    private final List<HttpCookie> cookies;
    private String responseBody;

    // 新增构造函数，接受OkHttp的Response对象
    public SpringHttpResponse(Response response) {
        this.response = response;
        this.responseEntity = null;
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

    // 保留原有的构造函数，兼容Spring的ResponseEntity
    public SpringHttpResponse(ResponseEntity<String> responseEntity) {
        this.response = null;
        this.responseEntity = responseEntity;
        this.header = new SpringHttpHeader();
        this.responseBody = responseEntity.getBody();

        List<String> setCookieHeaders = responseEntity.getHeaders().get(HttpHeaderConstants.RESPONSE_SET_COOKIE);

        if (setCookieHeaders != null && !setCookieHeaders.isEmpty()) {
            cookies = new ArrayList<>();
            for (String setCookie : setCookieHeaders) {
                SpringHttpCookie springCookie = parseSingleCookie(setCookie);
                if (springCookie != null) {
                    cookies.add(springCookie);
                }
            }

            responseEntity.getHeaders().remove(HttpHeaderConstants.RESPONSE_SET_COOKIE);
        } else {
            cookies = null;
        }

        responseEntity.getHeaders().forEach((k, v) ->
                v.forEach(val -> this.header.addHeader(k, val)));
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
        } else if (responseEntity != null) {
            if (responseEntity.getHeaders().getContentType() == null) {
                return false;
            }

            String contentType = responseEntity.getHeaders().getContentType().toString();

            return contentType.contains("text/") || responseEntity.getHeaders().getContentType().getSubtype().contains("json")
                    || responseEntity.getHeaders().getContentType().getSubtype().contains("xml");
        }
        return false;
    }

    @Override
    public boolean isOk() {
        if (response != null) {
            return response.isSuccessful();
        } else if (responseEntity != null) {
            return responseEntity.getStatusCode().is2xxSuccessful();
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
        } else if (responseEntity != null) {
            if (responseEntity.getHeaders().getContentType() == null) {
                return null;
            }

            if (responseEntity.getHeaders().getContentType().getCharset() == null) {
                return null;
            }

            return Charset.fromCharsetName(responseEntity.getHeaders().getContentType().getCharset().name());
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
        } else if (responseEntity != null) {
            if (responseEntity.getHeaders().getContentType() == null) {
                return null;
            }

            return MimeType.getByMimeType(String.format("%s/%s", responseEntity.getHeaders().getContentType().getType(),
                    responseEntity.getHeaders().getContentType().getSubtype()));
        }
        return null;
    }

    @Override
    public HttpStatus getStatus() {
        if (response != null) {
            return HttpStatus.getByCode(response.code());
        } else if (responseEntity != null) {
            return HttpStatus.getByCode(responseEntity.getStatusCode().value());
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