package com.xu.topnews.module;

/**
 * Created by 16413 on 2017/5/22.
 */

public interface OnLoadDataListener {

    void loadSuccess(Object o);
    void loadFailure(String errorMessage);

}
