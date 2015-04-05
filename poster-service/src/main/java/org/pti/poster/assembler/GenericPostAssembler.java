package org.pti.poster.assembler;

import org.pti.poster.dto.post.GenericPostDto;
import org.pti.poster.dto.post.RegisteredPostDto;
import org.pti.poster.dto.post.UnregisteredPostDto;
import org.pti.poster.model.AbstractPost;
import org.pti.poster.model.post.GenericPost;

import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class GenericPostAssembler {

	public static GenericPost fromDto(GenericPostDto postDto) {
		return convertFromDto(postDto);
	}

	public static GenericPostDto toDto(GenericPost post) {
		return convertToDto(post);
	}

	public static List<GenericPostDto> toDto(List<GenericPost> posts) {
		List<GenericPostDto> result = new ArrayList<>();

		for (GenericPost post : posts) {
			result.add(toDto(post));
		}

		return result;
	}

	private static GenericPostDto convertToDto(AbstractPost post) {
		String className;

		switch (post.getType()) {
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

		return convertToDto(className, post);
	}

	private static GenericPostDto convertToDto(String className, AbstractPost post) {
		GenericPostDto postDto = null;

		if (!className.isEmpty()) {
			Class<?> clazz;
			try {
				clazz = Class.forName(className);
				Constructor<?> ctor = clazz.getConstructor(getConstructorArgumentClasses(post));
				postDto = (GenericPostDto) ctor.newInstance(getConstructorArguments(post));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return postDto;
	}

	private static GenericPost convertFromDto(GenericPostDto postDto) {
		GenericPost post = null;
		String className = GenericPost.class.getName();

		if (!className.isEmpty()) {
			Class<?> clazz;
			try {
				clazz = Class.forName(className);
				Class[] constructorArgumentClasses = getConstructorArgumentClasses(postDto);
				Constructor<?> ctor = clazz.getConstructor(constructorArgumentClasses);
				post = (GenericPost) ctor.newInstance(getConstructorArguments(postDto));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return post;
	}


	private static Object[] getConstructorArguments(AbstractPost post) {
		List<Object> args = new ArrayList<>();

		args.add(post.getType());
		args.add(post.getId());
		args.add(post.getDate());
		args.add(post.getUserId());
		args.add(post.getText());

		return args.toArray();
	}

	private static Class[] getConstructorArgumentClasses(AbstractPost post) {
		List<Class> args = new ArrayList<>();

		args.add(post.getType().getClass());
		args.add(post.getId().getClass());
		args.add(post.getDate().getClass());
		args.add(post.getUserId().getClass());
		args.add(post.getText().getClass());

		return args.toArray(new Class[args.size()]);
	}

}
