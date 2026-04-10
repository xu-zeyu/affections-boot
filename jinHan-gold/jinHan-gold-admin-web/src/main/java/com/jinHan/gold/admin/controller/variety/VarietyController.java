package com.jinHan.gold.admin.controller.variety;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.affectionsboot.common.model.Result;
import com.affectionsboot.log.annotation.Log;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.admin.controller.variety.request.VarietyCreateRequest;
import com.jinHan.gold.admin.controller.variety.request.VarietyPageRequest;
import com.jinHan.gold.admin.controller.variety.request.VarietyUpdateRequest;
import com.jinHan.gold.admin.controller.variety.response.VarietyResponse;
import com.jinHan.gold.core.variety.domain.command.VarietyDeleteCommand;
import com.jinHan.gold.core.variety.domain.handler.VarietyCreateCommandHandler;
import com.jinHan.gold.core.variety.domain.handler.VarietyDeleteCommandHandler;
import com.jinHan.gold.core.variety.domain.handler.VarietyQueryHandler;
import com.jinHan.gold.core.variety.domain.handler.VarietyUpdateCommandHandler;
import com.jinHan.gold.core.variety.domain.model.Variety;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 类名: VarietyController
 * 描述: 品种管理控制器
 * 作者: xuzeyu
 * 创建时间: 2026/1/17
 */
@RestController
@RequestMapping("/variety")
public class VarietyController {
    @Resource
    private VarietyCreateCommandHandler varietyCreateCommandHandler;
    @Resource
    private VarietyUpdateCommandHandler varietyUpdateCommandHandler;
    @Resource
    private VarietyDeleteCommandHandler varietyDeleteCommandHandler;
    @Resource
    private VarietyQueryHandler varietyQueryHandler;

    /**
     * 创建品种
     *
     * @param request 创建请求
     * @return 创建结果
     */
    @Log(value = "创建品种", operationType = "VARIETY_CREATE")
    @SaCheckPermission("VARIETY_LIST_CREATE")
    @PostMapping("/create")
    public Result<VarietyResponse> create(@Valid @RequestBody VarietyCreateRequest request) {
        Variety variety = varietyCreateCommandHandler.create(request.toCommand());
        return Result.success(convertToResponse(variety));
    }

    /**
     * 更新品种
     *
     * @param request 更新请求
     * @return 更新结果
     */
    @Log(value = "更新品种", operationType = "VARIETY_UPDATE")
    @SaCheckPermission("VARIETY_LIST_UPDATE")
    @PostMapping("/update")
    public Result<VarietyResponse> update(@Valid @RequestBody VarietyUpdateRequest request) {
        Variety variety = varietyUpdateCommandHandler.update(request.toCommand());
        return Result.success(convertToResponse(variety));
    }

    /**
     * 删除品种
     *
     * @param id 品种ID
     * @return 删除结果
     */
    @Log(value = "删除品种", operationType = "VARIETY_DELETE")
    @SaCheckPermission("VARIETY_LIST_DELETE")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        varietyDeleteCommandHandler.delete(new VarietyDeleteCommand(id));
    }

    /**
     * 根据ID查询品种
     *
     * @param id 品种ID
     * @return 品种详情
     */
    @Log(value = "查询品种详情", operationType = "VARIETY_GET")
    @SaCheckPermission("VARIETY_LIST")
    @GetMapping("/find/{id}")
    public VarietyResponse findById(@PathVariable("id") Long id) {
        Variety variety = varietyQueryHandler.findById(id);
        return convertToResponse(variety);
    }

    /**
     * 根据名称模糊查询品种
     *
     * @param name 品种名称
     * @return 品种详情
     */
    @Log(value = "根据名称查询品种", operationType = "VARIETY_FIND_BY_NAME")
    @SaCheckPermission("VARIETY_LIST")
    @GetMapping("/find/by-name")
    public Result<List<VarietyResponse>> findByName(@RequestParam("name") String name) {
        List<Variety> variety = varietyQueryHandler.findByName(name);
        List<VarietyResponse> response = variety.stream().map(this::convertToResponse).toList();
        return Result.success(response);
    }

    /**
     * 查询所有品种
     *
     * @return 品种详情
     */
    @Log(value = "查询所有品种", operationType = "VARIETY_FIND_ALL")
    @GetMapping("/find/all")
    public Result<List<VarietyResponse>> findAll() {
        List<Variety> variety = varietyQueryHandler.findAll();
        List<VarietyResponse> response = variety.stream().map(this::convertToResponse).toList();
        return Result.success(response);
    }


    /**
     * 分页查询品种列表
     *
     * @param request 分页请求
     * @return 分页结果
     */
    @Log(value = "分页查询品种列表", operationType = "VARIETY_PAGE")
    @SaCheckPermission("VARIETY_LIST")
    @GetMapping("/page")
    public Result<IPage<VarietyResponse>> page(VarietyPageRequest request) {
        IPage<Variety> varietyPage = varietyQueryHandler.page(request.toCommand());
        List<VarietyResponse> varietyResponseList = varietyPage.getRecords().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

        IPage<VarietyResponse> responsePage = new Page<>(
                varietyPage.getPages(),
                varietyPage.getSize()
        );
        responsePage.setRecords(varietyResponseList); // 设置转换后的记录列表
        responsePage.setTotal(varietyPage.getTotal());
        responsePage.setCurrent(varietyPage.getCurrent());
        responsePage.setSize(varietyPage.getSize());
        responsePage.setPages(varietyPage.getPages());
        return Result.success(responsePage);
    }

    /**
     * 转换实体为响应对象
     *
     * @param variety 品种实体
     * @return 响应对象
     */
    private VarietyResponse convertToResponse(Variety variety) {
        VarietyResponse response = new VarietyResponse();
        response.setId(variety.getId());
        response.setName(variety.getName());
        response.setIconUrl(variety.getIconUrl());
        response.setMinPrice(variety.getMinPrice());
        response.setSortOrder(variety.getSortOrder());
        response.setStatus(variety.getStatus());
        response.setCreatedTime(variety.getCreatedTime());
        response.setUpdatedTime(variety.getUpdatedTime());
        return response;
    }
}
