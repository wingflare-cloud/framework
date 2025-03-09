package com.wingflare.facade.module.base.biz;


import com.wingflare.facade.module.base.bo.DictBo;
import com.wingflare.facade.module.base.bo.DictSearchBo;
import com.wingflare.facade.module.base.dto.DictDto;
import com.wingflare.facade.module.base.dto.SimpleDictDto;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
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
    PageDto<DictDto> list(@Valid DictSearchBo bo);

    /**
     * 查询系统字典详情
     */
    DictDto get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个系统字典详情
     */
    DictDto getOnlyOne(@Valid @NotNull DictSearchBo searchBo);

    /**
     * 删除系统字典
     */
    void delete(@Valid @NotNull IdBo bo);

    /**
     * 新增系统字典
     */
    @Validated({Default.class, Create.class})
    DictDto create(@Valid @NotNull DictBo bo);

    /**
     * 更新系统字典
     */
    @Validated({Default.class, Update.class})
    DictDto update(@Valid @NotNull DictBo bo);

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
    List<SimpleDictDto> getAllDictByCache();

}