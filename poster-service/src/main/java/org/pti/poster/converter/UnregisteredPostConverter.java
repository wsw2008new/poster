package org.pti.poster.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pti.poster.model.post.UnregisteredPost;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Map;

public class UnregisteredPostConverter extends AbstractHttpMessageConverter<UnregisteredPost> {
	public UnregisteredPostConverter(MediaType supportedType) {
		super(supportedType);
	}

	@Override
	protected boolean supports(Class<?> aClass) {
		return UnregisteredPost.class.equals(aClass);
	}

	@Override
	protected UnregisteredPost readInternal(Class<? extends UnregisteredPost> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
		ObjectMapper mapper = new ObjectMapper();
		Map jsonMap = mapper.readValue(httpInputMessage.getBody(), Map.class);
		String text = (String) jsonMap.get("text");
		return new UnregisteredPost(text);
	}

	@Override
	protected void writeInternal(UnregisteredPost post, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

	}
}
