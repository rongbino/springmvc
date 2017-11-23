package com.trong.wordcloud.log;

import java.lang.annotation.*;

/**
 * Created by tommy on 2017/3/13.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LogAop {
  String desc() default "";
}
