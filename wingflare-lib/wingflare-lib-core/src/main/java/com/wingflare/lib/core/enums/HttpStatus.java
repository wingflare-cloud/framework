package com.wingflare.lib.core.enums;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description HttpStatus
 */
public enum HttpStatus {

    // [RFC7231, Section 6.2.1](http://www.iana.org/go/rfc7231#section-6.2.1)
    CONTINUE(100, "Continue"),

    // [RFC7231, Section 6.2.2](http://www.iana.org/go/rfc7231#section-6.2.2)
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),

    // [RFC2518](http://www.iana.org/go/rfc2518)
    PROCESSING(102, "Processing"),

    // [RFC8297](http://www.iana.org/go/rfc8297)
    EARLY_HINTS(103, "Early Hints"),

    // [RFC7231, Section 6.3.1](http://www.iana.org/go/rfc7231#section-6.3.1)
    OK(200, "OK"),

    // [RFC7231, Section 6.3.2](http://www.iana.org/go/rfc7231#section-6.3.2)
    CREATED(201, "Created"),

    // [RFC7231, Section 6.3.3](http://www.iana.org/go/rfc7231#section-6.3.3)
    ACCEPTED(202, "Accepted"),

    // [RFC7231, Section 6.3.4](http://www.iana.org/go/rfc7231#section-6.3.4)
    NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),

    // [RFC7231, Section 6.3.5](http://www.iana.org/go/rfc7231#section-6.3.5)
    NO_CONTENT(204, "No Content"),

    // [RFC7231, Section 6.3.6](http://www.iana.org/go/rfc7231#section-6.3.6)
    RESET_CONTENT(205, "Reset Content"),

    // [RFC7233, Section 4.1](http://www.iana.org/go/rfc7233#section-4.1)
    PARTIAL_CONTENT(206, "Partial Content"),

    // [RFC4918](http://www.iana.org/go/rfc4918)
    MULTI_STATUS(207, "Multi-Status"),

    // [RFC5842](http://www.iana.org/go/rfc5842)
    ALREADY_REPORTED(208, "Already Reported"),

    // [RFC3229](http://www.iana.org/go/rfc3229)
    IM_USED(226, "IM Used"),

    // [RFC7231, Section 6.4.1](http://www.iana.org/go/rfc7231#section-6.4.1)
    MULTIPLE_CHOICES(300, "Multiple Choices"),

    // [RFC7231, Section 6.4.2](http://www.iana.org/go/rfc7231#section-6.4.2)
    MOVED_PERMANENTLY(301, "Moved Permanently"),

    // [RFC7231, Section 6.4.3](http://www.iana.org/go/rfc7231#section-6.4.3)
    FOUND(302, "Found"),

    // [RFC7231, Section 6.4.4](http://www.iana.org/go/rfc7231#section-6.4.4)
    SEE_OTHER(303, "See Other"),

    // [RFC7232, Section 4.1](http://www.iana.org/go/rfc7232#section-4.1)
    NOT_MODIFIED(304, "Not Modified"),

    // [RFC7231, Section 6.4.5](http://www.iana.org/go/rfc7231#section-6.4.5)
    USE_PROXY(305, "Use Proxy"),

    // [RFC7231, Section 6.4.7](http://www.iana.org/go/rfc7231#section-6.4.7)
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),

    // [RFC7538](http://www.iana.org/go/rfc7538)
    PERMANENT_REDIRECT(308, "Permanent Redirect"),

    // [RFC7231, Section 6.5.1](http://www.iana.org/go/rfc7231#section-6.5.1)
    BAD_REQUEST(400, "Bad Request"),

    // [RFC7235, Section 3.1](http://www.iana.org/go/rfc7235#section-3.1)
    UNAUTHORIZED(401, "Unauthorized"),

    // [RFC7231, Section 6.5.2](http://www.iana.org/go/rfc7231#section-6.5.2)
    PAYMENT_REQUIRED(402, "Payment Required"),

    // [RFC7231, Section 6.5.3](http://www.iana.org/go/rfc7231#section-6.5.3)
    FORBIDDEN(403, "Forbidden"),

    // [RFC7231, Section 6.5.4](http://www.iana.org/go/rfc7231#section-6.5.4)
    NOT_FOUND(404, "Not Found"),

    // [RFC7231, Section 6.5.5](http://www.iana.org/go/rfc7231#section-6.5.5)
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

    // [RFC7231, Section 6.5.6](http://www.iana.org/go/rfc7231#section-6.5.6)
    NOT_ACCEPTABLE(406, "Not Acceptable"),

    // [RFC7235, Section 3.2](http://www.iana.org/go/rfc7235#section-3.2)
    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),

    // [RFC7231, Section 6.5.7](http://www.iana.org/go/rfc7231#section-6.5.7)
    REQUEST_TIMEOUT(408, "Request Timeout"),

    // [RFC7231, Section 6.5.8](http://www.iana.org/go/rfc7231#section-6.5.8)
    CONFLICT(409, "Conflict"),

    // [RFC7231, Section 6.5.9](http://www.iana.org/go/rfc7231#section-6.5.9)
    GONE(410, "Gone"),

    // [RFC7231, Section 6.5.10](http://www.iana.org/go/rfc7231#section-6.5.10)
    LENGTH_REQUIRED(411, "Length Required"),

    // [RFC7232, Section 4.2](http://www.iana.org/go/rfc7232#section-4.2)
    // [RFC8144, Section 3.2](http://www.iana.org/go/rfc8144#section-3.2)
    PRECONDITION_FAILED(412, "Precondition Failed"),

    // [RFC7231, Section 6.5.11](http://www.iana.org/go/rfc7231#section-6.5.11)
    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),

    // [RFC7231, Section 6.5.12](http://www.iana.org/go/rfc7231#section-6.5.12)
    URI_TOO_LONG(414, "URI Too Long"),

    // [RFC7231, Section 6.5.13](http://www.iana.org/go/rfc7231#section-6.5.13)
    // [RFC7694, Section 3](http://www.iana.org/go/rfc7694#section-3)
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),

    // [RFC7233, Section 4.4](http://www.iana.org/go/rfc7233#section-4.4)
    RANGE_NOT_SATISFIABLE(416, "Range Not Satisfiable"),

    // [RFC7231, Section 6.5.14](http://www.iana.org/go/rfc7231#section-6.5.14)
    EXPECTATION_FAILED(417, "Expectation Failed"),

    // [RFC 2324](http://www.iana.org/go/rfc2324)
    IM_A_TEAPOT(418, "I'm A Teapot"),

    // [RFC7540, Section 9.1.2](http://www.iana.org/go/rfc7540#section-9.1.2)
    MISDIRECTED_REQUEST(421, "Misdirected Request"),

    // [RFC4918](http://www.iana.org/go/rfc4918)
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),

    // [RFC4918](http://www.iana.org/go/rfc4918)
    LOCKED(423, "Locked"),

    // [RFC4918](http://www.iana.org/go/rfc4918)
    FAILED_DEPENDENCY(424, "Failed Dependency"),

    // [RFC8470](http://www.iana.org/go/rfc8470)
    TOO_EARLY(425, "Too Early"),

    // [RFC7231, Section 6.5.15](http://www.iana.org/go/rfc7231#section-6.5.15)
    UPGRADE_REQUIRED(426, "Upgrade Required"),

    // [RFC6585](http://www.iana.org/go/rfc6585)
    PRECONDITION_REQUIRED(428, "Precondition Required"),

    // [RFC6585](http://www.iana.org/go/rfc6585)
    TOO_MANY_REQUESTS(429, "Too Many Requests"),

    // [RFC6585](http://www.iana.org/go/rfc6585)
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),

    // [RFC7725](http://www.iana.org/go/rfc7725)
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),

    // [RFC7231, Section 6.6.1](http://www.iana.org/go/rfc7231#section-6.6.1)
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    // [RFC7231, Section 6.6.2](http://www.iana.org/go/rfc7231#section-6.6.2)
    NOT_IMPLEMENTED(501, "Not Implemented"),

    // [RFC7231, Section 6.6.3](http://www.iana.org/go/rfc7231#section-6.6.3)
    BAD_GATEWAY(502, "Bad Gateway"),

    // [RFC7231, Section 6.6.4](http://www.iana.org/go/rfc7231#section-6.6.4)
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),

    // [RFC7231, Section 6.6.5](http://www.iana.org/go/rfc7231#section-6.6.5)
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),

    // [RFC7231, Section 6.6.6](http://www.iana.org/go/rfc7231#section-6.6.6)
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported"),

    // [RFC2295](http://www.iana.org/go/rfc2295)
    VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),

    // [RFC4918](http://www.iana.org/go/rfc4918)
    INSUFFICIENT_STORAGE(507, "Insufficient Storage"),

    // [RFC5842](http://www.iana.org/go/rfc5842)
    LOOP_DETECTED(508, "Loop Detected"),
    BANDWIDTH_LIMIT_EXCEEDED(509, "Bandwidth Limit Exceeded"),

    // [RFC2774](http://www.iana.org/go/rfc2774)
    NOT_EXTENDED(510, "Not Extended"),

    // [RFC6585](http://www.iana.org/go/rfc6585)
    NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required"),

    ;

    private int code;
    private String message;

    private HttpStatus(int code, String message)
    {
        setCode(code);
        setMessage(message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
