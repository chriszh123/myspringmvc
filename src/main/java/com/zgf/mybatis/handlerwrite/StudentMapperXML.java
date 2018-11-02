/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/15
 * Description: StudentMapperXML
 */
package com.zgf.mybatis.handlerwrite;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper.xml
 * 为了不牵涉到XML的解析过程，直接提供已经处理完毕的结果。
 * 其实就是namespace/statementID/SQL的存储、映射。
 *
 * @author zhangguifeng
 * @create 2018-09-15 10:49
 **/
public class StudentMapperXML {
    public static final String namespace = "com.zgf.mybatis.handlerwrite.StudentMapper";
    private static Map<String, String> methodSqlMap = new HashMap<>();

    static {
        methodSqlMap.put("findTaskById", "select * from t_coun_signin_school_task where wid = %s");
    }

    public static String getMethodSql(String method) {
        return methodSqlMap.get(method);
    }
}
