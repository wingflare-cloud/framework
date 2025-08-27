package com.wingflare.task.admin.core.route.strategy;


import com.wingflare.lib.task.biz.ExecutorBiz;
import com.wingflare.lib.task.biz.model.IdleBeatParam;
import com.wingflare.lib.task.biz.model.ReturnT;
import com.wingflare.lib.task.biz.model.TriggerParam;
import com.wingflare.task.admin.core.route.ExecutorRouter;
import com.wingflare.task.admin.core.scheduler.TaskScheduler;
import com.wingflare.task.admin.core.util.I18nUtil;

import java.util.List;

/**
 * Created by xuxueli on 17/3/10.
 */
public class ExecutorRouteBusyover extends ExecutorRouter {

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        StringBuffer idleBeatResultSB = new StringBuffer();
        for (String address : addressList) {
            // beat
            ReturnT<String> idleBeatResult = null;
            try {
                ExecutorBiz executorBiz = TaskScheduler.getExecutorBiz(address);
                idleBeatResult = executorBiz.idleBeat(new IdleBeatParam(triggerParam.gettaskId()));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                idleBeatResult = new ReturnT<String>(ReturnT.FAIL_CODE, ""+e );
            }
            idleBeatResultSB.append( (idleBeatResultSB.length()>0)?"<br><br>":"")
                    .append(I18nUtil.getString("jobconf_idleBeat") + "：")
                    .append("<br>address：").append(address)
                    .append("<br>code：").append(idleBeatResult.getCode())
                    .append("<br>msg：").append(idleBeatResult.getMsg());

            // beat success
            if (idleBeatResult.getCode() == ReturnT.SUCCESS_CODE) {
                idleBeatResult.setMsg(idleBeatResultSB.toString());
                idleBeatResult.setContent(address);
                return idleBeatResult;
            }
        }

        return new ReturnT<String>(ReturnT.FAIL_CODE, idleBeatResultSB.toString());
    }

}
