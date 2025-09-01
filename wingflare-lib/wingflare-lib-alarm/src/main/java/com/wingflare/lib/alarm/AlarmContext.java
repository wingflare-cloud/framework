package com.wingflare.lib.alarm;


import org.slf4j.helpers.MessageFormatter;

/**
 * @author: opensnail
 * @date : 2021-11-25 09:38
 */
public class AlarmContext {

    private String text;

    private String title;

    private String notifyAttribute;

    public AlarmContext text(String pattern, Object... arguments) {
        this.text = MessageFormatter.arrayFormat(pattern, arguments).getMessage();
        return this;
    }

    public AlarmContext text(String text) {
        this.text = text;
        return this;
    }

    public AlarmContext notifyAttribute(String notifyAttribute) {
        this.notifyAttribute = notifyAttribute;
        return this;
    }

    public AlarmContext title(String pattern, Object... arguments) {
        this.title = MessageFormatter.arrayFormat(pattern, arguments).getMessage();
        return this;
    }

    public AlarmContext title(String title) {
        this.title = title;
        return this;
    }

    public static AlarmContext build() {
        return new AlarmContext();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotifyAttribute() {
        return notifyAttribute;
    }

    public void setNotifyAttribute(String notifyAttribute) {
        this.notifyAttribute = notifyAttribute;
    }
}
