package org.pti.poster.rest;

import org.pti.poster.dto.post.GenericPostDto;
import org.pti.poster.dto.post.GenericPostCollectionDto;
import org.pti.poster.dto.post.UnregisteredPostDto;
import org.pti.poster.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
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
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/post")
@EnableOAuth2Client
@RestController
public class PostController {

	@Value("${oauth.resource:http://localhost:8080}")
	private String baseUrl;

	@Value("${oauth.authorize:http://localhost:21056/oauth/authorize}")
	private String authorizeUrl;

	@Value("${oauth.token:http://localhost:21056/oauth/token}")
	private String tokenUrl;

	@Autowired
	private DataSource dataSource;

//	@RequestMapping("/login")
//	public List<Map<String, ?>> home() {
//		@SuppressWarnings("unchecked")
//
//		List<Map<String, ?>> result = restTemplate().getForObject(baseUrl + "/api/**", List.class);
//		return result;
//	}


	@Autowired
	PostService postService;

	@RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
	public
	@ResponseBody
	GenericPostDto getPost(@RequestParam(value = "id") String id) {
		return postService.findPostById(id);
	}

	@RequestMapping(value = "/get", params = {"last"}, method = RequestMethod.GET)
	public
	@ResponseBody
	GenericPostCollectionDto getLastPosts(@RequestParam(value = "last") int number) {
		return postService.getLastPosts(number);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public
	@ResponseBody
	GenericPostDto save(@RequestBody UnregisteredPostDto post) {
		return postService.savePost(post);
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
		resource.setClientId("my-trusted-client");
		resource.setId("poster-rest");
		resource.setUseCurrentUri(true);
		return resource;
	}
}
