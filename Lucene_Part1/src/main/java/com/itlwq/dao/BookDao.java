package com.itlwq.dao;

import com.itlwq.domain.Book;

import java.util.List;

/**
 * Created by 文谦 on 2018/9/23
 */
public interface BookDao {
    void saveBook(Book book);
    List<Book> findAllBook();
}
