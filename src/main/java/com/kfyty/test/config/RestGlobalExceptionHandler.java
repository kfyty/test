package com.kfyty.test.config;

import com.kfyty.mvc.annotation.ExceptionHandler;
import com.kfyty.mvc.annotation.RestControllerAdvice;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/6/18 11:52
 * @email kfyty725@hotmail.com
 */
@RestControllerAdvice
public class RestGlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String exception(Exception e) {
        return e.getMessage();
    }
}
