package com.xu.appframwork.http.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * http拦截器
 */
public class XuBaseNetWorkInterceptor implements Interceptor{

    // 在线缓存10秒内直接读缓存
    private static final int CACHE_AGE_SEC = 10;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request.newBuilder()
                .removeHeader("Pragma").removeHeader("Cache-Control")
                .addHeader("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_AGE_SEC).build();
        Response response = chain.proceed(request);

        return response.newBuilder()
                .removeHeader("Pragma").removeHeader("Cache-Control")
                .addHeader("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_AGE_SEC).build();
    }
}
