package com.example.queue_manager.infrastructure;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AutoMeasuringTimeJoinPoint {
    @Pointcut("@annotation(com.example.queue_manager.infrastructure.HandleQueueEntry)")
    private void execAll() {
    }

    @Pointcut("execAll()")
    public void execPointcut() {
    }
}
