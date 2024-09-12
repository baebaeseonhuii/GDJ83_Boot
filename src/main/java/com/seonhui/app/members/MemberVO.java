package com.seonhui.app.members;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.seonhui.app.validate.MemberAddGroup;
import com.seonhui.app.validate.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberVO implements UserDetails, OAuth2User{
	
	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String username;
	
	private String passwordCheck;
	
	//@Pattern(groups = {MemberAddGroup.class}, regexp = "/^(?=.*[a-zA-Z])(?=.*[0-9]).{5,15}$/")
	@NotBlank(groups = {MemberAddGroup.class})
	private String password;
	
	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String name;
	@Email(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String email;
	@Past(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private Date birth;
	private boolean enabled;
	private List<RoleVO> vos;
	

	//Oauth2User
	//user의 토큰 정보 저장
	private Map<String, Object> attributes;
	
	
	private String accessToken;
	
	//sns 인지 일반로그인인지 구분
	private String sns;
	
	
	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(RoleVO roleVO: vos) {
			GrantedAuthority authority = new SimpleGrantedAuthority(roleVO.getRoleName());
			authorities.add(authority);
		}
		
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		// 사용자 계정의 유효 기간이 만료 되었습니다.
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		// 사용자 계정이 잠겨 있습니다.
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		// 자격 증명 유효 기간이 만료되었습니다.
		return true;
	}
	
	
	
	
	

}