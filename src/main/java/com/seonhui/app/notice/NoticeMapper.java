package com.seonhui.app.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper //DAO는 class로 만들지 않고 interface로 만듦
public interface NoticeMapper {
	
	public List<NoticeVO> getList() throws Exception;
	
	public int addNotice(NoticeVO noticeVO) throws Exception;
}
