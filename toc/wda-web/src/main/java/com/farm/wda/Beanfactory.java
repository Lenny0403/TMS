package com.farm.wda;

import java.net.MalformedURLException;
import java.nio.channels.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.apache.log4j.Logger;

import com.farm.wda.adaptor.DocConvertorBase;
import com.farm.wda.domain.DocTask;
import com.farm.wda.impl.WdaAppImpl;
import com.farm.wda.inter.WdaAppInter;
import com.farm.wda.service.FileKeyCoderInter;
import com.farm.wda.service.impl.FileKeyCoderImpl;
import com.farm.wda.util.AppConfig;
import com.farm.wda.util.ConfUtils;
import com.farm.wda.util.FileUtil;

/**
 * 服务门面类
 * 
 * @author macplus
 *
 */
public class Beanfactory {

	public static String WEB_DIR;
	public static String WEB_URL;
	private static boolean isstart;
	private static final Logger log = Logger.getLogger(Beanfactory.class);

	/**
	 * 获得转换服务
	 * 
	 * @return
	 */
	public static WdaAppInter getWdaAppImpl() {
		try {
			return new WdaAppImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得文件命名文件地址服务
	 * 
	 * @return
	 */
	public static FileKeyCoderInter getFileKeyCoderImpl() {
		return new FileKeyCoderImpl();
	}

	/**
	 * 启动office转换服务
	 */
	public static void startOpenOfficeServer() {
		Runtime runtime = Runtime.getRuntime();
		try {
			log.info("执行启动openoffice服务...");
			log.info(AppConfig.getString("config.server.openoffice.cmd"));
			runtime.exec(AppConfig.getString("config.server.openoffice.cmd"));
		} catch (Exception e) {
			log.info("Error:openoffice服务");
		}
	}

	/**
	 * 启动rim服务
	 */
	public static void startRmi() {
		try {
			int port = Integer.valueOf(AppConfig.getString("config.rmi.port"));
			String rui = "rmi://127.0.0.1:" + port + "/wda";
			WdaAppInter wda = Beanfactory.getWdaAppImpl();
			LocateRegistry.createRegistry(port);
			Naming.rebind(rui, wda);
			log.info("启动RMI服务" + rui);
		} catch (RemoteException e) {
			System.out.println("创建远程对象发生异常！");
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			System.out.println("发生重复绑定对象异常！");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("发生URL畸形异常！");
			e.printStackTrace();
		}
	}

	/**
	 * 启动转换器
	 */
	public static void statrtConverter() {
		if (isstart == true) {
			return;
		}
		isstart = true;
		Thread t = new Thread(new Runnable() {
			public void run() {
				log.info("启动转换任务！");
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
					if (!WdaAppImpl.tasks.isEmpty()) {
						DocTask task = WdaAppImpl.tasks.remove();
						FileUtil.wirteLog(task.getLogFile(), "file key is\"" + task.getKey() + "\"");
						FileUtil.wirteLog(task.getLogFile(),
								"start task!-" + task.getFileTypeName() + " to " + task.getTargetFile().getName());
						try {
							DocConvertorBase convertor = ConfUtils.getConvertor(task.getFileTypeName(),
									FileUtil.getExtensionName(task.getTargetFile().getName()));
							convertor.convert(task.getFile(), task.getFileTypeName(), task.getTargetFile(), task);
							FileUtil.wirteLog(task.getLogFile(), "success!" + task.getTargetFile().getName());
						} catch (Exception e) {
							e.printStackTrace();
							FileUtil.wirteLog(task.getLogFile(),
									"error!" + task.getTargetFile().getName() + e.getMessage());
							log.error("转换异常");
						}
					}
				}
			}
		});
		t.start();
	}
}
