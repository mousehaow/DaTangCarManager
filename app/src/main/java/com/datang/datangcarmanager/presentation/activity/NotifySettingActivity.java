package com.datang.datangcarmanager.presentation.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.presentation.adapter.NotifySettingsListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotifySettingActivity extends BaseActivity {

    public static final String NOTIFY_SETTINGS = "notify_settings";

    private static final String[] settingsTitles = {
            "点火启动提醒",
            "碰撞提醒",
            "故障提醒",
            "低电压提醒",
            "超速报警提醒",
            "违章报警提醒",
            "电子栅栏提醒",
            "保险到期提醒",
            "保养到期提醒",
            "年审到期提醒",
            "机车拆除提醒",
            "未匹配行驶报警提醒"
    };

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.notify_settings_recycler_view)
    RecyclerView mNotifySettingsRecyclerView;

    private NotifySettingsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_setting);
        ButterKnife.bind(this);
        initList();
    }

    private void initList() {
        mAdapter = new NotifySettingsListAdapter(this, settingsTitles);
        mAdapter.setListener(new NotifySettingsListAdapter.OnSwichChangeStateListener() {
            @Override
            public void checkedChanged(int position, boolean isChecked) {
                SharedPreferences settings = getSharedPreferences(NOTIFY_SETTINGS, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean(settingsTitles[position], isChecked);
                editor.commit();
            }
        });
        mNotifySettingsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mNotifySettingsRecyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.back_btn, R.id.back_title_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
            case R.id.back_title_btn:
                finish();
                overridePendingTransition(R.anim.custom_fade_in, R.anim.out_to_right);
                break;
        }
    }
}
