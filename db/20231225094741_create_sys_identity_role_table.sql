DROP TABLE IF EXISTS `sys_identity_role`;


CREATE TABLE `sys_identity_role`
(
    `id`             bigint(20) unsigned NOT NULL,
    `role_id`        bigint(20) unsigned NOT NULL DEFAULT '0',
    `identity_id`    bigint(20) unsigned NOT NULL DEFAULT '0',
    `created_time`   datetime    NOT NULL,
    `create_user`    varchar(32) NOT NULL DEFAULT '',
    `create_user_id` bigint(20) unsigned NOT NULL DEFAULT '0',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE,
    KEY              `role_id` (`role_id`) USING BTREE,
    KEY              `identity_id` (`identity_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='身份角色表';

