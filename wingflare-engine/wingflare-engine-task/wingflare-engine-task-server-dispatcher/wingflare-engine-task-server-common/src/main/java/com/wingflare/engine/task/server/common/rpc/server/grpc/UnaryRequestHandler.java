package com.wingflare.engine.task.server.common.rpc.server.grpc;

import com.wingflare.engine.task.common.core.grpc.auto.GrpcResult;
import com.wingflare.engine.task.common.core.grpc.auto.Metadata;
import com.wingflare.engine.task.common.core.grpc.auto.SnailJobGrpcRequest;
import com.wingflare.engine.task.server.common.dto.GrpcRequest;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.lib.core.Builder;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;
import org.apache.pekko.actor.ActorRef;

/**
 * @author: opensnail
 * @date : 2024-08-22
 */
public class UnaryRequestHandler implements ServerCalls.UnaryMethod<SnailJobGrpcRequest, GrpcResult>{

    @Override
    public void invoke(final SnailJobGrpcRequest snailJobRequest, final StreamObserver<GrpcResult> streamObserver) {
        Metadata metadata = snailJobRequest.getMetadata();

        GrpcRequest grpcRequest = Builder.of(GrpcRequest::new)
                .with(GrpcRequest::setUri, metadata.getUri())
                .with(GrpcRequest::setSnailJobRequest, snailJobRequest)
                .with(GrpcRequest::setStreamObserver, streamObserver)
                .build();

                ActorRef actorRef = ActorGenerator.requestGrpcHandlerActor();
        actorRef.tell(grpcRequest, actorRef);
    }
}
