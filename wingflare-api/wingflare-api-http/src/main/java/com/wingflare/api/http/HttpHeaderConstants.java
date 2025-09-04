package com.wingflare.api.http;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description http头
 */
public interface HttpHeaderConstants {

    /**
     * 标准通用头
     */
    String CONTENT_LENGTH = "Content-Length";
    String CONTENT_MD5 = "Content-MD5";
    String CONTENT_TYPE = "Content-Type";
    String DATE = "Date";
    String CACHE_CONTROL = "Cache-Control";
    String CONNECTION = "Connection";
    String UPGRADE = "Upgrade";
    String VIA = "Via";
    String WARNING = "Warning";

    /**
     * 非标准通用头
     */
    String X_REQUEST_ID = "X-Request-Id";
    String X_RESPONSE_ID = "X-Response-Id";

    /**
     * 标准请求头
     */
    String REQUEST_ACCEPT = "Accept";
    String REQUEST_ACCEPT_CHARSET = "Accept-Charset";
    String REQUEST_ACCEPT_ENCODING = "Accept-Encoding";
    String REQUEST_ACCEPT_LANGUAGE = "Accept-Language";
    String REQUEST_ACCEPT_DATETIME = "Accept-Datetime";
    String REQUEST_AUTHORIZATION = "Authorization";
    String REQUEST_COOKIE = "Cookie";
    String REQUEST_EXPECT = "Expect";
    String REQUEST_FROM = "From";
    String REQUEST_HOST = "Host";
    String REQUEST_IF_MATCH = "If-Match";
    String REQUEST_IF_MODIFIED_SINCE = "If-Modified-Since";
    String REQUEST_IF_NONE_MATCH = "If-None-Match";
    String REQUEST_IF_RANGE = "If-Range";
    String REQUEST_IF_UNMODIFIED_SINCE = "If-Unmodified-Since";
    String REQUEST_MAX_FORWARDS = "Max-Forwards";
    String REQUEST_ORIGIN = "Origin";
    String REQUEST_PRAGMA = "Pragma";
    String REQUEST_PROXY_AUTHORIZATION = "Proxy-Authorization";
    String REQUEST_RANGE = "Range";
    String REQUEST_REFERER = "Referer";
    String REQUEST_TE = "TE";
    String REQUEST_USER_AGENT = "User-Agent";

    /**
     * 标准扩展请求头
     */
    String REQUEST_X_FORWARDED_FOR = "X-Forwarded-For";

    /**
     * 非标准扩展请求头
     */
    String REQUEST_X_REAL_IP = "X-Real-IP";
    String REQUEST_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    String REQUEST_PROXY_CLIENT_IP = "Proxy-Client-IP";


    /**
     * 标准响应头
     */
    String RESPONSE_ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    String RESPONSE_ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    String RESPONSE_ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
    String RESPONSE_ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    String RESPONSE_ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";

    String RESPONSE_ACCEPT_PATCH = "Accept-Patch";
    String RESPONSE_ACCEPT_RANGES = "Accept-Ranges";
    String RESPONSE_AGE = "Age";
    String RESPONSE_ALLOW = "Allow";
    String RESPONSE_CONTENT_DISPOSITION = "Content-Disposition";
    String RESPONSE_CONTENT_ENCODING = "Content-Encoding";
    String RESPONSE_CONTENT_LANGUAGE = "Content-Language";
    String RESPONSE_CONTENT_LOCATION = "Content-Location";
    String RESPONSE_CONTENT_RANGE = "Content-Range";
    String RESPONSE_ETAG = "ETag";
    String RESPONSE_EXPIRES = "Expires";
    String RESPONSE_LAST_MODIFIED = "Last-Modified";
    String RESPONSE_LINK = "Link";
    String RESPONSE_LOCATION = "Location";
    String RESPONSE_P3P = "P3P";
    String RESPONSE_PROXY_AUTHENTICATE = "Proxy-Authenticate";
    String RESPONSE_PUBLIC_KEY_PINS = "Public-Key-Pins";
    String RESPONSE_REFRESH = "Refresh";
    String RESPONSE_RETRY_AFTER = "Retry-After";
    String RESPONSE_SERVER = "Server";
    String RESPONSE_SET_COOKIE = "Set-Cookie";
    String RESPONSE_STATUS = "Status";
    String RESPONSE_TRAILER = "Trailer";
    String RESPONSE_TRANSFER_ENCODING = "Transfer-Encoding";
    String RESPONSE_VARY = "Vary";
    String RESPONSE_WWW_AUTHENTICATE = "WWW-Authenticate";

}