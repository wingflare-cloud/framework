import { $t } from '@/locales';
import { transformRecordToNumberOption } from '@/utils/common';

export const yesOrNoRecord: Record<Api.Common.YesOrNo, App.I18n.I18nKey> = {
  '0': 'common.yesOrNo.no',
  '1': 'common.yesOrNo.yes'
};

export const enableStatusNumberRecord: Record<Api.Common.EnableStatusNumber, App.I18n.I18nKey> = {
  0: 'common.status.disable',
  1: 'common.status.enable'
};

export const enableStatusNumberOptions = transformRecordToNumberOption(enableStatusNumberRecord, true);

export const roleTypeRecord: Record<number, string> = {
  1: 'R_USER',
  2: 'R_ADMIN'
};

export const podsType: Record<Api.Dashboard.DashboardPodsType, App.I18n.I18nKey> = {
  1: 'page.pods.type.client',
  2: 'page.pods.type.server'
};

export const nodeTypeOptions = transformRecordToNumberOption(podsType);

export const alarmTypeRecord: Record<Api.NotifyRecipient.AlarmType, App.I18n.I18nKey> = {
  1: 'page.notifyRecipient.dingDing',
  2: 'page.notifyRecipient.email',
  3: 'page.notifyRecipient.weCom',
  4: 'page.notifyRecipient.lark',
  5: 'page.notifyRecipient.webhook'
};
export const alarmTypeRecordOptions = transformRecordToNumberOption(alarmTypeRecord);

export const alarmWebhookTypeRecord: Record<Api.NotifyRecipient.AlarmTypeWebhook, App.I18n.I18nKey> = {
  1: 'page.notifyRecipient.form.applicationJson',
  2: 'page.notifyRecipient.form.applicationXWwwFormUrlencoded'
};
export const alarmWebhookTypeRecordOptions = transformRecordToNumberOption(alarmWebhookTypeRecord);

export const systemTaskType: Record<Api.NotifyConfig.SystemTaskType, App.I18n.I18nKey> = {
  1: 'common.systemTaskType.retry',
  // 2: 'common.systemTaskType.callback',
  3: 'common.systemTaskType.job',
  4: 'common.systemTaskType.workflow'
};
export const systemTaskTypeOptions = transformRecordToNumberOption(systemTaskType);

export const retryNotifyScene: Record<Api.NotifyConfig.RetryNotifyScene, App.I18n.I18nKey> = {
  1: 'page.notifyConfig.retryNotifyScene.maxRetry',
  2: 'page.notifyConfig.retryNotifyScene.maxRetryError',
  3: 'page.notifyConfig.retryNotifyScene.clientReportError',
  4: 'page.notifyConfig.retryNotifyScene.clientComponentError',
  5: 'page.notifyConfig.retryNotifyScene.retryTaskFailError',
  6: 'page.notifyConfig.retryNotifyScene.retryTaskEnterDeadLetter',
  7: 'page.notifyConfig.retryNotifyScene.retryNoClientNodesError'
};
export const retryNotifySceneOptions = transformRecordToNumberOption(retryNotifyScene);

export const jobNotifyScene: Record<Api.NotifyConfig.JobNotifyScene, App.I18n.I18nKey> = {
  1: 'page.notifyConfig.jobNotifyScene.jobTaskError',
  2: 'page.notifyConfig.jobNotifyScene.jobClientError',
  3: 'page.notifyConfig.jobNotifyScene.jobNoClientNodesError'
};
export const jobNotifySceneOptions = transformRecordToNumberOption(jobNotifyScene);

export const workflowNotifyScene: Record<Api.NotifyConfig.WorkflowNotifyScene, App.I18n.I18nKey> = {
  2: 'page.notifyConfig.workflowNotifyScene.workflowClientError',
  3: 'page.notifyConfig.workflowNotifyScene.workNoClientNodesError',
  100: 'page.notifyConfig.workflowNotifyScene.workTaskError'
};
export const workflowNotifySceneOptions = transformRecordToNumberOption(workflowNotifyScene);

export const groupConfigStatusRecord: Record<Api.GroupConfig.GroupStatusType, App.I18n.I18nKey> = {
  0: 'common.status.disable',
  1: 'common.status.enable'
};
export const groupConfigStatusOptions = transformRecordToNumberOption(groupConfigStatusRecord, true);

export const groupConfigIdModeRecord: Record<Api.GroupConfig.IdGeneratorModeType, App.I18n.I18nKey> = {
  1: 'page.groupConfig.idMode.segment',
  2: 'page.groupConfig.idMode.idWorker'
};
export const groupConfigIdModeOptions = transformRecordToNumberOption(groupConfigIdModeRecord);

