package com.danier.web.java.dynamicProxy;

/**
 * 测试动态代理方式
 * @author Danier
 * @data 2014-11-18
 */
public class TestDynamicProxy {

    public static void main(String[] args) {
        UserHandler userHandler = new UserHandler();
        UserServiceImpl impl = new UserServiceImpl();
        UserService proxy = (UserService) userHandler.bind(impl);
        proxy.queryUser();
        proxy.updateUser();
    }
}
