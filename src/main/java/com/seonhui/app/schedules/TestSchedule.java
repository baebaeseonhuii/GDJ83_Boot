package com.seonhui.app.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.seonhui.app.qna.QnaMapper;
import com.seonhui.app.qna.QnaVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	
	@Autowired
	private QnaMapper qnaMapper;

	
	//@Scheduled(fixedDelay = 1000, initialDelay = 2000)
	public void test1() throws Exception{
		//실행 후 종료까지 약 2초 걸림
		//해당 메서드가 종료되고나서 1초 딜레이
		log.error("Schedule test");
		
		
	}
	
	
	//@Scheduled(fixedRate = 2000, initialDelayString = "3000")
	public void test2() throws Exception{
		//메서드의 종료와 관계없이 바로실행됨
		//멀티쓰레드 개념이랑 잘 쓰임
		log.error("=============Schedule test==============");
	}
	//fixedDelay&fixedRate 차이점
	//
	
	@Scheduled(cron = "*/5 * * * * *")
	public void test3() throws Exception {
		log.error("=============Schedule test==============");
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardWriter("test");
		qnaVO.setBoardTitle("title");
		qnaVO.setBoardContents("Contents");
		qnaMapper.add(qnaVO);
		qnaMapper.refUpdate(qnaVO);
	}
	
}
