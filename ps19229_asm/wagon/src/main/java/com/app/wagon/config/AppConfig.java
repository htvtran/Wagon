package com.app.wagon.config;

import java.util.function.BiFunction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Bean
    AppInterceptor myInterceptor() {
        return new AppInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(new MyInterceptor(subCatDao, catDao));
        registry.addInterceptor(myInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/assets/**", "/rest/**", "/admin/**");

    }

    @Bean
    public BiFunction<String, String, String> replaceOrAddParam() {
        return (paramName, newValue) -> ServletUriComponentsBuilder.fromCurrentRequest()
                .replaceQueryParam(paramName, newValue)
                .toUriString();
    }
}
