package org.pti.poster.view;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.pti.poster.model.Post;

@Produces({ MediaType.APPLICATION_JSON })
public class PostView {
	private final Post post;

	public PostView(String text) {
		this.post = new Post(text);
	}

	@GET
	@Path("id")
	public String getName() {
		return post.getId();
	}

	@GET
	@Path("text")
	public String getNumber() {
		return post.getText();
	}

	@GET
	@Path("/")
	public Post getPost() {
		return post;

	}
}
