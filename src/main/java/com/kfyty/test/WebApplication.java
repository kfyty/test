package com.kfyty.test;

import com.kfyty.boot.K;
import com.kfyty.database.jdbc.autoconfig.MapperAutoConfig;
import com.kfyty.mvc.annotation.EnableWebMvc;
import com.kfyty.support.autoconfig.annotation.BootApplication;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/5/29 13:04
 * @email kfyty725@hotmail.com
 */
@EnableWebMvc
@BootApplication(exclude = MapperAutoConfig.class)
public class WebApplication {

    public static void main(String[] args) {
        K.run(WebApplication.class, args);
    }
}
