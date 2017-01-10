package com.datang.datangcarmanager.presentation.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.AbnormalSituation;
import com.datang.datangcarmanager.model.CarInfo;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.presentation.activity.MailDetailsActivity;
import com.datang.datangcarmanager.presentation.activity.RemindMailActivity;
import com.datang.datangcarmanager.presentation.activity.SingleCarInfoActivity;
import com.datang.datangcarmanager.presentation.adapter.AbnormalSituationListAdapter;
import com.datang.datangcarmanager.presentation.adapter.WarningListAdapter;
import com.datang.datangcarmanager.presentation.view.DefultItemDecoration;
import com.datang.datangcarmanager.presenter.TroubleRepairAlarmPresenter;
import com.datang.datangcarmanager.utils.MyToast;
import com.datang.datangcarmanager.view.ITroubleRepairAlarmView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaintainFragment extends Fragment implements ITroubleRepairAlarmView {

    @BindView(R.id.maintain_recycler_view)
    RecyclerView mMaintainRecyclerView;
    @BindView(R.id.maintain_recycler_view_frame)
    PtrClassicFrameLayout mMaintainRecyclerViewFrame;

    private String searchInfo = "";
    private Context mContext;

    private WarningListAdapter mAdapter;
    private RecyclerAdapterWithHF mRealAdapter;

    private List<AbnormalSituation.AbnormalSituationBean> troubleRepairAlarms = new ArrayList<>();

    private TroubleRepairAlarmPresenter mPresenter;
    private boolean loadMore = false;

    public MaintainFragment(Context context, String info) {
        mContext = context;
        searchInfo = info;
    }

    public static MaintainFragment newInstance(Context context, String info) {
        return new MaintainFragment(context, info);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maintain, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new TroubleRepairAlarmPresenter(this.getActivity());
        initListView();
        return view;
    }

    private void initListView() {
        mAdapter = new WarningListAdapter(mContext, WarningListAdapter.MAINTAIN_LIST);
        mAdapter.setmWarningInfos(troubleRepairAlarms);
        mRealAdapter = new RecyclerAdapterWithHF(mAdapter);
        mRealAdapter.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                AbnormalSituation.AbnormalSituationBean bean = troubleRepairAlarms.get(position);
                showDetial(bean);
            }
        });
        mMaintainRecyclerView.setLayoutManager(
                new LinearLayoutManager(mContext, OrientationHelper.VERTICAL, false));
        mMaintainRecyclerView.addItemDecoration(new DefultItemDecoration(mContext,
                LinearLayoutManager.VERTICAL));
        mMaintainRecyclerView.setAdapter(mRealAdapter);
        mMaintainRecyclerViewFrame.setLoadMoreEnable(false);
        mMaintainRecyclerViewFrame.postDelayed(new Runnable() {
            @Override
            public void run() {

                mMaintainRecyclerViewFrame.autoRefresh(true);
            }
        }, 500);
        mMaintainRecyclerViewFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                doTroubleRepairAlarmInfoRequest();
            }
        });
        mMaintainRecyclerViewFrame.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                doLoadMoreTroubleRepairAlarmInfoRequest();
            }
        });
    }

    private void showDetial(AbnormalSituation.AbnormalSituationBean bean) {
        Intent intent = new Intent(this.getActivity(), MailDetailsActivity.class);
        intent.putExtra(MailDetailsActivity.TAG, bean.getAlarmCostId());
        intent.putExtra(MailDetailsActivity.TITLE_TAG, "故障维修详情");
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right, android.R.anim.fade_out);
    }

    public void pageChanged(int position) {
        if (mPresenter != null) {
            if (position == RemindMailActivity.VIEW_MAINTAIN) {
                if (troubleRepairAlarms.size() == 0) {
                    doTroubleRepairAlarmInfoRequest();
                    mMaintainRecyclerViewFrame.autoRefresh(true);
                } else if (!searchInfo.equals(mPresenter.getSearchMessage())) {
                    doTroubleRepairAlarmInfoRequest();
                    mMaintainRecyclerViewFrame.autoRefresh(true);
                }
            }
        }
    }

    private void doTroubleRepairAlarmInfoRequest() {
        mPresenter.getTroubleRepairAlarmInfo(this, searchInfo);
    }

    private void doLoadMoreTroubleRepairAlarmInfoRequest() {
        mPresenter.loadMoreTroubleRepairAlarmInfo(this);
    }

    public void searchInfoChanged(int currentPage, String searchInfo) {
        this.searchInfo = searchInfo;
        if (currentPage == RemindMailActivity.VIEW_MAINTAIN) {
            doTroubleRepairAlarmInfoRequest();
            mMaintainRecyclerViewFrame.autoRefresh(true);
        }
    }

    @Override
    public void getTroubleRepairAlarmSuccess(Responce<AbnormalSituation> responce, boolean loadMore) {
        troubleRepairAlarms.clear();
        troubleRepairAlarms.addAll(responce.getDetail().getDataList());
        if (troubleRepairAlarms.size() == 0) {
            MyToast.showToastShort("没有您要超照的数据。");
        }
        mAdapter.notifyDataSetChanged();
        mMaintainRecyclerViewFrame.refreshComplete();
        this.loadMore = loadMore;
        mMaintainRecyclerViewFrame.setLoadMoreEnable(loadMore);
    }

    @Override
    public void loadMoreTroubleRepairAlarmInfoSuccess(Responce<AbnormalSituation> responce, boolean loadMore) {
        troubleRepairAlarms.addAll(responce.getDetail().getDataList());
        mAdapter.notifyDataSetChanged();
        this.loadMore = loadMore;
        mMaintainRecyclerViewFrame.loadMoreComplete(loadMore);
    }


    @Override
    public void loadError() {
        if (mMaintainRecyclerViewFrame.isLoadingMore()) {
            mMaintainRecyclerViewFrame.loadMoreComplete(loadMore);
        }
        if (mMaintainRecyclerViewFrame.isRefreshing()) {
            mMaintainRecyclerViewFrame.refreshComplete();
        }
    }
}
