package com.example.h5test;

import android.app.Application;
import android.util.Log;

import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import java.util.HashMap;

/**
 * @Description: MyApplication
 * @Author: Anonymous
 * @Time: 2020/9/16 23:12
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initX5();
    }

    private void initX5() {
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                Log.v("x5", "下载完成");
            }

            @Override
            public void onInstallFinish(int i) {
                Log.v("x5", "安装完成");
            }

            @Override
            public void onDownloadProgress(int i) {
                Log.v("x5", "正在下载:" + i);
            }
        });
        QbSdk.initX5Environment(getApplicationContext(), new QbSdk.PreInitCallback() {

            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核
                Log.v("x5", "x5初始化:" + b);
            }
        });
    }
}
