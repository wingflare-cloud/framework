package com.wingflare.lib.idgenerator;

/**
 * 雪花算法配置
 */
public class IdGeneratorOption {

    /**
     * 雪花计算方法
     * （1-漂移算法|2-传统算法），默认1
     */
    protected short method = 1;

    /**
     * 基础时间（ms单位）
     * 不能超过当前系统时间
     */
    protected long baseTime = 1760025600000L;

    /**
     * 机器码
     * 必须由外部设定，最大值 2^WorkerIdBitLength-1
     */
    protected short workerId = 0;

    /**
     * 机器码位长
     * 默认值6，取值范围 [1, 15]（要求：序列数位长+机器码位长不超过22）
     */
    protected byte workerIdBitLength = 6;

    /**
     * 序列数位长
     * 默认值6，取值范围 [3, 21]（要求：序列数位长+机器码位长不超过22）
     */
    protected byte seqBitLength = 6;

    /**
     * 最大序列数（含）
     * 设置范围 [MinSeqNumber, 2^SeqBitLength-1]，默认值0，表示最大序列数取最大值（2^SeqBitLength-1]）
     */
    protected short maxSeqNumber = 0;

    /**
     * 最小序列数（含）
     * 默认值5，取值范围 [5, MaxSeqNumber]，每毫秒的前5个序列数对应编号是0-4是保留位，其中1-4是时间回拨相应预留位，0是手工新值预留位
     */
    protected short minSeqNumber = 5;

    /**
     * 最大漂移次数（含）
     * 默认2000，推荐范围500-10000（与计算能力有关）
     */
    protected short topOverCostCount = 2000;

    public short getMethod() {
        return method;
    }

    public void setMethod(short method) {
        this.method = method;
    }

    public long getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(long baseTime) {
        this.baseTime = baseTime;
    }

    public short getWorkerId() {
        return workerId;
    }

    public void setWorkerId(short workerId) {
        this.workerId = workerId;
    }

    public byte getWorkerIdBitLength() {
        return workerIdBitLength;
    }

    public void setWorkerIdBitLength(byte workerIdBitLength) {
        this.workerIdBitLength = workerIdBitLength;
    }

    public byte getSeqBitLength() {
        return seqBitLength;
    }

    public void setSeqBitLength(byte seqBitLength) {
        this.seqBitLength = seqBitLength;
    }

    public short getMaxSeqNumber() {
        return maxSeqNumber;
    }

    public void setMaxSeqNumber(short maxSeqNumber) {
        this.maxSeqNumber = maxSeqNumber;
    }

    public short getMinSeqNumber() {
        return minSeqNumber;
    }

    public void setMinSeqNumber(short minSeqNumber) {
        this.minSeqNumber = minSeqNumber;
    }

    public short getTopOverCostCount() {
        return topOverCostCount;
    }

    public void setTopOverCostCount(short topOverCostCount) {
        this.topOverCostCount = topOverCostCount;
    }
}
