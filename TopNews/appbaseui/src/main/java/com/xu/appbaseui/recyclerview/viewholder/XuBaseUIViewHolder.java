package com.xu.appbaseui.recyclerview.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xu.appbaseui.R;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: XuBaseUIViewHolder
 * @Title:
 * @Description: recyclerview baseviewholder
 * @Package com.xu.appbaseui.recyclerview.viewholder
 * @date 2017/5/23 14:25
 */


public class XuBaseUIViewHolder extends RecyclerView.ViewHolder {

    //集合类，layout里包含的View,以view的id作为key，value是view对象
    protected SparseArray<View> mViews;
    //上下文对象
    protected Context mContext;

    public XuBaseUIViewHolder(View itemView, Context context) {
        super(itemView);
        mContext = context;
        mViews = new SparseArray<>();
    }

    /**
     * 把itemview中TextView等实例化
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T findViewById(int viewId){

        View view = mViews.get(viewId);
        if (view == null){
            view = itemView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    public View getView(int viewId){
        return findViewById(viewId);
    }

    /**
     * 获取Textview
     * @param viewId
     * @return
     */
    public TextView getTextView(int viewId){
        return (TextView) getView(viewId);
    }

    /**
     * 获取ImageView
     * @param viewId
     * @return
     */
    public ImageView getImageView(int viewId){
        return (ImageView) getView(viewId);
    }

    /**
     * 获取Button
     * @param viewId
     * @return
     */
    public Button getButton(int viewId){
        return (Button) getView(viewId);
    }

    /**
     * 获取EditText
     * @param viewId
     * @return
     */
    public EditText getEditText(int viewId){
        return (EditText) getView(viewId);
    }

    /**
     * 获取RadioButton
     * @param viewId
     * @return
     */
    public RadioButton getRadioButton(int viewId){
        return (RadioButton) getView(viewId);
    }

    /**
     * 获取RadioGroup
     * @param viewId
     * @return
     */
    public RadioGroup getRadioGroup(int viewId){
        return (RadioGroup) getView(viewId);
    }

    /**
     * 获取ImageButton
     * @param viewId
     * @return
     */
    public ImageButton getImageButton(int viewId){
        return (ImageButton) getView(viewId);
    }

    /**
     * 获取LinearLayout
     * @param viewId
     * @return
     */
    public LinearLayout getLinearLayout(int viewId){
        return (LinearLayout) getView(viewId);
    }

    /**
     * 获取RelativeLayout
     * @param viewId
     * @return
     */
    public RelativeLayout getRelativeLayout(int viewId){
        return (RelativeLayout) getView(viewId);
    }

    /*****************************辅助方法**********************/

    public void setText(int viewId, String string){
        this.setText(viewId,string, R.color.baseui_text_color,14);
    }

    public void setText(int viewId, String string, int color){
        this.setText(viewId,string, color,14);
    }

    public void setText(int viewId, String string, int color, int textSize){
        TextView textView = getTextView(viewId);
        textView.setTextColor(color);
        textView.setText(string);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    public void setButtonText(int viewId, String text){
        Button button = getButton(viewId);
        button.setText(text);
    }

    public void setImageResource(int viewId, int resourceId){
        getImageView(viewId).setImageResource(resourceId);
    }






}
