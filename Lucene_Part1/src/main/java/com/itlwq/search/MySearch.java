package com.itlwq.search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/**
 * Created by 文谦 on 2018/9/21
 */
public class MySearch {
    private final String path = "E:\\upload\\Index";
    @Test
    public void fun() {
        try {
            /* 建立分析器对象(Analyzer) */
            Analyzer analyzer = new IKAnalyzer();
            /* 建立查询对象
             *      建立查询器解析器对象 */
            QueryParser queryParser = new QueryParser("bookname", analyzer);
            /* 使用查询解析器对象
             *      实例化查询对象, 解析表达式 */
            Query parse = queryParser.parse("bookname:java");
            /* 建立索引目录对象 */
            File file = new File(path);
            Directory directory = FSDirectory.open(file);
            /* 建立索引读取对象 */
            IndexReader indexReader = DirectoryReader.open(directory);
            /* 建立索引搜索对象 */
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            /* 执行搜索
             *   Query 查询对象
             *   int 搜索结果前几个)
             * TopDocs: 只封装了两个信息
             *      文档id
             *      文档的分值(分值越大, 越靠前)*/
            TopDocs search = indexSearcher.search(parse, 10);

            /* 查询总的个数 */
            int totalHits = search.totalHits;
            System.out.println("The NUM IS ~ " + totalHits);

            /* TopDocs中通过文档id获得目标数据 */
            ScoreDoc[] scoreDocs = search.scoreDocs;
            for (ScoreDoc scoreDoc : scoreDocs) {
                /* 文档id */
                int doc = scoreDoc.doc;
                /* 文档分值 */
                float score = scoreDoc.score;

                /*通过分值获取目标数据 */
                Document document = indexSearcher.doc(doc);
                String id = document.get("id");
                String bookName = document.get("bookname");
                String price = document.get("price");
                String pic = document.get("pic");
                String bookdesc = document.get("bookdesc");

                System.out.println(id + bookName + price + pic + bookdesc);
            }

            indexReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
