package com.kfyty.test.config;

import com.kfyty.mvc.servlet.HandlerInterceptor;
import com.kfyty.support.autoconfig.annotation.Component;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/5/30 18:23
 * @email kfyty725@hotmail.com
 */
@Slf4j
@Component
public class WebInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("request path: {}", request.getRequestURI());
        return true;
    }
}
