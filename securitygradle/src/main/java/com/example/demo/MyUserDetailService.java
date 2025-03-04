package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("MyUserDetailService : loadUserByUsername " + username);
		System.out.println(encoder.encode("1111"));
		System.out.println(encoder.encode("1111"));
		System.out.println(encoder.encode("1111"));
		System.out.println(encoder.matches("1111", encoder.encode("1111")));
		
		if(username.equalsIgnoreCase("admin")) {
			return User.builder()
					.username(username)
					.password(encoder.encode("1111"))
					.roles(UserRole.ADMIN.name())
					.build();			
		} else {
			return User.builder()
					.username(username)
					.password(encoder.encode("1111"))
					.roles(UserRole.USER.name())
					.build();
		}
	}

}
