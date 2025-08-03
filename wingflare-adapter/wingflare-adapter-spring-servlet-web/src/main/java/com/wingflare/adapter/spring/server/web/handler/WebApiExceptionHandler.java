package com.wingflare.adapter.spring.server.web.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wingflare.lib.core.exceptions.*;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.spring.utils.ApiHelperUtil;
import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.standard.R;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static net.logstash.logback.argument.StructuredArguments.e;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @description web api服务异常处理器
 */
@ControllerAdvice
public class WebApiExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 业务异常
     * @param req
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(BusinessLogicException.class)
    public ModelAndView businessExceptionHandler(HttpServletRequest req, BusinessLogicException e) {
        if (logger.isDebugEnabled()) {
            logger.warn("业务异常", e(Map.of(
                    "message", e.getMessage(),
                    "exception", e.getClass().getName()
            )));
            logger.warn("异常堆栈", e(Map.of(
                    "stack", ExceptionUtils.getStackTrace(e)
            )));
        }

        MappingJackson2JsonView view = new MappingJackson2JsonView(objectMapper);
        view.setExtractValueFromSingleKeyModel(true);
        ModelAndView modelAndView = new ModelAndView(view);

        if (e.getRet() != null) {
            modelAndView.addObject(R.r(e.getData(), e.getRet(), e.getMessage()));
        } else {
            modelAndView.addObject(R.fail(e.getData(), e.getMessage()));
        }

        return modelAndView;
    }

    /**
     * 数据错误异常
     * @param req
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataException.class)
    public ModelAndView dataExceptionHandler(HttpServletRequest req, DataException e) {
        if (logger.isDebugEnabled()) {
            logger.warn("数据异常", e(Map.of(
                    "message", e.getMessage(),
                    "exception", e.getClass().getName()
            )));
            logger.warn("异常堆栈", e(Map.of(
                    "stack", ExceptionUtils.getStackTrace(e)
            )));
        }

        MappingJackson2JsonView view = new MappingJackson2JsonView(objectMapper);
        view.setExtractValueFromSingleKeyModel(true);
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject(R.fail(e.getData(), e.getMessage()));
        return modelAndView;
    }

    /**
     * 无异常
     * @param req
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(NoException.class)
    public ModelAndView noExceptionHandler(HttpServletRequest req, NoException e) {
        MappingJackson2JsonView view = new MappingJackson2JsonView(objectMapper);
        view.setExtractValueFromSingleKeyModel(true);
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject(R.ok(e.getData(), e.getMessage()));
        return modelAndView;
    }

    /**
     * 无权限异常
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler(NoPermissionException.class)
    public Object noPermissionExceptionHandler(HttpServletRequest req, BusinessLogicException e) {
        ApiHelperUtil.setOriginalResp(true);
        if (logger.isDebugEnabled()) {
            logger.warn("未授权访问", e(Map.of(
                    "message", e.getMessage(),
                    "exception", e.getClass().getName()
            )));
            logger.warn("异常堆栈", e(Map.of(
                    "stack", ExceptionUtils.getStackTrace(e)
            )));
        }

        return "";
    }

    /**
     * 无权限异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(CaptchaException.class)
    public Object captchaExceptionHandler(HttpServletRequest req, CaptchaException e) {
        ApiHelperUtil.setOriginalResp(true);
        if (logger.isDebugEnabled()) {
            logger.warn("异常堆栈", e(Map.of(
                    "stack", ExceptionUtils.getStackTrace(e)
            )));
        }

        // 创建自定义响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add(Ctx.HEADER_KEY_CAPTCHA_OPEN, "true");

        if (CollectionUtil.isNotEmpty(e.urls)) {
            headers.add(Ctx.HEADER_KEY_CAPTCHA_URL, StringUtil.join(e.urls, ","));
        }

        return new ResponseEntity<>(null, headers, HttpStatus.LOCKED);
    }

    /**
     * 服务未找到异常
     * @param req
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView noHandlerFoundExceptionHandler(HttpServletRequest req, NoHandlerFoundException e) {
        MappingJackson2JsonView view = new MappingJackson2JsonView(objectMapper);
        view.setExtractValueFromSingleKeyModel(true);
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject(R.fail(DataNotFoundException.defaultMessage));
        return modelAndView;
    }

    /**
     * 405异常处理
     * @param req
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView httpRequestMethodNotSupportedExceptionHandler(
            HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
        MappingJackson2JsonView view = new MappingJackson2JsonView(objectMapper);
        view.setExtractValueFromSingleKeyModel(true);
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject(R.fail("request.method.notSupported"));
        return modelAndView;
    }

    /**
     * 415异常处理
     * @param req
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ModelAndView httpMediaTypeNotSupportedExceptionHandler(
            HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
        MappingJackson2JsonView view = new MappingJackson2JsonView(objectMapper);
        view.setExtractValueFromSingleKeyModel(true);
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject(R.fail("request.mediaType.notSupported"));
        return modelAndView;
    }

    /**
     * 兜底异常
     * @param req
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ModelAndView baseExceptionHandler(
            HttpServletRequest req, Throwable e) {
        logger.error("服务异常", e(Map.of(
                "message", e.getMessage(),
                "exception", e.getClass().getName()
        )));
        logger.error("异常堆栈", e(Map.of(
                "stack", ExceptionUtils.getStackTrace(e)
        )));
        MappingJackson2JsonView view = new MappingJackson2JsonView(objectMapper);
        view.setExtractValueFromSingleKeyModel(true);
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject(R.fail(ServerInternalException.defaultMessage));
        return modelAndView;
    }

    /**
     * 参数格式无法解析
     *
     * @param req
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ModelAndView argumentFormatExceptionHandler(
            HttpServletRequest req, HttpMessageNotReadableException e) {

        if (logger.isDebugEnabled()) {
            logger.error("参数解析异常", e(Map.of(
                    "message", e.getMessage(),
                    "exception", e.getClass().getName()
            )));
            logger.error("异常堆栈", e(Map.of(
                    "stack", ExceptionUtils.getStackTrace(e)
            )));
        }

        MappingJackson2JsonView view = new MappingJackson2JsonView(objectMapper);
        view.setExtractValueFromSingleKeyModel(true);
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject(R.fail("request.argumentFormatException"));
        return modelAndView;
    }

    /**
     * 断言（非法参数）
     */
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView illegalArgumentExceptionHandler(IllegalArgumentException e){
        if (logger.isDebugEnabled()) {
            logger.warn("逻辑异常", e(Map.of(
                    "message", e.getMessage(),
                    "exception", e.getClass().getName()
            )));
            logger.warn("异常堆栈", e(Map.of(
                    "stack", ExceptionUtils.getStackTrace(e)
            )));
        }
        MappingJackson2JsonView view = new MappingJackson2JsonView(objectMapper);
        view.setExtractValueFromSingleKeyModel(true);
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject(R.fail(e.getMessage()));
        return modelAndView;
    }

    /**
     * 参数验证失败异常
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        if (logger.isDebugEnabled()) {
            logger.warn("参数验证失败", e(Map.of(
                    "message", e.getMessage(),
                    "exception", e.getClass().getName()
            )));
            logger.warn("异常堆栈", e(Map.of(
                    "stack", ExceptionUtils.getStackTrace(e)
            )));
        }

        R<Object> r;
        List<String> errMsgList = e.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .distinct()
                .collect(Collectors.toList());

        if (errMsgList.size() == 1) {
            r = R.fail(errMsgList.get(0));
        } else {
            r = R.fail(errMsgList, "biz.business.paramErr");
        }

        MappingJackson2JsonView view = new MappingJackson2JsonView(objectMapper);
        view.setExtractValueFromSingleKeyModel(true);
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject(r);
        return modelAndView;
    }

    /**
     * 参数验证失败异常
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView constraintViolationExceptionHandler(ConstraintViolationException e){
        if (logger.isDebugEnabled()) {
            logger.warn("参数验证失败", e(Map.of(
                    "message", e.getMessage(),
                    "exception", e.getClass().getName()
            )));
            logger.warn("异常堆栈", e(Map.of(
                    "stack", ExceptionUtils.getStackTrace(e)
            )));
        }

        R<Object> r;
        Set<ConstraintViolation<?>> violationSet = e.getConstraintViolations();
        AtomicReference<String> path = new AtomicReference<>("");
        violationSet.stream().findFirst().ifPresent(v -> path.set(v.getPropertyPath().toString()));

        if (path.get().split("\\.").length == 2) {
            r = R.fail("sys.business.paramNull");
        } else {
            List<String> errMsgList = violationSet.stream()
                    .map(ConstraintViolation::getMessage)
                    .distinct()
                    .collect(Collectors.toList());

            if (errMsgList.size() == 1) {
                r = R.fail(errMsgList.get(0));
            } else {
                r = R.fail(errMsgList, "biz.business.paramErr");
            }
        }

        MappingJackson2JsonView view = new MappingJackson2JsonView(objectMapper);
        view.setExtractValueFromSingleKeyModel(true);
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject(r);
        return modelAndView;
    }

}
