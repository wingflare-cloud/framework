package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.UserSearchBo;
import com.wingflare.facade.module.user.dto.RoleUserDto;
import com.wingflare.lib.standard.PageDto;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 系统用户角色表 业务接口
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-10
 */
@Validated
public interface UserRoleBiz {


    PageDto<RoleUserDto> getUserList(@Valid @NotNull UserSearchBo userSearchBo);

}
