CREATE TABLE `base_identity`
(
    `identity_id`    varchar(32)  NOT NULL,
    `identity_code`  varchar(32)  NOT NULL DEFAULT '' COMMENT '身份代码',
    `org_id`         varchar(32)  NOT NULL DEFAULT '' COMMENT '组织机构id',
    `department_id`  varchar(32)  NOT NULL DEFAULT '' COMMENT '部门id',
    `identity_name`  varchar(256) NOT NULL DEFAULT '' COMMENT '岗位名称',
    `job_level_id`   varchar(32)  NOT NULL DEFAULT '' COMMENT '职级id',
    `created_time`   datetime     NOT NULL,
    `updated_time`   datetime     NOT NULL,
    `create_user`    varchar(32)  NOT NULL DEFAULT '',
    `create_user_id` varchar(32)  NOT NULL DEFAULT '',
    `update_user`    varchar(32)  NOT NULL DEFAULT '',
    `update_user_id` varchar(32)  NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`        int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`identity_id`) USING BTREE,
    KEY              `created_time` (`created_time`) USING BTREE,
    KEY              `org_id` (`org_id`) USING BTREE,
    KEY              `job_level_id` (`job_level_id`) USING BTREE,
    KEY              `department_id` (`department_id`) USING BTREE,
    KEY              `identity_name` (`identity_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位身份数据表';


CREATE TABLE `base_job_level_classify`
(
    `level_classify_id` varchar(32)  NOT NULL,
    `classify_name`     varchar(256) NOT NULL DEFAULT '' COMMENT '分类名',
    `classify_code`     varchar(32)  NOT NULL DEFAULT '' COMMENT '分类代码',
    `created_time`      datetime     NOT NULL,
    `updated_time`      datetime     NOT NULL,
    `create_user`       varchar(32)  NOT NULL DEFAULT '',
    `create_user_id`    varchar(32)  NOT NULL DEFAULT '',
    `update_user`       varchar(32)  NOT NULL DEFAULT '',
    `update_user_id`    varchar(32)  NOT NULL DEFAULT '',
    `is_delete`         tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`           int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`level_classify_id`) USING BTREE,
    KEY                 `classify_name` (`classify_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='职级分类表';


CREATE TABLE `base_job_level`
(
    `job_level_id`      varchar(32)  NOT NULL,
    `level_classify_id` varchar(32)  NOT NULL DEFAULT '' COMMENT '职级分类id',
    `level_name`        varchar(256) NOT NULL DEFAULT '' COMMENT '职级名称',
    `root_level`        int(11) NOT NULL DEFAULT '0' COMMENT '职级全局数值',
    `classify_level`    int(11) NOT NULL DEFAULT '0' COMMENT '职级分类数值',
    `created_time`      datetime     NOT NULL,
    `updated_time`      datetime     NOT NULL,
    `create_user`       varchar(32)  NOT NULL DEFAULT '',
    `create_user_id`    varchar(32)  NOT NULL DEFAULT '',
    `update_user`       varchar(32)  NOT NULL DEFAULT '',
    `update_user_id`    varchar(32)  NOT NULL DEFAULT '',
    `is_delete`         tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`           int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`job_level_id`) USING BTREE,
    KEY                 `level_name` (`level_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='职级表';


CREATE TABLE `base_org`
(
    `org_id`          varchar(32)  NOT NULL,
    `org_code`        varchar(32)  NOT NULL DEFAULT '' COMMENT '组织代码',
    `parent_org_id`   varchar(32)  NOT NULL DEFAULT '' COMMENT '父级机构id',
    `state`           tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '组织状态',
    `org_type`        varchar(32)  NOT NULL DEFAULT '' COMMENT '组织类型',
    `org_name`        varchar(256) NOT NULL DEFAULT '' COMMENT '组织名称',
    `simple_org_name` varchar(256) NOT NULL DEFAULT '' COMMENT '组织简称',
    `org_phone`       varchar(64)  NOT NULL DEFAULT '' COMMENT '组织手机或固话号',
    `org_address`     varchar(256) NOT NULL DEFAULT '' COMMENT '详细地址',
    `role_id`         varchar(32)  NOT NULL DEFAULT '' COMMENT '机构基础角色id',
    `user_id`         varchar(32)  NOT NULL DEFAULT '' COMMENT '机构主要负责人id',
    `created_time`    datetime     NOT NULL,
    `updated_time`    datetime     NOT NULL,
    `create_user`     varchar(32)  NOT NULL DEFAULT '',
    `create_user_id`  varchar(32)  NOT NULL DEFAULT '',
    `update_user`     varchar(32)  NOT NULL DEFAULT '',
    `update_user_id`  varchar(32)  NOT NULL DEFAULT '',
    `is_delete`       tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`         int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`org_id`) USING BTREE,
    KEY               `created_time` (`created_time`) USING BTREE,
    KEY               `parent_org_id` (`parent_org_id`) USING BTREE,
    KEY               `user_id` (`user_id`) USING BTREE,
    KEY               `org_type` (`org_type`) USING BTREE,
    KEY               `org_name` (`org_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织机构表';


CREATE TABLE `base_org_department`
(
    `department_id`        varchar(32)  NOT NULL,
    `state`                tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '部门状态',
    `org_id`               varchar(32)  NOT NULL DEFAULT '' COMMENT '组织id',
    `parent_department_id` varchar(32)  NOT NULL DEFAULT '' COMMENT '父级部门',
    `department_name`      varchar(256) NOT NULL DEFAULT '' COMMENT '部门名称',
    `role_id`              varchar(32)  NOT NULL DEFAULT '' COMMENT '部门基础角色id',
    `user_id`              varchar(32)  NOT NULL DEFAULT '' COMMENT '部门主要负责人id',
    `created_time`         datetime     NOT NULL,
    `updated_time`         datetime     NOT NULL,
    `create_user`          varchar(32)  NOT NULL DEFAULT '',
    `create_user_id`       varchar(32)  NOT NULL DEFAULT '',
    `update_user`          varchar(32)  NOT NULL DEFAULT '',
    `update_user_id`       varchar(32)  NOT NULL DEFAULT '',
    `is_delete`            tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`              int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`department_id`) USING BTREE,
    KEY                    `created_time` (`created_time`) USING BTREE,
    KEY                    `org_id` (`org_id`) USING BTREE,
    KEY                    `user_id` (`user_id`) USING BTREE,
    KEY                    `parent_department_id` (`parent_department_id`) USING BTREE,
    KEY                    `org_name` (`department_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织部门表';


CREATE TABLE `sys_dict`
(
    `dict_id`        varchar(32)  NOT NULL,
    `system_code`    varchar(32)  NOT NULL DEFAULT '' COMMENT '系统代码',
    `state`          tinyint(1) unsigned NOT NULL DEFAULT '0',
    `dict_type`      varchar(32)  NOT NULL DEFAULT '' COMMENT '字典类型',
    `dict_code`      varchar(128) NOT NULL DEFAULT '' COMMENT '字典代码',
    `dict_name`      varchar(64)  NOT NULL DEFAULT '' COMMENT '字典名称',
    `dict_value`     varchar(256) NOT NULL DEFAULT '' COMMENT '字典值',
    `dict_text`      varchar(256) NOT NULL DEFAULT '' COMMENT '字典文本',
    `sort`           int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序值',
    `created_time`   datetime     NOT NULL,
    `updated_time`   datetime     NOT NULL,
    `create_user`    varchar(32)  NOT NULL DEFAULT '',
    `create_user_id` varchar(32)  NOT NULL DEFAULT '',
    `update_user`    varchar(32)  NOT NULL DEFAULT '',
    `update_user_id` varchar(32)  NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`        int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`dict_id`) USING BTREE,
    KEY              `created_time` (`created_time`) USING BTREE,
    KEY              `dict_code` (`system_code`,`dict_code`) USING BTREE,
    KEY              `dict_name` (`system_code`,`dict_name`) USING BTREE,
    KEY              `dict_name2` (`dict_name`) USING BTREE,
    KEY              `dict_code2` (`dict_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统字典表';


CREATE TABLE `sys_file`
(
    `file_id`         varchar(32)  NOT NULL,
    `state`           varchar(32)  NOT NULL DEFAULT '' COMMENT '文件状态',
    `store_drive`     varchar(32)  NOT NULL DEFAULT '' COMMENT '存储驱动，字典storeDrive',
    `conf_key`        varchar(32)           DEFAULT '' COMMENT '配置key',
    `md5_hash`        char(32)     NOT NULL DEFAULT '' COMMENT '文件md5值',
    `sha256_hash`     char(32)     NOT NULL DEFAULT '' COMMENT '文件sha256值',
    `path`            varchar(256) NOT NULL DEFAULT '' COMMENT '文件存储路径',
    `domain`          varchar(256) NOT NULL DEFAULT '' COMMENT '文件访问域名',
    `size`            decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '文件大小，单位kb',
    `expiration_time` varchar(32)           DEFAULT NULL COMMENT '过期时间',
    `created_time`    datetime     NOT NULL,
    `updated_time`    datetime     NOT NULL,
    `create_user`     varchar(32)  NOT NULL DEFAULT '',
    `create_user_id`  varchar(32)  NOT NULL DEFAULT '',
    `update_user`     varchar(32)  NOT NULL DEFAULT '',
    `update_user_id`  varchar(32)  NOT NULL DEFAULT '',
    `is_delete`       tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`         int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`file_id`) USING BTREE,
    KEY               `md5_hash` (`md5_hash`, `sha256_hash`) USING BTREE,
    KEY               `sha256_hash` (`sha256_hash`) USING BTREE,
    KEY               `store_drive` (`store_drive`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统文件表';


CREATE TABLE `sys_file_put_policy`
(
    `policy_id`       varchar(32)  NOT NULL DEFAULT '',
    `status`          varchar(32)  NOT NULL DEFAULT '' COMMENT '策略状态',
    `conf_key`        varchar(32)  NOT NULL DEFAULT '' COMMENT '上传配置',
    `store_drive`     varchar(32)  NOT NULL DEFAULT '' COMMENT '上传驱动',
    `size_min`        decimal(16, 2) unsigned NOT NULL DEFAULT 0.00 COMMENT '文件大小，最小值',
    `size_max`        decimal(16, 2) unsigned NOT NULL DEFAULT 0.00 COMMENT '文件大小，最大值',
    `mime_limit`      text         NOT NULL COMMENT '文件mime类型限制',
    `file_id`         varchar(32)  NOT NULL DEFAULT '' COMMENT '文件id',
    `expiration_time` datetime              DEFAULT NULL COMMENT '过期时间',
    `auto_remove`     tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否自动删除',
    `remote_url`      varchar(512) NOT NULL DEFAULT '' COMMENT '远程下载地址',
    `created_time`    datetime     NOT NULL,
    PRIMARY KEY (`policy_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='文件上传策略表';


CREATE TABLE `sys_menu`
(
    `menu_id`         varchar(32)  NOT NULL,
    `parent_menu_id`  varchar(32)  NOT NULL DEFAULT '' COMMENT '父级菜单id',
    `state`           tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '启禁用状态',
    `menu_type`       varchar(32)  NOT NULL DEFAULT '' COMMENT '菜单类型',
    `system_code`     varchar(32)  NOT NULL DEFAULT '' COMMENT '系统代码',
    `permission_code` varchar(256) NOT NULL DEFAULT '' COMMENT '权限code',
    `menu_name`       varchar(64)  NOT NULL DEFAULT '' COMMENT '菜单名称',
    `menu_icon`       varchar(256) NOT NULL DEFAULT '' COMMENT '菜单图标',
    `menu_path`       varchar(256) NOT NULL DEFAULT '' COMMENT '菜单路径',
    `menu_code`       varchar(256) NOT NULL DEFAULT '' COMMENT '菜单代码',
    `href`            varchar(512) NOT NULL DEFAULT '' COMMENT '外部链接',
    `sort`            int(11) NOT NULL DEFAULT '0' COMMENT '排序',
    `created_time`    datetime     NOT NULL,
    `updated_time`    datetime     NOT NULL,
    `create_user`     varchar(32)  NOT NULL DEFAULT '' COMMENT '创建人名称',
    `create_user_id`  varchar(32)  NOT NULL COMMENT '创建人id',
    `update_user`     varchar(32)  NOT NULL DEFAULT '' COMMENT '更新人名称',
    `update_user_id`  varchar(32)  NOT NULL COMMENT '更新人id',
    `is_delete`       tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '删除状态',
    `version`         int(10) unsigned NOT NULL DEFAULT '0' COMMENT '数据版本号',
    PRIMARY KEY (`menu_id`) USING BTREE,
    KEY               `parent_menu_id` (`parent_menu_id`) USING BTREE,
    KEY               `system_code` (`system_code`) USING BTREE,
    KEY               `created_time` (`created_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统菜单表';


CREATE TABLE `sys_permission`
(
    `permission_id`  varchar(32)  NOT NULL,
    `menu_id`        varchar(32)  NOT NULL DEFAULT '' COMMENT '菜单id',
    `name`           varchar(32)  NOT NULL DEFAULT '' COMMENT '权限名称',
    `code`           varchar(128) NOT NULL DEFAULT '' COMMENT '权限代码',
    `remark`         varchar(32)  NOT NULL DEFAULT '' COMMENT '权限备注',
    `created_time`   datetime     NOT NULL,
    `updated_time`   datetime     NOT NULL,
    `create_user`    varchar(32)  NOT NULL DEFAULT '',
    `create_user_id` varchar(32)  NOT NULL DEFAULT '',
    `update_user`    varchar(32)  NOT NULL DEFAULT '',
    `update_user_id` varchar(32)  NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`        int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`permission_id`) USING BTREE,
    KEY              `created_time` (`created_time`) USING BTREE,
    KEY              `menu_id` (`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统权限表';


CREATE TABLE `sys_role_group`
(
    `role_group_id`  varchar(32) NOT NULL,
    `state`          tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '角色分组状态',
    `group_name`     varchar(32) NOT NULL DEFAULT '' COMMENT '角色分组名称',
    `group_remark`   varchar(32) NOT NULL DEFAULT '' COMMENT '角色分组备注',
    `created_time`   datetime    NOT NULL,
    `updated_time`   datetime    NOT NULL,
    `create_user`    varchar(32) NOT NULL DEFAULT '',
    `create_user_id` varchar(32) NOT NULL DEFAULT '',
    `update_user`    varchar(32) NOT NULL DEFAULT '',
    `update_user_id` varchar(32) NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`        int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`role_group_id`) USING BTREE,
    KEY              `group_name` (`group_name`) USING BTREE,
    KEY              `created_time` (`created_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统角色表';


CREATE TABLE `sys_role`
(
    `role_id`        varchar(32)   NOT NULL,
    `state`          tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '角色状态',
    `role_group_id`  varchar(32)   NOT NULL DEFAULT '' COMMENT '角色分组id',
    `parent_role_id` varchar(32)   NOT NULL DEFAULT '' COMMENT '父级角色id',
    `role_id_path`   varchar(1024) NOT NULL DEFAULT '' COMMENT '角色id层级路径',
    `role_name`      varchar(32)   NOT NULL DEFAULT '' COMMENT '角色名称',
    `role_remark`    varchar(32)   NOT NULL DEFAULT '' COMMENT '角色备注',
    `created_time`   datetime      NOT NULL,
    `updated_time`   datetime      NOT NULL,
    `create_user`    varchar(32)   NOT NULL DEFAULT '',
    `create_user_id` varchar(32)   NOT NULL DEFAULT '',
    `update_user`    varchar(32)   NOT NULL DEFAULT '',
    `update_user_id` varchar(32)   NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`        int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`role_id`) USING BTREE,
    KEY              `role_group_id` (`role_group_id`) USING BTREE,
    KEY              `parent_role_id` (`parent_role_id`) USING BTREE,
    KEY              `created_time` (`created_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统角色表';


CREATE TABLE `sys_role_permission`
(
    `id`              varchar(32)  NOT NULL,
    `role_id`         varchar(32)  NOT NULL DEFAULT '',
    `system_code`     varchar(32)  NOT NULL DEFAULT '',
    `permission_code` varchar(128) NOT NULL DEFAULT '',
    `created_time`    datetime     NOT NULL,
    `create_user`     varchar(32)  NOT NULL DEFAULT '',
    `create_user_id`  varchar(32)  NOT NULL DEFAULT '',
    PRIMARY KEY (`id`) USING BTREE,
    KEY               `system_role_id` (`system_code`, `role_id`) USING BTREE,
    KEY               `role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统角色权限表';


CREATE TABLE `sys_setting`
(
    `setting_id`     varchar(32)  NOT NULL,
    `state`          tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '设置状态',
    `system_code`    varchar(32)  NOT NULL DEFAULT '' COMMENT '系统代码',
    `setting_code`   varchar(128) NOT NULL DEFAULT '' COMMENT '设置代码',
    `setting_name`   varchar(64)  NOT NULL DEFAULT '' COMMENT '设置名称',
    `setting_value`  text COMMENT '设置值',
    `setting_text`   varchar(256) NOT NULL DEFAULT '' COMMENT '设置文本描述',
    `created_time`   datetime     NOT NULL,
    `updated_time`   datetime     NOT NULL,
    `create_user`    varchar(32)  NOT NULL DEFAULT '',
    `create_user_id` varchar(32)  NOT NULL DEFAULT '',
    `update_user`    varchar(32)  NOT NULL DEFAULT '',
    `update_user_id` varchar(32)  NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`        int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`setting_id`) USING BTREE,
    KEY              `created_time` (`created_time`) USING BTREE,
    KEY              `system_name` (`system_code`,`setting_name`) USING BTREE,
    KEY              `system_name2` (`setting_name`) USING BTREE,
    KEY              `system_code` (`system_code`,`setting_code`) USING BTREE,
    KEY              `system_code2` (`setting_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统设置表';


CREATE TABLE `sys_user`
(
    `user_id`             varchar(32)  NOT NULL,
    `super_administrator` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否为超管',
    `ban_state`           tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '账户起禁用状态',
    `sex`                 tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '性别',
    `user_channel`        varchar(64)  NOT NULL DEFAULT '' COMMENT '用户注册渠道系统',
    `account_type`        json         NOT NULL COMMENT '账户类型',
    `user_name`           varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名',
    `avatar`              varchar(256) NOT NULL DEFAULT '' COMMENT '用户头像',
    `user_account`        varchar(32)  NOT NULL DEFAULT '' COMMENT '登录账户',
    `user_phone`          char(11)              DEFAULT '' COMMENT '手机号',
    `user_email`          varchar(128) NOT NULL DEFAULT '' COMMENT '邮箱号',
    `user_passwd`         varchar(256) NOT NULL DEFAULT '' COMMENT '账户密码',
    `last_login_ip`       varchar(15)  NOT NULL DEFAULT '' COMMENT '最后登录ip',
    `last_login_time`     datetime              DEFAULT NULL COMMENT '最后登录时间',
    `created_time`        datetime     NOT NULL,
    `updated_time`        datetime     NOT NULL,
    `create_user`         varchar(32)  NOT NULL DEFAULT '',
    `create_user_id`      varchar(32)  NOT NULL DEFAULT '',
    `update_user`         varchar(32)  NOT NULL DEFAULT '',
    `update_user_id`      varchar(32)  NOT NULL DEFAULT '',
    `is_delete`           tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`             int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`user_id`) USING BTREE,
    KEY                   `user_name` (`user_name`) USING BTREE,
    KEY                   `user_email` (`user_email`) USING BTREE,
    KEY                   `user_account` (`user_account`) USING BTREE,
    KEY                   `user_phone` (`user_phone`) USING BTREE,
    KEY                   `created_time` (`created_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统用户表';


CREATE TABLE `sys_staff_org`
(
    `id`             varchar(32) NOT NULL,
    `user_id`        varchar(32) NOT NULL DEFAULT '',
    `staff_id`       varchar(32) NOT NULL DEFAULT '',
    `org_id`         varchar(32) NOT NULL DEFAULT '',
    `created_time`   datetime    NOT NULL,
    `create_user`    varchar(32) NOT NULL DEFAULT '',
    `create_user_id` varchar(32) NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE,
    KEY              `user_id` (`user_id`) USING BTREE,
    KEY              `org_id` (`org_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='员工组织表';


CREATE TABLE `sys_user_role`
(
    `id`             varchar(32) NOT NULL,
    `user_id`        varchar(32) NOT NULL DEFAULT '',
    `system_code`    varchar(32) NOT NULL DEFAULT '',
    `role_id`        varchar(32) NOT NULL DEFAULT '',
    `created_time`   datetime    NOT NULL,
    `create_user`    varchar(32) NOT NULL DEFAULT '',
    `create_user_id` varchar(32) NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE,
    KEY              `user_id` (`user_id`) USING BTREE,
    KEY              `system_role_id` (`system_code`, `role_id`) USING BTREE,
    KEY              `role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统用户角色表';


CREATE TABLE `sys_staff_identity`
(
    `id`             varchar(32) NOT NULL,
    `user_id`        varchar(32) NOT NULL DEFAULT '',
    `staff_id`       varchar(32) NOT NULL DEFAULT '',
    `identity_id`    varchar(32) NOT NULL DEFAULT '',
    `created_time`   datetime    NOT NULL,
    `create_user`    varchar(32) NOT NULL DEFAULT '',
    `create_user_id` varchar(32) NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE,
    KEY              `user_id` (`user_id`) USING BTREE,
    KEY              `identity_id` (`identity_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统员工身份表';


CREATE TABLE `sys_identity_role`
(
    `id`             varchar(32) NOT NULL,
    `role_id`        varchar(32) NOT NULL DEFAULT '',
    `identity_id`    varchar(32) NOT NULL DEFAULT '',
    `created_time`   datetime    NOT NULL,
    `create_user`    varchar(32) NOT NULL DEFAULT '',
    `create_user_id` varchar(32) NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE,
    KEY              `role_id` (`role_id`) USING BTREE,
    KEY              `identity_id` (`identity_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='身份角色表';


CREATE TABLE `sys_role_mutex`
(
    `id`             varchar(32) NOT NULL,
    `role_id`        varchar(32) NOT NULL DEFAULT '',
    `mutex_role_id`  varchar(32) NOT NULL DEFAULT '',
    `created_time`   datetime    NOT NULL,
    `create_user`    varchar(32) NOT NULL DEFAULT '',
    `create_user_id` varchar(32) NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE,
    KEY              `role_id` (`role_id`) USING BTREE,
    KEY              `mutex_role_id` (`mutex_role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色互斥表';


CREATE TABLE `sys_login_info`
(
    `login_id`      varchar(32)  NOT NULL,
    `system_code`   varchar(32)  NOT NULL DEFAULT '系统code',
    `user_id`       varchar(32)  NOT NULL DEFAULT '' COMMENT '用户id',
    `org_id`        varchar(32)  NOT NULL DEFAULT '' COMMENT '机构id',
    `identity_id`   varchar(32)  NOT NULL DEFAULT '' COMMENT '身份id',
    `refresh_token` varchar(512) NOT NULL DEFAULT '' COMMENT 'refresh_token',
    `user_agent`    varchar(512) NOT NULL DEFAULT '' COMMENT 'ua',
    `ipaddr`        varchar(15)  NOT NULL DEFAULT '' COMMENT '登陆ip',
    `expire_time`   datetime     NOT NULL COMMENT '过期时间',
    `created_time`  datetime     NOT NULL,
    PRIMARY KEY (`login_id`) USING BTREE,
    KEY             `system_code` (`system_code`) USING BTREE,
    KEY             `user_id` (`user_id`) USING BTREE,
    KEY             `org_id` (`org_id`) USING BTREE,
    KEY             `identity_id` (`identity_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='登陆信息表';


CREATE TABLE `sys_login_token`
(
    `token_id`     varchar(32)  NOT NULL,
    `login_id`     varchar(32)  NOT NULL DEFAULT '' COMMENT '登陆id',
    `token_key`    varchar(512) NOT NULL DEFAULT '' COMMENT 'tokenKey',
    `expire_time`  datetime     NOT NULL COMMENT '过期时间',
    `created_time` datetime     NOT NULL,
    PRIMARY KEY (`token_id`) USING BTREE,
    KEY            `login_id` (`login_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='登陆token表';


CREATE TABLE `sys_application`
(
    `app_id`         varchar(32)  NOT NULL,
    `app_status`     varchar(32)  NOT NULL DEFAULT '' COMMENT '应用状态',
    `app_type`       varchar(32)  NOT NULL DEFAULT '' COMMENT '应用类型',
    `app_name`       varchar(32)  NOT NULL DEFAULT '' COMMENT '应用名称',
    `app_icon`       varchar(512) NOT NULL DEFAULT '' COMMENT '应用图标',
    `website_url`    varchar(512) NOT NULL DEFAULT '' COMMENT '应用官网地址',
    `merchant_id`    varchar(32)  NOT NULL DEFAULT '' COMMENT '商户id',
    `service_mch_id` varchar(32)  NOT NULL DEFAULT '' COMMENT '服务商商户id',
    `service_app_id` varchar(32)  NOT NULL DEFAULT '' COMMENT '服务商appid',
    `remark`         varchar(256) NOT NULL DEFAULT '' COMMENT '应用描述',
    `created_time`   datetime     NOT NULL,
    `create_user`    varchar(32)  NOT NULL DEFAULT '',
    `create_user_id` varchar(32)  NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='开放平台应用表';


CREATE TABLE `sys_staff`
(
    `staff_id`       varchar(32)  NOT NULL,
    `user_id`        varchar(32)  NOT NULL DEFAULT '' COMMENT '系统用户id',
    `staff_state`    varchar(32)  NOT NULL DEFAULT '' COMMENT '员工状态',
    `org_id`         varchar(32)  NOT NULL DEFAULT '' COMMENT '所属组织',
    `department_id`  varchar(32)  NOT NULL DEFAULT '' COMMENT '所属部门',
    `id_number`      varchar(64)  NOT NULL DEFAULT '' COMMENT '身份证号码',
    `job_number`     varchar(32)  NOT NULL DEFAULT '' COMMENT '工号',
    `name`           varchar(128) NOT NULL DEFAULT '' COMMENT '姓名',
    `sex`            varchar(32)  NOT NULL DEFAULT '' COMMENT '性别',
    `job_level_id`   varchar(32)  NOT NULL DEFAULT '' COMMENT '职级id',
    `address`        varchar(32)  NOT NULL DEFAULT '' COMMENT '联系地址',
    `entry_date`     datetime              DEFAULT NULL COMMENT '入职日期',
    `leave_date`     datetime              DEFAULT NULL COMMENT '离职日期',
    `created_time`   datetime     NOT NULL,
    `updated_time`   datetime     NOT NULL,
    `create_user`    varchar(32)  NOT NULL DEFAULT '',
    `create_user_id` varchar(32)  NOT NULL DEFAULT '',
    `update_user`    varchar(32)  NOT NULL DEFAULT '',
    `update_user_id` varchar(32)  NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`        int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`staff_id`) USING BTREE,
    KEY              `updated_time` (`updated_time`) USING BTREE,
    KEY              `created_time` (`created_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工数据表';