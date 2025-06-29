package com.wingflare.lib.standard.bo;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;

/**
 * IdBo
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:09:10 CST 2023
 */
public class IdBo
{

    @NotNull(message = "id.notNull")
    @Min(message = "id.error", value = 1)
    private BigInteger id;

    public IdBo setId(BigInteger id)
    {
        this.id = id;
        return this;
    }

    public BigInteger getId()
    {
        return id;
    }

}
