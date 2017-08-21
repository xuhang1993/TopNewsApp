package com.xu.appframwork.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by 16413 on 2017/6/4.
 */

public class XuBaseGlideUtils {

//    DiskCacheStrategy.NONE(不做任何磁盘缓存)
//
//    DiskCacheStrategy.RESULT(缓存转换后的资源)
//
//    DiskCacheStrategy.SOURCE(缓存源资源)

    /**
     *  加载图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImageView(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }

    /**
     * 加载GIF动画图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadGifImageView(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).asGif().into(imageView);
    }

    /**
     * 只能加载静态图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadBitmapImageView(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).asBitmap().into(imageView);
    }

    /**
     * 加载图片 设置宽高
     * @param context
     * @param url
     * @param overrideHeight
     * @param overrideWidth
     * @param imageView
     */
    public static void loadImageViewSize(Context context, String url, int overrideHeight, int overrideWidth, ImageView imageView){
        Glide.with(context).load(url).override(overrideWidth,overrideHeight).into(imageView);
    }

    /**
     * 设置加载前默认和失败图片
     * @param context
     * @param url
     * @param placeId
     * @param errorId
     * @param imageView
     */
    public static void loadDefaultImageView(Context context, String url, int placeId, int errorId, ImageView imageView){
        Glide.with(context).load(url).placeholder(placeId).error(errorId).into(imageView);
    }

    /**
     * 设置加载前默认和失败图片 设置宽高
     * @param context
     * @param url
     * @param placeId
     * @param errorId
     * @param overrideHeight
     * @param overrideWidth
     * @param imageView
     */
    public static void loadDefaultImageViewSize(Context context, String url, int placeId, int errorId, int overrideHeight, int overrideWidth, ImageView imageView){
        Glide.with(context).load(url).placeholder(placeId).error(errorId).override(overrideWidth,overrideHeight).into(imageView);
    }

    /**
     * 设置加载前默认和失败图片  图片动画
     * @param context
     * @param url
     * @param placeId
     * @param errorId
     * @param animateId
     * @param imageView
     */
    public static void loadAnimateImageView(Context context, String url, int placeId, int errorId, int animateId, ImageView imageView){
        Glide.with(context).load(url).placeholder(placeId).error(errorId).animate(animateId).into(imageView);
    }


}
