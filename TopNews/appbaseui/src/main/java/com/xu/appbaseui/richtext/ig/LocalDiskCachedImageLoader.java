package com.xu.appbaseui.richtext.ig;

import android.widget.TextView;

import com.xu.appbaseui.richtext.ImageHolder;
import com.xu.appbaseui.richtext.RichTextConfig;
import com.xu.appbaseui.richtext.callback.ImageLoadNotify;
import com.xu.appbaseui.richtext.drawable.DrawableWrapper;
import com.xu.appbaseui.richtext.exceptions.BitmapCacheLoadFailureException;
import com.xu.appbaseui.richtext.exceptions.BitmapCacheNotfoudException;


/**
 * Created by zhou on 2017/3/25.
 * 本地缓存图片加载器
 */
class LocalDiskCachedImageLoader extends AbstractImageLoader implements Runnable {

    LocalDiskCachedImageLoader(ImageHolder holder, RichTextConfig config, TextView textView, DrawableWrapper drawableWrapper, ImageLoadNotify iln) {
        //noinspection unchecked
        super(holder, config, textView, drawableWrapper, iln, null, null);
    }


    @Override
    public void run() {
        int exist = BitmapPool.getPool().exist(holder.getKey());
        if (exist < 1) {
            onFailure(new BitmapCacheNotfoudException());
        } else {
            BitmapWrapper bitmapWrapper = BitmapPool.getPool().read(holder.getKey(), true);
            if (bitmapWrapper == null) {
                onFailure(new BitmapCacheLoadFailureException());
            } else {
                sizeCacheHolder = bitmapWrapper.getSizeCacheHolder();
                onResourceReady(ImageWrapper.createAsBitmap(bitmapWrapper.getBitmap()));
            }
        }
    }
}
