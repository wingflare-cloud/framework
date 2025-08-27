package com.wingflare.lib.task.executor;

import com.wingflare.lib.task.biz.AdminBiz;
import com.wingflare.lib.task.biz.client.AdminBizClient;
import com.wingflare.lib.task.handler.IJobHandler;
import com.wingflare.lib.task.annotation.WFTask;
import com.wingflare.lib.task.handler.impl.MethodJobHandler;
import com.wingflare.lib.task.log.TaskFileAppender;
import com.wingflare.lib.task.server.EmbedServer;
import com.wingflare.lib.task.thread.TaskLogFileCleanThread;
import com.wingflare.lib.task.thread.TaskThread;
import com.wingflare.lib.task.thread.TriggerCallbackThread;
import com.wingflare.lib.task.util.IpUtil;
import com.wingflare.lib.task.util.NetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by xuxueli on 2016/3/2 21:14.
 */
public class TaskExecutor {
    private static final Logger logger = LoggerFactory.getLogger(TaskExecutor.class);

    // ---------------------- param ----------------------
    private String adminAddresses;
    private String accessToken;
    private int timeout;
    private String appname;
    private String address;
    private String ip;
    private int port;
    private String logPath;
    private int logRetentionDays;

    public void setAdminAddresses(String adminAddresses) {
        this.adminAddresses = adminAddresses;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
    public void setAppname(String appname) {
        this.appname = appname;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
    public void setLogRetentionDays(int logRetentionDays) {
        this.logRetentionDays = logRetentionDays;
    }


    // ---------------------- start + stop ----------------------
    public void start() throws Exception {

        // init logpath
        TaskFileAppender.initLogPath(logPath);

        // init invoker, admin-client
        initAdminBizList(adminAddresses, accessToken, timeout);


        // init taskLogFileCleanThread
        TaskLogFileCleanThread.getInstance().start(logRetentionDays);

        // init TriggerCallbackThread
        TriggerCallbackThread.getInstance().start();

        // init executor-server
        initEmbedServer(address, ip, port, appname, accessToken);
    }

    public void destroy(){
        // destroy executor-server
        stopEmbedServer();

        // destroy jobThreadRepository
        if (jobThreadRepository.size() > 0) {
            for (Map.Entry<Integer, TaskThread> item: jobThreadRepository.entrySet()) {
                TaskThread oldTaskThread = removeJobThread(item.getKey(), "web container destroy and kill the job.");
                // wait for job thread push result to callback queue
                if (oldTaskThread != null) {
                    try {
                        oldTaskThread.join();
                    } catch (InterruptedException e) {
                        logger.error(">>>>>>>>>>> xxl-job, JobThread destroy(join) error, taskId:{}", item.getKey(), e);
                    }
                }
            }
            jobThreadRepository.clear();
        }
        jobHandlerRepository.clear();


        // destroy taskLogFileCleanThread
        TaskLogFileCleanThread.getInstance().toStop();

        // destroy TriggerCallbackThread
        TriggerCallbackThread.getInstance().toStop();

    }


    // ---------------------- admin-client (rpc invoker) ----------------------
    private static List<AdminBiz> adminBizList;
    private void initAdminBizList(String adminAddresses, String accessToken, int timeout) throws Exception {
        if (adminAddresses!=null && adminAddresses.trim().length()>0) {
            for (String address: adminAddresses.trim().split(",")) {
                if (address!=null && address.trim().length()>0) {

                    AdminBiz adminBiz = new AdminBizClient(address.trim(), accessToken, timeout);

                    if (adminBizList == null) {
                        adminBizList = new ArrayList<AdminBiz>();
                    }
                    adminBizList.add(adminBiz);
                }
            }
        }
    }

    public static List<AdminBiz> getAdminBizList(){
        return adminBizList;
    }

    // ---------------------- executor-server (rpc provider) ----------------------
    private EmbedServer embedServer = null;

    private void initEmbedServer(String address, String ip, int port, String appname, String accessToken) throws Exception {

        // fill ip port
        port = port>0?port: NetUtil.findAvailablePort(9999);
        ip = (ip!=null&&ip.trim().length()>0)?ip: IpUtil.getIp();

        // generate address
        if (address==null || address.trim().length()==0) {
            String ip_port_address = IpUtil.getIpPort(ip, port);   // registry-address：default use address to registry , otherwise use ip:port if address is null
            address = "http://{ip_port}/".replace("{ip_port}", ip_port_address);
        }

        // accessToken
        if (accessToken==null || accessToken.trim().length()==0) {
            logger.warn(">>>>>>>>>>> xxl-job accessToken is empty. To ensure system security, please set the accessToken.");
        }

        // start
        embedServer = new EmbedServer();
        embedServer.start(address, port, appname, accessToken);
    }

    private void stopEmbedServer() {
        // stop provider factory
        if (embedServer != null) {
            try {
                embedServer.stop();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }


    // ---------------------- job handler repository ----------------------
    private static ConcurrentMap<String, IJobHandler> jobHandlerRepository = new ConcurrentHashMap<String, IJobHandler>();
    public static IJobHandler loadJobHandler(String name){
        return jobHandlerRepository.get(name);
    }
    public static IJobHandler registJobHandler(String name, IJobHandler jobHandler){
        logger.info(">>>>>>>>>>> xxl-job register jobhandler success, name:{}, jobHandler:{}", name, jobHandler);
        return jobHandlerRepository.put(name, jobHandler);
    }
    protected void registJobHandler(WFTask WFTask, Object bean, Method executeMethod){
        if (WFTask == null) {
            return;
        }

        String name = WFTask.value();
        //make and simplify the variables since they'll be called several times later
        Class<?> clazz = bean.getClass();
        String methodName = executeMethod.getName();
        if (name.trim().length() == 0) {
            throw new RuntimeException("xxl-job method-jobhandler name invalid, for[" + clazz + "#" + methodName + "] .");
        }
        if (loadJobHandler(name) != null) {
            throw new RuntimeException("xxl-job jobhandler[" + name + "] naming conflicts.");
        }

        // execute method
        /*if (!(method.getParameterTypes().length == 1 && method.getParameterTypes()[0].isAssignableFrom(String.class))) {
            throw new RuntimeException("xxl-job method-jobhandler param-classtype invalid, for[" + bean.getClass() + "#" + method.getName() + "] , " +
                    "The correct method format like \" public ReturnT<String> execute(String param) \" .");
        }
        if (!method.getReturnType().isAssignableFrom(ReturnT.class)) {
            throw new RuntimeException("xxl-job method-jobhandler return-classtype invalid, for[" + bean.getClass() + "#" + method.getName() + "] , " +
                    "The correct method format like \" public ReturnT<String> execute(String param) \" .");
        }*/

        executeMethod.setAccessible(true);

        // init and destroy
        Method initMethod = null;
        Method destroyMethod = null;

        if (WFTask.init().trim().length() > 0) {
            try {
                initMethod = clazz.getDeclaredMethod(WFTask.init());
                initMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("xxl-job method-jobhandler initMethod invalid, for[" + clazz + "#" + methodName + "] .");
            }
        }
        if (WFTask.destroy().trim().length() > 0) {
            try {
                destroyMethod = clazz.getDeclaredMethod(WFTask.destroy());
                destroyMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("xxl-job method-jobhandler destroyMethod invalid, for[" + clazz + "#" + methodName + "] .");
            }
        }

        // registry jobhandler
        registJobHandler(name, new MethodJobHandler(bean, executeMethod, initMethod, destroyMethod));

    }


    // ---------------------- job thread repository ----------------------
    private static ConcurrentMap<Integer, TaskThread> jobThreadRepository = new ConcurrentHashMap<Integer, TaskThread>();
    public static TaskThread registJobThread(int taskId, IJobHandler handler, String removeOldReason){
        TaskThread newTaskThread = new TaskThread(taskId, handler);
        newTaskThread.start();
        logger.info(">>>>>>>>>>> xxl-job regist JobThread success, taskId:{}, handler:{}", new Object[]{taskId, handler});

        TaskThread oldTaskThread = jobThreadRepository.put(taskId, newTaskThread);	// putIfAbsent | oh my god, map's put method return the old value!!!
        if (oldTaskThread != null) {
            oldTaskThread.toStop(removeOldReason);
            oldTaskThread.interrupt();
        }

        return newTaskThread;
    }

    public static TaskThread removeJobThread(int taskId, String removeOldReason){
        TaskThread oldTaskThread = jobThreadRepository.remove(taskId);
        if (oldTaskThread != null) {
            oldTaskThread.toStop(removeOldReason);
            oldTaskThread.interrupt();

            return oldTaskThread;
        }
        return null;
    }

    public static TaskThread loadJobThread(int taskId){
        return jobThreadRepository.get(taskId);
    }
}
