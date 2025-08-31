/**
 * Namespace Api
 *
 * All backend api type
 */
declare namespace Api {
  namespace Common {
    /** common params of paginating */
    interface PaginatingCommonParams {
      /** page size */
      size: number;
      /** total count */
      total: number;
      /** current page number */
      page: number;
    }

    /** common params of paginating query list data */
    interface PaginatingQueryRecord<T = any> extends PaginatingCommonParams {
      data: T[];
      status: number;
    }

    /** common page record */
    type CommonPageRecord<T> = {
      data: T[];
      page: number;
      size: number;
      status: number;
      total: number;
    };

    /** common search params of table */
    type CommonSearchParams = Pick<Common.PaginatingCommonParams, 'page' | 'size'>;

    /**
     * enable status
     *
     * - "1": enabled
     * - "2": disabled
     */
    type EnableStatus = '1' | '2';

    /**
     * enable status
     *
     * - 0: enabled
     * - 1: disabled
     */
    type EnableStatusNumber = 0 | 1;

    /**
     * yes/no status
     *
     * - "0": no
     * - "1": yes
     */
    type YesOrNo = '0' | '1';

    /** common record */
    type CommonRecord<T = any> = {
      /** record id */
      id?: string;
      /** record creator */
      createBy?: string;
      /** record create time */
      createDt?: string;
      /** record updater */
      updateBy?: string;
      /** record update time */
      updateDt?: string;
      /** record status */
      status?: EnableStatus | null;
    } & T;

    /** 1: 一致性Hash 2: 随机 3: LRU 4: 轮询 5: 第一个 6: 最后一个 */
    type RouteKey = 1 | 2 | 3 | 4 | 5 | 6;

    /** 阻塞策略 1:丢弃 2:覆盖 3:并行 4:恢复 */
    type BlockStrategy = 1 | 2 | 3 | 4;

    /** 阻塞策略 1:丢弃 2:覆盖 3:并行 */
    type RetryBlockStrategy = 1 | 2 | 3 | 4;

    /** 失败策略 1:跳过 2:阻塞 */
    type FailStrategy = 1 | 2;

    /** 判定逻辑 1:and 2:or */
    type LogicalCondition = 1 | 2;

    /** 表达式类型 1:SpEl 2:Aviator 3:QL */
    type Expression = 1 | 2 | 3;

    /** 请求类型 1:application/json 2:application/x-www-form-urlencoded */
    type ContentType = 1 | 2;

    /** 工作流节点状态 */
    type WorkFlowNodeStatus = 0 | 1;

    /** 执行器类型 1:Java 2:Python 3:Go */
    type ExecutorType = 1 | 2 | 3;

    /** 触发类型 2:固定时间 3:CRON 表达式 5:指定时间点 99:工作流 */
    type TriggerType = 2 | 3 | 5 | 99;

    /** 任务类型 1:集群 2:广播 3:切片 4:Map 5:MapReduce */
    type TaskType = 1 | 2 | 3 | 4 | 5;

    /** 1、待处理 2、运行中 3、成功 4、失败 5、停止 6、取消 */
    type TaskBatchStatus = 1 | 2 | 3 | 4 | 5 | 6 | 98 | 99;

    /** 2、处理中 3、处理成功 4、处理失败、5、任务停止 6、取消 */
    type TaskStatus = 2 | 3 | 4 | 5 | 6;

    /**
     * 1、任务执行超时 2、无客户端节点 3、JOB已关闭 4、任务丢弃 5、任务被覆盖 6、无可执行任务项 7、任务执行期间发生非预期异常 8、手动停止 9、条件节点执行异常 10、任务中断 11、回调节点执行异常 12、无需处理
     * 13、节点关闭跳过执行 14、判定未通过
     */
    type OperationReason = 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14;

    /**
     * 1、任务执行超时 2、无客户端节点 3、JOB已关闭 4、任务丢弃 5、任务被覆盖 6、无可执行任务项 7、任务执行期间发生非预期异常 8、手动停止 8、当前重试非运行中 9、当前场景已关闭 10、重试失败
     * 11、客户端触发任务停止
     */
    type RetryOperationReason = 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11;
  }

  /**
   * namespace Auth
   *
   * backend api module: "auth"
   */
  namespace Auth {
    interface LoginToken {
      id: string;
      mode: string;
      role: string;
      token: string;
      refreshToken: string;
      createDt: string;
      updateDt: string;
      namespaceIds: NamespaceId[];
    }

    type DeleteAlertType = 'job-task' | 'retry-scene' | 'workflow-task';

    interface UserInfo {
      id: string;
      userId: string;
      username: string;
      userName: string;
      mode: string;
      role: number;
      roles: string[];
      buttons: string[];
      namespaceIds: NamespaceId[];
      deleteAlert: {
        [key: string]: boolean;
      };
    }

    interface NamespaceId {
      id: string;
      name: string;
      uniqueId: string;
    }
  }

  /**
   * namespace Route
   *
   * backend api module: "route"
   */
  namespace Route {
    type ElegantConstRoute = import('@elegant-router/types').ElegantConstRoute;

