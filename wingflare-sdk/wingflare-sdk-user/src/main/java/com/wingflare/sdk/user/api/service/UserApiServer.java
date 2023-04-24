package com.wingflare.sdk.user.api.service;

import com.wingflare.facade.module.user.biz.UserBiz;
import com.wingflare.facade.module.user.bo.UpdatePasswdBo;
import com.wingflare.facade.module.user.bo.UserBindRoleBo;
import com.wingflare.facade.module.user.bo.UserBo;
import com.wingflare.facade.module.user.bo.UserSearchBo;
import com.wingflare.facade.module.user.dto.UserDto;
import com.wingflare.lib.spring.annotation.ApiClient;
import com.wingflare.lib.spring.annotation.RequestAutoHeader;
import com.wingflare.lib.standard.PageDto;
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
    UserDto getUserByLoginName(@RequestParam("loginName") String loginName);

    /**
     * 更新用户密码
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value = "/user/updatePasswd", method = {RequestMethod.PUT})
    UserDto updatePasswd(@RequestBody UpdatePasswdBo bo);


    /**
     * 更新用户信息
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value = "/user/update", method = {RequestMethod.PUT})
    UserDto update(@RequestBody UserBo bo);


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
    PageDto<UserDto> list(@SpringQueryMap UserSearchBo bo);

    /**
     * 获取用户信息
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value = "/user/get", method = {RequestMethod.GET})
    UserDto get(@SpringQueryMap IdBo bo);

    /**
     * 获取当用户信息
     *
     * @param searchBo
     * @return
     */
    @Override
    @RequestMapping(value = "/user/getOnlyOne", method = {RequestMethod.GET})
    UserDto getOnlyOne(@SpringQueryMap UserSearchBo searchBo);

    /**
     * 删除单个用户
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value = "user/delete", method = {RequestMethod.DELETE})
    UserDto delete(IdBo bo);

    /**
     * 创建用户
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value = "/user/create", method = {RequestMethod.POST})
    UserDto create(UserBo bo);

    /**
     * 用户绑定角色
     *
     * @param bo
     */
    @Override
    @RequestMapping(value = "/user/role/bind", method = {RequestMethod.PUT})
    void userBindRole(UserBindRoleBo bo);

}
