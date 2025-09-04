package com.wingflare.engine.task.server.web.service.convert;

import cn.hutool.core.collection.CollUtil;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.server.web.model.request.NotifyConfigRequestVO;
import com.wingflare.engine.task.datasource.template.persistence.po.NotifyConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Set;

/**
 * @author: opensnail
 * @date : 2021-11-26 13:43
 */
@Mapper
public interface NotifyConfigConverter {

    NotifyConfigConverter INSTANCE = Mappers.getMapper(NotifyConfigConverter.class);

    static String toNotifyRecipientIdsStr(Set<Long> notifyRecipientIds) {
        if (CollUtil.isEmpty(notifyRecipientIds)) {
            return null;
        }

        return JsonUtil.toJsonString(notifyRecipientIds);
    }

    @Mappings({
            @Mapping(target = "recipientIds", expression = "java(NotifyConfigConverter.toNotifyRecipientIdsStr(notifyConfigVO.getRecipientIds()))")
    })
    NotifyConfig convert(NotifyConfigRequestVO notifyConfigVO);
}
