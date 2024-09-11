package com.seonhui.app.members;

import java.util.Enumeration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
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
	
	
	@GetMapping("check")
	public void check() throws Exception{
		log.info("동시세션");
	}
	
	
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
	public void mypage(HttpSession session)throws Exception{
		Enumeration<String> en = session.getAttributeNames();
		while(en.hasMoreElements()) {
			String name = en.nextElement();
			log.info("Name: {} ", name); // SPRING_SECURITY_CONTEXT 
		}
		
		// 로그인한 사용자의 정보를 확인하고 싶을 때
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		log.info("OBJ : {} ", obj.getClass());
		SecurityContextImpl sc = (SecurityContextImpl)obj;
		log.info("Security Context: {}", sc);
		
		SecurityContext context = SecurityContextHolder.getContext();
		log.info("context: {}", context);
		
		Authentication authentication= context.getAuthentication();
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		
		log.info("MemberVO: {}", memberVO);
		log.info("Name: {}", authentication.getName());
		
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
		int result = memberService.add(memberVO);
		
//		if(bindingResult.hasErrors()) {
//			log.error("username 비어있음");
//			return "member/add";
//		}
		
		return "redirect:../";
	}
	
	
	
	//detail
	
	@GetMapping("login")
	public String login(String message, Model model) throws Exception {
		model.addAttribute("message", message);
		
		// 뒤로가기 방지
		
		SecurityContext context = SecurityContextHolder.getContext();
		log.info("context: {}", context);
		
		if(context == null) {
			return "member/login";
		}
		
		String user = context.getAuthentication().getPrincipal().toString();
		if(user.equals("anonymousUser")) {
			return "member/login";
		}
		log.info("user: {}", user);
		
		return "redirect:/";
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
//	@GetMapping("logout")
//	public String logout(HttpSession session) throws Exception{
//		session.invalidate();
//		return "redirect:../";
//	}
	
	
}
