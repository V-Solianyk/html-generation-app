package com.example.htmlgenerationapp.retry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomRetryable {
    int maxAttempts() default 2;
    long initialDelay() default 1000L;
    double multiplier() default 2.0;
}