package com.wingflare.engine.task.server.web.model.response;

import com.wingflare.engine.task.common.model.response.base.RetryResponse;

import java.util.List;

/**
 * @author opensnail
 * @date 2022-02-27
 * @since 2.0
 */
public class RetryResponseWebVO extends RetryResponse {
    private List<RetryResponseWebVO> children;

    public List<RetryResponseWebVO> getChildren() {
        return children;
    }

    public void setChildren(List<RetryResponseWebVO> children) {
        this.children = children;
    }
}
