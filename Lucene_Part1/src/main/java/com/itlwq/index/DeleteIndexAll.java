package com.itlwq.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
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
public class DeleteIndexAll {

    private final String path = "E:\\upload\\Index";

    @Test
    public void fun() {
        try {
            /* 创建分析器 */
            Analyzer analyzer = new IKAnalyzer();

            /* 配置索引库信息 */
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);

            /* 创建索引库 */
            File file = new File(path);
            Directory directory = FSDirectory.open(file);

            /* 创建索引对象 */
            IndexWriter writer = new IndexWriter(directory,config);

            /* 删除所有 */
            writer.deleteAll();

            /* 关闭writer */
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
