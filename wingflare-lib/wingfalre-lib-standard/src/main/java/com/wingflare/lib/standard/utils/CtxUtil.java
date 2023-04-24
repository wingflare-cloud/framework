package com.wingflare.lib.standard.utils;


import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.utils.StringUtil;

import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;

/**
 * 框架上下文设置工具
 */
public class CtxUtil {

    /**
     * 设置全局基础上下文信息
     *
     * @param keys 需要写入的上下文key map
     * @param function 闭包
     */
    public static void cxtSetter(Map<String, String> keys, Function<String, String> function) {
        for (Map.Entry<String, String> key : keys.entrySet()) {
            String value = function.apply(key.getKey());
            if (StringUtil.isNotEmpty(value)) {
                String name;
                if (StringUtil.isNotBlank(key.getValue())) {
                    name = key.getValue();
                } else {
                    name = StringUtil.toCamelCase(key.getKey(), '-');
                }

                Matcher mat = SecurityUtil.typeValueMatch(value);

                if (mat.find()) {
                    ContextHolder.set(name, SecurityUtil.typeValueDecode(mat.group(1)));
                } else {
                    ContextHolder.set(name, value);
                }
            }
        }
    }

}
