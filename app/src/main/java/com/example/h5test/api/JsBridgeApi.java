package com.example.h5test.api;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.webkit.JavascriptInterface;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.RequiresApi;
import wendu.dsbridge.CompletionHandler;

/**
 * @Description: JsApi
 * @Author: Anonymous
 * @Time: 2020/11/16 17:09
 */
public class JsBridgeApi {

    //用于同步调用
    @SuppressLint("JavascriptInterface")
    @JavascriptInterface
    String testSyn(JSONObject jsonObject) throws JSONException {
        Log.v("TAG",jsonObject.toString());
        return jsonObject.getString("msg") + "［syn call］";
    }
    //用于异步调用
    @SuppressLint("JavascriptInterface")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @JavascriptInterface
    void testAsyn(JSONObject jsonObject, CompletionHandler handler) throws JSONException {
        handler.complete(jsonObject.getString("msg")+" [asyn call]");
    }

}