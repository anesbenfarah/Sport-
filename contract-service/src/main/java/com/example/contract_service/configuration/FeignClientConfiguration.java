package com.example.contract_service.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

@Configuration
public class FeignClientConfiguration {
    @Bean
    public RequestInterceptor bearerTokenRequestInterceptor() {
        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.getCredentials() instanceof String) {

                String token = (String) authentication.getCredentials();
                requestTemplate.header("Authorization", "Bearer " + token);
            } else if (authentication != null && authentication.getPrincipal() instanceof Jwt) {

                Jwt jwt = (Jwt) authentication.getPrincipal();
                requestTemplate.header("Authorization", "Bearer " + jwt.getTokenValue());
            }
        };
    }
}