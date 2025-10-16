package com.wingflare.adapter.spring.common.http;


import com.wingflare.api.http.HttpCookie;

import java.time.Instant;
import java.util.StringJoiner;


/**
 * spring环境下cookie的实现
 */
public class SpringHttpCookie implements HttpCookie {

    private final String name;
    private String value;
    private Instant expiry;
    private String path;
    private String domain;
    private boolean secure;
    private boolean httpOnly;
    private String sameSite;

    public SpringHttpCookie(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public HttpCookie setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public HttpCookie setExpiry(Instant expiry) {
        this.expiry = expiry;
        return this;
    }

    @Override
    public Instant getExpiry() {
        return expiry;
    }

    @Override
    public HttpCookie setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public HttpCookie setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public HttpCookie setSecure(boolean secure) {
        this.secure = secure;
        return this;
    }

    @Override
    public boolean isSecure() {
        return secure;
    }

    @Override
    public HttpCookie setHttpOnly(boolean httpOnly) {
        this.httpOnly = httpOnly;
        return this;
    }

    @Override
    public boolean isHttpOnly() {
        return httpOnly;
    }

    @Override
    public HttpCookie setSameSite(String sameSite) {
        this.sameSite = sameSite;
        return this;
    }

    @Override
    public String getSameSite() {
        return sameSite;
    }

    @Override
    public String toHeaderString() {
        StringJoiner joiner = new StringJoiner("; ");
        joiner.add(name + "=" + value);

        if (expiry != null) {
            joiner.add("Expires=" + expiry);
        }

        if (path != null) {
            joiner.add("Path=" + path);
        }

        if (domain != null) {
            joiner.add("Domain=" + domain);
        }

        if (secure) {
            joiner.add("Secure");
        }

        if (httpOnly) {
            joiner.add("HttpOnly");
        }

        if (sameSite != null) {
            joiner.add("SameSite=" + sameSite);
        }

        return joiner.toString();
    }
}