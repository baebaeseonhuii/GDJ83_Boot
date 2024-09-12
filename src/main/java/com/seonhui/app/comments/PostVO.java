package com.seonhui.app.comments;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
public class PostVO {

	private Long userId;
	private Long id;
	private String title;
	private String body;
	
}
