package com.jpivot.springbootdemo.concurrent;

import java.util.Arrays;

/**
 * Created by Sherlock on 2020/5/24.
 */
public class FinalFieldTest {

//    public String getTestFinal() {
//        return testFinal;
//    }
//
//    public void setTestFinal(String testFinal){
//        this.testFinal=testFinal;
//    }
//
//    final String testFinal;
//
////    public FinalFieldTest(){
////        testFinal=null;
////    }



    public static boolean testMutli(String ... args){
        Arrays.stream(args).forEach(System.out::println);
        return true;
    }

    public static void main(String[] args){
        FinalFieldTest finalFieldTest=new FinalFieldTest();

//        testFinal="hello";


//        System.out.println(finalFieldTest.testFinal);

        testMutli("hello","world","test");
    }

}
