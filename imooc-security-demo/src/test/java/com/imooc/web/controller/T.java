package com.imooc.web.controller;

import com.imooc.security.core.properties.LoginType;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by 邓仁波 on 2017-11-2.
 */
public class T {
    @Test
    public void f(){
        System.out.println(LoginType.JSON.toString().equals("JSON"));

    }
}
