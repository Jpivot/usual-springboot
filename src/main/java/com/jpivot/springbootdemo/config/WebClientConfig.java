package com.jpivot.springbootdemo.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.resources.LoopResources;

import java.util.function.Function;

import static io.netty.channel.ChannelOption.TCP_NODELAY;

/**
 * 响应式编程，spring替换RestTemplate
 */
@Configuration
public class WebClientConfig {
    @Bean
    ReactorResourceFactory resourceFactory() {
        ReactorResourceFactory factory = new ReactorResourceFactory();
        factory.setUseGlobalResources(false);
        factory.setConnectionProvider(ConnectionProvider.fixed("httpClient", 50, 10));
        factory.setLoopResources(LoopResources.create("httpClient", 50, true));
        return factory;
    }

    @Bean
    WebClient initializeWebClient() {
        Function<HttpClient, HttpClient> mapper = client ->
                client.tcpConfiguration(c ->
                        c.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10)
                                .option(TCP_NODELAY, true)
                                .doOnConnected(conn -> {
                                    conn.addHandlerLast(new ReadTimeoutHandler(10));
                                    conn.addHandlerLast(new WriteTimeoutHandler(10));
                                }));

        ClientHttpConnector connector =
                new ReactorClientHttpConnector(resourceFactory(), mapper);

        return WebClient.builder().clientConnector(connector).build();
    }
}