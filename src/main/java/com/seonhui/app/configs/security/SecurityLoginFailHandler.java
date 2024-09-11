package com.seonhui.app.configs.security;

import java.io.IOException;
import java.net.URLEncoder;



import org.apache.ibatis.javassist.expr.Instanceof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.seonhui.app.members.MemberMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SecurityLoginFailHandler implements AuthenticationFailureHandler{
	
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String message = "로그인 실패";
		
		log.error("Exception: {}", exception);
		
		if(exception instanceof BadCredentialsException) {
			//비번이 틀림
			message = "비밀번호를 확인하세요.";
		} 
		if(exception instanceof InternalAuthenticationServiceException) {
			//아이디가 틀림
			message = "없는 아이디입니다.";
		} 
		if(exception instanceof AccountExpiredException) {
			// isAccountNonExpired false
			// 계정 유효기간 만료
			message = "만료된 계정입니다. 관리자에게 문의";
		} 
		if(exception instanceof LockedException) {
			// isAccountNonLocked false
			// 계정 잠김
			message = "계정이 잠겨있습니다. 관리자에게 문의";
		} 
		if(exception instanceof CredentialsExpiredException) {
			// isCredentialsNonExpired false
			// 비번의 유효기간 만료
			message = "비밀번호의 유효기간이 만료되었습니다.";
		} 
		if(exception instanceof DisabledException) {
			// isEnabled false
			// 휴면 계정
			message = "휴면 계정입니다. 관리자에게 문의";
		}
		message = URLEncoder.encode(message, "UTF-8");
		
	
		response.sendRedirect("/member/login?message="+message);
		
	}
	
	
}
