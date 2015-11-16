package org.pti.poster.assembler;

import org.bson.types.ObjectId;
import org.pti.poster.dto.post.*;
import org.pti.poster.model.post.AbstractPost;
import org.pti.poster.model.post.GenericPost;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class GenericPostAssembler extends AbstractAssembler {

	public static GenericPost fromDto(GenericPostDto postDto) {
		return convertFromDto(postDto);
	}

	public static GenericPostDto toDto(GenericPost post) {
		return convertToDto(post);
	}

	public static GenericPostCollectionDto toDto(List<GenericPost> posts) {
		return convertToDto(posts);
	}

	public static void copyFieldsFromTo(AbstractPost from, AbstractPost to) {
		to.setType(from.getType());
		to.setId(from.getId());
		to.setText(from.getText());
		to.setDate(from.getDate());

		manageAbstarctPostUserId(from, to);
	}

	private static GenericPostCollectionDto convertToDto(List<GenericPost> posts) {
		GenericPostCollectionDto result = new GenericPostCollectionDto();

		if (posts == null) {
			throw new NullPointerException("No posts found");
		}

		for (GenericPost post : posts) {
			result.getPosts().add(toDto(post));
		}

		return result;
	}

	private static void manageAbstarctPostUserId(AbstractPost from, AbstractPost to) {
		if (from.getUserObjectId() != null) {
			to.setUserId(from.getUserObjectId().toHexString());
			to.setUserObjectId(from.getUserObjectId());
		} else {
			if (from.getUserId() != null) {
				to.setUserObjectId(new ObjectId(from.getUserId()));
				to.setUserId(from.getUserId());
			}
		}
	}

	private static GenericPostDto convertToDto(GenericPost post) {
		String className;

		switch (post.getType()) {
			case NEW_POST:
				className = NewPostDto.class.getName();
				break;
			case UNREGISTERED_POST:
				className = UnregisteredPostDto.class.getName();
				break;
			case REGISTERED_POST:
				className = RegisteredPostDto.class.getName();
				break;
			default:
				className = "";
				break;
		}

		GenericPostDto postDto = (GenericPostDto) getNewInstanceFor(className);
		copyFieldsFromTo(post, postDto);

		return postDto;
	}

	private static GenericPost convertFromDto(GenericPostDto postDto) {
		GenericPost post = new GenericPost();
		copyFieldsFromTo(postDto, post);

		return post;
	}

}
