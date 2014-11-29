package com.danier.web.dubbo.consumer;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.danier.web.dubbo.provide.DemoProvideService;
import com.danier.web.dubbo.provide.User;
/**
 * 消费方进行服务方接口调用时，消费方需要引入服务方的接口以及接口的依赖类组成的jar包
 * @author Danier
 * 
 */
public class Consumer {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "dubboConsumer-applicationContext.xml" });
		context.start();
		//服务方接口
		DemoProvideService demoService = (DemoProvideService) context.getBean("demoConsumerService"); //
		String hello = demoService.sayHello("tom"); // ִ
		System.out.println(hello); //

		//调用服务方接口返回服务方user的list
		List<User> list = demoService.getUsers();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
		System.in.read();
	}

}