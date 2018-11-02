/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/13
 * Description: MyHandler
 */
package com.zgf.proxy.jdk.handlewrite;

import com.zgf.proxy.jdk.Man;

import java.lang.reflect.Method;

/**
 * MyHandler
 *
 * @author zhangguifeng
 * @create 2018-09-13 20:13
 **/
public class MyHandler implements MyInvocationHandler {
    private Man man;

    public MyHandler(Man man) {
        this.man = man;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(man, null);
        after();
        return result;
    }

    private void before() {
        System.out.println("[MyHandler]->invoke->before!");
    }

    private void after() {
        System.out.println("[MyHandler]->invoke->after!");
    }
}
