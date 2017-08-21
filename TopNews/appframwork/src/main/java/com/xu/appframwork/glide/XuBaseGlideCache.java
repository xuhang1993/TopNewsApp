package com.xu.appframwork.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;
import com.xu.appframwork.resourcedefinition.XuBaseResourcedefinition;

/**
 * Created by 16413 on 2017/6/4.
 */

public class XuBaseGlideCache implements GlideModule {
    //设置缓存的大小为100M
    private static int CacheSize = 10 * 1024 * 1024;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //设置图片的显示格式ARGB_8888(指图片大小为32bit)
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //设置磁盘缓存目录（和创建的缓存目录相同）
        String downloadDirectoryPath = XuBaseResourcedefinition.appFileImagePath();
        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, CacheSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
