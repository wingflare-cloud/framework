package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.convert.UserConvert;
import com.wingflare.business.user.db.RoleUserDo;
import com.wingflare.business.user.service.UserRoleServer;
import com.wingflare.facade.module.user.biz.UserRoleBiz;
import com.wingflare.business.user.db.UserRoleDo;
import com.wingflare.business.user.convert.UserRoleConvert;
import com.wingflare.facade.module.user.bo.UserSearchBo;
import com.wingflare.facade.module.user.dto.RoleUserDto;
import com.wingflare.facade.module.user.dto.UserRoleDto;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


/**
 * <p>
 * 系统用户角色表 业务实现类
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-10
 */
@Component
@Validated
public class UserRoleBizImpl implements UserRoleBiz
{

    @Resource
    private UserRoleServer userRoleServer;


    public PageDto<RoleUserDto> getUserList(@Valid @NotNull UserSearchBo bo) {
        IPage<RoleUserDo> iPage = userRoleServer.getUserList(bo);

        return PageUtil.convertIPage(iPage,
                UserConvert.convert.toRoleUserList(iPage.getRecords()));
    }

    /**
     * 删除userRole
     */
	@Override
    public UserRoleDto delete(@Valid @NotNull IdBo bo)
    {
        UserRoleDo userRoleDo = userRoleServer.getById(bo.getId());

        if (userRoleDo != null) {
            userRoleServer.removeById(bo.getId());
		    return UserRoleConvert.convert.doToDto(userRoleDo);
        }

        return null;
    }

}