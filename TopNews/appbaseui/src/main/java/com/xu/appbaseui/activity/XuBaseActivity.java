package com.xu.appbaseui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: XuBaseActivity
 * @Title:
 * @Description: baseActivity
 * @Package com.xu.appbaseui.activity
 * @date 2017/6/8 11:23
 */

public abstract class XuBaseActivity extends AppCompatActivity {
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    public abstract int getLayoutId();

    public abstract void initView();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.ACTION_DOWN){
            onDownKeyBoardBack();
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onDownKeyBoardBack(){

    }


}
