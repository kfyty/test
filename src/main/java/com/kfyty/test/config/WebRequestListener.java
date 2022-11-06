package com.kfyty.test.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/6/6 16:51
 * @email kfyty725@hotmail.com
 */
@Slf4j
@WebListener
public class WebRequestListener implements ServletRequestListener {

    public void requestInitialized(ServletRequestEvent sre) {
        log.info("request init: {}", sre);
    }
}
