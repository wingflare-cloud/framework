package com.wingflare.api.http;


import com.wingflare.api.core.Charset;
import com.wingflare.api.core.MimeType;

import java.io.OutputStream;

/**
 * http响应
 */
public interface HttpResponse {

    /**
     * 是否为流式响应
     * @return
     */
    boolean isStream();

    /**
     * 是否为文本内容
     * @return
     */
    boolean isText();

    /**
     * 获取响应编码
     * @return
     */
    Charset getCharset();

    /**
     * 获取返回类型
     * @return
     */
    MimeType getContentType();

    /**
     * 获取状态码
     * @return
     */
    HttpStatus getStatus();

    /**
     * 获取头信息
     * @return
     */
    HttpHeader getHeader();

    /**
     * 获取响应体字符串
     * @return
     */
    String getBody();

    /**
     * 获取响应对象
     * @param clz
     * @return
     * @param <T>
     */
    <T> T getBody(Class<T> clz);

    /**
     * 获取cookie
     * @return
     */
    HttpCookie getCookie();

    /**
     * 获取响应流
     * @return
     */
    OutputStream getStream();

    /**
     * 检查响应是否已完成
     * @return
     */
    boolean isFinished();

    /**
     * 检查是否支持流式响应
     * @return 是否支持
     */
    boolean isStreamingSupported();

}
