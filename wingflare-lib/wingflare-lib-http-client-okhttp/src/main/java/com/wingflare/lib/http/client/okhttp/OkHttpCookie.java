package com.wingflare.lib.http.client.okhttp;


import com.wingflare.api.http.HttpCookie;

import java.time.Instant;
import java.util.StringJoiner;


/**
 * spring环境下cookie的实现
 */
public class OkHttpCookie implements HttpCookie {

    private final String name;
    private String value;
    private Instant expiry;
    private String path;
    private String domain;
    private boolean secure;
    private boolean httpOnly;
    private String sameSite;

    public OkHttpCookie(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public OkHttpCookie setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public OkHttpCookie setExpiry(Instant expiry) {
        this.expiry = expiry;
        return this;
    }

    @Override
    public Instant getExpiry() {
        return expiry;
    }

    @Override
    public OkHttpCookie setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public OkHttpCookie setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public OkHttpCookie setSecure(boolean secure) {
        this.secure = secure;
        return this;
    }

    @Override
    public boolean isSecure() {
        return secure;
    }

    @Override
    public OkHttpCookie setHttpOnly(boolean httpOnly) {
        this.httpOnly = httpOnly;
        return this;
    }

    @Override
    public boolean isHttpOnly() {
        return httpOnly;
    }

    @Override
    public OkHttpCookie setSameSite(String sameSite) {
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