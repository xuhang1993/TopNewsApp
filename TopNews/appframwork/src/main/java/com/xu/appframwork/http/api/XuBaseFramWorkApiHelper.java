package com.xu.appframwork.http.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xu.appcommonutils.util.FileUtils;
import com.xu.appframwork.configer.XuBaseFramWorkConfig;
import com.xu.appframwork.http.interceptor.XuBaseNetWorkInterceptor;
import com.xu.appframwork.http.interceptor.XuBaseRewriteCacheInterceptor;
import com.xu.appframwork.resourcedefinition.XuBaseResourcedefinition;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * baseFramWorkApi
 */
public class XuBaseFramWorkApiHelper {

    //链接超时
    private static long CONNECTION_TIME_OUT = 15;
    //单例
    private static XuBaseFramWorkApiHelper baseFramWorkApi = null;
    //Retrofit
    private  Retrofit mRetrofit;
    //OKhttpClient
    private static OkHttpClient mOkHttpClient;
    //设置缓存10m
    private static final int CACHE_FILE_SEZI = 10 * 1024 * 1024;
    /**
     * 双重校验锁
     * @return
     */
    public static XuBaseFramWorkApiHelper getInstance(){
        if (baseFramWorkApi == null){
            synchronized (XuBaseFramWorkApiHelper.class){
                if (baseFramWorkApi == null){
                    baseFramWorkApi = new XuBaseFramWorkApiHelper();
                }
            }
        }
        return baseFramWorkApi;
    }

    /**
     * 获取Retrofit
     * @return
     */
    public Retrofit getRetrofit(){
        if (mRetrofit == null){
            synchronized (XuBaseFramWorkApiHelper.class){
                if (mRetrofit == null){
                       /*获取retrofi对象*/
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.setLenient().create();
                    mRetrofit = new Retrofit.Builder()
                            .baseUrl(XuBaseFramWorkConfig.getInstance().getBaseUrl())
                            .client(getHttpClient())
                            .addConverterFactory(GsonConverterFactory.create(gson))//创建gson,对json进行转换
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//Rxjava
                            .build();
                }
            }
        }
        return mRetrofit;
    }


    /**
     *  获取Client
     * @return
     */
    private OkHttpClient getHttpClient(){
        if (mOkHttpClient == null){
            synchronized (XuBaseFramWorkApiHelper.class){
                if (mOkHttpClient == null){

                    //日志显示级别
                    HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
                    //新建log拦截器
                    HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            Log.d("xuhang","OkHttp====Message:"+message);
                        }
                    });
                    loggingInterceptor.setLevel(level);
                    Cache cache = new Cache(FileUtils.getFileByPath(XuBaseResourcedefinition.appFileCachePath()),CACHE_FILE_SEZI);
                    OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
                    mOkHttpClient = okHttpBuilder
                            //链接时长
                            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                            //读取时长
                            .readTimeout(CONNECTION_TIME_OUT,TimeUnit.SECONDS)
                            //写入时长
                            .writeTimeout(CONNECTION_TIME_OUT,TimeUnit.SECONDS)
                            //添加应用拦截器
                            .addInterceptor(loggingInterceptor)
                            .addInterceptor(new XuBaseRewriteCacheInterceptor())
                            //添加网络拦截器
                            .addNetworkInterceptor(new XuBaseNetWorkInterceptor())
                            .cache(cache)
                            .build();
                }
            }
        }
        return mOkHttpClient;
    }



    /**
     * 获取baseUrl
     * @return
     */
    public String getBaseUrl(){
        return XuBaseFramWorkConfig.getInstance().getApiTheme() + XuBaseFramWorkConfig.getInstance().getApiHost();
    }

}
