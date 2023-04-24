package com.wingflare.lib.standard.utils;

import com.wingflare.lib.standard.Ordered;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 排序工具
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
public class OrderUtil {


    public static <T> Collection<T> asc(Collection<T> collection) {
        List<T> list;

        if (!(collection instanceof List)) {
            list = new ArrayList<>(collection);
        } else {
            list = (List<T>) collection;
        }

        list.sort((o1, o2) -> {
            int o1o = o1 instanceof Ordered ? ((Ordered) o1).getOrder() : Ordered.DEFAULT_ORDER;
            int o2o = o2 instanceof Ordered ? ((Ordered) o2).getOrder() : Ordered.DEFAULT_ORDER;
            return o2o - o1o;
        });

        return list;
    }


    public static <T> Collection<T> desc(Collection<T> collection) {
        List<T> list;

        if (!(collection instanceof List)) {
            list = new ArrayList<>(collection);
        } else {
            list = (List<T>) collection;
        }

        list.sort((o1, o2) -> {
            int o1o = o1 instanceof Ordered ? ((Ordered) o1).getOrder() : Ordered.DEFAULT_ORDER;
            int o2o = o2 instanceof Ordered ? ((Ordered) o2).getOrder() : Ordered.DEFAULT_ORDER;
            return o1o - o2o;
        });

        return list;
    }

}
