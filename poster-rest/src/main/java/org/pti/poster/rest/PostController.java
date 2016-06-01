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
	@ResponseBody
	public GenericPostDto getPost(@PathVariable(value = "id") String id) {
		return postService.findPostById(id);
	}

	@RequestMapping(value = "/get/user/{id}", method = RequestMethod.GET)
	@ResponseBody
	public GenericPostCollectionDto getPostsByUserId(@PathVariable(value = "id") String id) {
		return postService.findPostsByUserId(id);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public GenericPostDto save(@RequestBody NewPostDto post) {
		return postService.savePost(post);
	}

	@RequestMapping(value = "/delete/id/{id}", method = RequestMethod.POST)
	@ResponseBody
	public void delete(@PathVariable(value = "id") String id) {
		postService.deletePost(id);
	}

	@RequestMapping(value = "/cache", method = RequestMethod.POST)
	@ResponseBody
	public void cache(@RequestBody NewPostDto post) {
		postCachingService.cachePost(post);
	}

	@RequestMapping(value = "/get/cached/", method = RequestMethod.GET)
	@ResponseBody
	public GenericPostCollectionDto getCachedPosts() {
		return postCachingService.getCachedPosts();
	}

}
