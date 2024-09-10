package com.seonhui.app.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //기본 config 말고 내가 만든 config 사용
public class SecurityConfig {
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() throws Exception {
		//Security에서 무시할 것들
		return web -> web
						.ignoring()
						.requestMatchers("/images/**")
						.requestMatchers("/css/**")
						.requestMatchers("/js/**")
						.requestMatchers("/vendors/**")
						.requestMatchers("/favicon/**")
						
						;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
		
		security
				.cors()
				.and()
				.csrf()
				.disable();
		
		//권한에 관련된 설정
		security.authorizeHttpRequests(
					(authorizeRequest)->
						authorizeRequest
							.requestMatchers("/").permitAll()
							.requestMatchers("/qna/list").permitAll()
							.requestMatchers("/qna/*").authenticated()
							.requestMatchers("/notice/list", "/notice/detail").permitAll()
							.requestMatchers("/notice/*").hasRole("ADMIN")
							.requestMatchers("/manager/*").hasAnyRole("MANAGER", "ADMIN")
							.requestMatchers("/member/add", "/member/login").permitAll()
							.requestMatchers("/member/*").authenticated()
							.anyRequest().permitAll()
							
							
				)
				//form login 관련 설정
				.formLogin(
						login ->
						login
							.loginPage("/member/login")
							.defaultSuccessUrl("/") //로그인 성공시
							.failureUrl("/member/login")
							// 파라미터 이름이 username이 아니고 id로 사용한 경우
							//.usernameParameter("id") 
							// 파라미터 이름이 password가 아니고 pw로 사용한 경우
							//.passwordParameter("pw")
							.permitAll()
							
							
							)
					
		
		; //authorizeHttpRequests 끝부분
		
		
		
		
		return security.build();
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	}
	
}
