package com.kfyty.test.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/6/6 16:51
 * @email kfyty725@hotmail.com
 */
@Slf4j
@WebFilter
public class WebRequestFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("do filter: {}", request);
        chain.doFilter(request, response);
    }
}
