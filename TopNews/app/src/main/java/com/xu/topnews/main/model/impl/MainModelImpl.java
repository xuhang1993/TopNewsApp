package com.xu.topnews.main.model.impl;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;

import com.xu.appbaseui.bottomnavigationbar.navigationbar.XuBaseNavigationTabBar;
import com.xu.topnews.R;
import com.xu.topnews.main.model.imodel.IMainModel;
import com.xu.topnews.module.newlists.ui.fragment.NewsListFragment;
import com.xu.topnews.module.newspicture.ui.NewsPictureFragment;
import com.xu.topnews.module.newsvideo.ui.NewsVideoFragment;
import com.xu.topnews.module.person.ui.PersonFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16413 on 2017/6/2.
 */

public class MainModelImpl implements IMainModel {

    private Context mContext;

    public MainModelImpl(Context context) {
        mContext = context;
    }

    @Override
    public ArrayList<XuBaseNavigationTabBar.Model> addData() {
        final String[] colors = mContext.getResources().getStringArray(R.array.default_preview);
        ArrayList<XuBaseNavigationTabBar.Model> models = new ArrayList<>();

        models.add(
                new XuBaseNavigationTabBar.Model.Builder
                        (mContext.getResources().getDrawable(R.drawable.navigation_unselet_news_icon), Color.parseColor(colors[0]))
                        .title(mContext.getResources().getString(R.string.app_navigationbar_news))
                        .build()
        );
        models.add(
                new XuBaseNavigationTabBar.Model.Builder
                        (mContext.getResources().getDrawable(R.drawable.navigation_unseleted_video_icon), Color.parseColor(colors[0]))
                        .title(mContext.getResources().getString(R.string.app_navigationbar_video))
                        .build()
        );

        models.add(
                new XuBaseNavigationTabBar.Model.Builder
                        (mContext.getResources().getDrawable(R.drawable.navigation_unseleted_picture), Color.parseColor(colors[0]))
                        .title(mContext.getResources().getString(R.string.app_navigationbar_picture))
                        .build()
        );

        models.add(
                new XuBaseNavigationTabBar.Model.Builder
                        (mContext.getResources().getDrawable(R.drawable.navigation_unseleted_me_icon), Color.parseColor(colors[0]))
                        .title(mContext.getResources().getString(R.string.app_navigationbar_me))
                        .build()
        );

        return models;
    }

    @Override
    public List<Fragment> addFragment() {
        List<Fragment> fragmentList = new ArrayList<>();
        NewsListFragment fragment = new NewsListFragment();
        NewsVideoFragment videoFragment = new NewsVideoFragment();
        NewsPictureFragment pictureFragment = new NewsPictureFragment();
        PersonFragment personFragment = new PersonFragment();

        fragmentList.add(fragment);
        fragmentList.add(videoFragment);
        fragmentList.add(pictureFragment);
        fragmentList.add(personFragment);

        return fragmentList;
    }


}
