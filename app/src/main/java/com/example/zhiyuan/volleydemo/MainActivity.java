package com.example.zhiyuan.volleydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.volley.R;
import com.example.zhiyuan.volleydemo.interfac.RequestCallBack;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void simpleRequest(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //定义一个请求
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "--"+response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "--"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //进行请求
        requestQueue.add(stringRequest);
    }

    public void sealRequest(View view) {
//        http://api.jisuapi.com/weather/city?appkey=b4d06fdd59ed379f
        HashMap<String,String> map=new HashMap<>();
        map.put("appkey","b4d06fdd59ed379f");
//        HttpManger.postRequest(this,"http://api.jisuapi.com/weather/city",map,new RequestCallBack(){
//            @Override
//            public void onSuccess(String result) {
//              // Toast.makeText(MainActivity.this, "-"+result, Toast.LENGTH_SHORT).show();
//                //今日头条---缓存
//            }
//
//            @Override
//            public void onError(VolleyError error) {
//                Toast.makeText(MainActivity.this, "失败了"+error, Toast.LENGTH_SHORT).show();
//            }
//        });

        NewsManger.getNews(this, "http://api.jisuapi.com/weather/city", map, new RequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(MainActivity.this, "--"+result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(MainActivity.this, "失败了"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
