package com.datang.datangcarmanager.presentation.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.datang.datangcarmanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserSettingsActivity extends BaseActivity {

    public static final String TAG = "UserSettingsActivity";

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.old_password_tv)
    EditText mOldPasswordTv;
    @BindView(R.id.new_password_tv)
    EditText mNewPasswordTv;
    @BindView(R.id.new_password_again_tv)
    EditText mNewPasswordAgainTv;
    @BindView(R.id.phone_num_tv)
    EditText mPhoneNumTv;
    @BindView(R.id.user_settings_scroll)
    ScrollView mUserSettingsScroll;
    @BindView(R.id.user_settings_toolbar)
    Toolbar mUserSettingsToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mUserSettingsScroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "强制隐藏键盘");
                mUserSettingsToolbar.setFocusable(true);
                mUserSettingsToolbar.setFocusableInTouchMode(true);
                mUserSettingsToolbar.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mOldPasswordTv.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(mNewPasswordTv.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(mNewPasswordAgainTv.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(mPhoneNumTv.getWindowToken(), 0);
                return false;
            }
        });
    }

    @OnClick({R.id.back_btn, R.id.back_title_btn, R.id.submit_new_password_btn, R.id.submit_new_phone_num_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
            case R.id.back_title_btn:
                finish();
                overridePendingTransition(R.anim.custom_fade_in, R.anim.out_to_right);
                break;
            case R.id.submit_new_password_btn:
                break;
            case R.id.submit_new_phone_num_btn:
                break;
        }
    }
}
