package com.voksel.electric.pc.config;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.zkoss.zk.au.http.DHtmlUpdateServlet;
import org.zkoss.zk.ui.http.DHtmlLayoutServlet;
import org.zkoss.zk.ui.http.HttpSessionListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edsarp on 7/3/2016.
 */
@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {


    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".zul");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Bean
    public ServletRegistrationBean dHtmlLayoutServlet() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("update-uri", "/zkau");
        DHtmlLayoutServlet dHtmlLayoutServlet = new DHtmlLayoutServlet();
        ServletRegistrationBean reg = new ServletRegistrationBean(dHtmlLayoutServlet, "*.zul");
        reg.setLoadOnStartup(1);
        reg.setInitParameters(params);
        return reg;
    }

    @Bean
    public ServletRegistrationBean dHtmlUpdateServlet() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("update-uri", "/zkau/*");
        ServletRegistrationBean reg = new ServletRegistrationBean(new DHtmlUpdateServlet(), "/zkau/*");
        reg.setLoadOnStartup(2);
        reg.setInitParameters(params);
        return reg;
    }

    @Bean
    public HttpSessionListener httpSessionListener() {
        return new HttpSessionListener();
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler(new String[]{"/asset/**"}).addResourceLocations(new String[]{"asset/"});
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addRedirectViewController("/","/index.zul");

    }
}
