package com.imooc.security.core.properties.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by 邓仁波 on 2017-11-9.
 */
public interface ValidateCodeGenerator {
    ImageCode createImageCode(ServletWebRequest request);
}
