/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/15
 * Description: BootStrap
 */
package com.zgf.mybatis.handlerwrite;

/**
 * BootStrap
 *
 * @author zhangguifeng
 * @create 2018-09-15 16:49
 **/
public class BootStrap {
    public static void main(String[] args) {
        MySqlSession sqlSession = new MyDefaultSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Long schoolTaskWid = 100L;
        SigninSchoolTask schoolTask = studentMapper.findTaskById(schoolTaskWid);
        System.out.println("schoolTask : " + schoolTask.getTaskName());
    }
}
