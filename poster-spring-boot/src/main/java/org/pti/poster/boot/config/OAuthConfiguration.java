package org.pti.poster.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.client.token.JdbcClientTokenServices;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
@EnableOAuth2Client
@RestController
public class OAuthConfiguration {

	@Value("${oauth.resource:http://localhost:8080}")
	private String baseUrl;

	@Value("${oauth.authorize:http://localhost:21056/oauth/authorize}")
	private String authorizeUrl;

	@Value("${oauth.token:http://localhost:21056/oauth/token}")
	private String tokenUrl;

	@javax.annotation.Resource
	@Qualifier("accessTokenRequest")
	private AccessTokenRequest accessTokenRequest;

	@Autowired
	private DataSource dataSource;

	@RequestMapping("/wtf")
	public List<Map<String, ?>> home() {
		@SuppressWarnings("unchecked")
		List<Map<String, ?>> result = restTemplate().getForObject(baseUrl + "/api/swagger/index.html", List.class);
		return result;
	}

	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
	public OAuth2RestOperations restTemplate() {
		OAuth2RestTemplate template = new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(accessTokenRequest));
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
		resource.setClientId("my-trusted-client");
		return resource;
	}

}
