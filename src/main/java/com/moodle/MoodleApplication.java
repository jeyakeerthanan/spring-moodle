package com.moodle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.moodle.*" })
@EntityScan(basePackages = { "com.moodle.entities" })
@EnableJpaRepositories(basePackages = { "com.moodle.repositories" })
@EnableTransactionManagement
public class MoodleApplication {

	public static void main(String[] args) {
		System.out.println("hi");
		SpringApplication.run(MoodleApplication.class, args);
	}

}
