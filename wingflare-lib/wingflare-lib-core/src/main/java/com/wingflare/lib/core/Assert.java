package com.wingflare.lib.core;


import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.utils.StringUtil;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * assert，来源spring框架assert
 *
 * @author naizui_ycx
 * @date Fri Mar 03 09:48:21 CST 2023
 */
public abstract class Assert {
    private Assert() {
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            execute(message);
        }
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            execute(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            execute(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            execute(message);
        }
    }

    public static void hasLength(String text, String message) {
        if (!StringUtil.hasLength(text)) {
            execute(message);
        }
    }

    public static void hasText(String text, String message) {
        if (!StringUtil.hasText(text)) {
            execute(message);
        }
    }

    public static void doesNotContain(String textToSearch, String substring, String message) {
        if (StringUtil.hasLength(textToSearch) && StringUtil.hasLength(substring) && textToSearch.contains(substring)) {
            execute(message);
        }
    }


    public static void notEmpty(Object[] array, String message) {
            if (ObjectUtils.isEmpty(array)) {
            execute(message);
        }
    }

    public static void noNullElements(Object[] array, String message) {
        if (array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if (element == null) {
                    execute(message);
                }
            }
        }

    }


    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtil.isEmpty(collection)) {
            execute(message);
        }
    }


    public static void noNullElements(Collection<?> collection, String message) {
        if (collection != null) {
            Iterator var2 = collection.iterator();

            while(var2.hasNext()) {
                Object element = var2.next();
                if (element == null) {
                    execute(message);
                }
            }
        }

    }

    public static void notEmpty(Map<?, ?> map, String message) {
        if (CollectionUtil.isEmpty(map)) {
            execute(message);
        }
    }

    public static void isTrue(boolean expression, String message, Object data) {
        if (!expression) {
            execute(message, data);
        }
    }

    public static void isFalse(boolean expression, String message, Object data) {
        if (expression) {
            execute(message, data);
        }
    }

    public static void isNull(Object object, String message, Object data) {
        if (object != null) {
            execute(message, data);
        }
    }

    public static void notNull(Object object, String message, Object data) {
        if (object == null) {
            execute(message, data);
        }
    }

    public static void hasLength(String text, String message, Object data) {
        if (!StringUtil.hasLength(text)) {
            execute(message, data);
        }
    }

    public static void hasText(String text, String message, Object data) {
        if (!StringUtil.hasText(text)) {
            execute(message, data);
        }
    }

    public static void doesNotContain(String textToSearch, String substring, String message, Object data) {
        if (StringUtil.hasLength(textToSearch) && StringUtil.hasLength(substring) && textToSearch.contains(substring)) {
            execute(message, data);
        }
    }


    public static void notEmpty(Object[] array, String message, Object data) {
        if (ObjectUtils.isEmpty(array)) {
            execute(message, data);
        }
    }

    public static void noNullElements(Object[] array, String message, Object data) {
        if (array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if (element == null) {
                    execute(message, data);
                }
            }
        }

    }


    public static void notEmpty(Collection<?> collection, String message, Object data) {
        if (CollectionUtil.isEmpty(collection)) {
            execute(message, data);
        }
    }


    public static void noNullElements(Collection<?> collection, String message, Object data) {
        if (collection != null) {
            Iterator var2 = collection.iterator();

            while(var2.hasNext()) {
                Object element = var2.next();
                if (element == null) {
                    execute(message, data);
                }
            }
        }

    }

    public static void notEmpty(Map<?, ?> map, String message, Object data) {
        if (CollectionUtil.isEmpty(map)) {
            execute(message, data);
        }
    }

    public static void isTrue(boolean expression, Throw t) {
        if (!expression) {
            execute(t);
        }
    }

    public static void isFalse(boolean expression, Throw t) {
        if (expression) {
            execute(t);
        }
    }

    public static void isNull(Object object, Throw t) {
        if (object != null) {
            execute(t);
        }
    }

    public static void notNull(Object object, Throw t) {
        if (object == null) {
            execute(t);
        }
    }

    public static void hasLength(String text, Throw t) {
        if (!StringUtil.hasLength(text)) {
            execute(t);
        }
    }

    public static void hasText(String text, Throw t) {
        if (!StringUtil.hasText(text)) {
            execute(t);
        }
    }

    public static void doesNotContain(String textToSearch, String substring, Throw t) {
        if (StringUtil.hasLength(textToSearch) && StringUtil.hasLength(substring) && textToSearch.contains(substring)) {
            execute(t);
        }
    }


    public static void notEmpty(Object[] array, Throw t) {
        if (ObjectUtils.isEmpty(array)) {
            execute(t);
        }
    }

    public static void noNullElements(Object[] array, Throw t) {
        if (array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if (element == null) {
                    execute(t);
                }
            }
        }

    }


    public static void notEmpty(Collection<?> collection, Throw t) {
        if (CollectionUtil.isEmpty(collection)) {
            execute(t);
        }
    }


    public static void noNullElements(Collection<?> collection, Throw t) {
        if (collection != null) {
            Iterator var2 = collection.iterator();

            while(var2.hasNext()) {
                Object element = var2.next();
                if (element == null) {
                    execute(t);
                }
            }
        }

    }

    public static void notEmpty(Map<?, ?> map, Throw t) {
        if (CollectionUtil.isEmpty(map)) {
            execute(t);
        }
    }

    private static void execute(String msg) {
        throw new BusinessLogicException(msg);
    }

    private static void execute(Throw t) {
        throw t.throwEx();
    }

    private static void execute(String msg, Object data) {
        throw new BusinessLogicException(msg, data);
    }

}