export const groupConfigYesOrNoRecord: Record<Api.GroupConfig.YesOrNoType, App.I18n.I18nKey> = {
  0: 'common.yesOrNo.no',
  1: 'common.yesOrNo.yes'
};
export const groupConfigYesOrNoOptions = transformRecordToNumberOption(groupConfigYesOrNoRecord, true);

export const retryStatusTypeRecord: Record<Api.Retry.RetryStatusType, App.I18n.I18nKey> = {
  0: 'page.retry.retryStatusType.retrying',
  1: 'page.retry.retryStatusType.finished',
  2: 'page.retry.retryStatusType.maxRetry',
  3: 'page.retry.retryStatusType.paused'
};
export const retryStatusTypeOptions = transformRecordToNumberOption(retryStatusTypeRecord);

export const retryTaskStatusTypeRecord: Record<Api.Retry.RetryTaskStatusType, App.I18n.I18nKey> = {
  1: 'common.retryTaskStatus.items.waiting',
  2: 'common.retryTaskStatus.items.running',
  3: 'common.retryTaskStatus.items.success',
  4: 'common.retryTaskStatus.items.fail',
  5: 'common.retryTaskStatus.items.stop',
  6: 'common.retryTaskStatus.items.cancel'
};
export const retryTaskStatusTypeOptions = transformRecordToNumberOption(retryTaskStatusTypeRecord);

export const retryTaskTypeRecord: Record<Api.Retry.TaskType, App.I18n.I18nKey> = {
  1: 'page.retry.taskTypeDict.retry',
  2: 'page.retry.taskTypeDict.callback'
};
export const retryTaskTypeOptions = transformRecordToNumberOption(retryTaskTypeRecord);

export const backOffRecord: Record<Api.RetryScene.BackOff, App.I18n.I18nKey> = {
  1: 'page.retryScene.backOffItem.delayLevel',
  2: 'page.retryScene.backOffItem.fixed',
  3: 'page.retryScene.backOffItem.cron',
  4: 'page.retryScene.backOffItem.random'
};
export const backOffRecordOptions = transformRecordToNumberOption(backOffRecord, true);

export const routeKeyRecord: Record<Api.Common.RouteKey, App.I18n.I18nKey> = {
  1: 'common.routeKey.items.consistentHash',
  2: 'common.routeKey.items.random',
  3: 'common.routeKey.items.lru',
  4: 'common.routeKey.items.round',
  5: 'common.routeKey.items.first',
  6: 'common.routeKey.items.last'
};
export const routeKeyRecordOptions = transformRecordToNumberOption(routeKeyRecord, true);

/** 阻塞策略 */
export const blockStrategyRecord: Record<Api.Common.BlockStrategy, App.I18n.I18nKey> = {
  1: 'common.blockStrategy.items.discard',
  2: 'common.blockStrategy.items.overwrite',
  3: 'common.blockStrategy.items.parallel',
  4: 'common.blockStrategy.items.recovery'
};
export const blockStrategyRecordOptions = transformRecordToNumberOption(blockStrategyRecord);

/** 失败策略 */
export const failStrategyRecord: Record<Api.Common.FailStrategy, App.I18n.I18nKey> = {
  1: 'common.failStrategy.items.skip',
  2: 'common.failStrategy.items.blockage'
};

export const failStrategyOptions = transformRecordToNumberOption(failStrategyRecord);

/** 判定逻辑 */
export const logicalConditionRecord: Record<Api.Common.LogicalCondition, string> = {
  1: 'and',
  2: 'or'
};

export const logicalConditionOptions = transformRecordToNumberOption(logicalConditionRecord);

/** 表达式类型 */
export const expressionRecord: Record<Api.Common.Expression, string> = {
  1: 'SpEl',
  2: 'Aviator',
  3: 'QL'
};

export const expressionOptions = transformRecordToNumberOption(expressionRecord);

/** 请求类型 */
export const contentTypeRecord: Record<Api.Common.ContentType, string> = {
  1: 'application/json',
  2: 'application/x-www-form-urlencoded'
};

export const contentTypeOptions = transformRecordToNumberOption(contentTypeRecord);

/** 执行器类型 */
export const executorTypeRecord: Record<Api.Common.ExecutorType, App.I18n.I18nKey> = {
  1: 'common.executorType.items.java',
  2: 'common.executorType.items.python',
  3: 'common.executorType.items.go'
};
export const executorTypeRecordOptions = transformRecordToNumberOption(executorTypeRecord);

