package com.imooc.security.core.validate.code.sms;

/**
 * Created by 邓仁波 on 2017-11-13.
 */
public interface SmsCodeSender {
    void send(String mobile,String code);
}
