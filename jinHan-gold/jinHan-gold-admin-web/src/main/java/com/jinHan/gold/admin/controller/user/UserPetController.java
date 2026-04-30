package com.jinHan.gold.admin.controller.user;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.affectionsboot.common.model.Result;
import com.affectionsboot.log.annotation.Log;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinHan.gold.admin.controller.user.request.UserPetCreateRequest;
import com.jinHan.gold.admin.controller.user.request.UserPetPageRequest;
import com.jinHan.gold.admin.controller.user.request.UserPetUpdateRequest;
import com.jinHan.gold.admin.controller.user.response.UserPetResponse;
import com.jinHan.gold.core.users.domain.command.UserPetDeleteCommand;
import com.jinHan.gold.core.users.domain.command.UserPetPageCommand;
import com.jinHan.gold.core.users.domain.handler.UserPetCreateHandler;
import com.jinHan.gold.core.users.domain.handler.UserPetDeleteHandler;
import com.jinHan.gold.core.users.domain.handler.UserPetQueryHandler;
import com.jinHan.gold.core.users.domain.handler.UserPetUpdateHandler;
import com.jinHan.gold.core.users.domain.model.UserPet;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 类名: UserPetController
 * 描述: 管理端用户爱宠接口
 * 作者: xuzeyu
 * 创建时间: 2026/4/30
 */
@Validated
@RestController
@RequestMapping("/user/pet")
public class UserPetController {

    @Resource
    private UserPetCreateHandler userPetCreateHandler;

    @Resource
    private UserPetUpdateHandler userPetUpdateHandler;

    @Resource
    private UserPetDeleteHandler userPetDeleteHandler;

    @Resource
    private UserPetQueryHandler userPetQueryHandler;

    @Log(value = "创建用户爱宠", operationType = "USER_PET_CREATE")
    @SaCheckPermission("USER_LIST_UPDATE")
    @PostMapping("/create")
    public Result<Long> create(@RequestBody @Valid UserPetCreateRequest request) {
        return Result.success(userPetCreateHandler.create(request.toCommand()));
    }

    @Log(value = "更新用户爱宠", operationType = "USER_PET_UPDATE")
    @SaCheckPermission("USER_LIST_UPDATE")
    @PutMapping("/update")
    public Result<UserPetResponse> update(@RequestBody @Valid UserPetUpdateRequest request) {
        UserPet userPet = userPetUpdateHandler.update(request.toCommand());
        return Result.success(new UserPetResponse(userPet));
    }

    @Log(value = "删除用户爱宠", operationType = "USER_PET_DELETE")
    @SaCheckPermission("USER_LIST_UPDATE")
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(userPetDeleteHandler.delete(new UserPetDeleteCommand(id, null)));
    }

    @Log(value = "查询用户爱宠详情", operationType = "USER_PET_GET")
    @SaCheckPermission("USER_LIST")
    @GetMapping("/{id}")
    public Result<UserPetResponse> detail(@PathVariable Long id) {
        return Result.success(new UserPetResponse(userPetQueryHandler.findById(id)));
    }

    @Log(value = "分页查询用户爱宠", operationType = "USER_PET_PAGE")
    @SaCheckPermission("USER_LIST")
    @GetMapping("/page")
    public Result<IPage<UserPetResponse>> page(UserPetPageRequest request) {
        UserPetPageCommand command = request.toCommand();
        IPage<UserPetResponse> responsePage = userPetQueryHandler.page(command).convert(UserPetResponse::new);
        return Result.success(responsePage);
    }
}
