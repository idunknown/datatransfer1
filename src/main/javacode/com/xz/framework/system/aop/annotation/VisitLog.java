package com.xz.framework.system.aop.annotation;

import java.lang.annotation.*;

/**
 * @author  wuhy on 2017/11/19.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VisitLog {
    String description()  default "";
    String clazz() default "" ;
    String method() default "";
}
