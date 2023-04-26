package com.wingflare.lib.security.aspect;


import cn.hutool.core.bean.BeanUtil;
import com.wingflare.lib.core.exceptions.ServerInternalException;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.security.properties.ApiProperties;
import com.wingflare.lib.security.standard.DataSecret;
import com.wingflare.lib.standard.annotation.security.Decrypt;
import com.wingflare.lib.standard.annotation.security.Encryption;
import com.wingflare.lib.standard.annotation.security.Secret;
import com.wingflare.lib.standard.enums.SecretType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.Resource;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保密数据处理切面
 *
 * @author naizui_ycx
 * @email chenxi@qq.com
 */
@Aspect
public class DataSecretAspect implements ApplicationContextAware {

    @Resource
    private ApiProperties apiProperties;

    private List<Class<? extends Annotation>> apiAnnotationClassList = null;

    private List<Class<?>> apiBaseClassList = null;

    private final Map<String, Boolean> apiTargetCache = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(DataSecretAspect.class);

    private final Map<String, Map<Integer, Info>> methodIndexCache = new HashMap<>();

    private final Map<String, Map<String, String>> decryptFieldNameCache = new HashMap<>();

    private final Map<String, Map<String, String>> encryptionFieldNameCache = new HashMap<>();

    private final Map<String, DataSecret> dataSecretMap = new HashMap<>();

    private final Map<String, String> resEncryptionCache = new HashMap<>();

    private final Map<String, String> resDecryptCache = new HashMap<>();


    @Around("@annotation(com.wingflare.lib.standard.annotation.security.Secret)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result;

        if (!point.getKind().equals(ProceedingJoinPoint.METHOD_EXECUTION)
                || !(point.getSignature() instanceof MethodSignature)) {
            return point.proceed(point.getArgs());
        }

        Secret secret = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(Secret.class);
        boolean isCall = secret.type().equals(SecretType.CALL) || secret.type().equals(SecretType.ALL);
        boolean isReturn = secret.type().equals(SecretType.RETURN) || secret.type().equals(SecretType.ALL);
        Object[] args = new Object[point.getArgs().length];

        if (isCall) {
            Map<Integer, Info> indexMap = getMethodIndexArr(((MethodSignature) point.getSignature()));

            if (CollectionUtil.isNotEmpty(indexMap)) {
                // 当前方法调用时是否执行加密
                boolean isDecrypt = isCallDecrypt(point);
                for (int i = 0; i < point.getArgs().length; i++) {
                    Object o = point.getArgs()[i];
                    if (indexMap.containsKey(i)) {
                        Info info = indexMap.get(i);
                        if (isDecrypt) {
                            if (info != null) {
                                String type = info.getDecryptType();
                                if (getDataSecretMap().get(type) != null) {
                                    if (o instanceof String) {
                                        args[i] = getDataSecretMap().get(type)
                                                .decrypt((String) o);
                                    } else if (BeanUtil.isBean(o.getClass())) {
                                        beanPropertyDecrypt(o);
                                        args[i] = o;
                                    } else {
                                        throw new ServerInternalException("sec.dataSecret.unsupportedParamDataType",
                                                o.getClass().getName());
                                    }
                                } else {
                                    throw new ServerInternalException("sec.dataSecret.driveNotFound", type);
                                }
                            } else {
                                beanPropertyDecrypt(o);
                                args[i] = o;
                            }
                        } else {
                            if (info != null) {
                                String type = info.getEncryptionType();
                                if (getDataSecretMap().get(type) != null) {
                                    if (o instanceof String) {
                                        args[i] = getDataSecretMap().get(type)
                                                .encryption((String) o);
                                    } else if (BeanUtil.isBean(o.getClass())) {
                                        beanPropertyEncryption(o);
                                        args[i] = o;
                                    } else {
                                        throw new ServerInternalException("sec.dataSecret.unsupportedParamDataType",
                                                o.getClass().getName());
                                    }
                                } else {
                                    throw new ServerInternalException("sec.dataSecret.driveNotFound", type);
                                }
                            } else {
                                beanPropertyEncryption(o);
                                args[i] = o;
                            }
                        }
                    }
                }
            } else {
                args = point.getArgs();
            }
        } else {
            args = point.getArgs();
        }

        result = point.proceed(args);

        if (isReturn && result != null) {
            boolean isDecrypt = isReturnDecrypt(point);

            if (isDecrypt) {
                if (!(result instanceof String)) {
                    beanPropertyDecrypt(result);
                } else if (resEncryptionCache.containsKey(point.getSignature().getName())) {
                    String type = resEncryptionCache.get(point.getSignature().getName());

                    if (!getDataSecretMap().containsKey(type)) {
                        throw new ServerInternalException("sec.dataSecret.driveNotFound", type);
                    }

                    result = getDataSecretMap().get(type)
                            .decrypt((String) result);
                }
            } else {
                if (!(result instanceof String)) {
                    beanPropertyEncryption(result);
                } else if (resDecryptCache.containsKey(point.getSignature().getName())) {
                    String type = resDecryptCache.get(point.getSignature().getName());

                    if (!getDataSecretMap().containsKey(type)) {
                        throw new ServerInternalException("sec.dataSecret.driveNotFound", type);
                    }

                    result = getDataSecretMap().get(type)
                            .encryption((String) result);
                }
            }
        }

        return result;
    }

