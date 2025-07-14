package com.wingflare.business.user.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.db.RoleUserDO;
import com.wingflare.business.user.db.UserDO;
import com.wingflare.business.user.db.UserRoleDO;
import com.wingflare.business.user.mapper.UserRoleMapper;
import com.wingflare.facade.module.user.bo.UserSearchBO;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 系统用户角色表 服务实现类
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-11
 */
@Service
public class UserRoleServer extends BaseService<UserRoleMapper, UserRoleDO> {

    /**
     * 获取userRoleMap
     * <p>
     * wrapper 查询参数
     *
     * @return 结果
     */
    public Map<BigInteger, UserRoleDO> getMap(QueryWrapper<UserRoleDO> wrapper) {
        List<UserRoleDO> list = list(wrapper);

        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }

        Map<BigInteger, UserRoleDO> userRoleDoMap = new HashMap<>(list.size());

        for (UserRoleDO userRoleDo : list) {
            userRoleDoMap.put(userRoleDo.getId(), userRoleDo);
        }

        return userRoleDoMap;
    }

    public void deleteByUserId(BigInteger userId) {
        getBaseMapper().delete(new LambdaQueryWrapper<UserRoleDO>().eq(UserRoleDO::getUserId, userId));
    }

    /**
     * 连表查询
     *
     * @param bo
     * @return
     */
    public IPage<RoleUserDO> getUserList(UserSearchBO bo) {
        JoinLambdaQueryWrapper joinLambdaQueryWrapper = new JoinLambdaQueryWrapper<>()
                .select(UserDO::getUserId, UserDO::getUserName, UserDO::getBanState, UserDO::getLastLoginIp, UserDO::getLastLoginTime,
                        UserDO::getUserEmail, UserDO::getUserPhone)
                .selectAs(UserRoleDO::getId, RoleUserDO::getId)
                .leftJoin(UserDO.class, UserDO::getUserId, UserRoleDO::getUserId)
                .eq(UserRoleDO::getRoleId, bo.getRoleId())
                .eq(UserDO::getIsDelete, 0)
                .eq(UserRoleDO::getIsDelete, 0)
                .eq(bo.getEq_banState() != null, UserDO::getBanState, bo.getEq_banState())
                .like(bo.getLike_userName() != null, UserDO::getUserName, bo.getLike_userName())
                .orderByDesc(UserDO::getUserId);

        return getBaseMapper().selectJoinPage(
                createPage(bo),
                RoleUserDO.class,
                joinLambdaQueryWrapper
        );
    }

}