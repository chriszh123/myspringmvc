/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/13
 * Description: ManHandler
 */
package com.zgf.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ManHandler
 *
 * @author zhangguifeng
 * @create 2018-09-13 19:46
 **/
public class ManHandler implements InvocationHandler {
    private Man man;

    public ManHandler(Man man) {
        this.man = man;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(man, null);
        after();
        return null;
    }

    private void before() {
        System.out.println("[ManHandler]->invoke->before!");
    }

    private void after() {
        System.out.println("[ManHandler]->invoke->after!");
    }
}
