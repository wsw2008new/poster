package org.pti.poster.auth.server.config;

import org.pti.poster.security.PosterURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {


	@Autowired
	private DataSource dataSource;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore());
		endpoints.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource)
				.withClient("curl")
				.authorities("ROLE_ADMIN")
				.resourceIds(PosterURL.API_ALL)
				.scopes("read", "write")
				.authorizedGrantTypes("client_credentials")
				.secret("password")
				.and()
				.withClient("web")
				.authorities("ROLE_ADMIN")
				.redirectUris("http://localhost:8080/api/swagger/index.html")
				.resourceIds(PosterURL.API_ALL)
				.scopes("read", "write")
				.authorizedGrantTypes("implicit")
				.secret("password");
	}
}
