package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// 스프링 시작 클래스 -main 메소드 존재
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("스프링부트서버 시작");
		SpringApplication.run(DemoApplication.class, args);
	}

}
