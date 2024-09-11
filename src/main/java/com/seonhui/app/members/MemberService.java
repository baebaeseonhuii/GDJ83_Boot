package com.seonhui.app.members;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	//검증 메서드
	public boolean memberValidate( MemberVO memberVO, BindingResult bindingResult) throws Exception{
		boolean check = false;
		//check==false 검증 성공
		//check==true 검증 실패
		
		//0. 기본 검증값 - 어노테이션 검증의 결과
		check = bindingResult.hasErrors();		
		//1. 패스워드 일치 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check = true;
			//에러메세지
			bindingResult.rejectValue("passwordCheck", "memberVO.pw.notEqual");
		}
		
		//2. 아이디 중복검사
		MemberVO result = memberMapper.detail(memberVO);
		if(result != null) {
			check = true;
			bindingResult.rejectValue("username", "memberVO.username.duplication");
		}
		
		
		return check;
	}
	
	public int add(MemberVO memberVO)throws Exception{
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberMapper.add(memberVO);
		
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("roleNum", 1);
		
		result = memberMapper.addRole(map);
		
		return result;
	}
	
	public MemberVO detail(MemberVO memberVO)throws Exception{
		MemberVO result = memberMapper.detail(memberVO);
		if(result != null) {
			if(result.getPassword().equals(memberVO.getPassword())) {
				return result;
			}
		}
		
		return null;
	}
	
	public int addRole(Map<String, Object> map)throws Exception{
		return 0;
	}
	

}
