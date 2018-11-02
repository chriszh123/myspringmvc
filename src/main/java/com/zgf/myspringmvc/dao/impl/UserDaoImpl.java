/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/13
 * Description: Dao Impl
 */
package com.zgf.myspringmvc.dao.impl;

import com.zgf.mybatis.handlerwrite.MyDefaultSqlSession;
import com.zgf.mybatis.handlerwrite.MySqlSession;
import com.zgf.mybatis.handlerwrite.SigninSchoolTask;
import com.zgf.mybatis.handlerwrite.StudentMapper;
import com.zgf.myspringmvc.annotation.Repository;
import com.zgf.myspringmvc.dao.UserDao;

/**
 * Dao Impl
 *
 * @author zhangguifeng
 * @create 2018-09-13 16:04
 **/
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Override
    public void insert() {
        System.out.println("[UserDaoImpl] -> insert ok!!!");
    }

    @Override
    public void update() {
//        testMybatis();
        System.out.println("[UserDaoImpl] -> update ok!!!");
    }

    private void testMybatis() {
        MySqlSession sqlSession = new MyDefaultSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Long schoolTaskWid = 100L;
        SigninSchoolTask schoolTask = studentMapper.findTaskById(schoolTaskWid);
        System.out.println("schoolTask : " + schoolTask);
    }
}
