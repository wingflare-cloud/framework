package com.wingflare.facade.module.base.biz;


import com.wingflare.api.core.PageDto;
import com.wingflare.api.core.validate.Create;
import com.wingflare.api.core.validate.Update;
import com.wingflare.api.validation.annotation.Validated;
import com.wingflare.facade.module.base.bo.DictBO;
import com.wingflare.facade.module.base.bo.DictSearchBO;
import com.wingflare.facade.module.base.dto.DictDTO;
import com.wingflare.facade.module.base.dto.SimpleDictDTO;
import com.wingflare.lib.standard.bo.IdBo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;

import java.util.List;


/**
 * 系统字典Biz
 *
 * @author naizui_ycx
 * @date Sat Mar 04 17:48:17 CST 2023
 */
@Validated
public interface DictBiz {

    /**
     * 查询系统字典列表
     */
    PageDto<DictDTO> list(@Valid DictSearchBO bo);

    /**
     * 查询系统字典详情
     */
    DictDTO get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个系统字典详情
     */
    DictDTO getOnlyOne(@Valid @NotNull DictSearchBO searchBo);

    /**
     * 删除系统字典
     */
    void delete(@Valid @NotNull IdBo bo);

    /**
     * 新增系统字典
     */
    @Validated({Default.class, Create.class})
    DictDTO create(@Valid @NotNull DictBO bo);

    /**
     * 更新系统字典
     */
    @Validated({Default.class, Update.class})
    DictDTO update(@Valid @NotNull DictBO bo);

    /**
     * 刷新系统字典
     *
     */
    void refresh();

    /**
     * 从缓存中取出全部字典数据
     *
     * @return
     */
    List<SimpleDictDTO> getAllDictByCache();

}