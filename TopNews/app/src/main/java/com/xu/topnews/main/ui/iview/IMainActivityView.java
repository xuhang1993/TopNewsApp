package com.xu.topnews.main.ui.iview;


import android.support.v4.app.Fragment;

import com.xu.appbaseui.bottomnavigationbar.navigationbar.XuBaseNavigationTabBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16413 on 2017/6/2.
 */

public interface IMainActivityView {

    void addNavigationSuccess(ArrayList<XuBaseNavigationTabBar.Model> lists);

    void addFragmentSuccess(List<Fragment> fragmentList);

    void addNavigation();

    void addFragment();

}
