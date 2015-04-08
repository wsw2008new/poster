package org.pti.poster.boot;


import org.pti.poster.SpringApplicationConfiguration;
import org.pti.poster.security.hibernate.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.InputStreamReader;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@Import(value = SpringApplicationConfiguration.class)
public class Application extends WebMvcAutoConfiguration {

	@Autowired
	private DataSource dataSource;

//	@PostConstruct
//	public void setUpTokenDatasource() {
//		Resource resource = new ClassPathResource("db_schema");
//
//		try {
//			ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection(), true, true);
//			scriptRunner.runScript(new InputStreamReader(resource.getInputStream()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}

}