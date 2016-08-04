package com.farm.wda.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.farm.wda.adaptor.DocConvertorBase;

public class ConfUtils {
	private static Document document;
	private static Map<String, Object> objmap = new HashMap<String, Object>();
	static {
		URL url = ConfUtils.class.getClassLoader().getResource(
				"docTypeConf.xml");
		File file = new File(url.getFile());
		try {
			document = Jsoup.parse(file, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得支持的被转换的文档
	 * 
	 * @return
	 */
	public static Set<String> getAcceptTypes() {
		Elements eles = document.getElementsByTag("type");
		Set<String> set = new HashSet<String>();
		for (Element node : eles) {
			set.add(node.getElementsByTag("name").text());
		}
		return set;
	}

	/**
	 * 获得所有转换目标类型配置
	 * 
	 * @return<file.exname,filepath配置在配置文件中的相对路径>
	 */
	public static Map<String, String> getTargetTypes() {
		Elements eles = document.getElementsByTag("file");
		Map<String, String> map = new HashMap<String, String>();
		for (Element node : eles) {
			String exname = node.getElementsByTag("exname").text();
			String filename = node.getElementsByTag("filename").text();
			String filepath = node.getElementsByTag("filename").attr("path");
			map.put(exname, filepath + File.separator + filename);
		}
		return map;
	}

	/**
	 * 获得转换目标类型
	 * 
	 * @param fileTypeName
	 * @return<target.exname,filepath配置在配置文件中的相对路径>
	 */
	public static Map<String, String> getTargetTypes(String fileTypeName) {
		Map<String, String> dic = getTargetTypes();
		Map<String, String> map = new HashMap<String, String>();
		Elements eles = document.getElementsByTag("type");
		for (Element type : eles) {
			if (type.getElementsByTag("name").text().toUpperCase()
					.equals(fileTypeName.toUpperCase())) {
				for (Element exname : type.getElementsByTag("target").get(0)
						.getElementsByTag("exname")) {
					map.put(exname.text(), dic.get(exname.text()));
				}
			}
		}
		return map;
	}

	/**
	 * 获得转换器实现类
	 * 
	 * @param fileTypeName
	 * @param extensionName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static DocConvertorBase getConvertor(String fileTypeName,
			String targetTypeName) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		Elements eles = document.getElementsByTag("type");
		for (Element type : eles) {
			if (type.getElementsByTag("name").text().toUpperCase()
					.equals(fileTypeName.toUpperCase())) {
				Elements targetexnames = type.getElementsByTag("exname");
				for (Element targetexname : targetexnames) {
					if (targetexname.text().toUpperCase()
							.equals(targetTypeName.toUpperCase())) {
						String className = targetexname.attr("impl").trim();
						DocConvertorBase convertor = (DocConvertorBase) objmap
								.get(className);
						if (convertor == null) {
							@SuppressWarnings("rawtypes")
							Class obj = Class.forName(className);
							convertor = (DocConvertorBase) obj.newInstance();
							objmap.put(className, convertor);
						}
						return convertor;
					}
				}
			}
		}
		throw new RuntimeException("未找到转换器：" + fileTypeName + " to "
				+ targetTypeName);
	}
}
