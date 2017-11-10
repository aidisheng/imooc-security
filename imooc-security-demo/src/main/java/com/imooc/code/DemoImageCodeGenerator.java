package com.imooc.code;

import com.imooc.security.core.properties.validate.code.ImageCode;
import com.imooc.security.core.properties.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by 邓仁波 on 2017-11-9.
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode createImageCode(ServletWebRequest request) {
        System.out.println("生成图像验证码");
        return null;
    }
}
