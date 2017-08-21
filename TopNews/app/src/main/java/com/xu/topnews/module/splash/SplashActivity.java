package com.xu.topnews.module.splash;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;

import com.xu.appbaseui.activity.XuBaseActivity;
import com.xu.appbaseui.countdowncircularprogressbar.CircleTextProgressbar;
import com.xu.appbaseui.dialog.XuBaseAlertDialog;
import com.xu.appcommonutils.util.PermissionUtils;
import com.xu.appcommonutils.util.SPUtils;
import com.xu.topnews.R;
import com.xu.topnews.constant.XuConstant;
import com.xu.topnews.main.ui.activity.MainActivity;
import com.xu.topnews.module.guide.GuideActivity;
import com.xu.topnews.module.web.WebActivity;

import butterknife.BindView;

public class SplashActivity extends XuBaseActivity implements View.OnClickListener {
    //是否第一次
    private boolean mIsNotFirst;
    //请求码
    private static final int REQUEST_CODE = 1002;
    //时间
    private static final long TOTAL_TIME = 4000;

    //圆形进度条
    @BindView(R.id.circularprogressbar)
    CircleTextProgressbar mCircularProgressBar;

    @BindView(R.id.layout_splash_container)
    RelativeLayout mSplashLayout;


    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        mCircularProgressBar.setTimeMillis(TOTAL_TIME);
        mCircularProgressBar.setProgressType(CircleTextProgressbar.ProgressType.COUNT);
        mCircularProgressBar.setOnClickListener(this);
        mCircularProgressBar.setCountdownProgressListener(1,progressListener);
        mSplashLayout.setOnClickListener(this);
        SPUtils spUtils = new SPUtils(XuConstant.SP_NAME);
        mIsNotFirst = spUtils.getBoolean(XuConstant.IS_NOT_FIRST);
        String[] permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        PermissionUtils.requestPermissions(SplashActivity.this, REQUEST_CODE, permission, new PermissionUtils.OnPermissionListener() {
            @Override
            public void onPermissionGranted() {
                mCircularProgressBar.start();
            }

            @Override
            public void onPermissionDenied(String[] deniedPermissions) {
                showErrorAlertDialog();
            }
        });
    }

    private CircleTextProgressbar.OnCountdownProgressListener progressListener = new CircleTextProgressbar.OnCountdownProgressListener() {
        @Override
        public void onProgress(int what, int progress) {
            if (what == 1 && progress == 100) {
                timeFinish();
            }
            // 比如在首页，这里可以判断进度，进度到了100或者0的时候，你可以做跳过操作。
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUtils.onRequestPermissionsResult(SplashActivity.this,REQUEST_CODE,permissions);
    }
    private void timeFinish(){
        Intent intent = new Intent();
        if (!mIsNotFirst){
            intent.setClass(SplashActivity.this, GuideActivity.class);
        }else {
            intent.setClass(SplashActivity.this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.circularprogressbar){
            mCircularProgressBar.stop();
            timeFinish();
        }else if (id == R.id.layout_splash_container){
            gotoWebActivity();
        }
    }

    /**
     * 跳转webactivity
    */
    private void gotoWebActivity(){
        mCircularProgressBar.stop();
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
        finish();
    }


    private void showErrorAlertDialog(){
        new XuBaseAlertDialog(SplashActivity.this, XuBaseAlertDialog.NORMAL_TYPE)
                .setTitleText(getResources().getString(R.string.app_splash_requestpermissions_title))
                .setConfirmText(getResources().getString(R.string.app_base_confirm))
                .setCancelText(getResources().getString(R.string.app_base_cancel))
                .showCancelButton(true)
                .setCancelClickListener(new XuBaseAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(XuBaseAlertDialog hexSweetAlertDialog) {
                        hexSweetAlertDialog.dismiss();
                        //按返回键返回桌面(返回桌面上次停留的页面)
                        moveTaskToBack(true);
                        // 关闭应用程序
                        finish();
                    }
                })
                .setConfirmClickListener(new XuBaseAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(XuBaseAlertDialog hexSweetAlertDialog) {
                        hexSweetAlertDialog.dismiss();
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                })
                .show();
    }
}
