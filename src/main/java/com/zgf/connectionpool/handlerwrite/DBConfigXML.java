/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/28
 * Description: DBConfigXML
 */
package com.zgf.connectionpool.handlerwrite;

/**
 * 在实际中使用数据库连接池，需要在Spring的配置文件中，进行一些参数配置。这里，为了简化解析，直接提供
 *
 * @author zhangguifeng
 * @create 2018-09-28 10:38
 **/
public class DBConfigXML {
    public static final String jdbcDriver = "com.mysql.jdbc.Driver";
    public static final String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/wec_biz_counselor_signin?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    public static final String jdbcUserName = "root";
    public static final String jdbcPwd = "123456";

    public static final int initCount = 10;
    // 连接池不足时增长的步进值
    public static final int step = 2;
    public static final int maxCount = 50;
}
