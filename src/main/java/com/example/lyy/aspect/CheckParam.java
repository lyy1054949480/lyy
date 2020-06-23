package com.example.lyy.aspect;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckParam {
    //默认String(00),自定义实体(01)
    String fieldType() default "00";

}