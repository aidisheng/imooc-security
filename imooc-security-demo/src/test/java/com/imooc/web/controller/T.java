package com.imooc.web.controller;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 邓仁波 on 2017-11-2.
 */
public class T {
    @Test
    public void f() {
        RestTemplate template = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("date", "2017-11-8");
        String forObject = template.getForObject("https://javawind.net/p131", String.class, map);

        System.out.println(forObject);
    }
}
