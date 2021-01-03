package com.jpivot.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.AsyncRestTemplate;

@Configuration
public class AsyncRestTemplateConfig {

    @Bean
    public AsyncRestTemplate getAsyncRestTemplate(){
        return new AsyncRestTemplate();
    }
}
