/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/13
 * Description: MyInvocationHandler
 */
package com.zgf.proxy.jdk.handlewrite;

import java.lang.reflect.Method;

/**
 * MyInvocationHandler
 *
 * @author zhangguifeng
 * @create 2018-09-13 20:12
 **/
public interface MyInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
