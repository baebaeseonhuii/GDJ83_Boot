package com.seonhui.app.lambdas;

public class Plus implements MyFunction{
	
	@Override
	public int calc(int num1, int num2) throws Exception {
		int result = num1+num2;
		return result;
	}

}
