/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/28
 * Description: TransactionManager
 */
package com.zgf.spring.transaction.handlewrite;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * TransactionManager
 *
 * @author zhangguifeng
 * @create 2018-09-28 14:39
 **/
public class TransactionManager {

    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return SingleThreadConnectionHolder.getConnection(dataSource);
    }

    public void start() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
    }

    public void rollback() throws SQLException {
        Connection connection = getConnection();
        connection.rollback();
    }

    public void close() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        connection.close();
    }
}
