package cn.bonuli.configuration;

import cn.bonuli.configuration.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * WebMvcConfig
 *
 * @author D.jin
 * @date 2018/10/23
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置访问静态资源
     */

    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //super.addResourceHandlers(registry);

    }


    /**
     * 配置访问html 页面
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/user/login").setViewName("login");
        registry.addViewController("/home/index").setViewName("homepage1");
        registry.addViewController("/operation/inbound").setViewName("inbound");
        registry.addViewController("/operation/outbound").setViewName("outbound");
        registry.addViewController("/error/500").setViewName("error");
        registry.addViewController("/error/404").setViewName("404");
        //super.addViewControllers(registry);

    }


    /**
     * 配置拦截规则
     */
    public void addInterceptors(InterceptorRegistry registry) {
        String[] exclude = new String[]{"/user/login","/user/login-in","/user/login.html","/error","/layuiadmin/**"};
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(exclude);
       // super.addInterceptors(registry);
    }
}
