package com.voksel.electric.pc.config;

import com.voksel.electric.pc.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackageClasses = AuthenticationService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService).passwordEncoder(passwordencoder());
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionFixation().none();
        http.authorizeRequests()
                .antMatchers(
                        "/zkau*/**",
                        "/login*", "/logout",
                        "/img/**",
                        "/static/**",
                        "/asset*/**",
                        "/WEB-INF*/**"
                )
                .permitAll().anyRequest().authenticated().and()
                .headers().frameOptions().disable().and()
                .formLogin()
                .loginPage("/login.zul")
                .permitAll()
                .defaultSuccessUrl("/main.zul", true).and()
                .logout().logoutSuccessUrl("/login.zul?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/login.zul?error").and()
                .csrf().disable();
    }

}
