package com.zgf.proxy.jdk.handlewrite;import java.lang.reflect.Method;public class $MyProxy0 implements com.zgf.proxy.jdk.Man{MyInvocationHandler h;public $MyProxy0(MyInvocationHandler h) {this.h = h;}@Overridepublic void findObject() throws Throwable {  Method method1 = com.zgf.proxy.jdk.Man.class.getMethod("findObject", null); this.h.invoke(this, method1, null);}}