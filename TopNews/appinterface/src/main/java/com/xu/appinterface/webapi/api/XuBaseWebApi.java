package com.xu.appinterface.webapi.api;

import com.xu.appframwork.http.api.XuBaseFramWorkApiHelper;
import com.xu.appinterface.base.XuBaseObserver;
import com.xu.appinterface.base.apithread.XuBaseSchedulerTransformer;
import com.xu.appinterface.callback.XuBaseApiCallBack;
import com.xu.appinterface.webapi.apiinterface.topnews.XuTopNewApiInterface;
import com.xu.appinterface.webapi.apimodel.topnews.XuTopNewsApiModel;
import com.xu.appinterface.webapi.apimodel.topnewsdetail.XuTopNewsDetailModel;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;

/**
 * Created by 16413 on 2017/4/26.
 */
public class XuBaseWebApi {

    private static XuBaseWebApi xuBaseWebApi = null;

    public static XuBaseWebApi getInstance(){
        if (xuBaseWebApi == null){
            synchronized (XuBaseWebApi.class){
                if (xuBaseWebApi == null){
                    xuBaseWebApi = new XuBaseWebApi();
                }
            }
        }
        return xuBaseWebApi;
    }

    /**
     * 获取新闻列表
     * @param type
     * @param id
     * @param pageIndex
     * @param rowsCount
     */
    public void getTopNews(final String type, final String id, final int pageIndex, final int rowsCount, final XuBaseApiCallBack<XuTopNewsApiModel> callBack) {
        Retrofit retrofit = XuBaseFramWorkApiHelper.getInstance().getRetrofit();
        XuTopNewApiInterface topNews = retrofit.create(XuTopNewApiInterface.class);
        Observable<XuTopNewsApiModel> newsList = topNews.getTopNewsList(type, id, pageIndex, rowsCount);
        newsList.compose(new XuBaseSchedulerTransformer<XuTopNewsApiModel>())
               .subscribe(new XuBaseObserver<XuTopNewsApiModel>(callBack));
    }

    /**
     * 获取新闻详情
     * @param postId
     * @param callBack
     */
    public void getNewsDetail(final String postId, final XuBaseApiCallBack<XuTopNewsDetailModel> callBack){
        Retrofit retrofit = XuBaseFramWorkApiHelper.getInstance().getRetrofit();
        XuTopNewApiInterface apiInterface = retrofit.create(XuTopNewApiInterface.class);
        Observable<Map<String, XuTopNewsDetailModel>> newsDetail = apiInterface.getTopNewsDetail(postId);
        newsDetail.compose(new XuBaseSchedulerTransformer<Map<String, XuTopNewsDetailModel>>())
                .map(new Function<Map<String,XuTopNewsDetailModel>, XuTopNewsDetailModel>() {
                    @Override
                    public XuTopNewsDetailModel apply(@NonNull Map<String, XuTopNewsDetailModel> map) throws Exception {
                        return map.get(postId);
                    }
                })
                .subscribe(new XuBaseObserver<XuTopNewsDetailModel>(callBack));
    }

}
