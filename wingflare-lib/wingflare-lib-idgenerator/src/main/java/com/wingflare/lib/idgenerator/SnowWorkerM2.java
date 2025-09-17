package com.wingflare.lib.idgenerator;

/**
 * 传统雪花
 */
public class SnowWorkerM2 extends SnowWorkerM1 {

    public SnowWorkerM2(IdGeneratorOption options) {
        super(options);
    }

    @Override
    public long nextId() {
        synchronized (_syncLock) {
            long currentTimeTick = getCurrentTimeTick();

            if (_lastTimeTick == currentTimeTick) {
                if (_currentSeqNumber++ > maxSeqNumber) {
                    _currentSeqNumber = minSeqNumber;
                    currentTimeTick = getNextTimeTick();
                }
            } else {
                _currentSeqNumber = minSeqNumber;
            }

            if (currentTimeTick < _lastTimeTick) {
                throw new IdGeneratorException("Time error for {0} milliseconds", _lastTimeTick - currentTimeTick);
            }

            _lastTimeTick = currentTimeTick;

            return ((currentTimeTick << _timestampShift) + ((long) workerId << seqBitLength) + (int) _currentSeqNumber);
        }

    }

}
