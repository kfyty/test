package com.kfyty.test.config;

import com.kfyty.mvc.annotation.EnableWebMvc;
import com.kfyty.mvc.autoconfig.TomcatAutoConfig;
import com.kfyty.mvc.autoconfig.WebSocketAutoConfig;
import com.kfyty.support.autoconfig.annotation.EnableAutoConfiguration;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/5/23 21:41
 * @email kfyty725@hotmail.com
 */
@EnableWebMvc
@EnableAutoConfiguration(exclude = {TomcatAutoConfig.class, WebSocketAutoConfig.class})
public class WebMvcConfig {
}
