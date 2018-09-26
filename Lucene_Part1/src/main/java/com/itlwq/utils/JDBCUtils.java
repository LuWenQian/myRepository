package com.itlwq.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by 文谦 on 2018/9/21
 */
public class JDBCUtils {
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/mysql_lucene",
                    "root",
                    "root");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }
}
