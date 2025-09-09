package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.api.core.PageDto;
import com.wingflare.business.user.convert.RolePermissionConvert;
import com.wingflare.business.user.db.RolePermissionDO;
import com.wingflare.business.user.service.RolePermissionServer;
import com.wingflare.business.user.wrapper.RolePermissionWrapper;
import com.wingflare.facade.module.user.biz.RoleBiz;
import com.wingflare.facade.module.user.biz.RolePermissionBiz;
import com.wingflare.facade.module.user.bo.PermissionCodesExistBO;
import com.wingflare.facade.module.user.bo.RolePermissionBO;
import com.wingflare.facade.module.user.dto.RoleDTO;
import com.wingflare.facade.module.user.dto.RolePermissionDTO;
import com.wingflare.lib.core.exceptions.DataException;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.facade.module.user.bo.RolePermissionSearchBO;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
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
@Validated
public class RolePermissionBizImpl implements RolePermissionBiz {

    private final RolePermissionServer rolePermissionServer;

    private final TransactionTemplate transactionTemplate;

    private final RoleBiz roleBiz;

    public RolePermissionBizImpl(RolePermissionServer rolePermissionServer, TransactionTemplate transactionTemplate, RoleBiz roleBiz) {
        this.rolePermissionServer = rolePermissionServer;
        this.transactionTemplate = transactionTemplate;
        this.roleBiz = roleBiz;
    }

    /**
     * 查询系统角色权限列表
     */
    @Override
    public PageDto<RolePermissionDTO> list(@Valid RolePermissionSearchBO bo) {
        IPage<RolePermissionDO> iPage = rolePermissionServer.page(
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
    public RolePermissionDTO get(@Valid @NotNull IdBo bo) {
        return RolePermissionConvert.convert.doToDto(
                rolePermissionServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个系统角色权限详情
     */
    @Override
    public RolePermissionDTO getOnlyOne(@Valid @NotNull RolePermissionSearchBO searchBo) {
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
        RolePermissionDO rolePermissionDo = rolePermissionServer.getById(bo.getId());

        if (rolePermissionDo != null) {
            rolePermissionServer.removeById(bo.getId());
        }
    }

    /**
     * 新增系统角色权限
     */
    @Override
    @Validated({Default.class, Create.class})
    public RolePermissionDTO create(@Valid @NotNull RolePermissionBO bo) {
        RolePermissionDO rolePermissionDo = RolePermissionConvert.convert.boToDo(bo);
        rolePermissionServer.save(rolePermissionDo);
        return RolePermissionConvert.convert.doToDto(rolePermissionDo);
    }

    /**
     * 更新系统角色权限
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    @Validated({Default.class, Update.class})
    public RolePermissionDTO update(@Valid @NotNull RolePermissionBO bo) {
        RolePermissionDO oldRolePermissionDO = rolePermissionServer.getById(bo.getId());

        if (oldRolePermissionDO == null) {
            throw new DataNotFoundException("rolePermission.data.notfound");
        }

        RolePermissionDO rolePermissionDo = RolePermissionConvert.convert.boToDo(bo);
        oldRolePermissionDO.setOnNew(rolePermissionDo);
        rolePermissionServer.updateById(oldRolePermissionDO);
        return RolePermissionConvert.convert.doToDto(rolePermissionDo);
    }

    @Override
    public Boolean savePermission(@Valid @NotNull PermissionCodesExistBO existBo) {
        Boolean ret = transactionTemplate.execute(status -> {
            RoleDTO roleDo = roleBiz.get(new IdBo().setId(existBo.getRoleId()));

            if (roleDo == null) {
                throw new DataNotFoundException("role.data.notfound");
            }

            rolePermissionServer.batchDelete(new RolePermissionSearchBO().setEq_roleId(existBo.getRoleId()));

            List<RolePermissionBO> list = new ArrayList<>();

            for (int i = 0; i < existBo.getCodes().size(); i++) {
                for (int j = 0; j < existBo.getCodes().get(i).getCodes().size(); j++) {
                    list.add(new RolePermissionBO().setRoleId(existBo.getRoleId())
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
    public List<PermissionCodesExistBO.CodesExist> permission(IdBo bo) {
        List<PermissionCodesExistBO.CodesExist> list = new ArrayList<>();

        List<RolePermissionDO> rawList = rolePermissionServer.list(
                RolePermissionWrapper.getQueryWrapper(new RolePermissionSearchBO().setEq_roleId(bo.getId())));

        Map<String, Integer> map = new HashMap<>();

        if (!rawList.isEmpty()) {
            for (RolePermissionDO rolePermissionDo : rawList) {
                PermissionCodesExistBO.CodesExist codesExist = null;

                if (map.containsKey(rolePermissionDo.getSystemCode())) {
                    codesExist = list.get(map.get(rolePermissionDo.getSystemCode()));
                } else {
                    codesExist = new PermissionCodesExistBO.CodesExist();
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