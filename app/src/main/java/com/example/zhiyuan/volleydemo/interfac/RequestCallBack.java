package com.example.zhiyuan.volleydemo.interfac;

import com.android.volley.VolleyError;

/**
 * Created by zhiyuan on 17/3/22.
 */

public interface RequestCallBack {
    public void onSuccess(String result);

    public void onError(VolleyError error);
}
