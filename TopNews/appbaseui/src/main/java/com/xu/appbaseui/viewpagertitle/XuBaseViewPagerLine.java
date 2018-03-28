package com.xu.appbaseui.viewpagertitle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xu.appcommonutils.util.ScreenUtils;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: XuBaseViewPagerLine
 * @Title:
 * @Description: 下划线
 * @Package com.xu.appbaseui.viewpagertitle
 * @date 2017/9/7 14:16
 */

public class XuBaseViewPagerLine extends View {
    /**
     * 高度
     */
    private int mLineHeight;
    /**
     * 截止颜色
     */
    private int mShaderColorEnd;
    /**
     * 开始颜色
     */
    private int mShaderColorStart;
    /**
     * 起点x, 终点x
     */
    private float startX, stopX;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * RectF
     */
    private RectF mRectF = new RectF(startX, 0, stopX, 0);

    public XuBaseViewPagerLine(Context context, int shaderColorStart, int shaderColorEnd, int lineHeight) {
        this(context, null);
        mLineHeight = lineHeight;
        mShaderColorEnd = shaderColorEnd;
        mShaderColorStart = shaderColorStart;
    }

    public XuBaseViewPagerLine(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XuBaseViewPagerLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShader(new LinearGradient(0, 200, ScreenUtils.getScreenWidth(), 200, Color.parseColor("#ffc125"), Color.parseColor("#ff4500"), Shader.TileMode.MIRROR));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(20, MeasureSpec.getMode(heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mRectF.set(startX, 0, stopX, 10);
        canvas.drawRoundRect(mRectF, 5, 5, mPaint);
    }

    /**
     * 更新view
     * @param startX
     * @param stopX
     */
    public void upDateView(float startX, float stopX){
        this.startX = startX;
        this.stopX = stopX;
        invalidate();
    }

}
