package com.farm.wda.test.util;

import java.util.Map.Entry;
import java.util.Set;

import com.farm.wda.util.ConfUtils;

public class ConfText {

	public static void main(String[] args) {
		Set<String> set = ConfUtils.getAcceptTypes();
		for (String type : set) {
			System.out.println(type);
		}
		for (Entry<String, String> node : ConfUtils.getTargetTypes().entrySet()) {
			System.out.println("key:" + node.getKey());
			System.out.println("value:" + node.getValue());
		}
	}

}
