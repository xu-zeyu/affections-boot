package com.jinHan.gold.api.controller.user;

import com.affectionsboot.auth.stp.StpUserUtil;
import com.affectionsboot.common.model.Result;
import com.jinHan.gold.api.controller.user.request.UserAuthRequest;
import com.jinHan.gold.api.controller.user.vo.UserInfoVo;
import com.jinHan.gold.api.controller.user.vo.UserPetVo;
import com.jinHan.gold.core.auth.domain.command.UserAuthCommand;
import com.jinHan.gold.core.auth.domain.handler.UserAuthHandler;
import com.jinHan.gold.core.users.domain.command.UserInfoCommand;
import com.jinHan.gold.core.users.domain.handler.UserInfoHandler;
import com.jinHan.gold.core.users.domain.handler.UserPetQueryHandler;
import com.jinHan.gold.core.users.domain.model.Users;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类名: UserController
 * 描述: 用户信息接口
 * 作者: xuzeyu
 * 创建时间: 2025/12/23
 */
@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserInfoHandler userInfoHandler;

    @Resource
    private UserAuthHandler userAuthHandler;

    @Resource
    private UserPetQueryHandler userPetQueryHandler;

    @GetMapping("")
    public Result<UserInfoVo> userInfo() {
        UserInfoCommand command = new UserInfoCommand(StpUserUtil.getLoginId().toString());
        Users userInfo = userInfoHandler.userInfo(command);
        UserInfoVo userInfoVo = new UserInfoVo(userInfo);
        userInfoVo.setPetList(toUserPetVoList(userInfo.getUserId()));
        return  Result.success(userInfoVo);
    }

    @PostMapping("/auth")
    public Result<Long> userAuth(@RequestBody @Valid UserAuthRequest request) {
        UserAuthCommand command = request.toCommand(StpUserUtil.getLoginId().toString());
        return Result.success(userAuthHandler.startAuth(command));
    }

    private List<UserPetVo> toUserPetVoList(Long userId) {
        return userPetQueryHandler.findByUserId(userId).stream()
                .map(UserPetVo::new)
                .toList();
    }
}
