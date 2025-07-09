package com.clone.metube.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해
                .allowedOrigins("http://localhost:3000") // React 앱 주소
                .allowedMethods("*") // GET, POST 등 모든 메서드 허용
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true); // 쿠키 포함 허용
    }
}
