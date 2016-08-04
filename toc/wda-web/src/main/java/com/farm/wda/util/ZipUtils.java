package com.farm.wda.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ZipUtils {
	/**
	 * 解压html压缩文件到指定目录
	 * 
	 * @param zipFile
	 *            压缩文件
	 * @param descDir
	 *            目标目录
	 * @param FileHandle
	 *            处理压缩文件
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static void unHtmlZipFiles(File zipFile, String descDir) throws IOException {
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		for (Enumeration entries = zip.getEntries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			zipEntryName = (enCodeChar(zipEntryName));
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + zipEntryName).replaceAll("\\\\", "/");
			// 判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}
			// int i=1;
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}
			// 输出文件路径信息
			if (outPath.indexOf("Footer_LightBlue.html") >= 0) {
			}
			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
			reCodeFilesUrl_link(new File(outPath));
		}
		System.out.println("******************解压完毕********************");
	}

	/**
	 * 编码中文的RUL路径名(编码文件名，和url)
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String enCodeChar(String str) throws UnsupportedEncodingException {
		String ylStr = str;
		str = str.replaceAll("%20", " ");
		str = str.replaceAll("/", "_2343321_");
		str = str.replaceAll("#", "_1246721_");
		str = str.replaceAll(" ", "_1311105_");
		String newstr = java.net.URLEncoder.encode(str, "UTF-8");
		{// 判断文件名是否超长，要是超出200个字符则原样输出
			int lastnum = newstr.lastIndexOf("_2343321_");
			if (lastnum < 0) {
				lastnum = 0;
			}
			if (newstr.substring(lastnum).length() > 200) {
				return ylStr;
			}
		}
		newstr = newstr.replaceAll("%", "A");
		newstr = newstr.replaceAll("_2343321_", "/");
		newstr = newstr.replaceAll("_1246721_", "#");
		newstr = newstr.replaceAll("_1311105_", " ");
		return newstr;
	}

	/**
	 * 重写中文URL,以及加入WCP--LOG
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void reCodeFilesUrl_link(File file) throws IOException {
		String charSet = "utf-8";
		Document docChar = Jsoup.parse(file, "gb2312");
		// 获得文件的编码格式
		if (file.getName().toUpperCase().indexOf(".HTML") > 0 || file.getName().toUpperCase().indexOf(".HTM") > 0) {
			Elements metas = docChar.getElementsByTag("meta");
			for (Element node : metas) {
				if (node.attr("content").toUpperCase().indexOf("CHARSET") >= 0) {
					// node.attr("content", "text/html; charset=utf-8");
					charSet = node.attr("content").substring(node.attr("content").toUpperCase().indexOf("CHARSET") + 8);
					break;
				}
			}
		}
		if (file.getName().toUpperCase().indexOf(".HTML") < 0 && file.getName().toUpperCase().indexOf(".HTM") < 0) {
			return;
		}
		Document doc = Jsoup.parse(file, charSet);
		// 改写文件的编码格式
		if (file.getName().toUpperCase().indexOf(".HTML") > 0 || file.getName().toUpperCase().indexOf(".HTM") > 0) {
			Elements metas = doc.getElementsByTag("meta");
			for (Element node : metas) {
				if (node.attr("content").toUpperCase().indexOf("CHARSET") >= 0) {
					node.attr("content", "text/html; charset=utf-8");
				}
			}
		}
		{
			// 重写中文URL
			Elements frames = doc.getAllElements();
			for (Element node : frames) {
				if (node.attr("href") != null && !node.attr("href").trim().equals("")) {
					node.attr("href", enCodeChar(node.attr("href")));
				}
				if (node.attr("src") != null && !node.attr("src").trim().equals("")) {
					node.attr("src", enCodeChar(node.attr("src")));
				}
			}
		}
		{
			// 重写title
			for (Element node : doc.getElementsByTag("title")) {
				StringBuffer str = new StringBuffer();
				str.append(AppConfig.getString("config.web.title"));
				node.prepend(str.toString() + ":");
			}
		}

		FileOutputStream fos = new FileOutputStream(file, false);
		fos.write(doc.html().getBytes("utf-8"));
		fos.close();
	}

	public static void main(String[] args) throws IOException {
	}
}
