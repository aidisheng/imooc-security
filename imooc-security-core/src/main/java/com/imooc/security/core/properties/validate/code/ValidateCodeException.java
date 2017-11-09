package com.imooc.security.core.properties.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by 邓仁波 on 2017-11-7.
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
