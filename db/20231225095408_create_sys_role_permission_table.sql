DROP TABLE IF EXISTS `sys_role_permission`;


CREATE TABLE `sys_role_permission`
(
    `id`              bigint(20) unsigned  NOT NULL,
    `role_id`         bigint(20) unsigned  NOT NULL DEFAULT '0',
    `system_code`     varchar(32)  NOT NULL DEFAULT '',
    `permission_code` varchar(128) NOT NULL DEFAULT '',
    `created_time`    datetime     NOT NULL,
    `create_user`     varchar(32)  NOT NULL DEFAULT '',
    `create_user_id`  bigint(20) unsigned  NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE,
    KEY               `system_role_id` (`system_code`,`role_id`) USING BTREE,
    KEY               `role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统角色权限表';

