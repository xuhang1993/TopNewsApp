package com.xu.appinterface.base.apithread;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 16413 on 2017/5/17.
 */
public class XuBaseSchedulerTransformer<T> implements ObservableTransformer<T,T> {
    @Override
    public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }
}
