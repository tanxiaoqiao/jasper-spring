package com.report.config;

import com.report.interceptor.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {

    @Bean
    PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pageInterceptor()).addPathPatterns("/**");
    }


}
