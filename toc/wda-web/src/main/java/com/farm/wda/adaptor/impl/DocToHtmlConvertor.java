package com.farm.wda.adaptor.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.net.ConnectException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.farm.wda.Beanfactory;
import com.farm.wda.adaptor.DocConvertorBase;
import com.farm.wda.util.FileUtil;

public class DocToHtmlConvertor extends DocConvertorBase {
	private static final Logger log = Logger.getLogger(DocToHtmlConvertor.class);

	public void run(File file, String fileTypeName, File targetFile) {
		// 创建Openoffice连接
		OpenOfficeConnection con = new SocketOpenOfficeConnection(8100);
		try {
			// 连接
			con.connect();
		} catch (ConnectException e) {
			log.error("获取OpenOffice连接失败..." + e.getMessage());
		}
		try {
			// 创建转换器
			DocumentConverter converter = new OpenOfficeDocumentConverter(con);
			DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry();
			DocumentFormat pdfFormat = formatReg.getFormatByFileExtension(fileTypeName.toLowerCase());
			DocumentFormat docFormat = formatReg
					.getFormatByFileExtension(FileUtil.getExtensionName(targetFile.getName()));
			// stream 流的形式
			// 转换文档问html
			converter.convert(file, pdfFormat, targetFile, docFormat);
			log.info("成功转换：" + file + "转换到" + targetFile);
			// 修改htm样式加入
			String charset = FileUtil.getCharSet(FileUtil.readTxtFile(targetFile));
			Document htmldoc = Jsoup.parse(targetFile, charset);
			//Elements html = htmldoc.getElementsByTag("html");
			Elements head = htmldoc.getElementsByTag("HEAD");
			head.append("<link href=\"" + Beanfactory.WEB_URL
					+ "/css/wdacontent.css\" rel=\"stylesheet\" type=\"text/css\" />");
			Elements body = htmldoc.getElementsByTag("body");
			String bodyhtml = body.html();
			body.html("");
			body.append("<div class=\"wdaTitleBox\"><img src=\"" + Beanfactory.WEB_URL
					+ "/img/htmllogo.png\"/></div><div class=\"wdahtmlbox\">" + bodyhtml + "</div>");
			FileOutputStream fos = new FileOutputStream(targetFile);
			fos.write(htmldoc.toString().getBytes(charset));
			fos.close();
		} catch (Exception e) {
			log.error("转换错误" + e + "/" + file + "转换到" + targetFile);
			throw new RuntimeException(e);
		} finally {
			// 关闭openoffice连接
			con.disconnect();
		}

	}
}
