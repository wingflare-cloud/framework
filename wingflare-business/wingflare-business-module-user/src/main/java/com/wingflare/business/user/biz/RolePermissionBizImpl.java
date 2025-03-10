package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.convert.RolePermissionConvert;
import com.wingflare.business.user.db.RolePermissionDo;
import com.wingflare.business.user.service.RolePermissionServer;
import com.wingflare.business.user.wrapper.RolePermissionWrapper;
import com.wingflare.facade.module.user.biz.RoleBiz;
import com.wingflare.facade.module.user.biz.RolePermissionBiz;
import com.wingflare.facade.module.user.bo.PermissionCodesExistBo;
import com.wingflare.facade.module.user.dto.RoleDto;
import com.wingflare.lib.core.exceptions.DataException;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.facade.module.user.bo.RolePermissionBo;
import com.wingflare.facade.module.user.bo.RolePermissionSearchBo;
import com.wingflare.facade.module.user.dto.RolePermissionDto;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统角色权限Biz
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:13:02 CST 2023
 */
@Component
@Validated
public class RolePermissionBizImpl implements RolePermissionBiz {

    @Resource
    private RolePermissionServer rolePermissionServer;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private RoleBiz roleBiz;

    /**
     * 查询系统角色权限列表
     */
    @Override
    public PageDto<RolePermissionDto> list(@Valid RolePermissionSearchBo bo) {
        IPage<RolePermissionDo> iPage = rolePermissionServer.page(
                rolePermissionServer.createPage(bo),
                RolePermissionWrapper.getQueryWrapper(bo)
        );

        return PageUtil.convertIPage(iPage,
                RolePermissionConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询系统角色权限详情
     */
    @Override
    public RolePermissionDto get(@Valid @NotNull IdBo bo) {
        return RolePermissionConvert.convert.doToDto(
                rolePermissionServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个系统角色权限详情
     */
    @Override
    public RolePermissionDto getOnlyOne(@Valid @NotNull RolePermissionSearchBo searchBo) {
        return RolePermissionConvert.convert.doToDto(
                rolePermissionServer.getOne(
                        RolePermissionWrapper.getQueryWrapper(searchBo)
                ));
    }

    /**
     * 删除系统角色权限
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(@Valid @NotNull IdBo bo) {
        RolePermissionDo rolePermissionDo = rolePermissionServer.getById(bo.getId());

        if (rolePermissionDo != null) {
            rolePermissionServer.removeById(bo.getId());
        }
    }

    /**
     * 新增系统角色权限
     */
    @Override
    @Validated({Default.class, Create.class})
    public RolePermissionDto create(@Valid @NotNull RolePermissionBo bo) {
        RolePermissionDo rolePermissionDo = RolePermissionConvert.convert.boToDo(bo);
        rolePermissionServer.save(rolePermissionDo);
        return RolePermissionConvert.convert.doToDto(rolePermissionDo);
    }

    /**
     * 更新系统角色权限
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    @Validated({Default.class, Update.class})
    public RolePermissionDto update(@Valid @NotNull RolePermissionBo bo) {
        RolePermissionDo oldRolePermissionDo = rolePermissionServer.getById(bo.getId());

        if (oldRolePermissionDo == null) {
            throw new DataNotFoundException("rolePermission.data.notfound");
        }

        RolePermissionDo rolePermissionDo = RolePermissionConvert.convert.boToDo(bo);
        oldRolePermissionDo.setOnNew(rolePermissionDo);
        rolePermissionServer.updateById(oldRolePermissionDo);
        return RolePermissionConvert.convert.doToDto(rolePermissionDo);
    }

    @Override
    public Boolean savePermission(@Valid @NotNull PermissionCodesExistBo existBo) {
        Boolean ret = transactionTemplate.execute(status -> {
            RoleDto roleDo = roleBiz.get(new IdBo().setId(existBo.getRoleId()));

            if (roleDo == null) {
                throw new DataNotFoundException("role.data.notfound");
            }

            rolePermissionServer.batchDelete(new RolePermissionSearchBo().setEq_roleId(existBo.getRoleId()));

            List<RolePermissionBo> list = new ArrayList<>();

            for (int i = 0; i < existBo.getCodes().size(); i++) {
                for (int j = 0; j < existBo.getCodes().get(i).getCodes().size(); j++) {
                    list.add(new RolePermissionBo().setRoleId(existBo.getRoleId())
                            .setSystemCode(existBo.getCodes().get(i).getSystemCode())
                            .setPermissionCode(existBo.getCodes().get(i).getCodes().get(j)));
                }
            }

            if (!rolePermissionServer.saveBatch(RolePermissionConvert.convert.boToDoList(list))) {
                throw new DataException("rolePermission.data.saveErr");
            }

            return true;
        });

        return ret;
    }

    @Override
    public List<PermissionCodesExistBo.CodesExist> permission(IdBo bo) {
        List<PermissionCodesExistBo.CodesExist> list = new ArrayList<>();

        List<RolePermissionDo> rawList = rolePermissionServer.list(
                RolePermissionWrapper.getQueryWrapper(new RolePermissionSearchBo().setEq_roleId(bo.getId())));

        Map<String, Integer> map = new HashMap<>();

        if (!rawList.isEmpty()) {
            for (RolePermissionDo rolePermissionDo : rawList) {
                PermissionCodesExistBo.CodesExist codesExist = null;

                if (map.containsKey(rolePermissionDo.getSystemCode())) {
                    codesExist = list.get(map.get(rolePermissionDo.getSystemCode()));
                } else {
                    codesExist = new PermissionCodesExistBo.CodesExist();
                    codesExist.setSystemCode(rolePermissionDo.getSystemCode());
                    list.add(codesExist);
                    codesExist.setCodes(new ArrayList<>());
                    map.put(rolePermissionDo.getSystemCode(), list.size() - 1);
                }

                codesExist.getCodes().add(rolePermissionDo.getPermissionCode());
            }
        }

        return list;
    }

}