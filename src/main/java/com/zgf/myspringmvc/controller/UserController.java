/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/13
 * Description: UserController
 */
package com.zgf.myspringmvc.controller;

import com.zgf.mybatis.handlerwrite.MyDefaultSqlSession;
import com.zgf.mybatis.handlerwrite.MySqlSession;
import com.zgf.mybatis.handlerwrite.SigninSchoolTask;
import com.zgf.mybatis.handlerwrite.StudentMapper;
import com.zgf.myspringmvc.annotation.Controller;
import com.zgf.myspringmvc.annotation.Qualifier;
import com.zgf.myspringmvc.annotation.RequestMapping;
import com.zgf.myspringmvc.service.UserService;

/**
 * UserController
 *
 * @author zhangguifeng
 * @create 2018-09-13 16:10
 **/
@Controller("userController")
@RequestMapping("/user")
public class UserController {

    @Qualifier("userService")
    private UserService userService;

    @RequestMapping("/insert")
    public void insert() {
        userService.insert();
    }

    @RequestMapping("/update")
    public void update() {
        userService.update();

    }
}
