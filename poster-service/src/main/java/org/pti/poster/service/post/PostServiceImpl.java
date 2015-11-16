package org.pti.poster.service.post;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.pti.poster.assembler.GenericPostAssembler;
import org.pti.poster.dto.post.GenericPostCollectionDto;
import org.pti.poster.dto.post.GenericPostDto;
import org.pti.poster.dto.post.UnregisteredPostDto;
import org.pti.poster.model.post.GenericPost;
import org.pti.poster.model.post.GenericPostType;
import org.pti.poster.repository.post.MongoPostRepository;
import org.pti.poster.service.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {

	public static final String DATE_FORMAT = "dd.MM.yyyy";
	private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);

	@Autowired
	private MongoPostRepository mongoPostRepository;

	@Autowired
	private UserServiceImpl userService;

	@Override
	public GenericPostDto findPostById(String id) {
		GenericPost queryResult = null;
		try {
			queryResult = mongoPostRepository.getPostById(id);
		} catch (Exception e) {
			LOGGER.warn("No post with id found", e);
		}
		return GenericPostAssembler.toDto(queryResult);
	}

	@Override
	public GenericPostCollectionDto findPostsByUserId(String id) {
		List<GenericPost> posts = null;
		try {
			posts = mongoPostRepository.getPostsByUserObjectId(new ObjectId(id));
		} catch (Exception e) {
			LOGGER.warn("No user with id found", e);
		}
		List<GenericPostDto> postsDto = GenericPostAssembler.toDto(posts);

		return new GenericPostCollectionDto(postsDto);
	}

	@Override
	public GenericPostDto savePost(GenericPostDto newPostDto) {
		GenericPost newPost = GenericPostAssembler.fromDto(newPostDto);

		if (isPostCanBeSaved(newPost)) {
			setPostRegistered(newPost);
			GenericPost registeredPost = mongoPostRepository.save(newPost);
			return GenericPostAssembler.toDto(registeredPost);
		} else {
			UnregisteredPostDto unregistredPost = new UnregisteredPostDto();
			GenericPostAssembler.copyFieldsFromTo(newPostDto, unregistredPost);
			unregistredPost.getErrorMessages().add("Post cannot be saved");
			return unregistredPost;
		}
	}

	@Override
	public void deletePost(String id) {
		mongoPostRepository.delete(id);
	}

	private static void setPostRegistered(GenericPost post) {
		post.setType(GenericPostType.REGISTERED_POST);
		post.setDate(getCurrentDate());
	}

	private static String getCurrentDate() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(DATE_FORMAT);
		return new DateTime().toString(dateTimeFormatter);
	}

	private boolean postUserExists(GenericPost post) {
		return userService.findUserById(post.getUserId()) != null;
	}

	private static boolean postMatchesLength(GenericPost post) {
		return post.getText() != null && post.getText().length() > 0;
	}

	private boolean isPostCanBeSaved(GenericPost post) {
		return postUserExists(post) && postMatchesLength(post);
	}
}
