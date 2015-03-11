package org.pti.poster.rest;

import org.pti.poster.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/")
@RestController
public class SystemController {
	@Autowired
	PostService postService;

	@RequestMapping(name = "/health", method = RequestMethod.GET)
	public String getHealth() {
		return "up";
	}
}
