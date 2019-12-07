package com.lizj.mars.mybatis.aspect;

import org.springframework.stereotype.Component;

@Component
public class MyProxyService implements IMyProxyService {

    @Override
    public void test() {
        System.out.println("com.lizj.mars.mybatis.aspect.MyProxyService.test()......");
    }

}
