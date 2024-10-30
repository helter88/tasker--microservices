package com.example.queue_manager.infrastructure;

import com.example.queue_manager.model.Queue;
import com.example.queue_manager.repository.QueueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Aspect
@Component
@RequiredArgsConstructor
@Log
public class AutoMeasuringTimeAspect {

    private final QueueRepository queueRepository;

    @Around("com.example.queue_manager.infrastructure.AutoMeasuringTimeJoinPoint.execPointcut()")
    public Object exec(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Instant begin = Instant.now(), end;
        Object result = null;
        Queue savedQueue;
        try {
            result = proceedingJoinPoint.proceed();
            if (result instanceof Queue queue) {
                savedQueue = queueRepository.save(queue);
            } else {
                throw new IllegalStateException("Method annotated with @HandleQueueEntry must return Queue object");
            }
        } catch (Throwable e) {
            throw e;
        }

        end = Instant.now();
        Duration duration = Duration.between(begin, end);
        var durationMillis= duration.toMillis();
        savedQueue.setExecutionTimeMillis(durationMillis);
        queueRepository.save(savedQueue);

        return result;
    }
}
