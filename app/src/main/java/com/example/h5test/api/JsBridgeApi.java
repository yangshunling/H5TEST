package com.example.h5test.api;

import android.util.Log;
import android.webkit.JavascriptInterface;

import wendu.dsbridge.CompletionHandler;

/**
 * @Description: JsApi
 * @Author: Anonymous
 * @Time: 2020/11/16 17:09
 */
public class JsBridgeApi {

    //用于同步调用
    @JavascriptInterface
    public void fromJS(Object msg, CompletionHandler<String> handler){
        Log.v("TAG", msg.toString());
        handler.complete("我是Android返回的消息");
    }
}