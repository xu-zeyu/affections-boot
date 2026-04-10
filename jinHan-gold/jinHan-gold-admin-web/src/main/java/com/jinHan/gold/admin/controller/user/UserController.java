package com.jinHan.gold.admin.controller.user;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.affectionsboot.auth.stp.StpUserUtil;
import com.affectionsboot.common.exception.BusinessException;
import com.affectionsboot.common.model.Result;
import com.affectionsboot.log.annotation.Log;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinHan.gold.admin.controller.user.request.AuditTypeRequest;
import com.jinHan.gold.admin.controller.user.request.UserPageRequest;
import com.jinHan.gold.admin.controller.user.response.UserInfoResponse;
import com.jinHan.gold.core.auth.domain.command.UserAuthAuditCommand;
import com.jinHan.gold.core.auth.domain.handler.UserAuthAuditHandler;
import com.jinHan.gold.core.auth.domain.mapper.IdCardInfoMapper;
import com.jinHan.gold.core.auth.domain.model.IdCardInfo;
import com.jinHan.gold.core.users.domain.command.UserInfoCommand;
import com.jinHan.gold.core.users.domain.command.UserPageCommand;
import com.jinHan.gold.core.users.domain.handler.UserInfoHandler;
import com.jinHan.gold.core.users.domain.handler.UserPageCommandHandler;
import com.jinHan.gold.core.users.domain.model.Users;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 类名: UserController
 * 描述: 用户信息接口
 * 作者: xuzeyu
 * 创建时间: 2025/12/23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IdCardInfoMapper idCardInfoMapper;

    @Resource
    private UserPageCommandHandler userPageCommandHandler;

    @Resource
    private UserAuthAuditHandler userAuthAuditHandler;

    @Resource
    private UserInfoHandler userInfoHandler;

    /**
     * 分页查询用户
     */
    @Log(value = "分页查询用户", operationType = "USER_PAGE")
    @SaCheckPermission("USER_LIST")
    @GetMapping("/page")
    public Result<Page<UserInfoResponse>> userInfo(UserPageRequest request) {
        UserPageCommand command = request.toCommand();
        Page<Users> usersPage = userPageCommandHandler.page(command);
        Page<UserInfoResponse> responsePage = (Page<UserInfoResponse>) usersPage.convert(UserInfoResponse::new);
        return Result.success(responsePage);
    }

    /**
     * 查询用户
     */
    @Log(value = "查询用户详情", operationType = "USER_GET")
    @GetMapping("/{userId}")
    public Result<UserInfoResponse> userInfo(@PathVariable String userId) {
        UserInfoCommand command = new UserInfoCommand(userId);
        Users userInfo = userInfoHandler.userInfo(command);
        UserInfoResponse userInfoResponse = new UserInfoResponse(userInfo);
        return  Result.success(userInfoResponse);
    }

    /*
    * 获取用户认证信息
    * */
    @Log(value = "获取用户认证信息", operationType = "USER_AUTH_INFO")
    @SaCheckPermission("USER_LIST_UPDATE")
    @GetMapping("/auth/{userId}")
    public Result<IdCardInfo> authInfo(@PathVariable Long userId) {
        LambdaQueryWrapper<IdCardInfo> lambdaQuery = new LambdaQueryWrapper<>();
        lambdaQuery.eq(IdCardInfo::getUserId, userId);
        IdCardInfo idCardInfo = idCardInfoMapper.selectOne(lambdaQuery);
        return Result.success(idCardInfo);
    }


    /*
     * 审核用户认证信息
     * */
    @Log(value = "审核用户认证信息", operationType = "USER_AUTH_AUDIT")
    @SaCheckPermission("USER_LIST_UPDATE")
    @PutMapping("/auth/audit/{auditType}/{userId}")
    public Result<Boolean> audit(@PathVariable AuditTypeRequest auditType, @PathVariable Long userId) {
        UserAuthAuditCommand userAuthAuditCommand = new UserAuthAuditCommand();
        userAuthAuditCommand.setUserId(userId);
        if (auditType == AuditTypeRequest.APPROVED) {
            return Result.success(userAuthAuditHandler.approved(userAuthAuditCommand));
        }

        if (auditType == AuditTypeRequest.REJECTED) {
            return Result.success(userAuthAuditHandler.rejected(userAuthAuditCommand));
        }

        throw new BusinessException("参数错误");
    }
}
