package com.example.lyy.annotation;

import java.lang.annotation.*;

/**
 * @Author: cjw
 * @Description: 接口幂等注解
 * @Date: 2020/4/1 9:19
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClassEncryptionAnnotation {
}
