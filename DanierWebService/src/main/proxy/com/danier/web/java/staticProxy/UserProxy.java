package com.danier.web.java.staticProxy;


/**
 * 静态代理方式下的UserService的代理操作类
 * @author Danier
 * @data 2014-11-18
 */
public class UserProxy implements UserService {

    private UserServiceImpl serviceImpl;

    public UserProxy(UserServiceImpl serviceImpl) {
        super();
        this.serviceImpl = serviceImpl;
    }

    public void queryUser() {
        System.out.println("queryUser 事务处理之前");
        // 调用委托类的方法;
        serviceImpl.queryUser();
        System.out.println("queryUser 事务处理之后");
    }

    public void updateUser() {
        System.out.println("updateUser 事务处理之前");
        // 调用委托类的方法;
        serviceImpl.updateUser();
        System.out.println("updateUser 事务处理之后");
    }
}
