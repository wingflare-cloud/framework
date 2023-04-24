package com.wingflare.business.auth.biz;


import com.wingflare.business.auth.convert.LoginTokenConvert;
import com.wingflare.business.auth.db.LoginTokenDo;
import com.wingflare.business.auth.service.LoginTokenServer;
import com.wingflare.facade.module.auth.biz.LoginTokenBiz;
import com.wingflare.facade.module.auth.bo.LoginTokenBo;
import com.wingflare.facade.module.auth.bo.LoginTokenSearchBo;
import com.wingflare.facade.module.auth.dto.LoginTokenDto;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * 登陆token业务
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
@Component
@Validated
public class LoginTokenBizImpl implements LoginTokenBiz {

    @Resource
    private LoginTokenServer loginTokenServer;

    /**
     * 查询登陆token列表
     */
    @Override
    public PageDto<LoginTokenDto> list(@Valid LoginTokenSearchBo bo) {
        return loginTokenServer.getLoginTokenPage(bo);
    }

    /**
     * 查询登陆token详情
     */
    @Override
    public LoginTokenDto get(@Valid @NotNull IdBo bo) {
        return LoginTokenConvert.convert.doToDto(
                loginTokenServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个登陆token详情
     */
    @Override
    public LoginTokenDto getOnlyOne(@Valid @NotNull LoginTokenSearchBo searchBo) {
        return LoginTokenConvert.convert.doToDto(
                loginTokenServer.getLoginTokenOnlyOne(searchBo));
    }

    /**
     * 删除登陆token
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(@Valid @NotNull IdBo bo) {
        LoginTokenDo loginTokenDo = loginTokenServer.getById(bo.getId());

        if (loginTokenDo != null) {
            loginTokenServer.removeById(bo.getId());
        }
    }

    /**
     * 新增登陆token
     */
    @Override
    public LoginTokenDto create(@Validated(Create.class) @NotNull LoginTokenBo bo) {
        LoginTokenDo loginTokenDo = LoginTokenConvert.convert.boToDo(bo);
        loginTokenServer.save(loginTokenDo);
        return LoginTokenConvert.convert.doToDto(loginTokenDo);
    }

    /**
     * 更新登陆token
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public LoginTokenDto update(@Validated(Update.class) @NotNull LoginTokenBo bo) {
        LoginTokenDo oldLoginTokenDo = loginTokenServer.getById(bo.getTokenId());

        if (oldLoginTokenDo == null) {
            throw new DataNotFoundException("loginToken.data.notfound");
        }

        LoginTokenDo loginTokenDo = LoginTokenConvert.convert.boToDo(bo);
        oldLoginTokenDo.setOnNew(loginTokenDo);
        loginTokenServer.updateById(oldLoginTokenDo);
        return LoginTokenConvert.convert.doToDto(loginTokenDo);
    }

}