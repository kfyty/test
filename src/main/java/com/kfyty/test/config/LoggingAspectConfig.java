package com.kfyty.test.config;

import com.kfyty.core.autoconfig.annotation.Component;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

/**
 * 描述: 日志切面配置
 *
 * @author kfyty725
 * @date 2021/8/1 20:36
 * @email kfyty725@hotmail.com
 */
@Slf4j
@Aspect
@Component
public class LoggingAspectConfig {

    @Around("execution(* com.kfyty.test.controller.*.*(..))")
    public Object round(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("request method: {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("request parameters: {}", Arrays.toString(joinPoint.getArgs()));
        Object retValue = joinPoint.proceed();
        log.info("request return value: {}", retValue);
        return retValue;
    }
}
