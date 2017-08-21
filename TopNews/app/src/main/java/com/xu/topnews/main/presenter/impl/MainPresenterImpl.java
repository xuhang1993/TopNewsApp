package com.xu.topnews.main.presenter.impl;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.xu.appbaseui.bottomnavigationbar.navigationbar.XuBaseNavigationTabBar;
import com.xu.topnews.main.model.imodel.IMainModel;
import com.xu.topnews.main.model.impl.MainModelImpl;
import com.xu.topnews.main.presenter.ipresenter.IMainPresenter;
import com.xu.topnews.main.ui.iview.IMainActivityView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16413 on 2017/6/2.
 */

public class MainPresenterImpl implements IMainPresenter {

    private IMainModel mMainModel;

    private IMainActivityView mActivityView;

    public MainPresenterImpl(IMainActivityView mainActivityView, Context context) {
        mMainModel = new MainModelImpl(context);
        mActivityView = mainActivityView;
    }

    @Override
    public void addNavigation() {
        ArrayList <XuBaseNavigationTabBar.Model> models = mMainModel.addData();
        mActivityView.addNavigationSuccess(models);
    }

    @Override
    public void addFragment() {
        List<Fragment> fragmentList = mMainModel.addFragment();
        mActivityView.addFragmentSuccess(fragmentList);
    }
}
