package com.seonhui.app.lambdas;

@FunctionalInterface //메서드가 한개만 있도록 보장
public interface MyFunction {
	
	public int calc(int num1, int num2) throws Exception;
	
	

}
