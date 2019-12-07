package com.lizj.mars.mybatis.advisor;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvisor {

    @Pointcut("execution(public * com.lizj.mars.mybatis.aspect.MyProxyService.test(..))")
    public void myPoint(){}

    @Before("myPoint()")
    public void before() {
        System.out.println("com.lizj.mars.mybatis.advisor.MyAdvisor.before()...");
    }
}
