package com.jpivot.springbootdemo;

import java.util.ArrayList;

/**
 * Created by Sherlock on 2020/4/4.
 */
public class TestStringSplit {
    public static void main(String[] args){
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);


        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add("hello");
        arrayList1.add("world");
//        arrayList1.add("test");
//        int i=1;
        arrayList.forEach(str -> {
            arrayList.remove(new Integer(1));

        });

        System.out.println(arrayList.toString());

    }
}
