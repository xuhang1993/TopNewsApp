package com.xu.appbaseui.richtext.callback;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.xu.appbaseui.richtext.ImageHolder;
import com.xu.appbaseui.richtext.RichTextConfig;


/**
 * Created by zhou on 2016/12/3.
 * 图片加载器
 */
public interface ImageGetter extends Recyclable {

    /**
     * 获取图片
     *
     * @param holder   ImageHolder
     * @param config   RichTextConfig
     * @param textView TextView
     * @return Drawable
     * @see ImageHolder
     * @see RichTextConfig
     */
    Drawable getDrawable(ImageHolder holder, RichTextConfig config, TextView textView);

    void registerImageLoadNotify(ImageLoadNotify imageLoadNotify);
}
