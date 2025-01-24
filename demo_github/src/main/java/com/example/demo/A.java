package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class A {
@RequestMapping("/a")
public String test() {
	return "sample";// 
	//리액트실행서버(node : 3000->4000)- ajax-   스프링부트실행서버(TOMCAT : 8080->9090)
}
}
