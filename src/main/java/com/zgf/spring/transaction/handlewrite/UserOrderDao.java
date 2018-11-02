/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/28
 * Description: UserOrderDao
 */
package com.zgf.spring.transaction.handlewrite;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * UserOrderDao
 *
 * @author zhangguifeng
 * @create 2018-09-28 15:19
 **/
public class UserOrderDao {

    private DataSource dataSource;

    public UserOrderDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void order() throws SQLException {
        Connection connection = SingleThreadConnectionHolder.getConnection(dataSource);
        System.out.println("当前用户订单线程：" + Thread.currentThread().getName() + ",使用管道(hashCode)：" + connection.hashCode());
    }
}
