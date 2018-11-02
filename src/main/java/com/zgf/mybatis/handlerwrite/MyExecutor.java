/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/15
 * Description: MyExecutor
 */
package com.zgf.mybatis.handlerwrite;

/**
 * MyExecutor
 *
 * @author zhangguifeng
 * @create 2018-09-15 10:34
 **/
public interface MyExecutor {
    <T> T query(String statement);
}
