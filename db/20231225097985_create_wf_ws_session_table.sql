DROP TABLE IF EXISTS `wf_ws_session`;


CREATE TABLE `wf_ws_session`
(
    `id`             varchar(32)  NOT NULL,
    `sid`            varchar(128) NOT NULL DEFAULT '',
    `terminal_sn`    varchar(32)  NOT NULL DEFAULT '' COMMENT '终端编号',
    `point`          varchar(32)  NOT NULL DEFAULT '' COMMENT '链接端点',
    `ws_serv`        varchar(32)  NOT NULL DEFAULT '' COMMENT 'session服务',
    `created_time`   datetime     NOT NULL,
    `updated_time`   datetime     NOT NULL,
    `create_user`    varchar(32)  NOT NULL DEFAULT '',
    `create_user_id` varchar(32)  NOT NULL DEFAULT '',
    `update_user`    varchar(32)  NOT NULL DEFAULT '',
    `update_user_id` varchar(32)  NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`        int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`),
    KEY              `terminal_sn` (`terminal_sn`, `point`),
    KEY              `ws_serv` (`ws_serv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='session信息';

