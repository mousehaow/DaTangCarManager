package com.datang.datangcarmanager.presentation.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.datang.datangcarmanager.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainAcitvity";

    @BindView(R.id.enterprise_car_cardview)
    CardView mEnterpriseCarCardview;
    @BindView(R.id.electronic_fence_cardview)
    CardView mElectronicFenceCardview;
    @BindView(R.id.remind_mail_cardview)
    CardView mRemindMailCardview;
    @BindView(R.id.search_car_cardview)
    CardView mSearchCarCardview;
    @BindView(R.id.statistics_analyze_cardview)
    CardView mStatisticsAnalyzeCardview;
    @BindView(R.id.system_setting_cardview)
    CardView mSystemSettingCardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.transparent);//通知栏所需颜色
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }



    @OnClick({R.id.enterprise_car_cardview, R.id.electronic_fence_cardview, R.id.remind_mail_cardview, R.id.search_car_cardview, R.id.statistics_analyze_cardview, R.id.system_setting_cardview})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.enterprise_car_cardview:
                intent = new Intent(MainActivity.this, EnterpriseCarActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                break;
            case R.id.electronic_fence_cardview:
                break;
            case R.id.remind_mail_cardview:
                intent = new Intent(MainActivity.this, RemindMailActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                break;
            case R.id.search_car_cardview:
                intent = new Intent(MainActivity.this, SearchCarActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                break;
            case R.id.statistics_analyze_cardview:
                intent = new Intent(MainActivity.this, StatisticsAnalyzeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                break;
            case R.id.system_setting_cardview:
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                break;
        }
    }
}
