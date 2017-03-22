package com.example.zhiyuan.volleydemo;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.zhiyuan.volleydemo.interfac.RequestCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhiyuan on 17/3/22.
 */

public class HttpManger {
    //提供网络请求的方法

    /**
     * GET请求请求参数放到地址栏后边
     *
     * @param context
     * @param url
     * @param requestCallBack
     */
    public static void getRequest(Context context, String url, final RequestCallBack requestCallBack) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //定义一个请求
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                requestCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestCallBack.onError(error);
            }
        });

        //进行请求
        requestQueue.add(stringRequest);
    }

    /**
     * post请求  HashMap<String,String> map=new HashMap</String,String>();
     * map.put("key","ddjdjdjd);
     * map.put("city","beijing");
     */
    public static void postRequest(Context context, String url, final HashMap<String, String> paramMap, final RequestCallBack requestCallBack) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //Post请求参数-请求实体里边
        //定义一个请求
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                requestCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestCallBack.onError(error);
            }
        }) {
            //定义post请求的请求参数
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return paramMap;
            }
        };

        //进行请求
        requestQueue.add(stringRequest);
    }
}
