package com.jpivot.springbootdemo;

import com.jpivot.springbootdemo.mapper.CustomerInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigInteger;


/**
 * Created by Sherlock on 2019/3/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Test
    public void contextLoads() {
       CustomerInfoMapper customerInfoMapper = (CustomerInfoMapper) ApplicationTests.queryMapper(CustomerInfoMapper.class);
//        AnnotationConfigApplicationContext ac =
    }

    public static Object queryMapper(Class clazz){
        Class[] classes = new Class[]{clazz};
        Object proxy = Proxy.newProxyInstance(ApplicationTests.class.getClassLoader(), classes, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 获取注解或者配置，进行数据库查询，然后返回相应数据
                return null;
            }
        });
        return proxy;
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(10,10));
    }

    public static int uniquePaths(int m, int n) {
        long ans =1;
        for(int i=m,j=1;i<=m+n-2;i++,j++){
            ans=ans*i/j;
        }
        return (int)ans;


    }
}
