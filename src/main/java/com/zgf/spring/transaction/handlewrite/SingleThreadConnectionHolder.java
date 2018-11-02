/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/28
 * Description: SingleThreadConnectionHolder
 */
package com.zgf.spring.transaction.handlewrite;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 在Spring中，有时候我们是不是要配置多个数据源DataSource？很显然，Spring需要通过DataSource来得到操作
 * 数据库的管道Connection，这有点类似于JNDI查找。
 * 这里通过ConnectionHolder类来完成这个过程，需要思考的是在多线程下，这显然是存在问题的。为避免多线程问题，难道我们采用线程安全的Map，
 * 比如ConcurrentHashMap，其实我们真正的目的是什么？是保证一个线程下，
 * 一个事务的多个操作拿到的是一个Connection，显然使用ConcurrentHashMap根本无法保证
 *
 * @author zhangguifeng
 * @create 2018-09-28 14:33
 **/
public class SingleThreadConnectionHolder {

    // 通过ThreadaLocal这么封装一下，立刻就变成了线程的局部变量，不仅仅安全了，还保证了一个线程下面的操作拿到的Connection是同一个对象
    private static ThreadLocal<ConnectionHolder> threadLocal = new ThreadLocal<>();

    private static ConnectionHolder getConnectionHolder() {
        ConnectionHolder connectionHolder = threadLocal.get();
        if (connectionHolder == null) {
            connectionHolder = new ConnectionHolder();
            threadLocal.set(connectionHolder);
        }

        return connectionHolder;
    }

    public static Connection getConnection(DataSource dataSource) throws SQLException {
        return getConnectionHolder().getConnectionByDateSource(dataSource);
    }
}
