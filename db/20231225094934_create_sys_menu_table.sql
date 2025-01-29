DROP TABLE IF EXISTS `sys_menu`;


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

