package com.banking.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerOAuth2Config
extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private javax.sql.DataSource dataSource;

	@Override
	public void configure(
			final AuthorizationServerSecurityConfigurer oauthServer)
					throws Exception {
		oauthServer
		.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(final ClientDetailsServiceConfigurer clients)
			throws Exception {
		clients.jdbc(this.dataSource)
		.withClient("clientIdPassword")
		.secret("secret")
		.authorizedGrantTypes(
				"password","authorization_code", "refresh_token")
		.accessTokenValiditySeconds(-1)
		.scopes("read", "write");
	}

	@Override
	public void configure(
			final AuthorizationServerEndpointsConfigurer endpoints)
					throws Exception {

		endpoints
		.tokenStore(this.tokenStore())
		.authenticationManager(this.authenticationManager);
	}

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(this.dataSource);
	}

	@Primary
	@Bean
	public RemoteTokenServices tokenService() {
		final RemoteTokenServices tokenService = new RemoteTokenServices();
		tokenService.setCheckTokenEndpointUrl(
				"http://localhost:8080/banking/oauth/check_token");
		tokenService.setClientId("clientIdPassword");
		tokenService.setClientSecret("secret");
		return tokenService;
	}
}
