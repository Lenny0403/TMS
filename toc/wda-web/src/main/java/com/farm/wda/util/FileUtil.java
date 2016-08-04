package com.farm.wda.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class FileUtil {
	private static final Logger log = Logger.getLogger(FileUtil.class);

	public static String readTxtFile(File file, String encodeset) {
		StringBuffer buffer = new StringBuffer();
		InputStreamReader read = null;
		InputStream inputStream = null;
		try {
			String encoding = encodeset;
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				inputStream = new FileInputStream(file);
				read = new InputStreamReader(inputStream, encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					buffer.append(lineTxt);
				}
				read.close();
			} else {
				log.error("找不到指定的文件");
			}
		} catch (Exception e) {
			log.error("读取文件内容出错" + e);
		} finally {
			try {
				read.close();
				inputStream.close();
			} catch (Exception e) {
				log.error("读取文件内容出错" + e);
			}
		}
		return buffer.toString();

	}

	public static String readTxtFile(File file) {
		StringBuffer buffer = new StringBuffer();
		InputStreamReader read = null;
		InputStream inputStream = null;
		try {
			String encoding = AppConfig.getString("config.file.encode");
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				inputStream = new FileInputStream(file);
				read = new InputStreamReader(inputStream, encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					buffer.append(lineTxt);
				}
				read.close();
			} else {
				log.error("找不到指定的文件");
			}
		} catch (Exception e) {
			log.error("读取文件内容出错" + e);
		} finally {
			try {
				read.close();
				inputStream.close();
			} catch (Exception e) {
				log.error("读取文件内容出错" + e);
			}
		}
		return buffer.toString();

	}

	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * 写日志
	 * 
	 * @param log2
	 *            文件
	 * @param e异常
	 */
	public static void wirteLog(File log2, String message) {
		RandomAccessFile raf;
		try {
			SimpleDateFormat sDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd   hh:mm:ss");
			String date = sDateFormat.format(new java.util.Date());
			if (!log2.exists()) {
				log2.createNewFile();
			}
			raf = new RandomAccessFile(log2, "rw");
			try {
				// 按读写方式创建一个随机访问文件流
				long fileLength = raf.length();// 获取文件的长度即字节数
				// 将写文件指针移到文件尾。
				raf.seek(fileLength);
				// 按字节的形式将内容写到随机访问文件流中
				raf.writeBytes(date + ":" + message + "\r\n");
			} catch (IOException e1) {
				log.error(e1);
			} finally {
				// 关闭流
				raf.close();
			}
		} catch (Exception e2) {
			log.error(e2);
		}
	}

	/**
	 * 写日志
	 * 
	 * @param log2
	 *            文件
	 * @param e异常
	 */
	public static void wirteInfo(File file, String text) {
		FileOutputStream out = null;
		try {
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				out = new FileOutputStream(file, false);
				out.write(text.getBytes(AppConfig
						.getString("config.file.encode")));
				out.close();
			} catch (IOException ex) {
				log.error(ex);
			} finally {
				// 关闭流
				out.close();
			}
		} catch (IOException e) {
			log.error(e);
		}
	}

	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	private static final String regEx_space = "\\s*|\t|\r|\n";// 定义空格回车换行符

	/**
	 * @param htmlStr
	 * @return 删除Html标签
	 */
	public static String delHTMLTag(String htmlStr) {
		Pattern p_script = Pattern.compile(regEx_script,
				Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		Pattern p_space = Pattern
				.compile(regEx_space, Pattern.CASE_INSENSITIVE);
		Matcher m_space = p_space.matcher(htmlStr);
		htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
		return htmlStr.trim().replaceAll("&nbsp;", ""); // 返回文本字符串
	}

	/**
	 * 获得页面字符匹配
	 */
	public static String matchCharset(String content) {
		String chs = "gb2312";
		Pattern p = Pattern.compile("(?<=charset=)(.+)(?=\")");
		Matcher m = p.matcher(content);
		if (m.find())
			return m.group();
		return chs;
	}

	/**
	 * 获得html字符集
	 * 
	 * @param content
	 * @return
	 */
	public static String getCharSet(String html) {
		String charset = matchCharset(html).replaceAll("'", "\"");
		charset = charset.substring(
				0,
				charset.indexOf("\"") > 0 ? charset.indexOf("\"") : charset
						.length());
		return charset.trim();
	}

}
