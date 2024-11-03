package com.likelion.lookie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class LookieApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.likelion.lookie.LookieApplication.class, args);
	}

}

