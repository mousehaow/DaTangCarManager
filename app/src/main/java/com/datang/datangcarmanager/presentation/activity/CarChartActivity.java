package com.datang.datangcarmanager.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.github.mikephil.charting.charts.BarChart;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarChartActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.left_day_pick_btn)
    TextView mLeftDayPickBtn;
    @BindView(R.id.date_show_tv)
    TextView mDateShowTv;
    @BindView(R.id.right_day_pick_btn)
    TextView mRightDayPickBtn;
    @BindView(R.id.oil_chart)
    BarChart mOilChart;
    @BindView(R.id.mileage_chart)
    BarChart mMileageChart;
    @BindView(R.id.time_chart)
    BarChart mTimeChart;

    private Date selectedDate;

    private Calendar calendar;

    private SimpleDateFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_chart);
        ButterKnife.bind(this);
        initDatePickView();
        initChartView();
    }

    private void initChartView() {

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
