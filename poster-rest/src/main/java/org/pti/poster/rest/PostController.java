package org.pti.poster.rest;

import org.pti.poster.model.post.Post;
import org.pti.poster.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/post")
@RestController
public class PostController {

	@Autowired
	PostService postService;

	@RequestMapping("/health")
	public String test() {
		return "Up";
	}

	@RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
	public
	@ResponseBody
	Post getPost(@RequestParam(value = "id") String id) {
		return postService.findPostById(id);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public
	@ResponseBody
	Post save(@RequestBody Post post) {
		return postService.savePost(post);
	}
}
