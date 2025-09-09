package com.wingflare.adapter.spring.common.converter;

import com.alibaba.fastjson.JSONArray;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author naizui_ycx
 * @date {2022/3/7}
 * @description
 */
@Component
public class String2JSONArray implements Converter <String, JSONArray> {


    @Override
    public JSONArray convert(String source) {
        return JSONArray.parseArray(source);
    }
}
