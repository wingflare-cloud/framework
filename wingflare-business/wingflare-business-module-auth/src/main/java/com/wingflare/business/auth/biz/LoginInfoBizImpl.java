package com.wingflare.business.auth.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.auth.convert.LoginInfoConvert;
import com.wingflare.business.auth.db.LoginInfoDo;
import com.wingflare.business.auth.service.LoginInfoServer;
import com.wingflare.business.auth.wrapper.LoginInfoWrapper;
import com.wingflare.facade.module.auth.biz.LoginInfoBiz;
import com.wingflare.facade.module.auth.bo.LoginInfoBo;
import com.wingflare.facade.module.auth.bo.LoginInfoSearchBo;
import com.wingflare.facade.module.auth.dto.LoginInfoDto;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * 登陆信息业务
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
@Component
@Validated
public class LoginInfoBizImpl implements LoginInfoBiz {

    @Resource
    private LoginInfoServer loginInfoServer;

    /**
     * 查询登陆信息列表
     */
    @Override
    public PageDto<LoginInfoDto> list(@Valid LoginInfoSearchBo bo) {
        IPage<LoginInfoDo> iPage = loginInfoServer.page(
                loginInfoServer.createPage(bo),
                LoginInfoWrapper.getQueryWrapper(bo)
        );

        return PageUtil.convertIPage(iPage,
                LoginInfoConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询登陆信息详情
     */
    @Override
    public LoginInfoDto get(@Valid @NotNull IdBo bo) {
        return LoginInfoConvert.convert.doToDto(
                loginInfoServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个登陆信息详情
     */
    @Override
    public LoginInfoDto getOnlyOne(@Valid @NotNull LoginInfoSearchBo searchBo) {
        return LoginInfoConvert.convert.doToDto(
                loginInfoServer.getOne(
                        LoginInfoWrapper.getQueryWrapper(searchBo)
                ));
    }

    /**
     * 删除登陆信息
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(@Valid @NotNull IdBo bo) {
        LoginInfoDo loginInfoDo = loginInfoServer.getById(bo.getId());

        if (loginInfoDo != null) {
            loginInfoServer.removeById(bo.getId());
        }
    }

    /**
     * 新增登陆信息
     */
    @Override
    public LoginInfoDto create(@Validated(Create.class) @NotNull LoginInfoBo bo) {
        LoginInfoDo loginInfoDo = LoginInfoConvert.convert.boToDo(bo);
        loginInfoServer.save(loginInfoDo);
        return LoginInfoConvert.convert.doToDto(loginInfoDo);
    }

    /**
     * 更新登陆信息
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public LoginInfoDto update(@Validated(Update.class) @NotNull LoginInfoBo bo) {
        LoginInfoDo oldLoginInfoDo = loginInfoServer.getById(bo.getLoginId());

        if (oldLoginInfoDo == null) {
            throw new DataNotFoundException("loginInfo.data.notfound" );
        }

        LoginInfoDo loginInfoDo = LoginInfoConvert.convert.boToDo(bo);
        oldLoginInfoDo.setOnNew(loginInfoDo);
        loginInfoServer.updateById(oldLoginInfoDo);
        return LoginInfoConvert.convert.doToDto(loginInfoDo);
    }

    /**
     * 判断是否存在符合条件的登陆信息
     *
     * @param bo 查询参数
     * @return 登陆信息
     */
    public boolean has(LoginInfoSearchBo bo) {
        return loginInfoServer.has(
                LoginInfoWrapper.getQueryWrapper(bo)
        );
    }

}