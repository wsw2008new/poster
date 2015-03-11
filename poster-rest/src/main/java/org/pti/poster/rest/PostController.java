package org.pti.poster.rest;

import org.pti.poster.model.post.BasicPost;
import org.pti.poster.model.post.Post;
import org.pti.poster.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/post")
@RestController
public class PostController {
	@Autowired
	PostService postService;

	@RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
	public
	@ResponseBody
	BasicPost getPost(@RequestParam(value = "id") String id) {
		return (BasicPost) postService.findPostById(id);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public
	@ResponseBody
	BasicPost save(@RequestBody BasicPost post) {
		return (BasicPost) postService.savePost(post);
	}
}
