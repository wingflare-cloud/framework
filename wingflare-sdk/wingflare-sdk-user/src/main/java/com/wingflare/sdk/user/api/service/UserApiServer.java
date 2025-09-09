package com.wingflare.sdk.user.api.service;

import com.wingflare.api.core.PageDto;
import com.wingflare.facade.module.user.biz.UserBiz;
import com.wingflare.facade.module.user.bo.UpdatePasswdBO;
import com.wingflare.facade.module.user.bo.UserBindRoleBO;
import com.wingflare.facade.module.user.bo.UserBO;
import com.wingflare.facade.module.user.bo.UserSearchBO;
import com.wingflare.facade.module.user.dto.UserDTO;
import com.wingflare.lib.spring.annotation.ApiClient;
import com.wingflare.lib.spring.annotation.RequestAutoHeader;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.sdk.user.Info;
import com.wingflare.sdk.user.api.fallback.UserApiFallbackFactory;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @InterfaceName UserApiServer
 * @Author naizui_ycx
 * @Date 2023/03/10
 * @Description 用户开放接口服务
 */
@ApiClient(
        contextId = "userApiService",
        name = Info.SERVER_NAME,
        fallbackFactory = UserApiFallbackFactory.class
)
@RequestAutoHeader
public interface UserApiServer extends UserBiz {

    /**
     * 通过用户名查询用户信息
     *
     * @param loginName 用户名
     * @return 结果
     */
    @Override
    @RequestMapping(value = "/user/getUserByLoginName", method = {RequestMethod.GET})
    UserDTO getUserByLoginName(@RequestParam("loginName") String loginName);

    /**
     * 更新用户密码
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value = "/user/updatePasswd", method = {RequestMethod.PUT})
    UserDTO updatePasswd(@RequestBody UpdatePasswdBO bo);


    /**
     * 更新用户信息
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value = "/user/update", method = {RequestMethod.PUT})
    UserDTO update(@RequestBody UserBO bo);


    /**
     * 获取用户附加信息
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value="/user/getAttribute", method={RequestMethod.GET})
    Map<String, Object> getAttribute(@SpringQueryMap IdBo bo);

    /**
     * 获取用户列表信息
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value="/user/list", method={RequestMethod.GET})
    PageDto<UserDTO> list(@SpringQueryMap UserSearchBO bo);

    /**
     * 获取用户信息
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value = "/user/get", method = {RequestMethod.GET})
    UserDTO get(@SpringQueryMap IdBo bo);

    /**
     * 获取当用户信息
     *
     * @param searchBo
     * @return
     */
    @Override
    @RequestMapping(value = "/user/getOnlyOne", method = {RequestMethod.GET})
    UserDTO getOnlyOne(@SpringQueryMap UserSearchBO searchBo);

    /**
     * 删除单个用户
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value = "user/delete", method = {RequestMethod.DELETE})
    UserDTO delete(IdBo bo);

    /**
     * 创建用户
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value = "/user/create", method = {RequestMethod.POST})
    UserDTO create(UserBO bo);

    /**
     * 用户绑定角色
     *
     * @param bo
     */
    @Override
    @RequestMapping(value = "/user/role/bind", method = {RequestMethod.PUT})
    void userBindRole(UserBindRoleBO bo);

}
