package com.wingflare.engine.task.server.common.enums;

import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import org.apache.pekko.actor.ActorRef;

import java.util.function.Supplier;

/**
 * @author opensnail
 * @date 2023-06-04
 * @since 2.0
 */
public enum SyetemTaskTypeEnum {
    RETRY(1, ActorGenerator::scanRetryActor),
    CALLBACK(2, ActorGenerator::scanCallbackGroupActor),
    JOB(3, ActorGenerator::scanJobActor),
    WORKFLOW(4, ActorGenerator::scanWorkflowActor),
    ;

    private final Integer type;
    private final Supplier<ActorRef> actorRef;

    SyetemTaskTypeEnum(Integer type, Supplier<ActorRef> actorRef) {
        this.type = type;
        this.actorRef = actorRef;
    }

    public Integer getType() {
        return type;
    }

    public Supplier<ActorRef> getActorRef() {
        return actorRef;
    }
}
