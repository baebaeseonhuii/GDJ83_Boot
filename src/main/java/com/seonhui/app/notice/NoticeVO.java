package com.seonhui.app.notice;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// DTO: Data Transfer Object
// VO: Value Object (굳이 구분하자면 읽기 전용 -> 값이 바뀌지 않음)

//@Data -> 아래의 모든 annotation을 전부 써줌
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString // 변수 안에 들어간 값을 확인할 때 하나하나 sysout 안해서 toString 호출하면 변수 확인 가능

@Data
public class NoticeVO {
	
	private Long boardNum;
	private String boardWriter;
	private String boardTitle;
	private String boardContents;
	private Date createDate;
	
	
	
}
