DROP TABLE IF EXISTS `sys_permission`;


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

