package com.wingflare.lib.idgenerator;


import com.wingflare.api.idgenerate.IdGenerate;
import com.wingflare.lib.config.ConfigUtil;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多id生成器
 */
public final class MultiIdGenerator implements IdGenerate {

    private final Map<String, DefaultIdGenerator> idGeneratorMap = new ConcurrentHashMap<>();

    private DefaultIdGenerator generator(String key) {
        if (idGeneratorMap.containsKey(key)) {
            return idGeneratorMap.get(key);
        }

        synchronized (idGeneratorMap) {
            if (!idGeneratorMap.containsKey(key)) {
                IdGeneratorOption option = new IdGeneratorOption();

                Optional.ofNullable(ConfigUtil.getLongProperty(String.format("IdGenerate.%s.method", key)))
                        .ifPresent(method -> option.setMethod(method.shortValue()));

                Optional.ofNullable(ConfigUtil.getLongProperty(String.format("IdGenerate.%s.baseTime", key)))
                        .ifPresent(option::setBaseTime);

                Optional.ofNullable(ConfigUtil.getLongProperty(String.format("IdGenerate.%s.workerId", key)))
                        .ifPresent(workerId -> option.setWorkerId(workerId.shortValue()));

                Optional.ofNullable(ConfigUtil.getLongProperty(String.format("IdGenerate.%s.workerIdBitLength", key)))
                        .ifPresent(workerIdBitLength -> option.setWorkerIdBitLength(workerIdBitLength.byteValue()));

                Optional.ofNullable(ConfigUtil.getLongProperty(String.format("IdGenerate.%s.seqBitLength", key)))
                        .ifPresent(seqBitLength -> option.setSeqBitLength(seqBitLength.byteValue()));

                Optional.ofNullable(ConfigUtil.getLongProperty(String.format("IdGenerate.%s.maxSeqNumber", key)))
                        .ifPresent(maxSeqNumber -> option.setMaxSeqNumber(maxSeqNumber.shortValue()));

                Optional.ofNullable(ConfigUtil.getLongProperty(String.format("IdGenerate.%s.minSeqNumber", key)))
                        .ifPresent(minSeqNumber -> option.setMinSeqNumber(minSeqNumber.shortValue()));

                Optional.ofNullable(ConfigUtil.getLongProperty(String.format("IdGenerate.%s.topOverCostCount", key)))
                        .ifPresent(topOverCostCount -> option.setTopOverCostCount(topOverCostCount.shortValue()));

                idGeneratorMap.put(key, new DefaultIdGenerator(option));
            }

            return idGeneratorMap.get(key);
        }
    }

    @Override
    public long nextId() {
        return nextId("default");
    }

    @Override
    public long nextId(String key) {
        return generator(key).nextId();
    }

}
