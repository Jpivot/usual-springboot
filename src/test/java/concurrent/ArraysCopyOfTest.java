package concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ArraysCopyOfTest {
    public static void main(String[] args) {
        Object[] elements;
        elements = new Object[10];
        elements[0]=10;
        elements[1]="string";
        elements=Arrays.copyOf(elements,20);
        System.out.println(elements.length);
        Arrays.stream(elements).forEach(System.out::println);

        for (String i="string",J="HELLO";J=="";){

        }
        Stack<String> stack = new Stack<>();


    }
}
