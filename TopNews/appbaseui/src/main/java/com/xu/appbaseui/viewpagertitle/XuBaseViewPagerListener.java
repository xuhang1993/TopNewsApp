package com.xu.appbaseui.viewpagertitle;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.xu.appcommonutils.util.ScreenUtils;

import java.util.List;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: XuBaseViewPagerListener
 * @Title:
 * @Description: viewpagerlistener
 * @Package com.xu.appbaseui.viewpagertitle
 * @date 2017/9/7 16:31
 */

public class XuBaseViewPagerListener implements ViewPager.OnPageChangeListener {
    /**
     * 下划线
     */
    private XuBaseViewPagerLine mPagerLine;
    /**
     * 标题栏
     */
    private XuBaseViewPagerTitle mPagerTitle;
    /**
     * 左右间距
     */
    private float mMargins;
    /**
     * 长度
     */
    private float mCountLength;
    /**
     * textview集合
     */
    private List<TextView> mTextViewList;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 每个标题栏的平均长度
     */
    private float mLength;
    /**
     * 当前position
     */
    private int mCurrentPosition;
    /**
     * 屏幕宽度
     */
    private final int mScreenWidth;
    /**
     * viewpager
     */
    private ViewPager mViewPager;
    /**
     *
     */
    private int [] mLocal = new int[2];
    /**
     * 文本长度
     */
    private final float mTextLength;


    public XuBaseViewPagerListener(Context context, List<TextView> textViewList, float countLength, float margains, XuBaseViewPagerTitle pagerTitle, XuBaseViewPagerLine pagerLine, ViewPager pager) {
        mContext = context;
        mTextViewList = textViewList;
        mCountLength = countLength;
        mMargins = margains;
        mPagerTitle = pagerTitle;
        mPagerLine = pagerLine;
        TextView textView = textViewList.get(0);
        mTextLength = textView.getPaint().measureText(textView.getText().toString());
        mScreenWidth = ScreenUtils.getScreenWidth();
        mLength = countLength / textViewList.size();
        mViewPager = pager;
    }

    /**
     *
     * @param position 滑向的位置
     * @param positionOffset 偏移量
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mCurrentPosition > position){//页面向右滑动 stopX不变 startX减小
            mPagerLine.upDateView((position + positionOffset) * mLength + mMargins, (mCurrentPosition + 1) * mLength - mMargins);
        }else {
            if (positionOffset > 0.5f) {
                positionOffset = 0.5f;
            }
            mPagerLine.upDateView(mCurrentPosition * mLength + mMargins , (position + positionOffset * 2) * mLength + mMargins + mTextLength);

        }


//        else {//页面向左滑动 startX不变 stopX加大
//            mPagerLine.upDateView(mCurrentPosition * mLength + mMargins, (position + positionOffset) * mLength - mMargins);
//        }
    }

    @Override
    public void onPageSelected(int position) {
        mPagerTitle.setCurrentIndex(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        boolean isScroll;
        if (state == ViewPager.SCROLL_STATE_SETTLING){
            isScroll = mCurrentPosition < mViewPager.getCurrentItem();
            mCurrentPosition = mViewPager.getCurrentItem();
            if (mCurrentPosition + 1 < mTextViewList.size() && mCurrentPosition - 1 >= 0) {
                mTextViewList.get(isScroll ? mCurrentPosition + 1 : mCurrentPosition - 1).getLocationOnScreen(mLocal);
                if (mLocal[0] > mScreenWidth) {
                    mPagerTitle.smoothScrollBy(mScreenWidth / 2, 0);
                } else if (mLocal[0] < 0) {
                    mPagerTitle.smoothScrollBy(-mScreenWidth / 2, 0);
                }
            }
        }
    }
}
