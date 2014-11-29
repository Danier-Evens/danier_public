package com.danier.web.java.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 定义一个远程接口，必须继承Remote接口，其中需要远程调用的方法必须抛出RemoteException异常
 * @author Danier
 * @data 2014-11-18
 */
public interface AddServer extends Remote {

    public void sayHello(String name) throws RemoteException;

    public int addNumbers(int firstnumber, int secondnumber) throws RemoteException;
}
