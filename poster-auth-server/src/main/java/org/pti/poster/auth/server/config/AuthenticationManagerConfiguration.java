package org.pti.poster.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE - 20)
public class AuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.withUser("dave")
				.password("secret")
				.roles("USER");
	}

}
