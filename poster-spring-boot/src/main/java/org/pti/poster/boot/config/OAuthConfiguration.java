package org.pti.poster.boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableResourceServer
public class OAuthConfiguration extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_API = "api/**";

	@Value("${oauth_db}")
	private String oauthDbJdbc;

	@Bean
	public TokenStore tokenStore() {
		DataSource tokenDataSource = DataSourceBuilder.create().driverClassName("org.sqlite.JDBC").url(oauthDbJdbc).build();
		return new JdbcTokenStore(tokenDataSource);
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(RESOURCE_API)
				.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, RESOURCE_API).access("#oauth2.hasScope('read')")
				.antMatchers(HttpMethod.OPTIONS, RESOURCE_API).access("#oauth2.hasScope('read')")
				.antMatchers(HttpMethod.POST, RESOURCE_API).access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.PUT, RESOURCE_API).access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.PATCH, RESOURCE_API).access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.DELETE, RESOURCE_API).access("#oauth2.hasScope('write')");
	}

}
