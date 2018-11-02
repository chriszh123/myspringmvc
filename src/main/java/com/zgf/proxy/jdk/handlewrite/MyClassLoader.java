/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/13
 * Description: MyClassLoader
 */
package com.zgf.proxy.jdk.handlewrite;

import lombok.Data;
import org.springframework.util.FileCopyUtils;

import java.io.File;

/**
 * 利用自定义的类加载器,在内存中生成动态代理类
 *
 * @author zhangguifeng
 * @create 2018-09-13 20:16
 **/
@Data
public class MyClassLoader extends ClassLoader {
    // 生成的代理类加载路径
    private File dir;
    private String proxyClassPackage;

    public MyClassLoader(String path, String proxyClassPackage) {
        this.dir = new File(path);
        this.proxyClassPackage = proxyClassPackage;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (null != dir) {
            File classFile = new File(dir, name + ".class");
            if (classFile.exists()) {
                try {
                    byte[] classBytes = FileCopyUtils.copyToByteArray(classFile);
                    return this.defineClass(proxyClassPackage + "." + name, classBytes, 0, classBytes.length);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        return super.findClass(name);
    }
}
