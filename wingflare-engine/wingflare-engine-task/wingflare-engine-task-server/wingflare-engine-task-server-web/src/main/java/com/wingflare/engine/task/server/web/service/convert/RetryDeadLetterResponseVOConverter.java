package com.wingflare.engine.task.server.web.service.convert;

import com.wingflare.engine.task.server.web.model.response.RetryDeadLetterResponseVO;
import com.wingflare.engine.task.datasource.template.persistence.po.RetryDeadLetter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: opensnail
 * @date : 2022-02-28 15:29
 */
@Mapper
public interface RetryDeadLetterResponseVOConverter {

    RetryDeadLetterResponseVOConverter INSTANCE = Mappers.getMapper(RetryDeadLetterResponseVOConverter.class);

    RetryDeadLetterResponseVO convert(RetryDeadLetter retryDeadLetter);

    List<RetryDeadLetterResponseVO> convertList(List<RetryDeadLetter> retryDeadLetters);
}
