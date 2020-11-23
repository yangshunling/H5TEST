package com.example.h5test.callback;

import wendu.dsbridge.CompletionHandler;

/**
 * @Description: JSCallback
 * @Author: Anonymous
 * @Time: 2020/11/23 16:24
 */
public interface JSCallback {
    void getVersionCode(CompletionHandler<String> handler);
}