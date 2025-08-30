package com.wingflare.engine.task.server.retry.support.generator.retry;

import com.wingflare.engine.task.common.core.enums.RetryStatusEnum;
import com.wingflare.engine.task.server.common.enums.TaskGeneratorSceneEnum;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 控制台手动单个新增
 *
 * @author opensnail
 * @date 2023-07-16 11:51:56
 * @since 2.1.0
 */
@Component
public class ManaSingleRetryGenerator extends AbstractGenerator {
    @Override
    public boolean supports(int scene) {
        return TaskGeneratorSceneEnum.MANA_SINGLE.getScene() == scene;
    }

    @Override
    protected Integer initStatus(TaskContext taskContext) {
        return Optional.ofNullable(taskContext.getInitStatus()).orElse(RetryStatusEnum.RUNNING.getStatus());
    }
}
