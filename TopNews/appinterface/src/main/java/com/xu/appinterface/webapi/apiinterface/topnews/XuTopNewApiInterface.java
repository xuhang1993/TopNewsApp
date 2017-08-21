package com.xu.appinterface.webapi.apiinterface.topnews;

import com.xu.appinterface.webapi.apimodel.topnews.XuTopNewsApiModel;
import com.xu.appinterface.webapi.apimodel.topnewsdetail.XuTopNewsDetailModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 新闻头条api
 */
public interface XuTopNewApiInterface {
    //http://c.m.163.com/nc/article/headline/T1348647909107/1-20.html
    //http://c.m.163.com/headline/T1348647909107/0-20.html http/1.1
    /**
     * 获取头条新闻
     * @param type
     * @param id
     * @param pageIndex
     * @return
     */
    @GET("/nc/article/{typepath}/{idpath}/{pageindexpath}-{rowscount}.html")
    Observable<XuTopNewsApiModel> getTopNewsList(@Path("typepath")String type, @Path("idpath")String id, @Path("pageindexpath")int pageIndex, @Path("rowscount") int rowsCount);

    //新闻详情：例子：http://c.m.163.com/nc/article/BFNFMVO800034JAU/full.html

    /**
     * 获取新闻详情
     * @param postId
     * @return
     */
    @GET("/nc/article/{postid}/full.html")
    Observable<Map<String, XuTopNewsDetailModel>> getTopNewsDetail(@Path("postid")String postId);


}
