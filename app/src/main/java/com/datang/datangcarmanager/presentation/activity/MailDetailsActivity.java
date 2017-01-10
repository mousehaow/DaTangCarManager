package com.datang.datangcarmanager.presentation.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.AlarmCostDetails;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.presentation.adapter.MailDetailsListAdapter;
import com.datang.datangcarmanager.presentation.view.DefultItemDecoration;
import com.datang.datangcarmanager.presenter.AlarmCostDetailsPresenter;
import com.datang.datangcarmanager.utils.MyToast;
import com.datang.datangcarmanager.view.IAlarmCostDetailsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MailDetailsActivity extends BaseActivity implements IAlarmCostDetailsView {

    public static final String TAG = "MailDetailsActivity";
    public static final String TITLE_TAG = "Title";

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.mail_details_recycler_view)
    RecyclerView mMailDetailsRecyclerView;

    private MailDetailsListAdapter mailDetailsListAdapter;

    private AlarmCostDetailsPresenter mPresenter;

    private String detailId;

    private String title;

    private List<Map<String, String>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_details);
        detailId = getIntent().getStringExtra(TAG);
        title = getIntent().getStringExtra(TITLE_TAG);
        Log.i(TAG, title);
        ButterKnife.bind(this);
        mTitle.setText(title);
        mPresenter = new AlarmCostDetailsPresenter(this);
        mailDetailsListAdapter = new MailDetailsListAdapter(this, data);
        mailDetailsListAdapter.setListener(new MailDetailsListAdapter.TrackBtnOnClickListener() {
            @Override
            public void buttonClicked() {

            }
        });
        mMailDetailsRecyclerView.setAdapter(mailDetailsListAdapter);
        mMailDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        mMailDetailsRecyclerView.addItemDecoration(new DefultItemDecoration(this, LinearLayoutManager.VERTICAL));
        mPresenter.getAlarmCostDetailsInfo(this, detailId);
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

    @Override
    public void getAlarmCostDetailsInfoSuccess(Responce<AlarmCostDetails> responce) {
        data.clear();
        if (title.equals("异常用车详情")) {
            Map<String, String> a = new HashMap<>();
            a.put("车牌号", responce.getDetail().getLpno());
            data.add(a);
            Map<String, String> b = new HashMap<>();
            b.put("时间", responce.getDetail().getAlarmTime());
            data.add(b);
            Map<String, String> c = new HashMap<>();
            c.put("部门", responce.getDetail().getDeptName());
            data.add(c);
            Map<String, String> d = new HashMap<>();
            d.put("驾驶员", responce.getDetail().getDriverName());
            data.add(d);
            Map<String, String> e = new HashMap<>();
            e.put("报警类型", responce.getDetail().getAlarmType());
            data.add(e);
            Map<String, String> f = new HashMap<>();
            f.put("具体描述", responce.getDetail().getDescription());
            data.add(f);
            Map<String, String> g = new HashMap<>();
            g.put("轨迹追踪", "");
            data.add(g);
        } else if (title.equals("故障维修详情")) {
            Map<String, String> a = new HashMap<>();
            a.put("车牌号", responce.getDetail().getLpno());
            data.add(a);
            Map<String, String> b = new HashMap<>();
            b.put("时间", responce.getDetail().getAlarmTime());
            data.add(b);
            Map<String, String> c = new HashMap<>();
            c.put("部门", responce.getDetail().getDeptName());
            data.add(c);
            Map<String, String> e = new HashMap<>();
            e.put("报警类型", responce.getDetail().getAlarmType());
            data.add(e);
            Map<String, String> f = new HashMap<>();
            f.put("具体描述", responce.getDetail().getDescription());
            data.add(f);
        } else if (title.equals("保险年检处理详情")) {
            Map<String, String> a = new HashMap<>();
            a.put("车牌号", responce.getDetail().getLpno());
            data.add(a);
            Map<String, String> c = new HashMap<>();
            c.put("部门", responce.getDetail().getDeptName());
            data.add(c);
            Map<String, String> b = new HashMap<>();
            b.put("当前里程", responce.getDetail().getCurrentMiles() + " 公里");
            data.add(b);
        }
        mailDetailsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadError() {
        MyToast.showToastShort("网络错误，正在为您重新请求数据。");
        mPresenter.getAlarmCostDetailsInfo(this, detailId);
    }
}
