package com.example.h5test.api;

import android.webkit.JavascriptInterface;

import com.example.h5test.callback.JSCallback;

import wendu.dsbridge.CompletionHandler;

/**
 * @Description: JsApi
 * @Author: Anonymous
 * @Time: 2020/11/16 17:09
 */
public class JsBridgeApi {

    private JSCallback mCallback;

    public JsBridgeApi(JSCallback callback) {
        mCallback = callback;
    }

    @JavascriptInterface
    public void getVersionCode(Object msg, CompletionHandler<String> handler) {
        mCallback.getVersionCode(handler);
    }

}