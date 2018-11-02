/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/28
 * Description: Test
 */
package com.zgf.spring.transaction.handlewrite;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * Spring事务设计思想之手写实现
 *
 * @author zhangguifeng
 * @create 2018-09-28 15:22
 **/
public class Test {
    public static final String jdbcDriver = "com.mysql.jdbc.Driver";
    public static final String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/wec_biz_counselor_signin?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    public static final String jdbcUserName = "root";
    public static final String jdbcPwd = "123456";

    public static void main(String[] args) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(jdbcDriver);
        basicDataSource.setUrl(jdbcUrl);
        basicDataSource.setUsername(jdbcUserName);
        basicDataSource.setPassword(jdbcPwd);

        final UserService userService = new UserService(basicDataSource);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    userService.action();
                }
            }).start();
        }

        try {
            Thread.sleep(10000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
