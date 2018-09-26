package com.itlwq.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/**
 * Created by 文谦 on 2018/9/23
 */
public class UpdateIndexByTerm {
    private final String path = "E:\\upload\\Index";
    @Test
    public void fun() {
        try {
            /* 创建分析器 */
            Analyzer analyzer = new IKAnalyzer();

            /* 索引库配置信息 */
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);

            /* 创建文档 */
            Document document = new Document();
            document.add(new TextField("name", "MyBatisAndStruts2AndSpringAndSpringMVC", Field.Store.YES));

            /* 索引库关联 */
            File file = new File(path);
            Directory directory = FSDirectory.open(file);

            /* 索引核心类, 与索引库进行关联 */
            IndexWriter writer = new IndexWriter(directory, config);

            /* 根据term条件进行更新 */
            Term term = new Term("name", "Struts2");
            writer.updateDocument(term, document);

            /* 释放资源 */
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
