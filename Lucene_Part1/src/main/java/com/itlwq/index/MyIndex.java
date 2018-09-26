package com.itlwq.index;

import com.itlwq.dao.BookDao;
import com.itlwq.dao.impl.BookDaoImpl;
import com.itlwq.domain.Book;
import com.itlwq.utils.JDBCUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 文谦 on 2018/9/21
 */
public class MyIndex {
    private final String path = "E:\\upload\\Index";
    @Test
    public void startIndex() {
        try {

            BookDao dao = new BookDaoImpl();
            List<Book> list = ((BookDaoImpl) dao).findAllBook();

            /* 采集数据, 分析文档 */
            ArrayList<Document> documents = new ArrayList<Document>();
            for (Book book : list) {

                Document document = new Document();
                document.add(new TextField("id", book.getId()+"", Field.Store.YES));
                document.add(new TextField("bookname", book.getBookname(), Field.Store.YES));
                document.add(new TextField("price", book.getPrice()+"", Field.Store.YES));
                document.add(new TextField("pic", book.getPic(), Field.Store.YES));
                document.add(new TextField("bookdesc", book.getBookdesc(), Field.Store.YES));

                documents.add(document);
            }

            /* 建立分析器对象 索引和检索分析器必须一致
             *  不同分析器, 不同算法 */
            Analyzer analyzer = new IKAnalyzer();

            /* 配置索引库的信息 */
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);

            /* 建立索引目录对象, 索引库的存放目录 */
            File file = new File(path);
            Directory directory = FSDirectory.open(file);

            /* 建立索引操作对象IndexWriter */
            IndexWriter writer = new IndexWriter(directory, config);

            /* 建立删除条件对象 */
            Term term = new Term("bookname", "java");

            writer.deleteDocuments(term);

            for (Document document : documents) {
//                添加索引
                writer.addDocument(document);
            }

            writer.close();

            /* 数据库book信息 */
            for (Book book : list) {
                System.out.println(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
