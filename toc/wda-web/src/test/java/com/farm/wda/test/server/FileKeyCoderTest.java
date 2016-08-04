package com.farm.wda.test.server;

import com.farm.wda.Beanfactory;
import com.farm.wda.exception.ErrorTypeException;
import com.farm.wda.service.FileKeyCoderInter;

public class FileKeyCoderTest {

	public static void main(String[] args) throws ErrorTypeException {
		FileKeyCoderInter coder = Beanfactory.getFileKeyCoderImpl();
		System.out.println(coder.parseDir("1"));
		System.out.println(coder.parseFileName("TXT"));
		System.out.println(coder.parseRealPath("1", "TXT"));
		System.out.println(coder.parseFile("1", "HTML"));
	}

}
