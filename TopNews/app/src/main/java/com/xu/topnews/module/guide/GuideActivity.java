package com.xu.topnews.module.guide;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xu.appbaseui.activity.XuBaseActivity;
import com.xu.appcommonutils.util.SPUtils;
import com.xu.topnews.R;
import com.xu.topnews.constant.XuConstant;
import com.xu.topnews.main.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GuideActivity extends XuBaseActivity {

    private int[] mPictureId = new int[]{R.drawable.guide_image_one,R.drawable.guide_image_two,R.drawable.guide_image_three};
    private List<ImageView> mImageLists;

    @BindView(R.id.viewpager_guide)
    ViewPager mViewPager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        initData();
        mViewPager.setAdapter(new ViewPagerAdapter());
    }

    private void initData(){
        mImageLists = new ArrayList<>();
        for (int id : mPictureId ) {
            ImageView imageView = new ImageView(GuideActivity.this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            imageView.setImageResource(id);

            mImageLists.add(imageView);
        }
    }


    class ViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mImageLists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageLists.get(position));
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            ImageView imageView = mImageLists.get(position);
            if (position == 2){
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SPUtils spUtils = new SPUtils(XuConstant.SP_NAME);
                        spUtils.put(XuConstant.IS_NOT_FIRST,true);

                        Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
            container.addView(imageView);
            return imageView;
        }
    }

}
