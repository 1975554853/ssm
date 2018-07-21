package com.beiyou.util;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface Systemrizhi {
    String desc() default "";
    boolean isWrite() default true;
}
