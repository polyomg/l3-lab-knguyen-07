package com.poly.lab8.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/admin/**", "/account/change-password", "/account/edit-profile", "/order/**")
                .excludePathPatterns("/admin/home/index");

        // LogInterceptor only for protected URIs (same patterns)
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/admin/**", "/account/change-password", "/account/edit-profile", "/order/**")
                .excludePathPatterns("/admin/home/index");
    }
}
