package com.espire.practice;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurentHashMapExcample {
    public static void main(String[] args) {
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        //map.put("Hello",null);
        HashMap<String,String> map1 = new HashMap<>();
        map1.put("Hello",null);
        map1.put(null,"null");
        map1.put(null,null);
        map1.put(null,null);
        map1.put(null,"ssss");
        System.out.println(map1.put(null,"ssss11"));

    }
}
