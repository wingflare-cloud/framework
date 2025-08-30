package com.wingflare.engine.task.client.common.log.report;

import com.wingflare.engine.task.client.common.RpcClient;
import com.wingflare.engine.task.client.common.rpc.client.RequestBuilder;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.window.Listener;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.common.model.request.LogTaskRequest;

import java.util.List;

/**
 * 批量异步上报
 *
 * @author: opensnail
 * @date : 2022-03-08 13:54
 * @since 1.0.0
 */
public class ReportLogListener implements Listener<LogTaskRequest> {

    private static final RpcClient CLIENT = RequestBuilder.<RpcClient, TaskRpcResult>newBuilder()
            .client(RpcClient.class)
            .callback(rpcResult -> SnailJobLog.LOCAL.info("Data report log successfully requestId:[{}]",
                    rpcResult.getReqId())).build();

    @Override
    public void handler(List<LogTaskRequest> list) {

        CLIENT.reportLogTask(list);
    }
}
