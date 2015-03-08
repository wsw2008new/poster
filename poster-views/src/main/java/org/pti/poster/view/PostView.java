package org.pti.poster.view;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.pti.poster.model.Post;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Produces({ MediaType.APPLICATION_JSON })
public class PostView {
	private final Post post;

	public PostView(String text) {
		this.post = new Post(text);
	}

	@RequestMapping(value = "name", method = RequestMethod.GET)
	public String getName() {
		return post.getId();
	}

	@GET
	@Path("text")
	public String getNumber() {
		return "hello";
	}

	@GET
	@Path("/")
	public Post getPost() {
		return post;

	}
}
