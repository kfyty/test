package com.kfyty.test.config;

import com.kfyty.loveqq.framework.core.autoconfig.annotation.Component;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/6/6 16:51
 * @email kfyty725@hotmail.com
 */
@Slf4j
@Component
public class WebRequestListener implements ServletRequestListener {

    public void requestInitialized(ServletRequestEvent sre) {
        log.info("request init: {}", sre);
    }
}
