package com.xu.topnews.module.headlines.model.impl;

import com.xu.topnews.module.OnLoadDataListener;
import com.xu.topnews.module.headlines.model.data.TopNewsTopChannel;
import com.xu.topnews.module.headlines.model.imodel.INewsHeadlineModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16413 on 2017/9/6.
 */

public class NewsHeadlineModelImpl implements INewsHeadlineModel {

    @Override
    public void loadChannelList(final OnLoadDataListener listner) {

        List<TopNewsTopChannel> list = new ArrayList<>();
        TopNewsTopChannel topNewsTopChannel = new TopNewsTopChannel("头条", "T1348647909107");
        TopNewsTopChannel topNewsTopChannel2 = new TopNewsTopChannel("娱乐", "T1348648517839");
        TopNewsTopChannel topNewsTopChannel3 = new TopNewsTopChannel("体育", "T1348649079062");
        list.add(topNewsTopChannel);
        list.add(topNewsTopChannel2);
        list.add(topNewsTopChannel3);
        listner.loadSuccess(list);
//        BmobQuery<TopNewsTopChannel> channelQuery = new BmobQuery<>();
//        //返回50条数据，如果不加上这条语句，默认返回10条数据
//        channelQuery.setLimit(50);
//        channelQuery.findObjects(new FindListener<TopNewsTopChannel>() {
//            @Override
//            public void done(List<TopNewsTopChannel> list, BmobException e) {
//                if (e == null){
//                    listner.loadSuccess(list);
//                }else {
//                    listner.loadFailure(e.getMessage());
//                }
//            }
//        });
    }
}
