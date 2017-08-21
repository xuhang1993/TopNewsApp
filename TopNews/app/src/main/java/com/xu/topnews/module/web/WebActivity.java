package com.xu.topnews.module.web;

import android.content.Intent;
import android.webkit.WebView;

import com.xu.appbaseui.activity.XuBaseActivity;
import com.xu.appbaseui.topnavigationbar.XuBaseTopNavigationBar;
import com.xu.topnews.R;
import com.xu.topnews.main.ui.activity.MainActivity;

import butterknife.BindView;

public class WebActivity extends XuBaseActivity{

    @BindView(R.id.top_view)
    XuBaseTopNavigationBar mTopNavigationBar;

    @BindView(R.id.web_view)
    WebView mWebView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initView() {
        mTopNavigationBar.setmClickNavigationLeftImageButtonListener(new XuBaseTopNavigationBar.OnClickNavigationLeftImageButtonListener() {
            @Override
            public void clickLeftImageButton() {
                Intent intent = new Intent(WebActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mWebView.loadUrl("https://www.baidu.com/");
    }
}
