package com.wingflare.engine.task.server.web.model.request;

import com.wingflare.engine.task.server.common.vo.base.BaseQueryVO;

/**
 * @author: xiaowoniu
 * @date : 2023-11-21 15:21
 * @since : 2.5.0
 */
public class NamespaceQueryVO extends BaseQueryVO {

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
