package org.pti.poster.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pti.poster.config.JacksonFilterConfig;
import org.pti.poster.model.post.RegisteredPost;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

public class RegisteredPostConverter extends AbstractHttpMessageConverter<RegisteredPost> {
	private JacksonFilterConfig filterConfig;

	public RegisteredPostConverter(JacksonFilterConfig filterConfig, MediaType supportedType) {
		super(supportedType);
		this.filterConfig = filterConfig;
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
		ObjectMapper mapper = new ObjectMapper();
		mapper.writer(filterConfig.getFilters()).writeValue(httpOutputMessage.getBody(), post);
	}
}
