package com.wingflare.engine.task.server.job.dto;


/**
 * @author opensnail
 * @date 2024-06-25 22:58:05
 * @since sj_1.1.0
 */
public class MapReduceArgsStrDTO {

    private Integer shardNum;

    private String argsStr;

    public Integer getShardNum() {
        return shardNum;
    }

    public void setShardNum(Integer shardNum) {
        this.shardNum = shardNum;
    }

    public String getArgsStr() {
        return argsStr;
    }

    public void setArgsStr(String argsStr) {
        this.argsStr = argsStr;
    }
}
