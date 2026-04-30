CREATE TABLE IF NOT EXISTS `user_pet` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '爱宠ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `pet_name` VARCHAR(64) NOT NULL COMMENT '宠物名称',
    `pet_type` VARCHAR(32) NOT NULL COMMENT '宠物类型',
    `variety_id` BIGINT DEFAULT NULL COMMENT '宠物品种ID',
    `gender` VARCHAR(8) DEFAULT NULL COMMENT '宠物性别',
    `birthday` DATE DEFAULT NULL COMMENT '宠物生日',
    `avatar` VARCHAR(255) DEFAULT NULL COMMENT '宠物头像',
    `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_pet_user_id` (`user_id`),
    KEY `idx_user_pet_variety_id` (`variety_id`),
    KEY `idx_user_pet_name` (`pet_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户爱宠表';
