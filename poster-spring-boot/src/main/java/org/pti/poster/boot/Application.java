package org.pti.poster.boot;


import org.pti.poster.SpringApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.client.token.JdbcClientTokenServices;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableOAuth2Client
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@Import(value = SpringApplicationConfiguration.class)

public class Application {

	@Value("${oauth.resource:http://localhost:8080}")
	private String baseUrl;

	@Value("${oauth.authorize:http://localhost:21056/oauth/authorize}")
	private String authorizeUrl;

	@Value("${oauth.token:http://localhost:21056/oauth/token}")
	private String tokenUrl;

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}

	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
	public OAuth2RestOperations restTemplate() {
		OAuth2RestTemplate template = new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext());
		AccessTokenProviderChain provider = new AccessTokenProviderChain(Arrays.asList(new AuthorizationCodeAccessTokenProvider()));
		provider.setClientTokenServices(clientTokenServices());
		return template;
	}

	@Bean
	public ClientTokenServices clientTokenServices() {
		return new JdbcClientTokenServices(dataSource);
	}

	@Bean
	protected OAuth2ProtectedResourceDetails resource() {
		AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
		resource.setAccessTokenUri(tokenUrl);
		resource.setUserAuthorizationUri(authorizeUrl);
		resource.setClientId("poster");
		resource.setId("auth-rest");
		resource.setUseCurrentUri(true);
		return resource;
	}

//	@RequestMapping("/login")
//	public List<Map<String, ?>> home() {
//		@SuppressWarnings("unchecked")
//
//		List<Map<String, ?>> result = restTemplate().getForObject(baseUrl + "/api/**", List.class);
//		return result;
//	}
}