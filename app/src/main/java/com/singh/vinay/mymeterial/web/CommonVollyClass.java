package com.singh.vinay.mymeterial.web;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.singh.vinay.mymeterial.activities.CommonBaseActivity;
import com.singh.vinay.mymeterial.fragments.CommonBaseFragment;
import com.singh.vinay.mymeterial.utils.AppPreference;
import com.singh.vinay.mymeterial.utils.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;


/**
 * Created by root on 10/11/16.
 */
public class CommonVollyClass<T> extends CommonBaseActivity {
    private static final String TAG = CommonVollyClass.class.getSimpleName();
    protected ProgressDialog _dialog;
    protected String message = "Loading...";
    Context _context;
    Class<T> _responseType;
    String _requestUrl;
    String methodName;
    private boolean isPost;
    private boolean isForFragment;
    private boolean isUserResponse;
    private boolean isApiRequired = true;
    private boolean isToShowProgress = true;
    private String apiKey;
    private String request_type;
    private String mode;
    private CommonBaseFragment mFragment;
    JSONObject object;
    int mMODE;
    String url;
    RequestQueue queue;
    T result = null;
    String response = "";

    public CommonVollyClass(Context context, String requestUrl, Class<T> responseType, String methodName, String requstType, String Mode, JSONObject obj) {
        _context = context;
        _requestUrl = requestUrl;
        _responseType = responseType;
        this.methodName = methodName;
        this.request_type = requstType;
        this.mode = Mode;
        this.object = obj;
        if (mode.equalsIgnoreCase("1")) {
            mMODE = Request.Method.GET;
        } else {
            mMODE = Request.Method.POST;
        }
        url = _requestUrl + this.methodName;
        queue = Volley.newRequestQueue(_context);
        queue.getCache().remove(url);
        queue.getCache().clear();
        AppPreference prefs = new AppPreference(_context.getApplicationContext());
        apiKey = prefs.getStringValueForTag(Constants.API_KEY);
        if (request_type.equalsIgnoreCase("object")) {
            if (isToShowProgress) {
                _dialog = ProgressDialog.show(_context, "", message, true);
            }

            gotoJsonObject(queue);

           /* Retrofit web Call method Start from here*/
            someMethodName(object, methodName);
             /* Retrofit web Call method End from here*/
        } else {
            if (isToShowProgress) {
                _dialog = ProgressDialog.show(_context, "", message, true);
            }
            gotoJsonArray(queue);
        }
    }

    public void someMethodName(JSONObject object, String url) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        okhttp3.Request.Builder ongoing = chain.request().newBuilder();
                        ongoing.addHeader("Accept", "application/json;versions=1");
                        if (isApiRequired) {
                            ongoing.addHeader("authkey", apiKey);
                        }
                        return chain.proceed(ongoing.build());
                    }
                })
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.HOST_URL)
                .client(httpClient)
                .build();

        ApiCalls fdtgh = retrofit.create(ApiCalls.class);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), object.toString());
        Call<ResponseBody> requestCall = fdtgh.someCall(url, body);
        requestCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse) {
                try {
                    String response = rawResponse.body().string();
                    Log.d("Responce_data", response);
                    if (_dialog != null) {
                        _dialog.cancel();
                    }
                    result = new Gson().fromJson(response, _responseType);
                    if (isFragment()) {
                        mFragment.processFragmentResponse(result);
                    } else {
                        ((CommonBaseActivity) _context).processResponse(result);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (_dialog != null) {
                    _dialog.cancel();
                }
                String response = throwable.getMessage();
            }
        });
    }

    private void gotoJsonArray(RequestQueue queue) {
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(mMODE, url, object, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray resultJson) {
                if (_dialog != null) {
                    _dialog.cancel();
                }

                String resultObj = resultJson.toString();
                result = new Gson().fromJson(response, _responseType);
                if (isFragment()) {
                    mFragment.processFragmentResponse(result);
                } else {
                    ((CommonBaseActivity) _context).processResponse(result);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_dialog != null) {
                    _dialog.cancel();
                }
                System.out.println(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError, NullPointerException {
                Map<String, String> params = new HashMap<>();
                if (apiKey != null)
                    params.put("authKey", apiKey);
                return params;
            }
        };
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(300000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsObjRequest);
    }

    private void gotoJsonObject(RequestQueue queue) {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(mMODE, url, object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject resultJson) {
                if (_dialog != null) {
                    _dialog.cancel();
                }
                String resultObj = resultJson.toString();
                result = new Gson().fromJson(response, _responseType);
                if (isFragment()) {
                    mFragment.processFragmentResponse(result);
                } else {
                    ((CommonBaseActivity) _context).processResponse(result);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_dialog != null) {
                    _dialog.cancel();
                }
                System.out.println(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError, NullPointerException {
                Map<String, String> params = new HashMap<>();
               /* if(apiKey !=null)
                    params.put("authKey",apiKey);*/
                return params;
            }
        };
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(300000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsObjRequest);
    }

    public void setIsApiKeyRequired(boolean isReqoured) {
        this.isApiRequired = isReqoured;
    }

    public boolean isFragment() {
        return isForFragment;
    }

    void setFragment(CommonBaseFragment fragment) {
        mFragment = fragment;
    }

    public void setForFragment(boolean isFragment) {
        this.isForFragment = isFragment;
    }

    public void setMessage(String loadingMessage) {
        message = loadingMessage;
    }

    public void setShowProgress(boolean isToShowProgress) {
        this.isToShowProgress = isToShowProgress;
    }


}
