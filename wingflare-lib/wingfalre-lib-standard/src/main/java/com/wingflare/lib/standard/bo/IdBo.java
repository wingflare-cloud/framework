package com.wingflare.lib.standard.bo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * IdBo
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:09:10 CST 2023
 */
public class IdBo
{

    @NotNull(message = "id.notNull")
    @NotBlank(message = "id.notBlank")
    @Pattern(regexp = "^$|^[0-9a-zA-Z]{1,32}$", message = "id.formatError")
    private String id;

    public IdBo setId(String id)
    {
        this.id = id;
        return this;
    }

    public String getId()
    {
        return id;
    }

}
