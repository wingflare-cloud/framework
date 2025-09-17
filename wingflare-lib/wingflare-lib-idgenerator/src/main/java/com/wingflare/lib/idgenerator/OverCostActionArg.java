package com.wingflare.lib.idgenerator;


/**
 * Id生成时回调参数
 */
public class OverCostActionArg {

    /**
     * 事件类型
     * 1-开始，2-结束，8-漂移
     */
    public int actionType = 0;

    /**
     * 时间戳
     */
    public long timeTick = 0;

    /**
     * 机器码
     */
    public short workerId = 0;

    /**
     *
     */
    public int overCostCountInOneTerm = 0;

    /**
     * 漂移期间生产ID个数
     */
    public int genCountInOneTerm = 0;

    /**
     * 漂移周期
     */
    public int termIndex = 0;

    public OverCostActionArg(short workerId, long timeTick, int actionType, int overCostCountInOneTerm, int genCountWhenOverCost, int index) {
        this.actionType = actionType;
        this.timeTick = timeTick;
        this.workerId = workerId;
        this.overCostCountInOneTerm = overCostCountInOneTerm;
        this.genCountInOneTerm = genCountWhenOverCost;
        this.termIndex = index;
    }

}
