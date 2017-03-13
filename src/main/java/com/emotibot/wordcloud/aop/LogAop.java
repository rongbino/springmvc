package com.emotibot.wordcloud.aop;

import java.lang.annotation.*;

/**
 * Created by tommy on 2017/3/13.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAop {
  public void beforeLog();
  public void afterLog();
}
