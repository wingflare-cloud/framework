package com.wingflare.tool.generator.configurer;

import com.wingflare.tool.generator.common.Result;
import com.wingflare.tool.generator.common.ResultCode;
import com.wingflare.tool.generator.common.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * 用于全局异常处理
 */
@ControllerAdvice
@Slf4j
public class WebControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest request, Exception e) {
        Result result = new Result();
        log.info("未捕获的异常：" + e.getMessage(), e);
        if (e instanceof ServiceException) {
            result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
        } else if (e instanceof NoHandlerFoundException) {
            result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
        } else if (e instanceof ServletException) {
            result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
        } else {
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("系统发生内部错误，请联系管理员");
            log.error("系统发生内部错误，请查看控制台日志了解详情", e);
        }
        return result;
    }

}
