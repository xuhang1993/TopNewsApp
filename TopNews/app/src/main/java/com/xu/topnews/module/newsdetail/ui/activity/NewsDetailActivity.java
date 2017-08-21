package com.xu.topnews.module.newsdetail.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xu.appbaseui.activity.XuBaseActivity;
import com.xu.appbaseui.loadingview.XuBaseUILoadingView;
import com.xu.appbaseui.richtext.RichText;
import com.xu.appbaseui.topnavigationbar.XuBaseTopNavigationBar;
import com.xu.appbaseui.videoplayer.BaseUIVideoPlayer;
import com.xu.appbaseui.videoplayer.BaseUIVideoPlayerStandard;
import com.xu.appcommonutils.util.ScreenUtils;
import com.xu.appframwork.glide.XuBaseGlideUtils;
import com.xu.appinterface.webapi.apimodel.topnewsdetail.XuTopNewsDetailModel;
import com.xu.topnews.R;
import com.xu.topnews.module.newsdetail.presenter.impl.NewsDetailPresenterImpl;
import com.xu.topnews.module.newsdetail.presenter.ipresenter.INewsDetailPresenter;
import com.xu.topnews.module.newsdetail.ui.iview.INewsDetailActivityView;

import butterknife.BindView;

public class NewsDetailActivity extends XuBaseActivity implements INewsDetailActivityView{

    @BindView(R.id.news_content_loadingview)
    XuBaseUILoadingView mLoadingView;

    @BindView(R.id.news_content_textview)
    TextView mContentTextView;

    @BindView(R.id.top_view)
    XuBaseTopNavigationBar mTopBar;

    @BindView(R.id.tv_newsdetail_title)
    TextView mNewsDetailTitleTV;

    @BindView(R.id.tv_newsdetail_source)
    TextView mNewsDetailSourseTV;

    @BindView(R.id.tv_newsdetail_date)
    TextView mNewsDetailDateTV;

    @BindView(R.id.vp_newsdetail_player)
    BaseUIVideoPlayerStandard mVideoPlayer;

    @BindView(R.id.iv_newsdetail_picture)
    ImageView mNewsDetailPictre;

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initView() {
        String postId = getIntent().getStringExtra("postId");
        INewsDetailPresenter newsDetailPresenter = new NewsDetailPresenterImpl(this);
        newsDetailPresenter.loadNewsDetail(postId);
        mTopBar.setmClickNavigationLeftTextViewListener(new XuBaseTopNavigationBar.OnClickNavigationLeftTextViewListener() {
            @Override
            public void clickLeftTextView() {
                finish();
            }
        });
    }

    @Override
    public void showProgress() {
        mLoadingView.play();
    }

    @Override
    public void hideProgress() {
        mLoadingView.stop();
    }

    @Override
    public void loadSuccess(Object object) {
        hideProgress();
        XuTopNewsDetailModel detailModel = (XuTopNewsDetailModel) object;
        RichText.from(detailModel.getBody()).into(mContentTextView);
//        mTopBar.setNavigationTitle(detailModel.getTitle());
        mNewsDetailTitleTV.setText(detailModel.getTitle());
        mNewsDetailSourseTV.setText(detailModel.getSource());
        mNewsDetailDateTV.setText(detailModel.getPtime());

        if (detailModel.getVideo() != null && detailModel.getVideo().size() > 0){
            mVideoPlayer.setVisibility(View.VISIBLE);
            XuTopNewsDetailModel.VideoBean videoBean = detailModel.getVideo().get(0);
            String videoUrl = videoBean.getUrl_mp4();
            String cover = videoBean.getCover();
            mVideoPlayer.setUp(videoUrl, BaseUIVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
            XuBaseGlideUtils.loadDefaultImageView(this, cover, R.drawable.default_image_resource, R.drawable.default_image_load_fail, mVideoPlayer.thumbImageView);
        }else if (detailModel.getImg() != null && detailModel.getImg().size() > 0){
            mNewsDetailPictre.setVisibility(View.VISIBLE);
            XuTopNewsDetailModel.ImgBean imgBean = detailModel.getImg().get(0);
            String pixel = imgBean.getPixel();
            String imageUrl = imgBean.getSrc();

            String [] pixelArray = pixel.split("\\*");

            if (pixelArray != null && pixelArray.length == 2){
                int width = ScreenUtils.getScreenWidth();
                int height = (width * Integer.parseInt(pixelArray[1])) / Integer.parseInt(pixelArray[0]);
                XuBaseGlideUtils.loadDefaultImageViewSize(this, imageUrl, R.drawable.default_image_resource, R.drawable.default_image_load_fail, height, width, mNewsDetailPictre);
            }else {
                XuBaseGlideUtils.loadDefaultImageView(this, imageUrl, R.drawable.default_image_resource, R.drawable.default_image_load_fail, mNewsDetailPictre);
            }

        }else {

        }

    }

    @Override
    public void loadFailer(String string) {
        hideProgress();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BaseUIVideoPlayer.releaseAllVideos();
    }

}
