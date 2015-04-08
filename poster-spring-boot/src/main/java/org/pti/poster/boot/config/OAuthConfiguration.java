package org.pti.poster.boot.config;

import org.pti.poster.security.PosterURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableResourceServer
public class OAuthConfiguration extends ResourceServerConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, PosterURL.API_ALL).access("#oauth2.hasScope('read')")
				.antMatchers(HttpMethod.OPTIONS, PosterURL.API_ALL).access("#oauth2.hasScope('read')")
				.antMatchers(HttpMethod.POST, PosterURL.API_ALL).access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.PUT, PosterURL.API_ALL).access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.PATCH, PosterURL.API_ALL).access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.DELETE, PosterURL.API_ALL).access("#oauth2.hasScope('write')");
	}

}
