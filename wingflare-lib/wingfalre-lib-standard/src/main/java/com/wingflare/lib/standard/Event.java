package com.wingflare.lib.standard;


import com.wingflare.lib.core.utils.StringUtil;

/**
 * @author naizui_ycx
 * @date {2022/1/4}
 * @email chenxi2511@qq.com
 * @description 框架业务事件接口
 */
public interface Event {

    /**
     * 获取事件名
     *
     * @return
     */
    String getEventName();

    /**
     * 获取事件数据
     *
     * @return
     */
    Object getData();

    /**
     * 获取事件状态
     *
     * @return
     */
    String getEventStatus();

    /**
     * 获取事件版本
     *
     * @return
     */
    String getEventVersion();

    /**
     * 获取事件源
     *
     * @return
     */
    Object getSource();

    /**
     * 获取事件发生时间
     *
     * @return
     */
    long getEventTimestamp();

    class Default implements Event {

        /**
         * 事件名称
         */
        protected String eventName;

        /**
         * 事件数据
         */
        protected Object data;

        /**
         * 事件状态
         */
        protected String eventStatus;

        /**
         * 事件版本
         */
        protected String eventVersion;

        /**
         * 事件源
         */
        protected Object source;

        /**
         * 事件推送时间
         */
        protected long eventTimestamp;


        public Default(String eventName) {
            this(eventName, null, null);
        }

        public Default(String eventName, Object source) {
            this(eventName, source, null);
        }

        public Default(String eventName, Object source, Object evData) {
            this(eventName, source, evData, null, null);
        }

        public Default(String eventName, Object source, Object evData, String eventStatus) {
            this(eventName, source, evData, eventStatus, null);
        }

        /**
         *
         * @param eventName 事件名
         * @param source 事件原始对象
         * @param evData 事件数据
         * @param eventStatus 事件状态
         * @param eventVersion 事件版本
         */
        public Default(String eventName, Object source, Object evData, String eventStatus, String eventVersion) {
            if (StringUtil.isBlank(eventName)) {
                throw new IllegalArgumentException("Event name cannot be empty");
            }

            setEventName(eventName);
            setData(evData);
            setEventStatus(eventStatus);
            setEventVersion(eventVersion);
            setEventTimestamp(System.currentTimeMillis());
        }

        @Override
        public String getEventName() {
            return eventName;
        }

        private void setEventName(String eventName) {
            this.eventName = eventName;
        }

        @Override
        public Object getData() {
            return data;
        }

        private void setData(Object data) {
            this.data = data;
        }

        @Override
        public String getEventStatus() {
            return eventStatus;
        }

        private void setEventStatus(String eventStatus) {
            this.eventStatus = eventStatus;
        }

        @Override
        public String getEventVersion() {
            return eventVersion;
        }

        private void setEventVersion(String eventVersion) {
            this.eventVersion = eventVersion;
        }

        @Override
        public Object getSource() {
            return source;
        }

        private void setSource(Object source) {
            this.source = source;
        }

        @Override
        public long getEventTimestamp() {
            return eventTimestamp;
        }

        private void setEventTimestamp(long eventTimestamp) {
            this.eventTimestamp = eventTimestamp;
        }
    }

}
