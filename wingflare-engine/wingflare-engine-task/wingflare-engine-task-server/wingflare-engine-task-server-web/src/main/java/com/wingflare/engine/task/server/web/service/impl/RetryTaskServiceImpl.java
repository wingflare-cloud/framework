package com.wingflare.engine.task.server.web.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.wingflare.api.event.EventPublisher;
import com.wingflare.engine.task.common.core.enums.RetryOperationReasonEnum;
import com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.constant.LogFieldConstants;
import com.wingflare.engine.task.common.model.request.RetryArgsDeserializeRequest;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.retry.convert.RetryConverter;
import com.wingflare.engine.task.server.retry.dto.TaskStopJobDTO;
import com.wingflare.engine.task.server.retry.support.handler.RetryTaskStopHandler;
import com.wingflare.engine.task.server.service.handler.RetryArgsDeserializeHandler;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.event.WsSendEvent;
import com.wingflare.engine.task.server.web.model.request.RetryTaskLogMessageQueryVO;
import com.wingflare.engine.task.server.web.model.request.RetryTaskQueryVO;
import com.wingflare.engine.task.server.web.model.request.UserSessionVO;
import com.wingflare.engine.task.server.web.model.response.RetryResponseWebVO;
import com.wingflare.engine.task.server.web.model.response.RetryTaskResponseVO;
import com.wingflare.engine.task.server.web.service.RetryTaskService;
import com.wingflare.engine.task.server.web.service.convert.RetryTaskLogResponseVOConverter;
import com.wingflare.engine.task.server.web.service.convert.RetryTaskResponseVOConverter;
import com.wingflare.engine.task.server.web.timer.LogTimerWheel;
import com.wingflare.engine.task.server.web.timer.RetryTaskLogTimerTask;
import com.wingflare.engine.task.server.web.util.UserSessionUtils;
import com.wingflare.engine.task.datasource.template.access.AccessTemplate;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.common.PageResponseDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.log.RetryTaskLogMessageDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.log.RetryTaskLogMessageQueryDO;
import com.wingflare.engine.task.datasource.template.persistence.mapper.RetryMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.RetryTaskLogMessageMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.RetryTaskMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.Retry;
import com.wingflare.engine.task.datasource.template.persistence.po.RetryTask;
import com.wingflare.engine.task.datasource.template.persistence.po.RetryTaskLogMessage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.wingflare.lib.container.Container;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


/**
 * @author: opensnail
 * @date : 2022-02-28 09:10
 */
@Service
public class RetryTaskServiceImpl implements RetryTaskService {
    private static final Long DELAY_MILLS = 5000L;
    private final RetryTaskMapper retryTaskMapper;
    private final RetryMapper retryMapper;
    private final RetryTaskLogMessageMapper retryTaskLogMessageMapper;
    private final RetryTaskStopHandler retryTaskStopHandler;
    private final AccessTemplate accessTemplate;
    private final RetryArgsDeserializeHandler retryArgsDeserializeHandler;

    public RetryTaskServiceImpl(RetryTaskMapper retryTaskMapper, RetryMapper retryMapper, RetryTaskLogMessageMapper retryTaskLogMessageMapper,
                                RetryTaskStopHandler retryTaskStopHandler, AccessTemplate accessTemplate,
                                RetryArgsDeserializeHandler retryArgsDeserializeHandler) {
        this.retryTaskMapper = retryTaskMapper;
        this.retryMapper = retryMapper;
        this.retryTaskLogMessageMapper = retryTaskLogMessageMapper;
        this.retryTaskStopHandler = retryTaskStopHandler;
        this.accessTemplate = accessTemplate;
        this.retryArgsDeserializeHandler = retryArgsDeserializeHandler;
    }

