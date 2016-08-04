package com.farm.wda.domain;

import java.io.File;

/**
 * 文档转换任务
 * 
 * @author macplus
 *
 */
public class DocTask {
	private String fileTypeName;
	private File file;
	private File targetFile;
	private File logFile;
	private File infoFile;
	private String key;
	private String info;
	private String targettype;
	private String authid;// 权限关键字，在wda中没用，将返回给wcp

	public DocTask(File file, File targetFile, File log) {
		this.file = file;
		this.targetFile = targetFile;
		this.logFile = log;
	}

	public DocTask(String key, File file, String fileTypeName, File targetFile, File log, File infoFile, String info,
			String targettype, String authid) {
		this.key = key;
		this.file = file;
		this.fileTypeName = fileTypeName;
		this.targetFile = targetFile;
		this.logFile = log;
		this.infoFile = infoFile;
		this.info = info;
		this.targettype = targettype;
		this.authid = authid;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public File getFile() {
		return file;
	}

	public File getLogFile() {
		return logFile;
	}

	public void setLogFile(File logFile) {
		this.logFile = logFile;
	}

	public File getInfoFile() {
		return infoFile;
	}

	public void setInfoFile(File infoFile) {
		this.infoFile = infoFile;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public File getTargetFile() {
		return targetFile;
	}

	public void setTargetFile(File targetFile) {
		this.targetFile = targetFile;
	}

	public String getFileTypeName() {
		return fileTypeName;
	}

	public String getAuthid() {
		return authid;
	}

	public void setAuthid(String authid) {
		this.authid = authid;
	}

	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTargettype() {
		return targettype;
	}

	public void setTargettype(String targettype) {
		this.targettype = targettype;
	}

}
