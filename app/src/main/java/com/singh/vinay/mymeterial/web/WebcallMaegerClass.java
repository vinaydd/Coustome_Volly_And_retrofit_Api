package com.singh.vinay.mymeterial.web;

import android.content.Context;

import com.singh.vinay.mymeterial.respoce.LoginResponse;
import com.singh.vinay.mymeterial.utils.Constants;

import org.json.JSONObject;

/**
 * Created by root on 10/11/16.
 */
public class WebcallMaegerClass {
    private static WebcallMaegerClass singleInstance;
    protected WebcallMaegerClass() {
    }
    public static WebcallMaegerClass getInstance() {
        if (singleInstance == null) {
            singleInstance = new WebcallMaegerClass();
        }
        return singleInstance;
    }
    public  void  getLogin(Context context,String requstType,String Mode,JSONObject obj ,boolean isProgress){
        CommonVollyClass<LoginResponse> httptask = new CommonVollyClass<>(context, Constants.HOST_URL, LoginResponse.class, Constants.LOGIN_URL,requstType,Mode,obj);
        httptask.setIsApiKeyRequired(false);
        httptask.setForFragment(false);
        httptask.setShowProgress(isProgress);
    }

}
