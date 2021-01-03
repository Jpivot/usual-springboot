package concurrent;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sherlock on 2020/9/16.
 */
public class PageTest<T> implements Serializable {
    public static void main(String[] args) {
        List<String> list= Arrays.asList("hello","world","cat","dog","duck");
        String s="string";
        Object[] objects=new Object[]{list,s};
        for (int i=0;i<objects.length;i++){
            if(objects[i] instanceof List){
                System.out.println("List类型对象");
                System.out.println(list.toString());
                continue;
            }
            if (objects[i] instanceof String){
                System.out.println("String类型对象");
                System.out.println(objects[i]);
            }
        }
        System.out.println("objects长度："+objects.length);
    }
}
