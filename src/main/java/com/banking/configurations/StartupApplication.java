package com.banking.configurations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan({ "com.banking.domain", "com.banking.infrastructure", "com.banking.infrastructure.repositories",
"com.banking.domain.security", "com.banking.controllers" })
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "com.banking.infrastructure.repositories" })
@EntityScan({ "com.banking.domain.account" })
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ, proxyTargetClass = true)
@Import({ DefaultConfiguration.class, SwaggerConfiguration.class, JPAConfiguration.class,
	ApplicationSecurityConfiguration.class, AuthServerOAuth2Config.class, OAuth2ResourceServerConfig.class })
public class StartupApplication extends org.springframework.boot.web.support.SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application;
	}

	public static void main(final String[] args) {
		SpringApplication.run(StartupApplication.class, args);
	}

}
