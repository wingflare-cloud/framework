package com.wingflare.business.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.auth.convert.LoginTokenConvert;
import com.wingflare.business.auth.db.LoginTokenDo;
import com.wingflare.business.auth.mapper.LoginTokenMapper;
import com.wingflare.business.auth.wrapper.LoginTokenWrapper;
import com.wingflare.facade.module.auth.bo.LoginTokenSearchBo;
import com.wingflare.facade.module.auth.dto.LoginTokenDto;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.PageDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登陆tokenServer
 *
 * @author naizui_ycx
 * @date Fri Mar 10 14:36:13 CST 2023
 */
@Service
public class LoginTokenServer extends BaseService<LoginTokenMapper, LoginTokenDo>
{
	/**
	 * 获取登陆token分页数据
	 *
	 * @param bo 查询参数
	 * @return 结果
	 */
	public PageDto<LoginTokenDto> getLoginTokenPage(LoginTokenSearchBo bo)
	{
		IPage<LoginTokenDo> iPage = page(
				createPage(bo),
				LoginTokenWrapper.getQueryWrapper(bo)
		);

		List<LoginTokenDto> modelList = LoginTokenConvert.convert.doToDtoList(iPage.getRecords());

		return PageUtil.convertIPage(iPage, modelList);
	}
	
	/**
     * 查询单个登陆token
     *
     * @param bo 查询参数
     * @return 登陆token
     */
    public LoginTokenDo getLoginTokenOnlyOne(LoginTokenSearchBo bo)
    {
		return getOne(
				LoginTokenWrapper.getQueryWrapper(bo)
		);
    }
	
	/**
     * 判断是否存在符合条件的登陆token
     *
     * @param bo 查询参数
     * @return 登陆token
     */
    public boolean hasLoginToken(LoginTokenSearchBo bo)
    {
		return has(
				LoginTokenWrapper.getQueryWrapper(bo)
		);
    }

    /**
     * 查询登陆token列表
     *
     * @param bo 查询参数
     * @return 登陆token集合
     */
    public List<LoginTokenDo> getLoginTokenList(LoginTokenSearchBo bo)
    {
		return list(
		        LoginTokenWrapper.getQueryWrapper(bo)
		);
    }

    /**
    * 获取登陆tokenMap
    *
    * bo 查询参数
    * @return 结果
    */
    public Map<String, LoginTokenDo> getLoginTokenMap(LoginTokenSearchBo bo)
	{
        List<LoginTokenDo> loginTokenDoList = getLoginTokenList(bo);

        if (loginTokenDoList == null || loginTokenDoList.isEmpty()) {
			return new HashMap<>();
        }

        Map<String, LoginTokenDo> loginTokenDoMap = new HashMap<>();

        for (LoginTokenDo loginTokenDo : loginTokenDoList) {
            loginTokenDoMap.put(loginTokenDo.getTokenId(), loginTokenDo);
        }

        return loginTokenDoMap;
    }
}
