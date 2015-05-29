package org.pti.poster.server.appserver;

import com.mongodb.Mongo;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import org.pti.poster.PosterAppServerApplicationConfiguration;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@Import(value = PosterAppServerApplicationConfiguration.class)
@EnableMongoRepositories("org.pti.poster.repository")
public class AppServer {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AppServer.class, args);
	}

	@Value("localhost:27017, localhost:27018, localhost:27019, localhost:27020")
	private ServerAddress[] serverAddressList;

	@Bean
	public Mongo mongo() throws Exception {
		Mongo mongo = new Mongo(Arrays.asList(serverAddressList));
		mongo.setReadPreference(ReadPreference.secondaryPreferred());
		WriteConcern writeConcern = new WriteConcern(2, 5000);
		mongo.setWriteConcern(writeConcern);
		return mongo;
	}

	@Bean
	public static CustomEditorConfigurer customEditorConfigurer() {
		CustomEditorConfigurer configurer = new CustomEditorConfigurer();
		configurer.setPropertyEditorRegistrars(
				new PropertyEditorRegistrar[]{new ServerAddressPropertyEditorRegistrar()});
		return configurer;
	}
}