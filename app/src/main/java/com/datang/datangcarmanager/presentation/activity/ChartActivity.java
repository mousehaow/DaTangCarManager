package com.datang.datangcarmanager.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.StatisticsAnalyze;
import com.datang.datangcarmanager.presentation.adapter.ChartListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChartActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.left_day_pick_btn)
    TextView mLeftDayPickBtn;
    @BindView(R.id.date_show_tv)
    TextView mDateShowTv;
    @BindView(R.id.right_day_pick_btn)
    TextView mRightDayPickBtn;
    @BindView(R.id.chart_recycler_view)
    RecyclerView mChartRecyclerView;

    private List<StatisticsAnalyze> data = new ArrayList<>();

    private ChartListAdapter mAdapter;

    private Date selectedDate;

    private Calendar calendar;

    private SimpleDateFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ButterKnife.bind(this);
        initDatePickView();
        initList();
    }

    private void initList() {
        mAdapter = new ChartListAdapter(this, data);
        mChartRecyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        mChartRecyclerView.setAdapter(mAdapter);
    }

    private void initDatePickView() {
        formatter = new SimpleDateFormat("yyyy-MM");
        calendar = new GregorianCalendar();
        selectedDate = new Date();
        matchPickBtn();
    }

    @OnClick({R.id.back_btn, R.id.back_title_btn, R.id.left_day_pick_btn, R.id.right_day_pick_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
            case R.id.back_title_btn:
                finish();
                overridePendingTransition(R.anim.custom_fade_in, R.anim.out_to_right);
                break;
            case R.id.left_day_pick_btn:
                calendar.setTime(selectedDate);
                calendar.add(calendar.MONTH, -1);
                selectedDate = calendar.getTime();
                matchPickBtn();
                break;
            case R.id.right_day_pick_btn:
                calendar.setTime(selectedDate);
                calendar.add(calendar.MONTH, 1);
                selectedDate = calendar.getTime();
                matchPickBtn();
                break;
        }
    }
    private void matchPickBtn() {
        mDateShowTv.setText(formatter.format(selectedDate));
        calendar.setTime(selectedDate);
        calendar.add(calendar.MONTH, 0);
        mLeftDayPickBtn.setText((calendar.getTime().getMonth() == 0 ? 12 : calendar.getTime().getMonth()) + "月");
        calendar.add(calendar.MONTH, 2);
        mRightDayPickBtn.setText((calendar.getTime().getMonth() == 0 ? 12 : calendar.getTime().getMonth()) + "月");
        if (formatter.format(selectedDate).equals(formatter.format(new Date()))) {
            mLeftDayPickBtn.setTextColor(getResources().getColor(R.color.black));
            mLeftDayPickBtn.setClickable(true);
            mRightDayPickBtn.setTextColor(getResources().getColor(R.color.grey_light));
            mRightDayPickBtn.setClickable(false);
            return;
        }
        if (selectedDate.before(new Date())) {
            mLeftDayPickBtn.setTextColor(getResources().getColor(R.color.black));
            mLeftDayPickBtn.setClickable(true);
            mRightDayPickBtn.setTextColor(getResources().getColor(R.color.black));
            mRightDayPickBtn.setClickable(true);
        }
    }
}
