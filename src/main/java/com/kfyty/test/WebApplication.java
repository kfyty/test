package com.kfyty.test;

import com.kfyty.loveqq.framework.boot.K;
import com.kfyty.loveqq.framework.core.autoconfig.annotation.BootApplication;
import com.kfyty.loveqq.framework.web.core.autoconfig.annotation.EnableWebMvc;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/5/29 13:04
 * @email kfyty725@hotmail.com
 */
@EnableWebMvc
@BootApplication
public class WebApplication {

    public static void main(String[] args) {
        K.run(WebApplication.class, args);
    }
}
