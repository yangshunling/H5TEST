package com.example.h5test.webview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.example.h5test.R;
import com.example.h5test.main.BaseActivity;
import com.example.h5test.api.JsBridgeApi;

import java.util.HashMap;

import androidx.annotation.Nullable;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import wendu.dsbridge.DWebView;
import wendu.dsbridge.OnReturnValue;

/**
 * @Description: WebActivity
 * @Author: Anonymous
 * @Time: 2020/9/17 0:01
 */
public class WebActivity extends BaseActivity {

    private SmoothProgressBar mProgressbar;
    private DWebView mWebView;
    private Button mSendMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        initWebview();
    }

    private void initView() {
        mWebView = findViewById(R.id.webview);
        mProgressbar = findViewById(R.id.progressbar);
        mSendMessage = findViewById(R.id.sendMessage);
        mSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                map.put("name", "yangsl");
                mWebView.callHandler("FromAndroid", new Object[]{"我是Android发出的消息"}, new OnReturnValue<String>() {
                    @Override
                    public void onValue(String retValue) {
                        Toast.makeText(WebActivity.this, retValue, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initWebview() {
        //开启调试模式
        mWebView.setWebContentsDebuggingEnabled(true);
        mWebView.addJavascriptInterface(new JsBridgeApi(), "JSBridge");
        WebSettings mWebSettings = mWebView.getSettings();
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
        mWebView.loadUrl(getIntent().getStringExtra("url"));
        mWebView.setWebViewClient(new MyWebviewClient());
        mWebView.setWebChromeClient(new MyChromeClient());
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
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mProgressbar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
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
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
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
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
