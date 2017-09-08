package com.xu.topnews.module.person.ui;


import android.view.View;

import com.xu.appbaseui.fragment.XuBaseFragment;
import com.xu.appbaseui.viewpagertitle.XuBaseViewPagerLine;
import com.xu.topnews.R;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: PersonFragment
 * @Title:
 * @Description: 个人页面
 * @Package com.xu.topnews.module.person.ui
 * @date 2017/9/7 15:54
 */
public class PersonFragment extends XuBaseFragment {


    @Override
    protected void iniView(View mView) {
        XuBaseViewPagerLine pagerLine = (XuBaseViewPagerLine) mView.findViewById(R.id.pager_line);
    }

    

    @Override
    protected int getmContentId() {
        return R.layout.fragment_person;
    }

}
