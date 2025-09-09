package com.wingflare.facade.module.base.biz;


import com.wingflare.api.core.PageDto;
import com.wingflare.facade.module.base.bo.SettingBO;
import com.wingflare.facade.module.base.bo.SettingSearchBO;
import com.wingflare.facade.module.base.dto.SettingDTO;
import com.wingflare.lib.standard.bo.IdBo;


/**
 * 系统设置Biz
 *
 * @author naizui_ycx
 * @date Fri Mar 03 09:48:21 CST 2023
 */
public interface SettingBiz {

    /**
     * 查询系统设置列表
     */
    PageDto<SettingDTO> list(SettingSearchBO bo);

    /**
     * 查询系统设置详情
     */
    SettingDTO get(IdBo bo);

    /**
     * 通过条件查询单个系统设置详情
     */
    SettingDTO getOnlyOne(SettingSearchBO searchBo);

    /**
     * 删除系统设置
     */
    void delete(IdBo bo);

    /**
     * 新增系统设置
     */
    SettingDTO create(SettingBO bo);

    /**
     * 更新系统设置
     */
    SettingDTO update(SettingBO bo);

    /**
     * 保存并刷新设置
     *
     * @param bo
     */
    void save(SettingBO bo);

}