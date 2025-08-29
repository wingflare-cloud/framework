package com.wingflare.engine.task.client.core.dto;


import java.util.List;

/**
 * Task执行结果
 *
 * @author: opensnail
 * @date : 2024-06-12 13:59
 */
public class MergeReduceArgs extends JobArgs {

    private List<?> reduces;

    public List<?> getReduces() {
        return reduces;
    }

    public void setReduces(List<?> reduces) {
        this.reduces = reduces;
    }
}
