package com.farm.wda.web.servlet;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.farm.wda.Beanfactory;
import com.farm.wda.util.AppConfig;

public class SysInit extends HttpServlet {

	/**
	 * 任务集合
	 */
	private static final long serialVersionUID = 1L;

	// 配置系统所有默认启动任务

	public SysInit() {
		super();
	}

	private final Logger log = Logger.getLogger(this.getClass());

	public void destroy() {
		super.destroy();
	}

	public void init() throws ServletException {
		initWebPath();
		Beanfactory.startOpenOfficeServer();
		Beanfactory.statrtConverter();
		Beanfactory.startRmi();
	}

	/**
	 * 初始化容器路径
	 */
	private void initWebPath() {
		Beanfactory.WEB_DIR = AppConfig.getString("config.file.dir.path")
				.replace("WEBROOT", super.getServletContext().getRealPath("/"));
		if (File.separator.equals("/")) {
			Beanfactory.WEB_DIR = Beanfactory.WEB_DIR.replace("\\\\", "/")
					.replace("//", "/");
		} else {
			Beanfactory.WEB_DIR = Beanfactory.WEB_DIR.replace("/", "\\")
					.replace("\\\\", "\\");
		}
		Beanfactory.WEB_URL = getServletContext().getContextPath();
		log.info("初始化容器路径" + Beanfactory.WEB_DIR);
		log.info("初始化容器URL" + Beanfactory.WEB_URL);
	}

}
