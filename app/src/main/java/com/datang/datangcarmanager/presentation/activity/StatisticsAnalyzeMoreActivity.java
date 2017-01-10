package com.datang.datangcarmanager.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.StatisticsAnalyze;
import com.datang.datangcarmanager.presentation.adapter.StatisticsAnalyzeGridAdapter;
import com.datang.datangcarmanager.presentation.view.BadgeView;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatisticsAnalyzeMoreActivity extends BaseActivity {

    public static final String TAG = "StatisticsMoreActivity";

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.left_day_pick_btn)
    TextView mLeftDayPickBtn;
    @BindView(R.id.date_show_tv)
    TextView mDateShowTv;
    @BindView(R.id.right_day_pick_btn)
    TextView mRightDayPickBtn;
    @BindView(R.id.statistics_analyze_more_recycler_view)
    RecyclerView mStatisticsAnalyzeMoreRecyclerView;

    private StatisticsAnalyzeGridAdapter mAdapter;

    private Date selectedDate;

    private Calendar calendar;

    private SimpleDateFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_analyze_more);
        ButterKnife.bind(this);
        initList();
        initDatePickView();
    }

    private void initList() {
        mAdapter = new StatisticsAnalyzeGridAdapter(this, new StatisticsAnalyze());
        mAdapter.setListener(new StatisticsAnalyzeGridAdapter.OnGridItemClickedListener() {
            @Override
            public void itemClicked(int position) {
                switch (position) {
                    /* 里程 */
                    case 0:

                        break;
                    /* 用车时间 */
                    case 1:

                        break;
                    /* 耗油量 */
                    case 2:

                        break;
                    /* 机车拆除 */
                    case 3:

                        break;
                    /* 怠速时长 */
                    case 4:

                        break;
                    /* 越界 */
                    case 5:

                        break;
                    /* 未匹配用车 */
                    case 6:

                        break;
                    /* 违章 */
                    case 7:

                        break;
                    /* 超速 */
                    case 8:

                        break;
                    default:
                        break;
                }
            }
        });
        mStatisticsAnalyzeMoreRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mStatisticsAnalyzeMoreRecyclerView.setAdapter(mAdapter);

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
