package com.xu.topnews.main.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.xu.appbaseui.activity.XuBaseActivity;
import com.xu.appbaseui.bottomnavigationbar.navigationbar.XuBaseNavigationTabBar;
import com.xu.appbaseui.topnavigationbar.XuBaseTopNavigationBar;
import com.xu.appbaseui.viewpager.XuBaseNoScrollViewPager;
import com.xu.topnews.R;
import com.xu.topnews.main.presenter.impl.MainPresenterImpl;
import com.xu.topnews.main.presenter.ipresenter.IMainPresenter;
import com.xu.topnews.main.ui.iview.IMainActivityView;
import com.xu.topnews.module.headlines.ui.fragment.NewsHeadLinesFragment;
import com.xu.topnews.module.newspicture.ui.NewsPictureFragment;
import com.xu.topnews.module.newsvideo.ui.NewsVideoFragment;
import com.xu.topnews.module.person.ui.PersonFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;


public class MainActivity extends XuBaseActivity implements IMainActivityView, ViewPager.OnPageChangeListener {
    //view集合
    private List<Fragment> mFragmentLists;

    //navigationtabbar
    @BindView(R.id.navigation_bar)
    XuBaseNavigationTabBar mNavigationTabBar;

    @BindView(R.id.fragment_viewpager)
    //viewpager
    XuBaseNoScrollViewPager mViewPager;

    @BindView(R.id.top_view)
    XuBaseTopNavigationBar mTopNavigationBar;

    @BindString(R.string.app_mainactivity_title)
    String mMainTitle;

    @BindString(R.string.app_mainactivity_video_title)
    String mMainVideoTitle;

    @BindString(R.string.app_mainactivity_picture_title)
    String mMainPictureTitle;

    @BindString(R.string.app_mainactivity_person_title)
    String mMainPersonTitle;

    //presenter
    private IMainPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mPresenter = new MainPresenterImpl(this,this);
        mViewPager.setPagingEnabled(false);
        addNavigation();
        addFragment();
        mNavigationTabBar.setOnPageChangeListener(this);
        mTopNavigationBar.setNavigationTitle(mMainTitle);
    }


    @Override
    public void addNavigationSuccess(ArrayList<XuBaseNavigationTabBar.Model> lists) {
        mNavigationTabBar.setModels(lists);
    }

    @Override
    public void addFragmentSuccess(List<Fragment> fragmentList) {
        mFragmentLists = fragmentList;
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mNavigationTabBar.setViewPager(mViewPager,0);
    }

    @Override
    public void addNavigation() {
        mPresenter.addNavigation();
    }

    @Override
    public void addFragment() {
        mPresenter.addFragment();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Fragment fragment = mFragmentLists.get(position);
        if (fragment instanceof NewsHeadLinesFragment){
            mTopNavigationBar.setNavigationTitle(mMainTitle);
        }else if (fragment instanceof NewsVideoFragment){
            mTopNavigationBar.setNavigationTitle(mMainVideoTitle);
        }else if (fragment instanceof NewsPictureFragment){
            mTopNavigationBar.setNavigationTitle(mMainPictureTitle);
        }else if (fragment instanceof PersonFragment){
            mTopNavigationBar.setNavigationTitle(mMainPersonTitle);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragmentLists.size();
        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = mFragmentLists.get(position);
            return  fragment;
        }

    }

    @Override
    protected void onDownKeyBoardBack() {
        super.onDownKeyBoardBack();

    }
}
