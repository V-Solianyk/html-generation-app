package com.example.htmlgenerationapp.retry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Aspect
@Component
public class CustomRetryAspect {
    @Around("@annotation(customRetryable)")
    public Object handleCustomRetry(ProceedingJoinPoint joinPoint,
                                    CustomRetryable customRetryable) throws Throwable {
        int maxAttempts = customRetryable.maxAttempts();
        long initialDelay = customRetryable.initialDelay();
        double multiplier = customRetryable.multiplier();
        return retryWithExponentialBackoff(joinPoint, maxAttempts, initialDelay, multiplier);
    }

    private Object retryWithExponentialBackoff(
            ProceedingJoinPoint joinPoint, int maxAttempts, long initialDelay,
            double multiplier) throws Throwable {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                return joinPoint.proceed();
            } catch (HttpClientErrorException exception) {
                if (attempt >= maxAttempts) {
                    throw exception;
                }
            }
            Thread.sleep(initialDelay);
            initialDelay *= multiplier;
        }
        throw new IllegalStateException();
    }
}
