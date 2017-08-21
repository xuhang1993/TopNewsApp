package com.xu.topnews.module.newlists.ui.iview;

/**
 * Created by 16413 on 2017/5/18.
 */

public interface INewsListFragmentView {

    void loadDataSuccess(Object o);

    void showProgress();

    void hideProgress();

    void loadDataFailer(String message);

}
