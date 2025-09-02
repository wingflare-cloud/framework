package com.wingflare.lib.email;


import org.slf4j.helpers.MessageFormatter;

import java.io.Serial;

/**
 * 邮件异常
 *
 * @author xiaoleilu
 */
public class MailException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 8247610319171014183L;

    public MailException(Throwable e) {
        super(e);
    }

    public MailException(String message) {
        super(message);
    }

    public MailException(String messageTemplate, Object... params) {
        super(MessageFormatter.arrayFormat(messageTemplate, params).getMessage());
    }

    public MailException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public MailException(String message, Throwable throwable, boolean enableSuppression, boolean writableStackTrace) {
        super(message, throwable, enableSuppression, writableStackTrace);
    }

    public MailException(Throwable throwable, String messageTemplate, Object... params) {
        super(MessageFormatter.arrayFormat(messageTemplate, params).getMessage(), throwable);
    }
}
