package com.kfyty.jiujia;

import com.kfyty.boot.K;
import com.kfyty.boot.configuration.ValidationAutoConfiguration;
import com.kfyty.jiujia.service.LockService;
import com.kfyty.support.autoconfig.CommandLineRunner;
import com.kfyty.support.autoconfig.annotation.Autowired;
import com.kfyty.support.autoconfig.annotation.BootApplication;

import java.util.Objects;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/5/29 13:04
 * @email kfyty725@hotmail.com
 */
@BootApplication(exclude = ValidationAutoConfiguration.class)
public class Application implements CommandLineRunner {
    @Autowired
    private LockService lockService;

    public static void main(String[] args) {
        K.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LockService.tellerInfo = resolveTellInfo(args);
        this.lockService.tryLock();
    }

    private static String resolveTellInfo(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (Objects.equals(args[i], "--tellerInfo")) {
                if (i == args.length - 1) {
                    throw new RuntimeException("请设置 --tellerInfo 参数 !");
                }
                return args[i + 1];
            }
        }
        throw new RuntimeException("请设置 --tellerInfo 参数 !");
    }
}