    interface MenuRoute extends ElegantConstRoute {
      id: string;
    }

    interface UserRoute {
      routes: MenuRoute[];
      home: import('@elegant-router/types').LastLevelRouteKey;
    }
  }

  /**
   * namespace Dashboard
   *
   * backend api module: "dashboard"
   */
  namespace Dashboard {
    type CommonSearchParams = Pick<Common.PaginatingCommonParams, 'page' | 'size'>;

    /** Task Retry Job */
    type CardCount = {
      jobTask: JobTask;
      workFlowTask: JobTask;
      retryTask: RetryTask;
      retryTaskBarList: RetryTaskBarList[];
      onLineService: OnlineService;
    };

    type OnlineService = {
      total: number;
      clientTotal: number;
      serverTotal: number;
    };

    type RetryTaskBarList = {
      x?: string;
      taskTotal?: number;
    };

    type RetryTask = {
      totalNum: number;
      runningNum: number;
      finishNum: number;
      maxCountNum: number;
      suspendNum: number;
    };

    type JobTask = {
      successNum: number;
      failNum: number;
      cancelNum: number;
      stopNum: number;
      totalNum: number;
      successRate: number;
    };

    /** Dashboard Line */
    type DashboardLine = {
      taskList: TaskList;
      rankList: RankList[];
      dashboardLineResponseDOList: DashboardLineResponseDO[];
    };

    type DashboardLineResponseDO = {
      createDt: string;
      total: number;
    } & DashboardLineJob &
      DashboardLineRetry;

    type DashboardLineJob = {
      createDt: string;
      total: number;
      fail: number;
      stop: number;
      cancel: number;
      success: number;
    };

    type DashboardLineRetry = {
      createDt: string;
      total: number;
      successNum: number;
      runningNum: number;
      maxCountNum: number;
      suspendNum: number;
    };

    type RankList = {
      name: string;
      total: string;
    };

    type TaskList = {
      status: number;
      data: Task[];
      page: number;
      size: number;
      total: number;
    };

    type Task = {
      groupName: string;
      run: number;
      total: number;
    };

    /**
     * dashboard line type
     *
     * - "DAY": "今日"
     * - "WEEK": "最近一周"
     * - "MONTH": "最近一月"
     * - "YEAR": "全年"
     * - "OTHERS": "自选日期"
     */
    type DashboardLineType = 'DAY' | 'WEEK' | 'MONTH' | 'YEAR' | 'OTHERS';

    /**
     * dashboard line mode
     *
     * - "JOB": "job"
     * - "WORKFLOW": "workflow"
     */
    type DashboardLineMode = 'JOB' | 'WORKFLOW';

    type TaskType = 'JOB' | 'RETRY' | 'WORKFLOW';

    type DashboardLineParams = {
      groupName?: string;
      type: DashboardLineType;
      mode?: DashboardLineMode;
      datetimeRange?: [string, string] | null;
    } & CommonSearchParams;

    /**
     * dashboard line mode
     *
     * - "1": "client"
     * - "2": "server"
     */
    type DashboardPodsType = 1 | 2;

    /** dashboard list */
    type DashboardPodList = Common.PaginatingQueryRecord<DashboardPod>;

    /** dashboard pod */
    type DashboardPod = {
      id: string;
      /** 路径/组 */
      consumerBuckets: number[];
      /** context path */
      contextPath: string;
      /** 创建时间 */
      createDt: string;
      /** ext attrs */
      extAttrs: string;
      /** 组名称 */
      groupName: string;
      /** labels */
      labels?: string;
      labelMap?: { key: string; value: string }[];
      /** host id */
      hostId: string;
      /** host IP */
      hostIp: string;
      /** host port */
      hostPort: string;
      /** 类型 */
      nodeType: DashboardPodsType;
      /** 更新时间 */
      updateDt: string;
      systemVersion: string;
      executorType: string;
      nodeStatus: number;
    };

    /** dashboard search params */
    type DashboardPodsParams = CommonType.RecordNullable<
      Pick<Api.Dashboard.DashboardPod, 'groupName'> & CommonSearchParams
    >;

    /** dashboard pods operate params */
    type DashboardPodsOperateParams = CommonType.RecordNullable<Pick<Api.Dashboard.DashboardPod, 'id' | 'labels'>>;

    type UpdatePodsStatus = {
      id: number;
      serverNodeStatus: number;
    };
  }

  /**
   * namespace Namespace
   *
   * backend api module: "Namespace"
   */
  namespace Namespace {
    type CommonSearchParams = Pick<Common.PaginatingCommonParams, 'page' | 'size'>;

    /** namespace */
    type Namespace = Common.CommonRecord<{
      /** 主键 */
      id?: string;
      /** 名称 */
      name?: string;
      /** UniqueId */
      uniqueId: string;
    }>;

    /** namespace search params */
    type NamespaceSearchParams = CommonType.RecordNullable<{ keyword: string } & CommonSearchParams>;

