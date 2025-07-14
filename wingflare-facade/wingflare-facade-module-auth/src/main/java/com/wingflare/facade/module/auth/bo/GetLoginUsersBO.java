package com.wingflare.facade.module.auth.bo;

import com.wingflare.lib.core.validation.MustUserId;

import jakarta.validation.constraints.*;

import java.math.BigInteger;

/**
 * @ClassName LoginBo
 * @Author naizui_ycx
 * @Date 2025/01/23
 * @Description 获取当前系统用户登录信息
 */
public class GetLoginUsersBO {

    @Min(value = 1L)
    @Max(value = 200L)
    private long pageSize = 50;

    private long startIndex = 0;

    @NotNull(message = "id.notNull", groups = MustUserId.class)
    @Min(message = "id.error", value = 1, groups = MustUserId.class)
    private BigInteger userId;

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(long startIndex) {
        this.startIndex = startIndex;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }
}
