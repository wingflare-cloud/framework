package com.wingflare.business.user.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.db.RoleUserDo;
import com.wingflare.business.user.db.UserDo;
import com.wingflare.business.user.db.UserRoleDo;
import com.wingflare.business.user.mapper.UserRoleMapper;
import com.wingflare.facade.module.user.bo.UserSearchBo;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;
import com.wingflare.lib.security.utils.UserAuthUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
public class UserRoleServer extends BaseService<UserRoleMapper, UserRoleDo> {

    @Resource
    private UserAuthUtil userAuthUtil;

    /**
     * 获取userRoleMap
     * <p>
     * wrapper 查询参数
     *
     * @return 结果
     */
    public Map<String, UserRoleDo> getMap(QueryWrapper<UserRoleDo> wrapper) {
        List<UserRoleDo> list = list(wrapper);

        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }

        Map<String, UserRoleDo> userRoleDoMap = new HashMap<>(list.size());

        for (UserRoleDo userRoleDo : list) {
            userRoleDoMap.put(userRoleDo.getId(), userRoleDo);
        }

        return userRoleDoMap;
    }

    /**
     * 连表查询
     *
     * @param bo
     * @return
     */
    public IPage<RoleUserDo> getUserList(UserSearchBo bo) {
        JoinLambdaQueryWrapper joinLambdaQueryWrapper = new JoinLambdaQueryWrapper<>()
                .select(UserDo::getUserId, UserDo::getUserName, UserDo::getBanState, UserDo::getLastLoginIp, UserDo::getLastLoginTime)
                .selectAs(UserRoleDo::getId, RoleUserDo::getId)
                .leftJoin(UserDo.class, UserDo::getUserId, UserRoleDo::getUserId)
                .eq(UserRoleDo::getRoleId, bo.getRoleId())
                .eq(UserDo::getIsDelete, 0)
                .eq(UserRoleDo::getIsDelete, 0)
                .eq(UserDo::getSuperAdministrator, 0)
                .ne(userAuthUtil.getUser() != null,UserDo::getUserId, userAuthUtil.getUser().getUserId())
                .eq(bo.getEq_banState() != null, UserDo::getBanState, bo.getEq_banState())
                .like(bo.getLike_userName() != null, UserDo::getUserName, bo.getLike_userName());

        return getBaseMapper().selectJoinPage(
                createPage(bo),
                RoleUserDo.class,
                joinLambdaQueryWrapper
        );
    }

}