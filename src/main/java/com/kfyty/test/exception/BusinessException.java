package com.kfyty.test.exception;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/6/18 11:53
 * @email kfyty725@hotmail.com
 */
public class BusinessException extends RuntimeException {
    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }
}