/** 任务类型 */
export const taskTypeRecord: Record<Api.Common.TaskType, App.I18n.I18nKey> = {
  1: 'common.taskType.items.cluster',
  2: 'common.taskType.items.broadcast',
  3: 'common.taskType.items.slice',
  4: 'common.taskType.items.map',
  5: 'common.taskType.items.mapreduce'
};
export const taskTypeRecordRecordOptions = transformRecordToNumberOption(taskTypeRecord);

/** 延迟等级 */
export const DelayLevel: Record<number, string> = {
  1: '10s',
  2: '15s',
  3: '30s',
  4: '35s',
  5: '40s',
  6: '50s',
  7: '1m',
  8: '2m',
  9: '4m',
  10: '6m',
  11: '8m',
  12: '10m',
  13: '20m',
  14: '40m',
  15: '1h',
  16: '2h',
  17: '3h',
  18: '4h',
  19: '5h',
  20: '6h',
  21: '7h',
  22: '8h',
  23: '9h',
  24: '10h',
  25: '11h',
  26: '12h'
};

export const triggerTypeRecord: Record<Api.Job.TriggerType, App.I18n.I18nKey> = {
  2: 'page.jobTask.triggerTypeItem.fixed',
  3: 'page.jobTask.triggerTypeItem.cron',
  5: 'page.jobTask.triggerTypeItem.specifiedTime',
  // 只会在定时任务中使用
  99: 'page.jobTask.triggerTypeItem.workflow'
};

export const triggerTypeOptions = transformRecordToNumberOption(triggerTypeRecord);

export const workflowTriggerTypeOptions = transformRecordToNumberOption(triggerTypeRecord).filter(
  item => item.value !== 99
);

export const taskBatchStatusRecord: Record<Api.Common.TaskBatchStatus, App.I18n.I18nKey> = {
  1: 'common.taskBatchStatus.items.waiting',
  2: 'common.taskBatchStatus.items.running',
  3: 'common.taskBatchStatus.items.success',
  4: 'common.taskBatchStatus.items.fail',
  5: 'common.taskBatchStatus.items.stop',
  6: 'common.taskBatchStatus.items.cancel',
  98: 'common.taskBatchStatus.items.decisionFailed',
  99: 'common.taskBatchStatus.items.skip'
};
export const taskBatchStatusRecordOptions = transformRecordToNumberOption(taskBatchStatusRecord);

export const taskStatusRecord: Record<Api.Common.TaskStatus, App.I18n.I18nKey> = {
  2: 'common.taskStatus.items.running',
  3: 'common.taskStatus.items.success',
  4: 'common.taskStatus.items.fail',
  5: 'common.taskStatus.items.stop',
  6: 'common.taskStatus.items.cancel'
};
export const taskStatusRecordOptions = transformRecordToNumberOption(taskStatusRecord);

export const operationReasonRecord: Record<Api.Common.OperationReason, App.I18n.I18nKey> = {
  0: 'common.jobOperationReason.items.none',
  1: 'common.jobOperationReason.items.taskExecutionTimeout',
  2: 'common.jobOperationReason.items.notClient',
  3: 'common.jobOperationReason.items.closed',
  4: 'common.jobOperationReason.items.discard',
  5: 'common.jobOperationReason.items.overlay',
  6: 'common.jobOperationReason.items.notExecutionTask',
  7: 'common.jobOperationReason.items.taskExecutionError',
  8: 'common.jobOperationReason.items.mannerStop',
  9: 'common.jobOperationReason.items.workflowConditionNodeExecutionError',
  10: 'common.jobOperationReason.items.jobTaskInterrupted',
  11: 'common.jobOperationReason.items.workflowCallbackNodeExecutionError',
  12: 'common.jobOperationReason.items.workflowNodeNoRequired',
  13: 'common.jobOperationReason.items.workflowNodeClosedSkipExecution',
  14: 'common.jobOperationReason.items.workflowDecisionFailed'
};
export const operationReasonOptions = transformRecordToNumberOption(operationReasonRecord);

