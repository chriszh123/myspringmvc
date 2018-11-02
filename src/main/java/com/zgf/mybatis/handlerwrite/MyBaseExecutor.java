/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/15
 * Description: MyBaseExecutor
 */
package com.zgf.mybatis.handlerwrite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 在MyBatis中，比如说select有多种形式，比如selectOne,selectList，那么其实到最后，
 * 还是向JDBC发出一个SQL而已。对于执行器而言，其实对于查询，提供一个query接口就可以了。
 * 这里，为了简便，直接执行已经处理好的SQL语句（动态SQL以及输入类型，这不是迷你版MyBatis关心的）。
 * 另外执行器的实现类MyBaseExecutor其实就是一段JDBC的操作代码。
 *
 * @author zhangguifeng
 * @create 2018-09-15 10:35
 **/
public class MyBaseExecutor implements MyExecutor {

    @Override
    public <T> T query(String statement) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://172.16.7.111:3306/wec_biz_counselor_signin", "admin", "wisedu@2016");
            preparedStatement = connection.prepareStatement(statement);
            resultSet = preparedStatement.executeQuery();
            SigninSchoolTask task = null;
            if (resultSet.next()) {
                task = new SigninSchoolTask();
                task.setWid(resultSet.getLong("wid"));
                task.setTaskName(resultSet.getString("task_name"));
            }

            return (T) task;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
