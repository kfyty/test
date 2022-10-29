package com.kfyty.test.config;

import com.kfyty.mvc.servlet.DispatcherServlet;
import com.kfyty.core.autoconfig.BeanCustomizer;
import com.kfyty.core.autoconfig.annotation.Bean;
import com.kfyty.core.autoconfig.annotation.Configuration;

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
