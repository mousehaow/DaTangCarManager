package com.datang.datangcarmanager.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.Department;
import com.datang.datangcarmanager.model.StatisticsAnalyze;
import com.datang.datangcarmanager.presentation.adapter.AbstractTreeListAdapter;
import com.datang.datangcarmanager.presentation.adapter.StatisticsAnalyzeListAdapter;
import com.datang.datangcarmanager.presentation.view.DefultItemDecoration;
import com.datang.datangcarmanager.presentation.view.DepartmentSelectPopWindow;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatisticsAnalyzeActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.left_day_pick_btn)
    TextView mLeftDayPickBtn;
    @BindView(R.id.date_show_tv)
    TextView mDateShowTv;
    @BindView(R.id.right_day_pick_btn)
    TextView mRightDayPickBtn;
    @BindView(R.id.department_select_btn)
    TextView mDepartmentSelectBtn;
    @BindView(R.id.statistics_analyze_recycler_view)
    RecyclerView mStatisticsAnalyzeRecyclerView;
    @BindView(R.id.statistics_analyze_recycler_view_frame)
    PtrClassicFrameLayout mStatisticsAnalyzeRecyclerViewFrame;

    private List<Department> departmentList = new ArrayList<>();

    private List<StatisticsAnalyze> analyzeList = new ArrayList<>();

    private StatisticsAnalyzeListAdapter mAdapter;
    private RecyclerAdapterWithHF mRealAdapter;

    private DepartmentSelectPopWindow mSelectPopWindow;

    private Date selectedDate;

    private Calendar calendar;

    private SimpleDateFormat formatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_analyze);
        ButterKnife.bind(this);
        mSelectPopWindow = new DepartmentSelectPopWindow(this, departmentList);
        mSelectPopWindow.setItemListener(new AbstractTreeListAdapter.OnItemSelectListener() {
            @Override
            public void onItemClick(int id) {

            }
        });
        initList();
        initDatePickView();
    }

    private void initList() {
        mAdapter = new StatisticsAnalyzeListAdapter(this, analyzeList);
        mRealAdapter = new RecyclerAdapterWithHF(mAdapter);
        mStatisticsAnalyzeRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                OrientationHelper.VERTICAL, false));
        mStatisticsAnalyzeRecyclerView.addItemDecoration(new DefultItemDecoration(this,
                LinearLayoutManager.VERTICAL));
        mStatisticsAnalyzeRecyclerView.setAdapter(mRealAdapter);
        mStatisticsAnalyzeRecyclerViewFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mStatisticsAnalyzeRecyclerViewFrame.autoRefresh(true);
            }
        }, 500);
        mStatisticsAnalyzeRecyclerViewFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mStatisticsAnalyzeRecyclerViewFrame.refreshComplete();
                mStatisticsAnalyzeRecyclerViewFrame.setLoadMoreEnable(true);
            }
        });
        mStatisticsAnalyzeRecyclerViewFrame.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                mStatisticsAnalyzeRecyclerViewFrame.loadMoreComplete(true);
            }
        });
    }

    private void initDatePickView() {
        formatter = new SimpleDateFormat("yyyy-MM");
        calendar = new GregorianCalendar();
        selectedDate = new Date();
        matchPickBtn();
    }

    @OnClick({R.id.back_btn,
            R.id.back_title_btn,
            R.id.more_info_btn,
            R.id.left_day_pick_btn,
            R.id.right_day_pick_btn,
            R.id.department_select_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
            case R.id.back_title_btn:
                finish();
                break;
            case R.id.more_info_btn:
                Intent intent = new Intent(StatisticsAnalyzeActivity.this, StatisticsAnalyzeMoreActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, android.R.anim.fade_out);
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
            case R.id.department_select_btn:
                mSelectPopWindow.setWidth(this.getWindowManager().getDefaultDisplay().getWidth());
                mSelectPopWindow.setHeight(this.getWindowManager().getDefaultDisplay().getHeight() / 2);
                mSelectPopWindow.showAsDropDown(mDepartmentSelectBtn, 20, 20);
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
