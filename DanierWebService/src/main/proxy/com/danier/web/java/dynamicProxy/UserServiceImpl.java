package com.danier.web.java.dynamicProxy;

/**
 * 接口实现
 * @author Danier
 * @data 2014-11-18
 */
public class UserServiceImpl implements UserService {

    /**
     * 查询用户
     */
    public void queryUser() {
        System.out.println("查询用户业务执行！！");
    }

    /**
     * 修改用户
     */
    public void updateUser() {
        System.out.println("修改用户业务执行！！");
    }
}
