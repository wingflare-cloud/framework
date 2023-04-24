package com.wingflare.tool.generator.strategy;

import com.baomidou.mybatisplus.generator.config.builder.BaseBuilder;

public interface StrategyHandle<T extends BaseBuilder> {

    void handle(T builder, Strategy strategy, boolean fileOverride);

    String getBuildClassName();

    String getFileType();

}
