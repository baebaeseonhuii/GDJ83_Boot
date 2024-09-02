package com.seonhui.app.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeMapperTest {

	@Autowired
	private NoticeMapper noticeDAO;
	
	@Test
	void getListTest() throws Exception {
		List<NoticeVO> ar = noticeDAO.getList();
		assertNotEquals(0, ar.size());
		for(NoticeVO vo: ar) {
			System.out.println(vo.toString());
		}
	}
	
//	@Test
//	void addNoticeTest() throws Exception {
//		
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoard_num(4L);
//		noticeVO.setBoard_title("hey");
//		noticeVO.setBoard_writer("seoni");
//		noticeVO.setBoard_contents("hey");
//		System.out.println(noticeVO.toString());
//		
//		int result = noticeDAO.addNotice(noticeVO);
//		
//		assertEquals(1, result);
//	}

}
