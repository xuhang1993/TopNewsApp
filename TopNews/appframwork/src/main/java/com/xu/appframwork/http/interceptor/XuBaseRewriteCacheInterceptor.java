package com.xu.appframwork.http.interceptor;

import com.xu.appcommonutils.util.NetworkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 16413 on 2017/6/4.
 */

public class XuBaseRewriteCacheInterceptor implements Interceptor {

    // 设离线缓存有效期为两天
    private static final int CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isConnected()){
            CacheControl cacheControl = new CacheControl.Builder()
                    .onlyIfCached()
                    .maxStale(CACHE_STALE_SEC, TimeUnit.SECONDS)
                    .build();

            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();

        }
        return chain.proceed(request);
    }
}
