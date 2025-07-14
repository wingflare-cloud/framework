package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.convert.RoleUserDoConvert;
import com.wingflare.business.user.db.RoleUserDO;
import com.wingflare.business.user.service.UserRoleServer;
import com.wingflare.facade.module.user.biz.UserRoleBiz;
import com.wingflare.facade.module.user.bo.UserSearchBO;
import com.wingflare.facade.module.user.dto.RoleUserDTO;
import com.wingflare.lib.core.enums.SensitiveType;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.security.annotation.Desensitize;
import com.wingflare.lib.security.annotation.DesensitizeGroups;
import com.wingflare.lib.standard.PageDto;
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

    @Override
    @DesensitizeGroups(
            desensitizes = {
                    @Desensitize(
                            jsonPath = "$.list[*].userPhone",
                            sensitiveType = SensitiveType.MOBILE_PHONE
                    ),
                    @Desensitize(
                            jsonPath = "$.list[*].userEmail",
                            sensitiveType = SensitiveType.EMAIL
                    )
            }
    )
    public PageDto<RoleUserDTO> getUserList(@Valid @NotNull UserSearchBO bo) {
        IPage<RoleUserDO> iPage = userRoleServer.getUserList(bo);

        return PageUtil.convertIPage(iPage,
                RoleUserDoConvert.convert.doToDtoList(iPage.getRecords()));
    }

}