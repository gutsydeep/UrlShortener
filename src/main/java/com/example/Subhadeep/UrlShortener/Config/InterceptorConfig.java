package com.example.Subhadeep.UrlShortener.Config;

import com.example.Subhadeep.UrlShortener.Interceptor.RequestValidationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestValidationInterceptor()).addPathPatterns("/**");
    }
}
