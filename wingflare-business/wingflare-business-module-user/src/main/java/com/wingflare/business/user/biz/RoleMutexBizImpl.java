package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.convert.RoleMutexConvert;
import com.wingflare.business.user.db.RoleMutexDo;
import com.wingflare.business.user.service.RoleMutexServer;
import com.wingflare.business.user.wrapper.RoleMutexWrapper;
import com.wingflare.facade.module.user.biz.RoleMutexBiz;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.facade.module.user.bo.RoleMutexBo;
import com.wingflare.facade.module.user.bo.RoleMutexSearchBo;
import com.wingflare.facade.module.user.dto.RoleMutexDto;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;


/**
 * 系统角色互斥关系Biz
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:09:10 CST 2023
 */
@Component
@Validated
public class RoleMutexBizImpl implements RoleMutexBiz {

    @Resource
    private RoleMutexServer roleMutexServer;

    /**
     * 查询系统角色互斥关系列表
     */
    @Override
    public PageDto<RoleMutexDto> list(@Valid RoleMutexSearchBo bo) {
        IPage<RoleMutexDo> iPage = roleMutexServer.page(
                roleMutexServer.createPage(bo),
                RoleMutexWrapper.getQueryWrapper(bo)
        );

        return PageUtil.convertIPage(iPage,
                RoleMutexConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询系统角色互斥关系详情
     */
    @Override
    public RoleMutexDto get(@Valid @NotNull IdBo bo) {
        return RoleMutexConvert.convert.doToDto(
                roleMutexServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个系统角色互斥关系详情
     */
    @Override
    public RoleMutexDto getOnlyOne(@Valid @NotNull RoleMutexSearchBo searchBo) {
        return RoleMutexConvert.convert.doToDto(
                roleMutexServer.getOne(
                        RoleMutexWrapper.getQueryWrapper(searchBo)
                ));
    }

    /**
     * 删除系统角色互斥关系
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(@Valid @NotNull IdBo bo) {
        RoleMutexDo roleMutexDo = roleMutexServer.getById(bo.getId());

        if (roleMutexDo != null) {
            roleMutexServer.removeById(bo.getId());
        }
    }

    /**
     * 新增系统角色互斥关系
     */
    @Override
    @Validated({Default.class, Create.class})
    public RoleMutexDto create(@Valid @NotNull RoleMutexBo bo) {
        RoleMutexDo roleMutexDo = RoleMutexConvert.convert.boToDo(bo);
        roleMutexServer.save(roleMutexDo);
        return RoleMutexConvert.convert.doToDto(roleMutexDo);
    }

    /**
     * 更新系统角色互斥关系
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    @Validated({Default.class, Update.class})
    public RoleMutexDto update(@Valid @NotNull RoleMutexBo bo) {
        RoleMutexDo oldRoleMutexDo = roleMutexServer.getById(bo.getId());

        if (oldRoleMutexDo == null) {
            throw new DataNotFoundException("roleMutex.data.notfound");
        }

        RoleMutexDo roleMutexDo = RoleMutexConvert.convert.boToDo(bo);
        oldRoleMutexDo.setOnNew(roleMutexDo);
        roleMutexServer.updateById(oldRoleMutexDo);
        return RoleMutexConvert.convert.doToDto(roleMutexDo);
    }

    /**
     * 判断是否存在符合条件的系统角色互斥关系
     *
     * @param bo 查询参数
     * @return 系统角色互斥关系
     */
    public boolean has(RoleMutexSearchBo bo) {
        return roleMutexServer.has(
                RoleMutexWrapper.getQueryWrapper(bo)
        );
    }

}