package com.danier.web.java.rmi.service.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.danier.web.java.rmi.service.AddServer;

/**
 * 客户端测试，在客户端调用远程对象上的远程方法，并返回结果。
 * @author Danier
 * @data 2014-11-18
 */
public class RmiClient {

    public static void main(String args[]) throws RemoteException, MalformedURLException, NotBoundException {
        String url = "rmi://127.0.0.1/Hello";
        AddServer add = (AddServer) Naming.lookup(url);
        add.sayHello("Danier");
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result = add.addNumbers(10, i);
            System.out.println(result);
        }
    }
}
