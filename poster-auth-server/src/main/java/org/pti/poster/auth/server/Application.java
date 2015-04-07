package org.pti.poster.auth.server;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.StringWriter;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void setUpTokenDatasource() {
	Resource resource = new ClassPathResource("db_schema");
	String query = "";

	try {
	    StringWriter writer = new StringWriter();
	    IOUtils.copy(resource.getInputStream(), writer, "UTF-8");
	    query = writer.toString();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	System.out.println(query);
	jdbcTemplate.execute(query);
    }

    public static void main(String[] args) {
	ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }
}
