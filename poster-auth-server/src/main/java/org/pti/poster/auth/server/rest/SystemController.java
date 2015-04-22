package org.pti.poster.auth.server.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/system")
@RestController
public class SystemController {

	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public String getHealth() {
		return "up";
	}
}