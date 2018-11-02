/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/13
 * Description: UserServiceImpl
 */
package com.zgf.myspringmvc.service.impl;

import com.zgf.mybatis.handlerwrite.MyDefaultSqlSession;
import com.zgf.mybatis.handlerwrite.MySqlSession;
import com.zgf.mybatis.handlerwrite.SigninSchoolTask;
import com.zgf.mybatis.handlerwrite.StudentMapper;
import com.zgf.myspringmvc.annotation.Qualifier;
import com.zgf.myspringmvc.annotation.Service;
import com.zgf.myspringmvc.dao.UserDao;
import com.zgf.myspringmvc.service.UserService;

/**
 * UserServiceImpl
 *
 * @author zhangguifeng
 * @create 2018-09-13 16:06
 **/
@Service("userService")
public class UserServiceImpl implements UserService {
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public void insert() {
        System.out.println("[UserServiceImpl] -> insert: start");
        userDao.insert();
        System.out.println("[UserServiceImpl] -> insert: end");
    }

    @Override
    public void update() {
        System.out.println("[UserServiceImpl] -> udpate: start");
        userDao.update();
        System.out.println("[UserServiceImpl] -> udpate: end");
    }
}
