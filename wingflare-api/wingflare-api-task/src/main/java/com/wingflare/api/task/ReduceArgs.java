package com.wingflare.api.task;


import java.util.List;

/**
 * Task执行结果
 *
 * @author: opensnail
 * @date : 2024-06-12 13:59
 */
public class ReduceArgs extends JobArgs {

    private List<?> mapResult;

    public List<?> getMapResult() {
        return mapResult;
    }

    public void setMapResult(List<?> mapResult) {
        this.mapResult = mapResult;
    }
}
