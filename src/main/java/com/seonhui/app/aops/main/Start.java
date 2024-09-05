package com.seonhui.app.aops.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seonhui.app.aops.pays.Card;
import com.seonhui.app.aops.transfers.Transfer;

@Component
public class Start {
	
	@Autowired
	private Transfer transfer;
	@Autowired
	private Card card;
	
	public void go() {
		
		transfer.takeBus(44);
		transfer.takeSubway(11L,"졸려");
		transfer.walk();
		
	}

}