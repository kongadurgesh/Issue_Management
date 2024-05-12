package com.tprm.ism.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class IssueServiceLoggingAspect {
    public static final Logger LOGGER = LoggerFactory.getLogger(IssueServiceLoggingAspect.class);

    @Before("execution(* com.tprm.ism.service.IssueService.*(..))")
    public void logBeforeServiceMethodCall(JoinPoint joinPoint) {
        LOGGER.info("Calling Method: {}.{} , with arguments: {}",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }
}
