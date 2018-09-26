package com.itlwq.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * Created by 文谦 on 2018/9/23
 */
public class DeleteIndexByTerm {
    private final String path = "E:\\upload\\Index";

    @Test
    public void fun() {
        try {
            /* 建立分析器对象 */
            Analyzer analyzer = new IKAnalyzer();

            /* 配置索引库信息 */
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);

            /* 配置索引库 */
            File file = new File(path);
            Directory directory = FSDirectory.open(file);

            /* 配置索引核心对象, 创建索引库 */
            IndexWriter writer = new IndexWriter(directory, config);

            /* 配置Term删除条件对象
             *      删除名称域中bookname包含java */
            Term term = new Term("bookname", "java");

            /* 根据Term删除 */
            writer.deleteDocuments(term);

            /* 关闭writer */
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
