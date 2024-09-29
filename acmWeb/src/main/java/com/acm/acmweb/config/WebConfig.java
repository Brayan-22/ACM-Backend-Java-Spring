package com.acm.acmweb.config;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;

@Configuration
public class WebConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        return builder.messageConverters(converter).build();
    }

    @Bean
    @Scope("prototype")
    public HttpClient getHttpClient() {
        return HttpClient.newHttpClient();
    }
}
