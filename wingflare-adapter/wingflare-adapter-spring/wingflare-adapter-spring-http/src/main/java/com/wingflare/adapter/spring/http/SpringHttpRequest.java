package com.wingflare.adapter.spring.http;


import com.wingflare.api.core.Charset;
import com.wingflare.api.http.HttpHeader;
import com.wingflare.api.http.HttpHeaderConstants;
import com.wingflare.api.http.HttpMethod;
import com.wingflare.api.http.HttpRequest;
import com.wingflare.api.http.HttpResponse;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;


@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class SpringHttpRequest implements HttpRequest {

    private String url;
    private HttpMethod method;
    private HttpHeader header = new SpringHttpHeader();
    private String contentType;
    private Map<String, String> queryParams = new HashMap<>();
    private Map<String, String> formParams = new HashMap<>();
    private String body;
    private Map<String, File> files = new HashMap<>();
    private int connectTimeout = 30000;
    private int readTimeout = 30000;
    private Map<String, String> cookies = new HashMap<>();
    private boolean followRedirects = true;
    private Charset charset;
    private final RestTemplate restTemplate;

    public SpringHttpRequest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public HttpRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public HttpRequest setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    @Override
    public HttpMethod getMethod() {
        return method;
    }

    @Override
    public HttpRequest addHeader(String key, String value) {
        header.addHeader(key, value);
        return this;
    }

    @Override
    public HttpRequest setHeader(HttpHeader header) {
        this.header = header;
        return this;
    }

    @Override
    public HttpRequest setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public HttpHeader getHeaders() {
        return header;
    }

    @Override
    public HttpRequest addQueryParam(String key, String value) {
        queryParams.put(key, value);
        return this;
    }

    @Override
    public HttpRequest addQueryParams(Map<String, String> params) {
        queryParams.putAll(params);
        return this;
    }

    @Override
    public HttpRequest setQueryParams(Map<String, String> params) {
        queryParams = params;
        return this;
    }

    @Override
    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    @Override
    public HttpRequest addFormParam(String key, String value) {
        formParams.put(key, value);
        return this;
    }

    @Override
    public HttpRequest addFormParams(Map<String, String> params) {
        formParams.putAll(params);
        return this;
    }

    @Override
    public HttpRequest setFormParams(Map<String, String> params) {
        formParams = params;
        return this;
    }

    @Override
    public Map<String, String> getFormParams() {
        return formParams;
    }

    @Override
    public HttpRequest setBody(String body) {
        this.body = body;
        return this;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public HttpRequest addFile(String fieldName, File file) {
        files.put(fieldName, file);
        return this;
    }

    @Override
    public HttpRequest setFiles(Map<String, File> files) {
        this.files = files;
        return this;
    }

    @Override
    public Map<String, File> getFiles() {
        return files;
    }

    @Override
    public HttpRequest setConnectTimeout(int timeoutMs) {
        this.connectTimeout = timeoutMs;
        return this;
    }

    @Override
    public int getConnectTimeout() {
        return connectTimeout;
    }

    @Override
    public HttpRequest setReadTimeout(int timeoutMs) {
        this.readTimeout = timeoutMs;
        return this;
    }

    @Override
    public int getReadTimeout() {
        return readTimeout;
    }

    @Override
    public HttpRequest addCookie(String name, String value) {
        cookies.put(name, value);
        return this;
    }

    @Override
    public HttpRequest addCookies(Map<String, String> cookies) {
        cookies.forEach((k, v) -> new SpringHttpCookie(k).setValue(v));
        return this;
    }

    @Override
    public HttpRequest setCookie(Map<String, String> cookies) {
        this.cookies = cookies;
        return this;
    }

    @Override
    public Map<String, String> getCookies() {
        return cookies;
    }

    @Override
    public HttpRequest setFollowRedirects(boolean follow) {
        this.followRedirects = follow;
        return this;
    }

    @Override
    public boolean isFollowRedirects() {
        return followRedirects;
    }

    @Override
    public HttpRequest setCharset(Charset charset) {
        this.charset = charset;
        return this;
    }

    @Override
    public Charset getCharset() {
        return charset;
    }

    @Override
    public HttpResponse execute() {
        HttpHeaders requestHeaders = ((SpringHttpHeader) header).getSpringHeaders();

        if (contentType != null) {
            if (charset != null) {
                requestHeaders.setContentType(
                        MediaType.parseMediaType(String.format("%s; charset=%s", contentType, charset.name())));
            } else {
                requestHeaders.setContentType(MediaType.parseMediaType(contentType));
            }
        }

        if (!cookies.isEmpty()) {
            StringJoiner cookieJoiner = new StringJoiner(";");

            for (Map.Entry<String, String> entry : cookies.entrySet()) {
                cookieJoiner.add(entry.getKey() + "=" + entry.getValue());
            }

            String cookieHeaderValue = cookieJoiner.toString();
            requestHeaders.set(HttpHeaderConstants.REQUEST_COOKIE, cookieHeaderValue);
        }

        HttpEntity<?> requestEntity;

        if (!formParams.isEmpty() || !files.isEmpty()) {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            formParams.forEach(body::add);
            files.forEach(body::add);
            requestEntity = new HttpEntity<>(body, requestHeaders);
        } else {
            requestEntity = new HttpEntity<>(body, requestHeaders);
        }

        org.springframework.http.HttpMethod springMethod =
                org.springframework.http.HttpMethod.valueOf(method.name());

        ResponseEntity<String> response = restTemplate.exchange(
                buildUrlWithQueryParams(),
                springMethod,
                requestEntity,
                String.class
        );

        return new SpringHttpResponse(response);
    }

    private String buildUrlWithQueryParams() {
        if (queryParams.isEmpty()) return url;

        StringBuilder urlBuilder = new StringBuilder(url);
        urlBuilder.append("?");
        queryParams.forEach((k, v) -> urlBuilder.append(k).append("=").append(v).append("&"));
        return urlBuilder.substring(0, urlBuilder.length() - 1);
    }
}