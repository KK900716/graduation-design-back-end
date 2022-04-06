package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 44380
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /**
     * com.example.config.MvcConfig.addCorsMappings():
     * 解决跨域问题
     * @author ljc
     * @date 2022/4/6~17:58
     * @param registry 拦截器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
