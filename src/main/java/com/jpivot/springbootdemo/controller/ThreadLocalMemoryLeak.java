package com.jpivot.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Sherlock
 */
@RestController
public class ThreadLocalMemoryLeak {

    /**
     *  创建线程池，通过线程池，保证创建的线程存活
     */
    final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(100, 100, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>());

    /**
     *  声明本地变量
     */
    final static ThreadLocal<Byte[]> localVariable = new ThreadLocal<Byte[]>();

    @RequestMapping(value = "/test0")
    public String test0(HttpServletRequest request) {
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                // 4mb
                Byte[] c = new Byte[4096*1024];
                // 为线程添加变量
                localVariable.set(c);
                localVariable.get();
                // 线程池下，用完及时remove
                localVariable.remove();
            }
        });
        return "success";
    }

    @RequestMapping(value = "/test1")
    public String test1(HttpServletRequest request) {
        List<Byte[]> temp1 = new ArrayList<Byte[]>();

        // 20kb
        Byte[] b = new Byte[1024*20];
        // 添加局部变量
        temp1.add(b);

        return "success";
    }

}
