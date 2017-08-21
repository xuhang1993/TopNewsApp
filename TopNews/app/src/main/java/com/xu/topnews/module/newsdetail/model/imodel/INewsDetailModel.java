package com.xu.topnews.module.newsdetail.model.imodel;

import com.xu.topnews.module.OnLoadDataListener;

/**
 * Created by 16413 on 2017/6/5.
 */

public interface INewsDetailModel {

    void loadNewsDetailData(String postId, OnLoadDataListener listener);

}
