package com.seonhui.app.robot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// xml 대신에 자바를 이용해서 객체를 생성

@Configuration  // xml 역할을 하는 annotation
public class RobotConfig {
	
	// <bean class=""> 의 역할을 함. 자바로 빈 객체를 만듦. annotation으로 Bean을 씀. 소괄호 안에 아무것도 없으면 클래스의 소문자로 이름이 만들어짐(robotArm)
	// 객체가 생성되면 스프링풀(Spring Pool)에 보관함. 다른 클래스에서 autowired해서 호출할 때마다 풀에서 꺼내짐
	@Bean("ra")
	RobotArm makeArm() {
		return new RobotArm();
	}
	
	
	@Bean
	Robot makeRobot() {
		Robot robot = new Robot();
		robot.setRobotArm(makeArm());
		return robot;
	}
	
}
