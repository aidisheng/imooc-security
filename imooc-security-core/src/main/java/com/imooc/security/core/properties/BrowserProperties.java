package com.imooc.security.core.properties;

/**
 * Created by 邓仁波 on 2017-11-3.
 */
public class BrowserProperties {
    private String loginPage = "/imooc-signIn.html";
    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}

