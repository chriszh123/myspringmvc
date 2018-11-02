/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/13
 * Description: DispatcherServlet
 */
package com.zgf.myspringmvc.servlet;

import com.zgf.myspringmvc.annotation.*;
import com.zgf.myspringmvc.controller.UserController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 其实，以前我们定义一个Servlet，需要在web.xml中去配置，不过在Servlet3.0后出现了基于注解的Servlet。
 * 仔细观察，你会发现，这个DispatcherServlet是自启动，而且传入了一个参数。
 * 要知道，在Spring MVC中，要想基于注解，需要在配置中指明扫描的包路径
 *
 * @author zhangguifeng
 * @create 2018-09-13 14:59
 **/
@WebServlet(name = "dispatcherServlet", urlPatterns = "/*", loadOnStartup = 1,
        initParams = {@WebInitParam(name = "base-package", value = "com.zgf.myspringmvc")})
public class DispatcherServlet extends HttpServlet {

    // 扫描基包
    private String basePackage = "";
    // 基包下面所有的带包路径权限定类名
    private List<String> packageNames = new ArrayList<>();
    // 注解实例化，注解上的名称：实例化对象
    private Map<String, Object> instanceMap = new HashMap<>();
    // 带包路径权限定类名:注解上的名称
    private Map<String, String> nameMap = new HashMap<>();
    // Url地址和方法的映射关系:方法调用链
    private Map<String, Method> urlMethodMap = new HashMap<>();
    // Method和权限定类名映射关系
    private Map<Method, String> methodPackageMap = new HashMap<>();

    /**
     * 在init中，我们主要是完成了什么呢？
     * 第一，我们应该去扫描基包下的类，得到信息A
     * 第二，对于@Controller/@Service/@Repository注解而言，我们需要拿到对应的名称，并初始化它们修饰的类，形成映射关系B
     * 第三，我们还得扫描类中的字段，如果发现有@Qualifier的话，我们需要完成注入
     * 第四，我们还需要扫描@RequestMapping，完成URL到某一个Controller的某一个方法上的映射关系C
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        basePackage = config.getInitParameter("base-package");
        try {
            // 扫描基包下的权限定类名
            scanBasePackage(basePackage);
            instance(packageNames);
            // Spring 依赖注入
            springIOC();
            // URL地址与方法映射关系
            handlerUrlMethodMap();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.replaceAll(contextPath, "");
        Method method = urlMethodMap.get(path);
        if (null == method) {
            System.out.println("You are a mang liu!!!");
            return;
        }
        String packageName = methodPackageMap.get(method);
        String controllerName = nameMap.get(packageName);
        UserController userController = (UserController) instanceMap.get(controllerName);
        try {
            method.setAccessible(true);
            method.invoke(userController);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void scanBasePackage(String basePackage) {
        // 基包是X.Y.Z的形式，而URL是X/Y/Z的形式，需要转换
        URL url = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.", "/"));
        File basePackageFile = new File(url.getPath());
        System.out.println("scan:" + url.getPath());
        File[] childFiles = basePackageFile.listFiles();
        for (File file : childFiles) {
            if (file.isDirectory()) {
                scanBasePackage(basePackage + "." + file.getName());
            } else if (file.isFile()) {
                packageNames.add(basePackage + "." + file.getName().split("\\.")[0]);
            }
        }
    }

    /**
     * 完成被注解标注的类的实例化，以及和注解名称的映射
     *
     * @param packageNames
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private void instance(List<String> packageNames) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (packageNames.size() < 1) {
            return;
        }

        for (String string : packageNames) {
            Class c = Class.forName(string);
            if (c.isAnnotationPresent(Controller.class)) {
                Controller controller = (Controller) c.getAnnotation(Controller.class);
                String controllerName = controller.value();
                instanceMap.put(controllerName, c.newInstance());
                nameMap.put(string, controllerName);
            } else if (c.isAnnotationPresent(Service.class)) {
                Service service = (Service) c.getAnnotation(Service.class);
                String serviceName = service.value();
                instanceMap.put(serviceName, c.newInstance());
                nameMap.put(string, serviceName);
            } else if (c.isAnnotationPresent(Repository.class)) {
                Repository repository = (Repository) c.getAnnotation(Repository.class);
                String repositoryName = repository.value();
                instanceMap.put(repositoryName, c.newInstance());
                nameMap.put(string, repositoryName);
            }
        }
    }

    /**
     * 依赖注入
     *
     * @throws IllegalAccessException
     */
    private void springIOC() throws IllegalAccessException {
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Qualifier.class)) {
                    String name = field.getAnnotation(Qualifier.class).value();
                    field.setAccessible(true);
                    field.set(entry.getValue(), instanceMap.get(name));
                }
            }
        }
    }

    private void handlerUrlMethodMap() throws ClassNotFoundException {
        if (packageNames.size() < 1) {
            return;
        }
        for (String string : packageNames) {
            Class c = Class.forName(string);
            if (c.isAnnotationPresent(Controller.class)) {
                StringBuffer baseUrl = new StringBuffer();
                // 映射关系:controller
                if (c.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = (RequestMapping) c.getAnnotation(RequestMapping.class);
                    baseUrl.append(requestMapping.value());
                }
                // 映射关系:method
                Method[] methods = c.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                        String methodMapping = baseUrl.toString() + requestMapping.value();
                        urlMethodMap.put(methodMapping, method);
                        methodPackageMap.put(method, string);
                    }
                }
            }
        }
    }
}