    /** namespace list */
    type NamespaceList = Common.PaginatingQueryRecord<Namespace>;
  }

  namespace GroupConfig {
    type CommonSearchParams = Pick<Common.PaginatingCommonParams, 'page' | 'size'>;

    type IdGeneratorModeType = 1 | 2;

    type GroupStatusType = 0 | 1;

    type YesOrNoType = 0 | 1;

    /** groupConfig */
    type GroupConfig = Common.CommonRecord<{
      /** 命名空间id */
      namespaceId?: string;
      /** 命名空间名称 */
      namespaceName?: string;
      /** 组名 */
      groupName: string;
      /** 组描述 */
      description: string;
      /** token */
      token: string;
      /** 组状态 0、未启用 1、启用 */
      groupStatus: GroupStatusType;
      /** 版本号 */
      version?: number;
      /** 分区 */
      groupPartition: number;
      /** 唯一id生成模式 默认号段模式 */
      idGeneratorMode: IdGeneratorModeType;
      /** 是否初始化场景 0:否 1:是 */
      initScene: YesOrNoType;
      /** bucket */
      bucketIndex?: number;
    }>;

    /** groupConfig search params */
    type GroupConfigSearchParams = CommonType.RecordNullable<
      Pick<Api.GroupConfig.GroupConfig, 'groupName' | 'groupStatus'> & CommonSearchParams
    >;

    /** export groupConfig */
    type ExportGroupConfig = Common.CommonRecord<{
      groupIds: string[];
    }> &
      CommonType.RecordNullable<Pick<Api.GroupConfig.GroupConfig, 'groupName' | 'groupStatus'>>;

    type GroupConfigRequestVO = {
      groupName: string;
      groupStatus: GroupStatusType;
      token?: string;
      description?: string;
      groupPartition?: number;
      idGeneratorMode?: IdGeneratorModeType;
      initScene?: YesOrNoType;
    };

    /** groupConfig list */
    type GroupConfigList = Common.PaginatingQueryRecord<GroupConfig>;
  }

  /**
   * namespace SystemManage
   *
   * backend api module: "systemManage"
   */
  namespace SystemManage {
    type CommonSearchParams = Pick<Common.PaginatingCommonParams, 'page' | 'size'>;

    /** role */
    type Role = Common.CommonRecord<{
      id: string;
      /** role name */
      roleName: string;
      /** role code */
      roleCode: string;
      /** role description */
      roleDesc: string;
    }>;

    /** role search params */
    type RoleSearchParams = CommonType.RecordNullable<
      Pick<Api.SystemManage.Role, 'roleName' | 'roleCode' | 'status'> & CommonSearchParams
    >;

    /** role list */
    type RoleList = Common.PaginatingQueryRecord<Role>;

    /** all role */
    type AllRole = Pick<Role, 'id' | 'roleName' | 'roleCode'>;

    /**
     * user gender
     *
     * - "1": "male"
     * - "2": "female"
     */
    type UserGender = '1' | '2';

    /** user */
    type User = Common.CommonRecord<{
      /** user name */
      userName: string;
      /** user gender */
      userGender: UserGender | null;
      /** user nick name */
      nickName: string;
      /** user phone */
      userPhone: string;
      /** user email */
      userEmail: string;
      /** user role code collection */
      userRoles: string[];
    }>;

    /** user search params */
    type UserSearchParams = CommonType.RecordNullable<
      Pick<Api.SystemManage.User, 'userName' | 'userGender' | 'nickName' | 'userPhone' | 'userEmail' | 'status'> &
        CommonSearchParams
    >;

    /** user list */
    type UserList = Common.PaginatingQueryRecord<User>;

    /**
     * menu type
     *
     * - "1": directory
     * - "2": menu
     */
    type MenuType = '1' | '2';

    type MenuButton = {
      /**
       * button code
       *
       * it can be used to control the button permission
       */
      code: string;
      /** button description */
      desc: string;
    };

    /**
     * icon type
     *
     * - "1": iconify icon
     * - "2": local icon
     */
    type IconType = '1' | '2';

    type MenuPropsOfRoute = Pick<
      import('vue-router').RouteMeta,
      | 'i18nKey'
      | 'keepAlive'
      | 'constant'
      | 'order'
      | 'href'
      | 'hideInMenu'
      | 'activeMenu'
      | 'multiTab'
      | 'fixedIndexInTab'
      | 'query'
    >;

    type Menu = Common.CommonRecord<{
      id: string;
      /** parent menu id */
      parentId: number;
      /** menu type */
      menuType: MenuType;
      /** menu name */
      menuName: string;
      /** route name */
      routeName: string;
      /** route path */
      routePath: string;
      /** component */
      component?: string;
      /** iconify icon name or local icon name */
      icon: string;
      /** icon type */
      iconType: IconType;
      /** buttons */
      buttons?: MenuButton[] | null;
      /** children menu */
      children?: Menu[] | null;
    }> &
      MenuPropsOfRoute;

