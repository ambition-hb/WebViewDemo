package com.haobi.webviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1、使用findViewById()方法获取到WebView的实例
        WebView webView = (WebView)findViewById(R.id.web_view);
        //2、调用WebView的getSettings()方法去设置一些浏览器的属性
        //setJavaScriptEnabled()方法用于设置是否执行页面的js方法
        webView.getSettings().setJavaScriptEnabled(true);
        //3、调用WenView的setWebViewClient()方法并传入一个WebViewClient的实例
        //作用在于网页跳转的时候，目标页面仍在当前WebView中显示，而不是打开系统浏览器
        webView.setWebViewClient(new WebViewClient());
        //4、调用WebView的loadUrl()方法并将网址传入
        webView.loadUrl("http://www.baidu.com");
    }
}
