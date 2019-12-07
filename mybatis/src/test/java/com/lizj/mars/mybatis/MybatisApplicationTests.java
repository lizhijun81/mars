package com.lizj.mars.mybatis;

import com.lizj.mars.mybatis.aspect.IMyProxyService;
import com.lizj.mars.mybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Resource
    private UserService userService;

    @Resource
    private IMyProxyService myProxyService;

    @Test
    public void test() {
        userService.getUser();
        System.out.println("......");
    }

    @Test
    public void testAspect() {
        myProxyService.test();
        System.out.println("...");
    }

    @Test
    public void testUser() {
        userService.insertUser();
    }

}
