package com.wingflare.facade.module.base.biz;


import com.wingflare.facade.module.base.bo.SettingBo;
import com.wingflare.facade.module.base.bo.SettingSearchBo;
import com.wingflare.facade.module.base.dto.SettingDto;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;


/**
 * 系统设置Biz
 *
 * @author naizui_ycx
 * @date Fri Mar 03 09:48:21 CST 2023
 */
@Validated
public interface SettingBiz {

    /**
     * 查询系统设置列表
     */
    PageDto<SettingDto> list(@Valid SettingSearchBo bo);

    /**
     * 查询系统设置详情
     */
    SettingDto get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个系统设置详情
     */
    SettingDto getOnlyOne(@Valid @NotNull SettingSearchBo searchBo);

    /**
     * 删除系统设置
     */
    void delete(@Valid @NotNull IdBo bo);

    /**
     * 新增系统设置
     */
    @Validated({Default.class, Create.class})
    SettingDto create(@Valid @NotNull SettingBo bo);

    /**
     * 更新系统设置
     */
    @Validated({Default.class, Update.class})
    SettingDto update(@Valid @NotNull SettingBo bo);

    /**
     * 保存并刷新设置
     *
     * @param bo
     */
    @Validated({Default.class, Create.class})
    void save(@Valid @NotNull SettingBo bo);

}