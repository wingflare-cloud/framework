package com.wingflare.lib.spring.utils;

import com.wingflare.lib.core.Snowflake;
import com.wingflare.lib.core.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author naizui_ycx
 * @date {2021/12/30}
 * @description
 */
@Component
public class SnowflakeUtil {

    @Value("${system.snowflake.datacenterId:1}")
    private long datacenterId = 1l;

    @Value("${system.snowflake.machineId:1}")
    private long machineId = 1l;

    private static volatile Snowflake snowflake;

    /**
     * 多线程环境下高效创建Snowflake单例实例方法
     *
     * @param datacenterId
     * @param machineId
     *
     * @return
     */
    private static Snowflake getSnowflake(long datacenterId, long machineId){
        if (snowflake == null) {
            synchronized (SnowflakeUtil.class) {
                if (snowflake == null) {
                    snowflake = new Snowflake(datacenterId, machineId);
                }
            }
        }

        return snowflake;
    }

    private Snowflake getSnowflake() {
        return getSnowflake(datacenterId, machineId);
    }


    public long nextLongId() {
        return getSnowflake().nextId();
    }


    public String nextStringId()
    {
        return ConvertUtil.toStr(nextLongId());
    }

}
