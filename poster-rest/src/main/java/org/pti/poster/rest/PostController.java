package org.pti.poster.rest;

import org.pti.poster.dto.post.GenericPostCollectionDto;
import org.pti.poster.dto.post.GenericPostDto;
import org.pti.poster.dto.post.NewPostDto;
import org.pti.poster.service.post.PostCachingService;
import org.pti.poster.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/poster/api/post")
@RestController
public class PostController {

	@Autowired
	PostService postService;

	@Autowired
	PostCachingService postCachingService;

	@RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
	public
	@ResponseBody
	GenericPostDto getPost(@PathVariable(value = "id") String id) {
		return postService.findPostById(id);
	}

	@RequestMapping(value = "/get/user/{id}", method = RequestMethod.GET)
	public
	@ResponseBody
	GenericPostCollectionDto getPostsByUserId(@PathVariable(value = "id") String id) {
		return postService.findPostsByUserId(id);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public
	@ResponseBody
	GenericPostDto save(@RequestBody NewPostDto post) {
		return postService.savePost(post);
	}

	@RequestMapping(value = "/cache", method = RequestMethod.POST)
	public
	@ResponseBody
	void cache(@RequestBody NewPostDto post) {
		postCachingService.cachePost(post);
	}

	@RequestMapping(value = "/get/cached/", method = RequestMethod.GET)
	public
	@ResponseBody
	GenericPostCollectionDto getCachedPosts() {
		return postCachingService.getCachedPosts();
	}

}