    /**
     * 对象字段解密
     *
     * @param o
     * @throws NoSuchFieldException
     */
    private void beanPropertyDecrypt(Object o) throws NoSuchFieldException {
        Map<String, String> fieldsName = getBeanDecryptFieldsName(o.getClass());
        for (Map.Entry<String, String> fieldName : fieldsName.entrySet()) {
            if (getDataSecretMap().containsKey(fieldName.getValue())) {
                String value = (String) BeanUtil.getFieldValue(o, fieldName.getKey());
                if (StringUtil.isNotEmpty(value)) {
                    BeanUtil.setFieldValue(o, fieldName.getKey(), getDataSecretMap()
                            .get(fieldName.getValue()).decrypt(value));
                }
            }
        }
    }

    /**
     * 对象字段加密
     *
     * @param o
     * @throws NoSuchFieldException
     */
    private void beanPropertyEncryption(Object o) throws NoSuchFieldException {
        Map<String, String> fieldsName = getBeanEncryptionFieldsName(o.getClass());
        for (Map.Entry<String, String> fieldName : fieldsName.entrySet()) {
            if (getDataSecretMap().containsKey(fieldName.getValue())) {
                String value = (String) BeanUtil.getFieldValue(o, fieldName.getKey());
                if (StringUtil.isNotEmpty(value)) {
                    BeanUtil.setFieldValue(o, fieldName.getKey(), getDataSecretMap()
                            .get(fieldName.getValue()).encryption(value));
                }
            }
        }
    }

    private List<Class<? extends Annotation>> getApiAnnotationClass() {
        if (apiAnnotationClassList == null) {
            synchronized (this) {
                if (apiAnnotationClassList == null) {
                    List<String> annNames = apiProperties.getAnnotationClasses();
                    apiAnnotationClassList = new ArrayList<>();
                    if (!CollectionUtil.isEmpty(annNames)) {
                        annNames.forEach(className -> {
                            try {
                                apiAnnotationClassList.add((Class<? extends Annotation>) Class.forName(className));
                            } catch (Throwable e) {
                                logger.warn("class [{}] notfound or not api annotation.", className);
                            }
                        });
                    }
                }
            }
        }

        return apiAnnotationClassList;
    }

    private List<Class<?>> getApiBaseClass() {
        if (apiBaseClassList == null) {
            synchronized (this) {
                if (apiBaseClassList == null) {
                    List<String> classNames = apiProperties.getBaseClasses();
                    apiBaseClassList = new ArrayList<>();
                    if (!CollectionUtil.isEmpty(classNames)) {
                        classNames.forEach(className -> {
                            try {
                                apiBaseClassList.add(Class.forName(className));
                            } catch (Throwable e) {
                                logger.warn("api base class [{}] notfound.", className);
                            }
                        });
                    }
                }
            }
        }

        return apiBaseClassList;
    }

    private boolean isCallDecrypt(ProceedingJoinPoint point) {
        return !isApiClient(point);
    }

    private boolean isReturnDecrypt(ProceedingJoinPoint point) {
        return isApiClient(point);
    }

    /**
     * 判断是否为api客户端调用
     *
     * @param point
     * @return
     */
    private boolean isApiClient(ProceedingJoinPoint point) {
        String name = point.getSignature().getDeclaringTypeName();

        if (apiTargetCache.containsKey(name)) {
            return apiTargetCache.get(name);
        }

        synchronized (apiTargetCache) {
            if (!apiTargetCache.containsKey(name)) {
                List<Class<? extends Annotation>> annClasses = getApiAnnotationClass();
                List<Class<?>> classes = getApiBaseClass();
                Annotation annotation = null;
                boolean ret = false;

                for (Class<?> clz : classes) {
                    if (point.getTarget().getClass().isAssignableFrom(clz)) {
                        ret = true;
                        break;
                    }
                }

                if (!ret) {
                    for (Class<? extends Annotation> annClass : annClasses) {
                        annotation = point.getSignature().getDeclaringType().getAnnotation(annClass);
                        if (annotation != null) {
                            ret = true;
                            break;
                        }
                    }
                }

                apiTargetCache.put(name, ret);
            }

            return apiTargetCache.get(name);
        }
    }

