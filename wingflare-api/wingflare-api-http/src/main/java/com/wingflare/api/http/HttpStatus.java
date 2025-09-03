package com.wingflare.api.http;

/**
 * HTTP状态码枚举类，定义了常见的HTTP状态码及其标准英文描述
 * 状态码分类：
 * 1xx: Informational - Server is processing the request
 * 2xx: Success - Request was successfully processed
 * 3xx: Redirection - Further action is needed to complete the request
 * 4xx: Client Error - Request contains errors or cannot be fulfilled
 * 5xx: Server Error - Server encountered an error while processing the request
 */
public enum HttpStatus {
    // 1xx Informational
    CONTINUE(100, "Continue"),
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),
    PROCESSING(102, "Processing"),
    EARLY_HINTS(103, "Early Hints"),

    // 2xx Success
    OK(200, "OK"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted"),
    NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),
    NO_CONTENT(204, "No Content"),
    RESET_CONTENT(205, "Reset Content"),
    PARTIAL_CONTENT(206, "Partial Content"),
    MULTI_STATUS(207, "Multi-Status"),
    ALREADY_REPORTED(208, "Already Reported"),
    IM_USED(226, "IM Used"),

    // 3xx Redirection
    MULTIPLE_CHOICES(300, "Multiple Choices"),
    MOVED_PERMANENTLY(301, "Moved Permanently"),
    FOUND(302, "Found"),
    SEE_OTHER(303, "See Other"),
    NOT_MODIFIED(304, "Not Modified"),
    USE_PROXY(305, "Use Proxy"),
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),
    PERMANENT_REDIRECT(308, "Permanent Redirect"),

    // 4xx Client Error
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    PAYMENT_REQUIRED(402, "Payment Required"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
    REQUEST_TIMEOUT(408, "Request Timeout"),
    CONFLICT(409, "Conflict"),
    GONE(410, "Gone"),
    LENGTH_REQUIRED(411, "Length Required"),
    PRECONDITION_FAILED(412, "Precondition Failed"),
    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
    URI_TOO_LONG(414, "URI Too Long"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    RANGE_NOT_SATISFIABLE(416, "Range Not Satisfiable"),
    EXPECTATION_FAILED(417, "Expectation Failed"),
    I_AM_A_TEAPOT(418, "I'm a teapot"),
    MISDIRECTED_REQUEST(421, "Misdirected Request"),
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
    LOCKED(423, "Locked"),
    FAILED_DEPENDENCY(424, "Failed Dependency"),
    TOO_EARLY(425, "Too Early"),
    UPGRADE_REQUIRED(426, "Upgrade Required"),
    PRECONDITION_REQUIRED(428, "Precondition Required"),
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),

    // 5xx Server Error
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported"),
    VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),
    INSUFFICIENT_STORAGE(507, "Insufficient Storage"),
    LOOP_DETECTED(508, "Loop Detected"),
    NOT_EXTENDED(510, "Not Extended"),
    NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required");

    private final int code;
    private final String reasonPhrase;

    HttpStatus(int code, String reasonPhrase) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

    /**
     * Get the HTTP status code
     * @return status code value
     */
    public int getCode() {
        return code;
    }

    /**
     * Get the reason phrase of the status code
     * @return reason phrase
     */
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    /**
     * Get the corresponding enum value by status code
     * @param code status code value
     * @return corresponding enum value, or null if not found
     */
    public static HttpStatus getByCode(int code) {
        for (HttpStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }

    /**
     * Check if the status code is informational (1xx)
     * @return true if it's an informational status code, false otherwise
     */
    public boolean isInformational() {
        return code >= 100 && code < 200;
    }

    /**
     * Check if the status code is a success code (2xx)
     * @return true if it's a success status code, false otherwise
     */
    public boolean isSuccess() {
        return code >= 200 && code < 300;
    }

    /**
     * Check if the status code is a redirection code (3xx)
     * @return true if it's a redirection status code, false otherwise
     */
    public boolean isRedirection() {
        return code >= 300 && code < 400;
    }

    /**
     * Check if the status code is a client error code (4xx)
     * @return true if it's a client error status code, false otherwise
     */
    public boolean isClientError() {
        return code >= 400 && code < 500;
    }

    /**
     * Check if the status code is a server error code (5xx)
     * @return true if it's a server error status code, false otherwise
     */
    public boolean isServerError() {
        return code >= 500 && code < 600;
    }
}