    /** menu list */
    type MenuList = Common.PaginatingQueryRecord<Menu>;

    type MenuTree = {
      id: string;
      label: string;
      pId: number;
      children?: MenuTree[];
    };
  }

  /**
   * namespace NotifyConfig
   *
   * backend api module: "notifyConfig"
   */
  namespace NotifyConfig {
    type CommonSearchParams = Pick<Common.PaginatingCommonParams, 'page' | 'size'>;

    /** notify-config */
    type NotifyConfig = Common.CommonRecord<{
      /** 组名称 */
      groupName: string | null;
      /** 通知人id */
      recipientIds: number[];
      /** 任务类型 1、重试任务 2、回调任务、3、JOB任务 4、WORKFLOW任务 */
      systemTaskType: SystemTaskType | null;
      /** 业务名称 */
      businessName?: string;
      /** 通知名称 */
      notifyName: string;
      /** 状态 */
      notifyStatus: Api.Common.EnableStatusNumber;
      /** 通知场景 */
      notifyScene: JobNotifyScene | RetryNotifyScene | WorkflowNotifyScene | null;
      /** 通知阈值 */
      notifyThreshold: number;
      /** 限流开关 */
      rateLimiterStatus: Api.Common.EnableStatusNumber;
      /** 每秒限流阈值 */
      rateLimiterThreshold: number | null;
      /** 描述 */
      description: string;
    }>;

    /** notify-config search params */
    type NotifySearchParams = CommonType.RecordNullable<
      Pick<
        Api.NotifyConfig.NotifyConfig,
        'groupName' | 'systemTaskType' | 'notifyStatus' | 'notifyScene' | 'notifyName'
      > &
        CommonSearchParams
    >;

    /** notify-config list */
    type NotifyConfigList = Common.PaginatingQueryRecord<NotifyConfig>;

    /** 任务类型 1、重试任务 2、回调任务、 3、JOB任务 4、WORKFLOW任务 */
    type SystemTaskType = 1 | 3 | 4;

    /** 1、场景重试数量超过阈值 2、场景重试失败数量超过阈值 3、客户端上报失败 4、客户端组件异常 5、任务重试失败 6、任务重试失败进入死信队列 7、任务重试失败（没有可执行的客户端节点） */
    type RetryNotifyScene = 1 | 2 | 3 | 4 | 5 | 6 | 7;

    /** 1、任务执行失败 2、客户端执行失败 3、没有可执行的客户端节点 */
    type JobNotifyScene = 1 | 2 | 3;

    /** 2、 客户端执行失败 3、没有可执行的客户端节点 100、工作流任务执行失败 */
    type WorkflowNotifyScene = 2 | 3 | 100;
  }

  /**
   * namespace Notify-recipient
   *
   * backend api module: "notify-recipient"
   */
  namespace NotifyRecipient {
    type CommonSearchParams = Pick<Common.PaginatingCommonParams, 'page' | 'size'>;

    /** notifyRecipient */
    type NotifyRecipient = Common.CommonRecord<{
      /** 接收人名称 */
      recipientName: string;
      /** 通知类型 */
      notifyType: AlarmType;
      /** 属性信息 */
      notifyAttribute?: string;
      /** 描述 */
      description: string;
    }>;

    /** dingDing Notify */
    type DingDingNotify = Common.CommonRecord<{
      /** 接收人名称 */
      webhookUrl: string;
      /** @的用户 */
      ats: string[];
    }> &
      NotifyRecipient;

    /** email Notify */
    type EmailNotify = Common.CommonRecord<{
      /** 通知人 */
      tos: string[];
    }> &
      NotifyRecipient;

    /** webhook Notify */
    type WebhookNotify = Common.CommonRecord<{
      /** 接收人名称 */
      webhookUrl: string;
      /* 通知类型 */
      contentType: string;
      /** 密钥 */
      secret?: string;
    }> &
      NotifyRecipient;

    /** notifyRecipient search params */
    type NotifyRecipientParams = CommonType.RecordNullable<
      Pick<Api.NotifyRecipient.NotifyRecipient, 'recipientName' | 'notifyType'> & CommonSearchParams
    >;

    /** notifyRecipient list */
    type NotifyRecipientList = Common.PaginatingQueryRecord<NotifyRecipient>;

    /** 1: 钉钉通知 2: 邮件通知 3: 企业通知 4: 飞书 5: Webhook */
    type AlarmType = 1 | 2 | 3 | 4 | 5;

    type ExportNotifyRecipient = Common.CommonRecord<{
      notifyRecipientIds: string[];
    }> &
      NotifyRecipientParams;

    /* 1: application/json 2：application/x-www-form-urlencoded */
    type AlarmTypeWebhook = 1 | 2;
  }

  namespace RetryDeadLetter {
    type CommonSearchParams = Pick<Common.PaginatingCommonParams, 'page' | 'size'>;

    type TaskType = 1 | 2;

