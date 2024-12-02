package com.api.products.productapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootProductApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProductApiApplication.class, args);
	}

}
