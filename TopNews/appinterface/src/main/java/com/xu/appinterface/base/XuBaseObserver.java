package com.xu.appinterface.base;


import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.xu.appcommonutils.util.NetworkUtils;
import com.xu.appinterface.callback.XuBaseApiCallBack;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by 16413 on 2017/5/25.
 */

public class XuBaseObserver<T> implements Observer<T> {

    public XuBaseApiCallBack <T> callBack;

    public XuBaseObserver(XuBaseApiCallBack<T> callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (callBack != null){
            callBack.loadBefore();
        }
    }

    @Override
    public void onNext(@NonNull T t) {
        if (callBack != null){
            callBack.loadSuccess(t);
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (callBack != null){
            String errorMsg = null;
            if (e instanceof HttpException) {
                switch (((HttpException) e).code()) {
                    case 403:
                        errorMsg = "没有权限访问此链接！";
                        break;
                    case 504:
                        if (NetworkUtils.isConnected()) {
                            errorMsg = "网络连接超时！";
                        } else {
                            errorMsg = "没有联网哦！";
                        }
                        break;
                    default:
                        errorMsg = ((HttpException) e).message();
                        break;
                }
            } else if (e instanceof UnknownHostException) {
                if (NetworkUtils.isConnected()) {
                    errorMsg = "获取数据失败，对不起哦！";
                } else {
                    errorMsg = "没有联网哦！检查下网络吧！";
                }
            } else if (e instanceof SocketTimeoutException) {
                errorMsg = "网络连接超时！";
            }else {
                errorMsg = "未知异常！";
            }
            callBack.loadFailure(errorMsg);
        }
    }

    @Override
    public void onComplete() {
        if (callBack != null){
            callBack.loadComplete();
        }
    }
}
