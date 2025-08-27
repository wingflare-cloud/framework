package com.wingflare.lib.task.context;

import com.wingflare.lib.task.log.TaskFileAppender;
import com.wingflare.lib.task.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

/**
 * helper for xxl-job
 *
 * @author xuxueli 2020-11-05
 */
public class TaskHelper {

    // ---------------------- base info ----------------------

    /**
     * current taskId
     *
     * @return
     */
    public static long gettaskId() {
        TaskContext taskContext = TaskContext.getXxlJobContext();
        if (taskContext == null) {
            return -1;
        }

        return taskContext.gettaskId();
    }

    /**
     * current JobParam
     *
     * @return
     */
    public static String getJobParam() {
        TaskContext taskContext = TaskContext.getXxlJobContext();
        if (taskContext == null) {
            return null;
        }

        return taskContext.getJobParam();
    }

    // ---------------------- for log ----------------------

    /**
     * current taskLogFileName
     *
     * @return
     */
    public static String gettaskLogFileName() {
        TaskContext taskContext = TaskContext.getXxlJobContext();
        if (taskContext == null) {
            return null;
        }

        return taskContext.gettaskLogFileName();
    }

    // ---------------------- for shard ----------------------

    /**
     * current ShardIndex
     *
     * @return
     */
    public static int getShardIndex() {
        TaskContext taskContext = TaskContext.getXxlJobContext();
        if (taskContext == null) {
            return -1;
        }

        return taskContext.getShardIndex();
    }

    /**
     * current ShardTotal
     *
     * @return
     */
    public static int getShardTotal() {
        TaskContext taskContext = TaskContext.getXxlJobContext();
        if (taskContext == null) {
            return -1;
        }

        return taskContext.getShardTotal();
    }

    // ---------------------- tool for log ----------------------

    private static Logger logger = LoggerFactory.getLogger("xxl-job logger");

    /**
     * append log with pattern
     *
     * @param appendLogPattern  like "aaa {} bbb {} ccc"
     * @param appendLogArguments    like "111, true"
     */
    public static boolean log(String appendLogPattern, Object ... appendLogArguments) {

        FormattingTuple ft = MessageFormatter.arrayFormat(appendLogPattern, appendLogArguments);
        String appendLog = ft.getMessage();

        /*appendLog = appendLogPattern;
        if (appendLogArguments!=null && appendLogArguments.length>0) {
            appendLog = MessageFormat.format(appendLogPattern, appendLogArguments);
        }*/

        StackTraceElement callInfo = new Throwable().getStackTrace()[1];
        return logDetail(callInfo, appendLog);
    }

    /**
     * append exception stack
     *
     * @param e
     */
    public static boolean log(Throwable e) {

        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        String appendLog = stringWriter.toString();

        StackTraceElement callInfo = new Throwable().getStackTrace()[1];
        return logDetail(callInfo, appendLog);
    }

    /**
     * append log
     *
     * @param callInfo
     * @param appendLog
     */
    private static boolean logDetail(StackTraceElement callInfo, String appendLog) {
        TaskContext taskContext = TaskContext.getXxlJobContext();
        if (taskContext == null) {
            return false;
        }

        /*// "yyyy-MM-dd HH:mm:ss [ClassName]-[MethodName]-[LineNumber]-[ThreadName] log";
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        StackTraceElement callInfo = stackTraceElements[1];*/

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(DateUtil.formatDateTime(new Date())).append(" ")
                .append("["+ callInfo.getClassName() + "#" + callInfo.getMethodName() +"]").append("-")
                .append("["+ callInfo.getLineNumber() +"]").append("-")
                .append("["+ Thread.currentThread().getName() +"]").append(" ")
                .append(appendLog!=null?appendLog:"");
        String formatAppendLog = stringBuffer.toString();

        // appendlog
        String logFileName = taskContext.gettaskLogFileName();

        if (logFileName!=null && logFileName.trim().length()>0) {
            TaskFileAppender.appendLog(logFileName, formatAppendLog);
            return true;
        } else {
            logger.info(">>>>>>>>>>> {}", formatAppendLog);
            return false;
        }
    }

    // ---------------------- tool for handleResult ----------------------

    /**
     * handle success
     *
     * @return
     */
    public static boolean handleSuccess(){
        return handleResult(TaskContext.HANDLE_CODE_SUCCESS, null);
    }

    /**
     * handle success with log msg
     *
     * @param handleMsg
     * @return
     */
    public static boolean handleSuccess(String handleMsg) {
        return handleResult(TaskContext.HANDLE_CODE_SUCCESS, handleMsg);
    }

    /**
     * handle fail
     *
     * @return
     */
    public static boolean handleFail(){
        return handleResult(TaskContext.HANDLE_CODE_FAIL, null);
    }

    /**
     * handle fail with log msg
     *
     * @param handleMsg
     * @return
     */
    public static boolean handleFail(String handleMsg) {
        return handleResult(TaskContext.HANDLE_CODE_FAIL, handleMsg);
    }

    /**
     * handle timeout
     *
     * @return
     */
    public static boolean handleTimeout(){
        return handleResult(TaskContext.HANDLE_CODE_TIMEOUT, null);
    }

    /**
     * handle timeout with log msg
     *
     * @param handleMsg
     * @return
     */
    public static boolean handleTimeout(String handleMsg){
        return handleResult(TaskContext.HANDLE_CODE_TIMEOUT, handleMsg);
    }

    /**
     * @param handleCode
     *
     *      200 : success
     *      500 : fail
     *      502 : timeout
     *
     * @param handleMsg
     * @return
     */
    public static boolean handleResult(int handleCode, String handleMsg) {
        TaskContext taskContext = TaskContext.getXxlJobContext();
        if (taskContext == null) {
            return false;
        }

        taskContext.setHandleCode(handleCode);
        if (handleMsg != null) {
            taskContext.setHandleMsg(handleMsg);
        }
        return true;
    }


}
