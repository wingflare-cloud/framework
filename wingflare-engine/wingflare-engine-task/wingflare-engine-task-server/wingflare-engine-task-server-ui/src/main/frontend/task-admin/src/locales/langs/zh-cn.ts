const local: App.I18n.Schema = {
  system: {
    title: 'Snail Job',
    desc: '灵活，可靠和快速的分布式任务重试和分布式任务调度平台',
    updateTitle: '系统版本更新通知',
    updateContent: '检测到系统有新版本发布，是否立即刷新页面？',
    updateConfirm: '立即刷新',
    updateCancel: '稍后再说'
  },
  common: {
    action: '操作',
    add: '新增',
    batchAdd: '批量添加',
    addSuccess: '添加成功',
    backToHome: '返回首页',
    batchDelete: '批量删除',
    cancel: '取消',
    close: '关闭',
    check: '勾选',
    expandColumn: '展开列',
    columnSetting: '列设置',
    config: '配置',
    confirm: '确认',
    save: '保存',
    delete: '删除',
    rollback: '回滚',
    batchRollback: '批量回滚',
    rollbackSuccess: '回滚成功',
    deleteSuccess: '删除成功',
    confirmDelete: '确认删除吗？',
    confirmRollback: '确认回滚吗？',
    checkUploadType: '只能上传json格式的文件，请重新上传',
    second: '秒',
    millisecond: '毫秒',
    import: '导入',
    export: '导出',
    exportAll: '确认导出列表中全部数据吗?',
    exportPar: '确认导出{num}条数据吗?',
    edit: '编辑',
    warning: '警告',
    error: '错误',
    detail: '详情',
    index: '序号',
    keywordSearch: '请输入关键词搜索',
    logout: '退出登录',
    logoutConfirm: '确认退出登录吗？',
    updatePassword: '修改密码',
    changePassword: '修改密码',
    lookForward: '敬请期待',
    modify: '修改',
    modifySuccess: '修改成功',
    noData: '无数据',
    operate: '操作',
    pleaseCheckValue: '请检查输入的值是否合法',
    refresh: '刷新',
    reset: '重置',
    search: '搜索',
    switch: '切换',
    tip: '提示',
    trigger: '触发',
    update: '更新',
    updateSuccess: '更新成功',
    updateFailed: '更新失败',
    downloadFail: '文件下载失败',
    success: '成功',
    fail: '失败',
    stop: '停止',
    confirmStop: '确认停止吗？',
    execute: '执行',
    copy: '复制',
    batchList: '批次',
    retryTaskList: '任务',
    resume: '恢复',
    pause: '暂停',
    finish: '完成',
    retry: '重试',
    running: '运行中',
    operateSuccess: '操作成功',
    operateFailed: '操作失败',
    executeSuccess: '执行成功',
    executeFailed: '执行失败',
    confirmExecute: '确认执行吗？',
    confirmResume: '确认恢复吗？',
    confirmPause: '确认暂停吗？',
    confirmFinish: '确认完成吗？',
    confirmRetry: '确认重试吗？',
    log: '日志',
    idDetailTip: '点击 ID 查看详情',
    generateRandomly: '随机生成',
    active: '活跃',
    none: '无',
    yesOrNo: {
      yes: '是',
      no: '否'
    },
    status: {
      enable: '启用',
      disable: '禁用'
    },
    systemTaskType: {
      retry: '重试任务',
      callback: '回调任务',
      job: '定时任务',
      workflow: '工作流'
    },
    routeKey: {
      routeLabel: '路由策略',
      routeForm: '请选择路由策略',
      items: {
        consistentHash: '一致性哈希',
        random: '随机',
        lru: 'LRU',
        round: '轮询',
        first: '第一个',
        last: '最后一个'
      }
    },
    blockStrategy: {
      label: '阻塞策略',
      form: '请选择阻塞策略',
      items: {
        discard: '丢弃',
        overwrite: '覆盖',
        parallel: '并行',
        recovery: '恢复'
      }
    },
    failStrategy: {
      items: {
        skip: '跳过',
        blockage: '阻塞'
      }
    },
    workFlowNodeStatus: {
      items: {
        open: '开启',
        close: '关闭'
      }
    },
    executorType: {
      label: '执行器类型',
      form: '请选择执行器类型',
      items: {
        java: 'Java',
        python: 'Python',
        go: 'Go'
      }
    },
    taskType: {
      label: '任务类型',
      form: '请选择任务类型',
      items: {
        cluster: '集群',
        broadcast: '广播',
        slice: '静态分片',
        map: 'Map',
        mapreduce: 'MapReduce'
      }
    },
    triggerType: {
      label: '触发类型',
      form: '请选择触发类型',
      items: {
        cron: 'CRON表达式',
        fixed: '固定时间',
        workflow: '工作流'
      }
    },
    taskBatchStatus: {
      label: '执行状态',
      form: '请选择执行状态',
      items: {
        waiting: '待处理',
        running: '运行中',
        success: '处理成功',
        fail: '处理失败',
        stop: '任务停止',
        cancel: '取消',
        decisionFailed: '判定未通过',
        skip: '跳过'
      }
    },
    retryTaskStatus: {
      label: '执行状态',
      form: '请选择执行状态',
      items: {
        waiting: '待处理',
        running: '运行中',
        success: '处理成功',
        fail: '处理失败',
        stop: '任务停止',
        cancel: '取消'
      }
    },
    taskStatus: {
      label: '状态',
      form: '请选择状态',
      items: {
        running: '运行中',
        success: '处理成功',
        fail: '处理失败',
        stop: '任务停止',
        cancel: '取消'
      }
    },
    jobOperationReason: {
      label: '操作原因',
      form: '请选择执行状态',
      items: {
        none: '无',
        taskExecutionTimeout: '任务执行超时',
        notClient: '无客户端节点',
        closed: '任务已关闭',
        discard: '任务丢弃',
        overlay: '任务被覆盖',
        notExecutionTask: '无可执行任务项',
        taskExecutionError: '任务执行期间发生非预期异常',
        mannerStop: '手动停止',
        workflowConditionNodeExecutionError: '条件节点执行异常',
        jobTaskInterrupted: '任务中断',
        workflowCallbackNodeExecutionError: '回调节点执行异常',
        workflowNodeNoRequired: '无需处理',
        workflowNodeClosedSkipExecution: '节点关闭跳过执行',
        workflowDecisionFailed: '判定未通过'
      }
    },
    retryOperationReason: {
      label: '操作原因',
      form: '请选择执行状态',
      items: {
        none: '无',
        taskExecutionTimeout: '任务执行超时',
        notClient: '无客户端节点',
        closed: '任务已关闭',
        discard: '任务丢弃',
        overlay: '任务被覆盖',
        notExecutionTask: '无可执行任务项',
        taskExecutionError: '任务执行期间发生非预期异常',
        mannerStop: '手动停止',
        noRunningRetry: '当前重试非运行中',
        sceneClosed: '当前场景已关闭',
        retryFail: '客户端执行重试失败',
        clientTriggerRetryStop: '客户端触发任务停止'
      }
    },
    updateDt: '更新时间',
    createDt: '创建时间',
    today: '今天',
    lastWeek: '最近一周',
    currentMonth: '当月',
    lastMonth: '最近一月',
    lastTwoMonth: '最近两月'
  },
  request: {
    logout: '请求失败后登出用户',
    logoutMsg: '用户状态失效，请重新登录',
    logoutWithModal: '请求失败后弹出模态框再登出用户',
    logoutWithModalMsg: '用户状态失效，请重新登录',
    refreshToken: '请求的token已过期，刷新token',
    tokenExpired: 'token已过期'
  },
  theme: {
    themeSchema: {
      title: '主题模式',
      light: '亮色模式',
      dark: '暗黑模式',
      auto: '跟随系统'
    },
    grayscale: '灰色模式',
    colourWeakness: '色弱模式',
    layoutMode: {
      title: '布局模式',
      vertical: '左侧菜单模式',
      'vertical-mix': '左侧菜单混合模式',
      horizontal: '顶部菜单模式',
      'horizontal-mix': '顶部菜单混合模式',
      reverseHorizontalMix: '一级菜单与子级菜单位置反转'
    },
    recommendColor: '应用推荐算法的颜色',
    recommendColorDesc: '推荐颜色的算法参照',
    themeColor: {
      title: '主题颜色',
      primary: '主色',
      info: '信息色',
      success: '成功色',
      warning: '警告色',
      error: '错误色',
      followPrimary: '跟随主色'
    },
    scrollMode: {
      title: '滚动模式',
      wrapper: '外层滚动',
      content: '主体滚动'
    },
    page: {
      animate: '页面切换动画',
      mode: {
        title: '页面切换动画类型',
        'fade-slide': '滑动',
        fade: '淡入淡出',
        'fade-bottom': '底部消退',
        'fade-scale': '缩放消退',
        'zoom-fade': '渐变',
        'zoom-out': '闪现',
        none: '无'
      }
    },
    fixedHeaderAndTab: '固定头部和标签栏',
    header: {
      height: '头部高度',
      breadcrumb: {
        visible: '显示面包屑',
        showIcon: '显示面包屑图标'
      },
      multilingual: {
        visible: '显示多语言按钮'
      }
    },
    tab: {
      visible: '显示标签栏',
      cache: '标签栏信息缓存',
      height: '标签栏高度',
      mode: {
        title: '标签栏风格',
        chrome: '谷歌风格',
        button: '按钮风格'
      }
    },
    sider: {
      inverted: '深色侧边栏',
      width: '侧边栏宽度',
      collapsedWidth: '侧边栏折叠宽度',
      mixWidth: '混合布局侧边栏宽度',
      mixCollapsedWidth: '混合布局侧边栏折叠宽度',
      mixChildMenuWidth: '混合布局子菜单宽度'
    },
    footer: {
      visible: '显示底部',
      fixed: '固定底部',
      height: '底部高度',
      right: '底部局右'
    },
    watermark: {
      visible: '显示全屏水印',
      text: '水印文本'
    },
    themeDrawerTitle: '主题配置',
    pageFunTitle: '页面功能',
    resetCacheStrategy: {
      title: '重置缓存策略',
      close: '关闭页面',
      refresh: '刷新页面'
    },
    configOperation: {
      copyConfig: '复制配置',
      copySuccessMsg: '复制成功，请替换 src/theme/settings.ts 中的变量 themeSettings',
      resetConfig: '重置配置',
      resetSuccessMsg: '重置成功'
    }
  },
  route: {
    login: '登录',
    403: '无权限',
    404: '页面不存在',
    500: '服务器错误',
    'iframe-page': '外链页面',
    log: '日志',
    home: '首页',
    about: '关于',
    document: '文档',
    pods: '在线机器',
    namespace: '命名空间',
    group: '组管理',
    notify: '告警通知',
    notify_recipient: '通知人',
    notify_config: '通知配置',
    retry: '重试任务',
    retry_task: '重试列表',
    retry_info: '重试记录',
    'retry_dead-letter': '死信任务',
    user: '用户管理',
    user_manager: '用户信息',
    retry_scene: '重试场景',
    workflow: '工作流',
    workflow_task: '流程管理',
    workflow_batch: '流程监控',
    workflow_form: '工作流',
    workflow_form_copy: '复制工作流',
    workflow_form_batch: '工作流批次详情',
    workflow_form_detail: '工作流详情',
    workflow_form_edit: '编辑工作流',
    workflow_form_add: '新增工作流',
    job: '定时任务',
    job_task: '定时配置',
    job_batch: '定时队列',
    job_executor: '执行器'
  },
  page: {
    common: {
      upadteTime: '更新时间',
      createTime: '创建时间'
    },
    login: {
      common: {
        loginOrRegister: '登录 / 注册',
        userNamePlaceholder: '请输入用户名',
        phonePlaceholder: '请输入手机号',
        codePlaceholder: '请输入验证码',
        passwordPlaceholder: '请输入密码',
        confirmPasswordPlaceholder: '请再次输入密码',
        codeLogin: '验证码登录',
        login: '登录',
        confirm: '确定',
        back: '返回',
        validateSuccess: '验证成功',
        loginSuccess: '登录成功',
        welcomeBack: '欢迎回来，{userName} ！',
        codeTip: '拖动滑块完成拼图'
      },
      pwdLogin: {
        title: '密码登录',
        rememberMe: '记住我',
        forgetPassword: '忘记密码？',
        register: '注册账号',
        otherAccountLogin: '其他账号登录',
        otherLoginMode: '其他登录方式',
        superAdmin: '超级管理员',
        admin: '管理员',
        user: '普通用户'
      }
    },
    about: {
      title: '关于',
      introduction: `<span style="font-weight: bold; color: #1366ff">Snail Job</span>
 <br />
      是一个功能强大的分布式重试和任务调度平台，为支持提高分布式业务系统一致性和分布式任务调度而设计。具备高度可伸缩性和容错性，还包含完善的权限管理和强大的告警监控功能。
      一次部署即可获得重试和任务调度功能，为开发人员提供了简单而高效的解决方案。
      <br />
      在分布式系统中，任务失败是不可避免的，可能由网络问题、服务故障等原因引起。
      <br />
      <span style="font-weight: bold; color: #1366ff">分布式重试模块</span> <br />
      旨在帮助开发人员处理这些失败，确保任务能够在失败后正确重试，提升系统稳定性和可靠性。
      高颜值、易用性的用户界面，方便开发人员配置任务的重试策略和调度规则。支持多种重试策略，如指数退避、固定间隔等，开发人员可根据业务需求选择最适合的策略。
      <br />
      <span style="font-weight: bold; color: #1366ff">分布式任务调度模块</span> <br />
      是高性能的任务调度框架，无需依赖外部中间件即可实现秒级任务间隔调度。支持多种执行模式，如集群模式、广播模式和分片模式，同时提供多种阻塞策略，如丢弃、覆盖和并行，以提高任务执行效率。平台还提供监控和日志功能，帮助开发人员实时监控任务执行状态。`,
      projectInfo: {
        title: '项目信息',
        version: '版本',
        latestBuildTime: '最新构建时间',
        githubLink: 'Github 地址',
        previewLink: '预览地址',
        giteeLink: 'Gitee 地址',
        officialWebsite: '官网',
        videoTutorial: '视频教程'
      },
      prdDep: '生产依赖',
      devDep: '开发依赖'
    },
    home: {
      Greeting: '{userName}，欢迎回来！',
      morningGreeting: '早安，{userName}，今天又是充满活力的一天！',
      bthGreeting: '上午好，{userName}，工作顺利吗，不要久坐，多起来走动走动哦！',
      noonGreeting: '中午好，{userName}，工作了一个上午，现在是午餐时间！',
      athGreeting: '下午好，{userName}，午后很容易犯困呢，是时候该打个盹了!',
      duskGreeting: '{userName}，傍晚了，窗外夕阳的景色很美丽呢，最美不过夕阳红~',
      eveningGreeting: '晚上好，{userName}，今天过得怎么样？请注意早点休息！',
      earlyMorningGreeting: '{userName}，已经这么晚了呀，早点休息吧，晚安~',
      retryTaskCount: '重试任务',
      jobTaskCount: '定时任务',
      userCount: '用户',
      retryDeadLetter: '死信任务',
      retryTaskTip: '总任务量: 重试/回调任务量',
      jobTask: '定时任务',
      jobBatch: '任务批次',
      jobTaskTip: '成功率:总完成/总调度量',
      onlineServiceCount: '总在线机器',
      onlineServiceTip: '总在线机器:注册到系统的客户端和服务端之和',
      workflow: '工作流',
      workflowTip: '成功率:总完成/总调度量',
      machine: {
        type: {
          client: '客户端',
          server: '服务端'
        }
      },
      retryTask: {
        title: '重试任务',
        status: {
          maxRetryTimes: '最大重试次数',
          pauseRetry: '暂停重试'
        }
      },
      retryTab: {
        params: {
          day: '今日',
          week: '最近一周',
          month: '最近一月',
          year: '全年'
        },
        rank: {
          title: '失败总量排名',
          titleRetry: '任务总量排名'
        },
        task: {
          title: '任务汇总',
          groupName: '组名称',
          run: '运行中任务数',
          total: '总任务数'
        },
        pie: {
          title: '成功比例图'
        }
      }
    },
    pods: {
      title: '在线机器',
      nodeType: '类型',
      groupName: '组名称',
      labels: '标签',
      hostId: 'Pod ID',
      hostIp: 'IP',
      hostPort: 'Port',
      consumerBuckets: '路径/分区',
      updateDt: '更新时间',
      contextPath: '路径/组',
      systemVersion: '系统版本',
      executorType: '执行器类型',
      form: {
        groupName: '请输入组名称'
      },
      type: {
        client: '客户端',
        server: '服务端'
      },
      online: '上线',
      offline: '下线'
    },
    namespace: {
      title: '命名空间',
      name: '名称',
      keyword: '空间名称/唯一标识',
      uniqueId: '唯一标识(默认UUID)',
      form: {
        name: '请输入空间名称',
        keyword: '请输入空间名称/唯一标识',
        uniqueId: '请输入唯一标识',
        uniqueIdRule: '仅支持长度为:1~64位字符.格式为:数字、字母、下划线、短横线。'
      },
      addNamespace: '新增命名空间',
      editNamespace: '编辑命名空间'
    },
    groupConfig: {
      title: '组管理',
      detail: '组详情',
      namespaceId: '命名空间ID',
      groupName: '组名称',
      token: 'Token',
      groupStatus: '状态',
      idGeneratorMode: 'ID生成模式',
      version: '版本',
      groupPartition: '分区',
      initScene: '初始化场景',
      bucketIndex: 'Bucket',
      updateDt: '更新时间',
      description: '描述',
      commonConfig: '通用配置',
      retryConfig: '重试配置',
      form: {
        groupName: '请输入组名称',
        token: 'Token',
        groupStatus: '状态',
        description: '描述',
        idGeneratorMode: 'ID生成模式',
        groupPartition: '分区',
        initScene: '初始化场景',
        collapseCommon: '通用配置',
        collapseRetry: '重试配置',
        groupNameRule: '组名称: 仅支持长度为:1~64位字符.格式为:数字、字母、下划线、短横线。'
      },
      idMode: {
        idWorker: '雪花算法',
        segment: '号段模式'
      },
      addGroupConfig: '新增组管理',
      editGroupConfig: '编辑组管理',
      generateToken: '随机生成'
    },
    notifyConfig: {
      title: '告警通知列表',
      groupName: '组名称',
      businessName: '业务ID',
      notifyName: '通知名称',
      notifyStatus: '通知状态',
      notifyType: '通知类型',
      notifyScene: '通知场景',
      notifyThreshold: '通知阈值',
      description: '描述',
      notifyAttribute: '通知属性',
      retryScene: '重试场景',
      ownerName: '负责人',
      job: '定时任务',
      workflow: '工作流',
      form: {
        notifyName: '请输入通知名称',
        description: '请输入描述',
        notifyType: '请选择通知类型',
        notifyAttribute: '请求输入通知属性',
        notifyScene: '请选择通知场景',
        groupName: '请选择组名称',
        notifyThreshold: '请输入通知阈值',
        notifyStatus: '请选择状态',
        systemTaskType: '请选择任务类型',
        notifyRecipient: '请选择通知人',
        rateLimiterThreshold: '请选择阈值',
        sceneName: '请选择重试场景',
        jobName: '请选择定时任务',
        workflowName: '请选择工作流'
      },
      addNotifyConfig: '新增告警通知',
      editNotifyConfig: '编辑告警通知',
      systemTaskType: '任务类型',
      retryNotifyScene: {
        maxRetry: '场景重试数量超过阈值',
        maxRetryError: '场景重试失败数量超过阈值',
        clientReportError: '客户端上报失败',
        clientComponentError: '客户端组件异常',
        retryTaskFailError: '任务重试失败',
        retryTaskEnterDeadLetter: '任务重试失败进入死信队列',
        retryNoClientNodesError: '任务重试失败（没有可执行的客户端节点）'
      },
      jobNotifyScene: {
        jobTaskError: '任务执行失败',
        jobClientError: '客户端执行失败',
        jobNoClientNodesError: '没有可执行的客户端节点'
      },
      workflowNotifyScene: {
        workflowClientError: '客户端执行失败',
        workNoClientNodesError: '没有可执行的客户端节点',
        workTaskError: '工作流任务执行失败'
      },
      notifyRecipient: '通知人信息',
      rateLimiterStatus: '限流状态',
      rateLimiterThreshold: '每秒限流阈值'
    },
    notifyRecipient: {
      title: '通知人列表',
      detail: '通知人详情',
      recipientName: '接收人名称',
      notifyType: '通知类型',
      notifyAttribute: '属性信息',
      description: '描述',
      form: {
        description: '请输入描述',
        notifyAttribute: '请输入属性信息',
        recipientName: '请输入接收人名称',
        notifyType: '请选择通知类型',
        dingDingAts: "请输入被{'@'}人手机号或钉钉号",
        weComAts: "请输入被{'@'}人企业微信用户id",
        larkAts: "请输入被{'@'}人open_id",
        webhookUrl: '请输入URL',
        secret: '请输入密钥',
        contentType: '请选择请求类型',
        applicationJson: 'application/json',
        applicationXWwwFormUrlencoded: 'application/x-www-form-urlencoded'
      },
      addNotifyRecipient: '新增通知接收人',
      editNotifyRecipient: '编辑通知接收人',
      ats: "{'@'}通知人",
      webhookUrl: '通知地址',
      secret: '密钥',
      tos: '通知人邮箱地址',
      dingDing: '钉钉',
      email: '邮箱',
      weCom: '企业微信',
      lark: '飞书',
      webhook: 'webhook',
      contentType: '请求类型'
    },
    retryDeadLetter: {
      title: '死信任务列表',
      detail: '死信任务详情',
      uniqueId: 'UniqueId',
      groupName: '组名',
      sceneName: '场景名称',
      idempotentId: '幂等ID',
      bizNo: '业务编号',
      taskType: '任务类型',
      createDt: '创建时间',
      form: {
        title: '请输入死信任务列表',
        uniqueId: '请输入UniqueId',
        groupName: '请输入组名',
        sceneName: '请输入场景名称',
        idempotentId: '请输入幂等ID',
        bizNo: '请输入业务编号',
        taskType: '请输入任务类型',
        createDt: '请输入创建时间'
      }
    },
    retry: {
      title: '重试列表',
      detail: '重试详情',
      groupName: '组名称',
      sceneName: '场景名称',
      labels: '标签',
      idempotentId: '幂等ID',
      bizNo: '业务编号',
      executorName: '执行器名称',
      argsStr: '方法参数',
      serializerName: '方法参数序列化器名称',
      nextTriggerAt: '下次触发时间',
      retryCount: '次数',
      retryStatus: '状态',
      taskType: '类型',
      form: {
        retryStatus: '请输入重试状态',
        bizNo: '请输入业务编号',
        uniqueId: '请输入UniqueId',
        groupName: '请输入组名称',
        argsStr: '请输入执行方法参数',
        sceneName: '请输入场景名称',
        labels: '请输入标签',
        executorName: '请选择/输入执行器名称',
        taskType: '请输入任务类型',
        idempotentId: '请输入幂等ID',
        logStr: '日志信息'
      },
      retryStatusType: {
        retrying: '处理中',
        finished: '完成',
        maxRetry: '最大重试次数',
        paused: '暂停'
      },
      taskTypeDict: {
        retry: '重试数据',
        callback: '回调数据'
      },
      generateIdempotentId: '通过客户端生成',
      addRetry: '新增重试',
      editRetry: '编辑重试',
      batchAddRetry: '批量新增'
    },
    retryScene: {
      title: '场景列表',
      detail: '场景详情',
      baseConfig: '基础配置',
      cbConfig: '回调配置',
      groupName: '组名',
      sceneName: '场景名',
      notifyName: '告警通知',
      sceneStatus: '状态',
      ownerName: '负责人',
      backOff: '退避策略',
      maxRetryCount: '最大重试次数',
      triggerInterval: '间隔时间',
      deadlineRequest: '调用链超时时间',
      executorTimeout: '超时时间',
      createDt: '创建时间',
      updateDt: '更新时间',
      description: '描述',
      routeKey: '路由策略',
      blockStrategy: '阻塞策略',
      cbStatus: '回调开关',
      cbTriggerType: '回调触发类型',
      cbTriggerInterval: '回调触发间隔',
      cbMaxCount: '最大回调执行次数',
      form: {
        notifyName: '请选择告警通知名称',
        maxRetryCount: '请输入最大重试次数',
        triggerInterval: '请输入间隔时间(秒)',
        groupName: '请输入组名',
        description: '请输入描述',
        executorTimeout: '请输入超时时间(秒)',
        sceneName: '请输入场景名',
        ownerId: '请输入负责人',
        sceneStatus: '请输入状态',
        deadlineRequest: '请输入调用链超时时间(毫秒)',
        routeKey: '请输入路由策略',
        backOff: '请输入退避策略',
        sceneName2: '场景名称: 仅支持长度为:1~64位字符.格式为:数字、字母、下划线和中横线。',
        cbTriggerType: '请选择回调触发类型',
        cbTriggerInterval: '请输入触发间隔',
        cbMaxCount: '请输入回调次数'
      },
      addScene: '新增场景',
      editScene: '编辑场景',
      backOffItem: {
        delayLevel: '延迟等级',
        fixed: '固定时间',
        cron: 'CRON表达式',
        random: '随机等待'
      }
    },
    retryTask: {
      title: '重试任务列表',
      detail: '重试详情',
      groupName: '组名称',
      sceneName: '场景名称',
      taskStatus: '状态',
      taskType: '类型',
      idempotentId: '幂等ID',
      bizNo: '业务编号',
      createDt: '创建时间',
      updateDt: '更新时间',
      operationReason: '操作原因',
      retryId: '重试ID',
      form: {
        groupName: '请输入组名称',
        idempotentId: '请输入幂等id',
        sceneName: '请输入场景名称',
        bizNo: '请输入业务编号',
        retryId: '请输入重试ID'
      },
      addRetryTask: '新增重试任务',
      editRetryTask: '编辑重试任务'
    },
    workflow: {
      title: '工作流列表',
      workflowName: '工作流名称',
      groupName: '组名称',
      ownerName: '负责人',
      nextTriggerAt: '触发时间',
      workflowStatus: '状态',
      triggerType: '触发类型',
      triggerInterval: '间隔时长',
      executorTimeout: '超时时间',
      updateDt: '更新时间',
      form: {
        workflowName: '请输入工作流名称',
        groupName: '请输入组名称',
        workflowStatus: '请输入状态'
      },
      addWorkflow: '新增工作流',
      editWorkflow: '编辑工作流'
    },
    workflowBatch: {
      title: '工作流批次列表',
      workflowName: '工作流名称',
      groupName: '组名称',
      executionAt: '执行时间',
      taskBatchStatus: '状态',
      operationReason: '操作原因',
      createDt: '创建时间',
      form: {
        workflowName: '请输入工作流名称',
        taskBatchStatus: '请输入状态',
        groupName: '请输入组名称'
      },
      addWorkflowBatch: '新增工作流批次',
      editWorkflowBatch: '编辑工作流批次'
    },
    jobTask: {
      title: '定时任务列表',
      groupName: '组名称',
      ownerName: '负责人',
      jobName: '任务名称',
      labels: '标签',
      argsStr: '方法参数',
      shardNum: 'reduce 分片数',
      argsType: '参数类型',
      nextTriggerAt: '触发时间',
      jobStatus: '状态',
      routeKey: '路由策略',
      executorType: '执行器类型',
      executorInfo: '执行器名称',
      triggerType: '触发类型',
      triggerInterval: '间隔时长',
      blockStrategy: '阻塞策略',
      executorTimeout: '超时时间(秒)',
      maxRetryTimes: '最大重试次数',
      retryInterval: '重试间隔',
      taskType: '任务类型',
      parallelNum: '并行数',
      bucketIndex: 'Bucket',
      description: '描述',
      updateDt: '更新时间',
      notifyId: '告警通知',
      executorsType: '执行器类型',
      form: {
        jobStatus: '请输入状态',
        ownerName: '请输入负责人名称',
        maxRetryTimes: '请输入最大重试次数',
        description: '请输入描述',
        triggerType: '请输入触发类型',
        jobName: '请输入任务名称',
        executorTimeout: '请输入超时时间',
        triggerInterval: '请输入间隔时长(秒)',
        triggerInterval_CRON: '请输入间隔时长',
        taskType: '请输入任务类型',
        parallelNum: '请输入并行数',
        bucketIndex: '请输入Bucket',
        executorType: '请输入执行器类型',
        executorInfo: '请输入执行器名称',
        routeKey: '请输入路由策略',
        blockStrategy: '请输入阻塞策略',
        argsType: '请输入参数类型',
        argsStr: '请输入方法参数',
        shardNum: '请输入 reduce 分片数',
        groupName: '请输入组名称',
        retryInterval: '请输入重试间隔',
        notifyId: '请输入选择告警配置'
      },
      addJobTask: '新增定时任务',
      editJobTask: '编辑定时任务',
      triggerTypeItem: {
        fixed: '固定时间',
        cron: 'CRON表达式',
        specifiedTime: '指定时间点',
        workflow: '工作流'
      },
      detail: '定时任务详情'
    },
    jobBatch: {
      title: '任务批次列表',
      groupName: '组名称',
      jobName: '任务名称',
      taskType: '任务类型',
      executorInfo: '执行器名称',
      executorType: '执行器类型',
      executionAt: '开始执行时间',
      duration: '执行时长(秒)',
      taskBatchStatus: '状态',
      operationReason: '操作原因',
      form: {
        groupName: '请输入组名称',
        jobName: '请输入任务名称',
        taskBatchStatus: '请输入状态'
      },
      detail: '执行批次详情',
      jobTask: {
        title: 'JobTask 列表',
        id: 'ID',
        groupName: '组名称',
        taskStatus: '状态',
        clientInfo: '地址',
        argsStr: '参数',
        resultMessage: '结果',
        retryCount: '重试次数',
        createDt: '开始执行时间'
      }
    },
    userManager: {
      title: '用户列表',
      username: '用户名',
      role: '角色',
      permissions: '权限',
      checkPassword: '确认密码',
      password: '密码',
      updatePassword: '更新密码',
      permissionList: '权限列表',
      oldPassword: '旧密码',
      newPassword: '新密码',
      form: {
        ownerName: '请选择负责人',
        role: '请输入角色',
        password: '请输入密码',
        username: '请输入用户名',
        checkPassword: '请输入确认密码',
        permissions: '请选择组',
        namespaceIds: '请选择命名空间',
        oldPassword: '请输入旧密码',
        newPassword: '请输入新密码'
      },
      addUser: '新增用户',
      editUser: '编辑用户',
      roleItem: {
        user: '普通用户',
        admin: '管理员'
      }
    },
    log: {
      title: '日志详情',
      view: '查看日志',
      info: '基本信息'
    }
  },
  workflow: {
    node: {
      priority: '优先级',
      task: {
        name: '任务',
        add: '添加任务',
        nodeName: '任务节点',
        conditionNodes: {
          nodeName: '任务 1'
        }
      },
      condition: {
        nodeName: '决策节点',
        conditionNodes: {
          nodeName: '条件',
          otherNodeName: '其他情况',
          otherTip: '该分支为系统默认创建，与其他分支互斥。只有当其他分支都无法运行时，才会运行该分支。',
          priority: '优先级',
          conditionTip: '请设置条件',
          logicalCondition: '判定逻辑',
          expressionType: '表达式类型',
          nodeExpression: '节点表达式',
          otherNodeTip: '如存在未满足其他分支条件的情况，则进入此分支'
        },
        addBranch: '添加条件'
      },
      callback: {
        nodeName: '回调通知',
        conditionNodes: {
          nodeName: '回调通知',
          contentType: '请求类型',
          webhookTip: '请配置回调通知'
        }
      },
      endNode: '流程结束',
      log: {
        title: '日志详情'
      }
    }
  },
  form: {
    required: '不能为空',
    userName: {
      required: '请输入用户名',
      invalid: '用户名格式不正确'
    },
    phone: {
      required: '请输入手机号',
      invalid: '手机号格式不正确'
    },
    pwd: {
      required: '请输入密码',
      invalid: '密码格式不正确，6-18位字符，包含字母、数字、下划线'
    },
    confirmPwd: {
      required: '请输入确认密码',
      invalid: '两次输入密码不一致'
    },
    code: {
      required: '请输入验证码',
      invalid: '验证码格式不正确'
    },
    email: {
      required: '请输入邮箱',
      invalid: '邮箱格式不正确'
    }
  },
  dropdown: {
    closeCurrent: '关闭',
    closeOther: '关闭其它',
    closeLeft: '关闭左侧',
    closeRight: '关闭右侧',
    closeAll: '关闭所有'
  },
  icon: {
    themeConfig: '主题配置',
    themeSchema: '主题模式',
    lang: '切换语言',
    fullscreen: '全屏',
    fullscreenExit: '退出全屏',
    magnify: '放大',
    restore: '还原',
    reload: '刷新页面',
    collapse: '折叠菜单',
    expand: '展开菜单',
    pin: '固定',
    unpin: '取消固定',
    namespace: '切换命名空间'
  },
  datatable: {
    itemCount: '共 {total} 条'
  }
};

export default local;
