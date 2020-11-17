package com.example.h5test.webview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.h5test.R;
import com.example.h5test.main.BaseActivity;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.HashMap;

import androidx.annotation.Nullable;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

/**
 * @Description: X5Activity
 * @Author: Anonymous
 * @Time: 2020/9/17 0:03
 */
public class X5Activity extends BaseActivity {

    private SmoothProgressBar mProgressbar;
    private WebView mWebview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        HashMap map = new HashMap();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(map);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x5);
        initView();
        initWebView();
    }

    private void initView() {
        mProgressbar = findViewById(R.id.progressbar);
        mWebview = findViewById(R.id.webview);
    }

    private void initWebView() {
        WebSettings mWebSettings = mWebview.getSettings();
        //与Javascript交互
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setBlockNetworkImage(false);
        //设置自适应屏幕，两者合用
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLoadWithOverviewMode(true);
        //设置可以访问文件
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置编码格式
        mWebSettings.setDefaultTextEncodingName("utf-8");
        //关闭webview中缓存
        mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启DOM storage API 功能
        mWebSettings.setDomStorageEnabled(true);
        mWebview.loadUrl(getIntent().getStringExtra("url"));
        mWebview.setWebViewClient(new MyWebviewClient());
        mWebview.setWebChromeClient(new MyChromeClient());
    }

    /**
     * 重写MyWebviewClient方法
     * <p>
     * shouldOverrideUrlLoading（） 拦截网页跳转，是之继续在WebView中进行跳转
     * onPageStarted（） 开始加载的时候（显示进度条）
     * onPageFinished（） 夹在结束的时候（隐藏进度条）
     */
    private class MyWebviewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String s) {
            webView.loadUrl(s);
            return false;
        }

        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
            mProgressbar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
            mProgressbar.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 重写MyChromeClient方法
     * <p>
     * onProgressChanged（） 设置动态进度条
     * onReceivedTitle（） 设置WebView的头部标题
     * onReceivedIcon（）  设置WebView的头部图标
     */
    private class MyChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
        }

        @Override
        public void onReceivedTitle(WebView webView, String s) {
            super.onReceivedTitle(webView, s);
        }

        @Override
        public void onReceivedIcon(WebView webView, Bitmap bitmap) {
            super.onReceivedIcon(webView, bitmap);
        }
    }

    /**
     * 实现WebView的回退栈
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()) {
            mWebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
