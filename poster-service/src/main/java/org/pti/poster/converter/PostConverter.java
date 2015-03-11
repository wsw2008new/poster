package org.pti.poster.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pti.poster.model.post.BasicPost;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Map;

public class PostConverter extends AbstractHttpMessageConverter<BasicPost> {
	public PostConverter(MediaType supportedType) {
		super(supportedType);
	}

	@Override
	protected boolean supports(Class<?> aClass) {
		return BasicPost.class.equals(aClass);
	}

	@Override
	protected BasicPost readInternal(Class<? extends BasicPost> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
		ObjectMapper mapper = new ObjectMapper();
		Map jsonMap = mapper.readValue(httpInputMessage.getBody(), Map.class);
		String text = (String) jsonMap.get("text");
		return new BasicPost("1", text);
	}

	@Override
	protected void writeInternal(BasicPost basicPost, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

	}
}
