package com.wingflare.sdk.user.api.fallback;


import com.wingflare.api.core.PageDto;
import com.wingflare.facade.module.user.bo.UpdatePasswdBO;
import com.wingflare.facade.module.user.bo.UserBO;
import com.wingflare.facade.module.user.bo.UserBindRoleBO;
import com.wingflare.facade.module.user.bo.UserSearchBO;
import com.wingflare.facade.module.user.dto.UserDTO;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.sdk.user.api.service.UserApiServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.e;

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
            public UserDTO updatePasswd(UpdatePasswdBO bo) {
                logger.error("更新用户密码失败", e(Map.of("message", throwable.getMessage())));
                return null;
            }

            @Override
            public UserDTO getUserByLoginName(String loginName) {
                logger.error("通过用户登录名获取用户信息失败", e(Map.of("message", throwable.getMessage())));
                return null;
            }

            @Override
            public PageDto<UserDTO> list(UserSearchBO bo) {
                logger.error("获取用户列表失败", e(Map.of("message", throwable.getMessage())));
                return null;
            }

            @Override
            public UserDTO get(IdBo bo) {
                logger.error("通过id获取用户信息失败", e(Map.of("message", throwable.getMessage())));
                return null;
            }

            @Override
            public UserDTO getOnlyOne(UserSearchBO searchBo) {
                logger.error("查询单个用户数据失败", e(Map.of("message", throwable.getMessage())));
                return null;
            }

            @Override
            public UserDTO delete(IdBo bo) {
                logger.error("删除用户失败", e(Map.of("message", throwable.getMessage())));
                return null;
            }

            @Override
            public UserDTO create(UserBO bo) {
                logger.error("创建用户失败", e(Map.of("message", throwable.getMessage())));
                return null;
            }

            @Override
            public void userBindRole(UserBindRoleBO bo) {
                logger.error("用户绑定角色失败", e(Map.of("message", throwable.getMessage())));
            }

            @Override
            public UserDTO update(UserBO bo) {
                logger.error("更新用户信息失败", e(Map.of("message", throwable.getMessage())));
                return null;
            }

            @Override
            public Map<String, Object> getAttribute(IdBo bo) {
                logger.error("获取用户附加信息失败", e(Map.of("message", throwable.getMessage())));
                return null;
            }
        };
    }
}
