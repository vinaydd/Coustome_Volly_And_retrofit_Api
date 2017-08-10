package com.singh.vinay.mymeterial.respoce;


import com.singh.vinay.mymeterial.model.LoginModel;

/**
 * Created by root on 4/7/16.
 */
public class LoginResponse extends BaseResponse {

    private LoginModel data;
    private String logintime;

    public String getLogintime() {
        return this.logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }


    public LoginModel getData() {
        return data;
    }

    public void setData(LoginModel data) {
        this.data = data;
    }
}
