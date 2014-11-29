package com.danier.web.dubbo.provide;

import java.util.List;

/**
 * 暴露的服务方接口
 * @author Danier
 */
public interface DemoProvideService {

    String sayHello(String name);

    public List<User> getUsers();
}