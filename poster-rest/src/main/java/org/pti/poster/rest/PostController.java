package org.pti.poster.rest;

import org.pti.poster.model.post.AbstractPost;
import org.pti.poster.model.post.PostCollection;
import org.pti.poster.model.post.UnregisteredPost;
import org.pti.poster.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/post")
@RestController
public class PostController {

	@Autowired
	PostService postService;

	@RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
	public
	@ResponseBody
	AbstractPost getPost(@RequestParam(value = "id") String id) {
		return postService.findPostById(id);
	}

	@RequestMapping(value = "/get", params = {"last"}, method = RequestMethod.GET)
	public
	@ResponseBody
	PostCollection getLastPosts(@RequestParam(value = "last") int number) {
		return postService.getLastPosts(number);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public
	@ResponseBody
	AbstractPost save(@RequestBody UnregisteredPost post) {
		return postService.savePost(post);
	}
}
