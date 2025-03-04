package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@RequestMapping("/a")
	@ResponseBody
	public String a() {
		System.out.println("/a 실행중");
		return "시큐리티인증통과";
	}
	
	@RequestMapping("/b")
	@ResponseBody
	public String b() {
		System.out.println("/b 실행중");
		return "인증없이사용가능";
	}
	
	@RequestMapping("/") // 인증 후
	//@ResponseBody
	public String c(Authentication auth, Model model) {
		System.out.println("/ 실행중");
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(auth.getName());
		model.addAttribute("loginid", auth.getName());
		model.addAttribute("loginrole", auth.getAuthorities());
		//return auth.getName() + " 님 로그인하셨습니다.";
		return "start";
	}
	
	@RequestMapping("/login")
	public String loginpage() {
		System.out.println("/login 실행중");
		return "login";
	}
	
	@RequestMapping("/adminpage")
	public String adminpage() {
		System.out.println("/adminpage 실행중");
		return "adminpage";
	}
	
	@RequestMapping("/userpage")
	public String userpage() {
		System.out.println("/userpage 실행중");
		return "userpage";
	}
}
