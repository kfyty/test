package com.kfyty.test.config;

import com.kfyty.loveqq.framework.web.core.annotation.ControllerAdvice;
import com.kfyty.loveqq.framework.web.core.annotation.ExceptionHandler;
import com.kfyty.test.exception.BusinessException;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/6/18 11:52
 * @email kfyty725@hotmail.com
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public String exception(BusinessException e) {
        return e.getMessage();
    }
}
