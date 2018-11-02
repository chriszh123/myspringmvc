/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/28
 * Description: UserAccountDao
 */
package com.zgf.spring.transaction.handlewrite;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * UserAccountDao
 *
 * @author zhangguifeng
 * @create 2018-09-28 15:15
 **/
public class UserAccountDao {

    private DataSource dataSource;

    public UserAccountDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void buy() throws SQLException {
        Connection connection = SingleThreadConnectionHolder.getConnection(dataSource);
        System.out.println("当前用户购买线程：" + Thread.currentThread().getName() + ",使用管道(hashCode)：" + connection.hashCode());
    }

}
