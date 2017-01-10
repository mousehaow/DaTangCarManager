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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.datang.datangcarmanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuggestActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.suggest_toolbar)
    Toolbar mSuggestToolbar;
    @BindView(R.id.suggest_edit_text)
    EditText mSuggestEditText;
    @BindView(R.id.suggest_layout)
    LinearLayout mSuggestLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        ButterKnife.bind(this);
        mSuggestLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mSuggestToolbar.setFocusable(true);
                mSuggestToolbar.setFocusableInTouchMode(true);
                mSuggestToolbar.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mSuggestEditText.getWindowToken(), 0);
                return false;
            }
        });
    }

    @OnClick({R.id.back_btn, R.id.back_title_btn, R.id.submit_suggest_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
            case R.id.back_title_btn:
                finish();
                overridePendingTransition(R.anim.custom_fade_in, R.anim.out_to_right);
                break;
            case R.id.submit_suggest_btn:
                break;
        }
    }
}
