<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">WingFlare-Cloud</h1>

<h4 align="center">基于Spring-Boot、Spring-Cloud、MyBatis-Plus，采用组件化的思想实现高内聚低耦合并且高度可配置化的云开发框架</h4>

## 框架简介

* 采用前后端分离模式
* 后端采用Spring Boot、Spring Cloud、Mybatis-Plus作为框架底座
* 支持多种架构，仅需极少量的代码调整即可切换不同架构，微服务 & 单体应用架构可平滑切换
* 不强依赖任何中间件，可以根据公司或团队技术栈随意切换
* 注重代码规范，严格控制包依赖，每个工程基本都是最小依赖
* 非常注重应用安全性，在框架设计之初就带入了许多安全相关的概念
* 引入组件化的思想实现高内聚低耦合并且高度可配置化
* 适配spring-cloud-tencent & spring-cloud-alibaba，支持平滑切换。默认为alibaba

### 工程结构

~~~
project-root
├── wingflare-abstraction       // 服务抽象层
        └── wingflare-abstraction-lib-captcha       // 验证码类库抽象层
        └── wingflare-abstraction-module-base       // base基础业务抽象层
├── wingflare-adapter           // 服务适配层，抽象层的具体实现
        └── wingflare-adapter-cloud-alibaba     // spring-cloud-alibaba适配
        └── wingflare-adapter-cloud-tencent     // spring-cloud-tencent适配
        └── wingflare-adapter-gateway-nosql-redis       // 网关redis适配
        └── wingflare-adapter-module-base-nosql-redis       // 基础业务模块redis适配
        └── wingflare-adapter-module-user-nosql-redis       // 用户业务模块redis适配
        └── wingflare-adapter-spring-server-web         // web服务适配器
├── wingflare-business          // 业务层，具体业务代码的实现
        └── wingflare-business-module-auth      // 认证中心业务层
        └── wingflare-business-module-base      // 基础服务业务层
        └── wingflare-business-module-user      // 用户服务业务层
├── wingflare-dep               // 依赖层，用于管理整个框架的内外部依赖
        └── wingflare-dep-abstraction       // 抽象层依赖
        └── wingflare-dep-adapter       // 适配层依赖
        └── wingflare-dep-all       // 全局依赖
        └── wingflare-dep-business      // 业务层依赖
        └── wingflare-dep-engine        // 引擎层依赖
        └── wingflare-dep-facade        // 门面层依赖
        └── wingflare-dep-lib           // 通用类库依赖
        └── wingflare-dep-sdk           // SDK依赖
        └── wingflare-dep-starter       // 自启器依赖
├── wingflare-develop-tool      // 开发工具包，代码生成器数据库版本管理等
        └── wingflare-tool-generator        // 代码生成器
        └── wingflare-tool-migration        // 数据库版本管理
├── wingflare-engine            // 引擎层，非业务类底座基础支撑服务
        └── wingflare-engine-pay            // 支付引擎
        └── wingflare-engine-task           // 分布式任务调度引擎，定时任务管理
        └── wingflare-engine-websocket      // websocket
├── wingflare-facade            // 门面层，服务生产者与服务消费者的中间连接层，通常生产者会定义一些标准业务接口在此层
        └── wingflare-facade-module-auth        // 认证中心门面层
        └── wingflare-facade-module-base        // 基础服务门面层
        └── wingflare-facade-module-user        // 用户服务门面层
├── wingflare-gateway           // 业务网关，基于spring cloud gateway。通常不建议业务网关直接处理南北流量
├── wingflare-lib               // 通用类库
        └── wingfalre-lib-standard              // 标准库，用于定义一些全局通用的标准
        └── wingflare-lib-captcha               // 验证码库，生产方
        └── wingflare-lib-captcha-support       // 验证码库，消费方
        └── wingflare-lib-core                  // 核心库，提供一些最基础的通用工具类
        └── wingflare-lib-datascope             // 数据权限库
        └── wingflare-lib-jwt                   // jwt
        └── wingflare-lib-mybatis-plus          // MP
        └── wingflare-lib-rabbitmq              // rabbitmq
        └── wingflare-lib-redis                 // 适配redisson，自带分布式锁
        └── wingflare-lib-security              // 身份验证以及应用安全相关库
        └── wingflare-lib-spring                // spring工具库
├── wingflare-module            // 业务模块服务层，通常http、tcp、udp等控制器代码会放在该层
        └── wingflare-module-auth       // 认证中心http服务
        └── wingflare-module-base       // 基础业务http服务
        └── wingflare-module-user       // 用户业务http服务
├── wingflare-sdk               // SDK层，业务服务会事先对一些开放的业务接口定制SDK方便其他服务直接调用而无需关注业务服务本身的逻辑，通常会配合门面层做抽象，方面在单体&微服务架构上做无感切换
        └── wingflare-sdk-auth          // 认证中心SDK
        └── wingflare-sdk-base          // 基础业务SDK
        └── wingflare-sdk-user          // 用户业务SDK
├── wingflare-starter           // starter，用于存放一些功能的自启代码
        └── wingflare-starter-datascope     // 数据权限自启器
        └── wingflare-starter-secuirty      // 安全库自启器
~~~

### 交流以及反馈
* 欢迎提交ISSUS，请写清楚问题的具体原因，重现步骤和环境(上下文)
* 个人博客：http://blog.wingflare.com
* 官网（在建）：http://www.wingflare.com
* 框架文档（在建）：http://docs.wingflare.com
* QQ群：280079415
* wechat: wingflare-cloud

### 后期计划
* 目前框架基本功能大概完成70%左右，后面会持续更新，计划23年年内完成全部基本功能
* 第一阶段，增加分布式任务调度中心、基本功能前端开发（完结全部基础功能）
* 第二阶段，增加多租户功能、工作流、以及开放平台应用接入功能
* 第三阶段，增加对monogo、rocketmq、Elasticsearch的适配
* 第四阶段，增加对TCP、UDP、websocket协议的适配、方便终端设备接入
* 第五阶段，可能会分享部分已经在多家公司生产环境实践过的框架配套自动化运维方案。（包含打版部署策略、网络安全策略、服务网络规划策略等）
* 第六阶段，增加业务规则引擎、以及基于基础框架开发一些 商城/OA/ERP项目丰富框架生态

### 关于版权
本项目原则上是基于MIT协议发行的，可免费商用或者修改后署名来源后二次分发。