DROP TABLE IF EXISTS `sys_menu`;


CREATE TABLE `sys_menu`
(
    `menu_id`         bigint(20) unsigned  NOT NULL,
    `parent_menu_id`  bigint(20) unsigned  NOT NULL DEFAULT '0' COMMENT '父级菜单id',
    `state`           tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '启禁用状态',
    `hide`            tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '隐藏状态',
    `constant`        tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否常量路由',
    `multi_tab`       tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '多标签页',
    `menu_type`       varchar(32)  NOT NULL DEFAULT '' COMMENT '菜单类型',
    `system_code`     varchar(32)  NOT NULL DEFAULT '' COMMENT '系统代码',
    `permission_code` varchar(256) NOT NULL DEFAULT '' COMMENT '权限code',
    `menu_name`       varchar(64)  NOT NULL DEFAULT '' COMMENT '菜单名称',
    `lang_key`        varchar(256) NOT NULL DEFAULT '' COMMENT '国际化Key',
    `menu_icon`       varchar(256) NOT NULL DEFAULT '' COMMENT '菜单图标',
    `icon_type`       varchar(32)  NOT NULL DEFAULT '' COMMENT '图标类型(iconify local)',
    `route_name`      varchar(256) NOT NULL DEFAULT '' COMMENT '路由名称',
    `route_path`      varchar(256) NOT NULL DEFAULT '' COMMENT '路由路径',
    `component`       varchar(256) NOT NULL DEFAULT '' COMMENT '组件路径',
    `query`           varchar(512) NOT NULL DEFAULT '' COMMENT '查询参数',
    `href`            varchar(512) NOT NULL DEFAULT '' COMMENT '外部链接',
    `sort`            int(11) NOT NULL DEFAULT '0' COMMENT '排序',
    `created_time`    datetime     NOT NULL,
    `updated_time`    datetime     NOT NULL,
    `create_user`     varchar(32)  NOT NULL DEFAULT '' COMMENT '创建人名称',
    `create_user_id`  bigint(20) unsigned  NOT NULL DEFAULT '0' COMMENT '创建人id',
    `update_user`     varchar(32)  NOT NULL DEFAULT '' COMMENT '更新人名称',
    `update_user_id`  bigint(20) unsigned  NOT NULL DEFAULT '0' COMMENT '更新人id',
    `is_delete`       tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '删除状态',
    `version`         int(10) unsigned NOT NULL DEFAULT '0' COMMENT '数据版本号',
    PRIMARY KEY (`menu_id`) USING BTREE,
    KEY               `parent_menu_id` (`parent_menu_id`) USING BTREE,
    KEY               `system_code` (`system_code`) USING BTREE,
    KEY               `created_time` (`created_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统菜单表';
