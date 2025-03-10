DROP TABLE IF EXISTS `sys_staff_org`;


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

