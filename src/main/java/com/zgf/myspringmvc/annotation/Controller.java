package com.zgf.myspringmvc.annotation;

import java.lang.annotation.*;

/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/13
 * Description: ${DESCRIPTION}
 */
@Documented
@Target(ElementType.TYPE) // 作用于类上
@Retention(RetentionPolicy.RUNTIME) // 生命周期
public @interface Controller {
    /**
     * controller名称
     *
     * @return String
     */
    public String value();
}
