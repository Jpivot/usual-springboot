package com.jpivot.springbootdemo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpivot.springbootdemo.model.CustomerInfo;

import java.io.IOException;
import java.util.List;

public class JacksonTest {
    public static void main(String[] args) throws IOException {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setId(111);
        customerInfo.setInterest("编程");
        customerInfo.setUsername("仗剑走天涯");
        ObjectMapper objectMapper = new ObjectMapper();
        // 序列化
        String jsonString = objectMapper.writeValueAsString(customerInfo);
        System.out.println(jsonString);

        // 反序列化
        String string = "{\"id\":111,\"username\":\"仗剑走天涯\",\"interest\":\"编程\"}";
        CustomerInfo customerInfo1 = objectMapper.readValue(string,CustomerInfo.class);
        System.out.println(customerInfo1.toString());

        // 基于”对象绑定” 的 ObjectMapper，将 JSON 解析为基于“树模型”的 JsonNode 对象
        JsonNode jsonNode = objectMapper.readTree(string);
        System.out.println(jsonNode.get("username").asText());

        // 借助 TypeReference 可以将 JSON 字符串数组转成泛型 List
        String listString = "[{\"id\":111,\"username\":\"仗剑走天涯\",\"interest\":\"编程\"},{\"id\":113,\"username\":\"我的祖国\",\"interest\":\"算法\"},{\"id\":141,\"username\":\"中国之子\",\"interest\":\"自然语言处理\"}]";
        List<CustomerInfo> customerInfoList = objectMapper.readValue(listString,new TypeReference<List<CustomerInfo>>(){});
        System.out.println(customerInfoList.toString());

        // configure()方法忽略“无法识别”的字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        String moreFieldString = "{\"id\":111,\"username\":\"仗剑走天涯\",\"interest\":\"编程\",\"occupation\":\"程序员\"}";
        CustomerInfo customerInfo2 = objectMapper.readValue(moreFieldString,CustomerInfo.class);
        System.out.println(customerInfo2.toString());

        // 在序列化时忽略值为 null 的属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // 忽略值为默认值的属性
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);
        // 造有空属性的对象，经测试，加了lombok注解的对象要在类上添加另外的注解，以上的设置没用
        CustomerInfo testNullPropertyCustomerInfo = new CustomerInfo();
        testNullPropertyCustomerInfo.setId(987897899);
        testNullPropertyCustomerInfo.setUsername("张三");
        System.out.println(objectMapper.writeValueAsString(testNullPropertyCustomerInfo));

        // 测试没有用lombok注解的实体类，上面对objectMapper的配置有起作用
        Author author = new Author();
        author.setAge(123);
        System.out.println(objectMapper.writeValueAsString(author));




    }

}

/**
 * 过滤多个字段的注解
 */
@JsonIgnoreProperties(value = { "age","birthday" })
class Author{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 过滤单个字段的注解
     * @return
     */
//    @JsonIgnore
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;
    private int age;

    // getter/setter

    // toString
}
