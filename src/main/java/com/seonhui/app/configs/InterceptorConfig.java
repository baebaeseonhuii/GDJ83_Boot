package com.seonhui.app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.seonhui.app.home.interceptors.AdminCheckInterceptor;
import com.seonhui.app.home.interceptors.LoginInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	@Autowired
	private AdminCheckInterceptor adminCheckInterceptor;
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

//	어떤 url이 왔을 때, 어떤 Interceptor를 실행할 것인가
//	/qna/list 라는 url이 왔을 때 loginInterceptor를 거치게 하자
		
		registry.addInterceptor(loginInterceptor)
		.addPathPatterns("/qna/*")
		.excludePathPatterns("/qna/list");

		registry.addInterceptor(adminCheckInterceptor)
		.addPathPatterns("/admin/*"); 

		registry.addInterceptor(localeChangeInterceptor)
		.addPathPatterns("/**");

	}


	
	
}