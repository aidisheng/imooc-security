package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by 邓仁波 on 2017-11-9.
 */
public interface ValidateCodeGenerator {
    ValidateCode createImageCode(ServletWebRequest request);
}
