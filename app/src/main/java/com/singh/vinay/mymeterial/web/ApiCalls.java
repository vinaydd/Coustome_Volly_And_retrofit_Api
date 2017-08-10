package com.singh.vinay.mymeterial.web;



import java.util.HashMap;

import okhttp3.RequestBody;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by sharadsingh on 10/08/17.
 */

public interface ApiCalls{
    @POST
    Call<ResponseBody> someCall(@Url String url, @Body RequestBody params);
}