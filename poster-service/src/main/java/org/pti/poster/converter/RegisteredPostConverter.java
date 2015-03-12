package org.pti.poster.converter;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.pti.poster.filter.PostFilter;
import org.pti.poster.model.post.Post;
import org.pti.poster.model.post.RegisteredPost;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

public class RegisteredPostConverter extends AbstractHttpMessageConverter<RegisteredPost> {
	public RegisteredPostConverter(MediaType supportedType) {
		super(supportedType);
	}

	@Override
	protected boolean supports(Class<?> aClass) {
		return RegisteredPost.class.equals(aClass);
	}

	@Override
	protected RegisteredPost readInternal(Class<? extends RegisteredPost> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
		return null;
	}

	@Override
	protected void writeInternal(RegisteredPost post, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
		PostFilter postFilter = new PostFilter();
		FilterProvider filters = new SimpleFilterProvider().addFilter("postFilter", postFilter);

		ObjectMapper mapper = new ObjectMapper();
		mapper.writer(filters).writeValue(httpOutputMessage.getBody(), post);
	}
}
