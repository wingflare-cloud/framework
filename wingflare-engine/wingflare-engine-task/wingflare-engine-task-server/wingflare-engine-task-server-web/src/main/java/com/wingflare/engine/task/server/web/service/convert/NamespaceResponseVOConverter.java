package com.wingflare.engine.task.server.web.service.convert;

import com.wingflare.engine.task.server.web.model.response.NamespaceResponseVO;
import com.wingflare.engine.task.datasource.template.persistence.po.Namespace;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: xiaowoniu
 * @date : 2023-11-21 16:20
 * @since : 2.5.0
 */
@Mapper
public interface NamespaceResponseVOConverter {

    NamespaceResponseVOConverter INSTANCE = Mappers.getMapper(NamespaceResponseVOConverter.class);

    List<NamespaceResponseVO> convertList(List<Namespace> namespaces);
}
