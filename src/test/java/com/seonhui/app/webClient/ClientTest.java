package com.seonhui.app.webClient;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.seonhui.app.comments.PostVO;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
@Slf4j
class ClientTest {
	
	@Test
	void webClientListTest(){
		WebClient webClient = WebClient.builder() //builder 패턴
												.baseUrl("https://jsonplaceholder.typicode.com/")
												.build();
		
		Flux<PostVO> res = webClient.get()
								    .uri("posts")
								    .retrieve()
								    .bodyToFlux(PostVO.class);
		res.subscribe(v->{
			PostVO p = v;
			log.info("v: {}", v);
			
		});
		
		
	}
	
	//@Test
	void webClient(){
		WebClient webClient = WebClient.builder() //builder 패턴
												.baseUrl("https://jsonplaceholder.typicode.com/")
												.build();
		
		Mono<PostVO> res = webClient.get()
								    .uri("posts/1")
								    .retrieve()
								    .bodyToMono(PostVO.class);
		PostVO postVO = res.block();
		
		log.info("WebClient: {}", postVO);
		
	}

	
	
	//@Test
	void test1() {	
		
		// RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		
		//parameter 생성
		//하나의 키로 값을 여러 개 넣을 수 있음
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("postId", "1");
		//요청 객체 생성
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params, null);
		
		//요청 전송 응답 처리
		List<PostVO> res = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", List.class);
		
		//PostVO result = res.getBody();
		
		log.info("result: {}", res.size());
		log.info("result: {}", res.get(0));
		
	}

}
