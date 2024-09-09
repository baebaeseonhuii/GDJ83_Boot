package com.seonhui.app.configs;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// 어떤 언어로 왔을 때 어떻게 처리할 건지
@Configuration
public class MessageConfig implements WebMvcConfigurer{

	@Bean
	public LocaleResolver localeResolver() {
		// 1. Session - 로그인 나가면 없어짐
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);
		
		// 2. Cookie - 정해진 시간 안에는 살아있음
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
		
		
		return cookieLocaleResolver;
	}

    @Bean
    LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("lang");
		
		//parameter를 받아서 언어를 구분할 것이다.
		//url?lang=en 이런식으로 올 것
		
		
		return changeInterceptor;
	}
	
}
