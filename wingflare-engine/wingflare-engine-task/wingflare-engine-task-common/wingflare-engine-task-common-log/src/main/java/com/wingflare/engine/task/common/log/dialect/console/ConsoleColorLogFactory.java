package com.wingflare.engine.task.common.log.dialect.console;

import com.wingflare.engine.task.common.log.dialect.Log;
import com.wingflare.engine.task.common.log.factory.LogFactory;

/**
 * 利用System.out.println()打印彩色日志
 *
 * @author wodeyangzipingpingwuqi
 */
public class ConsoleColorLogFactory extends LogFactory {

    public ConsoleColorLogFactory() {
        super("Console Color Logging");
    }

    @Override
    public Log createLog(String name) {
        return new ConsoleColorLog(name);
    }

    @Override
    public Log createLog(Class<?> clazz) {
        return new ConsoleColorLog(clazz);
    }
}
