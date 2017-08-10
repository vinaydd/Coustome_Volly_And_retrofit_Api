package com.singh.vinay.mymeterial.activities;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.singh.vinay.mymeterial.R;
import com.singh.vinay.mymeterial.model.Model;
import com.singh.vinay.mymeterial.respoce.BaseResponse;
import com.singh.vinay.mymeterial.respoce.LoginResponse;
import com.singh.vinay.mymeterial.utils.Constants;
import com.singh.vinay.mymeterial.web.ApiCalls;
import com.singh.vinay.mymeterial.web.WebcallMaegerClass;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Url;

/**
 * Created by root on 7/11/16.
 */
public class Login extends CommonBaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginnew);
        Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SlidingMenuActvity.class);
                startActivity(intent);
            }
        });


       /* JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("source","delhi");
            jsonObject.put("destination","mumbai");
            jsonObject.put("availability_date","2017-08-06");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/  JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("vehicleNumber","UP200002");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        WebcallMaegerClass.getInstance().getLogin(this, Constants.JSON_OBJECTS,Constants.POST_REQUEST,jsonObject ,true);
    }
    @Override
    public <T> void processResponse(T result) {
        super.processResponse(result);
        BaseResponse baseResponse  = (BaseResponse)result;
        if (result instanceof LoginResponse){
            LoginResponse resp = (LoginResponse) result;
        }
    }
}
