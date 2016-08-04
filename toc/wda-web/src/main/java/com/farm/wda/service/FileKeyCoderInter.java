package com.farm.wda.service;

import java.io.File;

import com.farm.wda.exception.ErrorTypeException;

/**
 * 关键字解析接口
 * 
 * @author Administrator
 *
 */
public interface FileKeyCoderInter {

	/**
	 * 解析出一个文档的文件夹路径(相对路径)
	 * 
	 * @param key
	 * @return
	 */
	public String parseDir(String key);

	/**
	 * 解析文件地址(绝对路径)
	 * 
	 * @param key
	 * @param exname
	 * @return
	 * @throws ErrorTypeException
	 */
	public String parseRealPath(String key, String exname)
			throws ErrorTypeException;

	/**
	 * 解析文件对象
	 * 
	 * @param key
	 *            文件id
	 * @param exname
	 *            文件类型
	 * @return
	 * @throws ErrorTypeException
	 */
	public File parseFile(String key, String exname) throws ErrorTypeException;

	/**
	 * 解析文件名
	 * 
	 * @param exname
	 * @return
	 * @throws ErrorTypeException
	 */
	public String parseFileName(String exname) throws ErrorTypeException;

	/**
	 * 解析日志文件
	 * 
	 * @param key
	 * @return
	 */
	public File parseLogFile(String key);

	/**
	 * 解析日志文件
	 * 
	 * @param key
	 * @return
	 */
	public File parseInfoFile(String key);

	/**
	 * 获得日志文件名
	 * 
	 * @return
	 */
	public String parseLogFileName();

	/**
	 * 获得日志文件名
	 * 
	 * @return
	 */
	public String parseInfoFileName();

	/**
	 * 获得文本文件名
	 * 
	 * @return
	 */
	public String parseTextFileName();

	/**
	 * 获得纯文本文件
	 * 
	 * @param key
	 */
	public File parseTextFile(String key);

	/**
	 * 获得索引目录
	 * 
	 * @return
	 */
	public File parseLuceneDir();
}
