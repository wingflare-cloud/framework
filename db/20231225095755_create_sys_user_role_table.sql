DROP TABLE IF EXISTS `sys_user_role`;


CREATE TABLE `sys_user_role`
(
    `id`             bigint(20) unsigned NOT NULL,
    `user_id`        bigint(20) unsigned NOT NULL DEFAULT '0',
    `system_code`    varchar(32) NOT NULL DEFAULT '',
    `role_id`        bigint(20) unsigned NOT NULL DEFAULT '0',
    `created_time`   datetime    NOT NULL,
    `create_user`    varchar(32) NOT NULL DEFAULT '',
    `create_user_id` bigint(20) unsigned NOT NULL DEFAULT '0',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE,
    KEY              `user_id` (`user_id`) USING BTREE,
    KEY              `system_role_id` (`system_code`,`role_id`) USING BTREE,
    KEY              `role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统用户角色表';

