package com.seonhui.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seonhui.app.util.Pager;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;



@Controller
@RequestMapping("/qna/*")
@Slf4j
@RestController // 이 클래스 안에 있는 모든 메소드에 ResponseBody가 적용됨
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@Value("${board.qna}")
	private String board;
	
	@ModelAttribute("board")
	public String getBoard() {
		return this.board;
	}
	
	//restful
	//jsp없애고 json형태로 데이터만 보냄 -> 프론트 개발자가 알아서 페이지를 만듦
	//jsp가 없어졌으니까 Model이 더 이상 안필요함
	//restful 서비스는 대체로 이런식임
	@GetMapping("list/{page}")
	@CrossOrigin
	public List<QnaVO> getList(Pager pager)throws Exception{
		//log.info("page: {}", page);
		log.info("pager: {}", pager);
		List<QnaVO> ar = qnaService.getList(pager);
		
		
		return ar;
		
	}
	
	@GetMapping("add")
	public void add(@ModelAttribute QnaVO qnaVO) throws Exception{
		
	}
	
	@PostMapping("add")
	public String add(@Valid QnaVO qnaVO, BindingResult bindingResult , MultipartFile [] attaches) throws Exception{
		if(bindingResult.hasErrors()) {
			log.error("writer 비어있음");
			return "qna/add";
		}
		int result = qnaService.add(qnaVO, attaches);
		return "redirect:./list";
	}
	
	//url형식으로 파라미터를 보낼 때 http://localhost:81/qna/detail/15 이런 식으로 씀
	//GEt 방식일 때는 물음표 대신에 url형식으로 씀
	@GetMapping("detail/{boardNum}/{name}")
	public QnaVO getDetail(@PathVariable(name = "boardNum") Long bn, @PathVariable String name, QnaVO qnaVO) throws Exception{
		//name: VO의 멤버변수명과 파라미터 변수명이 다를 때 VO의 멤버변수명을 적으면 파라미터 변수명이 달라도 넣어줌
		//required: 아무것도 안왔을 때 true면 에러, false면 그냥 들여보내줌
		//value: 파라미터에 아무것도 안들어왔을 때 디폴트값으로 value를 넣어줌
		log.info("boardNum: {}", bn);
		log.info("name: {}", name);
		qnaVO = qnaService.getDetail(qnaVO);
		
		return qnaVO;
	}
	
	@GetMapping("fileDown")
	public String fileDown(QnaFileVO qnaFileVO, Model model) throws Exception{
		qnaFileVO = qnaService.getFileDetail(qnaFileVO);
		model.addAttribute("file", qnaFileVO);
		return "fileDownView";
	}
	
	
}
