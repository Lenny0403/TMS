package com.farm.wda.lucene;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.farm.wda.lucene.face.WordAnalyzerFace;
import com.farm.wda.lucene.server.DocIndexImpl;
import com.farm.wda.lucene.server.DocIndexInter;
import com.farm.wda.lucene.server.DocQueryImpl;
import com.farm.wda.lucene.server.DocQueryInter;

public class FarmLuceneFace {
	private static final Logger log = Logger.getLogger(FarmLuceneFace.class);

	private FarmLuceneFace() {
	}

	private static FarmLuceneFace obj;

	/**
	 * 获得服务实例
	 * 
	 * @return
	 */
	public static FarmLuceneFace inctance() {
		if (obj == null) {
			obj = new FarmLuceneFace();
		}
		return obj;
	}

	/**
	 * 分词服务
	 * 
	 * @return
	 */
	public WordAnalyzerFace getWordAnalyzerFace() {
		return new WordAnalyzerFace();
	}

	/**
	 * 建立索引服务
	 * 
	 * @param indexDir
	 * @return
	 * @throws IOException
	 */
	public DocIndexInter getDocIndex(File indexDir) throws IOException {
		return DocIndexImpl.getInstance(indexDir);
	}

	/**
	 * 查询索引服务
	 * 
	 * @param indexFile
	 * @return
	 */
	public DocQueryInter getDocQuery(File indexDir) {
		return DocQueryImpl.getInstance(indexDir);
	}

	/**
	 * 多索引查询服务
	 * 
	 * @param indexFiles
	 * @return
	 */
	public DocQueryInter getDocQuery(List<File> indexDirs) {
		return DocQueryImpl.getInstance(indexDirs);
	}

	/**
	 * 获得索引路径
	 * 
	 * @param path
	 *            基于所配置目录的相对路径（luncene_index_dir+File.separator + path）
	 * @return
	 */
	public File getIndexPathFile(File basedir, String path) {
		File indexDir = new File(basedir.getPath() + File.separator + path);
		return indexDir;
	}
}
