package com.rongxin.test;

/**
 * @author com.rongxin
 */
public class HeiheiServiceImpl implements HeiheiService {

    @Override
    public String sayHeihei(String somebody) {
        return "----------- hei " + somebody;
    }
}
