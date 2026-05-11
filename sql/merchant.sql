-- 商家信息表
CREATE TABLE IF NOT EXISTS `merchant` (
    `merchant_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商家ID',
    `user_id` BIGINT NOT NULL COMMENT '关联用户ID',
    `merchant_name` VARCHAR(128) NOT NULL COMMENT '商家名称',
    `business_license` VARCHAR(64) DEFAULT NULL COMMENT '营业执照号',
    `contact_person` VARCHAR(64) NOT NULL COMMENT '联系人',
    `contact_phone` VARCHAR(16) NOT NULL COMMENT '联系电话',
    `address` VARCHAR(255) DEFAULT NULL COMMENT '商家地址',
    `description` VARCHAR(512) DEFAULT NULL COMMENT '商家简介',
    `logo` VARCHAR(255) DEFAULT NULL COMMENT '商家Logo',
    `status` VARCHAR(16) NOT NULL DEFAULT 'ACTIVE' COMMENT '商家状态: ACTIVE-正常, SUSPENDED-暂停, CLOSED-关闭',
    `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`merchant_id`),
    UNIQUE KEY `uk_merchant_user_id` (`user_id`),
    KEY `idx_merchant_name` (`merchant_name`),
    KEY `idx_merchant_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商家信息表';
