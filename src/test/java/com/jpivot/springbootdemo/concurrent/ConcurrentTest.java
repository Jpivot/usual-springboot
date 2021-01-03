package com.jpivot.springbootdemo.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Sherlock on 2019/8/13.
 */
public class ConcurrentTest {
    public static void main(String args[]){
        AnonymousTest anonymousTest=new AnonymousTest(){
            public void test() {
                System.out.println("hello world");
            }
        };
    }
}
