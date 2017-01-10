package com.datang.datangcarmanager.presentation.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.datang.datangcarmanager.presentation.activity.EnterpriseCarActivity;
import com.datang.datangcarmanager.presentation.activity.MailDetailsActivity;
import com.datang.datangcarmanager.presentation.activity.RemindMailActivity;
import com.datang.datangcarmanager.presentation.activity.SingleCarInfoActivity;
import com.datang.datangcarmanager.presentation.adapter.AbnormalSituationListAdapter;
import com.datang.datangcarmanager.presentation.view.DefultItemDecoration;
import com.datang.datangcarmanager.presenter.AbnormalSituationPresenter;
import com.datang.datangcarmanager.utils.MyToast;
import com.datang.datangcarmanager.view.IAbnormalSituationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbnormalSituationFragment extends Fragment implements IAbnormalSituationView {

    @BindView(R.id.abnormal_situation_recycler_view)
    RecyclerView mAbnormalSituationRecyclerView;
    @BindView(R.id.abnormal_situation_recycler_view_frame)
    PtrClassicFrameLayout mAbnormalSituationRecyclerViewFrame;


    private String searchInfo = "";
    private Context mContext;

    private AbnormalSituationListAdapter mAdapter;
    private RecyclerAdapterWithHF mRealAdapter;

    private AbnormalSituationPresenter mPresenter;

    private boolean loadMore = false;

    private List<AbnormalSituation.AbnormalSituationBean> abnormalSituationBeen = new ArrayList<>();

    public AbnormalSituationFragment(Context context, String info) {
        searchInfo = info;
        mContext = context;
    }

    public static AbnormalSituationFragment newInstance(Context context, String info) {
        return new AbnormalSituationFragment(context, info);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_abnormal_situation, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new AbnormalSituationPresenter(this.getContext());
        initListView();
        return view;
    }

    private void initListView() {
        mAdapter = new AbnormalSituationListAdapter(mContext, abnormalSituationBeen);
        mRealAdapter = new RecyclerAdapterWithHF(mAdapter);
        mRealAdapter.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                AbnormalSituation.AbnormalSituationBean bean = abnormalSituationBeen.get(position);
                showDetial(bean);
            }
        });
        mAbnormalSituationRecyclerView.setLayoutManager(
                new LinearLayoutManager(mContext, OrientationHelper.VERTICAL, false));
        mAbnormalSituationRecyclerView.addItemDecoration(new DefultItemDecoration(mContext,
                LinearLayoutManager.VERTICAL));
        mAbnormalSituationRecyclerView.setAdapter(mRealAdapter);
        mAbnormalSituationRecyclerViewFrame.setLoadMoreEnable(false);
        mAbnormalSituationRecyclerViewFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAbnormalSituationRecyclerViewFrame.autoRefresh(true);
            }
        }, 500);
        mAbnormalSituationRecyclerViewFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                doAbnormalSituationInfoRequest();
            }
        });
        mAbnormalSituationRecyclerViewFrame.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                doLoadMoreAbnormalSituationInfoRequest();
            }
        });
    }

    private void showDetial(AbnormalSituation.AbnormalSituationBean bean) {
        Intent intent = new Intent(this.getActivity(), MailDetailsActivity.class);
        intent.putExtra(MailDetailsActivity.TAG, bean.getAlarmCostId());
        intent.putExtra(MailDetailsActivity.TITLE_TAG, "异常用车详情");
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right, android.R.anim.fade_out);
    }

    private void doLoadMoreAbnormalSituationInfoRequest() {
        mPresenter.loadMoreAbnormalSituationInfo(this);
    }

    public void pageChanged(int position) {
        if (mPresenter != null) {
            if (position == RemindMailActivity.VIEW_ABNORMAIL_SITUATION) {
                if (abnormalSituationBeen.size() == 0) {
                    doAbnormalSituationInfoRequest();
                    mAbnormalSituationRecyclerViewFrame.autoRefresh(true);
                } else if (!searchInfo.equals(mPresenter.getSearchMessage())) {
                    doAbnormalSituationInfoRequest();
                    mAbnormalSituationRecyclerViewFrame.autoRefresh(true);
                }
            }
        }
    }

    private void doAbnormalSituationInfoRequest() {
        mPresenter.getAbnormalSituationInfo(this, searchInfo);
    }

    @Override
    public void getAbnormalSituationInfoSuccess(Responce<AbnormalSituation> responce, boolean loadMore) {
        abnormalSituationBeen.clear();
        abnormalSituationBeen.addAll(responce.getDetail().getDataList());
        if (abnormalSituationBeen.size() == 0) {
            MyToast.showToastShort("没有您要超照的数据。");
        }
        mAdapter.notifyDataSetChanged();
        mAbnormalSituationRecyclerViewFrame.refreshComplete();
        this.loadMore = loadMore;
        mAbnormalSituationRecyclerViewFrame.setLoadMoreEnable(loadMore);
    }

    @Override
    public void loadMoreAbnormalSituationInfoSuccess(Responce<AbnormalSituation> responce, boolean loadMore) {
        abnormalSituationBeen.addAll(responce.getDetail().getDataList());
        mAdapter.notifyDataSetChanged();
        this.loadMore = loadMore;
        mAbnormalSituationRecyclerViewFrame.loadMoreComplete(loadMore);
    }

    @Override
    public void loadError() {
        if (mAbnormalSituationRecyclerViewFrame.isLoadingMore()) {
            mAbnormalSituationRecyclerViewFrame.loadMoreComplete(loadMore);
        }
        if (mAbnormalSituationRecyclerViewFrame.isRefreshing()) {
            mAbnormalSituationRecyclerViewFrame.refreshComplete();
        }
    }

    public void searchInfoChanged(int currentPage, String searchInfo) {
        this.searchInfo = searchInfo;
        if (currentPage == RemindMailActivity.VIEW_ABNORMAIL_SITUATION) {
            doAbnormalSituationInfoRequest();
            mAbnormalSituationRecyclerViewFrame.autoRefresh(true);
        }
    }
}
