package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@ComponentScan(basePackages = "annotation.springmvc")
@ComponentScan(basePackages = "boardmapper")
@ComponentScan(basePackages = "myconfig")
@MapperScan(basePackages = "boardmapper")

public class DemogradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemogradleApplication.class, args);
	}

}
