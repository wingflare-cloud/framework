package com.wingflare.lib.core.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import java.util.Map;

public class MapArgsConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        StringBuilder sb = new StringBuilder();
        // 遍历所有参数
        for (Object arg : event.getArgumentArray()) {
            if (arg instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) arg;
                map.forEach((k, v) ->
                        sb.append(k).append("=").append(v).append("; ")
                );
            } else {
                sb.append(arg).append("; ");
            }
        }
        return sb.toString();
    }

}
