package com.voksel.electric.pc;

import com.voksel.electric.pc.component.MenuLoader;
import com.voksel.electric.pc.config.ApplicationMenuLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@ComponentScan()
@EnableAutoConfiguration
@EnableJpaRepositories("com.voksel.electric.pc.repository")
@EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommonsMultipartResolver multipartResolver(){
		return new CommonsMultipartResolver();
	}

	@Bean
	public MenuLoader menuLoader(){
		return new ApplicationMenuLoader();
	}

}
