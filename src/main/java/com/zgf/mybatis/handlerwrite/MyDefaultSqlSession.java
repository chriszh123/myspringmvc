/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/15
 * Description: MyDefaultSqlSession
 */
package com.zgf.mybatis.handlerwrite;

import java.lang.reflect.Proxy;

/**
 * 第一，MyDefaultSqlSession持有执行器的引用，调用selectOne等方法，就是在调用执行器的query方法。
 * 第二，在getMapper的获取过程中，我们看到了具体业务处理Handler的身影：MyMapperProxy，
 * 根据JDK动态代理的知识，最终都是要回调Handler的invoke方法完成的。
 *
 * @author zhangguifeng
 * @create 2018-09-15 16:40
 **/
public class MyDefaultSqlSession implements MySqlSession {
    private MyExecutor executor = new MyBaseExecutor();

    @Override
    public <T> T selectOne(String sql) {
        return executor.query(sql);
    }

    @Override
    public <T> T getMapper(Class<T> interfaces) {
        T mapper = (T) Proxy.newProxyInstance(interfaces.getClassLoader(), new Class[]{interfaces}, new MyMapperProxy(this));
        return mapper;
    }
}
