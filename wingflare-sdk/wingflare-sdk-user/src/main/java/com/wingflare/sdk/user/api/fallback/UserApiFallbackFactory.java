package com.wingflare.sdk.user.api.fallback;


import com.wingflare.facade.module.user.bo.UpdatePasswdBo;
import com.wingflare.facade.module.user.bo.UserBindRoleBo;
import com.wingflare.facade.module.user.bo.UserBo;
import com.wingflare.facade.module.user.bo.UserSearchBo;
import com.wingflare.facade.module.user.dto.UserDto;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.sdk.user.api.service.UserApiServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName UserApiFallbackFactory
 * @Author naizui_ycx
 * @Date 2023/04/04 04
 * @Description user api熔断
 */
@Component
public class UserApiFallbackFactory implements FallbackFactory<UserApiServer> {

    private static final Logger logger = LoggerFactory.getLogger(UserApiFallbackFactory.class);

    @Override
    public UserApiServer create(Throwable throwable) {
        return new UserApiServer() {
            @Override
            public UserDto updatePasswd(UpdatePasswdBo bo) {
                logger.error("更新用户密码失败: {}", throwable.getMessage());
                return null;
            }

            @Override
            public UserDto getUserByLoginName(String loginName) {
                logger.error("通过用户登录名获取用户信息失败: {}", throwable.getMessage());
                return null;
            }

            @Override
            public PageDto<UserDto> list(UserSearchBo bo) {
                logger.error("获取用户列表失败: {}", throwable.getMessage());
                return null;
            }

            @Override
            public UserDto get(IdBo bo) {
                logger.error("通过id获取用户信息失败: {}", throwable.getMessage());
                return null;
            }

            @Override
            public UserDto getOnlyOne(UserSearchBo searchBo) {
                logger.error("查询单个用户数据失败: {}", throwable.getMessage());
                return null;
            }

            @Override
            public UserDto delete(IdBo bo) {
                logger.error("删除用户失败: {}", throwable.getMessage());
                return null;
            }

            @Override
            public UserDto create(UserBo bo) {
                logger.error("创建用户失败: {}", throwable.getMessage());
                return null;
            }

            @Override
            public void userBindRole(UserBindRoleBo bo) {
                logger.error("用户绑定角色失败: {}", throwable.getMessage());
            }

            @Override
            public UserDto update(UserBo bo) {
                logger.error("更新用户信息失败: {}", throwable.getMessage());
                return null;
            }

            @Override
            public Map<String, Object> getAttribute(IdBo bo) {
                logger.error("获取用户附加信息失败: {}", throwable.getMessage());
                return null;
            }
        };
    }
}
