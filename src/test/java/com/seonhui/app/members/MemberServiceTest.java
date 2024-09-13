package com.seonhui.app.members;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.seonhui.app.notice.NoticeVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MemberServiceTest {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Test
	void passwordUpdateTest() throws Exception {
		// user 1234 -> user로 비번바꾸기
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("user");
		memberVO.setPassword("1234");
		String newpassword="1234";
		MemberVO check = memberMapper.detail(memberVO);
		
		log.info("Member VO: {}", memberVO);
		log.info("Check: {}", check);
		
		if(passwordEncoder.matches(memberVO.getPassword(), check.getPassword())) {
			log.info("일치합니다.");
		}
		
		if(check.getPassword().equals(memberVO.getPassword())) {
			log.info("일치한다");
		}
		
		assertEquals(check.getPassword(), memberVO.getPassword());
		
	}
	
	//@Test
	void test() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("user");
		memberVO.setPassword(passwordEncoder.encode("1234"));
		int result = memberMapper.pwUpdate(memberVO);
		assertEquals(1, result);
	}
	
}
