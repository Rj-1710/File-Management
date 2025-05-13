package com.mphasis.parent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAutoConfiguration(exclude={BatchAutoConfiguration.class})
@SpringBootApplication
@ComponentScan(basePackages= "com.mphasis.parent")
@EntityScan(basePackages= "com.mphasis.parent.entity")
@EnableJpaRepositories(basePackages = "com.mphasis.parent.")
public class ParentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParentApplication.class, args);
	}

}