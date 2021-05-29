package com.kfyty.test.config;

import com.kfyty.mvc.servlet.DispatcherServlet;
import com.kfyty.support.autoconfig.InitializingBean;
import com.kfyty.support.autoconfig.annotation.Autowired;
import com.kfyty.support.autoconfig.annotation.Configuration;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/5/23 21:41
 * @email kfyty725@hotmail.com
 */
@Configuration
public class WebMvcConfig implements InitializingBean {
    @Autowired
    private DispatcherServlet dispatcherServlet;

    public void afterPropertiesSet() {
        dispatcherServlet.setPrefix("/jsp");
    }
}
