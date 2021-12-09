package com.wutl.syscenter.config;

import com.wutl.syscenter.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * Web相关配置
 *
 * @author zifangsky
 * @date 2018/7/9
 * @since 1.0.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    private static final List<String> EXCLUDE_PATH = Arrays.asList("/", "/css/**", "/js/**", "/img/**", "/media/**", "/vendors/**");

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                //对应的不拦截的请求
                .excludePathPatterns("/", "/index", "/login", "/error");
        //注意，如果跳转/login地址报错了，则系统会默认跳转error页面，然后又跳转/login然后又error就一直死循环了。

    }
}
