package com.wingflare.engine.task.common.model.request;


import java.util.List;

/**
 * 同步的配置数据结构
 *
 * @author: opensnail
 * @date : 2021-11-22 13:45
 */
public class ConfigRequest {

    /**
     * 场景
     */
    private List<Scene> sceneList;

    /**
     * 通知
     */
    private List<Notify> notifyList;

    /**
     * 版本号
     */
    private Integer version;

    public List<Scene> getSceneList() {
        return sceneList;
    }

    public void setSceneList(List<Scene> sceneList) {
        this.sceneList = sceneList;
    }

    public List<Notify> getNotifyList() {
        return notifyList;
    }

    public void setNotifyList(List<Notify> notifyList) {
        this.notifyList = notifyList;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public static class Scene {

        /**
         * 场景名称
         */
        private String sceneName;

        /**
         * 调用链超时时间 单位毫秒(ms)
         */
        private long ddl = 60000L;

        public String getSceneName() {
            return sceneName;
        }

        public void setSceneName(String sceneName) {
            this.sceneName = sceneName;
        }

        public long getDdl() {
            return ddl;
        }

        public void setDdl(long ddl) {
            this.ddl = ddl;
        }
    }


    public static class Notify {

        /**
         * 通知人
         */
        private List<Recipient> recipients;

        /**
         * 触发阈值
         */
        private Integer notifyThreshold;

        /**
         * 重试通知场景 {@link com.wingflare.engine.task.common.core.enums.RetryNotifySceneEnum}
         */
        private Integer retryNotifyScene;

        /**
         * 定时任务&工作流通知场景 {@link com.wingflare.engine.task.common.core.enums.JobNotifySceneEnum}
         */
        private Integer jobNotifyScene;

        public List<Recipient> getRecipients() {
            return recipients;
        }

        public void setRecipients(List<Recipient> recipients) {
            this.recipients = recipients;
        }

        public Integer getNotifyThreshold() {
            return notifyThreshold;
        }

        public void setNotifyThreshold(Integer notifyThreshold) {
            this.notifyThreshold = notifyThreshold;
        }

        public Integer getRetryNotifyScene() {
            return retryNotifyScene;
        }

        public void setRetryNotifyScene(Integer retryNotifyScene) {
            this.retryNotifyScene = retryNotifyScene;
        }

        public Integer getJobNotifyScene() {
            return jobNotifyScene;
        }

        public void setJobNotifyScene(Integer jobNotifyScene) {
            this.jobNotifyScene = jobNotifyScene;
        }

        public static class Recipient {

            /**
             * 通知类型 {@link com.wingflare.engine.task.common.core.enums.AlarmTypeEnum}
             */
            private Integer notifyType;

            /**
             * 通知地址
             */
            private String notifyAttribute;

            public Integer getNotifyType() {
                return notifyType;
            }

            public void setNotifyType(Integer notifyType) {
                this.notifyType = notifyType;
            }

            public String getNotifyAttribute() {
                return notifyAttribute;
            }

            public void setNotifyAttribute(String notifyAttribute) {
                this.notifyAttribute = notifyAttribute;
            }
        }
    }


}
