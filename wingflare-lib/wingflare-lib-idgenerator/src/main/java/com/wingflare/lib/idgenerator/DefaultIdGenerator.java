package com.wingflare.lib.idgenerator;

import com.wingflare.api.idgenerate.IdGenerate;

/**
 * 默认id生成器
 */
public class DefaultIdGenerator implements IdGenerate {

    private static IdGenerate _snowWorker = null;

    public DefaultIdGenerator(IdGeneratorOption options) throws IdGeneratorException {
        if (options == null) {
            throw new IdGeneratorException("options error.");
        }

        // 1.BaseTime
        if (options.baseTime < 315504000000L || options.baseTime > System.currentTimeMillis()) {
            throw new IdGeneratorException("BaseTime error.");
        }

        // 2.WorkerIdBitLength
        if (options.workerIdBitLength <= 0) {
            throw new IdGeneratorException("WorkerIdBitLength error.(range:[1, 21])");
        }
        if (options.workerIdBitLength + options.seqBitLength > 22) {
            throw new IdGeneratorException("error：WorkerIdBitLength + SeqBitLength <= 22");
        }

        // 3.WorkerId
        int maxWorkerIdNumber = (1 << options.workerIdBitLength) - 1;
        if (maxWorkerIdNumber == 0) {
            maxWorkerIdNumber = 63;
        }
        if (options.workerId < 0 || options.workerId > maxWorkerIdNumber) {
            throw new IdGeneratorException(
                    "WorkerId error. (range:[0, " + (maxWorkerIdNumber > 0 ? maxWorkerIdNumber : 63) + "]");
        }

        // 4.SeqBitLength
        if (options.seqBitLength < 2 || options.seqBitLength > 21) {
            throw new IdGeneratorException("SeqBitLength error. (range:[2, 21])");
        }

        // 5.MaxSeqNumber
        int maxSeqNumber = (1 << options.seqBitLength) - 1;
        if (maxSeqNumber == 0) {
            maxSeqNumber = 63;
        }
        if (options.maxSeqNumber < 0 || options.maxSeqNumber > maxSeqNumber) {
            throw new IdGeneratorException("MaxSeqNumber error. (range:[1, " + maxSeqNumber + "]");
        }

        // 6.MinSeqNumber
        if (options.minSeqNumber < 5 || options.minSeqNumber > maxSeqNumber) {
            throw new IdGeneratorException("MinSeqNumber error. (range:[5, " + maxSeqNumber + "]");
        }

        // 7.TopOverCostCount
        if (options.topOverCostCount < 0 || options.topOverCostCount > 10000) {
            throw new IdGeneratorException("TopOverCostCount error. (range:[0, 10000]");
        }

        switch (options.method) {
            case 2:
                _snowWorker = new SnowWorkerM2(options);
                break;
            case 1:
            default:
                _snowWorker = new SnowWorkerM1(options);
                break;
        }

        if (options.method == 1) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new IdGeneratorException(e);
            }
        }
    }

    @Override
    public long nextId() {
        return _snowWorker.nextId();
    }

}
