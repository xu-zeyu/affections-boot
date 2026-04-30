package com.jinHan.gold.api.controller.user;

import com.affectionsboot.auth.stp.StpUserUtil;
import com.affectionsboot.common.model.Result;
import com.jinHan.gold.api.controller.user.request.UserPetCreateRequest;
import com.jinHan.gold.api.controller.user.request.UserPetUpdateRequest;
import com.jinHan.gold.api.controller.user.vo.UserPetVo;
import com.jinHan.gold.core.users.domain.command.UserPetDeleteCommand;
import com.jinHan.gold.core.users.domain.handler.UserPetCreateHandler;
import com.jinHan.gold.core.users.domain.handler.UserPetDeleteHandler;
import com.jinHan.gold.core.users.domain.handler.UserPetQueryHandler;
import com.jinHan.gold.core.users.domain.handler.UserPetUpdateHandler;
import com.jinHan.gold.core.users.domain.model.UserPet;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类名: UserPetController
 * 描述: 用户爱宠接口
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

    @PostMapping("/create")
    public Result<Long> create(@RequestBody @Valid UserPetCreateRequest request) {
        Long userId = Long.parseLong(StpUserUtil.getLoginId().toString());
        return Result.success(userPetCreateHandler.create(request.toCommand(userId)));
    }

    @PutMapping("/update")
    public Result<UserPetVo> update(@RequestBody @Valid UserPetUpdateRequest request) {
        Long userId = Long.parseLong(StpUserUtil.getLoginId().toString());
        UserPet userPet = userPetUpdateHandler.update(request.toCommand(userId));
        return Result.success(new UserPetVo(userPet));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        Long userId = Long.parseLong(StpUserUtil.getLoginId().toString());
        return Result.success(userPetDeleteHandler.delete(new UserPetDeleteCommand(id, userId)));
    }

    @GetMapping("/list")
    public Result<List<UserPetVo>> list() {
        Long userId = Long.parseLong(StpUserUtil.getLoginId().toString());
        List<UserPetVo> userPetList = userPetQueryHandler.findByUserId(userId).stream()
                .map(UserPetVo::new)
                .toList();
        return Result.success(userPetList);
    }

    @GetMapping("/{id}")
    public Result<UserPetVo> detail(@PathVariable Long id) {
        Long userId = Long.parseLong(StpUserUtil.getLoginId().toString());
        return Result.success(new UserPetVo(userPetQueryHandler.findById(id, userId)));
    }
}
