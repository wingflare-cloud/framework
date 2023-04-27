package com.wingflare.adapter.spring.server.web.aspect;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 输入输出日志切面
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
@Aspect
@RefreshScope
public class AccessLogAspect {

    @Resource
    private ObjectMapper objectMapper;

    @Value("${spring.application.name:}")
    private String applicationName;

    @Value("${wf.accessLog.enable:false}")
    private Boolean enableAccessLog;


    private Logger logger = LoggerFactory.getLogger(AccessLogAspect.class);
    /**
     * 保证每个线程都有一个单独的实例
     */
    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();


    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        threadLocal.set(System.currentTimeMillis());

        if (enableAccessLog) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();

            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                StringBuilder stringBuilder = new StringBuilder();

                // 记录请求的内容
                logHandle(joinPoint, request, stringBuilder);
                logger.info(stringBuilder.toString());
            }
        }

    }

    /**
     * 返回值信息
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "pointcut()")
    public void doAfterReturning(Object ret) throws Throwable {
        if (ObjectUtil.isNotEmpty(ret)) {
            if (BeanUtil.isBean(ret.getClass())) {
                logger.info("response: {}", objectMapper.writeValueAsString(ret));
            } else {
                logger.info("response: {}", ret);
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("time-cost: {}{}", System.currentTimeMillis() - threadLocal.get(), "ms");
        }
    }

    /**
     * 处理请求参数输出
     *
     * @param joinPoint
     * @param request
     * @param stringBuilder
     * @throws Exception
     */
    public void logHandle(JoinPoint joinPoint, HttpServletRequest request, StringBuilder stringBuilder) {
        Map<String, Object> paramMap = new HashMap<>(16);
        paramMap.put("spring-application-name", applicationName);
        paramMap.put("class-method", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        Enumeration<String> headers = request.getHeaderNames();

        while (headers.hasMoreElements()) {
            String name = headers.nextElement();
            paramMap.put("request-header-" + name, request.getHeader(name));
        }

        Object[] oArr = joinPoint.getArgs();

        if (oArr != null && oArr.length > 0) {
            String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
            int i = 0;
            for (Object o : oArr) {
                String key = "request-param-" + paramNames[i];
                if (o instanceof MultipartFile) {
                    MultipartFile file = (MultipartFile) o;
                    paramMap.put(key, "FILE_NAME{" + file.getOriginalFilename() + "}");
                } else {
                    paramMap.put(key, o);
                }
                i++;
            }
        }

        paramMap.forEach((key, value) -> stringBuilder.append(key).append(":").append(value).append("\n"));
    }

}
