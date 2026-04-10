package com.jinHan.gold.admin.controller.user.request;

import com.jinHan.gold.core.auth.domain.model.GenderEnum;
import com.jinHan.gold.core.product.domain.command.ProductPageCommand;
import com.jinHan.gold.core.users.domain.command.UserPageCommand;
import com.jinHan.gold.core.users.domain.model.UsersStatusEnum;
import lombok.Data;

/**
 * 类名: ProductPageRequest
 * 描述: 分页查询商品请求
 * 作者: xuzeyu
 * 创建时间: 2026/1/13
 */
@Data
public class UserPageRequest {
    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 每页大小
     */
    private Integer size = 10;

    /**
     * 商品名称
     */
    private String phone;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 状态
     */
    private UsersStatusEnum status;

    /**
     * 性别
     */
    private GenderEnum gender;

    public UserPageCommand toCommand() {
        UserPageCommand command = new UserPageCommand();
        command.setPage(this.page);
        command.setSize(this.size);
        command.setPhone(this.phone);
        command.setUsername(this.username);
        command.setStatus(this.status);
        command.setGender(this.gender);
        return command;
    }
}
