package org.pti.poster.config;

import org.pti.poster.converter.RegisteredPostConverter;
import org.pti.poster.converter.UnregisteredPostConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters) {
		httpMessageConverters.add(new RegisteredPostConverter(MediaType.APPLICATION_JSON));
		httpMessageConverters.add(new UnregisteredPostConverter(MediaType.APPLICATION_JSON));
	}
}
