package com.farm.wda.adaptor;

import java.io.File;
import java.rmi.Naming;

import org.apache.log4j.Logger;

import com.farm.wda.Beanfactory;
import com.farm.wda.domain.DocTask;
import com.farm.wda.util.AppConfig;
import com.farm.web.rmi.WcpAppInter;

public abstract class DocConvertorBase {
	private static final Logger log = Logger.getLogger(DocConvertorBase.class);

	public void convert(File file, String fileTypeName, File targetFile, DocTask task) {
		run(file, fileTypeName, targetFile);
		if (AppConfig.getString("config.callback.runLuceneIndex.start").toUpperCase().equals("TRUE")) {
			try {
				WcpAppInter wcpApp = (WcpAppInter) Naming
						.lookup(AppConfig.getString("config.callback.runLuceneIndex.url"));
				wcpApp.runLuceneIndex(task.getKey(), task.getAuthid(),
						Beanfactory.getWdaAppImpl().getText(task.getKey()));
			} catch (Exception e) {
				log.error("wcp索引任务回调失败：" + e.getMessage() + e.toString());
			}
		}
	}

	public abstract void run(File file, String fileTypeName, File targetFile);
}
