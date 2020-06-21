package com.rongxin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author com.rongxin
 */
public class TestCompile {

    static Map<String, String> testMap = new HashMap<>();

    public static void main(String[] args) {
        testMap.put("a", "bb");
        String s = testMap.get("a");
        s = "cc";
        System.out.println(testMap.get("a"));
    }
}
