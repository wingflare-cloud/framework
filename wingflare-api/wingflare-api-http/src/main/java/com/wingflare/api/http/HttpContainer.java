package com.wingflare.api.http;


import java.util.List;
import java.util.Map;

/**
 * http容器对象
 */
public interface HttpContainer {

    /**
     * 获取客户端ip
     *
     * @return
     */
    String getClientIp();

    /**
     * 获取请求参数
     * @param name
     * @return
     */
    String getParam(String name);

    /**
     * 获取请求参数（带默认值）
     * @param name
     * @param defaultValue
     * @return
     */
    String getParam(String name, String defaultValue);

    /**
     * 获取请求query参数
     * @return
     */
    String getQueryString();

    /**
     * 获取原始请求体
     * @return
     */
    String getRawBody();

    /**
     * 获取请求头
     * @param name
     * @return
     */
    String getHeader(String name);

    /**
     * 获取请求头
     * @param name
     * @return
     */
    List<String> getHeaders(String name);

    /**
     * 获取全部头信息
     * @return
     */
    Map<String, String> getHeaders();

    /**
     * 获取全部头信息
     * @return
     */
    Map<String, List<String>> getAllHeaders();

    /**
     * 获取session id
     * @return
     */
    String getSessionId();

    /**
     * 获取session属性值
     * @param name
     * @return
     */
    Object getSessionAttr(String name);

    /**
     * 获取全部session属性
     * @return
     */
    Map<String, Object> getSessionAttrs();

    /**
     * 设置session属性值
     * @param name
     * @param var
     */
    void setSessionAttr(String name, Object var);

    /**
     * 删除session属性
     * @param name
     */
    void removeSessionAttr(String name);

}
