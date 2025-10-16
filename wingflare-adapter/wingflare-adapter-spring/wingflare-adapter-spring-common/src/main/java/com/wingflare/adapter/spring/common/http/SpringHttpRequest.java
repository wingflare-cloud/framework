package com.wingflare.adapter.spring.common.http;

import com.wingflare.api.core.enums.Charset;
import com.wingflare.api.http.HttpHeader;
import com.wingflare.api.http.HttpHeaderConstants;
import com.wingflare.api.http.HttpMethod;
import com.wingflare.api.http.HttpRequest;
import com.wingflare.api.http.HttpResponse;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
    private Map<String, String> cookies = new HashMap<>();
    private boolean followRedirects = true;
    private Charset charset;
    private final OkHttpClient okHttpClient;

    public SpringHttpRequest(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
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
        try {
            Request request = buildOkHttpRequest();
            Response response = okHttpClient.newCall(request).execute();
            return new SpringHttpResponse(response);
        } catch (IOException e) {
            throw new RuntimeException("HTTP request failed", e);
        }
    }

    private Request buildOkHttpRequest() throws IOException {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(buildUrlWithQueryParams());
        String methodName = method != null ? method.name() : "GET";
        RequestBody requestBody = buildRequestBody();

        switch (methodName) {
            case "GET":
                requestBuilder.get();
                break;
            case "POST":
                requestBuilder.post(requestBody);
                break;
            case "PUT":
                requestBuilder.put(requestBody);
                break;
            case "DELETE":
                if (requestBody != null) {
                    requestBuilder.delete(requestBody);
                } else {
                    requestBuilder.delete();
                }
                break;
            case "PATCH":
                requestBuilder.patch(requestBody);
                break;
            case "HEAD":
                requestBuilder.head();
                break;
            default:
                requestBuilder.method(methodName, requestBody);
                break;
        }

        if (header instanceof SpringHttpHeader) {
            SpringHttpHeader springHeader = (SpringHttpHeader) header;
            HttpHeaders httpHeaders = springHeader.getSpringHeaders();
            // 遍历所有请求头并添加到OkHttp请求中
            httpHeaders.forEach((name, values) -> {
                if (values != null) {
                    for (String value : values) {
                        requestBuilder.addHeader(name, value);
                    }
                }
            });
        }

        if (contentType != null) {
            String contentTypeValue = contentType;
            if (charset != null) {
                contentTypeValue += "; charset=" + charset.name();
            }
            requestBuilder.header("Content-Type", contentTypeValue);
        }

        if (!cookies.isEmpty()) {
            StringJoiner cookieJoiner = new StringJoiner("; ");
            for (Map.Entry<String, String> entry : cookies.entrySet()) {
                cookieJoiner.add(entry.getKey() + "=" + entry.getValue());
            }
            requestBuilder.header(HttpHeaderConstants.REQUEST_COOKIE, cookieJoiner.toString());
        }

        return requestBuilder.build();
    }

    private RequestBody buildRequestBody() throws IOException {
        if (!files.isEmpty()) {
            // 文件上传使用multipart/form-data
            return buildMultipartFormData();
        } else if (!formParams.isEmpty()) {
            // 表单数据使用application/x-www-form-urlencoded
            return buildFormUrlEncoded();
        } else if (body != null) {
            // 普通文本请求体
            MediaType mediaType = contentType != null ?
                    MediaType.parse(contentType) :
                    MediaType.parse("text/plain");
            return RequestBody.create(body, mediaType);
        }
        return null;
    }

    private RequestBody buildMultipartFormData() throws IOException {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            builder.addFormDataPart(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, File> entry : files.entrySet()) {
            String fieldName = entry.getKey();
            File file = entry.getValue();
            if (file.exists()) {
                MediaType mediaType = MediaType.parse("application/octet-stream");
                builder.addFormDataPart(fieldName, file.getName(),
                        RequestBody.create(file, mediaType));
            }
        }

        return builder.build();
    }

    private RequestBody buildFormUrlEncoded() {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    private String buildUrlWithQueryParams() {
        if (queryParams.isEmpty()) return url;

        StringBuilder urlBuilder = new StringBuilder(url);
        if (!url.contains("?")) {
            urlBuilder.append("?");
        } else if (!url.endsWith("&") && !url.endsWith("?")) {
            urlBuilder.append("&");
        }

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                    .append("&");
        }

        // 移除末尾的&
        if (urlBuilder.charAt(urlBuilder.length() - 1) == '&') {
            urlBuilder.setLength(urlBuilder.length() - 1);
        }

        return urlBuilder.toString();
    }

}