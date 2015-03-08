package org.pti.poster.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.pti.poster.view.PostView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactsService {

	@RequestMapping(value = "get")
	public PostView getPost() {
		return new PostView("Hello World!");
	}
}
