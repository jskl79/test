package com.solo.test.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DataSourceIntercetor())
                .addPathPatterns("/*");

//        registry.addInterceptor(new DataSourceIntercetor())
//                .addPathPatterns("/*")
//                .excludePathPatterns("/test/**/")
//                .excludePathPatterns("/users/login"); //로그인 쪽은 예외처리를 한다.
    }
}
