package com.xu.appbaseui.viewpagertitle;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: XuBaseViewPagerTitle
 * @Title:
 * @Description: 顶部标题栏
 * @Package com.xu.appbaseui
 * @date 2017/9/5 18:08
 */

public class XuBaseViewPagerTitle extends HorizontalScrollView{

    public XuBaseViewPagerTitle(Context context) {
        this(context, null);
    }

    public XuBaseViewPagerTitle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XuBaseViewPagerTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttribute(context, attrs);
    }

    /**
     * 初始化
     * @param context
     * @param attrs
     */
    private void initAttribute(Context context, AttributeSet attrs){

    }

}
