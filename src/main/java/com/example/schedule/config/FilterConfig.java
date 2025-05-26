package com.example.schedule.config;

import com.example.schedule.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {

        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}