    @Override
    public PageResult<List<RetryTaskResponseVO>> getRetryTaskLogPage(RetryTaskQueryVO queryVO) {
        PageDTO<RetryTask> pageDTO = new PageDTO<>(queryVO.getPage(), queryVO.getSize());

        UserSessionVO userSessionVO = UserSessionUtils.currentUserSession();
        String namespaceId = userSessionVO.getNamespaceId();

        List<String> groupNames = UserSessionUtils.getGroupNames(queryVO.getGroupName());

        LambdaQueryWrapper<RetryTask> wrapper = new LambdaQueryWrapper<RetryTask>()
                .eq(RetryTask::getNamespaceId, namespaceId)
                .in(CollUtil.isNotEmpty(groupNames), RetryTask::getGroupName, groupNames)
                .eq(StrUtil.isNotBlank(queryVO.getSceneName()), RetryTask::getSceneName, queryVO.getSceneName())
                .eq(queryVO.getTaskStatus() != null, RetryTask::getTaskStatus, queryVO.getTaskStatus())
                .eq(Objects.nonNull(queryVO.getRetryId()), RetryTask::getRetryId, queryVO.getRetryId())
                .between(ObjUtil.isNotNull(queryVO.getDatetimeRange()),
                        RetryTask::getCreateDt, queryVO.getStartDt(), queryVO.getEndDt())
                .select(RetryTask::getGroupName, RetryTask::getId, RetryTask::getSceneName, RetryTask::getTaskStatus,
                        RetryTask::getCreateDt, RetryTask::getUpdateDt, RetryTask::getTaskType, RetryTask::getOperationReason, RetryTask::getRetryId)
                .orderByDesc(RetryTask::getCreateDt);

        PageDTO<RetryTask> retryTaskPageDTO = retryTaskMapper.selectPage(pageDTO, wrapper);
        return new PageResult<>(retryTaskPageDTO,
                RetryTaskLogResponseVOConverter.INSTANCE.convertList(retryTaskPageDTO.getRecords()));

    }

    @Override
    public void getRetryTaskLogMessagePage(RetryTaskLogMessageQueryVO queryVO) {
        String sid = queryVO.getSid();
        RetryTaskLogMessageQueryDO pageQueryDO = new RetryTaskLogMessageQueryDO();
        pageQueryDO.setPage(1);
        pageQueryDO.setSize(50);
        pageQueryDO.setRetryTaskId(queryVO.getRetryTaskId());
        pageQueryDO.setStartRealTime(queryVO.getStartRealTime());
        pageQueryDO.setSearchCount(true);
        // 拉取数据
        PageResponseDO<RetryTaskLogMessageDO> pageResponseDO = accessTemplate.getRetryTaskLogMessageAccess()
                .listPage(pageQueryDO);

        long total = pageResponseDO.getTotal();

        int totalPage = (int) ((total + queryVO.getSize() - 1) / queryVO.getSize());

        Long lastRealTime = 0L;

        if (0 == totalPage &&
                (null != pageQueryDO.getStartRealTime() && 0 != pageQueryDO.getStartRealTime())){
            lastRealTime = pageQueryDO.getStartRealTime();
        }

        for (int i = 1; i <= totalPage;) {
            for (RetryTaskLogMessageDO retryTaskLogMessageDO : pageResponseDO.getRows()) {
                // 发生日志内容到前端
                String message = retryTaskLogMessageDO.getMessage();
                List<Map<String, String>> logContents = JsonUtil.parseObject(message, List.class);
                logContents = logContents.stream()
                        .sorted(Comparator.comparingLong(o -> Long.parseLong(o.get(LogFieldConstants.TIME_STAMP))))
                        .toList();
                for (Map<String, String> logContent : logContents) {
                    // send发消息
                    WsSendEvent sendEvent = new WsSendEvent(this);
                    sendEvent.setSid(sid);
                    sendEvent.setMessage(JsonUtil.toJsonString(logContent));
                    Container.get(EventPublisher.class).publishEvent(sendEvent);
                }
            }

            // 继续查询下一页
            pageQueryDO.setSearchCount(false);
            pageQueryDO.setPage(++i);
            pageResponseDO = accessTemplate.getRetryTaskLogMessageAccess()
                    .listPage(pageQueryDO);
        }

        RetryTask retryTask = retryTaskMapper.selectOne(
                new LambdaQueryWrapper<RetryTask>().eq(RetryTask::getId, queryVO.getRetryTaskId()));

        if (Objects.isNull(retryTask)
                || (RetryTaskStatusEnum.TERMINAL_STATUS_SET.contains(retryTask.getTaskStatus()) &&
                retryTask.getUpdateDt().plusSeconds(15).isBefore(LocalDateTime.now()))) {
            // 发生完成标识
            WsSendEvent sendEvent = new WsSendEvent(this);
            sendEvent.setMessage("END");
            sendEvent.setSid(sid);
            Container.get(EventPublisher.class).publishEvent(sendEvent);
        } else {
            // 覆盖作为下次查询的起始条件
            queryVO.setStartRealTime(lastRealTime);
            scheduleNextAttempt(queryVO, sid);
        }
    }

    /**
     * 使用时间轮5秒再进行日志查询
     *
     * @param queryVO
     * @param sid
     */
    private void scheduleNextAttempt(RetryTaskLogMessageQueryVO queryVO, String sid) {
        LogTimerWheel.registerWithJobTaskLog(() -> new RetryTaskLogTimerTask(queryVO, sid), Duration.ofMillis(DELAY_MILLS));
    }

