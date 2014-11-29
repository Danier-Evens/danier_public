package com.danier.web.java.rmi.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 远程的接口的实现
 * @author Danier
 * @data 2014-11-18
 */
@SuppressWarnings("serial")
public class AddServerImpl extends UnicastRemoteObject implements AddServer {

    public AddServerImpl() throws RemoteException {
        super();
    }

    /**
     * 简单业务实现返回两个数相加的结果
     */
    public int addNumbers(int firstnumber, int secondnumber) throws RemoteException {
        return firstnumber + secondnumber;
    }

    /**
     * 
     */
    public void sayHello(String name) throws RemoteException {
        System.out.println("hello " + name + "! conccention success!");
    }
}
