package com.xu.appbaseui.viewpagertitle;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xu.appbaseui.R;
import com.xu.appcommonutils.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

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
    /**
     * 背景颜色
     */
    private int mBackGroudColor;
    /**
     * 默认字体颜色
     */
    private int mDefaultTextColor;
    /**
     * 选中字体颜色
     */
    private int mSelectTextColor;
    /**
     * 默认字体大小
     */
    private float mDefaultTextSize;
    /**
     * 选中字体大小
     */
    private float mSelectTextSize;
    /**
     * textview左右距离
     */
    private float mMargins;
    /**
     * 下划线开始颜色
     */
    private int mStartColor;
    /**
     * 下划线结束颜色
     */
    private int mEndColor;
    /**
     * 下划线高度
     */
    private int mHeight;
    /**
     * viewpager
     */
    private ViewPager mViewPager;
    /**
     * 默认选中位置
     */
    private int mDefaultIndex;
    /**
     * 标题
     */
    private List<String> mTitles;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 下划线
     */
    private XuBaseViewPagerLine mLine;
    /**
     * textview集合
     */
    private List<TextView> mTextViewLists;
    /**
     * 标题总长度
     */
    private float mCountTitleWidth;

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
    @TargetApi(Build.VERSION_CODES.M)
    private void initAttribute(Context context, AttributeSet attrs){
        mContext = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.XuBaseViewPagerTitle);
        mBackGroudColor = array.getColor(R.styleable.XuBaseViewPagerTitle_background_content_color, context.getColor(R.color.white));
        mDefaultTextColor = array.getColor(R.styleable.XuBaseViewPagerTitle_defaultTextViewColor, context.getColor(R.color.baseui_text_color));
        mSelectTextColor = array.getColor(R.styleable.XuBaseViewPagerTitle_selectedTextViewColor, context.getColor(R.color.baseui_select_text_color));
        mDefaultTextSize = array.getDimension(R.styleable.XuBaseViewPagerTitle_defaultTextViewSize, 18);
        mSelectTextSize = array.getDimension(R.styleable.XuBaseViewPagerTitle_selectedTextViewSize, 18);
        mMargins = array.getDimension(R.styleable.XuBaseViewPagerTitle_item_margins, 30);
        mStartColor = array.getColor(R.styleable.XuBaseViewPagerTitle_line_start_color, context.getColor(R.color.baseui_line_start_color));
        mEndColor = array.getColor(R.styleable.XuBaseViewPagerTitle_line_end_color, context.getColor(R.color.baseui_line_end_color));
        mHeight = array.getInt(R.styleable.XuBaseViewPagerTitle_line_height, 20);

        array.recycle();
    }

    /**
     * 初始化数据
     * @param titles
     * @param pager
     * @param defaultIndex
     */
    public void initData(List<String> titles, ViewPager pager, int defaultIndex){
        mTitles = titles;
        mViewPager = pager;
        mDefaultIndex = defaultIndex;
        mTextViewLists = new ArrayList<>();
        createAddLine();
        createAddTextView();

        setCurrentIndex(defaultIndex);

        XuBaseViewPagerListener listener = new XuBaseViewPagerListener(mContext, mTextViewLists, mCountTitleWidth, mMargins, this, mLine, mViewPager);
        mViewPager.setOnPageChangeListener(listener);

    }

    /**
     * 设置选中的textview
     * @param defaultIndex
     */
    public void setCurrentIndex(int defaultIndex) {

        for (int i = 0; i < mTextViewLists.size(); i++) {
            TextView textView = mTextViewLists.get(i);
            int tag = (int) textView.getTag();
            if (tag == defaultIndex){
                textView.setTextSize(mSelectTextSize);
                textView.setTextColor(mSelectTextColor);
            }else {
                textView.setTextSize(mDefaultTextSize);
                textView.setTextColor(mDefaultTextColor);
            }
        }

    }

    /**
     * 添加Textview和下划线
     */
    private void createAddTextView() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout verticalLayout = new LinearLayout(mContext);
        verticalLayout.setBackgroundColor(mBackGroudColor);
        verticalLayout.setLayoutParams(layoutParams);
        verticalLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout horizontalLayout = new LinearLayout(mContext);
        horizontalLayout.setLayoutParams(layoutParams);
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);


        int margins = getTextViewMargins();
        textParams.setMargins(margins, 0, margins, 0);

        for (int i = 0; i < mTitles.size(); i++) {
            TextView textView = new TextView(mContext);
            textView.setText(mTitles.get(i));
            textView.setTextColor(mDefaultTextColor);
            textView.setTextSize(mDefaultTextSize);
            textView.setGravity(Gravity.CENTER);
            textView.setTag(i);
            textView.setLayoutParams(textParams);
            textView.setOnClickListener(onClickListener);
            mTextViewLists.add(textView);
            horizontalLayout.addView(textView);
        }
        verticalLayout.addView(horizontalLayout);
        verticalLayout.addView(mLine);
        addView(verticalLayout);
    }

    /**
     * 点击事件
     */
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            setCurrentIndex(position);
            mViewPager.setCurrentItem(position);
        }
    };


    /**
     * 创建下划线
     */
    private void createAddLine() {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLine = new XuBaseViewPagerLine(mContext, mStartColor, mEndColor, mHeight);
        mLine.setLayoutParams(params);
    }

    /**
     * 获取textviewmargins
     * @return
     */
    private int getTextViewMargins() {
        int margins = 0;
        float countLength = 0;
        TextView textView = new TextView(mContext);
        textView.setTextSize(mDefaultTextSize);
        TextPaint textPaint = textView.getPaint();
        for (String title : mTitles) {
            countLength = countLength + textPaint.measureText(title) + mMargins * 2;
        }
        int screenWidth = ScreenUtils.getScreenWidth();
        if (countLength <= screenWidth){
            mCountTitleWidth = screenWidth;
            margins = (int) ((screenWidth / mTitles.size() - textPaint.measureText(mTitles.get(0))) / 2);
        }else {
            mCountTitleWidth = countLength;
            margins = (int) mMargins;
        }
        return margins;
    }


}
