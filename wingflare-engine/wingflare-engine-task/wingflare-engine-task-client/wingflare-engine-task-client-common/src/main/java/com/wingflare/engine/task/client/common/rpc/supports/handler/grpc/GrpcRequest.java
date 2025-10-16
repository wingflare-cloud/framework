package com.wingflare.engine.task.client.common.rpc.supports.handler.grpc;


import com.wingflare.engine.task.client.common.rpc.supports.http.HttpRequest;
import com.wingflare.engine.task.client.common.rpc.supports.http.HttpResponse;
import com.wingflare.engine.task.common.core.grpc.auto.TaskGrpcRequest;

/**
 * netty客户端请求模型
 *
 * @author: opensnail
 * @date : 2023-07-24 09:32
 */
public class GrpcRequest {

    private TaskGrpcRequest jobRequest;
    private final HttpResponse httpResponse;
    private final HttpRequest httpRequest;

    public GrpcRequest(HttpResponse httpResponse, HttpRequest httpRequest) {
        this.httpResponse = httpResponse;
        this.httpRequest = httpRequest;
    }

    public TaskGrpcRequest getJobRequest() {
        return jobRequest;
    }

    public void setJobRequest(TaskGrpcRequest jobRequest) {
        this.jobRequest = jobRequest;
    }

    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    public HttpRequest getHttpRequest() {
        return httpRequest;
    }
}
