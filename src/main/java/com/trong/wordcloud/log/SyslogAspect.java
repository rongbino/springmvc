package com.trong.wordcloud.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
@Aspect
public class SyslogAspect {

  @Before("@annotation(com.trong.wordcloud.log.LogAop)")
  public void doBefore(JoinPoint point) throws Exception {
    System.out.println("point = " + point.getSignature());
    MethodSignature methodSignature = (MethodSignature)point.getSignature();
    Method method = methodSignature.getMethod();
    System.out.println("desc = " + (method.getAnnotation(LogAop.class)).desc());
  }

  @After("@annotation(com.trong.wordcloud.log.LogAop)")
  public void doAfter(JoinPoint point)
          throws Exception
  {
    System.out.println("point = " + point.getSignature());

  }
}
