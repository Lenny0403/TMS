package com.farm.wda.test.rmi;


import com.farm.wda.Beanfactory;

public class RmiServerTest {
	public static void main(String[] args) {
		Beanfactory.startOpenOfficeServer();
		Beanfactory.startRmi();
		Beanfactory.statrtConverter();
	}
}