export const retryOperationReasonRecord: Record<Api.Common.RetryOperationReason, App.I18n.I18nKey> = {
  0: 'common.retryOperationReason.items.none',
  1: 'common.retryOperationReason.items.taskExecutionTimeout',
  2: 'common.retryOperationReason.items.notClient',
  3: 'common.retryOperationReason.items.closed',
  4: 'common.retryOperationReason.items.discard',
  5: 'common.retryOperationReason.items.overlay',
  6: 'common.retryOperationReason.items.taskExecutionError',
  7: 'common.retryOperationReason.items.mannerStop',
  8: 'common.retryOperationReason.items.noRunningRetry',
  9: 'common.retryOperationReason.items.sceneClosed',
  10: 'common.retryOperationReason.items.retryFail',
  11: 'common.retryOperationReason.items.clientTriggerRetryStop'
};
export const retryOperationReasonOptions = transformRecordToNumberOption(retryOperationReasonRecord);

export const jobOperationReasonEnum: Workflow.JobTagType = {
  0: {
    name: operationReasonRecord[0],
    color: '#f5f5f5'
  },
  1: {
    name: operationReasonRecord[1],
    color: '#64a6ea'
  },
  2: {
    name: operationReasonRecord[2],
    color: '#1b7ee5'
  },
  3: {
    name: operationReasonRecord[3],
    color: '#087da1'
  },
  4: {
    name: operationReasonRecord[4],
    color: '#3a2f81'
  },
  5: {
    name: operationReasonRecord[5],
    color: '#c2238a'
  },
  6: {
    name: operationReasonRecord[6],
    color: '#23c28a'
  },
  7: {
    name: operationReasonRecord[7],
    color: '#bdc223'
  },
  8: {
    name: operationReasonRecord[8],
    color: '#23c28a'
  },
  9: {
    name: operationReasonRecord[9],
    color: '#23c28a'
  },
  10: {
    name: operationReasonRecord[10],
    color: '#bdc223'
  },
  11: {
    name: operationReasonRecord[11],
    color: '#bdc223'
  },
  12: {
    name: operationReasonRecord[12],
    color: '#23c28a'
  },
  13: {
    name: operationReasonRecord[13],
    color: '#3a2f81'
  },
  14: {
    name: operationReasonRecord[14],
    color: '#b63f1a'
  }
};

export const roleRecord: Record<Api.UserManager.Role, App.I18n.I18nKey> = {
  1: 'page.userManager.roleItem.user',
  2: 'page.userManager.roleItem.admin'
};
export const roleRecordOptions = transformRecordToNumberOption(roleRecord);

export const workFlowNodeStatusRecord: Record<Api.Common.WorkFlowNodeStatus, App.I18n.I18nKey> = {
  0: 'common.workFlowNodeStatus.items.close',
  1: 'common.workFlowNodeStatus.items.open'
};

export const workFlowNodeStatusOptions = transformRecordToNumberOption(workFlowNodeStatusRecord);

export const jobStatusEnum: Workflow.JobTagType = {
  0: {
    name: 'common.workFlowNodeStatus.items.close',
    color: '#dc3f41'
  },
  1: {
    name: 'common.workFlowNodeStatus.items.open',
    color: '#1b7ee5'
  }
};

export const taskBatchStatusEnum: Record<Api.Common.TaskBatchStatus, Workflow.TaskBatchStatusType> = {
  1: {
    title: $t('common.taskBatchStatus.items.waiting'),
    name: 'waiting',
    color: '#64a6ea',
    icon: 'ant-design:warning-outlined'
  },
  2: {
    title: $t('common.taskBatchStatus.items.running'),
    name: 'running',
    color: '#1b7ee5',
    icon: 'ant-design:clock-circle-outlined'
  },
  3: {
    title: $t('common.taskBatchStatus.items.success'),
    name: 'success',
    color: '#087da1',
    icon: 'ant-design:check-circle-outlined'
  },
  4: {
    title: $t('common.taskBatchStatus.items.fail'),
    name: 'fail',
    color: '#f52d80',
    icon: 'ant-design:close-circle-outlined'
  },
  5: {
    title: $t('common.taskBatchStatus.items.stop'),
    name: 'stop',
    color: '#ac2df5',
    icon: 'ant-design:stop-outlined'
  },
  6: {
    title: $t('common.taskBatchStatus.items.cancel'),
    name: 'cancel',
    color: '#f5732d',
    icon: 'mdi:cancel'
  },
  98: {
    title: $t('common.taskBatchStatus.items.decisionFailed'),
    name: 'decision-failed',
    color: '#b63f1a',
    icon: 'ant-design:close-circle-outlined'
  },
  99: {
    title: $t('common.taskBatchStatus.items.skip'),
    name: 'skip',
    color: '#999999a6',
    icon: 'mdi:transit-skip'
  }
};

export const jobExecutorEnum: Workflow.JobTagType = {
  1: {
    name: 'common.executorType.items.java',
    color: '#d06892'
  }
};