    /** deadLetter */
    type DeadLetter = Common.CommonRecord<{
      /** id */
      id?: number;
      /** UniqueId */
      uniqueId?: string;
      /** 组名称 */
      groupName?: string;
      /** 场景名称 * */
      sceneName?: string;
      /** 幂等ID */
      idempotentId?: string;
      /** 业务编号 * */
      bizNo?: string;
      /** 任务类型 * */
      taskType?: TaskType;
      /** 创建时间 * */
      createDt?: string;
      /** 执行器名称 */
      executorName: string;
      /** 执行方法参数 */
      argsStr: string;
      /** 执行方法参数序列化器名称 */
      serializerName: string;
    }>;

    /** deadLetter search params */
    type RetryDeadLetterSearchParams = CommonType.RecordNullable<
      Pick<
        Api.RetryDeadLetter.DeadLetter,
        'id' | 'uniqueId' | 'groupName' | 'sceneName' | 'idempotentId' | 'bizNo' | 'taskType' | 'createDt'
      > &
        CommonSearchParams & { datetimeRange?: [string, string] }
    >;

    /** DeadLetter list */
    type RetryDeadLetterList = Common.PaginatingQueryRecord<DeadLetter>;

    type BatchDeadLetter = Common.CommonRecord<{
      groupName?: string;
      ids: number[];
    }>;
  }

  /**
   * namespace RetryTask
   *
   * backend api module: "retryTask"
   */
  namespace Retry {
    type CommonSearchParams = Pick<Common.PaginatingCommonParams, 'page' | 'size'>;

    type RetryStatusType = 0 | 1 | 2 | 3;

    type RetryTaskStatusType = 1 | 2 | 3 | 4 | 5 | 6;

    type TaskType = 1 | 2;

    /** RetryTask */
    type Retry = Common.CommonRecord<{
      /** 组名称 */
      groupName: string;
      /** 场景名称 */
      sceneName: string;
      /** 幂等id */
      idempotentId: string;
      /** 业务编号 */
      bizNo: string;
      /** 执行器名称 */
      executorName: string;
      /** 执行方法参数 */
      argsStr: string;
      /** 执行方法参数序列化器名称 */
      serializerName: string;
      /** 扩展字段 */
      extAttrs?: string;
      /** 下次触发时间 */
      nextTriggerAt?: string;
      /** 重试次数 */
      retryCount?: number;
      /** 重试状态 0、重试中 1、重试完成 2、最大次数 3、暂停 */
      retryStatus: RetryStatusType;
      /** 任务类型 1、重试数据 2、回调数据 */
      taskType?: TaskType;
      /** 标签 */
      labels: string;
      labelMap?: { key: string; value: string }[];
    }>;

    type RetryBatchAdd = {
      /** 组名称 */
      groupName: string;
      /** 重试状态 0、重试中 1、重试完成 2、最大次数 3、暂停 */
      retryStatus: RetryStatusType;
      /** 日志 */
      logStr: string;
    };

    type RetryUpdateStatusRequest = {
      /** id */
      id: number;
      /** 重试状态 0、重试中 1、重试完成 2、最大次数 3、暂停 */
      status: RetryStatusType;
    };

    type ManualTriggerRequestVO = {
      groupName: string;
      retryIds: number[];
    };

    type BatchDeleteRetryVO = {
      groupName: string;
      ids: string[];
    };

    type GenerateRetryIdempotentIdVO = {
      /** 组名称 */
      groupName: string;
      /** 场景名称 */
      sceneName: string;
      /** 执行参数 */
      argsStr: string;
      /** 执行器名称 */
      executorName: string;
    };

    /** RetryTask search params */
    type RetrySearchParams = CommonType.RecordNullable<
      Pick<Api.Retry.Retry, 'groupName' | 'sceneName' | 'idempotentId' | 'bizNo' | 'retryStatus'> & CommonSearchParams
    >;

    /** RetryTask list */
    type RetryList = Common.PaginatingQueryRecord<Retry>;
  }

  /**
   * namespace Scene
   *
   * backend api module: "scene"
   */
  namespace RetryScene {
    import EnableStatusNumber = Api.Common.EnableStatusNumber;
    import RetryBlockStrategy = Api.Common.RetryBlockStrategy;
    type CommonSearchParams = Pick<Common.PaginatingCommonParams, 'page' | 'size'>;

    /** scene */
    type Scene = Common.CommonRecord<{
      /** 组名 */
      groupName?: string;
      /** 场景名 */
      sceneName: string;
      /** 通知场景ids */
      notifyIds: number[];
      /** 阻塞策略 */
      blockStrategy: RetryBlockStrategy;
      /** 状态 */
      sceneStatus: EnableStatusNumber;
      /** 负责人 */
      ownerId?: string;
      ownerName: string;
      /** 退避策略 */
      backOff: BackOff;
      /** 路由策略 */
      routeKey: Common.RouteKey;
      /** 最大重试次数 */
      maxRetryCount: number;
      /** 间隔时间 */
      triggerInterval: string;
      /** 调用链超时时间 */
      deadlineRequest: number;
      /** 超时时间 */
      executorTimeout: number;
      /** 描述 */
      description: string;
      /** 回调状态 0、不开启 1、开启 */
      cbStatus: EnableStatusNumber;
      /** 触发类型 */
      cbTriggerType: BackOff;
      /** 回调间隔时间 */
      cbTriggerInterval: string;
      /** 回调的最大执行次数 */
      cbMaxCount: number;
      /** 标签 */
      labels?: string;
      /** 标签 */
      labelMap?: { key: string; value: string }[];
    }>;