    private void intBeanSecretFieldsName(Class clz) throws NoSuchFieldException {
        if (decryptFieldNameCache.containsKey(clz.getName())) {
            return;
        }

        synchronized (decryptFieldNameCache) {
            if (!decryptFieldNameCache.containsKey(clz.getName())) {
                Map<String, String> decryptMap = new HashMap<>();
                Map<String, String> encryptionMap = new HashMap<>();

                PropertyDescriptor[] descriptors = BeanUtil.getPropertyDescriptors(clz);

                for (PropertyDescriptor desc : descriptors) {
                    Field field = clz.getDeclaredField(desc.getName());
                    boolean isDecrypt = field.isAnnotationPresent(Decrypt.class);
                    boolean isEncryption = field.isAnnotationPresent(Encryption.class);
                    if (isDecrypt || isEncryption) {
                        if (field.getGenericType().equals(String.class)) {
                            if (isDecrypt) {
                                Decrypt decrypt = field.getAnnotation(Decrypt.class);
                                decryptMap.put(desc.getName(), decrypt.type());
                            }

                            if (isEncryption) {
                                Encryption encryption = field.getAnnotation(Encryption.class);
                                encryptionMap.put(desc.getName(), encryption.type());
                            }
                        } else {
                            logger.error("不支持的解密类型数据: {}.{}", clz.getName(), desc.getName());
                        }
                    }
                }

                decryptFieldNameCache.put(clz.getName(), decryptMap);
                encryptionFieldNameCache.put(clz.getName(), encryptionMap);
            }
        }
    }

    private Map<String, String> getBeanDecryptFieldsName(Class clz) throws NoSuchFieldException {
        if (!decryptFieldNameCache.containsKey(clz.getName())) {
            intBeanSecretFieldsName(clz);
        }

        return decryptFieldNameCache.get(clz.getName());
    }

    private Map<String, String> getBeanEncryptionFieldsName(Class clz) throws NoSuchFieldException {
        if (!encryptionFieldNameCache.containsKey(clz.getName())) {
            intBeanSecretFieldsName(clz);
        }

        return encryptionFieldNameCache.get(clz.getName());
    }

    private Map<Integer, Info> getMethodIndexArr(MethodSignature methodSignature) {
        if (methodIndexCache.containsKey(methodSignature.getName())) {
            return methodIndexCache.get(methodSignature.getName());
        }

        Map<Integer, Info> map = new HashMap<>();

        synchronized (methodIndexCache) {
            if (!methodIndexCache.containsKey(methodSignature.getName())) {
                Annotation[][] annotations = methodSignature.getMethod().getParameterAnnotations();
                Encryption encryption = methodSignature.getMethod().getAnnotation(Encryption.class);
                Decrypt decrypt = methodSignature.getMethod().getAnnotation(Decrypt.class);
                boolean returnIsString = methodSignature.getReturnType().isAssignableFrom(String.class);

                if (encryption != null) {
                    if (returnIsString) {
                        resEncryptionCache.put(methodSignature.getName(), encryption.type());
                    } else {
                        throw new ServerInternalException("sec.dataSecret.unsupportedReturnDataType",
                                methodSignature.getReturnType().getName());
                    }
                }

                if (decrypt != null) {
                    if (returnIsString) {
                        resDecryptCache.put(methodSignature.getName(), decrypt.type());
                    } else {
                        throw new ServerInternalException("sec.dataSecret.unsupportedReturnDataType",
                                methodSignature.getReturnType().getName());
                    }
                }

                for (int i = 0; i < annotations.length; i++) {
                    for (int j = 0; j < annotations[i].length; j++) {
                        if (annotations[i][j] instanceof Secret) {
                            map.put(i, null);
                        } else {
                            Info info = new Info();
                            boolean infoSet = false;

                            if (annotations[i][j] instanceof Decrypt) {
                                infoSet = true;
                                info.setDecryptType(((Decrypt) annotations[i][j]).type());
                            }

                            if (annotations[i][j] instanceof Encryption) {
                                if (!infoSet) {
                                    infoSet = true;
                                }
                                info.setEncryptionType(((Encryption) annotations[i][j]).type());
                            }

                            if (infoSet) {
                                map.put(i, info);
                            }
                        }
                    }
                }

                methodIndexCache.put(methodSignature.getName(), map);
            } else {
                map = methodIndexCache.get(methodSignature.getName());
            }
        }

        return map;
    }

    public Map<String, DataSecret> getDataSecretMap() {
        if (CollectionUtil.isEmpty(dataSecretMap)) {
            throw new ServerInternalException("sec.dataSecret.driveEmpty");
        }

        return dataSecretMap;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Collection<DataSecret> dataSecrets = applicationContext.getBeansOfType(DataSecret.class)
                .values();
        dataSecrets.forEach(item -> dataSecretMap.put(item.type(), item));
    }

    class Info {

        private String decryptType;

        private String encryptionType;

        public String getDecryptType() {
            return decryptType;
        }

        public void setDecryptType(String decryptType) {
            this.decryptType = decryptType;
        }

        public String getEncryptionType() {
            return encryptionType;
        }

        public void setEncryptionType(String encryptionType) {
            this.encryptionType = encryptionType;
        }
    }

}
