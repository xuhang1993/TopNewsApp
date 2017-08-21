package com.xu.topnews.main.model.imodel;

import android.support.v4.app.Fragment;

import com.xu.appbaseui.bottomnavigationbar.navigationbar.XuBaseNavigationTabBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16413 on 2017/6/2.
 */

public interface IMainModel {

    ArrayList<XuBaseNavigationTabBar.Model> addData();

    List<Fragment> addFragment();

}