    /** scene search params */
    type SceneSearchParams = CommonType.RecordNullable<
      Pick<
        Api.RetryScene.Scene,
        | 'ownerId'
        | 'groupName'
        | 'sceneName'
        | 'sceneStatus'
        | 'backOff'
        | 'maxRetryCount'
        | 'triggerInterval'
        | 'deadlineRequest'
        | 'executorTimeout'
        | 'description'
        | 'routeKey'
      > &
        CommonSearchParams
    >;

    type ExportScene = Common.CommonRecord<{
      sceneIds: string[];
    }> &
      SceneSearchParams;

    /** scene list */
    type SceneList = Common.PaginatingQueryRecord<Scene>;

    /** 1: 延迟等级 2: 固定时间 3: CRON表达式 4: 随机等待 */
    type BackOff = 1 | 2 | 3 | 4;
  }

  /**
   * namespace Workflow
   *
   * backend api module: "workflow"
   */
  namespace Workflow {
    import EnableStatusNumber = Api.Common.EnableStatusNumber;
    type CommonSearchParams = Pick<Common.PaginatingCommonParams, 'page' | 'size'>;

    /** workflow */
    type Workflow = Common.CommonRecord<{
      /** 负责人 */
      ownerId: string;
      ownerName: string;
      /** 工作流名称 */
      workflowName: string;
      /** 组名称 */
      groupName: string;
      /** 触发时间 */
      nextTriggerAt: string;
      /** 告警通知场景ids */
      notifyIds: number[];
      /** 状态 */
      workflowStatus: EnableStatusNumber;
      /** 触发类型 */
      triggerType: Job.TriggerType;
      /** 间隔时长 */
      triggerInterval: string;
      /** 超时时间 */
      executorTimeout: number;
      /** 上下文 */
      wfContext: string;
    }>;

    type WorkflowTriggerParams = {
      workflowId?: string;
      tmpWfContext?: string;
    };

    /** workflow search params */
    type WorkflowSearchParams = CommonType.RecordNullable<
      Pick<Api.Workflow.Workflow, 'workflowName' | 'groupName' | 'workflowStatus' | 'ownerId'> & CommonSearchParams
    >;

    /** workflow name search params */
    type WorkflowNameSearchParams = CommonType.RecordNullable<
      Pick<
        Common.CommonRecord<{
          keywords: string;
          workflowId: number;
          groupName: string;
          ownerId: string;
        }>,
        'keywords' | 'workflowId' | 'groupName' | 'ownerId'
      >
    >;

    type ExportWorkflow = Common.CommonRecord<{
      workflowIds: string[];
    }> &
      WorkflowSearchParams;

    /** workflow list */
    type WorkflowList = Common.PaginatingQueryRecord<Workflow>;

    type WorkflowdUpdateJobStatusRequestVO = {
      id: string;
      status: Common.EnableStatusNumber;
    };
  }

  /**
   * namespace Job
   *
   * backend api module: "job"
   */
  namespace Job {
    type CommonSearchParams = Pick<Common.PaginatingCommonParams, 'page' | 'size'>;

    /** Job */
    type Job = Common.CommonRecord<{
      /** 组名称 */
      groupName: string;
      /** 负责人id */
      ownerId: string;
      /** 负责人名 */
      ownerName: string;
      /** 通知场景ids */
      notifyIds: number[];
      /** 任务名称 */
      jobName: string;
      /** 方法参数 */
      argsStr: string;
      /** 参数类型 */
      argsType: number;
      /** 扩展字段 */
      extAttrs?: string;
      /** 下次触发时间 */
      nextTriggerAt?: string;
      /** 状态 */
      jobStatus: Common.EnableStatusNumber;
      /** 路由策略 */
      routeKey: Common.RouteKey;
      /** 执行器类型 */
      executorType: Common.ExecutorType;
      /** 执行器名称 */
      executorInfo: string;
      /** 触发类型 */
      triggerType: Common.TriggerType;
      /** 间隔时长 */
      triggerInterval: string;
      /** 阻塞策略 */
      blockStrategy: Common.BlockStrategy;
      /** 超时时间 */
      executorTimeout: number;
      /** 最大重试次数 */
      maxRetryTimes: number;
      /** 重试间隔 */
      retryInterval: number;
      /** 任务类型 */
      taskType: Common.TaskType;
      /** 并行数 */
      parallelNum: number;
      /** Bucket */
      bucketIndex?: number;
      /** 描述 */
      description?: string;
      /** 通知场景 */
      notifyScene?: string;
      /** 标签 */
      labels?: string;
      /** 标签 */
      labelMap?: { key: string; value: string }[];
    }>;

