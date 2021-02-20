package com.seckill.handler;

import com.seckill.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 * @author kai
 * @date 2021-02-20 12:19
 */
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(HttpServletRequest request, Exception e) {
        String reqUrl = request.getRequestURL().toString();
        String reqMethod = request.getMethod();
        log.info("异常请求信息：{} {}", reqMethod, reqUrl);
        log.error("统一异常处理：", e);
        return Result.failure(e.getMessage());
    }
}
