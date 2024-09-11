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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity //기본 config 말고 내가 만든 config 사용
public class SecurityConfig {
	
	// Security
	// 라이브러리 추가 
	// VO 수정
	// Service 수정
	// SecurityConfig 추가
	
	
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
		security
			.authorizeHttpRequests(
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
							// 개발자가 만든 로그인 페이지 사용
							.loginPage("/member/login")
							.defaultSuccessUrl("/") //로그인 성공시
							.failureUrl("/member/login")
							// 파라미터 이름이 username이 아니고 id로 사용한 경우
							//.usernameParameter("id") 
							// 파라미터 이름이 password가 아니고 pw로 사용한 경우
							//.passwordParameter("pw")
							.permitAll()
							
							
							)
				
				// logout설정
				.logout(
						logout -> 
							logout
								// RequestMatcher라는 객체 타입 ("url"), 로그아웃 url 지정
								// 두개 다 같음
								.logoutUrl("/member/logout")
								//.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
								// 로그아웃 당신성공 했을때 보내는 주소
								.logoutSuccessUrl("/")
								.invalidateHttpSession(true) // true 면 세션 만료, false 면 세션 만료 X
								//.deleteCookies("") // 쿠키 삭제하고 싶을 때
				)
				
					
		
		; //authorizeHttpRequests 끝부분
		
		
		
		
		return security.build();
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	}
	
}
