package com.wingflare.engine.task.client.core.handler.add;

import com.wingflare.engine.task.client.core.enums.AllocationAlgorithmEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;

/**
 * @author opensnail
 * @date 2024-10-19 12:25:49
 * @since sj_1.2.0
 */
public class MapAddHandler extends AddHandler<MapAddHandler> {

    public MapAddHandler() {
        this(JobTaskTypeEnum.MAP);
    }

    public MapAddHandler(JobTaskTypeEnum taskType) {
        super(taskType);
        setRouteKey(AllocationAlgorithmEnum.ROUND);
        setR(this);
    }

    @Override
    public MapAddHandler addArgsStr(String argsKey, Object argsValue) {
        return super.addArgsStr(argsKey, argsValue);
    }

    @Override
    public MapAddHandler setParallelNum(Integer parallelNum) {
        return super.setParallelNum(parallelNum);
    }
}
