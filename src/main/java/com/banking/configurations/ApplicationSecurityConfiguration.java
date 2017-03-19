package com.banking.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ApplicationSecurityConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable().anonymous().and().authorizeRequests()
		.antMatchers(HttpMethod.POST, "/v1/**/").authenticated()
		.antMatchers(HttpMethod.GET, "/v1/**/").authenticated();
	}

}