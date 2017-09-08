package com.xu.topnews.module.headlines.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xu.appbaseui.fragment.XuBaseFragment;
import com.xu.appbaseui.viewpagertitle.XuBaseViewPagerTitle;
import com.xu.appcommonutils.util.ToastUtils;
import com.xu.topnews.R;
import com.xu.topnews.module.headlines.model.data.TopNewsTopChannel;
import com.xu.topnews.module.headlines.presenter.impl.NewsChannelListPresenterImpl;
import com.xu.topnews.module.headlines.presenter.ipresenter.INewsChannelListPresenter;
import com.xu.topnews.module.headlines.ui.iview.INewsHeadLinesView;
import com.xu.topnews.module.newlists.ui.fragment.NewsListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.xu.topnews.constant.XuConstant.NEWS_HEAD_LINE;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: NewsHeadLinesFragment
 * @Title:
 * @Description: 头条
 * @Package com.xu.topnews.module.headlines.ui.fragment
 * @date 2017/9/6 10:57
 */
public class NewsHeadLinesFragment extends XuBaseFragment implements INewsHeadLinesView{

    @BindView(R.id.vpt_newsheadline)
    public XuBaseViewPagerTitle mViewPagerTitle;

    @BindView(R.id.vp_newsheadline)
    public ViewPager mViewPager;

    private INewsChannelListPresenter mPresenter;

    private List<String> mViewTitles;

    private List<NewsListFragment> mFragmentLists;
    @Override
    protected void iniView(View mView) {
        mViewTitles = new ArrayList<>();
        mFragmentLists = new ArrayList<>();
        mPresenter = new NewsChannelListPresenterImpl(this);
        mPresenter.loadNewsChannel();

    }
    @Override
    protected int getmContentId() {
        return R.layout.fragment_news_head_lines;
    }
    @Override
    public void loadDataSuccess(Object o) {
        List<TopNewsTopChannel> channelList = (List<TopNewsTopChannel>) o;
        for (TopNewsTopChannel channel : channelList) {
            mViewTitles.add(channel.channelName);
            NewsListFragment fragment = NewsListFragment.newInstance(NEWS_HEAD_LINE,channel.channelId);
            mFragmentLists.add(fragment);
        }
        mViewPagerTitle.initData(mViewTitles, mViewPager, 0);
        MyAdapter adapter = new MyAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void loadDataFail(String error) {
        ToastUtils.showShort(error);
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

}
