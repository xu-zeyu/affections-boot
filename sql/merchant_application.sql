-- 商家申请表
CREATE TABLE IF NOT EXISTS `merchant_application` (
    `application_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '申请ID',
    `user_id` BIGINT NOT NULL COMMENT '申请用户ID',
    `merchant_name` VARCHAR(128) NOT NULL COMMENT '商家名称',
    `business_license` VARCHAR(64) DEFAULT NULL COMMENT '营业执照号',
    `contact_person` VARCHAR(64) NOT NULL COMMENT '联系人',
    `contact_phone` VARCHAR(16) NOT NULL COMMENT '联系电话',
    `address` VARCHAR(255) DEFAULT NULL COMMENT '商家地址',
    `description` VARCHAR(512) DEFAULT NULL COMMENT '商家简介',
    `logo` VARCHAR(255) DEFAULT NULL COMMENT '商家Logo',
    `status` VARCHAR(16) NOT NULL DEFAULT 'PENDING' COMMENT '申请状态: PENDING-待审核, APPROVED-已通过, REJECTED-已拒绝',
    `reject_reason` VARCHAR(255) DEFAULT NULL COMMENT '拒绝原因',
    `reviewed_by` BIGINT DEFAULT NULL COMMENT '审核人ID',
    `reviewed_time` DATETIME DEFAULT NULL COMMENT '审核时间',
    `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`application_id`),
    KEY `idx_application_user_id` (`user_id`),
    KEY `idx_application_status` (`status`),
    KEY `idx_application_created_time` (`created_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商家申请表';
