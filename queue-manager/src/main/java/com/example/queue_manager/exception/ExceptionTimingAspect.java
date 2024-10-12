package com.example.queue_manager.exception;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExceptionTimingAspect {
    @Around("@annotation(com.example.queue_manager.exception.TimerAnnotation)")
    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        String actualClassName = joinPoint.getSignature().getDeclaringTypeName();

        log.info("Class name '{}' executed in {} ms", actualClassName, executionTime);

        return result;
    }
}
