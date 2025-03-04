package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	//
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		System.out.println("SecurityConfig - SecurityFilterChain");
		// 403 오류 --- 404없어 / 405 겟포스트 / 400파라미터타입 / 500 서버상아몰랑오류 / 시큐리티는 403오류로 처리
		return httpSecurity
				.authorizeHttpRequests(
				request -> request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers("/b","/error","login").permitAll()
					.requestMatchers("/a","/","c").authenticated()
					.requestMatchers("/adminpage").hasRole(UserRole.ADMIN.name())
					.requestMatchers("/userpage").hasRole(UserRole.USER.name())
					//.requestMatchers("/userpage").hasRole(UserRole.ADMIN.name())
					//.anyRequest().permitAll()
					//ADMIN, USER
			   )
			   //.formLogin(Customizer.withDefaults()) // 시큐리티 내장로그인페이지 그대로 사용 설정
			   .formLogin(login -> login.loginPage("/login") // 1.컨트롤러 정의
									    .loginProcessingUrl("/login-process") //2.폼태그안의 액션
									    //UserDetailService
									    .usernameParameter("userid")
									    .passwordParameter("pw")
									    .defaultSuccessUrl("/", true)
			   )
			   .logout(Customizer.withDefaults()) // logout 시큐리티 내장로그아웃기능 그대로 사용 설정
			   .csrf(csrf -> csrf.disable())
			.build();
	}

}
