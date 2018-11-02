/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/28
 * Description: Test
 */
package com.zgf.connectionpool.handlerwrite;

import java.sql.ResultSet;

/**
 * Test
 *
 * @author zhangguifeng
 * @create 2018-09-28 11:34
 **/
public class Test {
    public static IMyPool myPool = MyPoolFactory.getInstance();

    public static void main(String[] args) {
        int index = 0;
        for (int i = 0; i < 10; i++) {
            MyPooledConnection myPooledConnection = myPool.getMyPooledConnection();
            ResultSet resultSet = myPooledConnection.query("select * from t_coun_signin_school_task");
            try {
                while (resultSet.next()) {
                    index++;
                    String taskName = resultSet.getString("task_name");
                    String taskDescUrl = resultSet.getString("task_desc_url");
                    System.out.println(index + "【" + taskName + "】taskDescUrl:" + taskDescUrl);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                myPooledConnection.close();
            }
        }
    }
}