    type TriggerJobParams = {
      jobId?: string;
      tmpArgsStr?: string;
    };

    /** JobTask search params */
    type JobSearchParams = CommonType.RecordNullable<
      Pick<
        Api.Job.Job,
        | 'groupName'
        | 'jobName'
        | 'ownerId'
        | 'argsStr'
        | 'executorInfo'
        | 'argsType'
        | 'jobStatus'
        | 'routeKey'
        | 'executorType'
        | 'triggerType'
        | 'triggerInterval'
        | 'blockStrategy'
        | 'executorTimeout'
        | 'maxRetryTimes'
        | 'retryInterval'
        | 'taskType'
        | 'parallelNum'
        | 'description'
      > &
        CommonSearchParams
    >;

    type JobUpdateJobStatusRequestVO = {
      id: string;
      status: Common.EnableStatusNumber;
    };

    /** JobTask list */
    type JobList = Common.PaginatingQueryRecord<Job>;

    type ExportJob = Common.CommonRecord<{
      jobIds: string[];
    }> &
      JobSearchParams;

    /** 2、固定时间 3、CRON表达式 4、指定时间点 99、工作流 */
    type TriggerType = 2 | 3 | 5 | 99;

    type JobNameListSearchParams = CommonType.RecordNullable<{
      groupName?: string;
      jobId?: number;
      keywords?: string;
    }>;

    /** jobTask */
    type JobTask = Common.CommonRecord<{
      /** ID */
      id: string;
      /** 任务 ID */
      jobId: string;
      /** 组名称 */
      groupName: string;
      /** 任务名称 */
      taskName: string;
      /** 地址 */
      clientInfo: string;
      /** 参数 */
      argsStr: string;
      /** 结果 */
      resultMessage: string;
      /** 重试次数 */
      retryCount: string;
      /** 开始执行时间 */
      createDt: string;
      /** 任务批次 ID */
      taskBatchId: string;
      /** 任务状态 ID */
      taskStatus: Common.TaskStatus;
      /** 任务类型 */
      taskType: Common.TaskType;
      /** 父级 ID */
      parentId: string;
      /** 子节点 */
      children: JobTaskTree[];
      /** 是否存在下级 */
      isLeaf: boolean;
      /** 执行时长(virtual) */
      duration?: number;
    }>;

    type JobTaskTree = {
      parentId: string;
      children: JobTaskTree[];
    } & JobTask;

    /** jobTask search params */
    type jobTaskSearchParams = CommonType.RecordNullable<
      Pick<Api.Job.JobTask, 'groupName' | 'taskBatchId' | 'taskStatus'> &
        CommonSearchParams & { startId: number; fromIndex: number; parentId: string }
    >;

    /** jobTask list */
    type JobTaskList = Common.PaginatingQueryRecord<JobTask>;
    /** jobTask tree list */
    type JobTaskTreeList = JobTask[];

    /** jobExecutor search params */
    type JobExecutorParams = CommonType.RecordNullable<
      Pick<Api.Job.JobExecutor, 'groupName' | 'executorInfo'> & CommonSearchParams
    >;

    /** 执行器信息 */
    type JobExecutor = Common.CommonRecord<{
      groupName: string;
      executorInfo: string;
      executorType: Api.Common.ExecutorType;
    }>;

    /** JobExecutor list */
    type JobExecutorList = Common.PaginatingQueryRecord<JobExecutor>;
  }

  /**
   * namespace JobBatch
   *
   * backend api module: "jobBatch"
   */
  namespace JobBatch {
    import TaskType = Api.Common.TaskType;
    type CommonSearchParams = Pick<Common.PaginatingCommonParams, 'page' | 'size'>;

    /** JobBatch */
    type JobBatch = Common.CommonRecord<{
      /** 组名称 */
      groupName: string;
      /** 任务名称 */
      jobName: string;
      /** 任务类型 */
      taskType: TaskType;
      /** 工作流节点名称 */
      nodeName: string;
      /** 任务信息id */
      jobId: string;
      /** 状态 */
      taskBatchStatus: Common.TaskBatchStatus;
      /** 开始执行时间 */
      executionAt: string;
      /** 执行时长(virtual) */
      duration?: number;
      /** 操作原因 */
      operationReason: Common.OperationReason;
      /** 执行器类型 */
      executorType: Common.ExecutorType;
      /** 执行器名称 */
      executorInfo: string;
      /** 工作流的回调节点信息 */
      callback: object;
      /** 名称 */
      decision: object;
      /** 工作流批次id */
      workflowTaskBatchId: string;
      /** 工作流节点id */
      workflowNodeId: string;
    }>;

    /** JobBatch search params */
    type JobBatchSearchParams = CommonType.RecordNullable<
      Pick<Api.JobBatch.JobBatch, 'groupName' | 'jobName' | 'jobId' | 'taskType'> &
        CommonSearchParams & { datetimeRange?: [string, string]; taskBatchStatus: Common.TaskBatchStatus[] }
    >;

