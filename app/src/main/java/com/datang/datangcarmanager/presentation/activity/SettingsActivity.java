package com.datang.datangcarmanager.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.presentation.adapter.SettingsListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity {

    public static final String TAG = "SettingActivity";

    private static final String[] mTitles = {
            "用户设置",
            "通知设置",
            "帮助",
            "关于",
            "服务条款",
            "投诉建议",
            "设备购买",
            "推荐分享",
            "切换账号"
    };
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.settings_recycler_view)
    RecyclerView mSettingsRecyclerView;

    private SettingsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        initList();
    }

    private void initList() {
        mAdapter = new SettingsListAdapter(this, mTitles);
        mAdapter.setListener(new SettingsListAdapter.OnSettingsListItemClickedListener() {
            @Override
            public void itemClicked(int position) {
                Intent intent = null;
                switch (position) {
                     /* 用户设置 */
                    case 0:
                        intent = new Intent(SettingsActivity.this, UserSettingsActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_from_right, android.R.anim.fade_out);
                        break;
                    /* 通知设置 */
                    case 1:
                        intent = new Intent(SettingsActivity.this, NotifySettingActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_from_right, android.R.anim.fade_out);
                        break;
                    /* 帮助 */
                    case 2:

                        break;
                    /* 关于 */
                    case 3:

                        break;
                    /* 服务条款 */
                    case 4:

                        break;
                    /* 投诉建议 */
                    case 5:
                        intent = new Intent(SettingsActivity.this, SuggestActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_from_right, android.R.anim.fade_out);
                        break;
                    /* 设备购买 */
                    case 6:

                        break;
                    /* 切换账号 */
                    case 8:

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void shareBtnClicked(int type) {
                switch (type) {
                    case SettingsListAdapter.WEIBO_SHARE:
                        Log.i(TAG, "微博");
                        break;
                    case SettingsListAdapter.WECHAT_F_SHARE:
                        Log.i(TAG, "朋友圈");
                        break;
                    case SettingsListAdapter.WECHAT_SHARE:
                        Log.i(TAG, "微信");
                        break;
                    case SettingsListAdapter.QQSPACE_SHARE:
                        Log.i(TAG, "QQ");
                        break;
                    default:
                        break;
                }
            }
        });
        mSettingsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mSettingsRecyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.back_btn, R.id.back_title_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
            case R.id.back_title_btn:
                finish();
                break;
        }
    }
}
