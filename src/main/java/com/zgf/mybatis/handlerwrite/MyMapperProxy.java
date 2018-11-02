/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/15
 * Description: MyMapperProxy
 */
package com.zgf.mybatis.handlerwrite;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 当invoke方法被调用时，根据调用的方法，进行反射，得到namespace以及对应的SQL，
 * 然后，我们把SQL交给sqlSession进行执行即可。
 *
 * @author zhangguifeng
 * @create 2018-09-15 16:43
 **/
public class MyMapperProxy implements InvocationHandler {

    private MySqlSession sqlSession;

    public MyMapperProxy() {
    }

    public MyMapperProxy(MySqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mapperClass = method.getDeclaringClass().getName();
        System.out.println("mapperClass:" + mapperClass);
        if (StudentMapperXML.namespace.equals(mapperClass)) {
            String methodName = method.getName();
            System.out.println("methodName:" + methodName);
            String sql = StudentMapperXML.getMethodSql(methodName);
            String frtSql = String.format(sql, String.valueOf(args[0]));
            System.out.println("frtSql:" + frtSql);
            return sqlSession.selectOne(frtSql);
        }

        return null;
    }
}
