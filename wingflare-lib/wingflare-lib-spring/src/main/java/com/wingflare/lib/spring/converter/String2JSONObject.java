package com.wingflare.lib.spring.converter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author naizui_ycx
 * @date {2022/3/7}
 * @description
 */
@Component
public class String2JSONObject implements Converter<String, JSONObject> {

    @Override
    public JSONObject convert(String source) {
        return JSONObject.parseObject(source);
    }

}
