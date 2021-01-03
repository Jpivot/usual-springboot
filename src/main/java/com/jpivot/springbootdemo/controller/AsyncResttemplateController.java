package com.jpivot.springbootdemo.controller;

import com.jpivot.springbootdemo.model.CustomerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
@Slf4j
public class AsyncResttemplateController {
    @Autowired
    AsyncRestTemplate asyncRestTemplate;

    @Autowired
    WebClient webClient;

    @RequestMapping("/demo")
    public String demo() {
        try {
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date() + "--->>>30秒。。。。";
    }

    @RequestMapping("/async")
    public String async() {
        String url = "http://localhost:8080/customerinfo?id=2";
        // 调用完后立即返回（没有阻塞）
        ListenableFuture<ResponseEntity<CustomerInfo>> forEntity = asyncRestTemplate.getForEntity(url, CustomerInfo.class);


        // 异步调用后的回调函数
        forEntity.addCallback(new ListenableFutureCallback<ResponseEntity<CustomerInfo>>() {
            // 调用失败
            @Override
            public void onFailure(Throwable ex) {
                log.error("=====rest response faliure======");
            }

            // 调用成功
            @Override
            public void onSuccess(ResponseEntity<CustomerInfo> customerInfoResponseEntity) {
                log.info("--->async rest response success----, result = {}", customerInfoResponseEntity.toString());
            }
        });
        return new Date() + "--->>>异步调用结束";
    }

    @RequestMapping("/async-webclient")
    public String asyncWebClient() {
        Mono<CustomerInfo> result = webClient.get()
                .uri("http://localhost:8080/customerinfo?id=2", 256)
                .acceptCharset(StandardCharsets.UTF_8)
                // 返回结果如果设置成TEXT_HTML，则执行之后会报错。
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CustomerInfo.class);

        result.subscribe(customerInfo -> log.info("WebClient调用返回结果：{}",customerInfo.toString()));
        return new Date() + "--->>>异步调用结束";
    }
}
