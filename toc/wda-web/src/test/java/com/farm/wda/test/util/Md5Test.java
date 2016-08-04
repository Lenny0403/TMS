package com.farm.wda.test.util;

import com.farm.wda.util.Md5Utils;

public class Md5Test {

	public static void main(String[] args) {
		System.out.println(Md5Utils.MD5("231231231"));
		System.out.println(Md5Utils.MD5("231231232"));
		System.out.println(Md5Utils.MD5("231231233"));
		System.out.println(Md5Utils.MD5("231231234"));
		System.out.println(Md5Utils.MD5("2312312315"));
	}

}
