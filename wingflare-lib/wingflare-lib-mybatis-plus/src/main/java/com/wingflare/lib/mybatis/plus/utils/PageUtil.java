package com.wingflare.lib.mybatis.plus.utils;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.api.core.PageDto;

import java.util.List;

/**
 * @ClassName PageUtil
 * @Author naizui_ycx
 * @Date 2023/04/04 04
 * @Description 翻页工具
 */
public class PageUtil {

    /**
     * IPage 转换成 PageModel 对象
     *
     * @param iPage     MyBatisPlus查询返回结果分页对象
     * @param modelList model数据列表
     * @return {@link PageDto <Model>} 系统查询返回结果分页对象
     * @author shaoyuyao
     * @date 2022/8/9 16:34
     */
    public static <Model> PageDto<Model> convertIPage(IPage iPage, List<Model> modelList) {
        PageDto<Model> pageModel = new PageDto<Model>();
        pageModel.setPage((int) iPage.getCurrent());
        pageModel.setPageSize((int) iPage.getSize());
        pageModel.setList(modelList);
        pageModel.setSize(iPage.getTotal());
        pageModel.setNowSize(modelList != null ? modelList.size() : 0);
        return pageModel;
    }

}
