package org.pti.poster.rest;

import org.pti.poster.oauth.AuthorizationServerOAuthRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/auth")
@RestController
public class AuthorizationController {

	@Autowired
	AuthorizationServerOAuthRestTemplate authorizationServerOAuthRestTemplate;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public List<Map<String, ?>> login() {
		@SuppressWarnings("unchecked")

		List<Map<String, ?>> result = authorizationServerOAuthRestTemplate.getRestTemplate().getForObject(authorizationServerOAuthRestTemplate.resourceUrl + "/api/system/health", List.class);
		return result;
	}

}
