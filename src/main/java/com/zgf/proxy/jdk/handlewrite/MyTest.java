/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/13
 * Description: MyTest
 */
package com.zgf.proxy.jdk.handlewrite;

import com.zgf.proxy.jdk.Man;
import com.zgf.proxy.jdk.Zgf;

/**
 * MyTest:自定义手写动态代理
 *
 * @author zhangguifeng
 * @create 2018-09-13 21:19
 **/
public class MyTest {
    public static void main(String[] args) throws Throwable {
        Man man = new Zgf();
        MyHandler myHandler = new MyHandler(man);
        MyClassLoader classLoader = new MyClassLoader("E:\\pro\\code\\myspringmvc\\src\\main\\java\\com\\zgf\\proxy\\jdk\\handlewrite",
                "com.zgf.proxy.jdk.handlewrite");
        Man manProxy = (Man) MyProxy.newProxyInstance(classLoader, new Class[]{Man.class}, myHandler);
        System.out.println(manProxy.getClass().getName());
        manProxy.findObject();
    }
}
