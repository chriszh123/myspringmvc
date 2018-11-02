/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/28
 * Description: MyPooledConnection
 */
package com.zgf.connectionpool.handlerwrite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 数据库连接管道，就是对JDBC Connection进行封装而已，但是需要注意isBusy的这个标示。
 * 对管道的关闭，实际上只是标示的改变而已！
 *
 * @author zhangguifeng
 * @create 2018-09-28 10:49
 **/
public class MyPooledConnection {
    private Connection connection;
    private boolean isBusy = false;

    public MyPooledConnection(Connection connection, boolean isBusy) {
        this.connection = connection;
        this.isBusy = isBusy;
    }

    public ResultSet query(String sql) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public void close() {
        this.isBusy = false;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
