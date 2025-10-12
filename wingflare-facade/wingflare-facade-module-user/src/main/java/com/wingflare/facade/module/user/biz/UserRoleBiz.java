package com.wingflare.facade.module.user.biz;


import com.wingflare.api.core.PageDto;
import com.wingflare.api.validation.annotation.Validated;
import com.wingflare.facade.module.user.bo.UserSearchBO;
import com.wingflare.facade.module.user.dto.RoleUserDTO;

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


    PageDto<RoleUserDTO> getUserList(@Valid @NotNull UserSearchBO userSearchBo);

}
