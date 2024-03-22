package com.server.aeye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AeyeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AeyeApplication.class, args);
	}

}
