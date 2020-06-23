package com.example.lyy.thread;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRejectedPolicy {
    String value() default "";
}

