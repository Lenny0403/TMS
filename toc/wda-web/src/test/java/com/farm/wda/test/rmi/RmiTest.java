package com.farm.wda.test.rmi;

import java.io.File;
import java.rmi.Naming;

import com.farm.wda.inter.WdaAppInter;

public class RmiTest {

	private static String key = "3";
	private static String path = "D:\\temp\\1.docx";

	public static void main(String[] args) {
		try {
			// 调用远程对象，注意RMI路径与接口必须与服务器配置一致
			WdaAppInter wda = (WdaAppInter) Naming.lookup("rmi://127.0.0.1:8888/wda");
			wda.generateDoc(key, new File(path), "测试文档","权限id");
			for (int n = 0; n < 10; n++) {
				Thread.sleep(2000);
				if (wda.isLoged(key)) {
					try {
						System.out.println(wda.getText(key));
						return;
					} catch (Exception e) {
						System.out.println("文档未能读取" + n);
					}
				} else {
					System.out.println("文档生成中..." + n);
				}
			}
			System.out.println("文档生成失败");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
