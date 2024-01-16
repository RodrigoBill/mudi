package com.alura.mudi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.alura.mudi.interceptor.InterceptorDeAcessos;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport{

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorDeAcessos()).addPathPatterns("/**");
    }
    
}
