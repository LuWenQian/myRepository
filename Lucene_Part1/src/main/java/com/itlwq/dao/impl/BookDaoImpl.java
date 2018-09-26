package com.itlwq.dao.impl;

import com.itlwq.dao.BookDao;
import com.itlwq.domain.Book;
import com.itlwq.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 文谦 on 2018/9/23
 */
public class BookDaoImpl implements BookDao {
    public void saveBook(Book book) {
    }

    public List<Book> findAllBook() {
        try {
            String sql = "SELECT * FROM book";
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Book> list = new ArrayList<Book>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setBookname(resultSet.getString("bookname"));
                book.setPrice(resultSet.getFloat("price"));
                book.setPic(resultSet.getString("pic"));
                book.setBookdesc(resultSet.getString("bookdesc"));
                list.add(book);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
