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
import com.datang.datangcarmanager.model.AnnualSurveyInfos;
import com.datang.datangcarmanager.model.CarInfo;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.presentation.activity.MailDetailsActivity;
import com.datang.datangcarmanager.presentation.activity.RemindMailActivity;
import com.datang.datangcarmanager.presentation.adapter.AbnormalSituationListAdapter;
import com.datang.datangcarmanager.presentation.adapter.WarningListAdapter;
import com.datang.datangcarmanager.presentation.view.DefultItemDecoration;
import com.datang.datangcarmanager.presenter.AnnualSurveyPresenter;
import com.datang.datangcarmanager.utils.MyToast;
import com.datang.datangcarmanager.view.IAnnualSurveyView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AnnualSurveyFragment extends Fragment implements IAnnualSurveyView {

    @BindView(R.id.annual_survey_recycler_view)
    RecyclerView mAnnualSurveyRecyclerView;
    @BindView(R.id.annual_survey_recycler_view_frame)
    PtrClassicFrameLayout mAnnualSurveyRecyclerViewFrame;

    private String searchInfo = "";
    private Context mContext;

    private WarningListAdapter mAdapter;
    private RecyclerAdapterWithHF mRealAdapter;

    private List<AnnualSurveyInfos.AnnualSurveyBean> surveyBeanList = new ArrayList<>();
    private AnnualSurveyPresenter mPresenter;
    private boolean loadMore = false;

    public AnnualSurveyFragment(Context context, String info) {
        mContext = context;
        searchInfo = info;
    }

    public static AnnualSurveyFragment newInstance(Context context, String info) {
        return new AnnualSurveyFragment(context, info);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_annual_survey, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new AnnualSurveyPresenter(this.getActivity());
        initListView();
        return view;
    }

    private void initListView() {
        mAdapter = new WarningListAdapter(mContext, WarningListAdapter.ANNUAL_SURVEY_LIST);
        mAdapter.setmAnnualSurveyInfos(surveyBeanList);
        mRealAdapter = new RecyclerAdapterWithHF(mAdapter);
        mRealAdapter.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                AnnualSurveyInfos.AnnualSurveyBean bean = surveyBeanList.get(position);
                showDetial(bean);
            }
        });
        mAnnualSurveyRecyclerView.setLayoutManager(
                new LinearLayoutManager(mContext, OrientationHelper.VERTICAL, false));
        mAnnualSurveyRecyclerView.addItemDecoration(new DefultItemDecoration(mContext,
                LinearLayoutManager.VERTICAL));
        mAnnualSurveyRecyclerView.setAdapter(mRealAdapter);
        mAnnualSurveyRecyclerViewFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAnnualSurveyRecyclerViewFrame.autoRefresh(true);
            }
        }, 500);
        mAnnualSurveyRecyclerViewFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                doAnnualSurveyInfoRequest();
            }
        });
        mAnnualSurveyRecyclerViewFrame.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                doLoadMoreAnnualSurveyInfoRequest();
            }
        });
    }

    private void doLoadMoreAnnualSurveyInfoRequest() {
        mPresenter.loadMoreAnnualSurveyInfo(this);
    }

    private void showDetial(AnnualSurveyInfos.AnnualSurveyBean bean) {
        Intent intent = new Intent(this.getActivity(), MailDetailsActivity.class);
        intent.putExtra(MailDetailsActivity.TAG, bean.getRecUid());
        intent.putExtra(MailDetailsActivity.TITLE_TAG, "保险年检处理详情");
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right, android.R.anim.fade_out);
    }

    public void pageChanged(int position) {
        if (mPresenter != null) {
            if (position == RemindMailActivity.VIEW_ANNUAL_SURVEY) {
                if (surveyBeanList.size() == 0) {
                    doAnnualSurveyInfoRequest();
                    mAnnualSurveyRecyclerViewFrame.autoRefresh(true);
                } else if (!searchInfo.equals(mPresenter.getSearchMessage())) {
                    doAnnualSurveyInfoRequest();
                    mAnnualSurveyRecyclerViewFrame.autoRefresh(true);
                }
            }
        }
    }

    private void doAnnualSurveyInfoRequest() {
        Log.i("Annual", "Request");
        mPresenter.getAnnualSurveyInfo(this, searchInfo);
    }

    public void searchInfoChanged(int currentPage, String searchInfo) {
        this.searchInfo = searchInfo;
        if (currentPage == RemindMailActivity.VIEW_ANNUAL_SURVEY) {
            doAnnualSurveyInfoRequest();
            mAnnualSurveyRecyclerViewFrame.autoRefresh(true);
        }
    }

    @Override
    public void getAnnualSurveyInfosSuccess(Responce<AnnualSurveyInfos> responce, boolean loadMore) {
        Log.i("Annual", "Request get");
        surveyBeanList.clear();
        surveyBeanList.addAll(responce.getDetail().getDataList());
        if (surveyBeanList.size() == 0) {
            MyToast.showToastShort("没有您要超照的数据。");
        }
        mAdapter.notifyDataSetChanged();
        mAnnualSurveyRecyclerViewFrame.refreshComplete();
        this.loadMore = loadMore;
        mAnnualSurveyRecyclerViewFrame.setLoadMoreEnable(loadMore);
    }

    @Override
    public void loadMoreAnnualSurveyInfosSuccess(Responce<AnnualSurveyInfos> responce, boolean loadMore) {
        surveyBeanList.addAll(responce.getDetail().getDataList());
        mAdapter.notifyDataSetChanged();
        this.loadMore = loadMore;
        mAnnualSurveyRecyclerViewFrame.loadMoreComplete(loadMore);
    }

    @Override
    public void loadError() {
        if (mAnnualSurveyRecyclerViewFrame.isLoadingMore()) {
            mAnnualSurveyRecyclerViewFrame.loadMoreComplete(loadMore);
        }
        if (mAnnualSurveyRecyclerViewFrame.isRefreshing()) {
            mAnnualSurveyRecyclerViewFrame.refreshComplete();
        }
    }
}
