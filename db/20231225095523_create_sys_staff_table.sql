DROP TABLE IF EXISTS `sys_staff`;


CREATE TABLE `sys_staff`
(
    `staff_id`       bigint(20) unsigned  NOT NULL,
    `user_id`        bigint(20) unsigned  NOT NULL DEFAULT '0' COMMENT '系统用户id',
    `staff_state`    varchar(32)  NOT NULL DEFAULT '' COMMENT '员工状态',
    `org_id`         bigint(20) unsigned  NOT NULL DEFAULT '0' COMMENT '所属组织',
    `department_id`  bigint(20) unsigned  NOT NULL DEFAULT '0' COMMENT '所属部门',
    `id_number`      varchar(64)  NOT NULL DEFAULT '' COMMENT '身份证号码',
    `job_number`     varchar(32)  NOT NULL DEFAULT '' COMMENT '工号',
    `name`           varchar(128) NOT NULL DEFAULT '' COMMENT '姓名',
    `sex`            varchar(32)  NOT NULL DEFAULT '' COMMENT '性别',
    `job_level_id`   bigint(20) unsigned  NOT NULL DEFAULT '0' COMMENT '职级id',
    `address`        varchar(32)  NOT NULL DEFAULT '' COMMENT '联系地址',
    `entry_date`     datetime              DEFAULT NULL COMMENT '入职日期',
    `leave_date`     datetime              DEFAULT NULL COMMENT '离职日期',
    `created_time`   datetime     NOT NULL,
    `updated_time`   datetime     NOT NULL,
    `create_user`    varchar(32)  NOT NULL DEFAULT '',
    `create_user_id` bigint(20) unsigned  NOT NULL DEFAULT '0',
    `update_user`    varchar(32)  NOT NULL DEFAULT '',
    `update_user_id` bigint(20) unsigned  NOT NULL DEFAULT '0',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`        int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`staff_id`) USING BTREE,
    KEY              `updated_time` (`updated_time`) USING BTREE,
    KEY              `created_time` (`created_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工数据表';

