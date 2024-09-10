package com.seonhui.app.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seonhui.app.qna.QnaController;
import com.seonhui.app.validate.MemberAddGroup;
import com.seonhui.app.validate.MemberUpdateGroup;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("update")
	public String update(HttpSession session, Model model)throws Exception{
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		model.addAttribute("memberVO", memberVO);
		return "member/update";
	}
	
	@PostMapping("update")
	public String update(@Validated(MemberUpdateGroup.class) MemberVO memberVO, BindingResult bindingResult) throws Exception{
		if(bindingResult.hasErrors()) {
			return "member/update";
		}
		
		return "redirect:./mypage";
		
	}
	
	@GetMapping("mypage")
	public void mypage()throws Exception{
		
	}
	
	//add
	@GetMapping("add")
	public void add(MemberVO memberVO) throws Exception {
		//model.addAttribute("memberVO", new MemberVO());
		
	}
	
	@PostMapping("add")
	public String add(@Validated(MemberAddGroup.class) MemberVO memberVO, BindingResult bindingResult) throws Exception {
		boolean check = memberService.memberValidate(memberVO, bindingResult);
		if(check) {
			return "member/add";
		}
		//int result = memberService.add(memberVO);
		
//		if(bindingResult.hasErrors()) {
//			log.error("username 비어있음");
//			return "member/add";
//		}
		
		return "redirect:../";
	}
	
	
	
	//detail
	
	@GetMapping("login")
	public void login() throws Exception {
		
	}
	
//	@PostMapping("login")
//	public String login(MemberVO memberVO, HttpSession session) throws Exception {
//		memberVO = memberService.detail(memberVO);
//		
//		if(memberVO != null) {
//			session.setAttribute("member", memberVO);
//		}
//		
//		return "redirect:../";
//	}
	
	//logout
	@GetMapping("logout")
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:../";
	}
	
	
}
