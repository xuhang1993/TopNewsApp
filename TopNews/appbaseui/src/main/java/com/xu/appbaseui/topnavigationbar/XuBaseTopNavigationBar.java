package com.xu.appbaseui.topnavigationbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xu.appbaseui.R;

/**
 * Created by 16413 on 2017/6/8.
 */

public class XuBaseTopNavigationBar extends RelativeLayout implements View.OnClickListener {
    //左侧标题
    private TextView mLeftTextView;
    //左侧图片
    private ImageButton mLeftImageButton;
    //右侧标题
    private TextView mRightTextView;
    //右侧图片
    private ImageButton mRightImageButton;
    //标题
    private TextView mTitleTextView;

    //linstener

    private OnClickNaviagtionRightTextViewListener mClickNaviagtionRightTextViewListener;

    private OnClickNavigationRightImageButtonListener mClickNavigationRightImageButtonListener;

    private OnClickNavigationLeftTextViewListener mClickNavigationLeftTextViewListener;

    private OnClickNavigationLeftImageButtonListener mClickNavigationLeftImageButtonListener;

    private OnClickNavigationBarListener mClickNavigationBarListener;

    public XuBaseTopNavigationBar(Context context) {
        this(context, null);
    }

    public XuBaseTopNavigationBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XuBaseTopNavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFlate();
        postAttrs(context,attrs);

    }

    private void initFlate() {
        ((LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.base_navigation_bar_layout, this, true);
        mLeftTextView = (TextView) findViewById(R.id.navigation_left_textview);
        mRightTextView = (TextView) findViewById(R.id.navigation_right_textview);
        mRightImageButton = (ImageButton) findViewById(R.id.navigation_right_imagebutton);
        mLeftImageButton = (ImageButton) findViewById(R.id.navigation_left_imagebutton);
        mTitleTextView = (TextView) findViewById(R.id.navigation_title);

        mLeftTextView.setOnClickListener(this);
        mRightTextView.setOnClickListener(this);
        mRightImageButton.setOnClickListener(this);
        mLeftImageButton.setOnClickListener(this);

    }

    private void postAttrs(Context context, AttributeSet attrs) {

        if(attrs != null){
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.XuBaseTopNavigationBar);
            String leftText = array.getString(R.styleable.XuBaseTopNavigationBar_leftText);
            int leftImageId = array.getResourceId(R.styleable.XuBaseTopNavigationBar_leftImage, -1);
            String rightText = array.getString(R.styleable.XuBaseTopNavigationBar_rightText);
            int rightImageId = array.getResourceId(R.styleable.XuBaseTopNavigationBar_rightImage, -1);
            String titleText = array.getString(R.styleable.XuBaseTopNavigationBar_titleText);
            int leftType = array.getInt(R.styleable.XuBaseTopNavigationBar_left_type, LeftType.NONE.value);
            int rightType = array.getInt(R.styleable.XuBaseTopNavigationBar_right_type, RightType.NONE.value);

            mTitleTextView.setText(titleText);

            if (rightType == RightType.TEXT.value){
                mRightImageButton.setVisibility(GONE);
                mRightTextView.setText(rightText);
            }else if (rightType == RightType.IMAGE.value){
                mRightTextView.setVisibility(GONE);
                mRightImageButton.setImageResource(rightImageId);
            }else {
                mRightImageButton.setVisibility(GONE);
                mRightTextView.setVisibility(GONE);
            }

            if (leftType == LeftType.TEXT.value){
                mLeftImageButton.setVisibility(GONE);
                mLeftTextView.setText(leftText);
            }else if (leftType == LeftType.IMAGE.value){
                mLeftTextView.setVisibility(GONE);
                mLeftImageButton.setImageResource(leftImageId);
            }else if (leftType == LeftType.TEXTWITHIMAGE.value){
                mLeftImageButton.setVisibility(GONE);
                mLeftTextView.setText(leftText);

                Drawable drawable= getResources().getDrawable(leftImageId);
                /// 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                mLeftTextView.setCompoundDrawables(drawable, null, null, null);
            }else {
                mLeftImageButton.setVisibility(GONE);
                mLeftTextView.setVisibility(GONE);
            }

            array.recycle();
        }

    }

    /**
     * 设置标题
     * @param title
     */
    public void setNavigationTitle(String title){
        mTitleTextView.setText(title);
    }



    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.navigation_right_textview){//右侧Textview
            if (mClickNaviagtionRightTextViewListener != null){
                mClickNaviagtionRightTextViewListener.clickRightTextView();
            }

            if (mClickNavigationBarListener != null){
                mClickNavigationBarListener.clickRightTextView();
            }

        }else if (id == R.id.navigation_right_imagebutton){//右侧ImageButton

            if (mClickNavigationRightImageButtonListener != null){
                mClickNavigationRightImageButtonListener.clickRightImageButtonListener();
            }

            if (mClickNavigationBarListener != null){
                mClickNavigationBarListener.clickRightImageButton();
            }

        }else if (id == R.id.navigation_left_imagebutton){//左侧ImageButton
            if (mClickNavigationLeftImageButtonListener != null){
                mClickNavigationLeftImageButtonListener.clickLeftImageButton();
            }

            if (mClickNavigationBarListener != null){
                mClickNavigationBarListener.clickLeftImageButton();
            }
        }else if (id == R.id.navigation_left_textview){//左侧Textview
            if (mClickNavigationLeftTextViewListener != null){
                mClickNavigationLeftTextViewListener.clickLeftTextView();
            }

            if (mClickNavigationBarListener != null){
                mClickNavigationBarListener.clickLeftTextView();
            }
        }

    }


    public interface OnClickNaviagtionRightTextViewListener{
        /**
         * 点击右侧Textview
         */
        void clickRightTextView();
    }

    public interface OnClickNavigationRightImageButtonListener{
        /**
         * 点击右侧ImageButton
         */
        void clickRightImageButtonListener();
    }

    public interface OnClickNavigationLeftTextViewListener{
        /**
         * 点击左侧TextView
         */
        void clickLeftTextView();
    }

    public interface OnClickNavigationLeftImageButtonListener{
        /**
         * 点击左侧ImageButton
         */
        void clickLeftImageButton();
    }

    public interface OnClickNavigationBarListener{
        /**
         * 点击右侧Textview
         */
         void clickRightTextView();

        /**
         * 点击右侧ImageButton
         */
        void clickRightImageButton();

        /**
         * 点击左侧TextView
         */
         void clickLeftTextView();

        /**
         * 点击左侧ImageButton
         */
         void clickLeftImageButton();
    }


    public void setmClickNaviagtionRightTextViewListener(OnClickNaviagtionRightTextViewListener mClickNaviagtionRightTextViewListener) {
        this.mClickNaviagtionRightTextViewListener = mClickNaviagtionRightTextViewListener;
    }

    public void setmClickNavigationRightImageButtonListener(OnClickNavigationRightImageButtonListener mClickNavigationRightImageButtonListener) {
        this.mClickNavigationRightImageButtonListener = mClickNavigationRightImageButtonListener;
    }

    public void setmClickNavigationLeftTextViewListener(OnClickNavigationLeftTextViewListener mClickNavigationLeftTextViewListener) {
        this.mClickNavigationLeftTextViewListener = mClickNavigationLeftTextViewListener;
    }

    public void setmClickNavigationLeftImageButtonListener(OnClickNavigationLeftImageButtonListener mClickNavigationLeftImageButtonListener) {
        this.mClickNavigationLeftImageButtonListener = mClickNavigationLeftImageButtonListener;
    }

    public void setmClickNavigationBarListener(OnClickNavigationBarListener mClickNavigationBarListener) {
        this.mClickNavigationBarListener = mClickNavigationBarListener;
    }

    private enum LeftType{
        /**
         * 文本
         */
        TEXT(0),
        /**
         * 图片
         */
        IMAGE(1),
        /**
         * 文本和图片
         */
        TEXTWITHIMAGE(2),

        /**
         * 无
         */
        NONE(3);

        private final int value;

        LeftType(int value) {
            this.value = value;
        }

        private int getValue(){
            return value;
        }

        private static LeftType findByValue(int value){
            switch (value){
                case 0:
                    return TEXT;
                case 1:
                    return IMAGE;
                case 2:
                    return TEXTWITHIMAGE;
                case 3:
                    return NONE;
                default:
                    return TEXT;
            }

        }

    }

    private enum RightType{
        /**
         * 文本
         */
        TEXT(0),
        /**
         * 图片
         */
        IMAGE(1),
        /**
         * 无
         */
        NONE(2);
        private final int value;

        RightType(int value) {
            this.value = value;
        }

        private int getValue(){
            return value;
        }

        private static RightType findByValue(int value){
            switch (value){
                case 0:
                    return TEXT;
                case 1:
                    return IMAGE;
                case 2:
                    return NONE;
                default:
                    return TEXT;
            }

        }

    }


}
