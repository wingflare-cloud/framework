package com.wingflare.engine.task.server.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.server.common.exception.SnailJobServerException;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.NamespaceQueryVO;
import com.wingflare.engine.task.server.web.model.request.NamespaceRequestVO;
import com.wingflare.engine.task.server.web.model.response.NamespaceResponseVO;
import com.wingflare.engine.task.server.web.service.NamespaceService;
import com.wingflare.engine.task.server.web.service.convert.NamespaceResponseVOConverter;
import com.wingflare.task.datasource.template.persistence.mapper.GroupConfigMapper;
import com.wingflare.task.datasource.template.persistence.mapper.NamespaceMapper;
import com.wingflare.task.datasource.template.persistence.po.GroupConfig;
import com.wingflare.task.datasource.template.persistence.po.Namespace;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.DEFAULT_NAMESPACE;

/**
 * @author: xiaowoniu
 * @date : 2023-11-21 15:42
 * @since : 2.5.0
 */
@Service
public class NamespaceServiceImpl implements NamespaceService {
    private final NamespaceMapper namespaceMapper;
    private final GroupConfigMapper groupConfigMapper;

    public NamespaceServiceImpl(NamespaceMapper namespaceMapper, GroupConfigMapper groupConfigMapper) {
        this.namespaceMapper = namespaceMapper;
        this.groupConfigMapper = groupConfigMapper;
    }

    @Override
    public Boolean saveNamespace(final NamespaceRequestVO namespaceRequestVO) {
        String uniqueId = namespaceRequestVO.getUniqueId();

        if (StrUtil.isNotBlank(namespaceRequestVO.getUniqueId())) {
            Pattern pattern = Pattern.compile(SystemConstants.REGEXP);
            Matcher matcher = pattern.matcher(uniqueId);
            Assert.isTrue(matcher.matches(), () -> new SnailJobServerException("Only supports 1~64 characters, including numbers, letters, underscores, and hyphens"));

            Assert.isTrue(namespaceMapper.selectCount(
                            new LambdaQueryWrapper<Namespace>()
                                    .eq(Namespace::getUniqueId, namespaceRequestVO.getUniqueId())) == 0,
                    () -> new SnailJobServerException("Namespace already exists {}", namespaceRequestVO.getUniqueId()));
        }

        Namespace namespace = new Namespace();
        namespace.setName(namespaceRequestVO.getName());
        if (StrUtil.isBlank(uniqueId)) {
            namespace.setUniqueId(IdUtil.simpleUUID());
        } else {
            namespace.setUniqueId(uniqueId);
        }
        return 1 == namespaceMapper.insert(namespace);
    }

    @Override
    public Boolean updateNamespace(final NamespaceRequestVO namespaceRequestVO) {
        Long id = namespaceRequestVO.getId();
        Assert.notNull(id, () -> new SnailJobServerException("Parameter error"));

        Namespace namespace = new Namespace();
        namespace.setName(namespaceRequestVO.getName());
        namespace.setId(id);
        return 1 == namespaceMapper.updateById(namespace);
    }

    @Override
    public PageResult<List<NamespaceResponseVO>> getNamespacePage(final NamespaceQueryVO queryVO) {

        PageDTO<Namespace> pageDTO = new PageDTO<>(queryVO.getPage(), queryVO.getSize());
        String keywords = StrUtil.trim(queryVO.getKeyword());

        PageDTO<Namespace> selectPage = namespaceMapper.selectPage(pageDTO,
                new LambdaQueryWrapper<Namespace>()
                        .eq(Namespace::getDeleted, StatusEnum.NO.getStatus())
                        .and(StrUtil.isNotBlank(keywords), w ->
                                w.likeRight(Namespace::getName, keywords)
                                        .or().likeRight(Namespace::getUniqueId, keywords))
                        .orderByDesc(Namespace::getId));
        return new PageResult<>(pageDTO,
                NamespaceResponseVOConverter.INSTANCE.convertList(selectPage.getRecords()));
    }

    @Override
    public Boolean deleteByUniqueId(String uniqueId) {

        Assert.isFalse(DEFAULT_NAMESPACE.equals(uniqueId), ()-> new SnailJobServerException("Default space cannot be deleted"));

        Assert.isTrue(CollUtil.isEmpty(groupConfigMapper.selectList(new PageDTO<>(1, 1), new LambdaQueryWrapper<GroupConfig>()
                        .eq(GroupConfig::getNamespaceId, uniqueId).orderByAsc(GroupConfig::getId))),
                () -> new SnailJobServerException("There are undeleted group configuration tasks. Please delete the associated group configurations before retrying deletion"));

        Assert.isTrue(1 == namespaceMapper.delete(new LambdaQueryWrapper<Namespace>().eq(Namespace::getUniqueId, uniqueId)),
                () -> new SnailJobServerException("Failed to delete namespace"));

        return Boolean.TRUE;
    }

    @Override
    public List<NamespaceResponseVO> getAllNamespace() {
        List<Namespace> namespaces = namespaceMapper.selectList(
                new LambdaQueryWrapper<Namespace>()
                        .select(Namespace::getName, Namespace::getUniqueId)
                        .eq(Namespace::getDeleted, StatusEnum.NO.getStatus())
                        .orderByDesc(Namespace::getId)
        );
        return NamespaceResponseVOConverter.INSTANCE.convertList(namespaces);
    }
}
