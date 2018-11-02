/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/28
 * Description: MyDefaultPool
 */
package com.zgf.connectionpool.handlerwrite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * MyDefaultPool持有一个管道集合，基于多线程的考虑，这里使用了Vector
 *
 * @author zhangguifeng
 * @create 2018-09-28 11:10
 **/
public class MyDefaultPool implements IMyPool {

    private Vector<MyPooledConnection> myPooledConnectionVector = new Vector<>();
    private static String jdbcUrl;
    private static String jdbcUserName;
    private static String jdbcPwd;
    private static int initCount;
    private static int step;
    private static int maxCount;

    public MyDefaultPool() {
        init();
        try {
            Class.forName(DBConfigXML.jdbcDriver);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        createMyPooledConnection(initCount);
    }

    private void init() {
        jdbcUrl = DBConfigXML.jdbcUrl;
        jdbcUserName = DBConfigXML.jdbcUserName;
        jdbcPwd = DBConfigXML.jdbcPwd;
        initCount = DBConfigXML.initCount;
        step = DBConfigXML.step;
        maxCount = DBConfigXML.maxCount;
    }

    @Override
    public MyPooledConnection getMyPooledConnection() {
        if (myPooledConnectionVector.size() < 1) {
            throw new RuntimeException("连接池初始化错误");
        }
        MyPooledConnection myPooledConnection = null;
        try {
            myPooledConnection = getRealConnectionFromPool();

            while (myPooledConnection == null) {
                createMyPooledConnection(step);
                myPooledConnection = getRealConnectionFromPool();
                return myPooledConnection;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return myPooledConnection;
    }

    @Override
    public void createMyPooledConnection(int count) {
        if (myPooledConnectionVector.size() > maxCount || (myPooledConnectionVector.size() + count) > maxCount) {
            throw new RuntimeException("连接池已满");
        }
        for (int i = 0; i < count; i++) {
            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUserName, jdbcPwd);
                MyPooledConnection myPooledConnection = new MyPooledConnection(connection, false);
                myPooledConnectionVector.add(myPooledConnection);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 第一，这里使用了synchronized，就是为了避免多线程下产生问题。
     * 第二，要知道Connection是有超时机制的，如果我们得到的管道的Connection已经超时了怎么办呢？
     * 第三，得到管道后，一定注意isBusy的设置。
     *
     * @return
     * @throws SQLException
     */
    private synchronized MyPooledConnection getRealConnectionFromPool() throws SQLException {
        for (MyPooledConnection myPooledConnection : myPooledConnectionVector) {
            if (!myPooledConnection.isBusy()) {
                if (myPooledConnection.getConnection().isValid(3000)) {
                    myPooledConnection.setBusy(true);
                    return myPooledConnection;
                } else {
                    Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUserName, jdbcPwd);
                    myPooledConnection.setConnection(connection);
                    myPooledConnection.setBusy(true);
                    return myPooledConnection;
                }
            }
        }
        return null;
    }
}
