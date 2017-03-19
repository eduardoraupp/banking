package com.banking.configurations;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultConfiguration {

	private final Properties properties = new Properties();

	@PostConstruct
	public void init() {
		try {
			this.properties.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
		} catch (final IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Bean
	@Qualifier("property")
	public Properties getProperties() {
		return this.properties;
	}

}
