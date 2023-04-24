package com.wingflare.facade.module.auth.biz;


import com.wingflare.facade.module.auth.bo.LoginTokenBo;
import com.wingflare.facade.module.auth.bo.LoginTokenSearchBo;
import com.wingflare.facade.module.auth.dto.LoginTokenDto;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * 登陆tokenBiz
 *
 * @author naizui_ycx
 * @date Fri Mar 10 14:36:13 CST 2023
 */
@Validated
public interface LoginTokenBiz {

    /**
     * 查询登陆token列表
     */
    PageDto<LoginTokenDto> list(@Valid LoginTokenSearchBo bo);

    /**
     * 查询登陆token详情
     */
    LoginTokenDto get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个登陆token详情
     */
    LoginTokenDto getOnlyOne(@Valid @NotNull LoginTokenSearchBo searchBo);

    /**
     * 删除登陆token
     */
    void delete(@Valid @NotNull IdBo bo);

    /**
     * 新增登陆token
     */
    LoginTokenDto create(@Validated(Create.class) @NotNull LoginTokenBo bo);

    /**
     * 更新登陆token
     */
    LoginTokenDto update(@Validated(Update.class) @NotNull LoginTokenBo bo);

}