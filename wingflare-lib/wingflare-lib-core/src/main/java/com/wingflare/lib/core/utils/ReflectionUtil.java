package com.wingflare.lib.core.utils;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


public class ReflectionUtil {

    // 方法缓存：key为MethodKey，value为Method对象
    private static final ConcurrentHashMap<MethodKey, Method> METHOD_CACHE = new ConcurrentHashMap<>();

    // 基本类型与包装类型的映射
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER;
    private static final Map<Class<?>, Class<?>> WRAPPER_TO_PRIMITIVE;

    static {
        PRIMITIVE_TO_WRAPPER = new HashMap<>(9);
        WRAPPER_TO_PRIMITIVE = new HashMap<>(9);

        PRIMITIVE_TO_WRAPPER.put(boolean.class, Boolean.class);
        PRIMITIVE_TO_WRAPPER.put(byte.class, Byte.class);
        PRIMITIVE_TO_WRAPPER.put(char.class, Character.class);
        PRIMITIVE_TO_WRAPPER.put(double.class, Double.class);
        PRIMITIVE_TO_WRAPPER.put(float.class, Float.class);
        PRIMITIVE_TO_WRAPPER.put(int.class, Integer.class);
        PRIMITIVE_TO_WRAPPER.put(long.class, Long.class);
        PRIMITIVE_TO_WRAPPER.put(short.class, Short.class);
        PRIMITIVE_TO_WRAPPER.put(void.class, Void.class);

        for (Map.Entry<Class<?>, Class<?>> entry : PRIMITIVE_TO_WRAPPER.entrySet()) {
            WRAPPER_TO_PRIMITIVE.put(entry.getValue(), entry.getKey());
        }
    }

    /**
     * 调用指定方法（无参数）
     * @param method 要调用的方法
     * @param target 目标对象（静态方法可为null）
     * @return 方法返回值
     * @throws RuntimeException 包装反射相关异常
     */
    public static Object invokeMethod(Method method, Object target) {
        return invokeMethod(method, target, (Object[]) null);
    }

