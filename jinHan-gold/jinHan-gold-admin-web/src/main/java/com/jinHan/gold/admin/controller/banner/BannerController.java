package com.jinHan.gold.admin.controller.banner;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.affectionsboot.common.model.Result;
import com.affectionsboot.log.annotation.Log;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.admin.controller.banner.request.BannerCreateRequest;
import com.jinHan.gold.admin.controller.banner.request.BannerUpdateRequest;
import com.jinHan.gold.admin.controller.banner.response.BannerFindResponse;
import com.jinHan.gold.admin.controller.banner.request.BannerPageRequest;
import com.jinHan.gold.core.banner.domain.command.BannerCreateCommand;
import com.jinHan.gold.core.banner.domain.command.BannerFindCommand;
import com.jinHan.gold.core.banner.domain.command.BannerPageCommand;
import com.jinHan.gold.core.banner.domain.command.BannerUpdateCommand;
import com.jinHan.gold.core.banner.domain.handler.*;
import com.jinHan.gold.core.banner.domain.model.Banner;
import com.jinHan.gold.core.banner.domain.model.PlatformEnum;
import com.jinHan.gold.core.banner.domain.model.StatusEnum;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 类名: BannerController
 * 描述: 管理后台Banner接口
 * 作者: xuzeyu
 * 创建时间: 2025/12/22
 */
@Validated
@RestController
@RequestMapping("/banner")
public class BannerController {
    private final BannerCreateCommandHandler commandCreateHandler;
    private final BannerFindCommandHandler commandFindHandler;
    private final BannerUpdateCommandHandler commandUpdateHandler;
    private final BannerDeleteCommandHandler commandDeleteHandler;
    private final BannerPageCommandHandler commandPageHandler;
    BannerController(BannerCreateCommandHandler commandCreateHandler,
                     BannerFindCommandHandler commandFindHandler,
                     BannerUpdateCommandHandler commandUpdateHandler,
                     BannerDeleteCommandHandler commandDeleteHandler,
                     BannerPageCommandHandler commandPageHandler) {
        this.commandCreateHandler = commandCreateHandler;
        this.commandFindHandler = commandFindHandler;
        this.commandUpdateHandler = commandUpdateHandler;
        this.commandDeleteHandler = commandDeleteHandler;
        this.commandPageHandler = commandPageHandler;
    }



    @Log(value = "创建Banner", operationType = "BANNER_CREATE")
    @SaCheckPermission("BANNER_LIST_CREATE")
    @PostMapping("/create")
    public Result<Long> create(@RequestBody @Valid BannerCreateRequest bannerUpdateRequest) {
        BannerCreateCommand command = bannerUpdateRequest.toCommand();
        return Result.success(commandCreateHandler.create(command));
    }


    @Log(value = "查询Banner详情", operationType = "BANNER_GET")
    @SaCheckPermission("BANNER_LIST")
    @GetMapping("/{id}")
    public Result<BannerFindResponse> findById(@PathVariable("id") Long id) {
        BannerFindCommand command = new BannerFindCommand(id);
        BannerFindResponse response = new BannerFindResponse(commandFindHandler.findBanner(command));
        return Result.success(response);
    }

    @Log(value = "更新Banner", operationType = "BANNER_UPDATE")
    @SaCheckPermission("BANNER_LIST_UPDATE")
    @PutMapping("/update")
    public Result<Banner> update(@RequestBody @Valid BannerUpdateRequest bannerUpdateRequest) {
        BannerUpdateCommand command = bannerUpdateRequest.toCommand();
        return Result.success(commandUpdateHandler.update(command));
    }

    @Log(value = "删除Banner", operationType = "BANNER_DELETE")
    @SaCheckPermission("BANNER_LIST_DELETE")
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        BannerFindCommand command = new BannerFindCommand(id);
        return Result.success(commandDeleteHandler.deleteBanner(command));
    }

    @Log(value = "分页查询Banner列表", operationType = "BANNER_PAGE")
    @SaCheckPermission("BANNER_LIST")
    @GetMapping("/page")
    public Result<Page<BannerFindResponse>> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                        @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                        @RequestParam(value = "title", required = false) String title,
                                                        @RequestParam(value = "status", required = false) StatusEnum status,
                                                        @RequestParam(value = "platform", required = false) PlatformEnum platform) {
        BannerPageRequest bannerPageRequest = new BannerPageRequest();
        bannerPageRequest.setPage(page);
        bannerPageRequest.setSize(size);
        bannerPageRequest.setTitle(title);
        bannerPageRequest.setStatus(status);
        bannerPageRequest.setPlatform(platform);
        BannerPageCommand command = bannerPageRequest.toCommand();
        Page<Banner> bannerPage = commandPageHandler.page(command);
        Page<BannerFindResponse> responsePage = (Page<BannerFindResponse>) bannerPage.convert(BannerFindResponse::new);
        return Result.success(responsePage);
    }
}
