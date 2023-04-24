package com.wingflare.lib.standard.bo;


import com.wingflare.lib.core.utils.ObjectUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * @author naizui_ycx
 * @date {2022/1/17}
 * @description 消息传递bean
 */
public class MessageBean implements Serializable {

    private static final long serialVersionUID = 6053882511563830102L;

    private Map<String, Object> body;

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBodyAsBean(Object object) {
        setBody(ObjectUtil.objectToMap(object));
    }

    public static MessageBean create(Object object) {
        MessageBean messageBean = new MessageBean();
        messageBean.setBodyAsBean(object);
        return messageBean;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public <T> T cast(Class<T> clt) {

        try {
            T instance = clt.newInstance();
            BeanUtils.populate(instance, body);
            return instance;
        } catch (Throwable e) {

        }

        return null;
    }

}
