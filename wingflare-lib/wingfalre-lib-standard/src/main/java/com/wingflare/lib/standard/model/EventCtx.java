package com.wingflare.lib.standard.model;

import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.standard.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事件上下文
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
public class EventCtx {

    private final Map<String, Event> eventMap = new HashMap<>();

    private final List<String> eventNameList = new ArrayList<>();

    private EventCtx() {}

    public void setEvent(Event event) {
        eventNameList.add(event.getEventName());
        eventMap.put(event.getEventName(), event);
    }

    public Event get(String eventName) {
        return eventMap.get(eventName);
    }

    public Event next(String eventName) {
        int index  = eventNameList.indexOf(eventName);
        int next = index + 1;

        if (index > -1 && eventNameList.size() > next) {
            return eventMap.get(eventNameList.get(next));
        }

        return null;
    }

    public Event prev(String eventName) {
        int index  = eventNameList.indexOf(eventName);

        if (index > -1 && index != 0) {
            return eventMap.get(eventNameList.get(index - 1));
        }

        return null;
    }

    public boolean has(String eventName) {
        return eventMap.containsKey(eventName);
    }

    public Event last() {
        if (eventNameList.size() <= 0) {
            return null;
        }

        String eventName = eventNameList.get(eventNameList.size() - 1);
        return eventMap.get(eventName);
    }

    public static EventCtx getInstance() {
        EventCtx eventCtx = ContextHolder.get(Ctx.EVENT_CTX_DATA_CONTEXT, EventCtx.class);

        if (eventCtx == null) {
            eventCtx = new EventCtx();
            ContextHolder.set(Ctx.EVENT_CTX_DATA_CONTEXT, eventCtx);
        }

        return eventCtx;
    }

}
