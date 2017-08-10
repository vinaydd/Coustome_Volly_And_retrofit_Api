package com.singh.vinay.mymeterial.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.singh.vinay.mymeterial.R;
import com.singh.vinay.mymeterial.respoce.BaseResponse;
import com.singh.vinay.mymeterial.respoce.LoginResponse;
import com.singh.vinay.mymeterial.utils.Constants;
import com.singh.vinay.mymeterial.web.WebcallMaegerClass;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by root on 7/11/16.
 */
public class Home_fragmets extends CommonBaseFragment{
    public static Home_fragmets newInstance() {
        Home_fragmets fragment = new Home_fragmets();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("source","delhi");
            jsonObject.put("destination","mumbai");
            jsonObject.put("availability_date","2017-08-06");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        WebcallMaegerClass.getInstance().getLogin(getActivity(), Constants.JSON_OBJECTS,Constants.POST_REQUEST,jsonObject ,true);

        return view;
    }

    @Override
    public <T> void processFragmentResponse(T result) {
        super.processFragmentResponse(result);
        BaseResponse baseResponse  = (BaseResponse)result;
        if(result instanceof LoginResponse){

        }
    }
}
