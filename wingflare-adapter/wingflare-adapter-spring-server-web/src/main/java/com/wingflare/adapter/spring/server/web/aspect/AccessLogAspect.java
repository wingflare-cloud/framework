package com.wingflare.adapter.spring.server.web.aspect;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wingflare.adapter.spring.server.web.properties.AccessLogProperties;
import com.wingflare.lib.spring.utils.PointUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.e;

/**
 * 输入输出日志切面
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
@Aspect
public class AccessLogAspect {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private AccessLogProperties accessLogProperties;

    @Resource
    private PointUtil pointUtil;


    private Logger logger = LoggerFactory.getLogger(AccessLogAspect.class);


    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        if (accessLogProperties.isEnable() && !pointUtil.isApiClient(joinPoint)) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();

            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                logger.info("request info", e(logHandle(joinPoint, request)));
            }
        }
    }

    /**
     * 返回值信息
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "pointcut()", argNames = "joinPoint,ret")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) {
        if (accessLogProperties.isEnable() && !pointUtil.isApiClient(joinPoint)) {
            if (ObjectUtil.isNotEmpty(ret)) {
                try {
                    logger.info("response", objectMapper.writeValueAsString(ret));
                } catch (JsonProcessingException e) {
                    logger.error("参数序列化异常", e.getMessage());
                }
            }
        }
    }

    /**
     * 处理请求参数输出
     *
     * @param joinPoint
     * @param request
     *
     * @throws Exception
     */
    public Map<String, Object> logHandle(JoinPoint joinPoint, HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<>(16);
        paramMap.put("requestLogger", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        Enumeration<String> headers = request.getHeaderNames();
        paramMap.put("headers", headers);

        Object[] oArr = joinPoint.getArgs();

        if (oArr != null && oArr.length > 0) {
            paramMap.put("params", new HashMap<String, Object>(oArr.length));
            String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
            int i = 0;
            for (Object o : oArr) {
                if (o != null) {
                    if (o instanceof MultipartFile file) {
                        paramMap.put(paramNames[i], "FILE_NAME{" + file.getOriginalFilename() + "}");
                    } else {
                        if (BeanUtil.isBean(o.getClass())) {
                            try {
                                paramMap.put(paramNames[i], objectMapper.writeValueAsString(o));
                            } catch (JsonProcessingException e) {
                                logger.error("参数序列化异常", e.getMessage());
                            }
                        } else {
                            paramMap.put(paramNames[i], o);
                        }
                    }
                }
                i++;
            }
        }

        return paramMap;
    }

}
