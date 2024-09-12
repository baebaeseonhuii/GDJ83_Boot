package com.seonhui.app.configs.security;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.seonhui.app.members.MemberUserService;

@Configuration
@EnableWebSecurity //기본 config 말고 내가 만든 config 사용
public class SecurityConfig {
	
	// Security
	// 라이브러리 추가 
	// VO 수정
	// Service 수정
	// SecurityConfig 추가
	
	@Autowired
	private SecurityLoginSuccessHandler handler;
	
	@Autowired
	private SecurityLoginFailHandler failHandler;
	
	@Autowired
	private SecurityLogoutSuccessHandler logoutHandler;
	
	@Autowired
	private MemberUserService memberUserService;
	
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
		String message = URLEncoder.encode("로그인실패","UTF-8");
		
		
		
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
							.requestMatchers("/member/add", "/member/login", "/member/check").permitAll()
							.requestMatchers("/member/*").authenticated()
							.anyRequest().permitAll()
							
							
				)
				//form login 관련 설정
				.formLogin(
						login ->
						login
							// 개발자가 만든 로그인 페이지 사용
							.loginPage("/member/login")
							//.defaultSuccessUrl("/") //로그인 성공시
							.successHandler(handler)
							//.failureUrl("/member/login?message=" + message)
							.failureHandler(failHandler)
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
								.logoutSuccessHandler(logoutHandler)
								//.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
								// 로그아웃 당신성공 했을때 보내는 주소
								.logoutSuccessUrl("/")
								.invalidateHttpSession(true) // true 면 세션 만료, false 면 세션 만료 X
								//.deleteCookies("") // 쿠키 삭제하고 싶을 때
				)
				
				//rememberMe
				.rememberMe(
						remember->
							// parameter name
							remember.rememberMeParameter("rememberMe")
							// 토큰의 유효 시간
							.tokenValiditySeconds(60)
							// 토큰에 사용되는 값, 토큰 생성시 값, 필수값, 개발자가 값을 설정
							.key("rememberme")
							// 로그인할 때 인증 절차(로그인) 진행할 UserDetailService (controller로 안가고 이 서비스에서 받음)
							.userDetailsService(memberUserService)
							// 로그인이 성공했을 경우 진행할 핸들러
							.authenticationSuccessHandler(handler)
							.useSecureCookie(false)
						
						)
				
				//동시 세션
				.sessionManagement(
						sessionManager ->
							sessionManager
								// 최대 허용 개수, -1이면 무한대
								.maximumSessions(1)
								// 이전 로그인 나가게 할건지(false), 지금 로그인 시도한 사용자 나가게 할건지(true)
								.maxSessionsPreventsLogin(false)
								// 세션이 만료되었을 경우 리다이렉트 할 페이지 url
								.expiredUrl("/member/check")
								
								
						)
				
				//Social Login
				.oauth2Login(
						oauth2 ->
							oauth2
								.userInfoEndpoint(
										user -> user.userService(memberUserService)
										)
								
						
						)
				
					
		
		; //authorizeHttpRequests 끝부분
		
		
		
		
		return security.build();
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	}
	
}
