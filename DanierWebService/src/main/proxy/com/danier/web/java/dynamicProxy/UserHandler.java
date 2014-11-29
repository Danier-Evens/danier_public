package com.danier.web.java.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理下的UserService的代理操作类
 * @author Danier
 * @data 2014-11-18
 */
public class UserHandler implements InvocationHandler {

    private Object target;

    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + "：事物开始");
        Object invoke = method.invoke(target, args);
        System.out.println(method.getName() + "：事物结束");
        return invoke;
    }
}
