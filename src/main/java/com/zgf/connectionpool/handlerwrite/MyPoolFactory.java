/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/28
 * Description: MyPoolFactory
 */
package com.zgf.connectionpool.handlerwrite;

/**
 * MyPoolFactory
 *
 * @author zhangguifeng
 * @create 2018-09-28 11:32
 **/
public class MyPoolFactory {
    public static class CreatePool {
        public static IMyPool myPool = new MyDefaultPool();
    }

    public static IMyPool getInstance() {
        return CreatePool.myPool;
    }
}
