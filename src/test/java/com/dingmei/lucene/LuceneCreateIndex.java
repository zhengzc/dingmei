package com.dingmei.lucene;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import com.dingmei.BaseTest;

/**
 * 创建索引
 * @author zhengzhichao
 *
 */
public class LuceneCreateIndex extends BaseTest {
	/**
	 * 索引的路径
	 */
	private final String indexPath = "/home/zhengzhichao/testLucene";
	
	@Test
	public void createIndex() throws Exception{
		//a.从数据源准备索引数据
		List<KeyVO> keywords = getKeyWords();
		//b.创建IndexWriter
		IndexWriter indexWriter = getIndexWriter();
		//c.根据索引数据创建索引
		addDoc(indexWriter, keywords);
		
		indexWriter.close();
	}
	
	private List<KeyVO> getKeyWords(){
		KeyVO vo1 = new KeyVO();
		vo1.id = "1";
		vo1.name = "测试1";
		vo1.url = "www.baidu.com";
		vo1.keywords = new String[]{"测试css","测试one","简单测试1"};
		
		KeyVO vo2 = new KeyVO();
		vo2.id = "2";
		vo2.name = "测试2";
		vo2.url = "www.test2.com";
		vo2.keywords = new String[]{"测试css2","测试two","简单测试2"};
		
		List<KeyVO> ret = new ArrayList<KeyVO>();
		ret.add(vo1);
		ret.add(vo2);
		
		
		return ret;
	}
	
	private IndexWriter getIndexWriter() throws IOException {
		Directory dir = FSDirectory.open(new File(indexPath));
		//Version操作开始变得非常常见
		//中文分词器的引入，好像4.7.0对庖丁等第三方分词器兼容得并不好，可能也是因为apache对原生的做了一些整合的缘故
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47); 
		//同时引入了IndexWriterConfig对象，封装了早期版本的一大堆参数
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
		IndexWriter writer = new IndexWriter(dir, config);
		return writer;
	}
	
	private void addDoc(IndexWriter indexWriter, List<KeyVO> keywords) throws IOException {
		for (KeyVO keyVO : keywords) {
			Document doc = createDoc(keyVO);
			indexWriter.addDocument(doc);
		}
	}

	private Document createDoc(KeyVO keywords) throws UnsupportedEncodingException {
		Document doc = new Document();
		doc.add(new StringField("name", keywords.name, Field.Store.YES));
		doc.add(new StringField("id", keywords.id, Field.Store.YES));
		doc.add(new StoredField("url", keywords.url));
		
		//这个keywords就像博客文章的自定义“关键字”，这些字有多个，而且都会做用到索引并且接受分词操作的，“css学习”会被拆分为“css”和“学习”
		String[] keys = keywords.keywords;
		for (int i = 0; i < keys.length; i++) {
			doc.add(new TextField("keyword", keys[i],Field.Store.YES));
		}
 		
		return doc;
	}
}
