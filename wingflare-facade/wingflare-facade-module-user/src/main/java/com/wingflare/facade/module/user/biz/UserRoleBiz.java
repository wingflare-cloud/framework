package com.wingflare.facade.module.user.biz;

import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.facade.module.user.dto.UserRoleDto;
import com.wingflare.facade.module.user.bo.UserRoleBo;
import com.wingflare.facade.module.user.bo.UserRoleSearchBo;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;

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

    PageDto<UserRoleDto> list(@Valid UserRoleSearchBo bo);

    UserRoleDto get(@Valid @NotNull IdBo bo);

    UserRoleDto getOnlyOne(@Valid @NotNull UserRoleSearchBo bo);

    @Validated({Default.class, Create.class})
    UserRoleDto create(@Valid @NotNull UserRoleBo bo);

    @Validated({Default.class, Update.class})
    UserRoleDto update(@Valid @NotNull UserRoleBo bo);

    UserRoleDto delete(@Valid @NotNull IdBo bo);

}
