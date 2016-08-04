package com.farm.wda.adaptor.impl;

import java.io.File;
import java.io.IOException;

import com.farm.wda.adaptor.DocConvertorBase;
import com.farm.wda.util.ZipUtils;
import com.sun.star.uno.RuntimeException;

/**
 * 将web包转换成html站点
 * 
 * @author wangdong
 *
 */
public class WebSiteToHtmlConvertor extends DocConvertorBase {

	@Override
	public void run(File file, String fileTypeName, File targetFile) {
		try {
			ZipUtils.unHtmlZipFiles(file, targetFile.getParent() + File.separator);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage() + " ,[Verify that the Web file is compressed Zip, and in the root directory contains the file index.html file]");
		}
	}
}
