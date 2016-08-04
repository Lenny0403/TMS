package com.farm.wda.adaptor.impl;

import java.io.File;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.farm.wda.adaptor.DocConvertorBase;
import com.farm.wda.common.CompressFile;
import com.farm.wda.util.CharUtils;
import com.farm.wda.util.FileUtil;
import com.sun.star.uno.RuntimeException;

public class ZipRarToHtmlConvertor extends DocConvertorBase {
	private static final Logger log = Logger.getLogger(ZipRarToHtmlConvertor.class);

	@Override
	public void run(File file, String fileTypeName, File targetFile) {
		try {
			log.debug("解压文件" + file.getPath());
			log.debug("解压目录" + targetFile.getPath());
			deCompress(file.getPath(), fileTypeName, targetFile.getParent());
			File[] files = targetFile.getParentFile().listFiles();
			StringBuffer buffer = new StringBuffer();
			buffer.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
			buffer.append("<html><head>");
			buffer.append("<meta http-equiv=\"CONTENT-TYPE\" content=\"text/html; charset=utf-8\" /> ");
			buffer.append("<title></title>");
			buffer.append("<link href=\"/wda/css/wdacontent.css\" rel=\"stylesheet\" type=\"text/css\" />");
			buffer.append("</head>");
			buffer.append("<body><div class=\"wdaTitleBox\"><img src=\"/wda/img/htmllogo.png\" /></div>");
			buffer.append("<div class=\"wdahtmlbox\">");
			for (File dirfile : files) {
				if (dirfile.equals("index.html")) {
					continue;
				}
				if (!dirfile.isDirectory()) {
					String fileName = dirfile.getName();
					if (CharUtils.isChinese(fileName)) {
						buffer.append(dirfile.getName() + "<br/>");
					} else {
						if (fileName.toLowerCase().endsWith(".jsp")) {
							buffer.append(dirfile.getName() + "<br/>");
						} else {
							buffer.append("<a href='" + URLEncoder.encode(dirfile.getName(), "utf-8") + "'>"
									+ dirfile.getName() + "</a><br/>");
						}
					}
				} else {
					buffer.append(dirfile.getName() + "[目录]<br/>");
				}
			}
			buffer.append("</div>");
			buffer.append("</body>");
			buffer.append("</html>");
			FileUtil.wirteInfo(targetFile, buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 解压缩
	 */
	public static void deCompress(String sourceFile, String filetype, String destDir) throws Exception {
		// 保证文件夹路径最后是"/"或者"\"
		char lastChar = destDir.charAt(destDir.length() - 1);
		if (lastChar != '/' && lastChar != '\\') {
			destDir += File.separator;
		}
		// 根据类型，进行相应的解压缩
		String type = sourceFile.substring(sourceFile.lastIndexOf(".") + 1);
		if (filetype != null) {
			type = filetype;
		}
		if (type.toLowerCase().equals("zip")) {
			CompressFile.unZipFiles(new File(sourceFile), destDir);
		} else if (type.toLowerCase().equals("rar")) {
			CompressFile.unRarFile(sourceFile, destDir);
		} else {
			throw new Exception("只支持zip和rar格式的压缩包！");
		}
	}

}
