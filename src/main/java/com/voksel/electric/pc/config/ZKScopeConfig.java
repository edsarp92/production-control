package com.voksel.electric.pc.config;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zkoss.spring.web.context.request.*;

/**
 * Created by Edsarp on 7/3/2016.
 */
@Configuration
public class ZKScopeConfig {

    @Bean
    public static DesktopScope desktopScope() {
        return new DesktopScope();
    }

    @Bean
    public static PageScope pageScope() {
        return new PageScope();
    }

    @Bean
    public static IdSpaceScope idSpaceScope() {
        return new IdSpaceScope();
    }

    @Bean
    public static ComponentScope componentScope() {
        return new ComponentScope();
    }

    @Bean
    public static ExecutionScope executionScope() {
        return new ExecutionScope();
    }

    @Bean
    public static CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer scopeConfigurer = new CustomScopeConfigurer();
        scopeConfigurer.addScope("desktop", desktopScope());
        scopeConfigurer.addScope("idspace", idSpaceScope());
        scopeConfigurer.addScope("page", pageScope());
        scopeConfigurer.addScope("component", componentScope());
        scopeConfigurer.addScope("execution", executionScope());
        return scopeConfigurer;
    }
}
