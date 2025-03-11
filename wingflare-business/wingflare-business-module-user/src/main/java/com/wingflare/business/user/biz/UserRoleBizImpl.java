package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.service.UserRoleServer;
import com.wingflare.facade.module.user.biz.UserRoleBiz;
import com.wingflare.business.user.db.UserRoleDo;
import com.wingflare.business.user.convert.UserRoleConvert;
import com.wingflare.facade.module.user.bo.UserRoleBo;
import com.wingflare.facade.module.user.dto.UserRoleDto;
import com.wingflare.facade.module.user.bo.UserRoleSearchBo;
import com.wingflare.business.user.wrapper.UserRoleWrapper;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;

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

    /**
     * 查询userRole列表
     */
    public PageDto<UserRoleDto> list(@Valid UserRoleSearchBo bo)
    {
        IPage<UserRoleDo> iPage = userRoleServer.page(
                userRoleServer.createPage(bo),
            UserRoleWrapper.getQueryWrapper(bo)
        );
        return PageUtil.convertIPage(iPage,
            UserRoleConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询userRole详情
     */
	@Override
    public UserRoleDto get(@Valid @NotNull IdBo bo)
    {
        return UserRoleConvert.convert.doToDto(userRoleServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个userRole详情
     */
	@Override
    public UserRoleDto getOnlyOne(@Valid @NotNull UserRoleSearchBo bo)
    {
        return UserRoleConvert.convert.doToDto(userRoleServer.getOne(UserRoleWrapper.getQueryWrapper(bo)));
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

    /**
     * 新增userRole
     */
	@Override
    @Validated({Default.class, Create.class})
    public UserRoleDto create(@Valid @NotNull UserRoleBo bo)
    {
        UserRoleDo userRoleDo = UserRoleConvert.convert.boToDo(bo);
        userRoleServer.save(userRoleDo);
        return UserRoleConvert.convert.doToDto(userRoleDo);
    }

    /**
     * 更新userRole
     */
	@Override
    @Validated({Default.class, Update.class})
    public UserRoleDto update(@Valid @NotNull UserRoleBo bo)
    {
        UserRoleDo oldUserRoleDo = userRoleServer.getById(bo.getId());

        if (oldUserRoleDo == null) {
            throw new DataNotFoundException("userRole.data.notfound");
        }

        UserRoleDo userRoleDo = UserRoleConvert.convert.boToDo(bo);
        UserRoleDo oldField = oldUserRoleDo.setOnNew(userRoleDo);

        if (oldField != null) {
            userRoleServer.updateById(oldUserRoleDo);
        }

        return UserRoleConvert.convert.doToDto(userRoleDo);
    }

}