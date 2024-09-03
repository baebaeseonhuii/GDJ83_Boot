package com.seonhui.app.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@GetMapping("/")
	public String home()throws Exception {
		
		// 세밀한 추적
		log.trace("trace");
		// 디버그
		log.debug("debug");
		// 일반적인 정보
		log.info("info");
		// 문제가 생길 경우 경보
		log.warn("warning");
		// 에러 발생 시
		log.error("error");
		
		return "index";
	}
	
}
