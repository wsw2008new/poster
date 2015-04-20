package org.pti.poster;

import com.mangofactory.swagger.plugin.EnableSwagger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableSwagger
public class SpringApplicationConfiguration {
}