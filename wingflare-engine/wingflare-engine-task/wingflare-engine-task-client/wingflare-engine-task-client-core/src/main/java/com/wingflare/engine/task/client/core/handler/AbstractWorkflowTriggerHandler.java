package com.wingflare.engine.task.client.core.handler;

import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.model.request.WorkflowTriggerApiRequest;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractWorkflowTriggerHandler<H, R> extends AbstractJobRequestHandler<R> {

    private final WorkflowTriggerApiRequest reqDTO;

    private H r;

    public AbstractWorkflowTriggerHandler(Long workflowId) {
        this.reqDTO = new WorkflowTriggerApiRequest();
       // 设置调度id
        reqDTO.setWorkflowId(workflowId);
        reqDTO.setJobId(workflowId);
    }

    public WorkflowTriggerApiRequest getReqDTO() {
        return reqDTO;
    }

    public H getR() {
        return r;
    }

    public void setR(H r) {
        this.r = r;
    }

    /**
     * 添加上下文
     *
     * @param argsKey   参数名
     * @param argsValue 参数值
     * @return
     */
    protected H addContext(String argsKey, Object argsValue) {
        Map<String, Object> map = new HashMap<>();
        if (StrUtil.isNotBlank(reqDTO.getTmpWfContext())) {
            map = JsonUtil.parseHashMap(reqDTO.getTmpWfContext());
        }
        map.put(argsKey, argsValue);
        reqDTO.setTmpWfContext(JsonUtil.toJsonString(map));
        reqDTO.setTmpArgsStr(JsonUtil.toJsonString(map));
        return r;
    }

    protected abstract void afterExecute(Boolean aBoolean);
}
