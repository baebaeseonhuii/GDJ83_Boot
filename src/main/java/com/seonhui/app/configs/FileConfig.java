package com.seonhui.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//WebMVCConfigurer 구현: 웹 설정에 관련된 것들
//설정 class, xml 느낌
@Configuration
public class FileConfig implements WebMvcConfigurer{
	
	//properties 파일에서 설정한 값  
	@Value("${app.url.path}")
	private String url;
	
	@Value("${app.upload.location}")
	private String file;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(url)
				.addResourceLocations(file);
		//url이라는 자원이 들어왔을 때 file에서 찾으시오
	}
	
}
