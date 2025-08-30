package com.wingflare.engine.task.server.web.model.response;


/**
 * @author: opensnail
 * @date : 2024-04-20
 * @since : sj_1.0.0
 */
public class CommonLabelValueResponseVO {

    private String label;

    private Long value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
