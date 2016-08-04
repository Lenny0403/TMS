package com.farm.wda.service.impl;

import java.io.File;

import com.farm.wda.Beanfactory;
import com.farm.wda.exception.ErrorTypeException;
import com.farm.wda.service.FileKeyCoderInter;
import com.farm.wda.util.ConfUtils;
import com.farm.wda.util.Md5Utils;

public class FileKeyCoderImpl implements FileKeyCoderInter {
	private static String name = "wda";

	public String parseDir(String key) {
		String str = Md5Utils.MD5(key);
		String path;
		path = name + str.substring(0, 2);
		path = path + File.separator + name + str.substring(2, 4);
		path = path + File.separator + name + str.substring(4, 6);
		path = path + File.separator + name + str.substring(6, 8);
		path = path + File.separator + name + str.substring(8, 10);
		path = path + File.separator + name + str.substring(10);
		return path;
	}

	public String parseRealPath(String key, String exname)
			throws ErrorTypeException {
		String path = parseDir(key) + File.separator + parseFileName(exname);
		if (Beanfactory.WEB_DIR != null && !Beanfactory.WEB_DIR.isEmpty()) {
			String paht = (Beanfactory.WEB_DIR + File.separator + path);
			return paht;
		} else {
			return path;
		}
	}

	public File parseFile(String key, String exname) throws ErrorTypeException {
		File file = new File(parseRealPath(key, exname));
		file.getParentFile().mkdirs();
		return file;
	}

	public String parseFileName(String exname) throws ErrorTypeException {
		String fileName = ConfUtils.getTargetTypes().get(exname);
		if (fileName == null) {
			throw new ErrorTypeException("文件类型未被定义:" + exname);
		}
		return fileName;
	}

	public File parseLogFile(String key) {
		String path = parseDir(key) + File.separator + parseLogFileName();
		if (Beanfactory.WEB_DIR != null && !Beanfactory.WEB_DIR.isEmpty()) {
			path = (Beanfactory.WEB_DIR + File.separator + path);
		}
		File file = new File(path);
		file.getParentFile().mkdirs();
		return file;
	}

	public String parseLogFileName() {
		String fileName = "log" + File.separator + "file.log";
		return fileName;
	}

	@Override
	public String parseInfoFileName() {
		String fileName = "info.txt";
		return fileName;
	}

	@Override
	public File parseInfoFile(String key) {
		String path = parseDir(key) + File.separator + parseInfoFileName();
		if (Beanfactory.WEB_DIR != null && !Beanfactory.WEB_DIR.isEmpty()) {
			path = (Beanfactory.WEB_DIR + File.separator + path);
		}
		File file = new File(path);
		file.getParentFile().mkdirs();
		return file;
	}

	@Override
	public File parseTextFile(String key) {
		String path = parseDir(key) + File.separator + parseTextFileName();
		if (Beanfactory.WEB_DIR != null && !Beanfactory.WEB_DIR.isEmpty()) {
			path = (Beanfactory.WEB_DIR + File.separator + path);
		}
		File file = new File(path);
		file.getParentFile().mkdirs();
		return file;
	}

	@Override
	public String parseTextFileName() {
		String fileName = "text.txt";
		return fileName;
	}

	@Override
	public File parseLuceneDir() {
		String path = (Beanfactory.WEB_DIR + File.separator + "index");
		File indexdir = new File(path);
		indexdir.mkdirs();
		return indexdir;
	}

}
