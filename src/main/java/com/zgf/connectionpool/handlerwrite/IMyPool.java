/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/28
 * Description: IMyPool
 */
package com.zgf.connectionpool.handlerwrite;

/**
 * IMyPool
 *
 * @author zhangguifeng
 * @create 2018-09-28 11:00
 **/
public interface IMyPool {
     MyPooledConnection getMyPooledConnection();

     void createMyPooledConnection(int count);
}
