package com.dingmei.lucene;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

/**
 * 根据索引执行查询
 * @author zhengzhichao
 *
 */
public class LuceneSearchIndex {
	/**
	 * 索引的路径
	 */
	private final String indexPath = "/home/zhengzhichao/testLucene";
	
	@Test
	public void search() throws Exception{
		Directory directory = FSDirectory.open(new File(indexPath));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(ireader);
		//Version操作开始变得非常常见
		//中文分词器的引入，好像4.7.0对庖丁等第三方分词器兼容得并不好，可能也是因为apache对原生的做了一些整合的缘故
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);
		QueryParser parser = new QueryParser(Version.LUCENE_47, "keyword", analyzer);
		Query query = parser.parse("测试");
		int maxCount = 20;
		TopDocs topDocs = searcher.search(query, maxCount);
		
		
		ScoreDoc[] pageDocs = topDocs.scoreDocs;
		 // Iterate through the results:
	    for (int i = 0; i < pageDocs.length; i++) {
	      Document hitDoc = searcher.doc(pageDocs[i].doc);
	      System.out.println("----->:"+hitDoc.toString());
	    }
	    ireader.close();
	    directory.close();
	}
	
	


	/*private KeyVO getDocsItem(Query query, ScoreDoc scoreDoc) throws IOException {
		Set<String> fields = new HashSet<String>();
		fields.add("carId");
		fields.add("carName");
	
		org.apache.lucene.document.Document document = searcher.doc(scoreDoc.doc, fields);
		KeyVO resultVO = new KeyVO();
		resultVO.setId(document.getValues("id")[0]);
		resultVO.setCarName(document.getValues("carName")[0]);
		return resultVO;
	}*/

}
