package com.wingflare.facade.module.auth.bo;

import com.wingflare.lib.core.validation.MustUserId;

import jakarta.validation.constraints.*;

/**
 * @ClassName LoginBo
 * @Author naizui_ycx
 * @Date 2025/01/23
 * @Description 获取当前系统用户登录信息
 */
public class GetLoginUsersBo {

    @Min(value = 1L)
    @Max(value = 200L)
    private long pageSize = 50;

    private long startIndex = 0;

    @NotNull(message = "id.notNull", groups = MustUserId.class)
    @NotBlank(message = "id.notBlank", groups = MustUserId.class)
    @Pattern(regexp = "^$|^[0-9a-zA-Z]{1,32}$", message = "id.formatError", groups = MustUserId.class)
    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
