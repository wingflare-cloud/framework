package com.wingflare.abstraction.module.base;

import com.wingflare.facade.module.base.dto.SimpleDictDto;

/**
 * @InterfaceName DictStorage
 * @Author naizui_ycx
 * @Email chenxi2511@qq.com
 * @Description 字典存储接口
 */
public interface DictStorage {

    /**
     * 批量存储系统字典
     *
     * @param systemCode
     * @param simpleDictDtos
     * @return
     */
    Long save(String systemCode, SimpleDictDto... simpleDictDtos);

}
