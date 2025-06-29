package com.wingflare.lib.standard.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * IdBo
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:09:10 CST 2025
 */
public class StringIdBo {

    @NotNull(message = "id.notNull")
    @NotBlank(message = "id.notBlank")
    private String id;

    public String getId() {
        return id;
    }

    public StringIdBo setId(String id) {
        this.id = id;
        return this;
    }
}
