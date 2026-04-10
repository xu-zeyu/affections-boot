package com.jinHan.gold.core.log.domain.command;

import com.affectionsboot.starter.mybatis.PageParam;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 日志分页查询命令
 */
@Data
@Accessors(chain = true)
public class LogPageCommand extends PageParam {

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作类型
     */
    private String operationType;

    /**
     * 是否成功
     */
    private Boolean success;
    
    /**
     * 请求方法
     */
    private String requestMethod;
}
