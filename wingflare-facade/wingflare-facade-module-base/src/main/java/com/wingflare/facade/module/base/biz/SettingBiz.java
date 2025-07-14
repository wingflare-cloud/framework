package com.wingflare.facade.module.base.biz;


import com.wingflare.facade.module.base.bo.SettingBO;
import com.wingflare.facade.module.base.bo.SettingSearchBO;
import com.wingflare.facade.module.base.dto.SettingDTO;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;


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
    PageDto<SettingDTO> list(@Valid SettingSearchBO bo);

    /**
     * 查询系统设置详情
     */
    SettingDTO get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个系统设置详情
     */
    SettingDTO getOnlyOne(@Valid @NotNull SettingSearchBO searchBo);

    /**
     * 删除系统设置
     */
    void delete(@Valid @NotNull IdBo bo);

    /**
     * 新增系统设置
     */
    @Validated({Default.class, Create.class})
    SettingDTO create(@Valid @NotNull SettingBO bo);

    /**
     * 更新系统设置
     */
    @Validated({Default.class, Update.class})
    SettingDTO update(@Valid @NotNull SettingBO bo);

    /**
     * 保存并刷新设置
     *
     * @param bo
     */
    @Validated({Default.class, Create.class})
    void save(@Valid @NotNull SettingBO bo);

}