package com.kfyty.test.config;

import com.kfyty.loveqq.framework.core.autoconfig.BeanCustomizer;
import com.kfyty.loveqq.framework.core.autoconfig.annotation.Bean;
import com.kfyty.loveqq.framework.core.autoconfig.annotation.Configuration;
import com.kfyty.loveqq.framework.web.mvc.servlet.DispatcherServlet;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/5/23 21:41
 * @email kfyty725@hotmail.com
 */
@Configuration
public class WebMvcConfig {

    @Bean
    public BeanCustomizer<DispatcherServlet> dispatcherServletBeanCustomizer() {
        return e -> e.setPrefix("/jsp");
    }
}
