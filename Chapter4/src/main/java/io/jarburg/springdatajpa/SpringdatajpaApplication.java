package io.jarburg.springdatajpa;

import io.jarburg.springdatajpa.entities.User;
import io.jarburg.springdatajpa.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class SpringdatajpaApplication {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(SpringdatajpaApplication.class, args);
		//		builder(args);
	}

	private static void builder(String[] args) {
		SpringApplication ctx = new SpringApplicationBuilder(SpringdatajpaApplication.class)
				.properties(
						"spring.application.name=springdatajpa",
						"spring.main.banner-mode=off",
						"spring.datasource.url=jdbc:mysql://localhost/spring",
						"spring.datasource.username=root",
						"spring.datasource.password=",
						"spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect",
						"spring.jpa.show-sql=true",
						"spring.jpa.hibernate.ddl-auto=create"
				)
				.build();
		ctx.run(args);
	}

//	@Bean
//	public ApplicationRunner configure(UserRepository userRepository) {
//		return env -> {
//			User u1 = new User("Marika", LocalDate.of(2020, Month.AUGUST, 3));
//			User u2 = new User("Radagon", LocalDate.of(2020, Month.DECEMBER, 22));
//			// check if User u1 is already present
//			userRepository.save(u1);
//			userRepository.save(u2);
//			userRepository.findAll().forEach(System.out::println);
//		};
//	}

}
