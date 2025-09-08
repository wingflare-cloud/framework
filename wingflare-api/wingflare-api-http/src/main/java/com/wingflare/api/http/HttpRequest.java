package com.wingflare.api.http;


import com.wingflare.api.core.enums.Charset;

import java.io.File;
import java.util.Map;

/**
 * 全面的HTTP请求接口抽象
 */
public interface HttpRequest {

    // 设置请求URL
    HttpRequest setUrl(String url);

    // 获取请求URL
    String getUrl();

    // 设置请求方法
    HttpRequest setMethod(HttpMethod method);

    // 获取请求方法
    HttpMethod getMethod();

    // 添加请求头
    HttpRequest addHeader(String key, String value);

    // 设置http头
    HttpRequest setHeader(HttpHeader header);

    // 设置请求类型
    HttpRequest setContentType(String contentType);

    // 获取请求类型
    String getContentType();

    // 获取所有请求头
    HttpHeader getHeaders();

    // 添加URL查询参数
    HttpRequest addQueryParam(String key, String value);

    // 添加多个URL查询参数
    HttpRequest addQueryParams(Map<String, String> params);

    // 设置URL查询参数
    HttpRequest setQueryParams(Map<String, String> params);

    // 获取所有查询参数
    Map<String, String> getQueryParams();

    // 添加表单参数（适用于application/x-www-form-urlencoded）
    HttpRequest addFormParam(String key, String value);

    // 添加多个表单参数
    HttpRequest addFormParams(Map<String, String> params);

    // 设置表单参数
    HttpRequest setFormParams(Map<String, String> params);

    // 获取所有表单参数
    Map<String, String> getFormParams();

    // 设置请求体（适用于JSON等格式）
    HttpRequest setBody(String body);

    // 获取请求体
    String getBody();

    // 添加上传文件（适用于multipart/form-data）
    HttpRequest addFile(String fieldName, File file);

    // 添加多个上传文件
    HttpRequest setFiles(Map<String, File> files);

    // 获取所有上传文件
    Map<String, File> getFiles();

    // 设置连接超时时间（毫秒）
    HttpRequest setConnectTimeout(int timeoutMs);

    // 获取连接超时时间
    int getConnectTimeout();

    // 设置读取超时时间（毫秒）
    HttpRequest setReadTimeout(int timeoutMs);

    // 获取读取超时时间
    int getReadTimeout();

    // 添加Cookie
    HttpRequest addCookie(String name, String value);

    // 添加多个Cookie
    HttpRequest addCookies(Map<String, String> cookies);

    // 设置cookie
    HttpRequest setCookie(Map<String, String> cookies);

    // 获取所有Cookie
    Map<String, String> getCookies();

    // 设置是否跟随重定向
    HttpRequest setFollowRedirects(boolean follow);

    // 是否跟随重定向
    boolean isFollowRedirects();

    // 设置请求字符编码
    HttpRequest setCharset(Charset charset);

    // 获取请求字符编码
    Charset getCharset();

    // 执行请求并返回响应
    HttpResponse execute();
}
