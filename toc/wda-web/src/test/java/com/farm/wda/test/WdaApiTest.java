package com.farm.wda.test;

import java.io.File;
import java.rmi.RemoteException;

import com.farm.wda.Beanfactory;
import com.farm.wda.exception.ErrorStopedException;
import com.farm.wda.exception.ErrorTypeException;
import com.farm.wda.inter.WdaAppInter;

public class WdaApiTest {

	/**
	 * 该测试方法已经无法使用了
	 * 
	 * @param args
	 * @throws ErrorTypeException
	 * @throws InterruptedException
	 * @throws ErrorStopedException
	 * @throws RemoteException
	 */
	public static void main(String[] args)
			throws ErrorTypeException, InterruptedException, ErrorStopedException, RemoteException {
		WdaAppInter wad = Beanfactory.getWdaAppImpl();
		wad.generateDoc("asdfacxcxwerwedsfd", new File("d:\\doc\\3.xls"), "测试文档","权限id");
		while (true) {
			Thread.sleep(1000);
			System.out.println(wad.isGenerated("asdfacxcxwerwedsfd", "TXT"));
			System.out.println(wad.isGenerated("asdfacxcxwerwedsfd", "HTML"));
			try {
				System.out.println(wad.getText("asdfacxcxwerwedsfd"));
			} catch (Exception e) {
				System.out.println("出错");
			}
		}
	}

}
