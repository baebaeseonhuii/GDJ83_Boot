package com.seonhui.app.members;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

//repository 말고 mapper를 쓰는 이유: mapper 어노테이션이 xml파일의 위치도 지정해줌(properties에서도 지정)
@Mapper
public interface MemberMapper {
	
	//interface 쓰는 이유
	
	public int add(MemberVO memberVO)throws Exception;
	
	public MemberVO detail(MemberVO memberVO)throws Exception;
	
	public int addRole(Map<String, Object> map)throws Exception;
	
	public int pwUpdate(MemberVO memberVO) throws Exception;
	
}
