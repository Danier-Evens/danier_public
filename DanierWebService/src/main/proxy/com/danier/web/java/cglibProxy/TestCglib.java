package com.danier.web.java.cglibProxy;


/**
 * Cglib动态代理 JDK的动态代理机制只能代理实现了接口的类，而不能实现接口的类就不能实现JDK的动态代理，cglib是针对类来实现代理的，
 * 他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的类进行代理。
 * @author Danier
 * @data 2014-11-18
 */
public class TestCglib {

    public static void main(String[] args) {
        UserCglib cglib = new UserCglib();
        UserServiceImpl bookCglib = (UserServiceImpl) cglib.getInstance(new UserServiceImpl());
        bookCglib.queryUser();
        bookCglib.updateUser();
        
    }
}
