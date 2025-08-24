package com.wingflare.lib.task;


/**
 * 任务处理抽象类
 */
public abstract class TaskHandleAbstract {

    /**
     * execute handler, invoked when executor receives a scheduling request
     *
     * @throws Exception
     */
    public abstract void execute() throws Exception;

    /**
     * init handler, invoked when TaskThread init
     */
    public void init() throws Exception {
        // do something
    }


    /**
     * destroy handler, invoked when TaskThread destroy
     */
    public void destroy() throws Exception {
        // do something
    }

}
