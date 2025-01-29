DROP TABLE IF EXISTS `sys_identity_role`;


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

