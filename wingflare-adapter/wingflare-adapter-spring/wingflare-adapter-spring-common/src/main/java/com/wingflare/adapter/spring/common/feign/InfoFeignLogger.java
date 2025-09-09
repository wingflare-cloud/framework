package com.wingflare.adapter.spring.common.feign;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;

import java.io.IOException;

/**
 * feign日志
 * 2022-11-09
 * ycx
 */
public class InfoFeignLogger extends Logger {

    private final org.slf4j.Logger logger;

    private final String CRLF = "\r\n";

    public InfoFeignLogger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        this.logger.info(methodTag(configKey) + format);
    }

    protected void log(String configKey, boolean newline, String type, String format) {
        String logText;
        if (newline) {
            logText = methodTag(configKey) + "[" + type + "]\n" + format;
        } else {
            logText = methodTag(configKey) + "[" + type + "] " + format;
        }

        this.logger.info(logText);
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        if (this.logger.isDebugEnabled()) {
            String protocolVersion = resolveProtocolVersion(request.protocolVersion());
            StringBuilder rawHttpMessage = new StringBuilder(String.format(
                    "%s %s %s %s",
                    request.httpMethod().name(),
                    request.url(),
                    protocolVersion,
                    CRLF
            ));

            if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {
                for (String field : request.headers().keySet()) {
                    if (shouldLogRequestHeader(field)) {
                        for (String value : Util.valuesOrEmpty(request.headers(), field)) {
                            rawHttpMessage.append(String.format("%s: %s%s", field, value, CRLF));
                        }
                    }
                }

                int bodyLength = 0;
                if (request.body() != null) {
                    bodyLength = request.length();
                    if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                        String bodyText =
                                request.charset() != null
                                        ? new String(request.body(), request.charset())
                                        : null;
                        rawHttpMessage.append(CRLF)
                                .append(bodyText != null ? bodyText : "Binary data");
                    }
                }
                log(configKey, true, "request", rawHttpMessage.toString());
                log(configKey, false, "request-body-byte", String.valueOf(bodyLength));
            }
        }
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime)
            throws IOException {

        if (this.logger.isDebugEnabled()) {
            String protocolVersion = resolveProtocolVersion(response.protocolVersion());
            String reason = response.reason() != null && logLevel.compareTo(Level.NONE) > 0 ? " " + response.reason()
                    : "";
            int status = response.status();
            StringBuilder rawHttpMessage = new StringBuilder(
                    String.format("%s %s%s%s", protocolVersion, status, reason, CRLF)
            );
            log(configKey, false, "elapsedTime", String.format("%sms", elapsedTime));
            if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {

                for (String field : response.headers().keySet()) {
                    if (shouldLogResponseHeader(field)) {
                        for (String value : Util.valuesOrEmpty(response.headers(), field)) {
                            rawHttpMessage.append(String.format("%s: %s%s", field, value, CRLF));
                        }
                    }
                }

                int bodyLength = 0;
                if (response.body() != null && !(status == 204 || status == 205)) {
                    // HTTP 204 No Content "...response MUST NOT include a message-body"
                    // HTTP 205 Reset Content "...response MUST NOT include an entity"
                    byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                    bodyLength = bodyData.length;
                    if (logLevel.ordinal() >= Level.FULL.ordinal() && bodyLength > 0) {
                        rawHttpMessage
                                .append(CRLF)
                                .append(Util.decodeOrDefault(bodyData, Util.UTF_8, "Binary data"));
                    }
                    log(configKey, true, "response", rawHttpMessage.toString());
                    log(configKey, false, "response-body-byte", String.valueOf(bodyLength));
                    return response.toBuilder().body(bodyData).build();
                } else {
                    log(configKey, true, "response", rawHttpMessage.toString());
                    log(configKey, false, "response-body-byte", String.valueOf(bodyLength));
                }
            }
        }

        return response;
    }

}
