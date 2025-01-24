package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "entity")
public class JpagradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpagradleApplication.class, args);
	}

}
