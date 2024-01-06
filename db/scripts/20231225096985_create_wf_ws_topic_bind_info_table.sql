-- // create wf_ws_topic_bind_info table
-- Migration SQL that makes the change goes here.
CREATE TABLE `wf_ws_topic_bind_info`
(
    `bind_id`        varchar(32)  NOT NULL,
    `topic`          varchar(256) NOT NULL DEFAULT '' COMMENT '频道名',
    `topic_type`     varchar(32)  NOT NULL DEFAULT '' COMMENT '频道类型',
    `terminal_sn`    varchar(32)  NOT NULL DEFAULT '' COMMENT '终端sn',
    `permission_num` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '权限数值',
    `created_time`   datetime     NOT NULL,
    `updated_time`   datetime     NOT NULL,
    `create_user`    varchar(32)  NOT NULL DEFAULT '',
    `create_user_id` varchar(32)  NOT NULL DEFAULT '',
    `update_user`    varchar(32)  NOT NULL DEFAULT '',
    `update_user_id` varchar(32)  NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`        int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`bind_id`),
    KEY              `terminal_sn` (`terminal_sn`),
    KEY              `topic` (`topic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='频道终端绑定信息';


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE `wf_ws_topic_bind_info`;

