package com.example.zhiyuan.volleydemo;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.zhiyuan.volleydemo.interfac.RequestCallBack;
import com.example.zhiyuan.volleydemo.utils.MD5Encoder;
import com.example.zhiyuan.volleydemo.utils.MD5Utils;
import com.example.zhiyuan.volleydemo.utils.NetUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import static android.R.attr.tag;

/**
 * Created by zhiyuan on 17/3/22.
 */

public class NewsManger {
    public static void getNews(final Context context, final String url, HashMap<String, String> map, final RequestCallBack requestCallBack) {

        //做判断--是否有网
        boolean networkConnected = NetUtils.isNetworkConnected(context);
        //没有网络---去缓存中获取信息
        if (!networkConnected) {
            //缓存怎么处理---
            File cacheDir = context.getCacheDir();
            //拼接成对应的文件名称
            File targetFile = null;
            try {
                targetFile = new File(cacheDir, MD5Encoder.encode(url));
            } catch (Exception e) {
                e.printStackTrace();
            }
            BufferedReader bufferedReader=null;
            try {
                 bufferedReader=new BufferedReader(new FileReader(targetFile));
                StringBuilder stringBuilder=new StringBuilder();
                String line=null;
                while((line=bufferedReader.readLine())!=null){
                    //读到信息
                    stringBuilder.append(line);
                }
                //获取到了缓存的文件内容--回调
                requestCallBack.onSuccess(stringBuilder.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        } else {
            //获取缓存的文件信息--

            //去网路获取信息 http://www.baidu.com  MD5--sjsjsdbfakefhawkenfddd
            HttpManger.postRequest(context, url, map, new RequestCallBack() {
                @Override
                public void onSuccess(String result) {
                    //今日头条---缓存
                    //先存到文件中---文件 数据库 sp
                    //存到文件中---文件名（根据url定义  文件信息 文件内容）
                    File cacheDir = context.getCacheDir();
                    //拼接成对应的文件名称
                    File targetFile = null;
                    try {
                        targetFile = new File(cacheDir, MD5Encoder.encode(url));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //往文件中存储信息
                    BufferedWriter bufferedWriter = null;
                    try {
                        bufferedWriter = new BufferedWriter(new FileWriter(targetFile));
                        bufferedWriter.write(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    //从网络上读取
                    requestCallBack.onSuccess(result);
                }

                @Override
                public void onError(VolleyError error) {
                    requestCallBack.onError(error);
                }
            });
        }
    }
}
