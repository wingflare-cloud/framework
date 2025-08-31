declare namespace Workflow {
  /** 组 */
  export type NodeDataType = {
    /** 流程ID */
    id?: string;
    /** 负责人 */
    ownerId?: string;
    /** 工作流名称 */
    workflowName?: string;
    /** 组名称 */
    groupName?: string;
    /** 告警通知场景ids */
    notifyIds?: number[];
    /** 触发类型 */
    triggerType?: Api.Common.TriggerType;
    /** 触发间隔 */
    triggerInterval?: string;
    /** 阻塞策略 */
    blockStrategy?: Api.Common.BlockStrategy;
    /** 执行超时时间 */
    executorTimeout?: number;
    /** 方法参数 */
    wfContext?: string;
    wfContexts?: { key: string; value: string | number | boolean; type: string }[];
    /** 工作流状态 */
    workflowStatus?: Api.Common.WorkFlowNodeStatus;
    /** 工作流批次状态 */
    workflowBatchStatus?: Api.Common.TaskBatchStatus;
    /** 描述 */
    description?: string;
    /** 流程配置 */
    nodeConfig?: NodeModelType;
  };

  /** 节点 */
  type NodeModelType = {
    /** 节点名称 */
    nodeName?: string;
    /** 节点类型 */
    nodeType?: number;
    /** 条件节点列表 */
    conditionNodes?: ConditionNodeType[];
    /** 子节点 */
    childNode?: NodeModelType;
  };

  /** 条件节点 */
  type ConditionNodeType = {
    id?: string;
    /** 节点名称 */
    nodeName?: string;
    /** 优先级 */
    priorityLevel?: number;
    /** 任务批次状态 */
    taskBatchStatus?: Api.Common.TaskBatchStatus;
    /** 任务执行时间 */
    executionAt?: string;
    /** 操作原因 */
    operationReason?: number;
    /** 失败策略 */
    failStrategy?: Api.Common.FailStrategy;
    /** 工作流状态 */
    workflowNodeStatus?: Api.Common.WorkFlowNodeStatus;
    /** 任务节点 */
    jobTask?: TaskNodeType;
    /** 任务实例列表 */
    jobBatchList?: {
      /** 任务实例 ID */
      id: string;
      /** 任务 ID */
      jobId: string;
      /** 任务批次状态 */
      taskBatchStatus: number;
    }[];
    /** 条件节点 */
    decision?: BrachNodeType;
    /** 回调节点 */
    callback?: CallbackNodeType;
    /** 子节点 */
    childNode?: NodeModelType;
  };

  /** 任务节点 */
  type TaskNodeType = {
    /** 任务ID */
    jobId?: string;
    /** 任务名称 */
    jobName?: string;
    /** 标签 */
    labels?: string;
  };

  /** 条件节点 */
  type BrachNodeType = {
    /** 条件节点表达式 */
    nodeExpression?: string;
    /** 模拟上下文 */
    checkContent?: string;
    checkContents?: { key: string; value: string | number | boolean; type: string }[];
    /** 表达式类型 */
    expressionType?: Api.Common.Expression;
    /** 判定逻辑 */
    logicalCondition?: Api.Common.LogicalCondition;
    /** 其他情况标识 */
    defaultDecision?: number;
  };

  /** 回调节点 */
  type CallbackNodeType = {
    /** webhook */
    webhook?: string;
    /** 请求类型 */
    contentType?: Api.Common.ContentType;
    /** 秘钥 */
    secret?: string;
  };

  /** 任务批次状态 */
  type TaskBatchStatusType = {
    /** 名称 */
    name: string;
    /** 标识 */
    title: string;
    /** 颜色 */
    color: string;
    /** 图标 */
    icon: string;
  };

  /** 定时任务详情 */
  export type JobTaskType = {
    /** 定时任务 ID */
    id?: string;
    /** 组名称 */
    groupName?: string;
    /** 任务信息 ID */
    jobId?: string;
    /** 任务名称 */
    jobName?: string;
    /** 节点名称 */
    nodeName?: string;
    /** 任务实例 ID */
    taskBatchId?: string;
    /** 状态 */
    jobStatus?: number;
    /** 状态 */
    taskBatchStatus?: Api.Common.TaskBatchStatus;
    /** 执行器类型 */
    executorType?: Api.Common.ExecutorType;
    /** 操作原因 */
    operationReason?: Api.Common.OperationReason;
    /** 开始执行时间 */
    executionAt?: string;
    /** 执行器名称 */
    executorInfo?: string;
    /** 创建时间 */
    createDt?: string;
    /** 工作流节点ID */
    workflowNodeId?: string;
    /** 工作流任务批次ID */
    workflowTaskBatchId?: string;
  };

  /** 任务项列表 */
  export type JobBatchType = {
    /** ID */
    id?: string;
    /** 任务 ID */
    jobId?: string;
    /** 组名称 */
    groupName?: string;
    /** 地址 */
    clientInfo?: string;
    /** 参数 */
    argsStr?: string;
    /** 结果 */
    resultMessage?: string;
    /** 重试次数 */
    retryCount?: string;
    /** 开始执行时间 */
    createDt?: string;
    /** 任务批次 ID */
    taskBatchId?: string;
    /** 任务状态 ID */
    taskStatus?: Api.Common.TaskBatchStatus;
  };

  /* export type JobBatchPage = {
    total: number;
    data: JobTaskType[];
  }; */

  /** 任务日志 */
  /* export type JobLogType = {}; */

  /** Tag */
  export type JobTagType = {
    [key: number | string]: {
      /** 名称 */
      name: App.I18n.I18nKey;
      /** 颜色 */
      color: string;
    };
  };
}

declare module 'vue-drag-resize/src';
