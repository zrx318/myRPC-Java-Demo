package com.rongxin.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author com.rongxin
 */
public class MainServer {

    public static void main(String[] args) throws Exception {
        //发布服务
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("WmRpcDemo-server.xml");
        System.out.println(" 服务发布完成");
    }
}
