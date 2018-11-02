/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/15
 * Description: StudentMapper
 */
package com.zgf.mybatis.handlerwrite;

/**
 * StudentMapper
 *
 * @author zhangguifeng
 * @create 2018-09-15 10:44
 **/
public interface StudentMapper {
    SigninSchoolTask findTaskById(Long wid);

    void insert(SigninSchoolTask task);
}
