package com.seonhui.app.ioc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.seonhui.app.robot.Robot;

@SpringBootTest
class iocTest {
	
	@Autowired
	private Robot robot;

	@Test
	void test() {
		System.out.println("Test Case");
		robot.getRobotArm().punch();
		assertNotNull(robot);
	}

}
