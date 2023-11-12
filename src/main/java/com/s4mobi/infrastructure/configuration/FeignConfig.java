package com.s4mobi.infrastructure.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Value("${parse.application.id}")
    private String applicationId;

    @Value("${parse.resti-api.key}")
    private String restiApiKey;

    @Bean
    public RequestInterceptor authorizationRequestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-Parse-Application-Id", applicationId);
            requestTemplate.header("X-Parse-REST-API-Key", restiApiKey);
            requestTemplate.header("Content-Type", "application/json");
        };
    }
}
