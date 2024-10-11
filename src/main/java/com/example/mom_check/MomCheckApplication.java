package com.example.mom_check;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MomCheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(MomCheckApplication.class, args);
	}

}
