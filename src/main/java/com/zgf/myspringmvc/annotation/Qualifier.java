package com.zgf.myspringmvc.annotation;

import java.lang.annotation.*;

/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/13
 * Description: ${DESCRIPTION}
 */
@Documented
@Target(ElementType.FIELD) // 作用于字段上,实现注入
@Retention(RetentionPolicy.RUNTIME)
public @interface Qualifier {
    public String value();
}
