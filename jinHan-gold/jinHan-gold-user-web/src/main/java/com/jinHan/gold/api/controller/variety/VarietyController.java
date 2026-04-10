package com.jinHan.gold.api.controller.variety;

import com.affectionsboot.common.model.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.core.variety.domain.handler.VarietyQueryHandler;
import com.jinHan.gold.core.variety.domain.model.Variety;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 类名: VarietyController
 * 描述: 用户端品种接口
 * 作者: xuzeyu
 * 创建时间: 2025/1/27
 */
@RestController
@RequestMapping("/variety")
public class VarietyController {

    @Resource
    private VarietyQueryHandler varietyQueryHandler;

    /**
     * 获取热门品种列表（前8条）
     */
    @GetMapping("/hot")
    public Result<List<Variety>> getHotVarietyList() {
        // 查询启用的品种，按排序字段升序，限制8条
        List<Variety> varietyList = varietyQueryHandler.findAll();
        
        // 过滤启用的品种并按排序字段排序，取前8条
        List<Variety> hotVarietyList = varietyList.stream()
                .filter(v -> v.getStatus() != null && v.getStatus() == 1)
                .sorted((v1, v2) -> {
                    int sort1 = v1.getSortOrder() != null ? v1.getSortOrder() : 0;
                    int sort2 = v2.getSortOrder() != null ? v2.getSortOrder() : 0;
                    return Integer.compare(sort1, sort2);
                })
                .limit(8)
                .toList();
        
        return Result.success(hotVarietyList);
    }
}
