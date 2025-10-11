package com.wingflare.lib.idgenerator;

import com.wingflare.api.idgenerate.IdGenerate;

/**
 * 漂移雪花
 */
public class SnowWorkerM1 implements IdGenerate {

    /**
     * 基础时间
     */
    protected final long baseTime;

    /**
     * 机器码
     */
    protected final short workerId;

    /**
     * 机器码位长
     */
    protected final byte workerIdBitLength;

    /**
     * 自增序列数位长
     */
    protected final byte seqBitLength;

    /**
     * 最大序列数（含）
     */
    protected final int maxSeqNumber;

    /**
     * 最小序列数（含）
     */
    protected final short minSeqNumber;

    /**
     * 最大漂移次数（含）
     */
    protected final int topOverCostCount;

    protected final byte _timestampShift;
    protected final static byte[] _syncLock = new byte[0];

    protected short _currentSeqNumber;
    protected long _lastTimeTick = 0;
    protected long _turnBackTimeTick = 0;
    protected byte _turnBackIndex = 0;

    protected boolean _isOverCost = false;
    protected int _overCostCountInOneTerm = 0;
    protected int _genCountInOneTerm = 0;
    protected int _termIndex = 0;

    public SnowWorkerM1(IdGeneratorOption options) {
        baseTime = options.getBaseTime() != 0 ? options.getBaseTime() : 1582136402000L;
        workerIdBitLength = options.getWorkerIdBitLength() == 0 ? 6 : options.getWorkerIdBitLength();
        workerId = options.getWorkerId();
        seqBitLength = options.getSeqBitLength() == 0 ? 6 : options.getSeqBitLength();
        maxSeqNumber = options.getMaxSeqNumber() <= 0 ? (1 << seqBitLength) - 1 : options.getMaxSeqNumber();
        minSeqNumber = options.getMinSeqNumber();
        topOverCostCount = options.getTopOverCostCount();
        _timestampShift = (byte) (workerIdBitLength + seqBitLength);
        _currentSeqNumber = minSeqNumber;
    }

    private void doGenIdAction(OverCostActionArg arg) {

    }

    private void beginOverCostAction(long useTimeTick) {

    }

    private void endOverCostAction(long useTimeTick) {

    }

    private void beginTurnBackAction(long useTimeTick) {

    }

    private void endTurnBackAction(long useTimeTick) {

    }

    private long nextOverCostId() {
        long currentTimeTick = getCurrentTimeTick();

        if (currentTimeTick > _lastTimeTick) {
            endOverCostAction(currentTimeTick);

            _lastTimeTick = currentTimeTick;
            _currentSeqNumber = minSeqNumber;
            _isOverCost = false;
            _overCostCountInOneTerm = 0;
            _genCountInOneTerm = 0;

            return calcId(_lastTimeTick);
        }

        if (_overCostCountInOneTerm >= topOverCostCount) {
            endOverCostAction(currentTimeTick);

            _lastTimeTick = getNextTimeTick();
            _currentSeqNumber = minSeqNumber;
            _isOverCost = false;
            _overCostCountInOneTerm = 0;
            _genCountInOneTerm = 0;

            return calcId(_lastTimeTick);
        }

        if (_currentSeqNumber > maxSeqNumber) {
            _lastTimeTick++;
            _currentSeqNumber = minSeqNumber;
            _isOverCost = true;
            _overCostCountInOneTerm++;
            _genCountInOneTerm++;

            return calcId(_lastTimeTick);
        }

        _genCountInOneTerm++;
        return calcId(_lastTimeTick);
    }

    private long NextNormalId() throws IdGeneratorException {
        long currentTimeTick = getCurrentTimeTick();

        if (currentTimeTick < _lastTimeTick) {
            if (_turnBackTimeTick < 1) {
                _turnBackTimeTick = _lastTimeTick - 1;
                _turnBackIndex++;
                // 每毫秒序列数的前5位是预留位，0用于手工新值，1-4是时间回拨次序
                // 支持4次回拨次序（避免回拨重叠导致ID重复），可无限次回拨（次序循环使用）。
                if (_turnBackIndex > 4) {
                    _turnBackIndex = 1;
                }
            }

            return calcTurnBackId(_turnBackTimeTick);
        }

        // 时间追平时，_turnBackTimeTick清零
        if (_turnBackTimeTick > 0) {
            endTurnBackAction(_turnBackTimeTick);
            _turnBackTimeTick = 0;
        }

        if (currentTimeTick > _lastTimeTick) {
            _lastTimeTick = currentTimeTick;
            _currentSeqNumber = minSeqNumber;

            return calcId(_lastTimeTick);
        }

        if (_currentSeqNumber > maxSeqNumber) {
            beginOverCostAction(currentTimeTick);

            _termIndex++;
            _lastTimeTick++;
            _currentSeqNumber = minSeqNumber;
            _isOverCost = true;
            _overCostCountInOneTerm = 1;
            _genCountInOneTerm = 1;

            return calcId(_lastTimeTick);
        }

        return calcId(_lastTimeTick);
    }

    private long calcId(long useTimeTick) {
        long result = ((useTimeTick << _timestampShift) +
                ((long) workerId << seqBitLength) +
                (int) _currentSeqNumber);

        _currentSeqNumber++;
        return result;
    }

    private long calcTurnBackId(long useTimeTick) {
        long result = ((useTimeTick << _timestampShift) +
                ((long) workerId << seqBitLength) + _turnBackIndex);

        _turnBackTimeTick--;
        return result;
    }

    protected long getCurrentTimeTick() {
        long millis = System.currentTimeMillis();
        return millis - baseTime;
    }

    protected long getNextTimeTick() {
        long tempTimeTicker = getCurrentTimeTick();

        while (tempTimeTicker <= _lastTimeTick) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tempTimeTicker = getCurrentTimeTick();
        }

        return tempTimeTicker;
    }

    @Override
    public long nextId() {
        synchronized (_syncLock) {
            return _isOverCost ? nextOverCostId() : NextNormalId();
        }
    }

}
