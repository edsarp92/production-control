package com.voksel.electric.pc.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.voksel.electric.pc.domain.entity"})
@EnableJpaRepositories(basePackages = {"com.voksel.electric.pc.repository"})
@EnableElasticsearchRepositories(basePackages = {"com.voksel.electric.pc.repository.search"})
@ComponentScan(basePackages = {"com.voksel.electric.pc.service","com.voksel.electric.pc.security"})
@EnableTransactionManagement
public class ServiceConfiguration {
}
