/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/15
 * Description: MySqlSession
 */
package com.zgf.mybatis.handlerwrite;

/**
 * 对外暴露的API接口（MySqlSession）
 *
 * @author zhangguifeng
 * @create 2018-09-15 16:38
 **/
public interface MySqlSession {
    <T> T selectOne(String sql);

    <T> T getMapper(Class<T> var);
}
