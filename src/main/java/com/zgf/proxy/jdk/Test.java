/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/13
 * Description: test
 */
package com.zgf.proxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * JDK自带动态代理
 * 动态代理的过程：
 * 第一：Proxy通过传递给它的参数（interfaces/invocationHandler）生成代理类$Proxy0；
 * 第二：Proxy通过传递给它的参数（ClassLoader）来加载生成的代理类$Proxy0的字节码文件；
 *
 * @author zhangguifeng
 * @create 2018-09-13 19:51
 **/
public class Test {
    public static void main(String[] args) throws Throwable {
        Man man = new Zgf();
        ManHandler manHandler = new ManHandler(man);
        Man manProxy = (Man) Proxy.newProxyInstance(man.getClass().getClassLoader(), new Class[]{Man.class}, manHandler);

        System.out.println(manProxy.getClass().getName());
        manProxy.findObject();
        // 生成class文件
        createProxyFile(Man.class);
    }

    /**
     * $Proxy是实现了我们的业务接口（Man）的，所以客户端显然可以调用业务接口的方法。
     * 其次，注意到$Proxy是继承自Proxy，并通过构造方法将业务处理类传入给父类Proxy进行初始化。
     * （实质上，你可以看看源码，在Proxy中存在protected InvocationHandler h;）
     *
     * @param c
     */
    private static void createProxyFile(Class c) {
        byte[] datas = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{c});
        try {
            FileOutputStream fos = new FileOutputStream("$Proxy0.class");
            fos.write(datas);
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
