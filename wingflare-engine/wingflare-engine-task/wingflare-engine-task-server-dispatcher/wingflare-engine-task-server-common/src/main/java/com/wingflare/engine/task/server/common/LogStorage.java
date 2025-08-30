package com.wingflare.engine.task.server.common;

import com.wingflare.engine.task.common.log.dto.LogContentDTO;
import com.wingflare.engine.task.common.log.enums.LogTypeEnum;
import com.wingflare.engine.task.server.common.dto.LogMetaDTO;

/**
 * @author: xiaowoniu
 * @date : 2024-03-22
 * @since : 3.2.0
 */
public interface LogStorage {

    LogTypeEnum logType();

    void storage(final LogContentDTO logContentDTO, final LogMetaDTO logMetaDTO);
}
