package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//스프링 시작 클래스-main메소드 존재
@SpringBootApplication
//@ComponentScan(basePackages = "com.example.demo")
@ComponentScan
//@ComponentScan(basePackages = "annotation.springmvc")
//@ComponentScan(basePackages = "myconfig")
@ComponentScan(basePackages = "boardmapper")
@ComponentScan(basePackages = "react.ajax")
//@MapperScan(basePackages = "annotation.springmvc.mybatis")
@MapperScan(basePackages = "boardmapper")
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("스프링부트서버 시작");
		SpringApplication.run(DemoApplication.class, args);
	}
}