    /**
     * 调用指定方法（带参数）
     * @param method 要调用的方法
     * @param target 目标对象（静态方法可为null）
     * @param args 方法参数（可为null）
     * @return 方法返回值
     * @throws RuntimeException 包装反射相关异常
     */
    public static Object invokeMethod(Method method, Object target, Object... args) {
        // 校验方法非空
        if (method == null) {
            throw new IllegalArgumentException("方法对象不能为null");
        }

        try {
            // 确保方法可访问（支持私有/保护方法）
            method.setAccessible(true);

            // 处理参数为null的情况（适配无参方法）
            Object[] actualArgs = (args == null) ? new Object[0] : args;

            // 调用方法（静态方法时target为null）
            return method.invoke(target, actualArgs);

        } catch (IllegalAccessException e) {
            throw new RuntimeException("无法访问方法: " + method.toGenericString(), e);
        } catch (InvocationTargetException e) {
            // 解包方法内部抛出的异常
            Throwable targetEx = e.getTargetException();
            if (targetEx instanceof RuntimeException) {
                throw (RuntimeException) targetEx;
            } else {
                throw new RuntimeException("方法执行抛出异常: " + targetEx.getMessage(), targetEx);
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("参数与方法不匹配: " + method.toGenericString(), e);
        }
    }

    /**
     * 查找方法（带缓存逻辑）
     */
    private static Method findMethod(Class<?> targetClass, String methodName, Class<?>[] argsTypes)
            throws NoSuchMethodException {
        // 尝试从缓存获取（精确匹配）
        MethodKey exactKey = new MethodKey(targetClass, methodName, argsTypes);
        Method cachedMethod = METHOD_CACHE.get(exactKey);
        if (cachedMethod != null) {
            return cachedMethod;
        }

        // 缓存未命中，执行方法查找
        Method method = doFindMethod(targetClass, methodName, argsTypes);

        // 设置方法可访问（支持私有/保护方法）
        method.setAccessible(true);

        // 存入缓存（使用方法实际参数类型作为key）
        MethodKey cacheKey = new MethodKey(targetClass, methodName, method.getParameterTypes());
        METHOD_CACHE.put(cacheKey, method);

        return method;
    }

    /**
     * 实际执行方法查找（支持精确匹配和兼容匹配）
     */
    private static Method doFindMethod(Class<?> targetClass, String methodName, Class<?>[] argsTypes)
            throws NoSuchMethodException {
        int paramCount = argsTypes != null ? argsTypes.length : 0;

        // 1. 尝试精确匹配
        try {
            return targetClass.getMethod(methodName, argsTypes);
        } catch (NoSuchMethodException e) {
            // 精确匹配失败，继续兼容匹配
        }

        // 2. 兼容匹配（遍历类及父类的所有方法）
        List<Method> candidates = new ArrayList<>();
        Class<?> currentClass = targetClass;

        while (currentClass != null) {
            // 获取所有声明的方法（包括私有）
            Method[] methods = currentClass.getDeclaredMethods();
            for (Method method : methods) {
                // 筛选方法名相同且参数数量一致的方法
                if (method.getName().equals(methodName) && method.getParameterCount() == paramCount) {
                    if (isMethodCompatible(method, argsTypes)) {
                        candidates.add(method);
                    }
                }
            }
            currentClass = currentClass.getSuperclass();
        }

        // 处理候选方法
        if (candidates.isEmpty()) {
            throw new NoSuchMethodException("找不到兼容的方法: " + methodName);
        }
        return chooseMostSpecificMethod(candidates);
    }

    /**
     * 检查方法参数是否与实际参数类型兼容
     */
    private static boolean isMethodCompatible(Method method, Class<?>[] argsTypes) {
        Class<?>[] paramTypes = method.getParameterTypes();
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> paramType = paramTypes[i];
            Class<?> argType = argsTypes != null ? argsTypes[i] : null;

            if (!isCompatible(paramType, argType)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查参数类型是否兼容（支持基本类型与包装类型）
     */
    private static boolean isCompatible(Class<?> paramType, Class<?> argType) {
        if (argType == null) {
            // 实际参数为null时，参数类型不能是基本类型
            return !paramType.isPrimitive();
        }

        // 基本类型与包装类型兼容处理
        if (paramType.isPrimitive()) {
            Class<?> wrapperType = PRIMITIVE_TO_WRAPPER.get(paramType);
            return wrapperType.equals(argType);
        }
        if (argType.isPrimitive()) {
            Class<?> wrapperType = PRIMITIVE_TO_WRAPPER.get(argType);
            return paramType.equals(wrapperType);
        }

        // 普通类型：检查是否可赋值（子类->父类）
        return paramType.isAssignableFrom(argType);
    }

    /**
     * 从候选方法中选择最具体的方法（处理重载）
     */
    private static Method chooseMostSpecificMethod(List<Method> candidates) {
        if (candidates.size() == 1) {
            return candidates.get(0);
        }

        Method mostSpecific = null;
        for (Method candidate : candidates) {
            if (mostSpecific == null) {
                mostSpecific = candidate;
            } else {
                if (isMoreSpecific(candidate, mostSpecific)) {
                    mostSpecific = candidate;
                } else if (!isMoreSpecific(mostSpecific, candidate)) {
                    // 存在歧义的方法重载
                    throw new IllegalArgumentException("方法重载歧义: " + candidate.getName());
                }
            }
        }
        return mostSpecific;
    }

    /**
     * 检查方法1是否比方法2更具体
     */
    private static boolean isMoreSpecific(Method m1, Method m2) {
        Class<?>[] params1 = m1.getParameterTypes();
        Class<?>[] params2 = m2.getParameterTypes();

        for (int i = 0; i < params1.length; i++) {
            if (!isMoreSpecific(params1[i], params2[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查类型1是否比类型2更具体
     */
    private static boolean isMoreSpecific(Class<?> c1, Class<?> c2) {
        // c1是c2的子类则更具体
        return c2.isAssignableFrom(c1) && !c1.equals(c2);
    }

    /**
     * 获取参数类型数组
     */
    private static Class<?>[] getArgsTypes(Object[] args) {
        if (args == null || args.length == 0) {
            return null;
        }

        Class<?>[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            types[i] = arg != null ? arg.getClass() : null;
        }
        return types;
    }

    /**
     * 方法缓存的键对象
     */
    private static class MethodKey {
        private final Class<?> targetClass;
        private final String methodName;
        private final Class<?>[] parameterTypes;

        public MethodKey(Class<?> targetClass, String methodName, Class<?>[] parameterTypes) {
            this.targetClass = targetClass;
            this.methodName = methodName;
            this.parameterTypes = parameterTypes;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MethodKey methodKey = (MethodKey) o;
            return Objects.equals(targetClass, methodKey.targetClass) &&
                    Objects.equals(methodName, methodKey.methodName) &&
                    Arrays.equals(parameterTypes, methodKey.parameterTypes);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(targetClass, methodName);
            result = 31 * result + Arrays.hashCode(parameterTypes);
            return result;
        }
    }
}