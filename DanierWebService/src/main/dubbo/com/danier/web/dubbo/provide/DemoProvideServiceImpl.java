package com.danier.web.dubbo.provide;

import java.util.ArrayList;
import java.util.List;

/**
 * 暴露的服务方接口实现
 * @author Danier
 */
public class DemoProvideServiceImpl implements DemoProvideService {

    public String sayHello(String name) {
        return "Hello " + name;
    }

    public List<User> getUsers() {
        List<User> list = new ArrayList<User>();
        User u1 = new User();
        u1.setName("jack");
        u1.setAge(20);
        u1.setSex("哈哈");
        User u2 = new User();
        u2.setName("tom");
        u2.setAge(21);
        u2.setSex("嘿嘿");
        User u3 = new User();
        u3.setName("rose");
        u3.setAge(19);
        u3.setSex("呵呵");
        list.add(u1);
        list.add(u2);
        list.add(u3);
        return list;
    }
}
