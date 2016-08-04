package com.farm.wda.lucene.test;

import java.io.File;
import java.util.Map;

import com.farm.wda.lucene.common.IRResult;
import com.farm.wda.lucene.server.DocQueryImpl;
import com.farm.wda.lucene.server.DocQueryInter;

public class DoQueryTest {
	private static File indexDir = new File(
			"E:\\workspace\\WDA\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\wda-web\\text\\index");

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		DocQueryInter query = DocQueryImpl.getInstance(indexDir);
		// WHERE(sdf,dfd=sdfsfd)ORDER_BY(dddd:LONG|STRING|DOUBLE ASC)

		// DocIndexImpl dii = (DocIndexImpl)
		// DocIndexImpl.getInstance(indexDir,"testId");
		// dii.deleteFhysicsIndex("asdfsafdasfdasf");
		// dii.close();
		IRResult result = query.query(
				"WHERE(TEXT=管理员) ORDER_BY(TEXT:STRING ASC)", 1, 10);

		for (Map<String, Object> node : result.getResultList()) {
			System.out.println(node.get("TEXT"));
			System.out.println(node.get("ID"));
			System.out
					.println("-----------------------------------------------------------------");
		}

		//
		// IndexReader ireader = IndexReader.open(FSDirectory.open(indexDir));
		//
		// IndexSearcher isearcher = new IndexSearcher(ireader);
		//
		// // QueryParser ap = new QueryParser(Version.LUCENE_35, "TEXT",
		// // new IKAnalyzer());
		// String[] fields = { "TEXT" };
		// QueryParser mqp = new MultiFieldQueryParser(Version.LUCENE_35,
		// fields,
		// new IKAnalyzer());
		// mqp.setDefaultOperator(QueryParser.AND_OPERATOR);
		// Query query1 = mqp.parse("煤炭,煤炭");
		// TopDocs topdocs = isearcher.search(query1, 5);
		// System.out.println(topdocs.totalHits);

	}

}