    /** JobBatch list */
    type JobBatchList = Common.PaginatingQueryRecord<JobBatch>;
  }

  /**
   * namespace WorkflowBatch
   *
   * backend api module: "workflowBatch"
   */
  namespace WorkflowBatch {
    type CommonSearchParams = Pick<Common.PaginatingCommonParams, 'page' | 'size'>;

    /** workflowBatch */
    type WorkflowBatch = Common.CommonRecord<{
      /** 工作流名称 */
      workflowName: string;
      /** 工作流ID */
      workflowId?: number;
      /** 组名称 */
      groupName: string;
      /** 执行时间 */
      executionAt: string;
      /** 状态 */
      taskBatchStatus: Common.TaskBatchStatus;
      /** 操作原因 */
      operationReason: Common.OperationReason;
      /** 创建时间 */
      createDt: string;
    }>;

    /** workflowBatch search params */
    type WorkflowBatchSearchParams = CommonType.RecordNullable<
      Pick<Api.WorkflowBatch.WorkflowBatch, 'workflowId' | 'groupName' | 'workflowName' | 'taskBatchStatus'> &
        CommonSearchParams & { datetimeRange?: [string, string] }
    >;

    /** workflowBatch list */
    type WorkflowBatchList = Common.PaginatingQueryRecord<WorkflowBatch>;
  }

  /**
   * namespace RetryLog
   *
   * backend api module: "retryLog"
   */
  namespace RetryTask {
    import TaskType = Api.Retry.TaskType;
    import RetryTaskStatusType = Api.Retry.RetryTaskStatusType;
    type CommonSearchParams = Pick<Common.PaginatingCommonParams, 'page' | 'size'>;

    /** retryLog */
    type RetryTask = Common.CommonRecord<{
      /** UniqueId */
      retryId: string;
      /** 组名称 */
      groupName: string;
      /** 场景名称 */
      sceneName: string;
      /** 重试状态 */
      taskStatus: RetryTaskStatusType;
      /** 任务类型 */
      taskType: TaskType;
      /** 扩展字段 */
      extAttrs?: string;
      /** 客户端信息 */
      clientInfo: string;
      /** 失败原因 */
      operationReason: Common.RetryOperationReason;
      /** 重试信息 */
      responseVO: Api.Retry.Retry;
    }>;

    /** retryLog search params */
    type RetryTaskSearchParams = CommonType.RecordNullable<
      Pick<Api.RetryTask.RetryTask, 'retryId' | 'groupName' | 'sceneName' | 'taskStatus' | 'operationReason'> &
        CommonSearchParams & { datetimeRange?: [string, string] }
    >;

    /** retryLog list */
    type RetryTaskList = Common.PaginatingQueryRecord<RetryTask>;
  }
  /**
   * namespace UserManager
   *
   * backend api module: "UserManager"
   */
  namespace UserManager {
    type CommonSearchParams = Pick<Common.PaginatingCommonParams, 'page' | 'size'>;

    /** userCenter */
    type UserManager = Common.CommonRecord<{
      /** 用户名 */
      username: string;
      /** 密码 */
      password: string | null;
      /** 确认密码 */
      checkPassword?: string;
      /** 角色 */
      role: Role;
      /** 组 */
      permissions: Permission[];
    }>;

    type Permission = Common.CommonRecord<{
      groupName: string;
      namespaceId: string;
      namespaceName?: string;
    }>;

    /** userManager search params */
    type UserManagerSearchParams = CommonType.RecordNullable<
      Pick<Api.UserManager.UserManager, 'id' | 'username' | 'password' | 'checkPassword' | 'role' | 'permissions'> &
        CommonSearchParams
    >;

    /** userCenter list */
    type UserManagerList = Common.PaginatingQueryRecord<UserManager>;

    /** 1、user 2、admin */
    type Role = 1 | 2;

    type UpdateUserPassword = Common.CommonRecord<{
      /** 旧密码 */
      oldPassword: string;
      /** 新密码 */
      newPassword: string;
      /** 确认密码 */
      checkPassword?: string;
    }>;
  }

  /**
   * namespace JobLog
   *
   * backend api module: "JobLog"
   */
  namespace JobLog {
    type JobLevel = 'INFO' | 'WARN' | 'ERROR' | 'DEBUG';

    type JobLogSearchParams = {
      taskBatchId: string;
      jobId: string;
      taskId: string;
    } & LogSearchParams;

    type RetryLogSearchParams = {
      groupName: string;
      retryTaskId: string;
    } & LogSearchParams;

    type LogSearchParams = {
      startId: string;
      fromIndex: number;
      size: number;
    };

    type JobLogList = {
      finished: boolean;
      fromIndex: number;
      message: JobMessage[];
      nextStartId: string;
    };

    type JobMessage = {
      index: number;
      key: string;
      level: JobLevel;
      host: string;
      port: string;
      location: string;
      message: string;
      thread: string;
      ['time_stamp']: string;
      throwable: string;
    };
  }
}
