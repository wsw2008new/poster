package org.pti.poster;

import com.mangofactory.swagger.configuration.JacksonSwaggerSupport;
import com.mangofactory.swagger.plugin.EnableSwagger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebMvc
@ComponentScan
@EnableSwagger
public class SpringApplicationConfiguration {
}