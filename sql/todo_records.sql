CREATE TABLE IF NOT EXISTS `todo_records` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `biz_type` varchar(64) NOT NULL COMMENT '业务类型',
  `biz_id` varchar(64) NOT NULL COMMENT '业务主键',
  `title` varchar(128) NOT NULL COMMENT '待办标题',
  `content` varchar(512) DEFAULT NULL COMMENT '待办内容',
  `receiver_type` varchar(32) NOT NULL COMMENT '接收方类型',
  `receiver_id` bigint DEFAULT NULL COMMENT '接收方ID',
  `status` varchar(32) NOT NULL COMMENT '状态',
  `source_event` varchar(64) DEFAULT NULL COMMENT '来源事件',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `completed_time` datetime DEFAULT NULL COMMENT '完成时间',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_todo_receiver` (`receiver_type`, `receiver_id`, `status`),
  KEY `idx_todo_biz` (`biz_type`, `biz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='待办记录表';
