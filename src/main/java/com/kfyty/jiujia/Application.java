package com.kfyty.jiujia;

import com.kfyty.boot.K;
import com.kfyty.jiujia.service.LockService;
import com.kfyty.support.autoconfig.CommandLineRunner;
import com.kfyty.support.autoconfig.annotation.Autowired;
import com.kfyty.support.autoconfig.annotation.BootApplication;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/5/29 13:04
 * @email kfyty725@hotmail.com
 */
@BootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private LockService lockService;

    public static void main(String[] args) {
        K.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.lockService.tryLock();
    }
}
