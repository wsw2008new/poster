package org.pti.poster.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/system")
@RestController
public class SystemController {

	@RequestMapping(value = "/health")
	public String getHealth() {
		return "up";
	}
}