    @Override
    public RetryTaskResponseVO getRetryTaskById(Long id) {
        RetryTask retryTask = retryTaskMapper.selectById(id);

        if (retryTask == null) {
            return null;
        }

        Retry retry = retryMapper.selectById(retryTask.getRetryId());
        RetryTaskResponseVO responseVO = RetryTaskLogResponseVOConverter.INSTANCE.convert(retryTask);

        RetryResponseWebVO retryResponseWebVO = RetryTaskResponseVOConverter.INSTANCE.convert(retry);
        RetryArgsDeserializeRequest retryArgsDeserializeRequest = new RetryArgsDeserializeRequest();
        retryArgsDeserializeRequest.setArgsStr(retry.getArgsStr());
        retryArgsDeserializeRequest.setExecutorName(retry.getExecutorName());
        retryArgsDeserializeRequest.setScene(retry.getSceneName());
        retryArgsDeserializeRequest.setGroup(retry.getGroupName());
        retryArgsDeserializeRequest.setSerializerName(retry.getSerializerName());
        retryArgsDeserializeRequest.setNamespaceId(retry.getNamespaceId());
        retryResponseWebVO.setArgsStr(retryArgsDeserializeHandler.deserialize(retryArgsDeserializeRequest));

        responseVO.setResponseVO(retryResponseWebVO);
        return responseVO;
    }

    @Override
    @Transactional
    public boolean deleteById(final Long id) {
        String namespaceId = UserSessionUtils.currentUserSession().getNamespaceId();

        RetryTask retryTask = retryTaskMapper.selectOne(
                new LambdaQueryWrapper<RetryTask>()
                        .in(RetryTask::getTaskStatus, RetryTaskStatusEnum.TERMINAL_STATUS_SET)
                        .eq(RetryTask::getNamespaceId, namespaceId)
                        .eq(RetryTask::getId, id));
        Assert.notNull(retryTask, () -> new TaskServerException("Data deletion failed"));

        retryTaskLogMessageMapper.delete(new LambdaQueryWrapper<RetryTaskLogMessage>()
                .eq(RetryTaskLogMessage::getNamespaceId, namespaceId)
                .eq(RetryTaskLogMessage::getGroupName, retryTask.getGroupName())
                .eq(RetryTaskLogMessage::getRetryTaskId, id)
        );

        return 1 == retryTaskMapper.deleteById(id);
    }

    @Override
    @Transactional
    public boolean batchDelete(final Set<Long> ids) {
        String namespaceId = UserSessionUtils.currentUserSession().getNamespaceId();

        List<RetryTask> retryTasks = retryTaskMapper.selectList(
                new LambdaQueryWrapper<RetryTask>()
                        .in(RetryTask::getTaskStatus, RetryTaskStatusEnum.TERMINAL_STATUS_SET)
                        .eq(RetryTask::getNamespaceId, namespaceId)
                        .in(RetryTask::getId, ids));
        Assert.notEmpty(retryTasks, () -> new TaskServerException("Data does not exist"));
        Assert.isTrue(retryTasks.size() == ids.size(), () -> new TaskServerException("Data does not exist"));

        for (final RetryTask retryTask : retryTasks) {
            retryTaskLogMessageMapper.delete(
                    new LambdaQueryWrapper<RetryTaskLogMessage>()
                            .eq(RetryTaskLogMessage::getNamespaceId, namespaceId)
                            .eq(RetryTaskLogMessage::getGroupName, retryTask.getGroupName())
                            .eq(RetryTaskLogMessage::getRetryTaskId, retryTask.getId()));
        }
        return 1 == retryTaskMapper.deleteByIds(ids);
    }

    @Override
    public Boolean stopById(Long id) {

        RetryTask retryTask = retryTaskMapper.selectById(id);
        Assert.notNull(retryTask, () -> new TaskServerException("No executable tasks"));

        Retry retry = retryMapper.selectById(retryTask.getRetryId());
        Assert.notNull(retry, () -> new TaskServerException("Task does not exist"));

        TaskStopJobDTO taskStopJobDTO = RetryConverter.INSTANCE.toTaskStopJobDTO(retry);
        taskStopJobDTO.setOperationReason(RetryOperationReasonEnum.MANNER_STOP.getReason());
        taskStopJobDTO.setNeedUpdateTaskStatus(true);
        taskStopJobDTO.setMessage("User manually triggered stop");
        retryTaskStopHandler.stop(taskStopJobDTO);

        return true;
    }
}
