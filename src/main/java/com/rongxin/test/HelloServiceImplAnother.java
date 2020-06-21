package com.rongxin.test;

/**
 * @author com.rongxin
 */
public class HelloServiceImplAnother implements HelloService {
    @Override
    public String sayHello(String somebody) {
        return "hello another " + somebody + "!";
    }

    @Override
    public String sayHelloAnother(String somebody) {
        return "1111 another " + somebody + "!";
    }
}
