package com.wingflare.engine.task.server.common.dto;


import com.wingflare.engine.task.common.core.grpc.auto.GrpcResult;
import com.wingflare.engine.task.common.core.grpc.auto.SnailJobGrpcRequest;
import io.grpc.stub.StreamObserver;


/**
 * netty客户端请求模型
 *
 * @author: opensnail
 * @date : 2023-07-24 09:32
 */
public class GrpcRequest {

    private SnailJobGrpcRequest snailJobRequest;

    private StreamObserver<GrpcResult> streamObserver;

    private String uri;

    public SnailJobGrpcRequest getSnailJobRequest() {
        return snailJobRequest;
    }

    public void setSnailJobRequest(SnailJobGrpcRequest snailJobRequest) {
        this.snailJobRequest = snailJobRequest;
    }

    public StreamObserver<GrpcResult> getStreamObserver() {
        return streamObserver;
    }

    public void setStreamObserver(StreamObserver<GrpcResult> streamObserver) {
        this.streamObserver = streamObserver;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
