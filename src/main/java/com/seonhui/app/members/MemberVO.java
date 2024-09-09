package com.seonhui.app.members;

import java.sql.Date;
import java.util.List;

import com.seonhui.app.validate.MemberAddGroup;
import com.seonhui.app.validate.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberVO {
	
	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String username;
	
	private String passwordCheck;
	
	@Pattern(groups = {MemberAddGroup.class}, regexp = "/^(?=.*[a-zA-Z])(?=.*[0-9]).{5,15}$/")
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

}