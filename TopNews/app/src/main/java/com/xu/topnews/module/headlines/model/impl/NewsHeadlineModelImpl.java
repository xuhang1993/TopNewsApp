package com.xu.topnews.module.headlines.model.impl;

import com.xu.topnews.module.OnLoadDataListener;
import com.xu.topnews.module.headlines.model.data.TopNewsTopChannel;
import com.xu.topnews.module.headlines.model.imodel.INewsHeadlineModel;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 16413 on 2017/9/6.
 */

public class NewsHeadlineModelImpl implements INewsHeadlineModel {

    @Override
    public void loadChannelList(final OnLoadDataListener listner) {

        BmobQuery<TopNewsTopChannel> channelQuery = new BmobQuery<>();
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        channelQuery.setLimit(50);
        channelQuery.findObjects(new FindListener<TopNewsTopChannel>() {
            @Override
            public void done(List<TopNewsTopChannel> list, BmobException e) {
                if (e == null){
                    listner.loadSuccess(list);
                }else {
                    listner.loadFailure(e.getMessage());
                }
            }
        });
    }
}
