package com.aiqrango;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.aiqrango.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AiqRangoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiqRangoApiApplication.class, args);
	}

}
