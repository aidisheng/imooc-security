package com.imooc.security.core.properties;

/**
 * Created by 邓仁波 on 2017-11-9.
 */
public class ImageCodeProperties {
    //验证码宽度
    private int width = 67;
    //验证码高度
    private int height = 23;
    //验证码位数
    private int lenght = 4;
    //验证码有效时间
    private int expireIn = 60;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }
}
