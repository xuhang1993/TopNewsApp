package com.xu.appbaseui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 16413 on 2017/5/4.
 */
public abstract class XuBaseFragment extends Fragment {
    //布局
    protected View mView;
    //是否初始化
    protected boolean mIsInit;
    //是否加载过
    protected boolean mIsLoad;
    private Unbinder mUnBinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getmContentId(),container,false);
        mUnBinder = ButterKnife.bind(this, mView);
        iniView(mView);
        return mView;
    }

    protected void isCanLoadData(){}

    protected abstract void iniView(View mView);

    protected abstract int getmContentId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsInit = false;
        mUnBinder.unbind();
    }
}
