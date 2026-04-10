package com.jinHan.gold.api.controller.fosterCare;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.affectionsboot.auth.stp.StpUserUtil;
import com.affectionsboot.common.model.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinHan.gold.api.controller.fosterCare.request.FosterCareCreateRequest;
import com.jinHan.gold.api.controller.fosterCare.request.FosterCareStatusUpdateRequest;
import com.jinHan.gold.api.controller.fosterCare.request.FosterCareUpdateRequest;
import com.jinHan.gold.core.fosterCare.domain.command.FosterCareCreateCommand;
import com.jinHan.gold.core.fosterCare.domain.command.FosterCarePageQueryCommand;
import com.jinHan.gold.core.fosterCare.domain.command.FosterCareStatusUpdateCommand;
import com.jinHan.gold.core.fosterCare.domain.command.FosterCareUpdateCommand;
import com.jinHan.gold.core.fosterCare.domain.handler.FosterCareCreateHandler;
import com.jinHan.gold.core.fosterCare.domain.handler.FosterCareQueryHandler;
import com.jinHan.gold.core.fosterCare.domain.handler.FosterCareStatusUpdateHandler;
import com.jinHan.gold.core.fosterCare.domain.handler.FosterCareUpdateHandler;
import com.jinHan.gold.core.fosterCare.domain.model.FosterCareRecord;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 类名: FosterCareController
 * 描述: 寄养服务管理接口
 * 作者: xuzeyu
 * 创建时间: 2026/1/26
 */
@Validated
@RestController
@RequestMapping("/foster-care")
public class FosterCareController {
    
    private final FosterCareCreateHandler fosterCareCreateHandler;
    private final FosterCareQueryHandler fosterCareQueryHandler;
    private final FosterCareUpdateHandler fosterCareUpdateHandler;
    private final FosterCareStatusUpdateHandler fosterCareStatusUpdateHandler;

    public FosterCareController(FosterCareCreateHandler fosterCareCreateHandler,
                                FosterCareQueryHandler fosterCareQueryHandler,
                                FosterCareUpdateHandler fosterCareUpdateHandler,
                                FosterCareStatusUpdateHandler fosterCareStatusUpdateHandler) {
        this.fosterCareCreateHandler = fosterCareCreateHandler;
        this.fosterCareQueryHandler = fosterCareQueryHandler;
        this.fosterCareUpdateHandler = fosterCareUpdateHandler;
        this.fosterCareStatusUpdateHandler = fosterCareStatusUpdateHandler;
    }

    /**
     * 创建寄养记录
     */
    @PostMapping("/create")
    public Result<Long> create(@RequestBody @Valid FosterCareCreateRequest request) {
        FosterCareCreateCommand command = new FosterCareCreateCommand();
        command.setUserId(Long.parseLong(StpUserUtil.getLoginId().toString()));
        command.setPetName(request.getPetName());
        command.setPetType(request.getPetType());
        command.setOwnerName(request.getOwnerName());
        command.setOwnerPhone(request.getOwnerPhone());
        command.setFosterStartDate(request.getFosterStartDate());
        command.setFosterEndDate(request.getFosterEndDate());
        command.setFosterPrice(request.getFosterPrice());
        command.setFosterAddress(request.getFosterAddress());
        command.setSpecialRequirements(request.getSpecialRequirements());
        command.setRemark(request.getRemark());
        
        return Result.success(fosterCareCreateHandler.create(command));
    }


    /**
     * 分页查询寄养记录
     */
    @SaCheckPermission("FOSTER_CARE_LIST")
    @GetMapping("/page")
    public Result<IPage<FosterCareRecord>> queryPage(@Valid FosterCarePageQueryCommand command) {
        return Result.success(fosterCareQueryHandler.queryPage(command));
    }

    /**
     * 根据ID查询寄养记录详情
     */
    @SaCheckPermission("FOSTER_CARE_LIST_VIEW")
    @GetMapping("/{id}")
    public Result<FosterCareRecord> queryDetail(@PathVariable Long id) {
        return Result.success(fosterCareQueryHandler.queryDetail(id));
    }
}
