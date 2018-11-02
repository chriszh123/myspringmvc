/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/13
 * Description: MyProxy
 */
package com.zgf.proxy.jdk.handlewrite;

import org.springframework.util.FileCopyUtils;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * MyProxy
 *
 * @author zhangguifeng
 * @create 2018-09-13 20:37
 **/
public class MyProxy {

    private static final String rt = "\r";

    /**
     * 第一：需要根据interfaces接口构造出动态代理类需要的方法。（其实就是利用反射获取）
     * 第二：把动态生成的代理类（即.java文件）进行编译，生成字节码文件（即.class文件），然后利用类加载进行加载
     * 第三：动态代理类进行加载后，利用反射机制，通过构造方法进行实例化，并在实例化时，初始化业务Hanlder
     *
     * @param loader
     * @param interfaces
     * @param h
     * @return
     * @throws IllegalArgumentException
     */
    public static Object newProxyInstance(MyClassLoader loader, Class<?>[] interfaces, MyInvocationHandler h)
            throws IllegalArgumentException {
        if (null == h) {
            throw new NullPointerException();
        }
        Method[] methods = interfaces[0].getMethods();
        StringBuffer proxyClassString = new StringBuffer();
        proxyClassString.append("package ")
                .append(loader.getProxyClassPackage()).append(";").append(rt)
                .append("import java.lang.reflect.Method;").append(rt)
                .append("public class $MyProxy0 implements ").append(interfaces[0].getName()).append("{").append(rt)
                .append("MyInvocationHandler h;").append(rt)
                .append("public $MyProxy0(MyInvocationHandler h) {").append(rt)
                .append("this.h = h;}").append(rt)
                .append(getMethodString(methods, interfaces[0]))
                .append("}");

        String fileName = loader.getDir() + File.separator + "$MyProxy0.java";
        File myProxyFile = new File(fileName);
        try {
            compile(proxyClassString, myProxyFile);
            Class $myProxy0 = loader.findClass("$MyProxy0");
            Constructor constructor = $myProxy0.getConstructor(MyInvocationHandler.class);
            Object o = constructor.newInstance(h);

            return o;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private static void compile(StringBuffer proxtClassString, File myProxyFile) throws IOException {
        FileCopyUtils.copy(proxtClassString.toString().getBytes(), myProxyFile);
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null, null, null);
        Iterable javaFileObjects = standardJavaFileManager.getJavaFileObjects(myProxyFile);
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardJavaFileManager, null, null, null, javaFileObjects);
        boolean success = task.call();
        System.out.println("[MyProxy -> compile] success : " + success);
        standardJavaFileManager.close();
    }

    private static String getMethodString(Method[] methods, Class interfaces) {
        StringBuffer methodStringBuffer = new StringBuffer();
        for (Method method : methods) {
            methodStringBuffer.append("@Override").append(rt)
                    .append("public void ").append(method.getName()).append("()").append(" throws Throwable { ")
                    .append(" Method method1 = ").append(interfaces.getName())
                    .append(".class.getMethod(\"").append(method.getName())
                    .append("\", null);")
                    .append(" this.h.invoke(this, method1, null);}").append(rt);
        }
        return methodStringBuffer.toString();
    }
}
