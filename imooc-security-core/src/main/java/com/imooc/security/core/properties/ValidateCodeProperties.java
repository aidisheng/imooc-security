package com.imooc.security.core.properties;

/**
 * Created by 邓仁波 on 2017-11-9.
 */
public class ValidateCodeProperties {
    private ImageCodeProperties image=new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
