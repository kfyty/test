package com.kfyty.test.config;

import com.kfyty.loveqq.framework.core.autoconfig.annotation.Component;
import com.kfyty.loveqq.framework.web.core.interceptor.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

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
